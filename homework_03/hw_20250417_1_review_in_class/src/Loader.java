public class Loader implements Runnable {

    private String name;
    private int nBox;
    private int capacity;
    private Warehouse warehouse;
    private int done = 0;
    private static String winner = null;
    private static Object lock = new Object();

    public Loader(String name, int nBox, int capacity, Warehouse warehouse) {
        this.name = name;
        this.nBox = nBox;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    public static String getWinner () {
        return winner;
    }

    @Override
    public void run() {
        while (done < nBox) {
            int value = Math.min(nBox - done, capacity);
            warehouse.addValue(value);
            done += capacity;
        }
        synchronized (lock) {
            if (winner == null) {
                winner = name;
            }
        }
        System.out.println(name + " finish. Got " + done + " boxes.");
    }
}