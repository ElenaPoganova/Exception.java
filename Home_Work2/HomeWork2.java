public class HomeWork2 {
    
    public static void main(String[] args) {
        try {
            int d = 2; 
            int[] intArray = {1, 2, 5, 12, 4, 1, 2, 4}; 
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
         } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
         }   
    }
}

