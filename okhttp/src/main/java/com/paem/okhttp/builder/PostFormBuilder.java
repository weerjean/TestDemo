package com.paem.okhttp.builder;

import com.paem.okhttp.request.PostFormRequest;
import com.paem.okhttp.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paem on 15/12/14.
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable
{
    private List<FileInput> files = new ArrayList<>();
//    public static HashMap<String ,String> necessaryData=null;

    @Override
    public RequestCall build()
    {
//        if(necessaryData!=null&&params!=null){
//            params.putAll(necessaryData);
//        }
        return new PostFormRequest(url, tag, params, headers, files,id).build();
    }

    public PostFormBuilder files(String key, Map<String, File> files)
    {
        for (String filename : files.keySet())
        {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file)
    {
        files.add(new FileInput(name, filename, file));
        return this;
    }

//    /** @deprecated
//     *  @description 此方法为适配普惠而添加，请使用@params方法
//     */
//    @Deprecated
//    public PostFormBuilder addPairList(List<NameValuePair> pairList) {
//
//        if (this.params == null) {
//            params = new LinkedHashMap<>();
//        }
//        for (NameValuePair pair:pairList) {
//            params.put(pair.getName(),pair.getValue());
//        }
//        return this;
//    }


    public static class FileInput
    {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }



    @Override
    public PostFormBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }




}
