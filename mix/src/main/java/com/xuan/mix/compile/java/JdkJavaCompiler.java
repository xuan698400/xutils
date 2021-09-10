package com.xuan.mix.compile.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.xuan.mix.compile.CompileException;
import com.xuan.mix.compile.CompileConfig;
import com.xuan.mix.compile.Compiler;
import com.xuan.mix.compile.SourceCode;

/**
 * 使用JDK自带实现
 *
 * @author xuan
 * @since 2020/10/19
 */
public class JdkJavaCompiler implements Compiler {

    @Override
    public Class<?> compile(SourceCode sourceCode, CompileConfig compileOption) {

        //1. 保存Java代码到磁盘
        writeCodeToCompileDir(compileOption.getCompileDir(), sourceCode.getFullClassName(), sourceCode.getSourceCode());

        //2. 编译成class
        doCompile(compileOption.getCompileDir(), sourceCode.getFullClassName(), sourceCode.getSourceCode());

        //3. 加载class到内存
        return loadClass(compileOption.getCompileDir(), sourceCode.getFullClassName());
    }

    private void writeCodeToCompileDir(String dirPath, String fullClassName, String sourceCode) {
        //初始化目录
        FileUtil.createDir(dirPath);

        int index = fullClassName.lastIndexOf(".");
        if (index >= 0) {
            //创建类的全路径目录
            String packageName = fullClassName.substring(0, index);
            FileUtil.createPackageDirs(new File(dirPath), packageName);
        }

        //把sourceCode写到.java文件
        File file = new File(dirPath,
            fullClassName.replace('.', File.separatorChar) + ".java");
        FileUtil.createFile(file);
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file))) {
            printWriter.write(sourceCode);
            printWriter.flush();
        } catch (Exception e) {
            throw new CompileException(e.getMessage(), e);
        }
    }

    private void doCompile(String dirPath, String fullClassName, String sourceCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        List<StringJavaFileObject> javaFileObjects = new ArrayList<>();
        StringJavaFileObject srcObject = new StringJavaFileObject(fullClassName,
            sourceCode);
        javaFileObjects.add(srcObject);

        Iterable<String> options = Arrays.asList("-d", dirPath);

        CompilationTask task = compiler.getTask(
            CompileLogHelper.getPrintWriter(dirPath), fileManager, null,
            options, null, javaFileObjects);
        boolean result = task.call();

        if (!result) {
            throw new CompileException("Compile class error, class name is:" + fullClassName);
        }
    }

    private Class<?> loadClass(String dirPath, String fullClassName) {
        try {
            File classFile = new File(dirPath, fullClassName.replace('.', File.separatorChar) + ".class");
            byte[] classBytes = FileUtil.readFileToByteArray(classFile);

            return CompileClassLoader.getInstance(dirPath).defineClass(fullClassName, classBytes);
        } catch (Exception e) {
            throw new CompileException(e.getMessage(), e);
        }
    }

}
