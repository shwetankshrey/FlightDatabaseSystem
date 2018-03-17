import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Shwetank and Rohan on 10-02-2018.
 */
public class Passenger {

    private int id;
    private String fname;
    private String lname;
    private String email;
    private String sex;
    private LinkedList<Flight> flights;
    private Lock lock;

    public Passenger(int i, String f, String l, String e, String s) {
        id = i;
        fname = f;
        lname = l;
        email = e;
        sex = s;
        flights = new LinkedList<>();
        lock = new ReentrantLock();
    }

    public int getID() {
        return id;
    }

    public void add(Flight f) {
        flights.add(f);
    }

    public void remove(int i) {
        Flight p = null;
        for (Flight x : flights) {
            if(x.getID() == i) {
                p = x;
                break;
            }
        }
        flights.remove(p);
    }

    public boolean hasReservation(Flight flightx) {
        for(Flight f : flights) {
            if(f.getID() == flightx.getID()) {
                return true;
            }
        }
        return false;
    }

    public void listFlights() {
        System.out.print("Flights booked for passenger " + id + " are : ");
        for (Flight f : flights) {
            System.out.print(f.getID() + " ");
        }
        System.out.println();
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
