package com.yaoit.springcloud.springdemo.thread.scrapyJd.dao.daoImpl;

import com.yaoit.springcloud.springdemo.thread.scrapyJd.dao.GoodsInfoDao;
import com.yaoit.springcloud.springdemo.thread.scrapyJd.pojo.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class GoodsInfoDaoImpl implements GoodsInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBatch(List<GoodsInfo> infos) {
        String sql = "REPLACE INTO goods_info(" + "goods_id," + "goods_name," + "goods_price," + "img_url) "
                + "VALUES(?,?,?,?)";
        for(GoodsInfo info : infos) {
            jdbcTemplate.update(sql, info.getGoodsId(), info.getGoodsName(), info.getGoodsPrice(), info.getImgUrl());
        }
    }
}
