import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

    //returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //if any of the arguments passed are null throw an exception
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        //store the original array for use later
        Key[] aOriginal = a;
        //binary search the array for a given key and store the index of the random match found
        int randomMatchIndex = binarySearch(a, key, comparator);
        //if no keys are found by the binary search return -1
        if (randomMatchIndex == -1) return -1;

        //loop until the first index matching the key has been found
        while (true) {
            //if the current index is the first in the current array (which is also the first in the original array) then the first index has been found
            if (randomMatchIndex == 0) return randomMatchIndex;
            //if the element to the left and the current element (which always matches the key) do not match then the first index has been found...
            if (comparator.compare(a[randomMatchIndex], a[randomMatchIndex-1]) != 0) break;
            //...otherwise it has not been found so split the array (between the start of the orignal array and the current matching index)
            a = Arrays.copyOfRange(a, 0, randomMatchIndex);
            //search for another matching key in this now smaller array
            randomMatchIndex = binarySearch(a, key, comparator);
        }
        //once the first index has been found, search for the corresponding element in the original array and return its index
		//System.out.println("Passes: " + passes);
		//System.out.println("1 + log2N: " + (1 + (Math.log(a.length)/Math.log(2))));
        return Arrays.binarySearch(aOriginal, a[randomMatchIndex]);
    }

    //returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //if any of the arguments passed are null throw an exception
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        //store the original array for use later
        Key[] aOriginal = a;
        //binary search the array for a given key and store the index of the random match found
        int randomMatchIndex = binarySearch(a, key, comparator);
        //if no keys are found by the binary search return -1
        if (randomMatchIndex == -1) return -1;

        //loop until the first index matching the key has been found
        while (true) {
            //if the current index is the first in the current array (which is also the first in the original array) then the first index has been found
            if (randomMatchIndex == a.length-1) return randomMatchIndex;
            //if the element to the left and the current element (which always matches the key) do not match then the first index has been found...
            if (comparator.compare(a[randomMatchIndex], a[randomMatchIndex+1]) != 0) break;
            //...otherwise it has not been found so split the array (between the start of the orignal array and the current matching index)
            a = Arrays.copyOfRange(a, randomMatchIndex, a.length-1);
            //search for another matching key in this now smaller array
            randomMatchIndex = binarySearch(a, key, comparator);
        }
        //once the first index has been found, search for the corresponding element in the original array and return its index
        return Arrays.binarySearch(aOriginal, a[randomMatchIndex]);
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