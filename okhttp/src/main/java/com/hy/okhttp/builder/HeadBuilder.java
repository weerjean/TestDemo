package com.hy.okhttp.builder;

import com.hy.okhttp.OkHttpUtils;
import com.hy.okhttp.request.OtherRequest;
import com.hy.okhttp.request.RequestCall;

/**
 * Created by hy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
