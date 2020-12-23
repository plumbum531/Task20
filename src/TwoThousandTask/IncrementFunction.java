package TwoThousandTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        for (int i = 0; i < 5000; i++) {
            executor.execute(new RunnableCounter(countDownLatchStart, countDownLatchFinish, addOneToCounter));
        }
        countDownLatchStart.countDown();
        try {
            countDownLatchFinish.await();
            System.out.println("Counter: = " + addOneToCounter.myCounter);
        } catch (InterruptedException e) {
            System.out.println("Interrupted with message " + e.getMessage());
        } finally {
           executor.shutdown();
        }
    }
}
