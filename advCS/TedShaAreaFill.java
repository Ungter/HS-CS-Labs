/***********************************************************************************************************************************************
 * Name: Ted Sha
 * Period: 06
 * Name of the Lab: Area Fill 
 * Purpose of the Program: To fill an area of 
 *                         a 2D array using recursion.
 * Due Date: 10/2/2022
 * Date Submitted: 10/5/2022
 * What I learned: 
 * 1. Read the directions more carefully
 * 2. For 1. I spent so much time trying to 
 *    figure out how to skip over chars
 *    in recursion but then realized I didn't
 *    have to..
 * How I feel about this lab: 
 * Looks hard but simple overall
 * What I wonder: If it is possible to skip over 
 *                elements in a 2D array during
 *                recursion.
 *
 * Student(s) who helped me (to what extent): 
 * Student(s) whom I helped (to what extent): 
 *************************************************************************************************************************************************/

   
import java.util.Scanner;
import java.io.*;
public class TedShaAreaFill
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      boolean cont = true;

      while (cont) {
         Scanner sc = new Scanner(System.in);
         System.out.print("Filename: ");
         String filename = sc.next();
         grid = read(filename + ".txt");
         display(grid);
         System.out.print("\nEnter ROW COL to fill from: ");
         int row = sc.nextInt();
         int col = sc.nextInt(); 
         System.out.print("What char to replace with:");
         char replacement = new Scanner(System.in).next().charAt(0); 
         fill(grid, row, col, replacement, grid[row][col]);
         display(grid);
         while (true) {
            System.out.print("Start over? [y/n]: ");
            char st = new Scanner(System.in).next().charAt(0);
            if (st == 'y') {
               break;
            } else if (st == 'n') {
               cont = false;
               break;
            } else {
               System.out.print("Pick again pls");
            }
         }
      }
   }

   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(filename));
      char[][] board = new char[sc.nextInt()][sc.nextInt()];
      for(int i = 0; i < board.length; i++) {
         String row = sc.next();
         board[i] = row.toCharArray();
      }
      return board;
   }
   
   public static void display(char[][] g)
   {              
      for(int i = 0; i < g.length; i++) {
         for(int j = 0; j < g[i].length; j++) {
            System.out.print(g[i][j]);
         }
         System.out.println();
      }
   }
   
          /**
        * pre: method called in main method
        * post: modifies char[][] g and replaces characters of the 
        * index indicated on the grid with a *
        * @param g
        * @param r
        * @param c
        * @param ch
        */

   public static void fill(char[][] g, int r, int c, char ch, char retain) //recursive method
   {       
        if (r < 0 || c < 0 || r >= g.length || c >= g[0].length) {
            return;
        } else if (retain == g[r][c]) {
         g[r][c] = ch;
         fill(g, r+1, c, ch, retain);
         fill(g, r-1, c, ch, retain);
         fill(g, r, c-1, ch, retain);
         fill(g, r, c+1, ch, retain);
         
        }
   }// fill
}

// Test runs
/*
 * Filename: area
xxxx............................
...xx...........................
..xxxxxxxxxxxx..................
..x.........xxxxxxx.............
..x...........0000xxxx..........
..xxxxxxxxxxxx0..000............
..xxxxxxxxx...0...00.....0000000
..........xx.......0000000000000
.....xxxxxxxxx........0.........
....xx.................00000....
....xx.....................00...
.....xxxxxxxxxxxxxxxxxx....00...
......................xx...00...
.......................xxxx00000
............................0000

Enter ROW COL to fill from: 14
20
What char to replace with:y
xxxx............................
yyyxx...........................
yyxxxxxxxxxxxx..................
yyx.........xxxxxxx.............
yyx...........0000xxxx..........
yyxxxxxxxxxxxx0..000............
yyxxxxxxxxx...0...00.....0000000
yyyyyyyyyyxx.......0000000000000
yyyyyxxxxxxxxx........0.........
yyyyxx.................00000....
yyyyxx.....................00...
yyyyyxxxxxxxxxxxxxxxxxx....00...
yyyyyyyyyyyyyyyyyyyyyyxx...00...
yyyyyyyyyyyyyyyyyyyyyyyxxxx00000
yyyyyyyyyyyyyyyyyyyyyyyyyyyy0000
Start over? [y/n]: y
Filename: area2
..........00
...0....0000
...000000000
0000.....000
............
..#########.
..#...#####.
......#####.
...00000....

Enter ROW COL to fill from: 7
7
What char to replace with:g
..........00
...0....0000
...000000000
0000.....000
............
..ggggggggg.
..g...ggggg.
......ggggg.
...00000....
Start over? [y/n]: y
Filename: area3
+++
@+@
@+@
@@@

Enter ROW COL to fill from: 1
2
What char to replace with:*
+++
*+*
*+*
***
Start over? [y/n]: n
 */


