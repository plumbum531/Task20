import java.util.concurrent.*;

public class IncrementFunction {
    //    Создать 2000 одновременных задач, которые увеличивают целочисленный счетчик на 1.
//    Подтвердить проблему атомарности. Проверить ее решение с помощью volatile или Atomic классов.

    void counterPlusOne() {
        AddOneToCounter addOneToCounter = new AddOneToCounter();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 200000; i++) {
            executor.execute(new RunnableCounter(countDownLatch, addOneToCounter));
        }
        countDownLatch.countDown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter: = " + addOneToCounter.myCounter);
        executor.shutdown();
    }
}
