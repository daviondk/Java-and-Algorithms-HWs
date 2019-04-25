import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.*;

public class SHA256Sum {
    public static String getHash(InputStream in) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = new byte[16384];
        try (DigestInputStream dis = new DigestInputStream(in, sha256)) {
            while (dis.read(hashInBytes) != -1);
        }
        return DatatypeConverter.printHexBinary(sha256.digest()).toUpperCase();
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                printHash("-", System.in);
            } else {
                for (String fileName : args) {
                    try (FileInputStream in = new FileInputStream(fileName)) {
                        printHash(fileName, in);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Input/Output error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid hashing algorithm: " + e.getMessage());
        }
    }

    private static void printHash(String name, InputStream in) throws NoSuchAlgorithmException, IOException {
        System.out.println(getHash(in) + " *" + name);
    }
}