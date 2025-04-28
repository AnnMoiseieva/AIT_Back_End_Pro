import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Loader implements Runnable {

    private final String name;
    private final int nBox;
    private final int capacity;
    private final Warehouse warehouse;
    private int done = 0;
    private static final Random random = new Random();

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public Loader(String name, int nBox, int capacity, Warehouse warehouse) {
        this.name = name;
        this.nBox = nBox;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (done < nBox) {
            int value = Math.min(nBox - done, capacity);
            warehouse.addValue(value);
            done += value;

            // Пауза 5–15
            try {
                Thread.sleep(5 + random.nextInt(11));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long finishTime = System.currentTimeMillis();
        String formattedTime = timeFormat.format(new Date(finishTime));

        System.out.println(name + " finished at " + formattedTime + ". " + done + " boxes");

        synchronized (Main.winnerLock) {
            if (Main.winner == null) {
                Main.winner = name;
            }
        }
    }
}