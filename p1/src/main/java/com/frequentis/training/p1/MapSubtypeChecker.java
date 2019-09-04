package com.frequentis.training.p1;

import java.util.HashMap;
import java.util.Map;

public class MapSubtypeChecker {

    public boolean isSubMap(final Map<String, String> m1, final Map<String, String> m2) {
        return m1.entrySet().stream()
                 .allMatch(entry -> m2.containsKey(entry.getKey()) && m2.get(entry.getKey()).equals(entry.getValue()));
    }

    public static void main(String[] args) {
        Map<String, String> m1 = new HashMap<>();
        Map<String, String> m2 = new HashMap<>();

        m1.put("A", "val1");
        m1.put("B", "val2");
        m1.put("C", "val3");

        m2.put("A", "val1");
        m2.put("B", "val2");
        m2.put("C", "val3");
        m2.put("D", "val1");
        m2.put("E", "val2");
        m2.put("F", "val3");

        System.out.println(new MapSubtypeChecker().isSubMap(m1, m2));
    }
}
