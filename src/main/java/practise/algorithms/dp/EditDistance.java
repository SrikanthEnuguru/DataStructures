package practise.algorithms.dp;

/**
 * Given two strings str1 and str2 and below operations that can performed on str1.
 * Find minimum number of edits (operations) required to convert str1 into str2.
 * <p>
 * Insert
 * Remove
 * Replace
 * All of the above operations are of equal cost.
 * <p>
 * Examples:
 * <p>
 * Input:   str1 = "geek", str2 = "gesek"
 * Output:  1
 * We can convert str1 into str2 by inserting a 's'.
 * <p>
 * Input:   str1 = "cat", str2 = "cut"
 * Output:  1
 * We can convert str1 into str2 by replacing 'a' with 'u'.
 * <p>
 * Input:   str1 = "sunday", str2 = "saturday"
 * Output:  3
 * Last three and first characters are same.  We basically
 * need to convert "un" to "atur".  This can be done using
 * below three operations.
 * Replace 'n' with 'r', insert t, insert a
 * <p>
 * <p>
 * Logic:
 * <p>
 * If last characters of two strings are same, nothing much to do. Ignore last characters and get count for remaining strings. So we recur for lengths m-1 and n-1.
 * Else (If last characters are not same), we consider all operations on ‘str1’, consider all three operations on last character of first string, recursively compute minimum cost for all three operations and take minimum of three values.
 * Insert: Recur for m and n-1
 * Remove: Recur for m-1 and n
 * Replace: Recur for m-1 and n-1
 */

public class EditDistance {
    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";

        System.out.println("Recursive Approach: " + editDistanceRec(str1, str2));
        System.out.println("DP Approach: " + editDistanceDP(str1, str2));

        str1 = "geek";
        str2 = "gesek";
        System.out.println("Recursive Approach: " + editDistanceRec(str1, str2));
        System.out.println("DP Approach: " + editDistanceRec(str1, str2));
    }

    static int editDistanceRec(String s1, String s2) {
        return editDistanceAux(s1, s2, s1.length(), s2.length());
    }

    static int editDistanceAux(String s1, String s2, int m, int n) {

        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0) return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0) return m;

        //If last characters of two strings are same
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistanceAux(s1, s2, m - 1, n - 1);
        } else {
            int insertOp = editDistanceAux(s1, s2, m, n - 1);
            int deleteOp = editDistanceAux(s1, s2, m - 1, n);
            int updateOp = editDistanceAux(s1, s2, m - 1, n - 1);
            return 1 + Math.min(insertOp, Math.min(deleteOp, updateOp));
        }
    }

    // A Dynamic Programming based Java program to find minimum
    // number operations to convert str1 to str2

    static int editDistanceDP(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a table to store results of subproblems
        int temp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i == 0) {
                    temp[i][j] = j;
                }
                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j == 0) {
                    temp[i][j] = i;
                }
                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    temp[i][j] = temp[i - 1][j - 1];
                }
                // If the last character is different, consider all
                // possibilities and find the minimum
                else {
                    temp[i][j] = 1 + Math.min(temp[i][j - 1], Math.min(temp[i - 1][j], temp[i - 1][j - 1]));
                }
            }
        }
        return temp[m][n];
    }
}
