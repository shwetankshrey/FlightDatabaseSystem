import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Shwetank and Rohan on 12-02-2018.
 */
public class Airline {

    private LinkedList<Flight> flights;
    private LinkedList<Passenger> passengers;
    private Lock lock;

    public Airline() {
        flights = new LinkedList<>();
        addFlights();
        passengers = new LinkedList<>();
        addPassengers();
        Random rand = new Random();
        int i = 10;
        while(i-->0) {
            int f = rand.nextInt(5) + 1;
            int p = rand.nextInt(10) + 1;
            reserve(getFlight(f), getPassenger(p));
        }
        lock = new ReentrantLock();
        System.out.println("Database initialised");
        System.out.println("Loaded " + flights.size() + " flight records and " + passengers.size() + " passenger records.");
    }

    private void addFlights() {
        String file = "C:\\Users\\shwet\\Desktop\\FlightDatabaseSystem\\resources\\flights.csv";
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String value[] = line.split(",");
                flights.add(new Flight(Integer.parseInt(value[0]), value[1], value[2], value[3], value[4]));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addPassengers() {
        String file = "C:\\Users\\shwet\\Desktop\\FlightDatabaseSystem\\resources\\passengers.csv";
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String value[] = line.split(",");
                passengers.add(new Passenger(Integer.parseInt(value[0]), value[1], value[2], value[3], value[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Flight getFlight(int i) {
        for(Flight f : flights) {
            if(f.getID() == i) {
                return f;
            }
        }
        return null;
    }

    public Passenger getPassenger(int i) {
        for(Passenger p : passengers) {
            if(p.getID() == i) {
                return p;
            }
        }
        return null;
    }

    public void reserve(Flight _flight, Passenger _pass) {
        if(_pass.hasReservation(_flight)) {
            return;
        }
        _pass.add(_flight);
        _flight.add(_pass);
    }

    public void cancel(Flight _flight, Passenger _pass) {
        if(!_pass.hasReservation(_flight)) {
            return;
        }
        _flight.remove(_pass.getID());
        _pass.remove(_flight.getID());
    }

    public void listFlights(Passenger _pass) {
        _pass.listFlights();
    }

    public void totalReservations() {
        Flight.printTotal();
    }

    public void transfer(Flight _flight1, Flight _flight2, Passenger _pass) {
        _pass.remove(_flight1.getID());
        _flight1.remove(_pass.getID());
        _pass.add(_flight2);
        _flight2.add(_pass);
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
