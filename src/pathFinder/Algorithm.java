package pathFinder;

import java.awt.Color;
import java.util.*;
import java.util.Stack;

public class Algorithm {
    
	
	

	
	public static void simplify(int wallColor, int endColor, int x, int y) {
		//whenever the blue line is about to change directions make a lazer that checks if there is another unobstructed blue line in the path it was going
		//Turn turn the line magenta so that we dont go back on that same line later, also check that it doesnt go back to any of the lines currently on the list
		//this method will be called right after hugLeft or it wont work
		
		int[][] RGBMap = ImageReader.getrgbColor("result.png");
		System.out.println("Simplifying");
		
		
		
		
		
		
		/*
		 mark every explored index with -1, marks dead ends with -2
		 
		 
		 priority is to look for and adjacent blue pixel, if there are no more the second priority is adjacent pixels marked -1
		 
		 every time you have to go on -1's, store all the index's you went on until you find  a color, then turn them all into -2 except for the most recent
		 -1
		 
		 once there is no where left to go the algorithm ends, the solution is all the -1's left
		 
		 */
		
		RGBMap[y][x] = -1;
	
		while(true) {//while no blue or -1
			
			if(RGBMap[y][x+1] == endColor) {
				break;
			}
			
			else if(RGBMap[y][x-1] == endColor) {
				break;
			}
			else if(RGBMap[y+1][x] == endColor) {
				break;
			}
			else if(RGBMap[y-1][x] == endColor) {
				break;
			}
			
			
			else if(RGBMap[y][x+1] == 255) {//blue
				
				RGBMap[y][x] = -1;
				x+=1;
				
			}
			else if(RGBMap[y][x-1] == 255) {//blue
				
				RGBMap[y][x] = -1;
				x-=1;
				
			}
			else if(RGBMap[y-1][x] == 255) {//blue
				
				RGBMap[y][x] = -1;
				y-=1;
				
			}
			else if(RGBMap[y+1][x] == 255) {//blue
				
				
				RGBMap[y][x] = -1;
				y+=1;
				
			}
			
			
			else if(RGBMap[y][x+1] == -1) {
				RGBMap[y][x] = -2;
				
				x+=1;
			}
			
			else if(RGBMap[y][x-1] == -1) {
				RGBMap[y][x] = -2;
				
				
				x-=1;
			}
			else if(RGBMap[y+1][x] == -1) {
				RGBMap[y][x] = -2;
				
				y+=1;
			}
			else if(RGBMap[y-1][x] == -1) {
				
				RGBMap[y][x] = -2;
				
				y-=1;
			}
			else {
				break;
			}
			
		}
		
		
		
		for(int i = 0; i < RGBMap.length; i++) {
			for(int z =0; z < RGBMap[1].length;z++) {
				
				if(RGBMap[i][z]== -2) {
					RGBMap[i][z] = 255255255;
				}
				
				else if(RGBMap[i][z] == 255) {
					RGBMap[i][z] = 255255255;
				}
				
				
				
			}
		}
		for(int i = 0; i < RGBMap.length; i++) {
			for(int z =0; z < RGBMap[1].length;z++) {
			if(RGBMap[i][z] == -1) {
				RGBMap[i][z] = 255; 
				}
			}}
			
			
			ImageReader.editWholeImage(RGBMap);
			System.out.println("done");
			
		}
		
		
		
	
	
	
    public static void hugLeft(String file, int startingColor, int endColor, int wallColor){
    	
    	int[][] RGBMap = ImageReader.getrgbColor(file);
    	
        Point.setRGBMap(RGBMap);
        Point.setStartColor(startingColor);
            
            
        Point.setEndColor(endColor);
    
        Point.setWallColor(wallColor);

        ImageReader.editImage(file, new Line[] {new Line(new Point(1, 1), new Point(1, 1))},  Color.BLACK);
        
        Line tempLine;
        int[] start = findStart(RGBMap, startingColor);
        System.out.println("found start");
        Point current = new Point(start[0],start[1]);
        
        //go to west wall
        int x = current.getWestWall()[0]-1;
        int y = current.getWestWall()[1];
        int xCopy= x;
        int yCopy = y;
        System.out.println("Starting coords: ("+x+","+y+")" );
        current = new Point( new int[]{current.getWestWall()[0]-1, current.getWestWall()[1]} );
        Point next;
        
        
        
        int clk = 0;
        Line[] result = new Line[2];
        List<Line> list = new ArrayList<Line> ();
        int oldDirection = 2;
        int direction =2;//direction: 0 = north    direction: 1= east direction: 2 = south    direction: 3 = west
        //go west to the nearest wall

        //if going up look left
        //if going left look down
        //if going down look right
        //if going right look left

        while(true){
          
            if(direction ==0){//north
                
                
                while(true){
                	//loop is finished if we find the end
                	if( RGBMap[y][x] == endColor ){
                        break;
                    }
                	
                	//if you are on a wall you have to move back a square and change directions
                    if(wallColor == RGBMap[y][x]){
                        direction  = 1;
                        
                        y = y+1;//move back one because you are on a wall

                        break;
                    }
                    
                    //if there is no longer a wall change directions
                    if(wallColor != RGBMap[y][x-1]){
                        direction = 3;
                        x = x-1;
                        break; 
                    }

                    y = y-1;
                }

                
                    

            }
            
            else if(direction == 1){//east
                while(true){
                	if( RGBMap[y][x] == endColor ){
                        break;
                    }
                	
                    if(wallColor == RGBMap[y][x]){
                        direction  = 2;
                        
                        x = x-1;//move back one because you are on a wall

                        break;
                    }
                    if(wallColor != RGBMap[y-1][x]){
                        direction = 0;
                        y= y-1;
                        break; 
                    }

                    x = x+1;
                }
            }
            
            else if(direction == 2){//south
                while(true){
                	if( RGBMap[y][x] == endColor ){
                        break;
                    }
                	
                    if(wallColor == RGBMap[y][x]){
                    	
                        direction  = 3;
                        
                        y = y-1;//move back one because you are on a wall

                        break;
                    }
                    if(wallColor != RGBMap[y][x+1]){
                    	
                        direction = 1;
                        x= x+1;
                        break; 
                    }

                    y = y+1;
                }
            }
            
            else{//west
                while(true){
                	if( RGBMap[y][x] == endColor ){
                        break;
                    }
                	
                    if(wallColor == RGBMap[y][x]){
                        direction  = 0;
                        
                        x = x+1;//move back one because you are on a wall

                        break;
                    }
                    if(wallColor != RGBMap[y+1][x]){
                        direction = 2;
                        y = y+1;
                        break; 
                    }

                    x = x-1;
                }
            }
            
            
            
            
            next = new Point( new int[]{x,y});
            
          //move the line based on its direction for visual clarity
            if(oldDirection == 0){
            	tempLine = new Line(new Point(current.getCoords()[0], current.getCoords()[1]), new Point(current.getCoords()[0], next.getCoords()[1]) );

                list.add( tempLine);
            }
            else if(oldDirection == 1){
            	tempLine = new Line(new Point(current.getCoords()[0], current.getCoords()[1]), new Point(next.getCoords()[0], current.getCoords()[1]));
                list.add( tempLine  );

            }
            else if(oldDirection == 2){
            	tempLine = new Line(new Point(current.getCoords()[0], current.getCoords()[1]), new Point(current.getCoords()[0], next.getCoords()[1])); 
                list.add( tempLine );

            }
            else{
            	tempLine = new Line(new Point(current.getCoords()[0], current.getCoords()[1]), new Point(next.getCoords()[0], current.getCoords()[1])) ; 
                list.add( tempLine);
            }
           
            
            current = null;
            
            current = next;
            
            next = null;

            oldDirection = direction;

            //check whether we found the end
            
            if( RGBMap[y][x] == endColor ){
                break;
            }
            
            
            
            //clock to empty list to save memory and make algo more efficient
            clk +=1;
            if(clk == 50000) {//clk can be turned up or down based off available memory
            	result = new Line[list.size()];

                for(int i = 0; i < list.size(); i++){
                    result[i]= list.get(i);
                }
                ImageReader.editImage("result.png", result, Color.BLUE);
                list.clear();
                
                
                
                result = null;
                
                clk = 0;
                }
            
            
            
        }
        
        System.out.println("done");
        
        
        //empty list
        if(list.size() > 0) {
        	result = new Line[list.size()];

            for(int i = 0; i < list.size(); i++){
                result[i]= list.get(i);
            }
            
            //make sure that the line doesn't overlap with the end
            if(oldDirection == 0) {
            result[result.length-1]= new Line( result[result.length-1].point1 , 
            	new Point( result[result.length-1].point2.getCoords()[0] , result[result.length-1].point2.getCoords()[1]+1  )) ;
            }
            else if(oldDirection == 2) {
                result[result.length-1]= new Line( result[result.length-1].point1 , 
                new Point( result[result.length-1].point2.getCoords()[0] , result[result.length-1].point2.getCoords()[1]-1  )) ;
            }
            else if(oldDirection == 1) {
                result[result.length-1]= new Line( result[result.length-1].point1 , 
                new Point( result[result.length-1].point2.getCoords()[0] -1, result[result.length-1].point2.getCoords()[1]  )) ;
            }
            else if(oldDirection == 1) {
                result[result.length-1]= new Line( result[result.length-1].point1 , 
                new Point( result[result.length-1].point2.getCoords()[0] +1, result[result.length-1].point2.getCoords()[1]  )) ;
            }
            
            
            
            
            ImageReader.editImage("result.png", result, Color.BLUE);
            list.clear();
            result = null;
        }
        simplify(0 , endColor, xCopy, yCopy);

    }
    


