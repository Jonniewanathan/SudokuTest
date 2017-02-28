package com.jonniewanathan.sudoku;

import java.util.Arrays;

/**
 * Created by jonathan on 27/02/2017.
 */
public class SudokuUtility {

    private SudokuUtility()
    {

    }
    public static void fillSudokuBoard(int[][] sudoku)
    {
        int column = 0;
        int row = 0;
        boolean valid = false;
        int count = 0;

        while(column < 9)
        {
            row = 0;
            while(row < 9)
            {
                valid = false;
                count = 0;
                while(!valid)
                {
                    fill3X3WithNumber(column,(column+3),row,(row+3), sudoku);
                    valid = fill3X3(column,(column+3),row,(row+3), sudoku);

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
    }

    public static void removeNumbersPlayboard(int[][] sudoku)
    {
        int column = 0;
        int row = 0;

        while(column < 9)
        {
            while(row < 9)
            {
                removeNumbers3X3(column,(column+3),row,(row+3),sudoku);
                row = row+3;
            }
            row = 0;
            column = column+3;
        }
    }

    public static void removeNumbers3X3(int columnStart, int columnEnd, int rowStart, int rowEnd, int[][] sudokuBoard)
    {
        int count = 0;
        boolean valid = false;
        int row;
        int column;
        int numberToRemove = randomWithRange(3,4);

        do {
            column = randomWithRange(columnStart,(columnEnd-1));
            row = randomWithRange(rowStart,(rowEnd-1));

            if(count == numberToRemove)
            {
                valid = true;
            }
            else if(sudokuBoard[row][column] != 0)
            {
                sudokuBoard[row][column] = 0;
                count++;
                System.out.println();
                print2DArray(sudokuBoard);
                System.out.println("Count: " + count);

            }

        } while (!valid);



        System.out.println();

    }

    public static boolean fill3X3(int columnStart, int columnEnd, int rowStart, int rowEnd, int[][] sudoku) {
        boolean valid = false;
        int numberOfTimes = 0;
        int number = 0;
        int count = 0;
        int[] nums = new int[9];
        Arrays.fill(nums,10);


        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                valid = false;

                if (i == columnStart) {
                    number = randomWithRange(1, 9);
                    sudoku[i][j] = number;
                    nums[count] = number;
                }

                do {

                    if (linearSearch(nums,nums.length,number) || linearSearch(populateVerticalArray(j,sudoku),9,number) || linearSearch(populateHorizontalArray(i,sudoku),9,number)) {

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

    public static int[] populateVerticalArray(int column, int[][] sudoku) {
        int[] vert = new int[9];

        for (int i = 0; i < vert.length; i++) {
            vert[i] = sudoku[i][column];
        }
        return vert;
    }

    public static int[] populateHorizontalArray(int row, int[][] sudoku){
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
        return found;

    }

    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void fill3X3WithNumber(int columnStart, int columnEnd, int rowStart, int rowEnd, int[][] sudoku) {
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                sudoku[i][j] = 0;
            }

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
}
