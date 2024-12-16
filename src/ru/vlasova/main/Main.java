//вариант 4 Власова Елизавета

package ru.vlasova.main;
import ru.vlasova.task_1_fraction.*;
import ru.vlasova.task_2_meow.*;
import ru.vlasova.task_3.*;
import ru.vlasova.task_4.*;
import ru.vlasova.task_5.*;
import ru.vlasova.task_6.*;
import ru.vlasova.task_7.*;

import static java.lang.System.out;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        //1.1
        out.println("------------------ЗАДАНИЕ 1.1------------------");
        Fraction fraction1 = new Fraction(3, 4);
        out.println("исходная 1ая дробь : " + fraction1);
        out.println("вещественное значение 1ой дроби : " + fraction1.getDecimalValue());

        Fraction fraction2 = new Fraction(5, 8);
        out.println("исходная 2ая дробь : " + fraction2);
        out.println("вещественное значение 2ой дроби : " + fraction2.getDecimalValue());

        fraction1.setNumerator(6);   //изменение 1 дроби
        out.println("после изменения числителя 1ой дроби на 6: " + fraction1);
        out.println("обновленное вещественное значение дроби : " + fraction1.getDecimalValue());

        fraction2.setDenominator(10);   //изменение 2 дроби
        out.println("после изменения знаменателя 2ой дроби на 10: " + fraction2);
        out.println("обновленное вещественное значение дроби : " + fraction2.getDecimalValue());

        //повторное получение значения для проверки кэша
        out.println("повторное получение вещественного значения 1ой дроби (из кэша): " + fraction1.getDecimalValue());
        out.println("повторное получение вещественного значения 2ой дроби (из кэша): " + fraction2.getDecimalValue());

        //2.1
        out.println("------------------ЗАДАНИЕ 2.1------------------");

        List<Meowable> cats = new ArrayList<>();  //создаем список котов
        cats.add(new Cat("Барсик"));
        cats.add(new Cat("Мурзик"));

        Funs.meowsCare(cats); //передаем список в метод

        //3.4
        out.println("------------------ЗАДАНИЕ 3.4------------------");
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");
        list.add("D");

        String el = "B";

        out.println("исходный список: " + list);
        List<String> res = ListMod.addAfter(list, el);
        out.println("результат: " + res);

        //4.4
        out.println("------------------ЗАДАНИЕ 4.4------------------");
        String fileName = "test4.txt";
        List<Candidate> candidates = FileRead.readFromFile(fileName);

        if (candidates == null) {
            return;
        }

        //фильтрация и вывод
        out.println("абитуриенты, непрошедшие тестирование:");
        for (Candidate candidate : candidates) {
            if (!candidate.isPassed()) {
                out.println(candidate);
            }
        }

        //5.4
        out.println("------------------ЗАДАНИЕ 5.4------------------");
        String filePath = "text5.txt";
        //чтение файла
        String text = Consonants.readFile(filePath);
        if (text.isEmpty()) {
            out.println("текст не был прочитан");
            return;
        }

        //извлечение и сортировка глухих согласных
        Set<Character> consonants = Consonants.extract(text);

        //вывод
        if (consonants.isEmpty()) out.println("глухих согласных в нечетных словах не найдено");
        else {
            out.println("глухие согласные в алфавитном порядке: " + consonants);
        }

        //6.4
        out.println("------------------ЗАДАНИЕ 6.4------------------");
        Queue<String> queue = new LinkedList<>(); //создаем очередь
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("A");
        queue.add("A");
        queue.add("C");
        queue.add("B");

        int i = 0; //начальный индекс
        int j = 3; //конечный индекс
        out.println("равенство 1 сегмента с " + i + " по " + j + " элемент: " + QueueSegment.areEqual(queue, i, j));

        int p = 1;
        int q = 6;
        out.println("равенство 2 сегмента с " + p + " по " + q + " элемент: " + QueueSegment.areEqual(queue, p, q));

        //7.1
        out.println("------------------ЗАДАНИЕ 7.1------------------");
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, -2));
        points.add(new Point(1, 3));
        points.add(new Point(3, -4));
        points.add(new Point(3, 4));
        points.add(new Point(5, 5));
        points.add(new Point(2, -1));
        points.add(new Point(2, 2));

        //поток для обработки списка точек
        List<Point> listPoints = points.stream()
                .map(point -> {
                    if (point.getY() < 0) {
                        point.setY(-point.getY());  //поменять знак отрицательного у
                    }
                    return point;
                })
                .distinct()  //убираем дубликаты точек
                .sorted(Comparator.comparingDouble(Point::getX))  //сортировка по х
                .collect(Collectors.toList());

        //новая ломаная из обработанных точек
        Polyline resPolyline = new Polyline(listPoints);
        out.println(resPolyline);

        //7.2
        out.println("------------------ЗАДАНИЕ 7.2------------------");
        Path filePath2 = Path.of("text7_2.txt");

        Map<Integer, List<String>> groupedPeople = Files.lines(filePath2)
                .map(line -> line.replace("\uFEFF", "").split(":")) //убираем BOM и разбиваем строку
                .filter(parts -> parts.length == 2 && !parts[1].isBlank()) //убираем строки без номера
                .collect(Collectors.groupingBy(
                        parts -> Integer.parseInt(parts[1].trim()), //группируем по номеру
                        Collectors.mapping(
                                parts -> formatName(parts[0].trim()), //форматируем имя
                                Collectors.toList()
                        )
                ));

        //вывод
        out.println(groupedPeople);

    }

    //7.2 форматирование имени (первая буква заглавная, остальные строчные)
    private static String formatName(String name) {
        if (name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}