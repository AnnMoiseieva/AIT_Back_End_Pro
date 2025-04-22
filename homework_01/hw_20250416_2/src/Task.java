public class Task extends Thread {

    private final int factor;

    public Task(int factor) {
        this.factor = factor;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for(int i=2; true ; i+=factor) {
            System.out.println(name + "by " + 2 + ": " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}