package com.xuan.mix.compile.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.xuan.mix.compile.CompileException;

/**
 * @author xuan
 * @since 2020/10/19
 */
class CompileLogHelper {
    private static final String LOG_FILE_NAME = "compile.log";

    private static PrintWriter INSTANCE;

    static PrintWriter getPrintWriter(String dirOut) {
        if (null == INSTANCE) {
            synchronized (CompileLogHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = init(dirOut);
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }

    private static PrintWriter init(String dirOut) {
        if (!dirOut.endsWith(File.separator)) {
            dirOut += File.separator;
        }

        File logFile = new File(dirOut + LOG_FILE_NAME);
        FileUtil.createFile(logFile);
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(logFile), Charset.forName("UTF-8"));
            INSTANCE = new PrintWriter(osw, true);
        } catch (Exception e) {
            throw new CompileException(
                "Create java compile log printWriter exception. file:" + logFile, e);
        }
        return null;
    }

}
