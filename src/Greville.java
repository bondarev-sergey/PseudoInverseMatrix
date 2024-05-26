import java.util.ArrayList;

public class Greville {


    private static long[][] A2 = {
            {1, 2, 0, 0, -1},
            {1, 2, 0, 1, -1},
            {1, 2, 0, 0, -1},
            {1, 2, 1, 0, 0}
    };
    private static long[][] A1 = {
            {1, 0, 1, 0, -2},
            {0, 2, 1, 0, -1},
            {1, 0, 0, 2, -1},
            {0, 2, 0, 2, -2}
    };

    public static void main(String[] args) {
        long[][] A3 = {
                {3, -2, -3, 1, -1},
                {-2, 4, 3, 2, 3},
                {1, 1, -3, -1, -1},
                {1, 2, -4, 4, 0}
        };
        long[][] A = {
                { -12, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, -12, 0 },
                { 0, 0, 0, 0 }
        };
        long[][] T1 = Matrix.transposeMatrix(getColumnOfA(A, 1, 1));
        long[][] t1 = Matrix.multiplyMatrices(T1, getColumnOfA(A, 1, 1));
        start(A, T1, t1, 2);
    }

    public static PseudoInverseMatrix completeGrevilleAlgorithm(long[][] A) {
        System.out.println("Метод Гревиля");
        long[][] T1 = Matrix.transposeMatrix(getColumnOfA(A, 1, 1));
        long[][] t1 = Matrix.multiplyMatrices(T1, getColumnOfA(A, 1, 1));
        System.out.print("t" + 1 + ": ");
        Matrix.printMatrix(t1);
        System.out.println("T" + 1);
        Matrix.printMatrix(T1);
        PseudoInverseMatrix matrix = start(A, T1, t1, 2);
        return matrix;
    }

    public static long[][] getColumnOfA(long[][] m, int columnNumber, int columnCount) {
        long[][] temp = new long[m.length][columnCount];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < columnCount; j++)
                temp[i][j] = m[i][columnNumber - 1 + j];
        return temp;
    }

    public static long[][] getLengthOfVector(long[][] m) {
        long[][] sum = new long[1][1];
        for (int i = 0; i < m.length; i++) {
            sum[0][0] += Math.pow(m[i][0], 2);
        }
        return sum;
    }

    public static long findGCD(long[] numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = gcd(result, numbers[i]);
        }
        return result;
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static PseudoInverseMatrix start(long[][] A, long[][] T, long[][] t, int step) {
        System.out.println("Шаг " + step);
        long[][] left = Matrix.multiplyMatriceByConstant(getColumnOfA(A, step, 1), t[0][0]);
        long[][] right = Matrix.multiplyMatrices(Matrix.multiplyMatrices(getColumnOfA(A, 1, step - 1), T), getColumnOfA(A, step, 1));
        long[][] C = Matrix.subtractMatrices(left, right);
        System.out.println("C" + step);
        Matrix.printMatrix(C);
        long[][] newt;
        long[][] F;
        if (C[0][0] != 0) {
            newt = Matrix.subtractMatrices(
                    Matrix.multiplyMatrices(t, getLengthOfVector(getColumnOfA(A, step, 1))),
                    Matrix.multiplyMatrices(Matrix.multiplyMatrices(Matrix.multiplyMatrices(
                                    Matrix.transposeMatrix(getColumnOfA(A, step, 1)), getColumnOfA(A,1,step-1)), T
                    ), getColumnOfA(A, step, 1)
                    ));
            F = Matrix.transposeMatrix(C);
        }
        else {
            long[][] t_squared = Matrix.multiplyMatrices(t, t);
            long[][] Ta2 = Matrix.multiplyMatrices(T, getColumnOfA(A, step, 1));
            newt = Matrix.getSumOfMatrices(t_squared, getLengthOfVector(Ta2));
            F = Matrix.multiplyMatrices(Matrix.multiplyMatrices(Matrix.transposeMatrix(getColumnOfA(A, step, 1)), Matrix.transposeMatrix(T)), T);
        }
        long[][] subtraction = Matrix.subtractMatrices(Matrix.getUnitMatrix(F[0].length, newt[0][0]), Matrix.multiplyMatrices(getColumnOfA(A, step, 1), F));
        long[][] resultForNewT = Matrix.multiplyMatrices(T, subtraction);
        long[][] newT = Matrix.concatenateMatricesVertically(Matrix.divideMatriceByConstant(resultForNewT, t[0][0]), F);
        ArrayList<Long> numbers = new ArrayList<>();
        numbers.add(newt[0][0]);
        for (int i = 0; i < newT.length; i++) {
            for (int j = 0; j < newT[0].length; j++) {
                numbers.add(newT[i][j]);
            }
        }
        long commonDenominator = findGCD(numbers.stream()
                .mapToLong(Long::longValue)
                .toArray());
        newt = Matrix.divideMatriceByConstant(newt, commonDenominator);
        newT = Matrix.divideMatriceByConstant(newT, commonDenominator);
        System.out.print("t" + step + ": ");
        Matrix.printMatrix(newt);
        System.out.println("T" + step);
        Matrix.printMatrix(newT);
        if (step != A[0].length) {
            return start(A, newT, newt, step + 1);
        }
        else {
            return new PseudoInverseMatrix(newt, newT);
        }
    }
}