import java.util.List;

public final class CheckoutTest {
    private static int passed = 0, failed = 0;

    public static void main(String[] args) {
        test("example basket (offers applied)", () -> {
            int p = new Checkout().totalPence(List.of("Apple", "Apple", "Orange", "Apple"));
            expectEquals(145, p, "example with offers should be 145 pence");
        });

        test("empty basket", () -> {
            int p = new Checkout().totalPence(List.of());
            expectEquals(0, p, "empty basket should be 0 pence");
        });

        // --- Apple offer: Buy One Get One Free ---
        test("apples: 1 costs 60", () -> {
            int p = new Checkout().totalPence(List.of("Apple"));
            expectEquals(60, p, "1 apple costs 60");
        });

        test("apples: 2 costs 60 (BOGOF)", () -> {
            int p = new Checkout().totalPence(List.of("Apple", "Apple"));
            expectEquals(60, p, "2 apples cost 60 with BOGOF");
        });

        test("apples: 3 costs 120 (BOGOF)", () -> {
            int p = new Checkout().totalPence(List.of("Apple", "Apple", "Apple"));
            expectEquals(120, p, "3 apples cost 120 with BOGOF");
        });

        // --- Orange offer: 3 for the price of 2 ---
        test("oranges: 2 cost 50", () -> {
            int p = new Checkout().totalPence(List.of("Orange", "Orange"));
            expectEquals(50, p, "2 oranges cost 50");
        });

        test("oranges: 3 cost 50 (3-for-2)", () -> {
            int p = new Checkout().totalPence(List.of("Orange", "Orange", "Orange"));
            expectEquals(50, p, "3 oranges cost 50 with 3-for-2");
        });

        test("oranges: 4 cost 75 (3-for-2)", () -> {
            int p = new Checkout().totalPence(List.of("Orange", "Orange", "Orange", "Orange"));
            expectEquals(75, p, "4 oranges cost 75 with 3-for-2");
        });

        test("case-insensitive + trim", () -> {
            int p = new Checkout().totalPence(List.of(" apple ", "ORANGE", "ApPlE"));
            expectEquals(145, p, "case/trim should total 145 pence (BOGOF + one orange)");
        });

        test("unknown item throws", () -> {
            expectThrows(IllegalArgumentException.class,
                    () -> new Checkout().totalPence(List.of("Banana")));
        });

        test("null item throws", () -> {
            expectThrows(IllegalArgumentException.class,
                    () -> new Checkout().totalPence(java.util.Arrays.asList((String) null)));
        });

        System.out.printf("%nRESULT: %d passed, %d failed%n", passed, failed);
        if (failed > 0) throw new RuntimeException("Tests failed");
    }

    private static void test(String name, Runnable r) {
        try { r.run(); System.out.println("PASS " + name); passed++; }
        catch (Throwable t) { System.out.println("FAIL " + name + " - " + t.getMessage()); failed++; }
    }

    private static void expectEquals(int expected, int actual, String msg) {
        if (expected != actual)
            throw new AssertionError(msg + " (expected " + expected + ", got " + actual + ")");
    }

    private static void expectThrows(Class<? extends Throwable> type, Runnable r) {
        try { r.run(); }
        catch (Throwable t) { if (type.isInstance(t)) return;
            throw new AssertionError("Expected " + type.getSimpleName() + " but got " + t.getClass().getSimpleName()); }
        throw new AssertionError("Expected " + type.getSimpleName() + " but nothing thrown");
    }
}
