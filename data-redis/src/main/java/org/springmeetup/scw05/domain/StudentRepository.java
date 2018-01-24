package org.springmeetup.scw05.domain;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

	private static final String KEY = "Student";

	private final HashOperations<String, String, Student> hashOperations;

	public StudentRepository(@Qualifier("crudRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}
	
	public void put(Student student) {
		hashOperations.put(KEY, student.getId(), student);
	}

	public Map<String, Student> entries() {
		return hashOperations.entries(KEY);
	}
	
	public Student get(String hashKey) {
		return hashOperations.get(KEY, hashKey);
	}
	
}
