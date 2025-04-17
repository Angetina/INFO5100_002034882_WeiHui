package exercises.exercise7;

import java.util.*;
import java.util.function.Predicate;

public class GenericMethods {

    //(a)count the number of elements in a collection
    public static <T> int countIf(Collection<T> collection, Predicate<T> condition) {
        int count = 0;

        for (T item : collection) {
            if (condition.test(item)) {
                count++;
            }
        }
        return count;

    }

    //test main
    public static  void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // size = 10
        int oddCount = countIf(numbers, n -> n % 2 == 0);
        System.out.println("oddCount:" + oddCount);

        int primeCount = countIf(numbers, n -> n % 2 == 1);
        System.out.println("primeCount:" + primeCount);

        List<String> words = Arrays.asList("level", "world", "madam", "racecar", "emily", "for", "gogo", "hh");
        int palindromeCount = countIf(words, GenericMethods::isPalindrome);
        System.out.println("palindromeCount:" + palindromeCount);

        String[] names = {"Emily", "Madam", "Racecar", "Gogo", "Hh"};
        System.out.println("before exchang:" + Arrays.toString(names));
        swap(names, 0, 2);
        System.out.println("after exchang:" + Arrays.toString(names));

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // size = 10
        int begin =1, end = 10;
        Integer max = maxInRange(nums, begin, end);
        System.out.println("From index " + begin + " to " + (end-1) + " max:" + max);
    }

    //whether it is a prime number
    public static boolean isPrime(int n) {
        if (n<2) return false;
        for (int i=2; i<= Math.sqrt(n); i++) {
            if (n%i == 0) return false;
        }
        return true;
    }

    //whether it is a palindrome
    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    //(b)exchange the positions of two different elements in an array
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //(c)find the maximal element
    public static <T extends Comparable<T>> T maxInRange(List<T> list,int begin, int end) {
        if (list == null || list.isEmpty() || begin >= end || begin < 0 || end > list.size()) {
            throw new IndexOutOfBoundsException("Invalid range or empty list");
        }

        T max = list.get(begin);
        for(int i=begin+1;i<end;i++){
            if(list.get(i).compareTo(max) > 0){
                max = list.get(i);
            }
        }
        return max;
    }
}
