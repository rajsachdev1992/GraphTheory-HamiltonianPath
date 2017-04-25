package rsachde1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a utility class to create KML files for path of crimes.
 */
public class KMLUtility {

    private final GraphModel G; //Graph for which KML file will be generated.

    public KMLUtility(GraphModel G) {
        this.G = G;
    }

    public static final String TOP = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
            + "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n"
            + "<Document>\n"
            + "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n"
            + "<LineStyle>\n"
            + "<color>73FF0000</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>\n"
            + "<Style id=\"style5\">\n"
            + "<LineStyle>\n"
            + "<color>507800F0</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>";
    public static final String PLACEMARK_ENTRY = "<Placemark>\n"
            + "<name>{1}</name>\n"
            + "<description>{1}</description>\n"
            + "<styleUrl>{3}</styleUrl>\n"
            + "<LineString>\n"
            + "<tessellate>1</tessellate>\n"
            + "<coordinates>\n"
            + "{2}"
            + "</coordinates>\n"
            + "</LineString>\n"
            + "</Placemark>";
    public static final String BOTTOM = "</Document>\n"
            + "</kml>";

    /**
     * @param path1: TSP Path
     * @param path2: Optimal path
     * @return String which contains all the content of the KML file. 
     * Complexity: Theta(n) 
     * Precondition: path1 or path2 should not be null. They should be valid
     * comma seperated path entries.
     * Postcondition: A string which contains the content of KML file will be returned.
     * 
     * Complexity: Theta(n)
     */
    public String createKMLContent(String path1, String path2) {
        CrimeRecord record;
        StringBuilder MID = new StringBuilder("");
        MID.append(PLACEMARK_ENTRY.replace("{1}", "TSP path")
                .replace("{2}", createCoordinateEntry(path1))
                .replace("{3}", "#style6"));
        MID.append(PLACEMARK_ENTRY.replace("{1}", "Optimal path")
                .replace("{2}", createCoordinateEntry(path2))
                .replace("{3}", "#style5"));
        return TOP + MID.toString() + BOTTOM;
    }

    /**
     * Creates the coordinate entries for a path in the placemark of the KML file.
     * Helper method for createKMLContent() method.
     * @param path
     * @return String: coordinates to be inserted in the KML file.
     * 
     * PreConditions: path should not be null and should be a valid comma 
     * separated path entry.
     * PostConditions: A String containing coordinate entry for the path will be returned.
     */
    private String createCoordinateEntry(String path) {
        CrimeRecord c;
        String[] pathArray = path.split(",");
        StringBuilder s = new StringBuilder("");
        for (String v : pathArray) {
            c = G.getV()[Integer.parseInt(v)];
            s.append(c.getLongitude()).append(",").append(c.getLatitude()).append("\n");
        }
        return s.toString();
    }

    /**
     * This method creates the KML file from the content entered in the
     * argument.
     *
     * @param path1
     * @param path2 
     * PreCondition: content should not be null. 
     * PostCondition: A KML file will be generated in the project folder.
     */
    public void createKMLFile(String path1, String path2) {
        String content = createKMLContent(path1, path2);
        try (PrintWriter fileOut = new PrintWriter("PGHCrimes.kml", "UTF-8")) {
            fileOut.print(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(KMLUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
