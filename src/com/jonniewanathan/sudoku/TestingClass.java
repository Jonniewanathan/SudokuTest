package com.jonniewanathan.sudoku;

/**
 * Created by jonathan on 21/02/2017.
 */
public class TestingClass {
    private static int[][] sudoku = new int[9][9];

    public static void main(String[] args) {

        int column = 0;
        int row = 0;
        boolean valid = false;
        int count = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
            }
        }

        while(column < 9)
        {
            row = 0;
            while(row < 9)
            {
                valid = false;
                count = 0;
                while(!valid)
                {
                    fill3X3WithNumber(column,(column+3),row,(row+3));
                    valid = fill3X3(column,(column+3),row,(row+3));


                    if(valid)
                    {
                        count = 0;
                    }
                    if(count > 2000)
                    {
                        column = 0;
                        row = 0;
                        count = 0;
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                sudoku[i][j] = 0;
                            }
                        }
                    }
                    count++;


                }
                row = row+3;
            }
            column = column+3;

        }
        print2DArray(sudoku);

    }

    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void fillArray(int[] numbers, int number) {

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = number;
        }
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void printArray(int[] array) {
        for (int j = 0; j < 9; j++) {
            System.out.print(array[j] + " ");
        }
    }

    public static boolean fill3X3(int columnStart, int columnEnd, int rowStart, int rowEnd) {
        boolean valid = false;
        int numberOfTimes = 0;
        int number = 0;
        int count = 0;
        int[] nums = new int[9];
        fillArray(nums, 10);


        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                valid = false;

                if (i == columnStart) {
                    number = randomWithRange(1, 9);
                    sudoku[i][j] = number;
                    nums[count] = number;
                }

                do {

                    if (linearSearch(nums,nums.length,number) || linearSearch(populateVerticalArray(j),9,number) || linearSearch(populateHorizontalArray(i),9,number)) {

                        number = randomWithRange(1, 9);
                        numberOfTimes++;

                        if (numberOfTimes > 20)
                        {
                            return false;
                        }
                    } else {
                        nums[count] = number;
                        sudoku[i][j] = number;
                        valid = true;
                    }

                } while (!valid);

                count++;
            }
        }
        return valid;
    }

    public static int[] populateVerticalArray(int column) {
        int[] vert = new int[9];

        for (int i = 0; i < vert.length; i++) {
            vert[i] = sudoku[i][column];
        }
        return vert;
    }
    public static int[] populateHorizontalArray(int row){
        int[] horz = new int[9];

        for (int i = 0; i < horz.length; i++) {
            horz[i] = sudoku[row][i];
        }
        return horz;
    }

    public static boolean linearSearch(int[]a,int currentSize,int searchKey)
    {
        boolean found = false;
        int index = 0;

        while(!found && index < currentSize)
        {
            if(a[index] == searchKey)
            {
                found = true;
            }
            else
            {
                index++;
            }
        }
        if (found)
        {
            return found;
        }
        else
        {
            return found;
        }

    }
    public static void fill3X3WithNumber(int columnStart, int columnEnd, int rowStart, int rowEnd) {
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                sudoku[i][j] = 0;
            }

        }
    }

}
