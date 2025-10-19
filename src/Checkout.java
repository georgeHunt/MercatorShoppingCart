import java.util.Collection;
import java.util.Locale;

public final class Checkout {
    public int totalPence(Collection<String> items) {
        int apples = 0;
        int oranges = 0;

        for (String raw : items) {
            String item = normalize(raw);
            if ("apple".equals(item))  apples++;
            else if ("orange".equals(item)) oranges++;
            else throw new IllegalArgumentException("Unknown item: " + raw);
        }

        // Offers
        int payableApples = (apples / 2) + (apples % 2);
        int appleCost = payableApples * 60;

        int freeOranges = oranges / 3;
        int payableOranges = oranges - freeOranges;
        int orangeCost = payableOranges * 25;

        return appleCost + orangeCost;
    }

    private static String normalize(String s) {
        if (s == null) throw new IllegalArgumentException("item cannot be null");
        return s.trim().toLowerCase(Locale.ROOT);
    }
}
