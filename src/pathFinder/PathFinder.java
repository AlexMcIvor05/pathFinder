package pathFinder;

import java.util.*;

public class PathFinder {
    private String image, startColor, endColor, wallColor;

    private String[] pixelHex;

    private HashMap<Integer , Point> storage = new HashMap<Integer, Point>();
    
    public PathFinder(String image ){
        this.image = image;
    }

    public PathFinder(String image, String startColor, String endColor, String wallColor){
        this.image = image;
        this.startColor = startColor;
        this.endColor = endColor;
        this.wallColor = wallColor;
    }


    public void storage(Point point){
        //Store points or lines in a dictionnary with the key being its point on the
        //Y-axis 
        /*
         dictionnary{} *** Use y instead because images are shorter on their y-axis
        that stores all x coords as key so that you can compare the walls of one point on
        the same axe I.E. you can compare (10,12) with (10,30) to see if the are located
        in the same path
         */

        int[] coords = point.getCoords();
        
        storage.put(coords[1], point);




    	} 
    
	    public static void main( String args[]) {
	    	int[] test2 = new int[2000000];
	    	for(int i = 0 ; i < 2000000; i++) {
	    		test2[i]=123456789;
	    	
	    		
	    	}
	    	
	    	
	    	
	    	
	    }
	}
