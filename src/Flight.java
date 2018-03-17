import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Shwetank and Rohan on 10-02-2018.
 */
public class Flight {

    private int id;
    private int capacity;
    private String from;
    private String to;
    private String date;
    private String time;
    private LinkedList<Passenger> passengers;
    private static int totalReservations;
    private Lock lock;

    public Flight(int i, String f, String t, String d, String tm) {
        id = i;
        capacity = 100;
        from = f;
        to = t;
        date = d;
        time = tm;
        passengers = new LinkedList<>();
        totalReservations = 0;
        lock = new ReentrantLock();
    }

    public int getID() {
        return id;
    }

    public void add(Passenger p) {
        if(passengers.size() > capacity) {
            return;
        }
        passengers.add(p);
        totalReservations++;
    }

    public void remove(int i) {
        Passenger p = null;
        for (Passenger x : passengers) {
            if(x.getID() == i) {
                p = x;
                break;
            }
        }
        if(p != null) {
            return;
        }
        passengers.remove(p);
        totalReservations--;
    }

    public static void printTotal() {
        System.out.println("Total Number of Reservations are " + totalReservations);
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
