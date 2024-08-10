package pathFinder;

public class Point {
    
    //store the first instance of a wall in each cardinal direction from a certain point
    private static  int startColor, endColor, wallColor;
    public int[] northWall, southWall, westWall, eastWall;
    private int[] coords;//[]{x,y}
    private static int[][] RGBMap; 
    public static int[] end = new int[]{-1,-1};


    public Point(int x, int y){
        if(x < 0 || x> RGBMap[0].length || y < 0 || y>RGBMap.length){
            System.out.println("x:"+x+ " y:"+y);
            throw new IndexOutOfBoundsException("x:"+coords[0]+ " y:"+coords[1]);
        }
        
        coords = new int[2];
        this.coords[0]= x;
        this.coords[1]= y;
        setWalls();
    }

    public Point(int[] coords){
        //this point takes less space, it doesnt find walls automatically
        if(coords[0] < 0 || coords[0]> RGBMap[0].length || coords[1] < 0 || coords[1] >RGBMap.length){
            System.out.println("x:"+coords[0]+ " y:"+coords[1]);
            throw new IndexOutOfBoundsException("x:"+coords[0]+ " y:"+coords[1]);
        }
        
        this.coords = new int[2];
        this.coords[0]= coords[0];
        this.coords[1]= coords[1];
        
    }

    public Boolean isEqual(Object o){
        if(o == null){
            return false;
        }
        
        if(o.getClass() != this.getClass()){
            return false;
        }

        Point obj = (Point) o;

        return coordsIsEqual(this.coords, obj.coords );
    }

    public String toString(){
        return "(" + coords[0] + ","+coords[1]+")"; 
    }

    public Point(int x, int y,  int startColor, int endColor,int wallColor){
        //this constructor only need to be called once to set the colors
        coords= new int[2];
        this.startColor = startColor;
        this.endColor= endColor;
        this.wallColor = wallColor;

        this.coords[0]= x;
        this.coords[1] =y;
        setWalls();
    }
    
    private void setWalls(){
        //from this Point get the walls in each cardinal direction


        //call the method lazer for every direction and set the walls
        
        int[] temp = lazer(-1,0);//east

        if( temp[2] == -1){
            eastWall = new int[]{-1,-1};
        }
        else if (temp[2] == 0){
            eastWall = new int[]{temp[0],temp[1]};
        }
        else{
            end = new int[]{temp[0], temp[1]}; // the coords where the end was found is in this variable
            eastWall = new int[]{-2,-2};//this means that the eastWall is the end color
        }



        temp = lazer(1,0);//west

        if( temp[2] == -1){
            westWall = new int[]{-1,-1};
        }
        else if (temp[2] == 0){
            westWall = new int[]{temp[0],temp[1]};
        }
        else{
            end = new int[]{temp[0], temp[1]};
            westWall = new int[]{-2,-2};
        }
         


        temp = lazer(0,1);//south

        if( temp[2] == -1){
            southWall = new int[]{-1,-1};
        }
        else if (temp[2] == 0){
            southWall = new int[]{temp[0],temp[1]};
        }
        else{
            end = new int[]{temp[0], temp[1]};
            southWall = new int[]{-2,-2};
        }
        


        temp = lazer(0,-1);//north

        if( temp[2] == -1){
            northWall = new int[]{-1,-1};
        }
        else if (temp[2] == 0){
            northWall = new int[]{temp[0],temp[1]};
        }
        else{
            end = new int[]{temp[0], temp[1]};
            northWall = new int[]{-2,-2};
        }
    }

    private int[] lazer(int xStep, int yStep){
        //this method shoots a lazer from coords until it reaches a wall 
        //Parameters give where the laze will head towards
        //if int[3] = 0 then the lazer reached the wall, int[3] = 1 means it reached endColor
        
        int[] location = new int[3];
        

        int x = this.coords[0];
        int y = this.coords[1];


        //A while loop that that increases x and y by xStep and yStep until the color of the pixel 
        //Is one of the colors we are searching for 


        //RGBMap[y][x][0] == wallColor[0] && RGBMap[y][x][1] == wallColor[1] && RGBMap[y][x][2] == wallColor[2]
        //RGBMap[y][x][0] == endColor[0] && RGBMap[y][x][1] == endColor[1] && RGBMap[y][x][2] == endColor[2]
        while(!( RGBMap[y][x] == wallColor)  && !( RGBMap[y][x]== endColor)    ){
                
            if(y+1 >= RGBMap.length || x+1>= RGBMap[0].length || x <= 0 || y <= 0){return new int[]{-1,-1,-1};}//out of bounds

            x += xStep;
            y += yStep;
        }
        
        //define if we found a wall or the end color
        
        if(RGBMap[y][x] == wallColor){
            location[2] = 0;
        }
        else{
            location[2] = 1;
        }

        location[0] = x;
        location[1] = y;

        return location;
    }

    private boolean isInSamePath(Point p){
        //This method check if two points that are on the same x or y axis have anything obstructing in between them
        
        
      
        return !(!(coordsIsEqual(this.northWall, p.northWall)  && coordsIsEqual(this.southWall, p.southWall)) &&
                !(coordsIsEqual(this.westWall, p.westWall) && coordsIsEqual(this.eastWall, p.eastWall)));
        
    }

    public static boolean isInSamePath(Point p1, Point p2){
        //isInSamePath(new Point(450, 300), new Point(450, 320));          True
        //isInSamePath(new Point(450, 300), new Point(455, 300));           True
        //isInSamePath(new Point(450, 300), new Point(600, 300));   False
        //isInSamePath(new Point(450, 300), new Point(450, 350));    False  
        return p1.isInSamePath(p2);
    }
    






    public static boolean coordsIsEqual(int[] coord1, int[] coord2){
            // only to be used with int[2] or else it will crash
        return coord1[0] == coord2[0] && coord1[1] == coord2[1] && coord1[0] == coord2[0];
    }


    public static boolean RGBIsEqual(int[] RGB1, int[]RGB2){
        // only to be used with int[3] or else it will crash
        return RGB1[0] == RGB2[0] && RGB1[1] == RGB2[1] && RGB1[2] == RGB2[2];
    }

    public int[] getNorthWall(){
        return northWall;
    }

    public int[] getSouthWall(){
        return southWall;
    }

    public int[] getWestWall(){
        return westWall;
    }

    public int[] getEastWall(){
        return eastWall;
    }


    public static void setWallColor(int e){
        wallColor = e;
    }

    public static void setStartColor(int e){
        startColor = e;
    }

    public static void setEndColor(int e){
        endColor = e;
    }

    public static void setRGBMap(int[][] RGB){
        RGBMap = RGB;
    }

   public int[] getCoords(){
    return coords;
   } 

   public static void main(String args[]){
    int[][] RGBMap = ImageReader.getrgbColor("path.png");
    Point.setRGBMap(RGBMap);
    

    Point a  =new Point(100, 100);
   
    Point b = new Point(20, 100);

    Point c = new Point(100,100);

    Point d = null;

    System.out.println(a.isEqual(c));//true
    System.out.println(a.isEqual(b));//false
    System.out.println(a.isEqual(d));//false


}
}

