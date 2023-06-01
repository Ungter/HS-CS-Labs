/*********************************************************************************
NAME: Ted Sha   
PERIOD: 6
DATE SUBMITTED: 11/22/22
DUE DATE: 10/21/22
ASSIGNMENT: Maze Lab

PURPOSE OF THE LAB:
    - Find a viable path from START to EXIT

MISTAKES MADE:
    - Trying to check for WALL without using boundary checking,
      led to out of bound exceptions.
    - Not throwing the FileNotFound exception. 
    - Using the wrong symbols and getting stuck on a Overflow exception

NEW CONCEPTS LEARNED:
    - back tracking to undo the wrong path
HOW I FEEL ABOUT THIS LAB: 
    - In the Maze Lab Description File, under the "Help with Marking the Path
      in MazeMaster" section, it was suggested that to use a temporary marker 'o'
      to mark the path with the same properties in the AreaFill lab. However I feel
      like this was just an extra step, so instead I first marked the path with STEP,
      then when backtracking, any marker that wasn't associated with the correct path 
      was changed back to the original DOT. This way there wasn't extra steps and the
      outcome was still the same.

CREDITS: 

STUDENTS WHOM I HELPED: 
*/

import java.util.*;
import java.io.*;
public class MazeDriver_TedShaPd6
{
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      char[][] retArr;
      Maze m ;
   
      System.out.println();
      
