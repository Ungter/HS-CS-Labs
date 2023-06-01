  /*****************************************************************************************************************
NAME: Ted Sha     
PERIOD: 5
DUE DATE: 1/14/2022 

PURPOSE: To practice 1D arrays and methods.   

WHAT I LEARNED:    
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): 

****************************************************************************************************************/

public class OneDimLabTedShaPd5
{
   public static void main(String[] args)
   {
      int [] array = {15, -5, 25, 75, 9, -2, -80, -100, 99, 21, 23, 40, 45, 67,
                       100, 44, 28, 1, 3};
                                  
      System.out.println ("Max: " + findMax(array));
      System.out.println ("Min: " + findMin(array));
      System.out.println ("MaxIndex: " + findMaxIndex(array));
      System.out.println ("MinIndex: " + findMinIndex(array));
      System.out.println ("Search result: " + find (array, 3));
   
      System.out.print ("Positive array: " );
      printArray (posArray(array));
      System.out.println();
      System.out.print ("Negative array: " );
      printArray (negArray(array));
      System.out.println();
   				   
      System.out.print ("Even members array: " );
      printArray (evenMembers(array));  
      System.out.println();
      System.out.print ("Odd members array: " );
      printArray (oddMembers(array));
      System.out.println(); 			
          
      System.out.println("Sum : " + sum(array));
      System.out.println("Avg : " + average(array));
   
   }  // main

   // postcondition: find and return the max element in a
   public static  int findMax(int [] a)
   {
      int max = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (a[i] > max)
         {
            max = a[i];
         }
      }
      return max;
   }
   // postcondition: find and return the index of the max element in a
   public static int findMaxIndex(int [] a)
   {
      int max = a[0];
      int maxIndex = 0;
      for (int i = 1; i < a.length; i++)
      {
         if (a[i] > max)
         {
            max = a[i];
            maxIndex = i;
         }
      }
      return maxIndex;
   }

   // postcondition: find and return the min element in a
   public static int findMin(int [] a)
   {
      int min = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (a[i] < min)
         {
            min = a[i];
         }
      }
      return min;
   }
   // postcondition: find and return the index of the min element in a
   public static int findMinIndex(int [] a)
   {
      int min = a[0];
      int minIndex = 0;
      for (int i = 1; i < a.length; i++)
      {
         if (a[i] < min)
         {
            min = a[i];
            minIndex = i;
         }
      }
      return minIndex;
   }

   // postcondition: check to see if target can be found in a or not.  If yes, return
   //                true; otherwise, returns false.
   public static boolean find (int [] a, int target)
   {
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] == target)
         {
            return true;
         }
      }
      return false;
   } 
   
   

   // postcondition: put all the negative numbers in an array and return it
   // Hint: 
   // Step 1: Use a for loop to find out how many negative numbers are in parameter a
   // Step 2: Use that number to create an int array of that size
   // Step 3: Use a for loop to traverse a and whenever a negative number is found,
   //             store that number in the newly created negative array.
   public static int [] negArray(int [] a)
   {
      int negCount = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] < 0)
         {
            negCount++;
         }
      }
      int [] negArray = new int [negCount];
      int index = 0;
      for (int i : a)
      {
         if (i < 0)
         {
            negArray[index] = i;
            index++;
         }
      }
      return negArray;
   }

   // postcondition: put all the positive numbers in a in an array and return the array
   public static int [] posArray(int [] a)
   {
      int posCount = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] > 0)
         {
            posCount++;
         }
      }
      int [] posArray = new int [posCount];
      int index = 0;
      for (int i : a)
      {
         if (i > 0)
         {
            posArray[index] = i;
            index++;
         }
      }
      return posArray;

   }
	
   // postcondition: sum up all the numbers in a and return the sum
   public static int sum (int [] a)
   {
      int sum = 0;
      for (int i : a)
      {
         sum += i;
      }
      return sum;
   }
   // postcondition: find the average of all the numbers in a 
   public static double average(int [] a)
   {
      double sum = 0;
      for (int i : a)
      {
         sum += i;
      }
      return sum / a.length;
   }
	
   // postcondition: put all the even numbers in a in an array and return the array
   public static int [] evenMembers(int [] a)
   {
      int evenCount = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] % 2 == 0)
         {
            evenCount++;
         }
      }
      int [] evenArray = new int [evenCount];
      int index = 0;
      for (int i : a)
      {
         if (i % 2 == 0)
         {
            evenArray[index] = i;
            index++;
         }
      }
      return evenArray;	
   }
	
   // postcondition: put all the odd numbers in a in an array and return the array	 
   public static  int [] oddMembers(int [] a)
   {
      int oddCount = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] % 2 != 0)
         {
            oddCount++;
         }
      }
      int [] oddArray = new int [oddCount];
      int index = 0;
      for (int i : a)
      {
         if (i % 2 != 0)
         {
            oddArray[index] = i;
            index++;
         }
      }
      return oddArray;	
   }
	
   // postcondition: print all the elements in a onto the screen
   // Suggestion: practice using for-each loop
   public static void printArray (int [] a)
   {
      for (int i = 0; i < a.length; i++)
         System.out.print (a[i] + " ");  // don't use println
   }

}

