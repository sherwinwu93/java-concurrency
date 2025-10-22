package c15;

import java.util.Vector;

/**
 * @author Wusd
 * @date 2025/10/22
 * @description
 */
public class TestVector {
    private Vector<String> vector;

    public Object getLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        return vector.get(lastIndex);
    }

    public void deleteLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        vector.remove(lastIndex);
    }

    public Object getLastSynchronized(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
    }

    public void deleteLastSynchronized(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            vector.remove(lastIndex);
        }
    }
}
