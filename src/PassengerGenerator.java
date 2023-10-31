import java.util.concurrent.Semaphore;

public class PassengerGenerator extends Generator {

    public PassengerGenerator(BusHalt busHalt){
        super(busHalt, 2);
    }

    public void run(){
        while (true){
            Passenger passenger = new Passenger(busHalt);
            this.incrementCount();
            passenger.setName(Integer.toString(this.getCount()));
            passenger.start();

            try{
                Thread.sleep(this.period);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
