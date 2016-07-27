package com.paem.okhttp.log;

import android.text.TextUtils;

import com.paem.okhttp.utils.L;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by paem on 16/3/1.
 */
public class LoggerInterceptor implements Interceptor
{
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse)
    {
        if (!TextUtils.isEmpty(tag))
        {
            L.TAG=tag;
        }
        L.debug=true;
        this.showResponse = showResponse;

    }

    public LoggerInterceptor(String tag)
    {
        this(tag, false);
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response)
    {
        int logLv=L.LOG_LV_D;
        try
        {
            //===>response log
            L.p( logLv, "########################################response'log########################################");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            if(clone.code()<200||clone.code()>=300){
                logLv=L.LOG_LV_E;
            }
            
            L.p( logLv, "url : " + clone.request().url());
            L.p( logLv, "code : " + clone.code());
            L.p( logLv, "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
                L.p( logLv, "message : " + clone.message());

            if (showResponse)
            {
                ResponseBody body = clone.body();
                if (body != null)
                {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null)
                    {
                        L.p( logLv, "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType))
                        {
                            String resp = body.string();
                            L.p( logLv, "responseBody's content : " + resp);

                            body = ResponseBody.create(mediaType, resp);
                            L.p( logLv, "end#####################################response'log1########################################end");
                            L.p( logLv, "\r \n ");
                            return response.newBuilder().body(body).build();
                        } else
                        {
                            L.p( logLv, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }

        } catch (Exception e)
        {
//            e.printStackTrace();
        }
        L.p( logLv, "end#####################################response'log2########################################end");
        L.p( logLv, "\r \n ");
        return response;
    }

    private void logForRequest(Request request)
    {
        try
        {
            String url = request.url().toString();
            Headers headers = request.headers();

            L.p( L.LOG_LV_D, "****************************************request'log****************************************");
            L.p( L.LOG_LV_D, "method : " + request.method());
            L.p( L.LOG_LV_D, "url : " + url);

            if(!url.contains("pingan.com.cn")&&!url.contains("1qianbao.com")){
                L.e("***************非平安ss地址或1qianbao地址*******************");
            }

            if (headers != null && headers.size() > 0)
            {
                L.p( L.LOG_LV_D, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null)
            {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null)
                {
                    L.p( L.LOG_LV_D, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType))
                    {
                        L.p( L.LOG_LV_D, "requestBody's content : " + bodyToString(request));
                    } else
                    {
                        L.p( L.LOG_LV_D, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            L.p( L.LOG_LV_D, "end*************************************request'log****************************************end");
            L.p( L.LOG_LV_D, "\r \n ");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType)
    {
        if (mediaType.type() != null && mediaType.type().equals("text"))
        {
            return true;
        }
        if (mediaType.subtype() != null)
        {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request)
    {
        try
        {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e)
        {
            return "something error when show requestBody.";
        }
    }
}
