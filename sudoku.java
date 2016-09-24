/*
 * Sudoku Solver v0.1.0 (http://techguyification.com)
 * Copyright 2014-2015 Techguyification
 * MIT License
 *
 * TERMS OF USE - Sudoku Solver
 *
 * Open source under the BSD License.
 *
 * Copyright © 2015 Matthew Paletta
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list
 * of conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 *
 * Neither the name of the author nor the names of contributors may be used to endorse
 * or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.*;
import java.lang.*;

public class sudoku {
    public void sudoku() {
        // Constructor
    }
    
    public static void main(String args[]) {
        int[][] a = {
            {0,0,5, 3,0,0, 0,0,0},
            {8,0,0, 0,0,0, 0,2,0},
            {0,7,0, 0,1,0, 5,0,0},
    
            {4,0,0, 0,0,5, 3,0,0},
            {0,1,0, 0,7,0, 0,0,6},
            {0,0,3, 2,0,0, 0,8,0},
    
            {0,6,0, 5,0,0, 0,0,9},
            {0,0,4, 0,0,0, 0,3,0},
            {0,0,0, 0,0,9, 7,0,0}
        };
        
        sudoku S = new sudoku();
        
        S.sudokusolver(a);
        
        S.printPuzzle(a);
    }
    
    public boolean sudokusolver(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    continue;
                }
                for (int num = 1; num <= arr.length; num++) {
                    if (legalsquare(num, i, j, arr)) {
                        arr[i][j] = num;
                        if (sudokusolver(arr)) {
                            return true;
                        } else {
                            arr[i][j] = 0;
                        }
                    }
                }
                return false;
            }
        }
        
        return true;
    }
    
    
    public boolean legalsquare(int val, int col, int row, int[][] arr) {
        return checkSquare(val, col, row, arr) && checkRow(val, row, arr) && checkCol(val, col, arr);
    }
    
    public boolean checkSquare(int val, int cols, int rows, int[][] arr) {
        int col = (int) Math.floor(cols/3) * 3;
        int row = (int) Math.floor(rows/3) * 3;
        
        for (int i = col; i < (col +3); i++) {
            for (int j = row; j < (row+3); j++) {
                if (arr[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkRow(int val, int row, int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i][row] == val) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkCol(int val, int col, int[][] a) {
        for (int i = 0; i < a[0].length; i++) {
            if (a[col][i] == val) {
                return false;
            }
        }
        return true;
    }
    
    
    
    public void printPuzzle(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
                if (j % 3 == 2) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println();
            }
        }
    }
}