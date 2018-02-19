import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that
    // equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        ArrayList<Integer> keyIndices = findKeyIndices(a, key, comparator);

        int firstIndex = a.length - 1;

        if (keyIndices.size() != 0) {
            for (int keyIndex : keyIndices) {
                if (keyIndex < firstIndex) {
                    firstIndex = keyIndex;
                }
            }
        }
        return firstIndex;
    }

    // Returns the index of the last key in a[] that
    //equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        ArrayList<Integer> keyIndices = findKeyIndices(a, key, comparator);

        int lastIndex = 0;

        if (keyIndices.size() != 0) {
            for (int keyIndex : keyIndices) {
                if (keyIndex > lastIndex) {
                    lastIndex = keyIndex;
                }
            }
        }
        return lastIndex;
    }

    public static <Key> int binarySearch(Key[] a, Key key, Comparator<Key> comparator) {
        int lowIndex = 0;
        int middleIndex;
        int highIndex = a.length;

        while ((lowIndex + 1) < highIndex) {
            middleIndex = (highIndex + lowIndex) / 2;
            if (comparator.compare(key, a[middleIndex]) < 0) {
                highIndex = middleIndex;
            } else if (comparator.compare(key, a[middleIndex]) > 0) {
                System.out.println(key.toString() + " : " + a[middleIndex].toString());
                lowIndex = middleIndex;
            } else {

                return middleIndex;
            }
        }
        return -1;
    }

    public static <Key> ArrayList<Integer> findKeyIndices(Key[] a, Key key, Comparator<Key> comparator) {
        ArrayList<Integer> keyIndices = new ArrayList<>();

        while (true) {
            int index = binarySearch(a, key, comparator);

            if (index >= 0) {
                keyIndices.add(index);
                a[index] = null;
            } else {
                break;
            }
        }
        return keyIndices;
    }
}
