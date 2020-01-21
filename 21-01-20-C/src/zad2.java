import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class zad2 {
    static String[] text;
    static Map<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) {
        text = readFile("w-pustyni.txt", Charset.forName("cp1250"))
                .toLowerCase().split("[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?]+");
        for (var i = 0; i < text.length; i++) {
            var prev = counts.getOrDefault(text[i], 0);
            counts.put(text[i], prev + 1);
        }

        System.out.println("Top 100:");
        List<Map.Entry<String,Integer>> l = new ArrayList<>();
        for(Map.Entry<String,Integer> e:counts.entrySet()){
            l.add(e);
        }
        l.sort(Comparator.comparingInt(Map.Entry::getValue));
        var top = l.stream().skip(l.size()-101).collect(Collectors.toList());
        for(Map.Entry<String,Integer> e:top) {
            System.out.println(e.getKey() + "->" + e.getValue());
        }

        System.out.println("\nWith occurences of 5:");
        var special = l.stream().filter(x->x.getValue()==5)
                .collect(Collectors.toList());
        special.sort(Comparator.comparing(Map.Entry::getKey));
        for(Map.Entry<String,Integer> e:special) {
            System.out.println(e.getKey() + "->" + e.getValue());
        }
    }

    static String readFile(String name, Charset charset){
        StringBuilder s = new StringBuilder();
        try(BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(name), charset))){
            for(;;) {
                int c=file.read();
                if (c<0)
                    break;
                s.append((char)c);
            }
            return s.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}