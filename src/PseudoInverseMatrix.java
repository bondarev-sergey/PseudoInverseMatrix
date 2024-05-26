import java.util.Arrays;

public class PseudoInverseMatrix {
    long[][] divider;
    long[][] matrix;

    public PseudoInverseMatrix(long[][] divider, long[][] matrix) {
        this.divider = divider;
        this.matrix = matrix;
    }

    public long[][] getDivider() {
        return divider;
    }

    public long[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                str += matrix[i][j] + "\t";
            }
            str += "\n";
        }
        str += "\n" + divider[0][0];
        return str;
    }
}
