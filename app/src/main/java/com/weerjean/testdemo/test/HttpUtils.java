package com.weerjean.testdemo.test;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by : weerjean
 */
public class HttpUtils {

    public SSLSocketFactory mSSLSocketFactory;
    public X509TrustManager mX509TrustManager;
    private static HttpUtils mInstance = new HttpUtils();

    /**
     * 私有化构造方法
     */
    private HttpUtils(){
        try {
            mX509TrustManager = new MyTrustManager();
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{mX509TrustManager},null);
            mSSLSocketFactory = sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象实例
     * @return
     */
    public static HttpUtils getInstance(){
        return mInstance;
    }

    /**
     * 证书弱校验，实现系统 X509TrustManager
     */
    private static class MyTrustManager implements X509TrustManager
    {
        private X509TrustManager defaultTrustManager;

        public MyTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            defaultTrustManager = getTrustManager(trustManagerFactory.getTrustManagers());
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 校验服务端证书
            defaultTrustManager.checkServerTrusted(chain, authType);

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static X509TrustManager getTrustManager(TrustManager[] trustManagers)
    {
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }


}
