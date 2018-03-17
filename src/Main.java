import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shwetank and Rohan on 09-02-2018.
 */
public class Main {

    public static void main(String args[]) {
        System.out.print("Starting Flight Database");
       try {
           int x = 3;
           while(x-->0) {
               System.out.print(".");
               TimeUnit.SECONDS.sleep(1);
           }
       }
       catch(InterruptedException e) {
           e.printStackTrace();
       }
        System.out.println();
        Airline vistara = new Airline();
        System.out.println();
        System.out.print("Starting Simulation");
       try {
           int x = 3;
           while(x-->0) {
               System.out.print(".");
               TimeUnit.SECONDS.sleep(1);
           }
       }
       catch(InterruptedException e) {
           e.printStackTrace();
       }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Transaction Threads : ");
        int t = sc.nextInt();
        System.out.print("Enter 1 for Serial Scheduling or 2 for 2 Phase Locking : ");
        int p = sc.nextInt();
        new Thread(new Counter()).start();
        while(t-->0){
            new Thread(new Transaction(vistara, t, p)).start();
        }
    }
}
