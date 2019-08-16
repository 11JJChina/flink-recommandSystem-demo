package com.zhang.map;

import com.zhang.client.HbaseClient;
import com.zhang.domain.LogEntity;
import com.zhang.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class LogMapFunction implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {

        LogEntity log = LogToEntity.getLog(s);
        if (null != log){
            String rowKey = log.getUserId() + "_" + log.getProductId()+ "_"+ log.getTime();
            HbaseClient.putData("con",rowKey,"log","userid",String.valueOf(log.getUserId()));
            HbaseClient.putData("con",rowKey,"log","productid",String.valueOf(log.getProductId()));
            HbaseClient.putData("con",rowKey,"log","time",log.getTime().toString());
            HbaseClient.putData("con",rowKey,"log","action",log.getAction());
        }
        return log;
    }
}
