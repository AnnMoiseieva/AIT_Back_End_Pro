public class Main {
    private static final int N_BOX = 1000;
    private static final int CAPACITY = 1;

    public static String winner = null;
    public static final Object winnerLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Warehouse warehouse = new Warehouse("#1");

        Thread[] loaders = {
                new Thread(new Loader("Jack", N_BOX, CAPACITY, warehouse)),
                new Thread(new Loader("John", N_BOX, CAPACITY, warehouse)),
                new Thread(new Loader("Nick", N_BOX, CAPACITY, warehouse))
        };

        for (Thread thread : loaders) {
            thread.start();
        }

        for (Thread thread : loaders) {
            thread.join();
        }

        System.out.println("Winner: " + winner);
    }
}
