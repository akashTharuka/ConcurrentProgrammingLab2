import java.util.concurrent.Semaphore;

public class BusGenerator extends Generator {
    public BusGenerator(BusHalt busHalt) {
        super(busHalt, 400);
    }
    public void run(){
        while (true){
            Bus bus = new Bus(busHalt);
            this.incrementCount();
            bus.setName(Integer.toString(this.getCount()));
            bus.start();

            try{
                Thread.sleep(this.period);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