    /*Algo 1
    
    wallHug

    algo that sticks to a wall until it hits the finish. 
    Have a way to detect if we end up going over the same spot twice. 
    This algorithm is simple and will not be sufficient for harder paths.


    *If this algo doesnt work after a certain amount of time, switch from Algo 1
    to Algo 2*
    */

   
        /*Algo 2

         lazer. shoots a line, find walls, to see if you are going backwards, 
         find the line that you think it is in the same area in and see if there 
         is a wall in between the two points  
         
         problem : how do you figure out which two lines would be the same area



         */
/*
    public static ? lazer(){



    }
 */


    public static int[] findStart(int[][] RGBMap, int startingColor ){
        //finds the first pixel on the image that coresponds to the starting color we want

        int y = 0;
        int x = 0;

        while( !(RGBMap[y][x] == startingColor) ){
            x += 1;
            
            if(x == RGBMap[0].length){
                x=0;
                y+=1;
            }

        }

        return new int[]{x, y};
    }


   public static void main(String args[]){
    
    /* 
    for(int y = 155; y < RGBMap.length;y++){
        System.out.println(" ");
        for(int x = 0; x < RGBMap[0].length; x++){
            System.out.println(RGBMap[y][x][0]+" "+ RGBMap[y][x][1]+" "+ RGBMap[y][x][2]+" ");
        }
    }
     */

     



   


    long startTime = System.nanoTime();
    hugLeft("./src/pathFinder/path.png", 255000000, 128000, 0 );
    long endTime = System.nanoTime();
    
    System.out.println((endTime - startTime) / 100000000);
    /*
    startTime = System.nanoTime();
    hugLeft2("./src/pathFinder/path.png", new int[]{255, 0, 0}, new int[]{0,128,0}, new int[]{0,0,0} );
    endTime = System.nanoTime();
    System.out.println("algo2");
    System.out.println((endTime - startTime) / 100000000);
   */
   }

}

