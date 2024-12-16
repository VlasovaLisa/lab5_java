package ru.vlasova.task_4;

import java.io.*;
import java.util.*;

public class FileRead {
    public static List<Candidate> readFromFile(String fileName) {
        List<Candidate> candidates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstLine = br.readLine();
            if (firstLine == null) {
                throw new NumberFormatException("файл пуст или отсутствует первая строка с количеством абитуриентов");
            }
            int n = Integer.parseInt(firstLine.trim().replaceAll("\uFEFF", "")); //количество абитуриентов

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                if (line != null && !line.isEmpty()) {
                    String[] parts = line.split(" ", 4); //разделяем строку на части

                    if (parts.length == 4) {
                        String surname = parts[0];
                        String name = parts[1];
                        int score1 = Integer.parseInt(parts[2]);
                        int score2 = Integer.parseInt(parts[3]);

                        candidates.add(new Candidate(surname, name, score1, score2));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("ошибка при чтении файла: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("ошибка в формате данных: " + e.getMessage());
            return null;
        }

        return candidates;
    }
}
