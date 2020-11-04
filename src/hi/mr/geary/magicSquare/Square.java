package hi.mr.geary.magicSquare;

import java.time.temporal.ValueRange;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Square {
    private static ValueRange squareRange = Main.squareRange;

    private final Integer[][] data;
    private final int n;
    public final boolean magic;

    public Square(Integer[][] data) {
        this.data = data;
        n = data.length;
        magic = isMagic();
    }

    private boolean isMagic() {
        int magicConstant = constantExpected();
        for (Integer[] row : data) { if (Arrays.stream(row).mapToInt(Integer::intValue).sum() != magicConstant) return false; }
        for (int i = 0; i < n; i++) {
            int finalI = i;
            if (Arrays.stream(data).mapToInt(row -> row[finalI]).sum() != magicConstant) return false;
        }
        if (IntStream.range(0, n).map(i -> data[i][i]).sum() != magicConstant) return false;
        if (IntStream.range(0, n).map(i -> data[i][n-1-i]).sum() != magicConstant) return false;
        List<Integer> flatList = Arrays.stream(data).flatMap(Arrays::stream).collect(Collectors.toList());
        if(flatList.size() != flatList.stream().distinct().collect(Collectors.toList()).size()) return false;
        return true;
    }

    private int constantExpected() {
        return Arrays.stream(data).map(i -> i[0]).mapToInt(Integer::intValue).sum();
    }                                     
    public Integer magicConstant() {
        if (magic) return constantExpected();
        return null;
    }

    public static boolean inRange(int size) {
        return squareRange.isValidIntValue(size);
    }

    public String toString(){
        return Arrays.deepToString(data).replace("[[", "[").replace("]]", "]").replace(", [", "\n[");
    }
}
