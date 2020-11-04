package hi.mr.geary.magicSquare;

import java.util.Arrays;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        int n = 4;
        Integer[][] data = new Integer[n][n];
        int corner = n/4;
        int center = n/2;
        for(int x = 1; x <= 4; x++){
            int i; int j;
            if(x == 1) i = j = 0;
            else if (x == 2){ i = 0; j = n - corner; }
            else if (x == 3){ i = n - corner; j = 0; }
            else { i = j = n - corner; }
            int iCopy = i, jCopy = j;
            for (; i < iCopy + corner; i++) {
                for (j = jCopy; j < jCopy + corner; j++) {
                    data[i][j] = (i*n) + j + 1;
                }
            }
        }
        for (int i = corner; i < corner + center; i++){
            for (int j = corner; j < corner + center; j++){
                data[i][j] = (i*n) + j + 1;
            }
        }
        for (int x = 1; x <= 2; x++) {
            int i;
            if (x == 1) i = 0;
            else i = n - corner;
            int iCopy = i;
            for (; i < iCopy + corner; i++){
                for(int j = corner; j < corner + center; j++){
                    data[i][j] = (int) Math.pow(n, 2) - (i * n + j);
                }
            }
        }
        for (int x = 1; x <= 2; x++) {
            int j;
            if (x == 1) j = 0;
            else j = n - corner;
            int jCopy = j;
            for (int i = corner; i < corner + center; i++){
                for(j = jCopy; j < jCopy + corner; j++){
                    data[i][j] = (int) Math.pow(n, 2) - (i * n + j);
                }
            }
        }
        System.out.println(Arrays.deepToString(data).replace("[[", "[").replace("]]", "]").replace(", [", "\n["));
    }
}
