package hi.mr.geary.magicSquare;

import hi.mr.geary.magicSquare.exceptions.InvalidSize;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MagicSquareGenerator {

    public static Square squareGenerator(int n, int startingNumber) throws InvalidSize {
        if (n < 2) throw new InvalidSize(n);
        Integer[][] data;
        if (n % 2 == 1) data = oddGenerator(n);
        else if (n % 4 == 0) data = doublyEven(n);
        else data = null;
        data = Arrays.stream(data).map(i -> Arrays.stream(i).map(j -> j + startingNumber - 1).toArray(Integer[]::new)).toArray(Integer[][]::new);
        return new Square(data);
    }

    private static Integer[][] oddGenerator(int n){
        Integer[][] array = new Integer[n][n];
        int row = 0;
        int col = n/2;
        for(int i = 1; i<= Math.pow(n, 2); i++){
            try{
                if(array[row][col] != null){
                    row += 2;
                    col --;
                }
            } catch (Exception ignore) {}
            array[row][col] = i;
            col ++; row--;
            if(row < 0 && col == n) {
                row +=2;
                col--;
            } else {
                if(row < 0) row = n-1;
                if(col == n) col = 0;
            }
        }
        return array;
    }

    private static Integer[][] doublyEven(int n){
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
        return data;
    }
}
