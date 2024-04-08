package com.kernelfrenzy;

import redis.clients.jedis.Jedis;

public class JedisHelper {

    private static JedisHelper instance;

    private Jedis jedis;

    private JedisHelper() {
        jedis = new Jedis("localhost", 6379);
    }

    public static synchronized JedisHelper getInstance() {
        if (instance == null) {
            synchronized (JedisHelper.class) {
                if (instance == null) {
                    instance = new JedisHelper();
                }
            }
        }
        return instance;
    }

    public boolean isConnected() {
        return jedis.isConnected();
    }

    public void storeString(String key, String value) {
        jedis.set(key, value);
    }

    public String getString(String key) {
        return jedis.get(key);
    }

    public void expireKeyInSeconds(String key, int seconds) {
        jedis.expire(key, seconds);
    }
}
