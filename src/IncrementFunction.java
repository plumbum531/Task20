import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IncrementFunction {
    //    Создать 2000 одновременных задач, которые увеличивают целочисленный счетчик на 1.
//    Подтвердить проблему атомарности. Проверить ее решение с помощью volatile или Atomic классов.

    void counterPlusOne() {
        AddOneToCounter addOneToCounter = new AddOneToCounter();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 2000; i++) {
            executor.execute(new RunnableCounter(countDownLatch, addOneToCounter));
        }
        countDownLatch.countDown();
        try {
            countDownLatch.await(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter: = " + addOneToCounter.myCounter);
        executor.shutdown();
    }
}
