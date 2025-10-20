package c07;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wusd
 * @date 2025/10/20
 * @description
 */
public class MyHappensBefore {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        buildMap1(map1);
        buildMap2(map2);
    }

    private static void buildMap2(Map<String, String> map2) {
        for (int i = 0; i < 10000000; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
        }
    }

    private static void buildMap1(Map<String, String> map1) {
        for (int i = 0; i < 10000000; i++) {
            map1.put(String.valueOf(i), String.valueOf(i));
        }
    }
}
