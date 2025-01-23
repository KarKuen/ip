import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        ArrayList l = new ArrayList<String>();

        System.out.println(
                "____________________________________\n" +
                "Hello ! I'm Friday\n" +
                "What can I do for you?\n" +
                "____________________________________\n"
        );

        while (true) {
            String logo = in.nextLine();
            if (logo.compareTo("bye") == 0) {
                System.out.println(
                        "____________________________________\n" +
                                "Bye. Hope to see you again soon!" +
                                "\n____________________________________\n"
                );
                break;
            } else if (logo.compareTo("list") == 0) {
                int counter = 1;
                System.out.println("____________________________________\n");
                for (int i=0; i< l.size(); i++) {
                    System.out.println(counter + ". " + l.get(i));
                    counter ++;
                }
                System.out.println("____________________________________\n");

            } else {
                System.out.println(
                        "____________________________________\n" +
                                "added: " + logo +
                                "\n____________________________________\n"
                );
                l.add(logo);
            }
        }
        out.close();
    }
}
