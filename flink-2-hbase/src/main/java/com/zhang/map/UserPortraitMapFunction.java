package com.zhang.map;

import com.zhang.client.HbaseClient;
import com.zhang.client.MysqlClient;
import com.zhang.domain.LogEntity;
import com.zhang.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.sql.ResultSet;

/**
 * @author XINZE
 */
public class UserPortraitMapFunction implements MapFunction<String, String> {
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        ResultSet rst = MysqlClient.selectById(log.getProductId());
        if (rst != null){
            while (rst.next()){
                String userId = String.valueOf(log.getUserId());

                String country = rst.getString("country");
                HbaseClient.increamColumn("user",userId,"country",country);
                String color = rst.getString("color");
                HbaseClient.increamColumn("user",userId,"color",color);
                String style = rst.getString("style");
                HbaseClient.increamColumn("user",userId,"style",style);
            }

        }
        return null;
    }
}
