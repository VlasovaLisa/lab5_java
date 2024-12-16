package ru.vlasova.task_4;

public class Candidate {
    String surname;
    String name;
    int score1;
    int score2;

    public Candidate(String surname, String name, int score1, int score2) {
        this.surname = surname;
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
    }

    //прошел ли абитуриент тестирование
    public boolean isPassed() {
        return score1 >= 30 && score2 >= 30;
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }
}
