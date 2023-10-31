import java.util.concurrent.Semaphore;

public class Main {

    public static boolean bus = false;
    public static void main(String[] args) {
        BusHalt busHalt = new BusHalt();

        PassengerGenerator passengerGenerator = new PassengerGenerator(busHalt);
        BusGenerator busGenerator = new BusGenerator(busHalt);

        passengerGenerator.start();
        busGenerator.start();
    }
}

