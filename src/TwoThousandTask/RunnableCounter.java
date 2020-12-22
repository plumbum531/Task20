package TwoThousandTask;

import java.util.concurrent.CountDownLatch;

public class RunnableCounter implements Runnable {
    private final AddOneToCounter addOneToCounter;
    private final CountDownLatch countDownLatchStart;
    private final CountDownLatch countDownLatchFinish;

    public RunnableCounter(CountDownLatch countDownLatchStart,
                           CountDownLatch countDownLatchFinish,
                           AddOneToCounter addOneToCounter) {
        this.addOneToCounter = addOneToCounter;
        this.countDownLatchStart = countDownLatchStart;
        this.countDownLatchFinish = countDownLatchFinish;
    }

    @Override
    public void run() {
        try {
            countDownLatchStart.await();
            addOneToCounter.increment(countDownLatchFinish);
        } catch (InterruptedException e) {
            System.out.println("Interruptes with message " + e.getMessage());
        }
    }
}
