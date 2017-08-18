import java.util.Arrays;

/*
 * Sum an array by using two threads.
 * One thread for the sum of the first half.
 * Another thread to simultaneously calculate the sum if the second half
 */
public class AdderThread extends Thread{

    private int[] array;
    private int sum;

    public AdderThread(int[] array) {
        this.array = array;
    }

    public void run() {
        for (int i = 0; i < array.length; i++) {
            sum+= array[i];
        }
    }

    public int getSum() {
        return sum;
    }
}

class MainAdder {
    public static int addArray(int[] array) throws InterruptedException {
        int midpoint = array.length/2;

        // split array in half
        int[] firstHalf  = Arrays.copyOfRange(array, 0, midpoint);
        int[] secondHalf = Arrays.copyOfRange(array, midpoint, array.length);

        AdderThread firstAdder = new AdderThread(firstHalf);
        AdderThread secondAdder = new AdderThread(secondHalf);

        firstAdder.start();
        secondAdder.start();


        // wait here for the threads to finish running
        // require checked exception InterruptedException
        firstAdder.join();
        secondAdder.join();

        int firstSum = firstAdder.getSum();
        int secondSum = secondAdder.getSum();

        return firstSum + secondSum;
        }

    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[]{1,2,3,4,5,6};

        System.out.println(addArray(array));

    }
}
