package com.yaoit.springcloud.springdemo.thread.scrapyJd.dao;

import com.yaoit.springcloud.springdemo.thread.scrapyJd.pojo.GoodsInfo;

import java.util.List;

public interface GoodsInfoDao {
    /**
     * 插入商品信息
     * @param infos
     */
    void saveBatch(List<GoodsInfo> infos);
}
