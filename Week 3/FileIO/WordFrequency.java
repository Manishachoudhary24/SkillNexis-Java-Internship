
package FileIO;

import java.io.*;
import java.util.*;

public class WordFrequency {

    public static void main(String[] args) {

        Map<String, Integer> frequency = new TreeMap<>();

        File inputFile = new File("FileIO/input.txt");
        File outputFile = new File("FileIO/output.txt");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase()
                        .split("[^a-z0-9']+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        frequency.put(
                                word,
                                frequency.getOrDefault(word, 0) + 1
                        );
                    }
                }
            }

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(outputFile))) {

                for (Map.Entry<String, Integer> entry
                        : frequency.entrySet()) {

                    String result = entry.getKey()
                            + " : " + entry.getValue();

                    writer.write(result);
                    writer.newLine();

                    System.out.println(result);
                }
            }

            System.out.println("\nResults saved to FileIO/output.txt");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}


