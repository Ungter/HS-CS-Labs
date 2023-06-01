/****************************************************************************
 Name: Ted Sha
 Lab: Theater Seating
 Date submitted: 3/6/2022

 Purpose of the program: To assist the user to buy tickets for a theater.
 What I Learned (be as specific as possible): 
   - Using while loops to restart from the beginning of the loop if the user
     enters an invalid input without having to call the function again.

   - Flipping the 2d array backwards to print from a higher row to a lower row
     while maintaining the original layout.

   - + 1 and - 1 are used to make sure the user's input is within the range of
     the array, make the array seem like it starts from 1 instead of 0,
     and make sure the index isn't out of bounds.

 ****************************************************************************/

/**************************
 * Checklist:
 * Illusion that the maxtrix starts with 1: Done
 * No IndexOutOfBoundsException: Done
 * Program backtracks to the proper position of the loop if the user enters an invalid input:
 *     - When price doesn't exist: Done
 *     - When seating is not available/purchased: Done
 *     - When the user enters a negative number: Done
 * Program prints out the correct & updated maxtix: Done
 ***************************/
import java.util.Scanner;
public class TheaterSeatingTedShaPd5 
{
   public static void main(String[] args) 
   {
      final int YES = 0;        // purchase a ticket
      // define some constants here based on what we use in the code below
      final int BY_PRICE = 2;
      final int BY_SEAT = 1;

      TheaterSeatingChart chart = new TheaterSeatingChart();
      boolean notDonePurchasing = true;
      Scanner console = new Scanner (System.in);
      
      while (notDonePurchasing)
      {
         chart.printSeatingChart();
         System.out.print ("\nDo you want to purchase a ticket? \nEnter 0 for Yes, 1 for No :");
         
         int choice = console.nextInt();
         if (choice == YES)
         {
            System.out.print ("\nWould you like to choose a seat by number or by price? " +
                              "\nEnter 1 for \"Choose by number\", 2 for \"Choose by price\": ");
         
            int seatChoice = console.nextInt();
         
            if (seatChoice == BY_PRICE)
            {
               // your code goes here
               chart.pickPrice();
            }
            else if (seatChoice == BY_SEAT)
            {
               // your code goes here
               chart.pickSeat();
            }
         
         } // outer if
         else
         {
            System.out.println ("\nGoodbye!");
            notDonePurchasing = false;
         }
         
      } // while
   } // main
} // TheaterSeatDriver


class TheaterSeatingChart
{  
   private int[][] seats;
   private static final int ROWS = 9;
   private static final int COLS = 10;

