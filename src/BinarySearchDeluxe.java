import java.util.Comparator;

public class BinarySearchDeluxe {

    //returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int index = binarySearch(a, key, comparator);

        if (index >= 0) {
            for (int i = index; i >= 0; i--) {
                if (i == 0) {
                    return i;
                } else {
                    if (comparator.compare(a[i], a[i-1]) != 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    //returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int index = binarySearch(a, key, comparator);

        if (index >= 0) {
            for (int i = index; i < a.length; i++) {
                if (i == a.length - 1) {
                    return i;
                } else {
                    if (comparator.compare(a[i], a[i+1]) != 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    //binary search implementation using custom comparator
    public static <Key> int binarySearch(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int lowIndex = 0;
        int middleIndex;
        int highIndex = a.length - 1;

        while (lowIndex <= highIndex) {
            middleIndex = (highIndex + lowIndex) / 2;

            if (comparator.compare(key, a[middleIndex]) < 0) {
                highIndex = middleIndex - 1;
            } else if (comparator.compare(key, a[middleIndex]) > 0) {
                lowIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }
}
