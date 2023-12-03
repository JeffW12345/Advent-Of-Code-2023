package Day3_2023.Part_2;

public class FullNumber {

    private final int number;
    public FullNumber(int number) {
        this.number = number;
    }
    public int getFactor(FullNumber otherNumberObject) {
        return this.number * otherNumberObject.number;
    }
}
