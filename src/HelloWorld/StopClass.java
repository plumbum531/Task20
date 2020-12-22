package HelloWorld;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;

public class StopClass implements Runnable {
    private ScheduledExecutorService scheldulerExecutor;
    private CountDownLatch latchStart;

    public StopClass(CountDownLatch latchStart, ScheduledExecutorService scheldulerExecutor) {
        this.scheldulerExecutor = scheldulerExecutor;
        this.latchStart = latchStart;
    }

    @Override
    public void run() {
        try {
            latchStart.await();
            scheldulerExecutor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
