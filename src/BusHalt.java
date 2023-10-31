import java.util.concurrent.Semaphore;

public class BusHalt {
    private Semaphore busCapacity;
    private final Semaphore busSynchronizer;
    private int passengerWaitCount;

    private Bus currentBus;

    public BusHalt() {
        this.busSynchronizer = new Semaphore(1);
        this.passengerWaitCount = 0;
        this.currentBus = null;
    }

    public void arrive(Bus bus) {
        try {
            this.currentBus = bus;
            this.busSynchronizer.acquire();

            synchronized (this) {
                System.out.println("Bus " + bus.getName() + " arrived.");
                bus.setPassengerWaitCount(this.passengerWaitCount);
                this.passengerWaitCount = 0;
                this.notifyAll();
            }

            if(bus.getPassengerWaitCount() != 0){
                synchronized (bus){
                    System.out.println("Bus " + bus.getName() + " waiting to depart");
                    bus.wait();
                }
            }

            synchronized (this){
                this.depart(bus);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void depart(Bus bus) {
        this.busSynchronizer.release();
        this.currentBus = null;
        System.out.println("Bus " + bus.getName() + " departed.");
    }

    public Bus getCurrentBus(){
        return currentBus;
    }

    public void incrementPassengerWaitCount(){
        this.passengerWaitCount++;
    }

}
