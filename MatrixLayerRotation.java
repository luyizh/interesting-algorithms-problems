/*You are given a 2D matrix, a, of dimension MxN and a positive integer R. 
You have to rotate the matrix R times and print the resultant matrix. 
Rotation should be in anti-clockwise direction.

Note that in one rotation, you have to shift elements by one step only.
It is guaranteed that the minimum of M and N will be even.

Input Format 
First line contains three space separated integers, M, N and R, 
where M is the number of rows, N is number of columns in matrix, 
and R is the number of times the matrix has to be rotated. 
Then M lines follow, where each line contains N space separated positive integers. 
These M lines represent the matrix.

Constraints 
2 <= M, N <= 300 
1 <= R <= 109 
min(M, N) % 2 == 0 
1 <= a_{ij} <= 10^8, where i ∈ [1..M] & j ∈ [1..N]

Output Format 
Print the rotated matrix.*/

import java.io.*;
import java.util.*;

public class MatrixLayerRotation {
    
    public static String[] leftRotation(String[] a, int d) {
        String[] rotatedArr = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            rotatedArr[(i-d + a.length) % a.length] = a[i];
        }
        return rotatedArr;
    }
    
    public static String[][] loop(String[][] M, int numRots) {
        int numRows = M.length;
        int numCols = M[0].length;
        
        int minDim = Math.min(numRows, numCols);
        
        // generate blank matrix
        String[][] R = new String[numRows][numCols];
        
        // split array into k concentric rectangular loops
        for (int k = 0; k < minDim/2; k++) {
        
            // hold loop's contents in counterclockwise order, 
            // starting from top left corner, in arraylist
            List<String> loop = new ArrayList<String>();

            // down 
            for (int r = 0+k; r <= numRows-2-k; r++) {
                loop.add(M[r][0+k]);
            }
            // right
            for (int c = 0+k; c <= numCols-2-k; c++) {
                loop.add(M[numRows-1-k][c]);
            }         
            // up
            for (int r = numRows-1-k; r >= 1+k; r--) {
                loop.add(M[r][numCols-1-k]);
            }
            // left
            for (int c = numCols-1-k; c >= 1+k; c--) {
                loop.add(M[0+k][c]);
            }
            
            // turn arraylist into array
            String[] loopVals = new String[loop.size()];
            for (int j = 0; j < loop.size(); j++) {
                loopVals[j] = loop.get(j);
            }
            
            // rotate array
            String[] loopValsRotated = new String[loop.size()];
            loopValsRotated = leftRotation(loopVals, numRots % loop.size());
            
            // populate blank matrix with rotated array's values
            // (fill that loop in the blank matrix)
            int count = 0;       

            // down 
            for (int r = 0+k; r <= numRows-2-k; r++) {
                R[r][0+k] = loopValsRotated[count];
                count++;
            }
            // right
            for (int c = 0+k; c <= numCols-2-k; c++) {
                R[numRows-1-k][c] = loopValsRotated[count];
                count++;
            }         
            // up
            for (int r = numRows-1-k; r >= 1+k; r--) {
                R[r][numCols-1-k] = loopValsRotated[count];
                count++;
            }
            // left
            for (int c = numCols-1-k; c >= 1+k; c--) {
                R[0+k][c] = loopValsRotated[count];
                count++;
            }
        }

        return R;
               
    }
    


    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String[] parameters = scan.nextLine().split(" ");
        int numRows = Integer.parseInt(parameters[0]);
        int numCols = Integer.parseInt(parameters[1]);
        int numRots = Integer.parseInt(parameters[2]);
                
        // generate matrix of strings
        String[][] matrix = new String[numRows][numCols];     
        for (int i = 0; i < numRows; i++) {
            matrix[i] = scan.nextLine().split(" ");
        }
        
        // generate rotated matrix
        String[][] R = loop(matrix, -1*numRots);
        // print rotated matrix
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(R[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}

