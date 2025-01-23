import java.io.PrintWriter;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        System.out.println(
                "____________________________________\n" +
                "Hello ! I'm Friday\n" +
                "What can I do for you?\n" +
                "____________________________________\n"
        );

        while (true) {
            String logo = in.next();
            if (logo.compareTo("bye") == 0) {
                System.out.println(
                        "____________________________________\n" +
                                "Bye. Hope to see you again soon!" +
                                "\n____________________________________\n"
                );
                break;
            }
            System.out.println(
                    "____________________________________\n" +
                    logo +
                    "\n____________________________________\n"
            );
        }
        out.close();
    }
}
