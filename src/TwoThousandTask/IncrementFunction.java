package TwoThousandTask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static javax.swing.plaf.synth.ColorType.MAX_COUNT;


//    Создать 2000 одновременных задач, которые увеличивают целочисленный счетчик на 1.
//    Подтвердить проблему атомарности. Проверить ее решение с помощью volatile или Atomic классов.
public class IncrementFunction {
    public static void main(String[] args) {
        new IncrementFunction().counterPlusOne();
    }
    void counterPlusOne() {
        int expectedCount = (int)(Math.random()* MAX_COUNT + 2000);
        System.out.println("expectedCount " + expectedCount);
        AddOneToCounter addOneToCounter = new AddOneToCounter();
        CountDownLatch countDownLatchStart = new CountDownLatch(1);
        CountDownLatch countDownLatchFinish = new CountDownLatch(expectedCount);
        ExecutorService executor = Executors.newFixedThreadPool(expectedCount);

        Runnable counter = ()->{
            try {
                countDownLatchStart.await();
                addOneToCounter.increment();
                countDownLatchFinish.countDown();
            } catch (InterruptedException e) {
                System.out.println("Interruptes with message " + e.getMessage());
            }
        };

        for (int i = 0; i < expectedCount; i++) {
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
