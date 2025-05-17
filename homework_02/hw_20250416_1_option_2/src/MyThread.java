public class MyThread extends Thread {
    private int number = 1;
    private int div;

    public MyThread(int div) {
        this.div = div;
    }

    @Override
    public void run() {
        while (true) {
            if (number % div == 0) {
                System.out.println("Thread div by " + div + " is - " + number);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            number++;
        }
    }

}
