package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Vitaly", "Aleksey", "Mark");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Petrov", "Ploshkin");

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            people.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(90),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        System.out.println("Количество несовершеннолетних");
        System.out.println(people.stream()
                .filter(x -> x.getAge() < 18)
                .count());

        System.out.println("Список фамилий призывников");
        System.out.println(people.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList()));

        System.out.println("Потенциально работоспособные люди с высшим образованием");
        System.out.println(people.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> (x.getSex().equals(Sex.WOMAN) && (x.getAge() >= 18 && x.getAge() < 60))
                || (x.getSex().equals(Sex.MAN) && (x.getAge() >= 18 && x.getAge() < 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()));
    }
}
