package TwoThousandTask;

import java.util.concurrent.CountDownLatch;

public class AddOneToCounter {
    int myCounter;
 //   private final Object lock = new Object();

    public void increment(CountDownLatch countDownLatchFinish) {
//        synchronized (lock) {
            myCounter += 1;
            countDownLatchFinish.countDown();
//        }
    }
}