      for (;;)
      {
         System.out.print("\nEnter the maze's filename (file extension not needed): ");
         retArr = buildCharArr(sc.next());
         m = new Maze(retArr);
         System.out.println ("Maze: ");
         m.display();
         
         System.out.println("\nWhat do you want to do (choose 1, 2, or 3):");
         System.out.println("   1: Mark only the correct path.");
         System.out.println("   2: Mark only the correct path, and display the count of STEPs.");
         System.out.println("   3: Exit");
      
         m.solve(sc.nextInt());
         
         m.display();  
         
      } 
   } // main
   
   // postcondition: take in a filename (without .txt), and return a char[][]
   public static char[][] buildCharArr(String fileName) throws FileNotFoundException
   {
    Scanner in = new Scanner(new File(fileName + ".txt"));
    char[][] arr = new char[in.nextInt()][in.nextInt()];
    for(int i = 0; i < arr.length; i++) {
        String row = in.next();
        arr[i] = row.toCharArray();
     }
    return arr;
   }  // buildCharArr
   
   private static void transfer2DGridToFile (char [][] m) throws FileNotFoundException
   {
      System.out.print ("Enter the name of the output file? \nUse 'txt' as the file extension: ");
      Scanner input = new Scanner (System.in);
      String name = input.next();
      File outputFile = new File (name);
      if (outputFile.exists())
      {
         System.out.println (name + "already exists");
         System.exit(1);
      }
      
      PrintWriter pw = new PrintWriter (outputFile);  
      
      System.out.println ("Enter the dimensions (row and column) of the random maze (separated the numbers with a space): ");
      pw.println(input.next() + " " + input.next());
   
      // transfer the 2D grid to the .txt text file
      for (char [] row : m)
      {
         pw.println (row);
      } 
      pw.close();
   
   } // transfer2DGridToFile

}  // MazeDriver


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //fields
   private char[][] maze;
   private int startRow, startCol;  // store where the start location is
   private boolean S_Exists=false, E_Exists=false;
   
   /** Initializes all the field of a Maze object: maze, startRow, startCol
   */
   public Maze(char[][] inCharArr)    
   {
      maze = inCharArr ;           
      for (int i = 0; i < maze.length; i++) {
         for (int j = 0; j < maze[i].length; j++) {
            if (maze[i][j] == START) {
               startRow = i ;               
               startCol = j ;               
               S_Exists = true;               
            }               
            if (maze[i][j] == EXIT) {                
               E_Exists = true;               
            }               
         }               
      }               
   }  // Maze
   
   /**
   */
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }  // display
   
   
   /**
   */
   public void solve(int n)
   {
      final int FIND_PATH = 1;
      final int FIND_PATH_AND_COUNT_PATH_LENGTH = 2;
      final int QUIT = 3;
   
      if(n == FIND_PATH)
      {
         if (!markTheCorrectPath(startRow, startCol))
            System.out.println ("No Path found!");
      }
      else if(n == FIND_PATH_AND_COUNT_PATH_LENGTH)
      {
         if (!markCorrectPathAndCountStars(startRow, startCol, 0))
            System.out.println ("No Path found!");
      
      }
      else if (n == QUIT)
      {
         System.out.println("Goodbye!\n"); 
         System.exit (0);
      }
        
      else System.out.println("Invalid choice\n");
   }  // solve
   
    /**
    * pre: the maze has been initialized and the start location has been set.
    * post: Creates a path from the start location to the exit location, or returns false if no path exists.
    * 
    * @param r
    * @param c
    *
    **/
   private boolean markTheCorrectPath(int r, int c)
   {
      // All the possible false reactions
      if (r < 0 || r >= maze.length || 
          c < 0 || c >= maze[0].length || 
          maze[r][c] == STEP || maze[r][c] == WALL) {
        return false;
      } else if (maze[r][c] == EXIT) { // The part that the recursives check if there was a path or not.
            return true;
      } else { // if the current location is not a wall, step, or exit, mark it as a step and try to find a path there.
            if (maze[r][c] != START) { // So START doesn't get replaced as well 
                maze[r][c] = STEP;
            }
            if (markTheCorrectPath(r+1, c) ||
                markTheCorrectPath(r-1, c) || 
                markTheCorrectPath(r, c+1) || 
                markTheCorrectPath(r, c-1)) { // if any of the above calls return true, then we have found a path.
                    return true;
            } else { // Backtracks and makes the non-correct paths DOT again
                if (maze[r][c] != START) {
                    maze[r][c] = DOT;
                }
               
                return false;
            }
      }
   } // markTheCorrectPath

   
   /**
     precondition: the maze has been initialized and the start location has been set.
     postcondition: Creates a path from the start location to the exit location, or returns false if no path exists and prints the number of steps in the path.
    */
   private boolean markCorrectPathAndCountStars(int r, int c, int count)
   {
            if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == STEP || maze[r][c] == WALL) {
                return false;
              } else if (maze[r][c] == EXIT) {
                System.out.println("Number of steps: " + count + " ");
                return true;
              } else {
                if (maze[r][c] != START) { 
                    count++;
                    maze[r][c] = STEP;
                    }
                if (markCorrectPathAndCountStars(r+1, c, count) ||
                    markCorrectPathAndCountStars(r-1, c, count) || 
                    markCorrectPathAndCountStars(r, c+1, count) || 
                    markCorrectPathAndCountStars(r, c-1, count)) {
                  return true;
                } else {
                    if (maze[r][c] != START) {
                        count--;
                        maze[r][c] = DOT;
                    }
                  return false;
                }
              }
   } // markCorrectPathAndCountStars
}

/*
 * Some test runs:
 * 
 * 
Enter the maze's filename (file extension not needed): maze1
Maze: 
WWWWWWWW
W....W.W
WW.WW..W
W....W.W
W.W.WW.E
S.W.WW.W
WW.....W
WWWWWWWW


What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1
WWWWWWWW
W....W.W
WW.WW..W
W***.W.W
W*W*WW*E
S*W*WW*W
WW.****W
WWWWWWWW


Enter the maze's filename (file extension not needed): maze2
Maze: 
WWWSWWWWWW
W....W.W.W
WWWW.....W
W...W.WW.W
W.W....W.W
WEWWWWWWWW


What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2
Number of steps: 12 
WWWSWWWWWW
W..**W.W.W
WWWW**...W
W***W*WW.W
W*W***.W.W
WEWWWWWWWW


Enter the maze's filename (file extension not needed): maze6NoPath
Maze: 
WWWWW
W...W
W.W.W
S.WWE
WWWWW


What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2
No Path found!
WWWWW
W...W
W.W.W
S.WWE
WWWWW
 * 
 * 
 * 
 * 
 */