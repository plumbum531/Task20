package SingletonObject;

public class SingletonClass {
    private static SingletonClass singleton;
    private int secretInt;
    static int counter;

    private SingletonClass() {
        secretInt = 45;
        counter++;
    }

    public static SingletonClass getSingleton() {
        if (singleton == null) {
            try {
                int sleepTime = 2;
                Thread.sleep(sleepTime);
                singleton = new SingletonClass();
            } catch (InterruptedException e) {
                System.out.println("Interupted in sleep time " + e.getMessage());
            }
        }
        return singleton;
    }
}
