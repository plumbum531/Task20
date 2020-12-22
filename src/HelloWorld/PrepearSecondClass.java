package HelloWorld;

import java.util.concurrent.CountDownLatch;

public class PrepearSecondClass implements Runnable {
    private PrintClass printClass;
    private CountDownLatch latchStart;

    public PrepearSecondClass(PrintClass printClass, CountDownLatch latchStart) {
        this.printClass = printClass;
        this.latchStart = latchStart;
    }

    @Override
    public void run() {
        try {
            latchStart.await();
            printClass.secondMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
