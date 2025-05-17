public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {
            System.out.println("Incorrect operation: " + e.getMessage());
        }
    }
}
