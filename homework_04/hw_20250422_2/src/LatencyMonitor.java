import java.util.concurrent.atomic.AtomicLong;

public class LatencyMonitor {

    private final AtomicLong minLatency = new AtomicLong(Long.MAX_VALUE);

    public void updateLatency(double latency) {
        long newLatency = (long) latency;
        long current;

        do {
            current = minLatency.get();
            if (newLatency >= current) {
                return;
            }
        } while (!minLatency.compareAndSet(current, newLatency));
    }

    public long getMinLatency() {
        return minLatency.get();
    }
}
