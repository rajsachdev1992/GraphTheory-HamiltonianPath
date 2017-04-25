package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * This class represents a Priority Queue which is implemented using a min heap data structure.
 * The elements are prioritized on the basis of their distance values. Lower the distance, higher 
 * the priority.
 */
public class PriorityQueue {

    private final VerticeDistancePair[] heap; //array which stores heap elements
    private final int heapCapacity;           //capacity of the heap.
    private int count;                        //number of elements in the heap.

    public PriorityQueue(int heapCapacity) {
        this.heapCapacity = heapCapacity + 1;
        this.count = 0;
        this.heap = new VerticeDistancePair[this.heapCapacity];
    }

    /**
     * inserts a new key value pair in the heap.
     * @param record
     * PreCondition: record should not be null.
     * PostCondition: The record will be inserted in the appropriate spot
     * in the heap.
     * Complexity: Theta(log(n))
     */
    public void insert(VerticeDistancePair record) {
        heap[++count] = record;
        heapifyUpward();
    }
    
    /**
     * Checks if the heap is empty.
     * @return boolean: True if the heap is empty.
     * Complexity: Theta(1)
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Deletes the element which has the minimum distance value from the heap.
     * @return VerticeDistancePair
     * PreCondition: The heap should not be empty.
     * PostCondition: The element with the shortest distance is deleted from the heap and returned.
     * Complexity: Theta(log(n))
     */
    public VerticeDistancePair deleteMin() {
        if (count == 0) {
            //heap is empty
            throw new RuntimeException("Heap underflow exception");
        }
        VerticeDistancePair recordDeleted = heap[1];
        heap[1] = heap[count--];
        heapifyDownward();
        return recordDeleted;
    }

    /**
     * Checks if a particular vertice exists in the heap.
     * @param v
     * @return True if vertice exists.
     * Complexity: Theta(n)
     */
    public boolean isVerticeExists(int v) {
        boolean isExists = false;
        for (int i = 1; i <= count; i++) {
            if (heap[i].getVertice() == v) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    /**
     * Updates the priority (distance) of a vertex in the heap.
     * @param vertice
     * @param distance 
     * PreCondition: The heap must contain the vertex entered.
     * PostCondition: The priority will be updated for that vertex.
     * Complexity: Worst case : Theta(n), Best Case :Theta(log(n))
     */
    public void updatePriority(int vertice, double distance) {
        for (int i = 1; i <= count; i++) {
            if (heap[i].getVertice() == vertice) {
                heap[i].setDistance(distance);
                heapifyUpward(i);
                break;
            }
        }
    }

    /**
     * This method adjusts the element in the top of a heap to a suitable position
     * in the heap. This is a helper method for the deleteMin() method.
     * PreCondition: The heap should not be empty.
     * PostCondition: The top element is shifted to a suitable position in the min heap.
     * Complexity: Theta(log(n))
     */
    private void heapifyDownward() {
        VerticeDistancePair temp = heap[1];
        int parentIndex = 1;
        int childIndex = 2;
        while (childIndex <= this.count) {
            if (childIndex < count && heap[childIndex].getDistance() > heap[childIndex + 1].getDistance()) {
                childIndex++;
            }
            if (temp.getDistance() <= heap[childIndex].getDistance()) {
                break;
            }

            heap[parentIndex] = heap[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2;
        }
        heap[parentIndex] = temp;
    }

    /**
     * This method adjusts the last element in the heap to a suitable position
     * in the heap. This is a helper method for the insert() method.
     * PreCondition: The heap should not be empty.
     * PostCondition: The last element is shifted to a suitable position in the min heap.
     * Complexity: Theta(log(n))
     */
    private void heapifyUpward() {
        int tempPosition = count;
        VerticeDistancePair newEntry = heap[count];
        while (tempPosition != 1 && newEntry.getDistance() < heap[tempPosition / 2].getDistance()) {
            heap[tempPosition] = heap[tempPosition / 2];
            tempPosition /= 2;
        }
        heap[tempPosition] = newEntry;
    }

    //heapifies according to last entered entry
    /**
     * This method adjusts the element at position 'i' in the heap to a suitable position
     * in the heap. This is a helper method for the updatePriority() method.
     * @param position 
     * PreCondition: There should be an element at position 'i' in the heap.
     * PostCondition: The element at position 'i' will be shifted to a suitable position
     * in the heap.
     * Complexity: Theta(log(n))
     */
    private void heapifyUpward(int position) {
        int tempPosition = position;
        VerticeDistancePair newEntry = heap[position];
        while (tempPosition != 1 && newEntry.getDistance() < heap[tempPosition / 2].getDistance()) {
            heap[tempPosition] = heap[tempPosition / 2];
            tempPosition /= 2;
        }
        heap[tempPosition] = newEntry;
    }

}
