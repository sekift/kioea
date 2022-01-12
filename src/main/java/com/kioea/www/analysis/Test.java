package com.kioea.www.analysis;

public class Test {
    public static void main(String[] args) {
        ConvolutionNerousNetwork();
        System.out.println("Hello World!");
    }

    static void ConvolutionNerousNetwork() {
        int[][] input = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6},
                {3, 4, 5, 6, 7},
                {4, 5, 6, 7, 8},
                {5, 6, 7, 8, 9}
        };
        int[][] kernel = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int[][] output = new int[input.length - kernel.length + 1][input[0].length - kernel[0].length + 1];
        for (int i = 0; i < input.length - kernel.length + 1; i++) {
            for (int j = 0; j < input[0].length - kernel[0].length + 1; j++) {
                for (int m = 0; m < kernel.length; m++) {
                    for (int n = 0; n < kernel[0].length; n++) {
                        output[i][j] += input[i + m][j + n] * kernel[m][n];
                    }
                }
            }
        }
        for (int[] ints : output) {
            for (int j = 0; j < output[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

}
