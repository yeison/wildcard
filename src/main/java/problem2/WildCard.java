package problem2;

public class WildCard implements Comparable<WildCard> {

    private final int generationTime;
    private final int overhead;

    public WildCard(int generationTime, int overhead){
        this.generationTime = generationTime;
        this.overhead = overhead;
    }

    public int getGenerationTime(){
        return generationTime;
    }

    public int getOverhead(){
        return overhead;
    }

    @Override
    public int compareTo(WildCard o) {

        if(this.overhead < o.overhead){
            return -1;
        } else if(this.overhead == o.overhead){
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public String toString() {
        return String.format("(overhead: %3d, generationTime: %3d)", overhead, generationTime);
    }
}