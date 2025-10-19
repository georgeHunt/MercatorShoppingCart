import java.util.List;

public final class CheckoutTest {
    private static int passed = 0, failed = 0;

    public static void main(String[] args) {
        test("example basket", () -> {
            int p = new Checkout().totalPence(List.of("Apple", "Apple", "Orange", "Apple"));
            expectEquals(205, p, "example basket should be 205 pence");
        });

        test("empty basket", () -> {
            int p = new Checkout().totalPence(List.of());
            expectEquals(0, p, "empty basket should be 0 pence");
        });

        test("only oranges", () -> {
            int p = new Checkout().totalPence(List.of("Orange", "Orange"));
            expectEquals(50, p, "two oranges should be 50 pence");
        });

        test("only apples", () -> {
            int p = new Checkout().totalPence(List.of("Apple", "Apple", "Apple"));
            expectEquals(180, p, "three apples should be 180 pence");
        });

        test("unknown item throws", () -> {
            expectThrows(IllegalArgumentException.class,
                    () -> new Checkout().totalPence(List.of("Banana")));
        });

        test("null item throws", () -> {
            expectThrows(IllegalArgumentException.class,
                    () -> new Checkout().totalPence(List.of((String) null)));
        });

        test("case-insensitive + trim", () -> {
            int p = new Checkout().totalPence(List.of(" apple ", "ORANGE", "ApPlE"));
            expectEquals(145, p, "case/trim should still total 145 pence");
        });

        System.out.printf("%nRESULT: %d passed, %d failed%n", passed, failed);
        if (failed > 0) throw new RuntimeException("Tests failed");
    }

    private static void test(String name, Runnable r) {
        try {
            r.run();
            System.out.println("PASS " + name);
            passed++;
        } catch (Throwable t) {
            System.out.println("FAIL " + name + " - " + t.getMessage());
            failed++;
        }
    }

    private static void expectEquals(int expected, int actual, String msg) {
        if (expected != actual)
            throw new AssertionError(msg + " (expected " + expected + ", got " + actual + ")");
    }

    private static void expectThrows(Class<? extends Throwable> type, Runnable r) {
        try {
            r.run();
        } catch (Throwable t) {
            if (type.isInstance(t)) return;
            throw new AssertionError("Expected " + type.getSimpleName() + " but got " + t.getClass().getSimpleName());
        }
        throw new AssertionError("Expected " + type.getSimpleName() + " but nothing thrown");
    }
}
