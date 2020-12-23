package SingletonObject;

public class SingletonClass {
    private volatile static SingletonClass singleton;
    private static final Object lock = new Object();
    private int secretInt;
    static int counter;

    private SingletonClass() {
        secretInt = 45;
        counter++;
    }

    public static SingletonClass getSingleton() {
        synchronized (lock) {
            if (singleton == null) {
                try {
                    int sleepTime = 52;
                    Thread.sleep(sleepTime);
                    singleton = new SingletonClass();
                } catch (InterruptedException e) {
                    System.out.println("Interupted in sleep time " + e.getMessage());
                }
            }
        }
        return singleton;
    }
}
