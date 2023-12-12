package com.xuan.xutils.geek.code.compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Java代码编译器
 *
 * @author xuan
 * @since 2023/9/28
 */
public class GkJavaCompiler {

    private static final String LOG_FILE_NAME = "compile.log";

    private static final int EOF = -1;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * 缓存文件夹
     */
    private String cacheDir;

    /**
     * 编译日志输出打印者
     */
    private static PrintWriter logPrintWriter;

    public GkJavaCompiler() {
        this(System.getProperty("user.home") + "/.gk_java_compiler/");
    }

    public GkJavaCompiler(String cacheDir) {
        this.cacheDir = cacheDir;
        initCacheDir();
        initLogPrintWriter();
    }

    public Class<?> compile(String fullClassName, String javaCode) {

        //1、 保存Java代码到磁盘
        writeCodeToCacheDir(fullClassName, javaCode);

        //2、 编译成class
        compileSourceCode(fullClassName, javaCode);

        //3、 加载class到内存
        return loadClazz(fullClassName);
    }

    private void initCacheDir() {
        if (!cacheDir.endsWith(File.separator)) {
            cacheDir += File.separator;
        }

        createDir(cacheDir);
    }

    private void initLogPrintWriter() {

        File logFile = new File(cacheDir + LOG_FILE_NAME);
        createFile(logFile);

        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(logFile), Charset.forName("UTF-8"));
            logPrintWriter = new PrintWriter(osw, true);
        } catch (Exception e) {
            throw new RuntimeException(
                String.format("Create PrintWriter exception. logFile[%s]", logFile.getAbsolutePath()), e);
        }
    }

    private void writeCodeToCacheDir(String fullClassName, String sourceCode) {

        //0、基础参数校验
        if (null == fullClassName || fullClassName.trim().length() == 0) {
            throw new RuntimeException("fullClassName is empty");
        }

        if (null == sourceCode || sourceCode.trim().length() == 0) {
            throw new RuntimeException("sourceCode is empty");
        }

        int index = fullClassName.lastIndexOf(".");
        if (index < 0) {
            throw new RuntimeException(String.format("Invalid fullClassName[%s]", fullClassName));
        }

        //1、初始化缓存文件夹
        createDir(cacheDir);

        //2、初始化类包的文件夹
        String packageName = fullClassName.substring(0, index);
        String packageDir = packageName.replace('.', File.separatorChar);
        createDir(cacheDir + packageDir);

        //3、创建Java文件
        File file = new File(cacheDir, fullClassName.replace('.', File.separatorChar) + ".java");
        createFile(file);

        //4、写入JavaCode
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file))) {
            printWriter.write(sourceCode);
            printWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Write javaCode exceptio. filePath[%s]", file.getAbsolutePath()),
                e);
        }
    }

    private void compileSourceCode(String fullClassName, String sourceCode) {
        //JDK编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        //JDK源代码对象集合
        List<StringJavaFileObject> javaFileObjectList = new ArrayList<>();
        StringJavaFileObject srcObject = new StringJavaFileObject(fullClassName, sourceCode);
        javaFileObjectList.add(srcObject);

        //编译配置项
        Iterable<String> options = Arrays.asList("-d", cacheDir);

        CompilationTask task = compiler.getTask(logPrintWriter, fileManager, null, options, null, javaFileObjectList);
        boolean result = task.call();
        if (!result) {
            throw new RuntimeException(String.format("Compile class error, fullClassName[%s]", fullClassName));
        }
    }

    private Class<?> loadClazz(String fullClassName) {
        try {
            File classFile = new File(cacheDir, fullClassName.replace('.', File.separatorChar) + ".class");
            byte[] classBytes = readFileToByteArray(classFile);

            return CompileClassLoader.getInstance(cacheDir).defineClass(fullClassName, classBytes);
        } catch (Exception e) {
            throw new RuntimeException("CompileClassLoader defineClass exception. fullClassName：" + fullClassName, e);
        }
    }

    private void createDir(String dirStr) {
        if (null == dirStr || dirStr.trim().length() == 0) {
            throw new IllegalArgumentException("dirStr is empty");
        }

        File dirFile = new File(dirStr);
        if (dirFile.exists()) {
            return;
        }
        if (!dirFile.mkdirs()) {
            throw new RuntimeException(String.format("Failed to mkdir[%s]", dirStr));
        }
    }

    private static void createFile(File file) {
        if (file.exists()) {
            return;
        }

        try {
            boolean b = file.createNewFile();
            if (!b) {
                throw new RuntimeException(String.format("Failed to createNewFile[%s]", file.getName()));
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Exception to createNewFile[%s]", file.getName()), e);
        }
    }

    public static byte[] readFileToByteArray(final File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            long fileLength = file.length();
            return fileLength > 0 ? toByteArray(in, (int)fileLength) : toByteArray(in);
        }
    }

    private static byte[] toByteArray(InputStream input, int size) throws IOException {
        byte[] data = new byte[size];
        int offset = 0;
        int read;

        while (offset < size && (read = input.read(data, offset, size - offset)) != EOF) {
            offset += read;
        }

        if (offset != size) {
            throw new RuntimeException(String.format("Unexpected read size. current[%s], expected[%s]", offset, size));
        }

        return data;
    }

    private static byte[] toByteArray(final InputStream input) throws IOException {
        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int n;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            while (EOF != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        }
    }

    private static class StringJavaFileObject extends SimpleJavaFileObject {
        private final String javaCode;

        StringJavaFileObject(String fullClassName, String javaCode) {
            super(URI.create("string:///" + fullClassName.replace('.', '/') + Kind.SOURCE.extension),
                Kind.SOURCE);
            this.javaCode = javaCode;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return javaCode;
        }
    }

    private static class CompileClassLoader extends URLClassLoader {
        private static volatile CompileClassLoader instance = null;

        private CompileClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public static CompileClassLoader getInstance(String dirPath) {
            if (null == instance) {
                synchronized (CompileClassLoader.class) {
                    if (null == instance) {
                        try {
                            URL url = new URL("file:///" + dirPath);
                            instance = new CompileClassLoader(new URL[] {url},
                                CompileClassLoader.class.getClassLoader());
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e.getMessage(), e);
                        }
                    }
                }
            }
            return instance;
        }

        public Class<?> defineClass(String name, byte[] classBytes) {
            return defineClass(name, classBytes, 0, classBytes.length);
        }
    }

}
