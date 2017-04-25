package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * 
 * This class contains methods to calculate a MST of a graph G using Prim's Algorithm.
 */
public class PrimsAlgorithm {

    GraphModel G; //Graph for which MST is to be calculated;

    public PrimsAlgorithm(GraphModel G) {
        this.G = G;
    }
    
    /**
     * This method is an implementation of Prim's Algorithm to find the MST of
     * graph G.
     * 
     * @return MST
     * PreConditions: Graph G should not be null.
     * PostConditions: An MST will be computed for the Graph G and returned by the method.
     * Complexity: Theta(V^2), V = number of vertices.
     */
    public MST generateMST() {
        CrimeRecord[] vertices = G.getV();
        double Adj[][] = G.getAdj();
        PriorityQueue pQueue = new PriorityQueue(vertices.length);

        //Initialize key[] and p[]
        MST mst = new MST(vertices.length);

        mst.setKeyIndex(0, 0.0);
        mst.setParentIndex(0, -1);
        pQueue.insert(new VerticeDistancePair(0.0, 0));
        
        for (int i = 1; i < vertices.length; i++) {
            pQueue.insert(new VerticeDistancePair(Double.MAX_VALUE, i));
            mst.setKeyIndex(i, Double.MAX_VALUE);
        }

        VerticeDistancePair u;
        while (!pQueue.isEmpty()) {
            u = pQueue.deleteMin();
            for (int v = 0; v < vertices.length; v++) {
                if (pQueue.isVerticeExists(v) && Adj[u.getVertice()][v] < mst.getKeyIndex(v)) {
                    mst.setParentIndex(v, u.getVertice());
                    mst.setKeyIndex(v, Adj[u.getVertice()][v]);
                    pQueue.updatePriority(v, Adj[u.getVertice()][v]);
                }
            }
        }
        return mst;
    }
}
