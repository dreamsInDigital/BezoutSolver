/**
 * Small Java Program used to solve Micorsoft BlueHat Reversing Challenge Level 3. Spoilers ahead! 
 * A solver for Bezout equations, using the Extended Euclidian Algorithm.
 * 
 * @author Jenn N.
 * */
public class Solver
{  
  /**
   * Main - Calls with our two constants from the eq.( x * 2813506931L - y * 3237618779L == 1L )
   * */
  public static void main( String args[] ) {
    bezout( 2813506931L, 3237618779L );    
  }
 
  /**
   * Initializes two arrays containing the base rows to be passed to the recursive reduction method.
   * Eg.   1*2813506931L - 0*3237618779L = 2813506931L
   *       0*2813506931L - 1*3237618779L = 3237618779L
   * @param a   The value to be multiplied with our found x value (2813506931L in this case)
   * @param b   The value to be multiplied with our found x value (3237618779L in this case)
   * */
  public static void bezout( Long a, Long b )  {
    Long[] row1 = { 1L, 0L, a };
    Long[] row2 = { 0L , 1L, b };
    bezout( row1, row2, a, b );
  }
 
  /**
   * Recursive method for reducing our bezout equation and determining the values for x and y.
   * @param row1   A row of the form x * 2813506931L - y * 3237618779L = result (Array { x, y, result } )
   * @param row2   Furthest reduced row of the above form, also an array containing { x, y, result } for this row
   * @param num1   The value to be multiplied with our found x value (2813506931L in this case)
   * @param num2   The value to be multiplied with our found x value (3237618779L in this case)
   * */
  public static int bezout( Long[] row1, Long[] row2, Long num1, Long num2 )  {
    Long[] rowNew = new Long[3];
    // Determines result based on last row calculated (looking for a value of 1)
    Long result = row2[0]*num1 + row2[1]*num2;
   
    // BASE CASE - If found, prints results in both decimal values of x and y, and the formatted hex serial #
    if ( result == 1L )  {
      System.out.println( "x = " + row2[0] + ", y = " + row2[1]*(-1L) );
      System.out.println( Long.toHexString(row2[0]).toUpperCase()+":"+Long.toHexString(row2[1]*(-1L)).toUpperCase() );
      return 0;
    }
   
    // First find factor to multiply row by
    Long factor = 0L;
    while ( (row1[2] - factor*row2[2]) > row2[2] ) {
      factor = factor + 1; 
    }

    // Row operation
    for ( int i = 0; i < rowNew.length; i++ ) {
      rowNew[i] = row1[i] - row2[i]*( factor );
    }
   
    // Recursive call
    bezout( row2, rowNew, num1, num2 );
    return 0;
  }
}
