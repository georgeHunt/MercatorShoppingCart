import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter items (Apple/Orange) separated by commas or spaces.");
        System.out.println("You can just press ENTER to use the example: Apple Apple Orange Apple");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        List<String> items = (line == null || line.isBlank())
                ? List.of("Apple", "Apple", "Orange", "Apple")
                : Arrays.stream(line.trim().split("[,\\s]+"))
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        try {
            int pence = new Checkout().totalPence(items);
            System.out.printf("Total: £%d.%02d%n", pence / 100, pence % 100);
        } catch (IllegalArgumentException ex) {
            System.err.println("Error: " + ex.getMessage());
            System.exit(1);
        }
    }
}