  // precondition: none;
  // postcondition: Generates a theater seating chart with all seats available and prices;
   public TheaterSeatingChart()
   {
      seats = new int[][]{{30, 40, 50, 50, 50, 50, 50, 50, 40, 30},
                          {20, 30, 30, 40, 50, 50, 40, 30, 30, 20},
                          {20, 20, 30, 30, 40, 40, 30, 30, 20, 20},
                          {10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
                          {10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
                          {10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
                          {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                          {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                          {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}};
   }
    
   // precondition: Constructor has been called;
   // postcondition: Prints out the seating chart with prices and available seats backwards;
   public void printSeatingChart()
   {
      System.out.println("*************************************" +
                         "\nSeat#: 1  2  3  4  5  6  7  8  9  10 ");

      // Print out the seating chart backwards by rows
      // so that the rows can start with 1 without adding 
      // another row 
      for (int i = ROWS - 1; i >= 0; i--) {
         System.out.print("Row ");
         System.out.print((i + 1) + ": ");
         for (int j = 0; j < COLS; j++) {
            System.out.print(seats[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println("\n" + "LEGEND:" +
                         "\n" + "number represents ticket price" +
                         "\n" + "-1 indicates the seat has been sold" + 
                         "\n" + "*************************************");
   }  // printSeatingChart
   

   
   
   // precondition: Constructor has been called;
   // postcondition: Picks a seat by price and set it to -1;
   public void pickPrice()
   {  
      boolean selected = false;

      while (!selected) {
         System.out.println();
         System.out.print("Please input your preferred price: ");
         int pickedPrice = new Scanner(System.in).nextInt();
         System.out.println();

         if (hasPrice(pickedPrice)) {
            selectSeat(pickedPrice);
            selected = true;

         } else {
            System.out.println("I'm sorry. The theater does not offer any seats for $" +
                               pickedPrice + " Please choose a ticket price from $10-$50.");
         }
      }
      

   } // pickPrice
   
   // precondition: Constructor has been called;
   // postcondition: Picks a seat by number and set it to -1;
   public void pickSeat()
   {
      System.out.println();

      boolean havePickedSeat = false;

      while (!havePickedSeat) {
         System.out.print("Please input your preferred row:");
         int pickedRow = new Scanner(System.in).nextInt() - 1;
         System.out.print("Please input your preferred seat:");
         int pickedSeat = new Scanner(System.in).nextInt() - 1;
         System.out.println();
   
         
         if (isInArray(pickedRow, pickedSeat)) {
            
            if (seats[pickedRow][pickedSeat] != -1) {
               System.out.println("You have successfully purchased a ticket for $" +
                                seats[pickedRow][pickedSeat] + " in row " + (pickedRow + 1) +
                                " seat " + (pickedSeat + 1) + "\nThank you!\n");

               seats[pickedRow][pickedSeat] = -1;
               havePickedSeat = true;

            } else {
               System.out.println("I'm sorry, that seat has already been purchased.\n" +
                                  "Let us start over again.\n");
               havePickedSeat = true;
            }
   
         } else {
            System.out.println("You picked a wrong seat!\n");
         } 
      }
   } // pickSeat 

   // you might need to write some private methods for this class for
   // internal use only
   
   // Start of private methods that help with the pickPrice method

   // Precondition: The user has input a integer 
   //               value for the price they want to purchase
   // Postcondition: Returns true if the price is valid
   private boolean hasPrice(int price) {
      for (int i = ROWS - 1; i >= 0; i--) {
         for (int j = 0; j < COLS; j++) {
            if (seats[i][j] == price) {
               return true;
            }
         }
      }
      return false;
   }
   
   // Precondition: hasPrice(price) == true
   // Postcondition: Prints out the seats that have the price.
   private void availableSeats(int price) {
      for (int i = ROWS - 1; i >= 0; i--) {
         for (int j = 0; j < COLS; j++){
            if (seats[i][j] == price) {
               System.out.println("Row: " + (i + 1) + ", Seat: " + (j + 1));
            }
         }
      }
      System.out.println();
   }

   // Precondition: hasPrice(price) is true
   // Postcondition: Check if the seat is actually in the array
   //                to prevent out of bounds exception.
   private boolean isInArray(int pickedRow, int pickedSeat) {
      if (pickedRow <= -1 || pickedRow > ROWS - 1 || pickedSeat <= -1 || pickedSeat > COLS - 1) {
         return false;
      }
      return true;
   }

   // Precondition: hasPrice(price) is true, user has input a valid price
   // Postcondition: Sets the selected seat to -1 or start over.
   private void selectSeat(int pickedPrice) {

      // Declears a boolean so that the user can pick a seat again
      // if they selected a seat that isn't in the list
      // of available seats without having to start over at the beginning.
      boolean havePickedSeat = false;

      System.out.println("The following seats are available with $" +
                            pickedPrice + ": \n");

      availableSeats(pickedPrice);

      while (!havePickedSeat) {
         System.out.print("Please input your preferred row:");
         int pickedRow = new Scanner(System.in).nextInt() - 1;
         System.out.print("Please input your preferred seat:");
         int pickedSeat = new Scanner(System.in).nextInt() - 1;
         System.out.println();
   
         
         if (isInArray(pickedRow, pickedSeat)) {
            
            if (seats[pickedRow][pickedSeat] != -1 && seats[pickedRow][pickedSeat] == pickedPrice) {
               System.out.println("You have successfully purchased a ticket for $" +
                                pickedPrice + " in row " + (pickedRow + 1) +
                                " seat " + (pickedSeat + 1) + "\nThank you!\n");

               seats[pickedRow][pickedSeat] = -1;
               havePickedSeat = true;

            } else if (seats[pickedRow][pickedSeat] == -1) {
               System.out.println("I'm sorry, that seat has already been purchased.\n" +
                              "Let us start over again.\n");
               havePickedSeat = true;
            } else {
               System.out.println("You picked a wrong seat!\n");
            }
   
         } else {
            System.out.println("You picked a wrong seat!\n");
         } 
      }
      
   }
   // End of private methods that help with the pickPrice method

} // TheaterSeatingChart

// Program sample output(s):

/*
*************************************
Seat#: 1  2  3  4  5  6  7  8  9  10
Row 9: 10 10 10 10 10 10 10 10 10 10
Row 8: 10 10 10 10 10 10 10 10 10 10
Row 7: 10 10 10 10 10 10 10 10 10 10
Row 6: 10 10 20 20 20 20 20 20 10 10
Row 5: 10 10 20 20 20 20 20 20 10 10
Row 4: 10 10 20 20 20 20 20 20 10 10
Row 3: 20 20 30 30 40 40 30 30 20 20
Row 2: 20 30 30 40 50 50 40 30 30 20
Row 1: 30 40 50 50 50 50 50 50 40 30

LEGEND:
number represents ticket price
-1 indicates the seat has been sold
*************************************

Do you want to purchase a ticket?
Enter 0 for Yes, 1 for No :0

Would you like to choose a seat by number or by price?   
Enter 1 for "Choose by number", 2 for "Choose by price": 1

Please input your preferred row:2
Please input your preferred seat:3

You have successfully purchased a ticket for $30 in row 2 seat 3
Thank you!

*************************************
Seat#: 1  2  3  4  5  6  7  8  9  10
Row 9: 10 10 10 10 10 10 10 10 10 10
Row 8: 10 10 10 10 10 10 10 10 10 10
Row 7: 10 10 10 10 10 10 10 10 10 10
Row 6: 10 10 20 20 20 20 20 20 10 10
Row 5: 10 10 20 20 20 20 20 20 10 10
Row 4: 10 10 20 20 20 20 20 20 10 10
Row 3: 20 20 30 30 40 40 30 30 20 20
Row 2: 20 30 -1 40 50 50 40 30 30 20
Row 1: 30 40 50 50 50 50 50 50 40 30

LEGEND:
number represents ticket price
-1 indicates the seat has been sold
*************************************

Do you want to purchase a ticket?
Enter 0 for Yes, 1 for No :0

Would you like to choose a seat by number or by price?
Enter 1 for "Choose by number", 2 for "Choose by price": 2

Please input your preferred price: 5

I'm sorry. The theater does not offer any seats for $5 Please choose a ticket price from $10-$50.

Please input your preferred price: 60

I'm sorry. The theater does not offer any seats for $60 Please choose a ticket price from $10-$50.

Please input your preferred price: 30

The following seats are available with $30:

Row: 3, Seat: 3
Row: 3, Seat: 4
Row: 3, Seat: 7
Row: 3, Seat: 8
Row: 2, Seat: 2
Row: 2, Seat: 8
Row: 2, Seat: 9
Row: 1, Seat: 1
Row: 1, Seat: 10

Please input your preferred row:9
Please input your preferred seat:1

You picked a wrong seat!

Please input your preferred row:8
Please input your preferred seat:2

You picked a wrong seat!

Please input your preferred row:2
Please input your preferred seat:8

You have successfully purchased a ticket for $30 in row 2 seat 8
Thank you!

*************************************
Seat#: 1  2  3  4  5  6  7  8  9  10
Row 9: 10 10 10 10 10 10 10 10 10 10
Row 8: 10 10 10 10 10 10 10 10 10 10
Row 7: 10 10 10 10 10 10 10 10 10 10
Row 6: 10 10 20 20 20 20 20 20 10 10
Row 5: 10 10 20 20 20 20 20 20 10 10
Row 4: 10 10 20 20 20 20 20 20 10 10
Row 3: 20 20 30 30 40 40 30 30 20 20
Row 2: 20 30 -1 40 50 50 40 -1 30 20
Row 1: 30 40 50 50 50 50 50 50 40 30

LEGEND:
number represents ticket price
-1 indicates the seat has been sold
*************************************

Do you want to purchase a ticket?
Enter 0 for Yes, 1 for No :0

Would you like to choose a seat by number or by price?
Enter 1 for "Choose by number", 2 for "Choose by price": 1

Please input your preferred row:2
Please input your preferred seat:3

I'm sorry, that seat has already been purchased.
Let us start over again.

*************************************
Seat#: 1  2  3  4  5  6  7  8  9  10
Row 9: 10 10 10 10 10 10 10 10 10 10
Row 8: 10 10 10 10 10 10 10 10 10 10
Row 7: 10 10 10 10 10 10 10 10 10 10
Row 6: 10 10 20 20 20 20 20 20 10 10
Row 5: 10 10 20 20 20 20 20 20 10 10
Row 4: 10 10 20 20 20 20 20 20 10 10
Row 3: 20 20 30 30 40 40 30 30 20 20
Row 2: 20 30 -1 40 50 50 40 -1 30 20
Row 1: 30 40 50 50 50 50 50 50 40 30

LEGEND:
number represents ticket price
-1 indicates the seat has been sold
*************************************

Do you want to purchase a ticket?
Enter 0 for Yes, 1 for No :0

Would you like to choose a seat by number or by price?
Enter 1 for "Choose by number", 2 for "Choose by price": 1

Please input your preferred row:9
Please input your preferred seat:11

You picked a wrong seat!

Please input your preferred row:1
Please input your preferred seat:11

You picked a wrong seat!

Please input your preferred row:9
Please input your preferred seat:2

You have successfully purchased a ticket for $10 in row 9 seat 2
Thank you!

*************************************
Seat#: 1  2  3  4  5  6  7  8  9  10
Row 9: 10 -1 10 10 10 10 10 10 10 10
Row 8: 10 10 10 10 10 10 10 10 10 10
Row 7: 10 10 10 10 10 10 10 10 10 10
Row 6: 10 10 20 20 20 20 20 20 10 10
Row 5: 10 10 20 20 20 20 20 20 10 10
Row 4: 10 10 20 20 20 20 20 20 10 10
Row 3: 20 20 30 30 40 40 30 30 20 20
Row 2: 20 30 -1 40 50 50 40 -1 30 20
Row 1: 30 40 50 50 50 50 50 50 40 30

LEGEND:
number represents ticket price
-1 indicates the seat has been sold
*************************************

Do you want to purchase a ticket?
Enter 0 for Yes, 1 for No :1

Goodbye!
*/

