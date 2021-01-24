package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String SPACE = " ";
    private static final String EMPTY = "";

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Path file = Paths.get(fileName);
        List<String> lines = Files.readAllLines(file);

        printMostUsedWords(parseWords(lines));
    }

    private static Map<String, Integer> parseWords(List<String> lines) {
        Map<String, Integer> wordsCountMap = new HashMap<>();
        for (String line : lines) {
            String[] words = line.split(SPACE);
            for (String word : words) {
                word = word.trim();
                if (word.equals(EMPTY)) {
                    continue;
                }
                if (wordsCountMap.containsKey(word)) {
                    Integer count = wordsCountMap.get(word) + 1;
                    wordsCountMap.put(word, count);
                } else {
                    wordsCountMap.put(word, 1);
                }
            }
        }

        return wordsCountMap;
    }

    private static void printMostUsedWords(Map<String, Integer> wordsCountMap) {
        wordsCountMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(100)
            .forEach(System.out::println);
    }
}
