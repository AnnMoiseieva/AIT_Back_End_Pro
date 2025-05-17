public class Loader implements Runnable {

    private String name;
    private int nBox;
    private int capacity;
    private Warehouse[] warehouse;
    private int done = 0;

    public Loader(String name, int nBox, int capacity, Warehouse[] warehouse) {
        this.name = name;
        this.nBox = nBox;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (done < nBox) {
            int value = Math.min(nBox - done, capacity);
            int oneValue = value / warehouse.length;
            for (int i = 0; i < warehouse.length; i++) {
                if (i == 0) {
                    warehouse[0].addValue(oneValue + (value % warehouse.length));
                } else {
                    warehouse[i].addValue(oneValue);
                }
            }
            done += capacity;
        }
        System.out.println(name + " finish. Got " + done + " boxes.");
    }
}