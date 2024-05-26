public class Matrix {
    static long[][] transposeMatrix(long[][] m) {
        long[][] temp = new long[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    static long[][] getSumOfMatrices(long[][] firstMatrix, long[][] secondMatrix) {
        long[][] result = new long[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++)
            for (int col = 0; col < result[row].length; col++)
                result[row][col] = firstMatrix[row][col] + secondMatrix[row][col];

        return result;
    }

    static long[][] subtractMatrices(long[][] firstMatrix, long[][] secondMatrix) {
        long[][] result = new long[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++)
            for (int col = 0; col < result[row].length; col++)
                result[row][col] = firstMatrix[row][col] - secondMatrix[row][col];

        return result;
    }

    public static long[][] multiplyMatrices(long[][] m1, long[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length
        long[][] mResult = new long[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {     // rows from m1
            for (int j = 0; j < mRColLength; j++) {   // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

    static long getSumOfMainDiagonal(long[][] m) {
        long result = 0;
        for (int i = 0; i < m.length; i++) {
            result += m[i][i];
        }
        return result;
    }

    public static long[][] multiplyMatriceByConstant(long[][] m, long c) {
        long[][] result = new long[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {     // rows from m1
            for (int j = 0; j < m[0].length; j++) {   // columns from m2
                result[i][j] = m[i][j] * c;
            }
        }
        return result;
    }

    public static long[][] divideMatriceByConstant(long[][] m, long c) {
        long[][] result = new long[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {     // rows from m1
            for (int j = 0; j < m[0].length; j++) {   // columns from m2
                result[i][j] = m[i][j] / c;
            }
        }
        return result;
    }

    static void printMatrix(long[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.printf("%d\t", m[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static long[][] concatenateMatricesVertically(long[][] m1, long[][] m2) {
        long[][] mResult = new long[m1.length+m2.length][m1[0].length];
        int i = 0;
        for (; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                mResult[i][j] = m1[i][j];
            }
        }
        for (; i < m1.length + m2.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                mResult[i][j] = m2[i - m1.length][j];
            }
        }
        return mResult;
    }

    public static long[][] concatenateMatricesHorizontally(long[][] m1, long[][] m2) {
        long[][] mResult = new long[m1.length][m1[0].length + m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            int j = 0;
            for (; j < m1[0].length; j++) {
                mResult[i][j] = m1[i][j];
            }
            for (; j < m1[0].length + m2[0].length; j++) {
                mResult[i][j] = m2[i][j - m1[0].length];
            }
        }
        return mResult;
    }

    public static long[][] getUnitMatrix(int n, long value) {
        long[][] I = new long[n][n];
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[0].length; j++) {
                if (i != j)
                    I[i][j] = 0;
                else
                    I[i][j] = value;
            }
        }
        return I;
    }
}
