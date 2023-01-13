package com.xuan.moho.tools.compiler.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.xuan.moho.tools.compiler.CompileException;
import com.xuan.moho.tools.compiler.core.FileUtil;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class CompilePrintWriter {
    private static final String LOG_FILE_NAME = "compile.log";

    private static PrintWriter instance;

    public static PrintWriter getInstance(String dirOut) {
        if (null == instance) {
            synchronized (CompilePrintWriter.class) {
                if (null == instance) {
                    instance = init(dirOut);
                    return instance;
                }
            }
        }
        return instance;
    }

    private static PrintWriter init(String dirOut) {
        if (!dirOut.endsWith(File.separator)) {
            dirOut += File.separator;
        }

        File logFile = new File(dirOut + LOG_FILE_NAME);
        FileUtil.createFile(logFile);
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(logFile), Charset.forName("UTF-8"));
            instance = new PrintWriter(osw, true);
        } catch (Exception e) {
            throw new CompileException(
                "Create java compile log printWriter exception. file:" + logFile, e);
        }
        return null;
    }

}
