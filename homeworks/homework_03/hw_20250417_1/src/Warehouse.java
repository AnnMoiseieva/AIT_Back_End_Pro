public class Warehouse {
    private String title;
    private int value;
    private final Object lock = new Object();

    public Warehouse(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Warehouse: title - %s, value - %d.", title, value);

    }

    public void addValue(int value) {
        synchronized (lock) {
            this.value += value;
        }
    }
}
