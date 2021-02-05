package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class KuayuInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {

        Controller c = inv.getController();
        c.getResponse().addHeader("Access-Control-Allow-Origin", "*");

        c.getResponse().addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");//允许跨域的方法

        c.getResponse().addHeader("Access-Control-Allow-Headers", "access-token, mobile, power-by, token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");//表示允许的额外header
        c.getResponse().addHeader("Access-Control-Max-Age", "3600");
        c.getResponse().addHeader("Access-Control-Allow-Credentials", "true");//必须为true

        //多文件上传 getfiles必须放在最前面 否则无法取到参数
/*
        if (StrKit.notBlank(c.getResponse().getContentType()) && c.getResponse().getContentType().contains("multipart/form-data")) {
            //resp.getFiles();
            c.getFile();
            c.getFiles();
        }
*/
        inv.invoke();

    }


}
