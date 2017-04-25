package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * This class represents a Minimum Spanning Tree. Provides methods to do a Hamiltonian
 * walk on the MST.
 */
public class MST {

    private double key[];  //stores min weight for each vertice
    private int p[];    //stores parent for each vertice

    private String shortestPath;
    private double minPathLength;

    public MST(int numVertices) {
        key = new double[numVertices];
        p = new int[numVertices];
    }
    
    /**
     * Does a pre order walk on the MST.
     * @param vertices 
     * 
     * PreCondition: vertices should not be null.
     * PostCondition: The crime records in the MST will be printed in pre-order fashion.
     * 
     * Complexity: Theta(V+E), V = number of vertices, E = number of edges.
     */
    public void dfsTraversal(CrimeRecord[] vertices) {
        Stack s = new Stack(p.length);
        boolean[] visited = new boolean[p.length];
        int[][] adjMST = createAdjFromParentMatrix();
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        s.push(0);
        int v;
        while (!s.isEmpty()) {
            v = s.pop();
            if (!visited[v]) {
                System.out.println(vertices[v]);
                visited[v] = true;
                for (int i = 0; i < p.length; i++) {
                    if (adjMST[v][i] == 1) {
                        s.push(i);
                    }
                }
            }
        }
    }

    /**
     * Does computed the shortest approximate Hamiltonian walk and its distance
     * on the graph G.
     * 
     * @param G 
     * 
     * PreCondition: G should not be null.
     * PostCondition: The shortest Hamiltonian path will be stored in this.shortestPath;
     * The distance of the path will be stored in this.minPathLength.
     * 
     * Complexity: Theta(V+E), V = number of vertices, E = number of edges.
     */
    public void hamilonianCycle(GraphModel G) {
        Stack s = new Stack(p.length);
        boolean[] visited = new boolean[p.length];
        StringBuilder minPath = new StringBuilder("");
        int[][] adjMST = createAdjFromParentMatrix();
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        s.push(0);
        int v;
        int prev = 0; //starting index
        double cycleLength = 0.0;
        while (!s.isEmpty()) {
            v = s.pop();
            if (!visited[v]) {
                if (v != prev) {
                    cycleLength += G.getAdj()[v][prev];
                    prev = v;
                }
                //System.out.println(v);
                minPath.append(v).append(",");
                visited[v] = true;
                for (int i = 0; i < p.length; i++) {
                    if (adjMST[v][i] == 1) {
                        s.push(i);
                    }
                }
            }
        }
        minPath.deleteCharAt(minPath.length() - 1);
        minPath.append(",").append(minPath.charAt(0));
        cycleLength += G.getAdj()[prev][0];
        minPathLength = cycleLength;
        shortestPath = minPath.toString();
    }
    
    /**
     * Creates an Adjacency matrix to represent the paths in the MST.
     * @return Integer[][]: Adjacency Matrix
     * 
     * PreConditions: The MST should exist, i.e. the p[] matrix should not be empty.
     * PostConditions: An Adjacency matrix representing the MST is computed and returned.
     */
    private int[][] createAdjFromParentMatrix() {
        int[][] adjMatrix = new int[p.length][p.length];
        for (int i = 0; i < p.length; i++) {
            if (p[i] != -1) {
                adjMatrix[p[i]][i] = 1;
            }
        }
        return adjMatrix;
    }

    public double[] getKey() {
        return key;
    }

    public void setKey(double[] key) {
        this.key = key;
    }

    public int[] getP() {
        return p;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public double getKeyIndex(int index) {
        return this.key[index];
    }

    public int getParentIndex(int index) {
        return this.p[index];
    }

    public void setKeyIndex(int index, double value) {
        this.key[index] = value;
    }

    public void setParentIndex(int index, int value) {
        this.p[index] = value;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public double getMinPathLength() {
        return minPathLength;
    }
    

}
