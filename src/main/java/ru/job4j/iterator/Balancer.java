package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<ArrayList<Integer>> cyclicIterator = new CyclicIterator<>(nodes);
        while (cyclicIterator.hasNext() && source.hasNext()) {
            List<Integer> list = cyclicIterator.next();
            list.add(source.next());
        }
    }
}
