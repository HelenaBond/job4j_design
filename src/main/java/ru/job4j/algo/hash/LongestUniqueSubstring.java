package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    /**
     * разработать алгоритм нахождение наименьшего диапазона
     * с данным количеством различных элементов
     * @param str
     * @return
     */
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        int maxLength = 0;
        /**
         * начало подстроки с уникальными символами
         */
        int start = 0;
        int left = 0;
        Set<Character> uniqueChars = new HashSet<>();

        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);
            /**
             * Удаляем символы из множества, пока текущий символ уже есть в множестве
             */
            while (uniqueChars.contains(currentChar)) {
                uniqueChars.remove(str.charAt(left));
                left++;
            }
            uniqueChars.add(currentChar);
            /**
             * Обновляем максимальную длину и начало самой длинной подстроки
             */
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }

        return str.substring(start, start + maxLength);
    }
}
