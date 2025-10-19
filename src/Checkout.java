import java.util.Collection;
import java.util.Locale;

public final class Checkout {
    public int totalPence(Collection<String> items) {
        int total = 0;
        for (String raw : items) {
            String item = normalize(raw);
            if ("apple".equals(item))  total += 60;
            else if ("orange".equals(item)) total += 25;
            else throw new IllegalArgumentException("Unknown item: " + raw);
        }
        return total;
    }

    private static String normalize(String s) {
        if (s == null) throw new IllegalArgumentException("item cannot be null");
        return s.trim().toLowerCase(Locale.ROOT);
    }
}
