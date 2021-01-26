package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String SPACE = " ";
    private static final String EMPTY = "";

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Path file = Paths.get(fileName);
        String[] words = Files.readAllLines(file).stream()
            .flatMap(line -> Arrays.stream(line.split(SPACE)))
            .toArray(String[]::new);

        printMostUsedWords(parseWords(words));
    }

    private static Map<String, Integer> parseWords(String[] words) {
        Map<String, Integer> wordsCountMap = new HashMap<>();
        for (String word : words) {
            word = word.trim();
            if (word.equals(EMPTY)) {
                continue;
            }
            int count = 1;
            if (wordsCountMap.containsKey(word)) {
                count = wordsCountMap.get(word) + 1;
            }

            wordsCountMap.put(word, count);
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
