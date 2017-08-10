
/* Array Representation of Heap */
public class Heap {
    int[] values;
    int size = 0;

    public Heap(int maxHeight) {
        values = new int[(int) Math.pow(2, maxHeight) - 1];
    }

    public void swapValues(int index1, int index2) {
        int tmp = values[index1];
        values[index1] = values[index2];
        values[index2] = tmp;
    }

    public void add(int value) {
        if (size == values.length) {
            try {
                throw new HeapException("The Heap is full");
            } catch (HeapException e) {
                e.printStackTrace();
            }
        }
        values[size] = value;
        bubbleUp(size);
        size++;
    }

    public void bubbleUp(int index) {
        if (index == 0) return;
        int parentIndex = (index - 2) / 2;
        if (values[parentIndex] < values[index]) {
            swapValues(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    public int extract() {
        if (size == 0) {
            try {
                throw new HeapException("The Heap is empty");
            } catch (HeapException e) {
                e.printStackTrace();
            }
        }
        // largest value is first element of the array
        int extractMax = values[0];
        // swap first and last elements of array
        values[0] = values[size - 1];
        bubbleDown(0);
        size -= 1;
        return extractMax;
    }

    public void bubbleDown(int index) {
        int greaterChild; // which of the two children is greater
        int leftIndex = (index * 2) + 1;
        int rightIndex = (index * 2) + 2;
        // make sure indexes are within bounds of array
        // and compare indexes to find greater child
        if (rightIndex < size && values[rightIndex] > values[leftIndex]) {
            greaterChild = rightIndex;
        } else if (leftIndex < size) {
            greaterChild = leftIndex;
        } else return;
        //
        if (values[index] < values[greaterChild]) {
            swapValues(index, greaterChild);
            bubbleDown(greaterChild);
        }
    }
}
