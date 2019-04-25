
import java.math.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

public class WordStatLineIndex {
    public static void main(String[] args) {
        BufferedReader in = null;
        BufferedWriter out = null;
        Map<String, List<String>> stats = new TreeMap<String, List<String>>();
        List<String> file = null;

        try{
            in = Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8);
            out = Files.newBufferedWriter(Paths.get(args[1]), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            System.err.print("File not found!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("Too many arguments!");
            return;
        } catch (UnsupportedEncodingException e) {
            System.err.print("Unsopported encoding!");
            return;
        } catch (IOException e) {
            System.err.print("Invalid input/output!");
            return;
        } catch (SecurityException e) {
            System.err.print("Access denied!");
            return;
        }catch (IllegalArgumentException e) {
            System.err.print("Too few arguments!");
            return;
        }
        int number = 0;
        List<String> cur = new ArrayList<String>();
        int lineNo = 1;
        int noInLine = 0;

        int s = 0;
        char c = 0;
        String wd = "";

        try{
            while(s != -1){
                if(c == '\n'){
                    lineNo++;
                    noInLine = 0;
                }
				s = in.read();
				c = (char)s;
                wd = "";
                int k = 0;
                while (
                        s != -1 &&
                        (Character.isLetter(c)) ||
                        Character.getType(c) == Character.DASH_PUNCTUATION ||
                        c == '\''
                )
                {
                    k++;
                    wd += c;
                    s = in.read();
                    c = (char)s;
                }
                if (k>0) {
                    noInLine++;
                    wd = wd.toLowerCase();
                    if(stats.get(wd) == null){
                        cur = new ArrayList<String>();
                        cur.add(lineNo + ":" + noInLine);
                        stats.put(wd, cur);
                    }
                    else{
                        stats.get(wd).add(lineNo + ":" + noInLine);
                    }
                }
            }

        } catch(IOException e){
            System.err.print("Invalid input/output!");
            try{
                in.close();
                out.close();
                return;
            }
            catch(IOException t){
                System.err.print("Invalid input/output!");
                return;
            }
        }

        try{
            for(Map.Entry<String,List<String>> stat : stats.entrySet()){
                out.write(stat.getKey() + " " + stat.getValue().size());
                for(String str : stat.getValue()){
                    out.write(" " + str);
                }
                out.write('\n');
            }
        } catch (IOException e) {
            System.err.print("Invalid input/output!");
            return;
        } catch (OutOfMemoryError e) {
            System.err.print("Out of memory!");
            return;
        }
        finally{
            try{
                in.close();
                out.close();
            }
            catch(IOException e){
                System.err.print("Invalid input/output!");
                return;
            }
        }
    }
}