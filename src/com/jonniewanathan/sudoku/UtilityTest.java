package com.jonniewanathan.sudoku;

/**
 * Created by jonathan on 27/02/2017.
 */
public class UtilityTest{
    public static void main(String[] args) {
        int numOfColumns = 9;
        int numOfRows = 9;

        int[][] board = new int[numOfColumns][numOfRows];
        int[][] playboard;
        playboard = board;


        SudokuUtility.fillSudokuBoard(board);

        SudokuUtility.print2DArray(board);

        //SudokuUtility.removeNumbers3X3(6,9,0,3,playboard);
        SudokuUtility.removeNumbersPlayboard(playboard);
        System.out.println();
        SudokuUtility.print2DArray(playboard);
    }
}
