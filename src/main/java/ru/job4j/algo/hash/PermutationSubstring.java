package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class PermutationSubstring {
    /**
     * Даны две строки s1 и s2, вернуть, true если s2 содержит
     * s1 или её анограмму.
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        int m = s1.length();
        if (m > n) {
            return false;
        }

        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
            windowMap.put(s2.charAt(i), windowMap.getOrDefault(s2.charAt(i), 0) + 1);
        }

        if (s1Map.equals(windowMap)) {
            return true;
        }

        for (int i = m; i < n; i++) {
            // карта начинает двигаться по строке s2
            // удаляя первые элементы строки из карты и добавляя последующие
            // сохраняя при этом размерность в s1
            windowMap.put(s2.charAt(i), windowMap.getOrDefault(s2.charAt(i), 0) + 1);
            char charToRemove = s2.charAt(i - m);
            windowMap.put(charToRemove, windowMap.get(charToRemove) - 1);
            if (windowMap.get(charToRemove) == 0) {
                windowMap.remove(charToRemove);
            }
            // как только карта s1 сравняется с картой плавающего окна - строка найдена
            if (s1Map.equals(windowMap)) {
                return true;
            }
        }
        return false;
    }
}
