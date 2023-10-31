import java.util.concurrent.Semaphore;

public class Bus extends Thread {
    private int passengerWaitCount;
    private int currentPassengerCount;
    private final BusHalt busHalt;
    private final Semaphore sem;

    public Bus(BusHalt busHalt) {
        this.busHalt = busHalt;
        this.sem = new Semaphore(50);
        this.currentPassengerCount = 0;
    }

    public void run(){
        busHalt.arrive(this);
    }

    public boolean board() {
        boolean acquired = this.sem.tryAcquire();

        if(!acquired)
            return false;

        synchronized (this){
            if (++this.currentPassengerCount == this.passengerWaitCount){
                this.notify();
                System.out.println("Bus currently has " + this.currentPassengerCount + " passengers");
            }
        }

        return true;
    }

    public void setPassengerWaitCount(int passengerWaitCount) {
        this.passengerWaitCount = Math.min(passengerWaitCount, 50);
        System.out.println("Bus " + this.getName() + " is waiting for " + this.passengerWaitCount + " passengers.");
    }

    public int getPassengerWaitCount(){
        return this.passengerWaitCount;
    }

}
