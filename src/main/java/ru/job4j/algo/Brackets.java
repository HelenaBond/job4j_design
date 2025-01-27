package ru.job4j.algo;

import java.util.*;

public class Brackets {
    /**
     * Доп. память O(n) в случае если в строке все скобки открывающиеся.
     * Сложность выполнения O(n) где n - кол-во символов в строке.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                stack.push(c);
            } else {
                char top = stack.isEmpty() ? '?' : stack.pop();
                if (top != map.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
