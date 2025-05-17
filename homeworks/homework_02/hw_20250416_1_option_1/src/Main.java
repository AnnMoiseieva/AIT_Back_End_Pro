public class Main {

    public static void main(String[] args) {

        System.out.println("MAIN START");

        ThreadDivByTwo thread2 = new ThreadDivByTwo();
        ThreadDivByThree thread3 = new ThreadDivByThree();

        thread2.start();
        thread3.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread2.stopRunning();
        thread3.stopRunning();

        try {
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("MAIN FINISH");
    }
}
