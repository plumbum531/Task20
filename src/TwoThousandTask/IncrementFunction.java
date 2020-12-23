package TwoThousandTask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//    Создать 2000 одновременных задач, которые увеличивают целочисленный счетчик на 1.
//    Подтвердить проблему атомарности. Проверить ее решение с помощью volatile или Atomic классов.
public class IncrementFunction {
    public static void main(String[] args) {
        new IncrementFunction().counterPlusOne();
    }
    void counterPlusOne() {
        AddOneToCounter addOneToCounter = new AddOneToCounter();
        CountDownLatch countDownLatchStart = new CountDownLatch(1);
        CountDownLatch countDownLatchFinish = new CountDownLatch(5000);
        ExecutorService executor = Executors.newFixedThreadPool(5000);
        Future <?> future = null;

        Runnable counter = ()->{
            try {
                countDownLatchStart.await();
                addOneToCounter.increment();
                countDownLatchFinish.countDown();
            } catch (InterruptedException e) {
                System.out.println("Interruptes with message " + e.getMessage());
            }
        };

        for (int i = 0; i < 5000; i++) {
//            executor.execute(new RunnableCounter(countDownLatchStart, countDownLatchFinish, addOneToCounter));
            executor.execute(counter);
        }
        countDownLatchStart.countDown();

        try {
            countDownLatchFinish.await();
//            System.out.println("Counter: = " + myCounter);
            System.out.println("Counter: = " + addOneToCounter.getMyCounter());
        } catch (InterruptedException e) {
            System.out.println("Interrupted with message " + e.getMessage());
        } finally {
           executor.shutdown();
        }
    }
}
