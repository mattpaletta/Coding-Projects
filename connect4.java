import java.util.*;
import java.io.*;
import java.lang.*;

public class connect4 {
    int maxdepth = 6;
    int nummoves = 0;
    
    boolean end = false;
    
    int Infinity = 999999999;
    
    public void connect4() {}
    
    
    public static void main(String[] args) {
        
        int[][] arr = new int[7][6];
        
        int col = 0;
        
        Scanner input = new Scanner(System.in);
        
        connect4 L = new connect4();
        while(!L.end) {
            L.printGrid(arr);
            
            /*
            System.out.print("Enter Next Column: ");
            col = input.nextInt();
            System.out.println();
            
            */
            arr = L.makemove(col, arr);
        
        }
        L.printGrid(arr);
        /*
        arr[3][L.insert(3, arr)] = 2;
        arr[3][L.insert(3, arr)] = 2;
        arr[3][L.insert(3, arr)] = 2;
        arr[3][L.insert(3, arr)] = 2;
        L.printGrid(arr);
        System.out.println("Total: "+L.countUp(arr, 2));
        System.out.println("Won: "+L.score(arr, 0, 1));
        */
    }
    
    public void printGrid(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("--");
        }
        System.out.println();
        
        for (int i = a[0].length-1; i >= 0; i--) {
            for (int j = 0; j < a.length; j++) {
                if (a[j][i] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(a[j][i] + " ");
                }
                
            }
            System.out.println();
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print("--");
        }
        System.out.println();
        
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.print(i+ " ");
        }
        System.out.println();
    }
    
    public int[][] makemove(int col, int[][] arr) {
        
        col = move(arr,1);
        
        for (int i = 0; i < arr[0].length; i++) {
            if (arr[col][i] == 0) {
                arr[col][i] = 1; // player
                break;
            }
        }
        
        
        
        
        if (won(1, arr) || won(2, arr) || gameover(arr)) {
            endgame(arr);
            end = true;
            return arr;
        }
        
        
        // Computer Turn
        int result = move(arr,2);
        System.out.println("Num Moves: " + nummoves);
        System.out.println("Depth: " + maxdepth);
        
        for (int i = 0; i < arr[0].length; i++) {
            if (arr[result][i] == 0) {
                arr[result][i] = 2; // computer
                break;
            }
        }
        
        
        
        if (won(1, arr) || won(2, arr) || gameover(arr)) {
            endgame(arr);
            end = true;
            return arr;
        }
        return arr;
    }
    
    public int move(int[][] a, int player) {
        
        int depth = 0;
        nummoves = 0;
        int var = 0;
        
        int[] result = minmax(a, 0, player, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        //System.out.println("Num Moves: " + nummoves);
        //System.out.println("Depth: " + maxdepth);
        var = result[2];
        return var;
    }
    
    
    public int[] minmax(int[][] game, int depth, int player, int turn, int alpha, int beta) {
        
        int[][] moves = get_available_moves(game);
        
        int score;
        int bestcol = -1;
        int bestrow = -1;
        
        if (moves.length == 0 || depth >= maxdepth) {
            return new int[] {score(game, depth, player), bestrow, bestcol};
        } else {
            for (int i = 0; i < moves.length; i++) {
                nummoves++;
                // try this move
                int row = moves[i][0];
                int col = moves[i][1];
                game[col][insert(col, game)] = player;
                
                if (player == turn) { //COMPUTER
                    
                    score = minmax(game, depth+1, (turn+1)%2+1, turn, alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestrow = row;
                        bestcol = col;
                    }
                } else { // PLAYER
                    score = minmax(game, depth+1, (turn+1)%2+1, turn, alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestrow = row;
                        bestcol = col;
                    }
                    
                }
                // undo the move
                game[col][remove(col, game)] = 0;
                if (alpha >= beta) break;
            }
        }
        
        return new int[] {(player == turn) ? alpha : beta, bestrow, bestcol};
    }
    
    
    public int remove(int col, int[][] a) {
        for (int i = 0; i < a[0].length-1; i++) {
            if (a[col][i+1] == 0) {
                return i;
            }
        }
        
        return a[0].length-1;
    }
    
    public int insert(int col, int[][] a) {
        for (int i = 0; i < a[0].length; i++) {
            if (a[col][i] == 0) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int[][] get_available_moves(int[][] a) {
        int free = 0;
        for (int col = 0; col < a.length; col++) {
            if (a[col][a[0].length-1] == 0) {
                free++;
            }
        }
        
        int[][] moves = new int[free][2];

        int i = 0;
        
        for (int col = 0; col < a.length; col++) {
            int row = insert(col, a);
            if (row == -1) {
                continue; // that row is full
            }
            moves[i++] = new int[] {row, col};
        }
        return moves;
    }
    
    public void endgame(int[][] a) {
        if (won(1, a)) {
            System.out.println("Player 1 Won!");
        } else if (won(2, a)) {
            System.out.println("The Computer Won!");
        } else {
            System.out.println("The Game Ended In A Draw");
        }
    }
    
    public boolean gameover(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i][a[0].length-1] == 0) { // Only check top columns
                return false;
            }
        }
        return true;
    }
    
    public boolean won(int val, int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    continue;
                }
                if (up(val,i,j,a) || right(val,i,j,a) || rightup(val,i,j,a) || leftup(val,i,j,a)) {
                    return true;
                }
            }
        }
        return false;
    
    }
    
    public boolean up(int val, int i, int j, int[][] a) {
        boolean result = true;
        
        for (int m = 0; m < 4; m++) {
            if ((i-m) < 0 || j < 0 || (i-m) > a.length-1 || j > a[0].length-1) {
                return false;
            }
            if (a[i-m][j] == 0) {
                result = false;
            }
            result = result && (a[i-m][j] == val);
        }
        return result;
    
    }
    public boolean right(int val, int i, int j, int[][] a) {
        boolean result = true;
        
        for (int m = 0; m < 4; m++) {
            if (i < 0 || (j+m) < 0 || i > a.length-1 || (j+m) > a[0].length-1) {
                return false;
            }
            if (a[i][j+m] == 0) {
                result = false;
            }
            result = result && (a[i][j+m] == val);
        }
        
        return result;
    
    }
    public boolean rightup(int val, int i, int j, int[][] a) {
        boolean result = true;
        for (int m = 0; m < 4; m++) {
            if ((i+m) < 0 || (j+m) < 0 || (i+m) > a.length-1 || (j+m) > a[0].length-1) {
                return false;
            }
            
            if (a[i+m][j+m] == 0) {
                result = false;
            }
            result = result && (a[i+m][j+m] == val);
        }
        return result;
    
    }
    public boolean leftup(int val, int i, int j, int[][] a) {
        boolean result = true;
        for (int m = 0; m < 4; m++) {
            if ((i-m) < 0 || (j+m) < 0 || (i-m) > a[0].length-1 || (j+m) > a[0].length-1) {
                return false;
            }
            if (a[i-m][j+m] == 0) {
                result = false;
            }
            result = result && (a[i-m][j+m] == val);
        }
        return result;
    
    }
    
    public int score (int[][] a, int depth, int player) {
        if (won(player, a)) { // Computer Won
            return 1000-depth;
        } else if (won((player+1)%2+1, a)) { // Player Won
            return depth-1000;
        } else { // Ended in Draw
            int me = count(a, player);
            int other = count(a, (player+1)%2+1);
            
            if (other >= me) {
                return depth-1000+other;
            } else {
                return 1000+me-depth;
            }
        }
    }
    
    public int count(int[][] a, int player) {
        return countUp(a, player)+countRight(a, player)+countRightUp(a, player)+countLeftUp(a, player);
    }
    
    public int countUp(int[][] a, int player) {
        int count = 0;
        for (int i = 0; i < a.length; i++) { // rows
            for (int j = 0; j < a[0].length-4; j+=4) { // cols
                
                int skips = 0;
                for (int m = 0; m < 4; m++) {
                    
                    if (a[i][j+m] != player && a[i][j+m] == 0) {
                        skips++;
                    }
                }
                if (skips <= 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int countRight(int[][] a, int player) {
        int count = 0;
        for (int i = 0; i < a[0].length; i++) { // cols
            for (int j = 0; j < a.length-4; j+=4) { // row
                int skips = 0;
                for (int m = 0; m < 4; m++) {
                    if (a[j+m][i] != player && a[j+m][i] == 0) {
                        skips++;
                    }
                }
                if (skips <= 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int countRightUp(int[][] a, int player) {
        int count = 0;
        for (int i = 0; i < a.length-4; i+=4) { // rows
            for (int j = 0; j < a[0].length-4; j++) { // cols
                int skips = 0;
                for (int m = 0; m < 4; m++) {
                    if (a[i+m][j+m] != player && a[i+m][j+m] == 0) {
                        skips++;
                    }
                }
                if (skips <= 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int countLeftUp(int[][] a, int player) {
        int count = 0;
        for (int i = 4; i < a.length; i+=4) { // rows
            for (int j = 0; j < a[0].length-4; j++) { // cols
                
                int skips = 0;
                for (int m = 0; m < 4; m++) {
                    if (a[i-m][j+m] != player && a[i-m][j+m] == 0) {
                        skips++;
                    }
                }
                if (skips <= 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    
}