package com.weerjean.testdemo.network;

import android.app.Activity;

import android.content.res.AssetManager;
import com.hy.okhttp.utils.L;
import com.weerjean.testdemo.base.MyApplication;
import com.weerjean.testdemo.utils.ToastUtils;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkhttpUtils {

    /**
     * 使用OkHttp进行post请求。
     * @param context
     * @param url
     */
    public static void post(Activity context, String url) {

        try {

            AssetManager assetManager = MyApplication.getInstance().getAssets();
            String[] cas =  assetManager.list("ca");
            InputStream [] inputStreams = new InputStream[cas.length];
            for (int i= 0;i<cas.length;i++) {
                InputStream in = assetManager.open(cas[i]);
                inputStreams[i] =in;
            }

            OkHttpClient mOkHttpClient = setCertificates(inputStreams);

            if (mOkHttpClient == null) {
                ToastUtils.toast(context, "證書加載失敗");
            }

            RequestBody formBody = new FormBody.Builder()

                    .add("platform", "a")

                    .build();

            Request request = new Request.Builder()

                    .url(url)

                    .post(formBody)

                    .build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    L.p(L.LOG_LV_D,e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    L.p(L.LOG_LV_D,response.body().string());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取校验证书的okhttpClient，这是一种有效方式。
     * @param certificates 本地证书的流对象数组
     * @return OkHttpClient
     */
    public static OkHttpClient setCertificates(InputStream... certificates) {
        //OkHttpClient mOkHttpClient = new OkHttpClient();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//            mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
//            mOkHttpClient.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
//			mOkHttpClient.setFollowSslRedirects(false);
            return new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER)
                    .build();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取校验证书的okhttpClient，暂未完善，会报错
     * @param certificates 本地证书的流对象数组
     * @return OkHttpClient
     */
    public static OkHttpClient setCertificates2(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
//			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//			trustManagerFactory.init(keyStore);

            TrustManager tm = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                    for (X509Certificate cer : chain) {
                        cer.checkValidity();
                        System.out.println("---------------------------------------------------------------");
                        System.out.println("版本号:" + cer.getVersion());
                        System.out.println("序列号:" + cer.getSerialNumber().toString(16));
                        System.out.println("主体名：" + cer.getSubjectDN());
                        System.out.println("签发者：" + cer.getIssuerDN());
                        System.out.println("有效期：" + cer.getNotBefore());
                        System.out.println("签名算法：" + cer.getSigAlgName());
                        byte[] sig = cer.getSignature();//签名值
                        System.out.println("签名：" + new String(sig));
                        System.out.println("服务器公钥：" + cer.getPublicKey());

                        System.out.println("---------------------------------------------------------------");
                    }
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            };


            sslContext.init(null, new TrustManager[]{tm}, new SecureRandom());
//            mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
//            mOkHttpClient.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
//			mOkHttpClient.setFollowSslRedirects(false);
           return new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
