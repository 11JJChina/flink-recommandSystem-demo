package com.zhang.map;

import com.zhang.domain.LogEntity;
import com.zhang.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class TopProductMapFunction implements MapFunction<String, LogEntity> {
    @Override
    public LogEntity map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        return log;
    }
}
