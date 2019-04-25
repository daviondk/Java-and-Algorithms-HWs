import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumAbcFile {
    public static void main(String[] args) {
        int sum = 0;
        Scanner in = null;
        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.exit(-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.exit(-1);
        }

        while (in.hasNext()) {
            try {
                sum += in.nextInt();
            } catch (InputMismatchException neh) {
                String s = in.next();
                boolean llll = false;
                int buff = 0;

                for (int i = 0; i < s.length(); i++) {
                    if (Character.isAlphabetic(s.charAt(i)) && s.toLowerCase().charAt(i) < 'k') {
                        buff = buff * 10 + Integer.parseInt(s.toLowerCase().charAt(i) - 'a' + "");

                    } else if (s.charAt(i) == '-') {
                        llll = true;
                    }

                }
                if (llll) {
                    sum -= buff;
                } else {
                    sum += buff;
                }
            }
        }

        try {
            PrintWriter pw = new PrintWriter(new File(args[1]));
            pw.print(sum);
            pw.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        in.close();
    }
}
