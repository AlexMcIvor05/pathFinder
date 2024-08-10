package pathFinder;

public class Line {
    //Its a line
    int slope;// m  -> y = mx + b
    
    Point point1;
    Point point2;

    public Line(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;

    }

    private void getSteps(){

    }
    public String toString(){
        return "line["+point1+","+point2+"]";
    }

    public boolean isEqual(Object o){
        if(o == null){
            return false;
        }
        
        if(o.getClass() != this.getClass()){
            return false;
        }

        Line obj = (Line) o;

        return obj.point1.isEqual(this.point1) && obj.point2.isEqual(this.point2) ||
        obj.point1.isEqual(this.point2) && obj.point2.isEqual(this.point1);
    }

    private static int divisible(int a1, int a2){
        //recursive modulus to find the smallest numbers cant be negative
        if(a2 == 0 ){
            return a1;
        }
        if(a1 == 0){
            return a2;
        }
        if(a1 > a2){
            return divisible(a2,a1 % a2);
        }
        else{
            return divisible(a1,a2 % a1);
        }




    }

    private static int[] simplify(Line Line){
        // get xstep and ystep
        //with these two coords you can get all of the points which are on the line
       
        int xRange = Line.point1.getCoords()[0] -Line.point2.getCoords()[0];
        if(xRange < 0){
            xRange = -xRange;
        }
        int yRange = Line.point1.getCoords()[1] -Line.point2.getCoords()[1];
        if(yRange < 0){
            yRange = -yRange;
        }

        int divide = divisible(xRange, yRange);

        return new int[]{xRange/divide, yRange/divide};
       
    }



    public static boolean isInSamePath(Line line1,Line line2){

        // find a way to get a Point on the same axis on both lines
        
        //

        return true;
    }

    public static void main(String args[]){
        int[][][] RGBMap = ImageReader.getRGBColor("path.png");

        

        Line a = new Line( new Point(100, 30), new Point(200, 60)) ;
       System.out.println(simplify(a)[0]+" "+ simplify(a)[1]);
        
    }
}

