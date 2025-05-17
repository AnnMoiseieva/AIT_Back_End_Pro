public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main started");
        Thread thread2 = new MyThread(2);
        Thread thread3 = new MyThread(3);

        thread2.setDaemon(true);
        thread3.setDaemon(true);

        thread2.start();
        thread3.start();

        Thread.sleep(3000);

        System.out.println("Main finished");
    }
}
