import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class frenchDict {
    static HashMap<String, String> map = new HashMap<String, String>();
    static HashMap<String, Integer> freq = new HashMap<String, Integer>();
    static void fileEdit() {
        File infile = new File("C:\\Users\\Ms PD\\Desktop\\t8.shakespeare.txt");
        File outfile = new File("C:\\Users\\Ms PD\\Desktop\\t8.shakespeare.translated.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(infile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
            String line;
            while ((line = br.readLine()) != null) {
                String newLine = editLine(line);
                bw.write(newLine);
                bw.newLine();}
        } catch (IOException e) {
            System.out.println("file not found");
        }

    }
    static String editLine(String line) {
        String str[] = line.split("[^a-zA-Z]+");
        for (int i = 0; i < str.length; i++) {
            String low = str[i].toLowerCase();
            if (map.containsKey(low)) {
                freq.put(low, freq.getOrDefault(low,0) + 1);
                if (Character.isUpperCase(str[i].charAt(0))) {
                    line = line.replaceFirst(str[i], map.get(low).substring(0, 1).toUpperCase() + map.get(low).substring(1));
                } else {
                    line = line.replace(str[i], map.get(low));
                }

            }
        }

        return line;
    }
    static void fillMap() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ms PD\\Desktop\\french_dictionary.csv"));
        String line = null;

        while ((line = br.readLine()) != null) {
            String str[] = line.split(",");
            map.put(str[0], str[1]);
        }
    }

    static void writeData() throws IOException {

        File outfile = new File("C:\\Users\\Ms PD\\Desktop\\frequency.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
        Iterator it = map.entrySet().iterator();
        for (Map.Entry<String, String> element : map.entrySet()) {
            bw.write(element.getKey() + "," + element.getValue() + "," + freq.getOrDefault(element.getKey(),0));
            bw.newLine();
        }

    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        fillMap();
        fileEdit();
        writeData();
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        memory=memory/(1024*1024);
        long end = System.currentTimeMillis();
        long time = (long) ((end - start) / 1000F);
        String time1 = "0 minutes " + time + " seconds";
        String memory1 = memory + " MB";
       print(time1,memory1);

    }
static void print(String time1,String memory1) throws IOException {
 File outfile = new File("C:\\Users\\Ms PD\\Desktop\\performance.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
        bw.write(time1);
        bw.newLine();
        bw.write(memory1);
        bw.newLine();
System.out.println("Time to process: "+time1);
System.out.println("Memory used: "+memory1);
}
}
