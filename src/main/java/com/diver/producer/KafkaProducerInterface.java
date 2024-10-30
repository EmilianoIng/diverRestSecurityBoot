package com.diver.producer;

public interface KafkaProducerInterface {
	
	    void sendMessage(String topic, String message);

	    <T> void sendMessage(String topic, T message);
	
}
