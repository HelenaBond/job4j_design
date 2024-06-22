package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        generics.printBoundedWildCard(first);
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
        generics.printLowerBoundedWildCard(third);
    }

    /* public void printObject(List<Object> list) {
        for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next(); */
    public void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*public void printBoundedWildCard(List<Predator> list) {
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();*/
    public void printBoundedWildCard(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("Текущий элемент: " + animal);
        }
    }

    /*public void printLowerBoundedWildCard(List<Predator> list) {
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();*/
    public void printLowerBoundedWildCard(List<? super Tiger> animals) {
        for (Object next : animals) {
            System.out.println("Текущий элемент: " + next);
        }
    }
}
