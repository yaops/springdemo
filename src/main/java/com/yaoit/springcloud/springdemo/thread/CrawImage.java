package com.yaoit.springcloud.springdemo.thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawImage {

//    public static void getUrl(Document document, ExecutorService pool){
//        Element id = document.getElementById("listBox");
//        Elements els = id.getElementsByTag("img");
//
//        for(Element el : els){
//            //url.add(el.attr("src"));
//            String imageUrl = el.attr("src");
//            pool.execute(new DownloadImage(imageUrl));
//            System.out.println(el.attr("src"));
//        }
//
//    }
public static void getUrl(Document document, ExecutorService pool){
    Element id = document.getElementById("content");
    Elements els = id.getElementsByTag("img");

    for(Element el : els){
        //url.add(el.attr("src"));
        String imageUrl = el.attr("src");
        pool.execute(new DownloadImage(imageUrl));
        System.out.println(el.attr("src"));
    }

}
  static ExecutorService pool=null;
    public static void main(String[] args) throws Exception{
   //创建一个缓冲池
        pool = Executors.newCachedThreadPool();
        //设置其容量为9
        pool = Executors.newFixedThreadPool(9);
        Document first = CrawImage.getFirst();
        getUrl(first,pool);
        getUrl(first);

    }
    public  static Document getFirst(){
        try {
            //获取指定网页源码
//            Document document = Jsoup.connect("http://item.kongfz.com/Cjisuanji/w2/").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            Document document = Jsoup.connect("http://www.ojbk.cc/metcn/210.html").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            return  document;
        }
        catch (Exception e){
            return  null;
        }

    }

    public static   void getUrl(Document document){
        //Set<String> url = new TreeSet<>();
        try{


//            int a = 4;
//            while(a--!=0){
//                Element el = document.getElementById("pagerBox");
//                Elements el2 = el.getElementsByClass("next-btn");
//                if(el2 == null){
//                    System.out.println("到最后了");
//                    break;
//                }
//                String urlIndex = el2.attr("href");
//                Document document2 = Jsoup.connect(urlIndex).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
//                getUrl(document2,pool);
//            }
//            int a = 4;
            String pre="http://www.ojbk.cc/metcn/";

                Element el = document.getElementsByClass("content-page").first();
                Element el2 = el.getElementsByClass("page-ch").last();

                String urlIndex = el2.attr("href");
                if(urlIndex != null){
//                    System.out.println("到最后了"+urlIndex);
//                   return null;

                System.out.println("uu"+urlIndex);
                Document document2 = Jsoup.connect(pre+urlIndex).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                getUrl(document2,pool);
                CrawImage.getUrl(document2);

                }

            //遍历set中图片的url
//            for(String imageUrl:url){
//                pool.execute(new DownloadImage(imageUrl));
//            }
            pool.shutdown();

        }catch(Exception e){
            System.out.print(e);
        }

    }
}
