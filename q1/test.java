

public class test {
    
    public static void main(String[] args) {               
        
        int [] a = {1,2,3,4,5,6,4,8,9,10};
        System.out.print(toString(before4(a)));
        

        // Create the following pattern:
        // *
        // **
        // ***
        // ****
        // *****

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Create the following pattern:
        // *****
        // ****
        // ***
        // **
        // *

        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        int[] a1 = {1,2,3,4,5,6,4,8,9,10};
         System.out.print(1 % 2);
    }

    

    // Write a method when given a array of ints, returns smallest value
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

    // Write a method when given a array of ints, swap the first and last elements and return the array.
    public static int[] swapFirstLast(int [] a)
    {
       int temp = a[0];
       a[0] = a[a.length - 1];
       a[a.length - 1] = temp;
       return a;
    }

    // Write a method that accepts an array of ints and return a new array with two consecutive copies of each value
    public static int[] doubleEach(int [] a)
    {
       int [] b = new int[a.length * 2];
       for (int i = 0; i < a.length; i++)
       {
          b[2 * i] = a[i];
          b[2 * i + 1] = a[i];
       }
       return b;
    }

    // Write a toString method for array of ints;
    public static String toString(int [] a)
    {
       String s = "[";
       for (int i = 0; i < a.length; i++)
       {
          s += a[i];
          if (i < a.length - 1)
          {
             s += ", ";
          }
       }
       s += "]";
       return s;
    }

    // Write a method that accepts an array of ints and returns a new array containing the element from the original array that come before the element with the value 4.
    public static int[] before4(int [] a)
    {
       int [] b = new int[a.length];
       int count = 0;
       for (int i = 0; i < a.length; i++)
       {
          if (a[i] < 4)
          {
             b[count] = a[i];
             count++;
          }
       }
         int [] c = new int[count];
            for (int i = 0; i < count; i++)
            {
                c[i] = b[i];
            }
         return c;
         
    }

    


      

    


    

    

    





}












