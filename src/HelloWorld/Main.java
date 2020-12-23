package HelloWorld;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    void run() {
        CountDownLatch latchStart = new CountDownLatch(1);
        ScheduledExecutorService scheldulerExecutor = Executors.newScheduledThreadPool(3);

        Runnable newLine = ()->{
            try {
                latchStart.await();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable firstMethod = ()->{
            try {
                latchStart.await();
                System.out.print(" Hello ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable secondMethod = ()->{
            try {
                latchStart.await();
                System.out.print(" World ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable stopClass = ()->{
            try {
                latchStart.await();
                scheldulerExecutor.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        scheldulerExecutor.scheduleAtFixedRate(firstMethod, 0, 10, TimeUnit.SECONDS);
        scheldulerExecutor.scheduleAtFixedRate(secondMethod, 0, 10, TimeUnit.SECONDS);
        scheldulerExecutor.scheduleAtFixedRate(stopClass,60,1, TimeUnit.SECONDS );
        scheldulerExecutor.scheduleAtFixedRate(newLine,300,10000, TimeUnit.MILLISECONDS );

        latchStart.countDown();
    }
}
