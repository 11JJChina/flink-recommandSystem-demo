package com.zhang.service;

public interface KafkaService {

    void send(String key, String value);

    String makeLog(String userId, String productId, String action);
}
