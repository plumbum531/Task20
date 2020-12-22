package DeadLock;

public class ResultClass {
    public int counter;
    private final Object lockOne = new Object();
    private final Object lockTwo = new Object();

    void functionAddOne(int number) {
        synchronized (lockOne) {
            System.out.println("First lock");
            //первое действие в первом потоке - поток спит, иначе не получается блокировки
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockTwo) {
                counter += number;
                System.out.println("I am counting at one now");
                functionAddTen(10);

            }
        }
    }

    void functionAddTen(int number) {
        synchronized (lockTwo) {
            System.out.println("Second lock");
            //второе действиево втором потоке - поток спит, иначе не получается блокировки
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockOne) {
                counter += number;
                System.out.println("I am counting at ten now");
                functionAddOne(1);

            }
        }
    }
}
