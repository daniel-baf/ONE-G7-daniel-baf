package Utils;

import java.util.Map;

public class PrinterUtils<K, V> {
    public void printMapKeyValue(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            System.out.println("The map is empty or null.");
            return;
        }

        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}