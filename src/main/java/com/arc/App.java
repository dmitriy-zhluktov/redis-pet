package com.arc;

import com.arc.redis.MessageListener;
import com.arc.redis.PubClient;
import com.arc.redis.SubClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class App {
    private static String host = "192.168.99.100";
    private static int port = 6379;

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis(host, port);
        jedis.append("key1", "val1");
        System.out.println(jedis.get("key1") + "\n");

        PubClient pubClient = new PubClient(host, port);
        String channel = "myChannel";
        pubClient.publish(channel, "My first published message. No receivers. It'll be lost");

        SubClient subClient = new SubClient(host, port);
        JedisPubSub listener = new MessageListener(jedis);
        subClient.setChannelAndListener(listener, channel);
        subClient.start();

        for (int i = 0; i < 10; i++) {
            pubClient.publish(channel, "Message number " + i);
            Thread.sleep(1000);
        }
        pubClient.publish(channel, "quit");
        subClient.unsubscribe(channel);
        Thread.sleep(1000);
        pubClient.publish(channel, "This message will not be received");
        pubClient.close(channel);
    }

}
