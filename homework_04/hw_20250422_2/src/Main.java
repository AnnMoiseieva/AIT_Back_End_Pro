import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LatencyMonitor monitor = new LatencyMonitor();
        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    double latency = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
                    monitor.updateLatency(latency);
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Minimum Latency: " + monitor.getMinLatency() + " ms");
    }
}
