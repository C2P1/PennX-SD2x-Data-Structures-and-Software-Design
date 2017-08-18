/*
 * Main used as parent class due to lack of imagination...
 */
public class MyImplementedThread extends Main implements Runnable {

    public MyImplementedThread() {
        // constructor stuff
    }

    /**
     * Entry point of the new thread
     */
    @Override
    public void run() {

    }
}

class MainTwo {
    public static void main(String[] args) {
        MyImplementedThread myImplementedThread = new MyImplementedThread();

        Thread thread = new Thread(myImplementedThread);

        thread.start();

        // this code continues whilst myThread.run() executes simultaneously

    }
}
