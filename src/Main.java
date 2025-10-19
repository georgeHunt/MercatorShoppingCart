import java.util.List;

public final class Main {
    public static void main(String[] args) {
        List<String> basket = List.of("Apple", "Apple", "Orange", "Apple");
        int pence = new Checkout().totalPence(basket);
        System.out.printf("£%d.%02d%n", pence / 100, pence % 100);
    }
}
