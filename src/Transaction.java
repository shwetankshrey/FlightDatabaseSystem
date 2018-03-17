import java.util.Random;

/**
 * Created by Shwetank and Rohan on 09-02-2018.
 */
public class Transaction implements Runnable {

    private Airline airline;
    private int id;
    private int type;
    private static int count;

    public Transaction(Airline air, int i, int j) {
        airline = air;
        id = i;
        type = j;
        count = 0;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run() {
        switch (type) {
            case 1: {
                while (true) {
                    try {
                        airline.lock();
                        simulate1();
                        Thread.sleep(200);
                        airline.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            case 2: {
                while (true) {
                    try {
                        simulate2();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void simulate1() {
        //System.out.println("Using Transaction Thread " + id);
        Random rand = new Random();
        int i = rand.nextInt(1000);
        count++;
        if(i < 400) {
            int f = rand.nextInt(5) + 1;
            int p = rand.nextInt(10) + 1;
            airline.reserve(airline.getFlight(f), airline.getPassenger(p));
            System.out.println("Reserved Flight " + f + " for Passenger " + p);
            return;
        }
        if(i < 450) {
            int f = rand.nextInt(5) + 1;
            int p = rand.nextInt(10) + 1;
            airline.cancel(airline.getFlight(f), airline.getPassenger(p));
            System.out.println("Cancelled Reservation on Flight " + f + " for Passenger " + p);
            return;
        }
        if(i < 700) {
            int p = rand.nextInt(10) + 1;
            airline.listFlights(airline.getPassenger(p));
            return;
        }
        if(i < 900) {
            airline.totalReservations();
            return;
        }
        if(i < 1000) {
            int p = rand.nextInt(10) + 1;
            int f1 = rand.nextInt(5) + 1;
            int f2 = rand.nextInt(5) + 1;
            airline.transfer(airline.getFlight(f1), airline.getFlight(f2), airline.getPassenger(p));
            System.out.println("Transferred Reservation from Flight " + f1 + " to Flight " + f2 + " for Passenger " + p);
            return;
        }
    }

    private void simulate2() {
        //System.out.println("Using Transaction Thread " + id);
        Random rand = new Random();
        int i = rand.nextInt(1000);
        count++;
        if(i < 400) {
            int f = rand.nextInt(5) + 1;
            int p = rand.nextInt(10) + 1;
            airline.getFlight(f).lock();
            airline.getPassenger(p).lock();
            airline.reserve(airline.getFlight(f), airline.getPassenger(p));
            System.out.println("Reserved Flight " + f + " for Passenger " + p);
            airline.getFlight(f).unlock();
            airline.getPassenger(p).unlock();
            return;
        }
        if(i < 450) {
            int f = rand.nextInt(5) + 1;
            int p = rand.nextInt(10) + 1;
            airline.getFlight(f).lock();
            airline.getPassenger(p).lock();
            airline.cancel(airline.getFlight(f), airline.getPassenger(p));
            System.out.println("Cancelled Reservation on Flight " + f + " for Passenger " + p);
            airline.getFlight(f).unlock();
            airline.getPassenger(p).unlock();
            return;
        }
        if(i < 700) {
            int p = rand.nextInt(10) + 1;
            airline.getPassenger(p).lock();
            airline.listFlights(airline.getPassenger(p));
            airline.getPassenger(p).unlock();
            return;
        }
        if(i < 900) {
            airline.lock();
            airline.totalReservations();
            airline.unlock();
            return;
        }
        if(i < 1000) {
            int p = rand.nextInt(10) + 1;
            int f1 = rand.nextInt(5) + 1;
            int f2 = rand.nextInt(5) + 1;
            airline.getFlight(f1).lock();
            airline.getFlight(f2).lock();
            airline.getPassenger(p).lock();
            airline.transfer(airline.getFlight(f1), airline.getFlight(f2), airline.getPassenger(p));
            System.out.println("Transferred Reservation from Flight " + f1 + " to Flight " + f2 + " for Passenger " + p);
            airline.getFlight(f1).unlock();
            airline.getFlight(f2).unlock();
            airline.getPassenger(p).unlock();
            return;
        }
    }
}
