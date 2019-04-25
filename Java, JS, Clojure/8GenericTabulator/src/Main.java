import exceptions.UnknownModeException;
import expression.generic.GenericTabulator;
import expression.generic.Tabulator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // args[0] == option(type)
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        int x1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y1 = sc.nextInt();
        int y2 = sc.nextInt();
        int z1 = sc.nextInt();
        int z2 = sc.nextInt();

        Tabulator gT = new GenericTabulator();
        Object[][][] result = null;
        try {
            result = gT.tabulate(args[0], query, x1, x2, y1, y2, z1, z2);
        } catch (UnknownModeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println(query);

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    System.out.println("mode=" + args[0] + ", x=" + i + " y=" + j + " z=" + k + ", result=" + result[i - x1][j - y1][k - z1]);
                }
            }
        }
    }
}
