import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account("DE1111","Jack", 1000);
        Account accountB = new Account("DE2222","John", 1000);

        Thread thread1 = new Thread(
                () -> {
                    try {
                        transfer(accountA, accountB, 100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, "T1");
        Thread thread2 = new Thread(
                () -> {
                    try {
                        transfer(accountB, accountA, 500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);

    }

    public static void transfer(Account from, Account to, double amount) throws InterruptedException {
        Account first =from.hashCode()<to.hashCode()? from: to;
        Account second=from.hashCode()<to.hashCode()? to: from;

        ReentrantLock firstLock = (ReentrantLock) first.getLock();
        ReentrantLock secondLock = (ReentrantLock) first.getLock();

        try {
            //block with timeout
            if (firstLock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println("account " + from + " is locked by " + Thread.currentThread().getName());
                try {
                    if (secondLock.tryLock(2, TimeUnit.SECONDS)) {
                        System.out.println("account " + to + " is locked by" + Thread.currentThread().getName());

                        if (from.withdraw(amount)) {  // перевод денег from .... to
                            to.deposit(amount);
                            System.out.println("transferred " + amount + " " + from + " -> " + to);
                        } else {
                            System.out.println("not enough money on account - " + from);
                        }
                    } else {
                        System.out.println("timeout occurred while waiting for lock on account " + to);
                    }
                } finally {
                    if (secondLock.isHeldByCurrentThread()) {
                        secondLock.unlock();
                        System.out.println("account " + to + " is unlocked");
                    }
                }
            } else {
                System.out.println("Timeout occurred while waiting for lock on account " + from);
            }
        } finally {
            if (firstLock.isHeldByCurrentThread()) {
                firstLock.unlock();
                System.out.println("account " + from + " is unlocked");
            }
        }
        System.out.println("transfer is finish");
    }
}
