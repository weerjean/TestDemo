package com.hy.okhttp.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by weerjean on 2016/7/5  11:18
 * description:
 */
public class UrlUtils {
    /**
     *
     * [检查请求地址是否包含网络协议]<BR>
     * [功能详细描述]
     *
     * @param sendURL
     *            请求Url全路径
     * @return true:包含协议 false:不包含协议
     */
    public static boolean isUrl(String sendURL) {
        boolean hasProtocol = false;
        try {
            URL url = new URL(sendURL);
            url.getProtocol();
            hasProtocol = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            L.e(e.toString());
        }
        return hasProtocol;
    }

}
