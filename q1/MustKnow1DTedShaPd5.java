public class MustKnow1DTedShaPd5 {
    public static void main (String [] args)
   {
      // when you test your program, modify a accordingly or create
      // other integer arrays for testing purposes
      int [] a = {1,2,3,4,5}; 
      int [] b = {5, 4, 3, 2, 1};
      
      print (a);
      System.out.println();
      print (b);
      System.out.println();
      
      System.out.println(inIncreasingOrder(b));
      System.out.println(inIncreasingOrder(a));
      System.out.println(inDecreasingOrder(b));
      System.out.println(inDecreasingOrder(a));
   
   } // main
   
   // precondition: a.length >= 1 and a is an integer array
   // postcondition: return true if elements in a are in ascending order; otherwise, returns false
   public static boolean inIncreasingOrder (int [] a)
   {
        for (int i = 0; i < a.length - 1; i++)
        {
             if (a[i] > a[i + 1])
             {
                return false;
             }
        }
        return true;
     
   }  // inIncreasingOrder
   
   
   // precondition: a.length >= 1 and a is an integer array
   // postcondition: return true if elements in a are in descending order; otherwise, returns false
   public static boolean inDecreasingOrder (int [] a)
   {
        for (int i = 0; i < a.length - 1; i++) 
        {
            if (a[i] < a[i + 1])
            {
                return false;
            }
        }
        return true;
      
   } // inDecreasingOrder
   
   // precondition: a has been properly initialized and is an integer array
   // postcondition: print out the elements of a and insert a space between adjacent elements
   public static void print (int [] a)
   {
        for (int i : a) System.out.print(i + " ");
        
   } // print
}

/**********************
 * Output:
 * 1 2 3 4 5
 * 5 4 3 2 1
 * false
 * true
 * true
 * false
 *************************/