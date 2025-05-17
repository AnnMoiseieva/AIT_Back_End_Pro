public class Main {

    public static void main(String[] args) {

        Task task2 = new Task(2);
        Task task3 = new Task(3);

        task2.setDaemon(true);
        task3.setDaemon(true);

        task2.start();
        task3.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---- game over");
    }
}
