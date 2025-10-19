import java.util.List;

public final class Main {
    public static void main(String[] args) {
        List<String> basket = List.of("Apple", "Apple", "Orange", "Apple");
        int total = 0;
        for (String item : basket) total += price(item);
        System.out.println(formatGBP(total));
    }

    static int price(String item) {
        if ("Apple".equals(item))  return 60;
        if ("Orange".equals(item)) return 25;
        throw new IllegalArgumentException("Unknown item: " + item);
    }

    static String formatGBP(int pence) {
        return String.format("£%d.%02d", pence / 100, pence % 100);
    }
}
