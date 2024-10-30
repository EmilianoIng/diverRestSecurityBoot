package com.diver.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class KafkaProducer implements KafkaProducerInterface {

	   private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	    @Autowired
	    KafkaTemplate<String, String> kafkaProducerTemplate;

	    @Override
	    public void sendMessage(String topic, String message) {
	        Message<String> kafkaMessage = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topic).build();

	        ListenableFuture<SendResult<String, String>> future = kafkaProducerTemplate.send(kafkaMessage);

	        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	            @Override
	            public void onSuccess(SendResult<String, String> result) {
	                LOGGER.info("Sent message=[{}] on topic=[{}] with offset=[{}]", message, topic, result.getRecordMetadata().offset());
	            }

	            @Override
	            public void onFailure(Throwable ex) {
	                LOGGER.error(String.format("Unable to send message=[%s] on topic=[%s] due to : %s", message, topic, ex.getMessage()));
	            }
	        });
	    }

	    @Override
	    public <T> void sendMessage(String topic, T message) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.registerModule(new JavaTimeModule());
	        try {
	            sendMessage(topic, objectMapper.writeValueAsString(message));
	        } catch (JsonProcessingException e) {
	            LOGGER.error(e.getMessage(), e);
	        }
	    }

}
