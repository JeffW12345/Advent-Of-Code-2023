package Day_5.Part_2;

class Mapping {
    private final long startNumber;
    private final long destinationNumber;
    private final long duration;

    public Mapping(long startNumber, long destinationNumber, long duration) {
        this.startNumber = startNumber;
        this.destinationNumber = destinationNumber;
        this.duration = duration;
    }
    public long calculateValueFromKey(long key) {
        return destinationNumber + (key - startNumber);
    }

    public boolean withinRange(long key){
        return key >= startNumber && key < startNumber + duration;
    }

}