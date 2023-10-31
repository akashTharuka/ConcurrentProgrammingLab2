public class Generator extends Thread{

    public final BusHalt busHalt;
    private int count;
    public final int period;

    public Generator(BusHalt busHalt, int period) {
        this.busHalt = busHalt;
        this.count = 0;
        this.period = period;
    }

    public void incrementCount(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
