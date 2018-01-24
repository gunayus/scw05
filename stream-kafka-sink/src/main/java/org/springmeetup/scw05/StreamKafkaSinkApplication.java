package org.springmeetup.scw05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamKafkaSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamKafkaSinkApplication.class, args);
	}
	
	@StreamListener(Sink.INPUT)
	public void onMessage(String text) {
		System.out.println("Msg received: " + text);
	}
	
}
