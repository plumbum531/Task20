package SingletonObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }


    void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latchStart = new CountDownLatch(1);
        CountDownLatch latchFinish = new CountDownLatch(10);

        Runnable trigger = () -> {
            try {
                latchStart.await();
                SingletonClass.getSingleton();
                latchFinish.countDown();
            } catch (InterruptedException e) {
                System.out.println("thred is wait trigger " + e.getMessage());
            }
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(trigger);
        }

        latchStart.countDown();
        try {
            latchFinish.await(1000, TimeUnit.MILLISECONDS);
            System.out.println("Counter = " + SingletonClass.counter);
        } catch (InterruptedException e) {
            System.out.println("wait all thread would been complited " + e.getMessage());
        }
        executorService.shutdown();

    }
}
