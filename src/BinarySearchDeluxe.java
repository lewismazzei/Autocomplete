import java.util.Comparator;

public class BinarySearchDeluxe {

    //returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        //find a random index that matches the key
        int j = binarySearch(a, key, comparator);

        //if a key has been found...
        if (j >= 0) {
            //...loop from the random index towards the start of the array
            for (int i = j; i >= 0; i--) {
                //if the start of the list has been reached the first index has been found
                if (i == 0) {
                    return i;
                //otherwise, check to see if the next element to the left still matches the prefix, if it doesn't, the first index has been found
                } else {
                    if (comparator.compare(a[i], a[i-1]) != 0) {
                        return i;
                    }
                    //if the next element to the left did still match the prefix then just continue looping through elements
                }
            }
        }
        //if the key has not been found return -1
        return -1;
    }

    //returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        //find a random index that matches the key
        int j = binarySearch(a, key, comparator);

        //if a key has been found...
        if (j >= 0) {
            //...loop from the random index towards the end of the array
            for (int i = j; i < a.length; i++) {
                //if the end of the list has been reached the last index has been found
                if (i == a.length - 1) {
                    return i;
                //otherwise, check to see if the next element to the right still matches the prefix, if it doesn't, the last index has been found
                } else {
                    if (comparator.compare(a[i], a[i+1]) != 0) {
                        return i;
                    }
                    //if the next element to the right did still match the prefix then just continue looping through elements
                }
            }
        }
        //if the key has not been found return -1
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
