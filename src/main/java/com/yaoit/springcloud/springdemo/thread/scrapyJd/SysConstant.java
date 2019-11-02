package com.yaoit.springcloud.springdemo.thread.scrapyJd;

import java.util.HashMap;
import java.util.Map;

public class SysConstant {
    /**
     * 系统默认字符集
     */
  public   static String DEFAULT_CHARSET = "utf-8";
    /**
     * 需要爬取的网站
     */
   public static String BASE_URL = "https://search.jd.com/Search";

    interface Header {
        String ACCEPT = "Accept";
        String ACCEPT_ENCODING = "Accept-Encoding";
        String ACCEPT_LANGUAGE = "Accept-Language";
        String CACHE_CONTROL = "Cache-Controle";
        String COOKIE = "Cookie";
        String HOST = "Host";
        String PROXY_CONNECTION = "Proxy-Connection";
        String REFERER = "Referer";
        String USER_AGENT = "User-Agent";
    }



    public static Map setHeader(){
        Map headerMap=new HashMap<String,String>();
        headerMap.put(":authority","search.jd.com");
        headerMap.put(":method","GET");
        headerMap.put(":scheme","https");
        headerMap.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headerMap.put("accept-encoding","gzip, deflate, br");
        headerMap.put("accept-language","zh-CN,zh;q=0.9");
        headerMap.put("cache-control","max-age=0");
        headerMap.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");

     return headerMap;
    }

    /**
     * 默认日期格式
     */
    public static  String DEFAULT_DATE_FORMAT = "yyy-MM-dd HH:mm:ss";
}
