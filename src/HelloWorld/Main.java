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
        CountDownLatch latchFinish = new CountDownLatch(6);
        PrintClass printClass = new PrintClass();
        ScheduledExecutorService scheldulerExecutor = Executors.newScheduledThreadPool(3);

        scheldulerExecutor.scheduleAtFixedRate(new PrepearFirstClass(printClass, latchStart), 0, 1,
                TimeUnit.SECONDS);
        scheldulerExecutor.scheduleAtFixedRate(new PrepearSecondClass(printClass, latchStart), 0, 1,
                TimeUnit.SECONDS);
        scheldulerExecutor.scheduleAtFixedRate(new StopClass(latchStart, scheldulerExecutor),6,1, TimeUnit.SECONDS );

        latchStart.countDown();


    }
}
