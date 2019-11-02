package com.yaoit.springcloud.springdemo.thread.scrapyJd.spiderHandler;

import com.google.common.collect.Maps;
import com.yaoit.springcloud.springdemo.thread.scrapyJd.SysConstant;
import com.yaoit.springcloud.springdemo.thread.scrapyJd.service.SpiderService;
import com.yaoit.springcloud.springdemo.thread.scrapyJd.threadPool.UserRejectHandler;
import com.yaoit.springcloud.springdemo.thread.scrapyJd.threadPool.UserThreadFactory;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class SpiderHandler {

    @Autowired
    private SpiderService spiderService;

    private static final Logger logger = LoggerFactory.getLogger(SpiderHandler.class);

    public void spiderData() {
        //缓存队列为20
        BlockingDeque queue=new LinkedBlockingDeque(2400);
        //设置线程组
        UserThreadFactory f1=new UserThreadFactory("线程组1");
        UserRejectHandler handler=new UserRejectHandler();
        //创建线程池
//        参数可行
//        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(8,15,60,
//                       TimeUnit.SECONDS,queue,f1,handler);
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(50,90,60,
                TimeUnit.SECONDS,queue,f1,handler);

        logger.info("爬虫开始....");
        Date startDate = new Date();
        // 使用现线程池提交任务
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 1; i < 201; i += 2) {
            Map<String, String> params = Maps.newHashMap();
            params.put("keyword", "零食");
            params.put("enc", "utf-8");
            params.put("wc", "零食");
            params.put("page", i + "");
//            executorService.submit(() -> {
//                spiderService.spiderData(SysConstant.BASE_URL, params);
//                countDownLatch.countDown();
//            });
            threadPoolExecutor.execute(()->{
                spiderService.spiderData(SysConstant.BASE_URL, params);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        executorService.shutdown();
        threadPoolExecutor.shutdown();
        Date endDate = new Date();
        FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        logger.info("爬虫结束....");
        logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");

    }

}
