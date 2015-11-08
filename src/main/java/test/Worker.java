package test;

/**
 * Created by Timur on 07.11.2015.
 */
public class Worker {

    public synchronized void stop() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId());
    }

    public synchronized void work() {
        notifyAll();
    }

}
