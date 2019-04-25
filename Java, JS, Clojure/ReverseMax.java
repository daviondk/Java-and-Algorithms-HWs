import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseMax{

    public static void main(String[] args) {
        List<List<Integer>> mat = new ArrayList<>();
        Scanner in =new Scanner(System.in);
        int maxlen = 0;
        while(in.hasNextLine()){
            ArrayList<Integer> arra = new ArrayList<>();
            String line = in.nextLine();
            Scanner nm = new Scanner(line);
            while (nm.hasNextInt()){
                arra.add(nm.nextInt());
                if (maxlen<arra.size()){ maxlen=arra.size();}
            }
            mat.add(arra);
        }

        int[] l = new int[mat.size()];
        int[] c = new int[maxlen];

        for (int r:l){ r=Integer.MIN_VALUE;}
        for (int r:c){ r=Integer.MIN_VALUE;}


        for (int i=0;i<mat.size();i++) {
            for (int j = 0; j < mat.get(i).size(); j++) {
                try {
                    if (mat.get(i).get(j) > l[i]) {l[i] = mat.get(i).get(j);}
                }
                catch (IndexOutOfBoundsException ignored){}
            }
        }
        for (int i=0;i<maxlen;i++) {
            for (int j = 0; j < mat.size(); j++) {
                try {
                    if (mat.get(j).get(i) > c[i]) {c[i] = mat.get(j).get(i);}
                }
                catch (IndexOutOfBoundsException ignored){}
            }
        }

        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < c.length; j++) {
                if (mat.get(i).size() > j) {
                    System.out.print(Math.max(l[i], c[j]) + " ");
                }
            }
            System.out.print('\n');
        }


    }
}