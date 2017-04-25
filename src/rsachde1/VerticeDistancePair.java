package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * 
 * This class models a vertex-distance pair which is stored by the heap
 * in the PriorityQueue.
 */
public class VerticeDistancePair {
    
    private double distance; //weight, shortest distance to vertex
    private int vertice; //vertice index

    public VerticeDistancePair(double distance, int vertice) {
        this.distance = distance;
        this.vertice = vertice;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    } 
}
