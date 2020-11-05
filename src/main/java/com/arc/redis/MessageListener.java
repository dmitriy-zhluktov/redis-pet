package com.arc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class MessageListener extends JedisPubSub {
    private Jedis jedis;
    private static final String SAMPLE_KEY = "sampleKey";

    public MessageListener(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("Channel '%s' received a message: %s \n", channel, message));
        if(message.equalsIgnoreCase("quit")) {
            this.unsubscribe(channel);
        } else {
            jedis.append(SAMPLE_KEY, message);
        }

        System.out.println(String.format("Message with key '%s' is '%s' \n", SAMPLE_KEY, jedis.get(SAMPLE_KEY)));
        jedis.del(SAMPLE_KEY);
    }
}
