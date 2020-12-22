package HelloWorld;

import java.util.concurrent.CountDownLatch;

public class PrepearFirstClass implements Runnable {
    private PrintClass printClass;
    private CountDownLatch latchStart;

    public PrepearFirstClass(PrintClass printClass, CountDownLatch latchStart) {
        this.printClass = printClass;
        this.latchStart = latchStart;
    }

    @Override
    public void run() {
        try {
            latchStart.await();
            printClass.firstMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
