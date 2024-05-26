public class LinearAlgebraicEquation {
    private static long[][] A3 = {
            { 1, 1, 1, 1 },
            { 2, -1, 3, -1 },
            { 3, 2, -1, 4 },
            { 4, -2, 6, -2 }
    };

    private static long[][] A1 = {
            {1, 2, 0, 0, -1},
            {1, 2, 0, 1, -1},
            {1, 2, 0, 0, -1},
            {1, 2, 1, 0, 0}
    };

    private static long[][] A2 = {
            {0, 0, 0, 0, 0},
            {0, 10, -20, -30, 40},
            {0, -20, 40, 60, -80},
            {0, -30, 60, 90, -120},
            {0, 40, -80, -120, 160}
    };

    private static long[][] A = {
            { -12, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, -12, 0 },
            { 0, 0, 0, 0 }
    };

    public static void main(String[] args) {
//        Faddeev.completeFaddeevAlgorithm(A);
//        long[][] newRow = {
//                { 2, 1, 1, 1 }
//        };
//        long[][] newColumn = {
//                { 1 },
//                { 1 },
//                { 2 },
//                { 2 },
//                { 1 }
//        };
//        A = Matrix.concatenateMatricesVertically(A, newRow);
//        A = Matrix.concatenateMatricesHorizontally(A, newColumn);
        System.out.println("Matrix A:");
        Matrix.printMatrix(A);
        Greville.completeGrevilleAlgorithm(A);
//        Faddeev.completeFaddeevAlgorithm(A);
    }
}
