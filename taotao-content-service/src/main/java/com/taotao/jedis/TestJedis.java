package com.taotao.jedis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	public static void main(String[] args) {
		TestJedis jedis=new TestJedis();
		jedis.testJedis();
	}
    public void testJedis(){
		ClassPathXmlApplicationContext content=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClientPool jedisClientPool=content.getBean(JedisClientPool.class);
		jedisClientPool.set("gaoshiqing", "搞事情");
		System.out.println(jedisClientPool.get("gaoshiqing"));
    }
}
