package ru.vlasova.task_2_meow;

public class Cat implements Meowable{
    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    //строковое представление
    @Override
    public String toString() {
        return "кот: " + name;
    }

    //мяуканье
    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }
}
