package rsachde1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raj.Sachdev
 *
 * This class models the Graph in which Crime Records are stored.
 */
public class GraphModel {

    private CrimeRecord V[]; //Array of vertices
    private double Adj[][];  //Adjacency Matrix
    public static final Scanner INPUT = new Scanner(System.in);

    public CrimeRecord[] getV() {
        return V;
    }

    public double[][] getAdj() {
        return Adj;
    }

    /**
     * This method accepts inputs from the user, reads the csv file, and stores
     * the crime records into a graph data structure.
     *
     * @return boolean: true if graph is successfully created.
     */
    public boolean getUserInputAndCreateGraph() {
        boolean isReadSuccess = true;
        System.out.println("Enter start index: ");
        int startIndex = Integer.parseInt(INPUT.next());
        System.out.println("Enter end index: ");
        int endIndex = Integer.parseInt(INPUT.next());
        int numVertices = endIndex - startIndex + 1;

        //initialize the V array
        this.V = new CrimeRecord[numVertices];

        try {
            Scanner fileScanner = new Scanner(new File("CrimeLatLonXY1990.csv"));

            //skip first entry and the entries upto startIndex
            fileScanner.nextLine();
            for (int i = 0; i < startIndex; i++) {
                if (fileScanner.hasNextLine()) {
                    fileScanner.nextLine();
                } else {
                    isReadSuccess = false;
                    break;
                }
            }

            //scan the entries upto endIndex and store them into an array
            if (isReadSuccess) {
                String[] lineTokens;
                CrimeRecord crimeRecord;
                for (int i = 0; i < numVertices; i++) {
                    if (fileScanner.hasNextLine()) {
                        //create crime record and store it in array
                        lineTokens = fileScanner.nextLine().split(",");
                        crimeRecord = new CrimeRecord(Double.parseDouble(lineTokens[0]), Double.parseDouble(lineTokens[1]),
                                Long.parseLong(lineTokens[2]), lineTokens[3], lineTokens[4], lineTokens[5], Long.parseLong(lineTokens[6]),
                                Double.parseDouble(lineTokens[7]), Double.parseDouble(lineTokens[8]));
                        V[i] = crimeRecord;
                    } else {
                        isReadSuccess = false;
                        break;
                    }
                }
            }

            //initialze adjacency matrix
            if (isReadSuccess) {
                initializeAdjMatrix();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isReadSuccess;
    }

    /**
     * This method creates the Adjacency matrix of the graph. 
     * 
     * PreConditions: The V matrix should be populated. 
     * PostConditions: An Adjacency matrix containing the distances 
     * in miles for each path is created.
     *
     * Complexity: Theta(n^2)
     */
    private void initializeAdjMatrix() {
        this.Adj = new double[V.length][V.length];
        for (int i = 0; i <= V.length; i++) {
            for (int j = i; j < V.length; j++) {
                if (V[i] == V[j]) {
                    Adj[i][j] = 0;
                } else {
                    Adj[i][j] = Adj[j][i] = 0.00018939 * computeDistance(V[i], V[j]);
                }
            }
        }
    }
    
    /**
     * computes the distance between two crime locations by using their coordinates.
     * @param a
     * @param b
     * @return double: distance between a and b
     * PreCondition: a and b should not be null.
     * PostCondition: Distance will be calculated between the 2 points using Pythagorean formula.
     */
    private double computeDistance(CrimeRecord a, CrimeRecord b) {
        double xDiff = Math.pow((a.getX() - b.getX()), 2);
        double yDiff = Math.pow((a.getY() - b.getY()), 2);
        return Math.sqrt(xDiff + yDiff);
    }
}
