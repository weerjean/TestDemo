package com.paem.okhttp.builder;

import com.paem.okhttp.OkHttpUtils;
import com.paem.okhttp.request.OtherRequest;
import com.paem.okhttp.request.RequestCall;

/**
 * Created by paem on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
