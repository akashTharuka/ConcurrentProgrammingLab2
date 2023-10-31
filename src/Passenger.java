import java.util.concurrent.Semaphore;

public class Passenger extends Thread {

    private final BusHalt busHalt;

    public Passenger(BusHalt busHalt){
        this.busHalt = busHalt;
    }

    public void run(){
        while(true){
            synchronized (this.busHalt){
                try {
                    this.busHalt.incrementPassengerWaitCount();
                    System.out.println("Passenger " + this.getName() + " waiting");
                    this.busHalt.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                boolean boarded = false;

                synchronized (busHalt){
                    if(this.busHalt.getCurrentBus() != null){
                        System.out.println("Passenger " + this.getName() + " trying to board bus " + busHalt.getCurrentBus().getName());
                        boarded = this.busHalt.getCurrentBus().board();
                    }
                    else{
                        continue;
                    }
                }

                if(boarded){
                    System.out.println("Passenger " + this.getName() + " boarded bus " + busHalt.getCurrentBus().getName());
                    break;
                }

            }
        }
    }

}
