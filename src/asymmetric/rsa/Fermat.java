package asymmetric.rsa;

import java.math.*; 
import java.util.*; 
  
class Fermat { 
  
    // Function to find the Floor 
    // of square root of a number 
    public static BigInteger sqrtF(BigInteger x) 
        throws IllegalArgumentException 
    { 
        // if x is less than 0 
        if (x.compareTo(BigInteger.ZERO) < 0) { 
            throw new IllegalArgumentException( 
                "Negative argument."); 
        } 
  
        // if x==0 or x==1 
        if (x.equals(BigInteger.ZERO) 
            || x.equals(BigInteger.ONE)) { 
            return x; 
        } 
  
        BigInteger two 
            = BigInteger.valueOf(2L); 
        BigInteger y; 
  
        // run a loop 
        y = x.divide(two); 
        while (y.compareTo(x.divide(y)) > 0) 
            y = ((x.divide(y)).add(y)) 
                    .divide(two); 
        return y; 
    } 
  
    // function to find the Ceil 
    // of square root of a number 
    public static BigInteger sqrtC(BigInteger x) 
        throws IllegalArgumentException 
    { 
        BigInteger y = sqrtF(x); 
  
        if (x.compareTo(y.multiply(y)) == 0) { 
            return y; 
        } 
  
        else { 
            return y.add(BigInteger.ONE); 
        } 
    } 
  
    // Fermat factorisation 
    static String FermatFactors(BigInteger n) 
    { 
        // constants 
        BigInteger ONE = new BigInteger("1"); 
        BigInteger ZERO = new BigInteger("0"); 
        BigInteger TWO = new BigInteger("2"); 
  
        // if n%2 ==0 then return the factors 
        if (n.mod(TWO).equals(ZERO)) { 
            return n.divide(TWO) 
                       .toString() 
                + ", 2"; 
        } 
  
        // find the square root 
        BigInteger a = sqrtC(n); 
  
        // if the number is a perfect square 
        if (a.multiply(a).equals(n)) { 
            return a.toString() 
                + ", " + a.toString(); 
        } 
  
        // else perform factorisation 
        BigInteger b; 
        while (true) { 
            BigInteger b1 = a.multiply(a) 
                                .subtract(n); 
            b = sqrtF(b1); 
  
            if (b.multiply(b).equals(b1)) 
                break; 
            else
                a = a.add(ONE); 
        } 
  
        return a.subtract(b).toString() 
            + ", " + a.add(b).toString(); 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
        String N = "20124077"; 
  
        System.out.println( 
            FermatFactors( 
                new BigInteger(N))); 
    } 
} 