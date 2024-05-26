import java.util.ArrayList;

public class Faddeev {
    private static long[][] AT;
    private static long[][] AAT;

    public static void main(String[] args) {
        long[][] A1 = {
                {3, -2, -3, 1, -1},
                {-2, 4, 3, 2, 3},
                {1, 1, -3, -1, -1},
                {1, 2, -4, 4, 0}
        };
        long[][] A3 = {
                {1, 2, 0, 0, -1},
                {1, 2, 0, 1, -1},
                {1, 2, 0, 0, -1},
                {1, 2, 1, 0, 0}
        };
        long[][] A2 = {
                {1, 0, 1, 0, -2},
                {0, 2, 1, 0, -1},
                {1, 0, 0, 2, -1},
                {0, 2, 0, 2, -2}
        };
        long[][] A = {
                { -12, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, -12, 0 },
                { 0, 0, 0, 0 }
        };
        AT = Matrix.transposeMatrix(A);
        AAT = Matrix.multiplyMatrices(AT, A);

        long[][] F = new long[A[0].length][A[0].length];
        for (int i = 0; i < A[0].length; i++)
            F[i][i] = 1;
        long f = Matrix.getSumOfMainDiagonal(AAT);
        printStep(F, f, 1);
        getPseudo(F, f, 2);
    }

    public static void completeFaddeevAlgorithm(long[][] A) {
        System.out.println("Метод Фаддеева");
        AT = Matrix.transposeMatrix(A);
        AAT = Matrix.multiplyMatrices(AT, A);

        long[][] F = new long[A[0].length][A[0].length];
        for (int i = 0; i < A[0].length; i++)
            F[i][i] = 1;

        long f = Matrix.getSumOfMainDiagonal(AAT);
        printStep(F, f, 1);
        getPseudo(F, f, 2);
    }

    static void printStep(long[][] F, long f, int step) {
        System.out.print("\n");
        System.out.printf("Шаг%d:\nФ%d:\n", step, step);
        Matrix.printMatrix(F);
        System.out.printf("f%d: %d\n", step, f);
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

    static void getPseudo(long[][] F, long f, int step) {
        long[][] I = new long[F[0].length][F[0].length];
        for (int i = 0; i < F[0].length; i++)
            I[i][i] = f;
        long[][] newF = Matrix.subtractMatrices(I, Matrix.multiplyMatrices(AAT, F));
        long newFi = Matrix.getSumOfMainDiagonal(Matrix.multiplyMatrices(AAT, newF)) / step;

        System.out.println("F" + step + ":");
        Matrix.printMatrix(newF);
        System.out.println("f" + step + ": " + newFi);

        if (newFi != 0 && step < 100) {
            getPseudo(newF, newFi, step + 1);
        }
        else {
            long[][] FAT = Matrix.multiplyMatrices(F, AT);
            ArrayList<Long> numbers = new ArrayList<>();
            numbers.add(f);
            for (int i = 0; i < FAT.length; i++) {
                for (int j = 0; j < FAT[0].length; j++) {
                    numbers.add(FAT[i][j]);
                }
            }
            long commonDenominator = findGCD(numbers.stream()
                    .mapToLong(Long::longValue)
                    .toArray());
            f /= commonDenominator;
            FAT = Matrix.divideMatriceByConstant(FAT, commonDenominator);

            System.out.print("\nОкончательный ответ Ф:\n");
            Matrix.printMatrix(FAT);
            System.out.printf("f: %d\n", f);
        }
    }
}


