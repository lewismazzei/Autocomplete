import java.util.Comparator;

public class BinarySearchDeluxe {

    //returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int index = binarySearch(a, key, comparator);

        if (index >= 0) {
            while (comparator.compare(a[index], a[index - 1]) == 0 && index > 0) {
                index--;
            }
            return index;
        } else {
            return -1;
        }
    }

    //returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int index = binarySearch(a, key, comparator);

        if (index >= 0) {
            while (comparator.compare(a[index], a[index+1]) == 0 && index < a.length-2) {
                index++;
            }
            return index;
        } else {
            return -1;
        }
    }

    //binary search implementation using custom comparator
    public static <Key> int binarySearch(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int lowIndex = 0;
        int middleIndex;
        int highIndex = a.length;

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
