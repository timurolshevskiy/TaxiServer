package test;

/**
 * Created by Timur on 07.11.2015.
 */
public class Runner implements Runnable {

    private boolean notif;
    Worker worker;

    public Runner(Worker worker, boolean n) {
        this.worker = worker;
        notif = n;
    }

    public void run() {
        if (!notif) worker.stop();
        else worker.work();
    }

}
