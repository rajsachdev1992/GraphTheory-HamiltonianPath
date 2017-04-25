package rsachde1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Raj.Sachdev
 * This class provides methods to find the shortest Hamiltonian path in graph G.
 */
public class BruteForcePathOptimizer {

    private final GraphModel G;             //Graph of crime data
    private final List<String> pathList;    //Stores all premutations of possible paths on G.
    private double minPathLength;           //stores the distance of the shirtest Hamiltonian Path
    private String shortestPath;            //stores the shortest Hamiltonian path.

    public BruteForcePathOptimizer(GraphModel G) {
        this.G = G;
        pathList = new LinkedList<>();
    }

    public double getMinPathLength() {
        return minPathLength;
    }

    public String getShortestPath() {
        return shortestPath;
    }
    
    /**
     * Finds the shortest Hamiltonian path on Graph G by brute force computation.
     * PreCondition: The graph should not be null and should have at least 2 nodes.
     * PostCondition: The shortest Hamiltonian path and distance are computed and stored in the object.
     * Complexity: O(n!)
     */
    public void findShortestHamiltonianPath() {
        String[] vertices = new String[G.getV().length];
        for(int i=0;i<G.getV().length;i++) {
            vertices[i] = String.valueOf(i);
        }
        this.getPermutations(vertices, 0, vertices.length);
        Iterator<String> iter = pathList.iterator();
        String path;
        double pathLength;
        minPathLength = Double.MAX_VALUE;
        shortestPath = "";
        while (iter.hasNext()) {
            pathLength = 0.0;
            path = iter.next();
            int from;
            int to;
            String[] v = path.split(",");
            //System.out.println("path: "+ path);
            for (int i = 1; i < v.length; i++) {
                from = Integer.parseInt(v[i-1]);
                to = Integer.parseInt(v[i]);
                pathLength += G.getAdj()[from][to];
            }
            if (pathLength < minPathLength) {
                minPathLength = pathLength;
                shortestPath = path;
            }
        }
    }
    
    /**
     * Returns the crime records representation of the shortest hamiiltonian path.
     * @return String (String representation of the shortest path)
     * PreCondition: The shortestPath should be computed.
     * PostCondition: The Crime records in the order of the shortest path will be returned.
     * Complexity: Theta(n)
     */
    public String printShortestPath() {
        StringBuilder s = new StringBuilder("");
        int v;
        CrimeRecord c;
        String[] pathArray = shortestPath.split(",");
        for (int i = 0; i < pathArray.length - 1; i++) {
            v = Integer.parseInt(pathArray[i]);
            c = G.getV()[v];
            s.append(c.toString()).append("\n");
        }
        return s.toString();
    }
    
    /**
     * This method recursively computes all possible permutations of the elements in the
     * elements[] array and inserts them into the pathList.
     * @param elements
     * @param start
     * @param end
     * PreCondition: elements should not be null.
     * PostCondition: All possible permutations of the element[] array are stored
     * in the pathList
     */
    private void getPermutations(String[] elements, int start, int end) {
        if (start == end) {
            pathList.add(convertIntoPathFormat(elements));
        } else {
            for (int i = start; i < end; i++) {
                swap(elements, start, i);
                getPermutations(elements, start + 1, end);
                swap(elements, start, i);
            }
        }
    }
    
    /**
     * Swaps the element at index i with that at j in elements[] array
     * @param strArray
     * @param i
     * @param j 
     */
    private static void swap(String[] strArray, int i, int j) {
        String temp = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = temp;
    }
    
    /**
     * converts the elements in the array into a comma seperated path format.
     * @param strArray
     * @return String
     * PreCondition: strArray should not be null.
     * PostCondition: A string representation of the path in strArray will be returned.
     * Complexity: Theta(n)
     */
    private String convertIntoPathFormat(String[] strArray) {
        StringBuilder s = new StringBuilder("");
        for (String str : strArray) {
            s.append(str).append(",");
        }
        s.append(strArray[0]);
        return s.toString();
    }

}
