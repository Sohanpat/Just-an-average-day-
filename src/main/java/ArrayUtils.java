import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Utility class providing min, max, sum, and average calculations
 * for integer arrays using both traditional for-loops and the Stream API.
 */
public class ArrayUtils {

    // -------------------------------------------------------------------------
    // Part 1: For-loop implementations
    // -------------------------------------------------------------------------

    /**
     * Returns the minimum value in the array using a for loop.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the smallest element
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int minLoop(int[] arr) {
        validateArray(arr);
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * Returns the maximum value in the array using a for loop.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the largest element
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int maxLoop(int[] arr) {
        validateArray(arr);
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Returns the sum of all elements in the array using a for loop.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the sum of all elements
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static long sumLoop(int[] arr) {
        validateArray(arr);
        long sum = 0;
        for (int value : arr) {
            sum += value;
        }
        return sum;
    }

    /**
     * Returns the average of all elements in the array using a for loop.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the average as a double
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static double averageLoop(int[] arr) {
        validateArray(arr);
        return (double) sumLoop(arr) / arr.length;
    }

    // -------------------------------------------------------------------------
    // Part 2: Stream API implementations
    // -------------------------------------------------------------------------

    /**
     * Returns the minimum value in the array using the Stream API.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the smallest element
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int minStream(int[] arr) {
        validateArray(arr);
        return Arrays.stream(arr)
                     .min()
                     .getAsInt();
    }

    /**
     * Returns the maximum value in the array using the Stream API.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the largest element
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int maxStream(int[] arr) {
        validateArray(arr);
        return Arrays.stream(arr)
                     .max()
                     .getAsInt();
    }

    /**
     * Returns the sum of all elements in the array using the Stream API.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the sum of all elements
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static long sumStream(int[] arr) {
        validateArray(arr);
        return Arrays.stream(arr)
                     .asLongStream()
                     .sum();
    }

    /**
     * Returns the average of all elements in the array using the Stream API.
     *
     * @param arr a non-null, non-empty array of integers
     * @return the average as a double
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static double averageStream(int[] arr) {
        validateArray(arr);
        return Arrays.stream(arr)
                     .average()
                     .getAsDouble();
    }

    // -------------------------------------------------------------------------
    // Shared helper
    // -------------------------------------------------------------------------

    private static void validateArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must be non-null and non-empty.");
        }
    }
}
