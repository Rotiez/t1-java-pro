package edu.t1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        final Random random = new Random();

        for(int i = 0; i < countDownLatch.getCount(); i++) {
            pool.execute(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    long lockSeconds = random.nextInt(10);

                    System.out.printf("[STARTED] task by thread %s (for %d sec)%n", threadName, lockSeconds);

                    LockSupport.parkNanos(lockSeconds * 1_000_000_000);

                    System.out.printf("[FINISHED] task by thread %s%n", threadName);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        pool.shutdown();
        pool.awaitTermination();
    }
}