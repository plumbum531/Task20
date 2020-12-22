package DeadLock;

public class AddOneToNumber implements  Runnable {
    private ResultClass resultClass;

    public AddOneToNumber(ResultClass resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public void run() {
        resultClass.functionAddOne(1);
        System.out.println("1 is added");
    }
}
