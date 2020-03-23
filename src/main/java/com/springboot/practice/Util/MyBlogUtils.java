package com.springboot.practice.Util;

import java.net.URI;

public class MyBlogUtils {
    public static URI getHost(URI uri){
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(),uri.getUserInfo(),uri.getHost(),uri.getPort(),null,null,null);
        }catch (Throwable var){
            effectiveURI = null;
        }
        return effectiveURI;
    }
}
