package com.arc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubClient extends Thread {
    private Jedis subClient;
    private String channel;
    private JedisPubSub listener;

    public SubClient(String host, int port) {
        subClient = new Jedis(host, port);
    }

    public void setChannelAndListener(JedisPubSub listener, String channel) {
        this.listener = listener;
        this.channel = channel;
    }

    private void subscribe() {
        System.out.println("Subscribed to channel " + channel);
        subClient.subscribe(listener, channel);
    }

    public void unsubscribe(String channel) {
        System.out.println("Unsubscribed from channel " + channel);
        listener.unsubscribe(channel);
    }

    @Override
    public void run() {
        try {
            System.out.println("Subscription starts");
            subscribe();
            System.out.println("Subscription ends");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
