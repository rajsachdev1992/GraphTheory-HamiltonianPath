package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * 
 * This class is the driver for Task 3.
 */
public class Task3Driver {

    public static void main(String args[]) {
        //for TSP path
        GraphModel gm = new GraphModel();
        gm.getUserInputAndCreateGraph();
        PrimsAlgorithm pm = new PrimsAlgorithm(gm);
        MST mst = pm.generateMST();
        mst.hamilonianCycle(gm);

        //for Optimal path
        BruteForcePathOptimizer optimizer = new BruteForcePathOptimizer(gm);
        optimizer.findShortestHamiltonianPath();
        
        //create KML file
        KMLUtility kmlUtil = new KMLUtility(gm);
        kmlUtil.createKMLFile(mst.getShortestPath(), optimizer.getShortestPath());
        
        System.out.println("Created PGHCrimes.kml. Check project folder");
    }

}
