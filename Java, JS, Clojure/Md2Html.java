package md2html;

import base.Pair;

import java.io.*;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));

            List<String> content = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    Parser.parse(content, writer);
                    content.clear();
                }
                else {
                    content.add(line);
                }
                line = reader.readLine();
            }
            Parser.parse(content, writer);
            reader.close();
            writer.close();
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

class Parser {
    private static List<Pair<String, String>> tags = new ArrayList<>();
    private static List<Pair<String, String>> replacements = new ArrayList<>();
    static {
        tags.add(new Pair<>("**", "strong"));
        tags.add(new Pair<>("__", "strong"));
        tags.add(new Pair<>("*", "em"));
        tags.add(new Pair<>("_", "em"));
        tags.add(new Pair<>("--", "s"));
        tags.add(new Pair<>("++", "u"));
        tags.add(new Pair<>("`", "code"));

        replacements.add(new Pair<>("<", "&lt;"));
        replacements.add(new Pair<>(">", "&gt;"));
        replacements.add(new Pair<>("&", "&amp;"));
    }
    public static void parse(List<String> content, BufferedWriter writer) throws IOException {
        if (content.isEmpty()) {
            return;
        }

        String tag;

        int headerLevel = getHeaderLevel(content.get(0));
        if (headerLevel > 0) {
            content.set(0, content.get(0).substring(headerLevel + 1));
            tag = "h" + headerLevel;
        }
        else {
            tag = "p";
        }

        writer.write("<" + tag + ">");
        parseContent(content, writer);
        writer.write("</" + tag + ">\n");
    }

    private static int getHeaderLevel(String content) {
        int result = 0;
        while (content.charAt(result) == '#') {
            ++result;
        }
        return (Character.isWhitespace(content.charAt(result)) ? result : 0);
    }

    private static void parseContent(List<String> content, BufferedWriter writer) throws IOException {
        List<Integer> count = new ArrayList<>(), used = new ArrayList<>();
        for (int j = 0; j < tags.size(); ++j) {
            count.add(0);
            used.add(0);
        }

        for (String line : content) {
            for (int i = 0; i < line.length(); ++i) {
                if (line.charAt(i) == '\\') {
                    ++i;
                    continue;
                }

                for (int j = 0; j < tags.size(); ++j) {
                    if (line.substring(i).startsWith(tags.get(j).getFirst())) {
                        count.set(j, count.get(j) + 1);
                        i += tags.get(j).getFirst().length() - 1;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < tags.size(); ++i) {
            count.set(i, count.get(i) - (count.get(i) % 2));
        }

        for (int lineIndex = 0; lineIndex < content.size(); ++lineIndex) {
            if (lineIndex > 0) {
                writer.newLine();
            }

            String line = content.get(lineIndex);
            for (int i = 0; i < line.length(); ++i) {
                if (line.charAt(i) == '\\') {
                    writer.write(line.charAt(++i));
                    continue;
                }

                boolean replaced = false;
                for (Pair<String, String> replacement : replacements) {
                    if (line.substring(i).startsWith(replacement.getFirst())) {
                        writer.write(replacement.getSecond());
                        i += replacement.getFirst().length() - 1;
                        replaced = true;
                        break;
                    }
                }
                if (replaced) {
                    continue;
                }

                for (int j = 0; j < tags.size(); ++j) {
                    if (line.substring(i).startsWith(tags.get(j).getFirst())) {
                        if (used.get(j) >= count.get(j)) {
                            continue;
                        } else if (used.get(j) % 2 == 0) {
                            writer.write("<" + tags.get(j).getSecond() + ">");
                        } else {
                            writer.write("</" + tags.get(j).getSecond() + ">");
                        }
                        used.set(j, used.get(j) + 1);
                        i += tags.get(j).getFirst().length() - 1;
                        replaced = true;
                        break;
                    }
                }
                if (replaced) {
                    continue;
                }

                writer.write(line.charAt(i));
            }
        }
    }
}