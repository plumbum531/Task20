import java.util.concurrent.CountDownLatch;

public class RunnableCounter implements Runnable {
    private final AddOneToCounter addOneToCounter;
    private final CountDownLatch countDownLatch;

    public RunnableCounter(CountDownLatch countDownLatch, AddOneToCounter addOneToCounter) {
        this.addOneToCounter = addOneToCounter;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            addOneToCounter.increment();
        } catch (InterruptedException e) {
            System.out.println("Interruptes with message " + e.getMessage());
        }
    }
}
