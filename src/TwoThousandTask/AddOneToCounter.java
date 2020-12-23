package TwoThousandTask;

import java.util.concurrent.CountDownLatch;

public class AddOneToCounter {
    private int myCounter;
 //   private final Object lock = new Object();

    public void increment(CountDownLatch countDownLatchFinish) {
//        synchronized (lock) {
            myCounter += 1;
            countDownLatchFinish.countDown();
//        }
    }

    public void increment() {
//        synchronized (lock) {
        myCounter += 1;
//        }
    }

    public int getMyCounter() {
        return myCounter;
    }
}
