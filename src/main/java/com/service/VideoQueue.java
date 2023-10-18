package com.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

@Component
public class VideoQueue {
    private static final BlockingQueue<VideoRequest> queue = new LinkedBlockingQueue<>();

    public static void enqueue(VideoRequest request) {
        queue.offer(request);
    }

    public static VideoRequest dequeue() throws InterruptedException {
        return queue.take();
    }
}