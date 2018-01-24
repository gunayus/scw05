package org.springmeetup.scw05.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@SpringBootApplication
public class ReceiverConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverConfig.class);

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, Receiver receiver) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);

		container.addMessageListener(listenerAdapter(receiver), new PatternTopic("chat"));
		container.addMessageListener(listenerAdapterForStudent(receiver), new PatternTopic("student"));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	MessageListenerAdapter listenerAdapterForStudent(Receiver receiver) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, "receiveStudent");
		messageListenerAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
		
		return messageListenerAdapter;
	}
	
}
