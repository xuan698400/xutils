package com.xuan.moho.tools.compiler.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.xuan.moho.tools.compiler.CompileConfig;
import com.xuan.moho.tools.compiler.CompileException;
import com.xuan.moho.tools.compiler.Compiler;
import com.xuan.moho.tools.compiler.SourceCode;
import com.xuan.moho.tools.compiler.core.FileUtil;

/**
 * 使用JDK自带实现
 *
 * @author xuan
 * @since 2020/10/19
 */
public class JdkJavaCompiler implements Compiler {

    @Override
    public Class<?> compile(SourceCode sourceCode, CompileConfig config) {

        //1. 保存Java代码到磁盘
        writeCodeToCompileDir(config.getCacheDir(), sourceCode.getFullClassName(), sourceCode.getSourceCode());

        //2. 编译成class
        compileSourceCode(config.getCacheDir(), sourceCode.getFullClassName(), sourceCode.getSourceCode());

        //3. 加载class到内存
        return loadClazz(config.getCacheDir(), sourceCode.getFullClassName());
    }

    /**
     * 保存源代码到本地磁盘
     *
     * @param dirPath       保存目录
     * @param fullClassName 类全路径
     * @param sourceCode    源代码
     */
    private void writeCodeToCompileDir(String dirPath, String fullClassName, String sourceCode) {
        //初始化目录
        FileUtil.createDir(dirPath);

        //根据类全路径截取到package路径，并初始化目录
        int index = fullClassName.lastIndexOf(".");
        if (index >= 0) {
            String packageName = fullClassName.substring(0, index);
            FileUtil.createPackageDirs(new File(dirPath), packageName);
        }

        //磁盘创建.java文件，并写入源代码
        File file = new File(dirPath, fullClassName.replace('.', File.separatorChar) + ".java");
        FileUtil.createFile(file);
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file))) {
            printWriter.write(sourceCode);
            printWriter.flush();
        } catch (Exception e) {
            throw new CompileException("write javaCode exception. file:+" + file.getAbsolutePath(), e);
        }
    }

    /**
     * 编译源代码成.class
     *
     * @param outPath       输出目录
     * @param fullClassName 类全路径
     * @param sourceCode    源代码
     */
    private void compileSourceCode(String outPath, String fullClassName, String sourceCode) {
        //JDK编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        //JDK源代码对象集合
        List<StringJavaFileObject> javaFileObjectList = new ArrayList<>();
        StringJavaFileObject srcObject = new StringJavaFileObject(fullClassName, sourceCode);
        javaFileObjectList.add(srcObject);

        //编译配置项
        Iterable<String> options = Arrays.asList("-d", outPath);

        //输出目录
        Writer outWriter = CompilePrintWriter.getInstance(outPath);

        CompilationTask task = compiler.getTask(outWriter, fileManager, null, options, null, javaFileObjectList);
        boolean result = task.call();
        if (!result) {
            throw new CompileException("Compile class error, class name is：" + fullClassName);
        }
    }

    /**
     * 加载.class文件到内存
     *
     * @param dirPath       .class存放目录
     * @param fullClassName 类全路径
     * @return Class对象
     */
    private Class<?> loadClazz(String dirPath, String fullClassName) {
        try {
            File classFile = new File(dirPath, fullClassName.replace('.', File.separatorChar) + ".class");
            byte[] classBytes = FileUtil.readFileToByteArray(classFile);

            return CompileClassLoader.getInstance(dirPath).defineClass(fullClassName, classBytes);
        } catch (Exception e) {
            throw new CompileException("CompileClassLoader defineClass exception. fullClassName：" + fullClassName, e);
        }
    }

}
