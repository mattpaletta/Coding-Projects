import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class SortsCapade {
    final public static int MAX_NUMBERS = 1000;
    final public static int DEFAULT_SEED = 10;
    public static int original[], forSorting[];
  
    public static boolean dump(int array[], boolean doPrint) {
        boolean isSorted = true;
        for (int i = 0; i < (array.length)-1; i++) {
            if (doPrint) {
                System.out.println(array[i]);
            }
            if (array[i] > array[i+1]) {
                isSorted = false;
            }
        }
        if (doPrint) {
            System.out.println(array[array.length-1]);
        }
        
        return isSorted; 
    }


    public static int selectionIndexOfLargest(int array[], int n) {
        int indexSoFar = 0;

        for (int i = 1; i < n; i++) {
            if (array[i] > array[indexSoFar]) {
                indexSoFar = i;
            }
        }

        return indexSoFar;
    }


    public static void selectionSort(int array[], int n) {
        for (int last = n-1; last >= 1; last--) {
            int largest = selectionIndexOfLargest(array, last+1);
            int temp = array[largest];
            array[largest] = array[last];
            array[last] = temp;
        }
    }


    public static void bubbleSort(int array[], int n) {
        boolean sorted = false;

        for (int pass = 1; pass < n && !sorted; pass++) {
            sorted = true;
            for (int i = 0; i < n - pass; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }


    public static void insertionSort(int array[], int n) {
        for (int i = 1; i < n; i++) {
            int next = array[i];
            int  loc  = i;

            while (loc > 0 && array[loc-1] > next) {
                array[loc] = array[loc-1];
                loc--;
            }
            array[loc] = next;
        }
    }


    public static void merge(int array[], int tempArray[],
        int first, int mid, int last)
    {
        int first1 = first;
        int last1  = mid;
        int first2 = mid + 1;
        int last2  = last;

        int i = first1;

        while (first1 <= last1 && first2 <= last2) {
            if (array[first1] < array[first2]) {
                tempArray[i] = array[first1];
                first1++;
            } else {
                tempArray[i] = array[first2];
                first2++;
            }
            i++;
        }

        while (first1 <= last1) {
            tempArray[i] = array[first1];
            first1++;
            i++;
        }

        while (first2 <= last2) {
            tempArray[i] = array[first2];
            first2++;
            i++;
        }

        for (i = first; i <= last; i++) {
            array[i] = tempArray[i];
        }
    }


    // Note: The tempArray[] is allocated once (i.e., first call
    // to mergeSort) and is passed along from there on in. Doing
    // this saves hammering the memory allocator for each
    // merge step. Code is based on "Walls and Mirrors" text.
    //
    public static void mergesort(int array[], int tempArray[],
        int first, int last)
    {
        if (first < last) {
            int mid = (first + last)/2;
            mergesort(array, tempArray, first, mid);
            mergesort(array, tempArray, mid+1, last);
            merge(array, tempArray, first, mid, last);
        }
    }


    public static int partition(int array[], int first, int last) {
        int tempItem;

        int pivot = array[first];  // Simple pivot...
        
        int lastS1 = first;

        for (int firstUnknown = first + 1;
                 firstUnknown <= last;
                 firstUnknown++)
        {
            if (array[firstUnknown] < pivot) {
                lastS1++;
                tempItem = array[firstUnknown];
                array[firstUnknown] = array[lastS1];
                array[lastS1] = tempItem;
            }
        }        

        tempItem = array[first];
        array[first] = array[lastS1];
        array[lastS1] = tempItem;

        return lastS1;
    }

    public static void quicksort(int array[], int first, int last) {
        int pivotIndex;

        if (first < last) {
            pivotIndex = partition(array, first, last);
            quicksort(array, first, pivotIndex-1);
            quicksort(array, pivotIndex+1, last);
        }
    }

    public static void radixsortA(int array[]) {

        // Find maximum
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }

        // Create buckets
        int digits = (int)(Math.ceil(Math.log10(max)));
        List<LinkedList<String>> bucket = 
            new ArrayList<LinkedList<String>>();
        for (int i = 0; i < digits; i++) {
            bucket.add(new LinkedList<String>());
        }

        // Convert integers to strings. We'll work with
        // chars as that will be faster than dividing by
        // ten / modulo by ten.
        String arrayS[] = new String[array.length];
        String f = "%0" + digits + "d";
        for (int i = 0; i < array.length; i++) {
            arrayS[i] = String.format(f, array[i]);
        }

        // Now perform the algorithm
        for (int d = 0; d < digits; d++) {
            int i;
            // Empty the current contents of the buckets
            for (int b = 0; b < digits; b++) {
                bucket.get(b).clear();
            }
            for (i = 0; i < arrayS.length; i++) {
                char c = arrayS[i].charAt(digits - d - 1);
                int  ic = (int)(c - '0');
                bucket.get(ic).add(arrayS[i]);
            }
            i = 0;
            for (int b = 0; b < digits; b++) {
                for (String s : bucket.get(b)) {
                    arrayS[i++] = s;
                }
            }
        }

        // Convert back from Strings to int
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(arrayS[i]);
        }
    }


    // Pretty much straight from Wikipedia
    //
    public static void radixsortB(int array[]) {
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] counter = new LinkedList[] {
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>(),
            new LinkedList<Integer>()
        };

       
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int digits = (int)(Math.ceil(Math.log10(max)));

        long mod;
        int dev;
        int i;
        for (i = 0, mod = 10, dev = 1; 
            i < digits;
            i++, dev *= 10, mod *= 10)
        {
            for (int j = 0; j < array.length; j++) {
                int bucket = (int)(array[j] % mod) / dev;
                counter[bucket].add(array[j]);
            }
            int pos = 0;
            for (int j = 0; j < counter.length; j++) {
                Integer value = null;
                while ((value = counter[j].poll()) != null) {
                    array[pos++] = value; 
                }
            }
        } 
    }

    // Based on an idea at StackOverflow, but cleaned up
    // a little. This is based on the radix as binary
    // number.
    //
    // http://stackoverflow.com/questions/4146843/
    //    when-should-we-use-radix-sort
    //
    // In this clever idea, the buckets are
    // actually contained within a single array of int having
    // the same size as the original array. Lots of bit shifting
    // and masking here (which will be faster than integer
    // division).
    //

    public static void radixsortC(int array[]) {
        int bucket[] = new int[array.length];
        int radix = 256; // 8-bit radix
        int mask = radix-1;  // Now have a value with all lower bits = 1
        int shift[] = {0, 8, 16, 24};
        int top[] = new int[radix];  // Number of buckets equals radix

        for (int s = 0; s < shift.length; s++) {
            for (int r = 0; r < radix; r++) {
                top[r] = 0;
            }

            for (int i = 0; i < array.length; i++) {
                int bucketIndex = (array[i] >> shift[s]) & mask;
                top[bucketIndex]++;
            }

            for (int r = 1; r < radix; r++) {
                top[r] = top[r] + top[r-1];
            }

            for (int r = radix - 1; r > 0; r--) {
                top[r] = top[r-1];
            }
            top[0] = 0;

            for (int i = 0; i < array.length; i++) {
                int bucketIndex = (array[i] >> shift[s]) & mask;
                bucket[top[bucketIndex]] = array[i]; 
                top[bucketIndex]++;
            }

            for (int i = 0; i < array.length; i++) {
                array[i] = bucket[i];
            }
        }
        
    }


    // If executed without command-line parameters, the program will perform
    // sorts on an array of 1000 random integer with the random-seed value
    // initialized to DEFAULT_SEED.
    //
    // e.g., $ java SortsCapade
    //
    // If one integer is given as a command-line parameter, then the program
    // with still randomize using the default seed but it will sort the number
    // of integers given as the parameter:
    //
    // e.g., $ java SortsCapade 100000
    //
    // will sort 100,000 integers.
    //
    // If two integers are given, the first will be the number of random numbers
    // and the second will be the value given as the seed for the random-number
    // generator:
    //
    // e.g., $ java SortsCapade 5000 3141
    //
    // will sort 5.000 random integers, with 3141 given as the seed value for
    // the random-number generator.
    //

    public static void main(String args[]) {
        long beforeTime, afterTime;
        Random r;

        if (args.length < 1) {
            original = new int[MAX_NUMBERS];
            r = new Random(DEFAULT_SEED);
        } else if (args.length < 2) {
            original = new int[Integer.parseInt(args[0])];
            r = new Random(DEFAULT_SEED);
        } else {
            original = new int[Integer.parseInt(args[0])];
            r = new Random(Integer.parseInt(args[1]));
        }

        for (int i = 0; i < original.length; i++) {
            original[i] = Math.abs(r.nextInt());
        }
  
        dump(original, true);
 
        for (int c = 0; c <= 8; c++) {
            String message = "";   
            forSorting = Arrays.copyOf(original, original.length);
            beforeTime = System.nanoTime();

            switch(c) {
            case 0:
                message = "Java JCF sort";
                Arrays.sort(forSorting, 0, forSorting.length);
                break;
            case 1:
                message = "selection sort";
                selectionSort(forSorting, forSorting.length);
                break;
            case 2:
                message = "bubble sort";
                bubbleSort(forSorting, forSorting.length);
                break;
            case 3:
                message = "insertion sort";
                insertionSort(forSorting, forSorting.length);
                break;
            case 4:
                message = "mergesort";
                int tempArray[] = new int[forSorting.length];
                mergesort(forSorting, tempArray, 0, forSorting.length-1);
                break;
            case 5:
                message = "quicksort";
                quicksort(forSorting, 0, forSorting.length-1);
                break;
            case 6:
                message = "radix sort (implementation A)";
                radixsortA(forSorting);
                break;
            case 7:
                message = "radix sort (implementation B)";
                radixsortB(forSorting);
                break;
            case 8:
                message = "radix sort (implementation C)";
                radixsortC(forSorting);
                break;
            default:
                break;
            }

            afterTime = System.nanoTime();

            if (dump(forSorting, false) == false) {
                System.out.println(message + " : FAILED");
            } else {
                System.out.println(message + " -- microseconds: " +
                    (double)(afterTime - beforeTime)/1000);
            }
        }

    } 
}
