import java.util.Collection;

public final class Checkout {
    // TODO: still case sensitive
    public int totalPence(Collection<String> items) {
        int total = 0;
        for (String item : items) {
            if ("Apple".equals(item))  total += 60;
            else if ("Orange".equals(item)) total += 25;
            else throw new IllegalArgumentException("Unknown item: " + item);
        }
        return total;
    }
}
