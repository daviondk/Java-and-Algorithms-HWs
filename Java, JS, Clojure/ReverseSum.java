import java.util.*;
import java.io.*;

class Scanner {
    BufferedReader br;
    int check = -1;
	Integer bufInt = 0;
    boolean checkInt = false;
    Scanner (InputStream is) {
        this.br = new BufferedReader(new InputStreamReader(is));
    }
    public void close() throws IOException {
        br.close();
    }
    public int hasNext() {
        if (check == -1) {
            try {
                check = br.read();
            } catch (IOException t) {}
            if (check == -1)
                return 0;
            else
				if (check == '\n') {
					//System.out.println("n");
					return 2;
				}
				else
					return 1;
        } else
        if (check == '\n') {
			//System.out.println("n double");
            return 2;
		}
        else
            return 1;
    }
    public char next() {
        if (hasNext() != 0) {
            char val = (char)check;
            check = -1;
            return val;
        }
        else
			throw new NoSuchElementException("End of file reached");
    }
    public boolean hasNextInt() {
        if (checkInt == false) {
            char cur;
            if (hasNext() != 0) {
                cur = next();
                while (Character.isWhitespace(cur) && hasNext() == 1) {
					//System.out.println("whitespace");
                    cur = next();
                }
                int num = 0, k = 0;
				String str = "";
                boolean sign = false;
                while (Character.isDigit(cur) || cur == '-') {
					str += cur;
					k++;
					//System.out.println("isdigit");
                   /*if (cur == '-')
                        sign = true;
                    else {
                        num += num*10 + Character.getNumericValue(cur);
                        k++;
                    }*/
                    if (hasNext() == 1)
                        cur = next();
					else
						break;
                }
                if (k != 0) {
                    bufInt = Integer.valueOf(str);//sign ? -num : num;
                    checkInt = true;
                    return true;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else {
            return true;
        }
    }
    public Integer nextInt() {
        if (hasNextInt()) {
            checkInt = false;
            return bufInt;
        }  //else
        /*if (hasNext() == 1)
            throw new InputMismatchException("Wrong input");
        else
            throw new NoSuchElementException("End of file reached");*/
		else
			return 228;
    }
}

public class ReverseSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(new ArrayList<Integer>());
        List<Integer> sumX = new ArrayList<Integer>();
        List<Integer> sumY = new ArrayList<Integer>();
		sumY.add(0);
        Integer x; 
		int nX = 0, nY = 0, maxNX = -1;
		int a = 0;
		char newLine;
        while (sc.hasNext() != 0) {
			//System.out.println("program");
            if (sc.hasNext() == 2) {
				newLine = sc.next();
				//a++;
                matrix.add(new ArrayList<Integer>());
                nY++;
                nX = 0;
                sumY.add(0);
            }
            else if (sc.hasNextInt() == true) {
				x = sc.nextInt();
                matrix.get(matrix.size() - 1).add(x);
                if (maxNX < nX) {
                    sumX.add(0);
                    maxNX++;
                }
                sumX.set(nX, (sumX.get(nX) + x));
                nX++;
                sumY.set(nY, (sumY.get(nY) + x));
            }
			//if (a == 3)
			//break;
			
        }
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(sumY.get(i) + sumX.get(j) - matrix.get(i).get(j) + " ");
            }
            if (i != matrix.size() - 1) 
				System.out.println();
        }
		//System.out.println();
        try {
            sc.close();
        } catch (IOException t) {}
    }
}