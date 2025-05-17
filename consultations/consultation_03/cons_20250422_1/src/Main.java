public class Main {

    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Account accountA = new Account("DE1111", "Jack", 1000);
        Account accountB = new Account("DE2222", "John", 1000);

        Thread thread1 = new Thread(() -> transfer(accountA, accountB, 100), "T1");
        Thread thread2 = new Thread(() -> transfer(accountB, accountA, 500), "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);

    }

    public static void transfer(Account from, Account to, double amount) {

        synchronized (from) {
            //System.out.println("account " + from + " is locked by " + Thread.currentThread().getName());

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            synchronized (to) {
                System.out.println("account " + to + " is locked by " + Thread.currentThread().getName());
                from.withdraw(amount);
                to.deposit(amount);
            }
            System.out.println("account " + to + " is unlocked");
        }
        System.out.println("account " + from + " is unlocked");
        System.out.println("transfer is finished");
    }
}

