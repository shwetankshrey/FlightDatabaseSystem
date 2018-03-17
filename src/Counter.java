import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Shwetank and Rohan on 14-02-2018.
 */
public class Counter implements Runnable {

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\shwet\\Desktop\\FlightDatabaseSystem\\resources\\plot.csv", true));
            while (true) {
                try {
                    Thread.sleep(5000);
                    long stop = System.currentTimeMillis();
                    long time = stop - start;
                    long c = Transaction.getCount();
                    bw.write(time / 1000 + "," + c);
                    bw.newLine();
                    bw.flush();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
