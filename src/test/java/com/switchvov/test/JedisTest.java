package com.switchvov.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
    private JedisPool pool;

    @Before
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(200);
        config.setMaxTotal(600);
        config.setTestOnBorrow(true);
        String host = "192.168.1.190";
        int port = 6379;
        int timeout = 2000;
        String password = "rcbox2016";
        int db = 1;
        pool = new JedisPool(config, host, port, timeout, password, db);
    }

    @Test
    public void testJedis() {
    }
}
