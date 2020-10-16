package com.xuan.mix.http.client.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Map;
import java.util.Random;

import com.xuan.mix.http.client.HttpRequest;

/**
 * 基于URLConnection实现的文件上传请求实体
 *
 * @author xuan
 * @date 2019/5/19
 */
public class UrlMultipartEntity {

    /**
     * 标准的换行符结束
     */
    private static final char[] CLRF = new char[] {'\r', '\n'};

    /**
     * 标准的两个换行符结束
     */
    private static final char[] DOUBLE_CLRF = new char[] {'\r', '\n', '\r', '\n'};

    /**
     * 分割线
     */
    private static final String BOUNDARY_START = "---------------------------HttpAPIFormBoundary";
    
    public void writeDataToBody(HttpURLConnection connection, HttpRequest request) throws Exception {
        String boundary = BOUNDARY_START + new Random().nextLong();

        // Open the connection and set the correct header
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);

        boundary = "--" + boundary;

        OutputStream os = connection.getOutputStream();

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(os);
            for (Map.Entry<String, String> entry : request.getParamMap().entrySet()) {
                // Write the start of our request
                writer.write(boundary);
                writer.write(CLRF);
                writer.write("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
                // Write a newline before the content
                writer.write(DOUBLE_CLRF);
                // Write the data
                writer.write(entry.getValue());

                writer.write(CLRF);
            }
            for (Map.Entry<String, File> entry : request.getFileParamMap().entrySet()) {
                // Write the start of our request
                writer.write(boundary);
                writer.write(CLRF);
                writer.write("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");

                writer.write("; filename=\"" + entry.getValue().getName() + "\"");
                writer.write(CLRF);

                // Get the mime type
                String type = URLConnection.guessContentTypeFromName(entry.getValue().getName());
                if (type == null) {
                    type = "application/octet-stream";
                }

                // Write the mime type
                writer.write("Content-Type: ");
                writer.write(type);
                writer.write(DOUBLE_CLRF);

                writer.flush();

                InputStream input = null;
                try {
                    input = new FileInputStream(entry.getValue());
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int read = input.read(buffer, 0, buffer.length);
                        if (read == -1) {
                            break;
                        }
                        os.write(buffer, 0, read);
                    }
                    os.flush();
                } catch (Exception e) {
                    throw new Exception(e);
                } finally {
                    try {
                        if (null != input) {
                            input.close();
                        }
                    } catch (Exception e) {
                        //Ignore
                    }
                }

                writer.write(CLRF);
            }

            // Set the final boundary
            boundary = boundary + "--";
            // Write a boundary to let the server know the previous content area is finished
            writer.write(boundary);
            // Write a final newline
            writer.write(CLRF);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }
    }

}
