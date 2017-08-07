import java.util.LinkedList;

public class HashSet {
    // elements stored in array of LinkedLists, or "buckets"
    // each element has associated hash code (int representation)
    // adding and finding element are O(1)
    // elements with the same hashcode are added to a linkedlist in that bucket


    private LinkedList<String>[] buckets;
    private int currentSize = 0;
    private double loadFactor; // average number of elements per bucket above which we resize
    // loadFactor is a balance between space and time
    public HashSet(int size, double loadFactor) {
        buckets = new LinkedList[size];
        for (int i = 0; i <size; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.loadFactor = loadFactor;
    }

    private int hashCode(String value) {
        return value.length();
    }

    public boolean add(String value) {
        if (!contains(value)) {
            int index = hashCode(value) % buckets.length;
            LinkedList<String> bucket = buckets[index];
            bucket.addFirst(value);
            currentSize++;

            double averageLoad = currentSize/ (double) buckets.length;
            if (averageLoad > loadFactor) {
                reinsertAll();
            }
            return true;
        }
        return false;
    }

    public boolean contains(String value) {
        int index = hashCode(value) % buckets.length;
        return buckets[index].contains(value);
    }

    public void reinsertAll() {
        LinkedList<String>[] oldBuckets = buckets;
        buckets = new LinkedList[buckets.length * 2];

        for (LinkedList<String> bucket : oldBuckets) {
            for (String element : bucket) {
                int index = hashCode(element) % buckets.length;
                LinkedList<String> newBucket = buckets[index];
                newBucket.addFirst(element);
            }
        }
    }
}
