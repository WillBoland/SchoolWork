package com.company;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        System.out.println(Lab.maxNumber(1, -3, 2));
        System.out.println(Lab.allCaps("ThIs sHoUld be all caps!!!"));
        System.out.println(Lab.subsequenceIndex(new int[]{1, 2, 3, 4}, new int[] {3, 4}));
    }
}


/**
 * Lab 1
 *  1: Given 3 numbers, find the largest
 *  2: Convert a string to all capital letters
 *  3: Given 2 arrays, a and b, find the index of the where b is a subsequence of a
 */
class Lab {
    static int maxNumber(int first, int second, int third) {
        int[] nums = { first, second, third };
        int max = nums[0];
        for(int current: nums) {
            max = current > max ? current : max;
        }
        return max;
    }

    static String allCaps(String input) {
        return input.toUpperCase();
    }

    /**
     * finds where b is a subsequence of a
     * @param a larger list of numbers
     * @param b smaller list of numbers
     * @return index where the sequence starts; -1 if no sequence found
     */
    static int subsequenceIndex(int[] a, int[] b) {
        String inputA = "";
        String inputB = "";

        for(int num: a) {
            inputA += num;
        }
        for(int num: b) {
            inputB += num;
        }

        return inputA.indexOf(inputB);
    }
}