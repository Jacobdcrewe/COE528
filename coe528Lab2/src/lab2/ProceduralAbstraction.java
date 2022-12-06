package lab2;

/**
 *
 * @author Jacob
 */
public class ProceduralAbstraction {
    //Requires: None
    //Modifies: None 
    //Effects: Returns the smallest positive integer n for which n!  
    //         (i.e. 1*2*3*...*n) is greater than or equal to x, for positive  
    //         integer x. Otherwise returns 1.  

    public static int reverseFactorial(int x) {
        double output = x;
        if (x >= 0) {
            for (int i = 1; i < (output / i); i++) {
                output = output / i;
            }
            return (int) Math.ceil(output);
        } else {
            return 1;
        }
    }
    //Requires: None
    //Modifies: None
    //Effects: If the matrix arr satisfies Nice property, prints the sum and 
    //         returns true. Otherwise returns false.  

    public static boolean isMatrixNice(int[][] arr) {
        int[] sum = null;
        boolean nice = false;
        outerloop:
        for (int[] arr1 : arr) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1.length != arr.length) {
                    nice = false;
                    break outerloop;
                } else {
                    nice = true;
                }
            }
        }

        if (nice) {
            sum = new int[arr.length * 2 + 2]; //the number of combos is the sum of m and n in an m x n matrix plus the 2 diagonals

            //giving values for the sum of the rows
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    sum[i] = sum[i] + arr[i][j];
                }
            }

            //giving values for the sum of the columns
            for (int i = 0; i < arr[0].length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    sum[i + arr.length] = sum[i + arr.length] + arr[j][i];
                }
            }

            //giving values for the sum of the first diagonal
            for (int i = 0; i < arr.length; i++) {
                sum[2 * arr.length] = sum[2 * arr.length] + arr[i][i];
            }

            //giving values for the sum of the second diagonal
            for (int i = 0; i < arr.length; i++) {
                sum[2 * arr.length + 1] = sum[2 * arr.length + 1] + arr[i][arr.length - i - 1];
            }

            //testing if the sums are all the same
            for (int i = 0; i < sum.length - 1; i++) {
                if (sum[i] == sum[i + 1] && i + 1 < sum.length) {
                    nice = true;
                } else if (sum[i] != sum[i + 1] && i + 1 < sum.length) {
                    nice = false;
                    break;
                }
            }

        }
        if (nice) {
            System.out.println("The sums of each row, of each column, and of each diagonal all are " + sum[0]);
        }
        return nice;
    }

    public static void main(String[] args) {
        int fact = 24, notFact = 119;
        System.out.println("The factorial value for which " + fact + " is less than or equal to n! is where n=" + reverseFactorial(fact));
        System.out.println("The factorial value for which " + notFact + " is less than or equal to n! is where n=" + reverseFactorial(notFact)+"\n");
        int[][] nice = {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};
        System.out.println("Is this a nice matrix? " +isMatrixNice(nice) + "\n");
        
        int[][] notNice = {{-3, 1, 0},
                           {4, -3, 4},
                           {7, -9, 0}};
        System.out.println("Is this a nice matrix? " +isMatrixNice(notNice));
    }
}
