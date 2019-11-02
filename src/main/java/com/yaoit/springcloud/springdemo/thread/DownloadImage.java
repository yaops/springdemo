package com.yaoit.springcloud.springdemo.thread;


import java.io.*;
import java.net.*;

public class DownloadImage implements Runnable{
    String downUrl;
    public DownloadImage(String downUrl){
        this.downUrl = downUrl;
    }
    public void run(){
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try{
            //生成url对象
            URL url = new URL(downUrl);
            //创建urlconnection对象
            URLConnection uc = url.openConnection();
            //获取uc的输入流
            bis = new BufferedInputStream(uc.getInputStream());
            //创建图片的存储对象
            String[] p = downUrl.split("/");
            String path = "E:\\picture\\"+p[p.length-1];
            fos = new FileOutputStream(path);
            int c;
            while((c=bis.read())!=-1){
                fos.write(c);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{
                if(bis!=null){
                    bis.close();
                }

                if(fos!=null){
                    fos.close();
                }
            }catch(Exception e){
                System.out.println("没办法了");
            }
        }
    }
}

