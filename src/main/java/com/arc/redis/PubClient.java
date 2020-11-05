package com.arc.redis;

import redis.clients.jedis.Jedis;

public class PubClient {
    private Jedis pubClient;

    public PubClient(String host, int port) {
        pubClient = new Jedis(host, port);
    }

    public void publish(String channel, String message) {
        System.out.println(String.format("publishing to channel '%s' message '%s'", channel, message));
        pubClient.publish(channel, message);
    }

    public void close(String channel) {
        System.out.println(String.format("Closing channel '%s'", channel));
        pubClient.publish(channel, "quit");
    }
}
