package com.xuan.moho.net.httpclient.core;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.xuan.moho.net.httpclient.HttpClient;
import com.xuan.moho.net.httpclient.HttpRequest;
import com.xuan.moho.net.httpclient.HttpResponse;

/**
 * 用UrlConnect方式实现HTTP请求
 * <p>
 * Created by xuan on 16/2/24.
 */
public class HttpClientUrlConnectionImpl implements HttpClient {

    /**
     * 缓存字节数
     */
    private static final int BUFFER_SIZE = 1024;
    /**
     * 表单POST提交
     */
    private static final String METHOD_POST = "POST";

    @Override
    public HttpResponse getDowload(HttpRequest request) {
        try {
            URL url = new URL(request.getGetUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            initRequest(conn, request);

            return readResponseForFile(conn, request);
        } catch (Exception e) {
            e.printStackTrace();
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    @Override
    public HttpResponse postDowload(HttpRequest request) {
        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // POST模式
            conn.setRequestMethod(METHOD_POST);
            //发送POST请求必须设置如下
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            initRequest(conn, request);
            //设置POST参数
            putParamsToOutputStream(conn, request);

            return readResponseForFile(conn, request);
        } catch (Exception e) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    @Override
    public HttpResponse get(HttpRequest request) {
        try {
            URL url = new URL(request.getGetUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            initRequest(conn, request);

            return readResponseForString(conn, request.getEncode());
        } catch (Exception e) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    @Override
    public HttpResponse post(HttpRequest request) {
        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // POST模式
            conn.setRequestMethod(METHOD_POST);
            //发送POST请求必须设置如下
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            initRequest(conn, request);
            //设置POST参数
            putParamsToOutputStream(conn, request);

            return readResponseForString(conn, request.getEncode());
        } catch (Exception e) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    @Override
    public HttpResponse postJson(HttpRequest request) {
        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // POST模式
            conn.setRequestMethod(METHOD_POST);
            //发送POST请求必须设置如下
            conn.setDoOutput(true);
            initRequest(conn, request);
            //设置POST参数
            putParamsToOutputStreamForJson(conn, request);

            return readResponseForString(conn, request.getEncode());
        } catch (Exception e) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    @Override
    public HttpResponse upload(HttpRequest request) {
        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            initRequest(conn, request);
            putBodyEntityToOutputStream(conn, request);

            return readResponseForString(conn, request.getEncode());
        } catch (Exception e) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
            return response;
        }
    }

    private void initRequest(URLConnection conn, HttpRequest request) {
        //头部设置
        for (Map.Entry<String, String> entry : request.getHeaderMap().entrySet()) {
            conn.addRequestProperty(entry.getKey(), entry.getValue());
        }

        //超时设置
        conn.setConnectTimeout(request.getConnectionTimeout());
        conn.setReadTimeout(request.getReadTimeout());
    }

    private static void putBodyEntityToOutputStream(HttpURLConnection conn, HttpRequest request) {
        try {
            UrlMultipartEntity bodyEntity = new UrlMultipartEntity();
            bodyEntity.writeDataToBody(conn, request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void putParamsToOutputStreamForJson(HttpURLConnection conn, HttpRequest request) {
        String bodyJson = request.getBodyJson();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(conn.getOutputStream());
            dos.write(bodyJson.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (null != dos) {
                    dos.flush();
                    dos.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }
    }

    private static void putParamsToOutputStream(HttpURLConnection conn, HttpRequest request) {
        String paramStr = request.getParamsStr();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(conn.getOutputStream());
            //dos.writeBytes(paramStr);
            dos.write(paramStr.getBytes());
        } catch (Exception e) {
            //Ignore
        } finally {
            try {
                if (null != dos) {
                    dos.flush();
                    dos.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }
    }

    private static HttpResponse readResponseForFile(HttpURLConnection conn, HttpRequest request) {
        boolean hasListener = false;
        if (null != request.getDownloadListener()) {
            //设置了下载监听,需要非压缩的方式下载,这样可以提前计算出文件大小
            hasListener = true;
        }

        HttpResponse response = new HttpResponse();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            //当前字节量
            int curCount = 0;
            //总字节量
            int total = -1;
            if (hasListener) {
                total = conn.getContentLength();
            }

            while ((len = inStream.read(buffer)) != -1) {
                curCount += len;
                if (hasListener) {
                    // 加载中回调
                    request.getDownloadListener().callBack(total, curCount, false);
                }
                outStream.write(buffer, 0, len);
            }
            if (hasListener) {
                // 加载中回调
                request.getDownloadListener().callBack(total, curCount, true);
            }

            response.setStatusCode(conn.getResponseCode());
            response.setReasonPhrase(conn.getResponseMessage());
            byte[] data = outStream.toByteArray();

            File file = new File(request.getDownloadFileName());
            writeByteArrayToFile(file, data, false);
            response.setResultFile(file);
        } catch (Exception e) {
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
        } finally {
            try {
                outStream.close();
                if (null != inStream) {
                    inStream.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }

        return response;
    }

    private static HttpResponse readResponseForString(HttpURLConnection conn, String encode) {
        HttpResponse response = new HttpResponse();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();

            response.setStatusCode(conn.getResponseCode());
            response.setReasonPhrase(conn.getResponseMessage());
            response.setResultStr(new String(data, encode));
        } catch (Exception e) {
            response.setStatusCode(HttpResponse.STATUS_CODE_FAIL);
            response.setReasonPhrase(e.getMessage());
        } finally {
            try {
                outStream.close();
                if (null != inStream) {
                    inStream.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }

        return response;
    }

    private static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            out.write(data);
        } finally {
            closeQuietly(out);
        }
    }

    private static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(String.format("file %s exists but is a directory.", file.getPath()));
            }
            if (!file.canWrite()) {
                throw new IOException(String.format("file %s cannot be written to.", file.getPath()));
            }
        } else {
            File parent = file.getParentFile();
            if (null != parent && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException(String.format("directory %s could not be created.", parent.getPath()));
            }
        }
        return new FileOutputStream(file, append);
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

}