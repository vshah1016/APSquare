package hi.mr.geary.magicSquare.exceptions;

public class InvalidSize extends InvalidInput{
    public InvalidSize(int size) {
        super(size + " is an invalid size for this magic square calculator.");
    }
}
