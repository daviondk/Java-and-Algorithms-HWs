import java.math.*;
import java.util.*;
public class Reverse {
    public static void main(String[] args) {
        String line = "", s;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
		//for(int z = 0; z<3; z++){
            s = sc.nextLine();
			line = '\n' + line;
            for(int i = 0; i < s.length(); i++){
                int k = i;
                while (
                    i < s.length() && 
                    (Character.isDigit(s.charAt(i)) ||
                    s.charAt(i) == '-')
			    )
                    i++;
                if (i != k) {
                    line = Integer.parseInt(s.substring(k, i)) + " " + line;
                }
            }
        }
		System.out.println(line.substring(0,line.length()-1));
    }
}