import java.util.List;

public final class Main {
    public static void main(String[] args) {
        // Example from brief
        List<String> basket = List.of("Apple", "Apple", "Orange", "Apple");

        int totalPence = 0;
        for (String item : basket) {
            if ("Apple".equals(item)) totalPence += 60;
            else if ("Orange".equals(item)) totalPence += 25;
            else throw new IllegalArgumentException("Unknown item: " + item);
        }

        System.out.printf("£%d.%02d%n", totalPence / 100, totalPence % 100);
    }
}
