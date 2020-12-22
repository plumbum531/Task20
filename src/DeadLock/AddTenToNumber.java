package DeadLock;

public class AddTenToNumber implements Runnable {
    private ResultClass resultClass;

    public AddTenToNumber(ResultClass resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public void run() {
        resultClass.functionAddTen(10);
        System.out.println("10 is added");
    }
}
