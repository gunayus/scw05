package org.springmeetup.scw05;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.util.StringUtils;
import org.springmeetup.scw05.domain.Student;
import org.springmeetup.scw05.domain.Student.Gender;
import org.springmeetup.scw05.domain.StudentRepository;

@SpringBootApplication
public class DataRedisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DataRedisApplication.class, args);
	}
	
	@Bean("crudRedisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory);
		return template;
	}

	@Bean("studentRedisTemplate")
	public RedisTemplate<String, Student> studentRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Student> template = new RedisTemplate<String, Student>();
		template.setConnectionFactory(connectionFactory);
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		return template;
	}

	@Bean("stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("studentRedisTemplate")
	RedisTemplate<String, Student> studentRedisTemplate;
	
	@Override
	public void run(String... arg0) throws Exception {
		studentRepository.put(new Student("id_1", "foo", Gender.MALE, 5));
		studentRepository.put(new Student("id_2", "bee", Gender.FEMALE, 8));
		
		Map<String, Student> studentMap = studentRepository.entries();
		for (Student student : studentMap.values()) {
			System.out.printf("%s, %s, %s, %d \n", student.getId(), student.getName(), student.getGender(), student.getGrade());
		}
	
		// chat
		Scanner sc = new Scanner(System.in);
		while (true) {
			String line = sc.nextLine();
			if (! StringUtils.hasText(line))
				break;
			
			stringRedisTemplate.convertAndSend("chat", line);
			studentRedisTemplate.convertAndSend("student", new Student("id_1", line, Gender.FEMALE, 1));
		}
		
		System.exit(0);
	}
}

