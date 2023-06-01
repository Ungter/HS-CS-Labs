/************************************************
* Qianhe Sha; Pd. 5
* Lab: Slope
* Purpose of the Program: To detect either if the line is vertical, horizontal, or display
*                         its slope based on the user's input of two points.
* 
* What I learned: 
*  #1: The .nextDouble() returns the user input as a double ex: if 
*      the user inputs 1, it returns 1.0
*  
*        
*
*  P.S: My monitor at home is kind of big and it displays about 
*          200 characters per line so I'll try to be careful about 
*          the length of each line.
*
*  Credits: My past JS experience ( for loop )
**************************************************/
import java.util.Scanner;

public class Pd5TedShaSlope {
    public static void main(String args[]) {

        // Execute the function 3 times
        for (int i = 0; i < 3; i++) {
            calcSlope();
        }
    }

    /* Takes points as inputs, calculate the slope and y-intercept,
    *  and prints out statements based on the calculations         */
    public static void calcSlope() {
        
        double x1, x2, y1, y2, slope, intercept;

        System.out.print("Enter your first point: \n");
        x1 = new Scanner(System.in).nextDouble();
        y1 = new Scanner(System.in).nextDouble();

        System.out.print("Enter your second point: \n");
        x2 = new Scanner(System.in).nextDouble();
        y2 = new Scanner(System.in).nextDouble();

        slope = (y2 - y1) / (x2 - x1); // Formula of the slope 

        // First three if/else if statements check for anomalies
        if (x1 == x2 && y1 == y2) {
            System.out.println("These points are the same! Invalid output\n" +
                             "No equation of line\n");
        } else if (x2 - x1 == 0) {
            System.out.println("Slope is undefined/vertical\n" + 
                             "Undefined/Vertical at: x = " + (int) x2 + '\n');
        } else if (slope == 0) {
            System.out.println("Slope is zero/horizontal\n" + 
                             "Horizontal at: y = " + y2 + '\n');
        } else {
            intercept = y2 - slope * x2; // Formula of the Y-intercept

            if (slope == 1) { // Disregard x's coefficient if it's equal to 1
                System.out.print("\nEquation of the line: y = x + " + 
                                 intercept + "\n\n");
            } else if (intercept < 0) { /* Replace the + sign with a - sign
                                        *  and turn the intercept into a pos. number*/
                System.out.print("\nEquation of the line: y = " + 
                                  slope + "x - " + intercept * -1 + "\n\n");
            } else if (intercept == 0) { // Don't print intercept if it's equal to 0
                System.out.print("\nEquation of the line: y = " + 
                                  slope + 'x' + "\n\n");
            } else {
                System.out.print("\nEquation of the line: y = " + 
                                 slope + "x + " + intercept + "\n\n");
            }
        }
    } // calcSlope
} // Class

// generate a random number between 80 and 140 inclusive

