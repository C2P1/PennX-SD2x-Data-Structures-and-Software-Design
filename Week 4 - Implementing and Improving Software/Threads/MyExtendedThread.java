public class MyExtendedThread extends Thread {
    private int howMany;
    
    public MyExtendedThread(int name) {
        this.howMany = name;
    }

    /**
     * This is the entry point of a new thread
     */
    public void run() {
        /*
         * Do something with the instance variables that have been
         * initialized by the constructor.
         */

        for (int i = 0; i < howMany; i++) {
            System.out.println(i);
        }
    }
}


class Main {
    public static void main(String[] args) {
        MyExtendedThread myThread = new MyExtendedThread(10);
        myThread.start(); // launches new thread & invokes myThread.run()
    }
}

