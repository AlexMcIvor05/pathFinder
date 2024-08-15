package pathFinder;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;

public class ImageReader{



    public ImageReader(String file ){

    }
    /*
    public static void editImage(String file, Line lines) {
    
    	editImage(file, new Line[]{lines})
    }
    */
    public static void editImage(String file, Line line) {
        
        BufferedImage img =  new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);;//placeholder 

        try{
            img = ImageIO.read(new File(file));
        }
        catch(IOException e){
            System.out.println("error reading file");
        }
       
        

        Graphics2D g = img.createGraphics(); // Create a graphics object to modify the buffered image

        g.setColor(Color.BLUE); // set what color you want to draw in
        
        g.drawLine(line.point1.getCoords()[0], line.point1.getCoords()[1], line.point2.getCoords()[0], line.point2.getCoords()[1] );
        

        g.dispose();
        


        File imgfile = new File("result.png"); //Modify pathname here to change file name
        
        try{
            ImageIO.write(img, "png", imgfile);}
            catch(Exception e){
                System.out.println("failed making the new file");
            }
        
    }
    public static void editWholeImage(int[][] RGBMap) {
    	
    	int red, green, blue;
    	int i = 0;
        int[] data = new int[RGBMap.length * RGBMap[2].length];
        
        
        for(int y = 0; y < RGBMap.length; y++) {
        	for(int x = 0; x < RGBMap[1].length; x++) {
        		//blue+green*1000+red*1000000;
        		red = RGBMap[y][x] /1000000;
        		green = (RGBMap[y][x]/1000) % 1000;
        		blue = RGBMap[y][x] % 1000;
        		data[i++] = (red << 16) | (green << 8 ) | blue;
        		
        	}
        }
        
        
        
        BufferedImage image = new BufferedImage(RGBMap[1].length, RGBMap.length, BufferedImage.TYPE_INT_RGB);
        
        image.setRGB(0, 0, RGBMap[1].length,  RGBMap.length, data, 0,RGBMap[1].length);
        try {
        ImageIO.write(image, "png", new File("simplified.png")); 
        }
        catch(Exception e) {
        	System.out.print("Method editWholeImage failed");
        }
        
    }
    
    
    public static void editImage(String file, Line[] lines , Color color) {
        
        BufferedImage img =  new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);;//placeholder 

        try{
            img = ImageIO.read(new File(file));
        }
        catch(IOException e){
            System.out.println("error reading file");
        }
       
        

        Graphics2D g = img.createGraphics(); // Create a graphics object to modify the buffered image

        g.setColor(color); // set what color you want to draw in
        
       
        for(int i = 0; i < lines.length; i++){  //draw all the lines
            g.drawLine(lines[i].point1.getCoords()[0], lines[i].point1.getCoords()[1], lines[i].point2.getCoords()[0], lines[i].point2.getCoords()[1] );
            
            }

        g.dispose();
        


        File imgfile = new File("result.png"); //Modify pathname here to change file name
        
        try{
            ImageIO.write(img, "png", imgfile);}
            catch(Exception e){
                System.out.println("failed making the new file");
            }
        
    }


    public static int[][] getrgbColor(String file){
    	BufferedImage image = null;

        try{
            image = ImageIO.read(ImageIO.createImageInputStream(new FileInputStream(file)));
        } 
        catch(Exception IOException){
            System.out.println("Invalid file input \" " +file+" \", remember to add the file type. I.E \".png\"");
            
            
        }

            int width   = image.getWidth();
            int height  = image.getHeight();
            


            int[][] result = new int[height][width];

            for(int i = 0; i < width ; i++){
                for(int j = 0; j < height; j++){
                    
                    result[j][i] = convertTorgb( image.getRGB(i, j) );

                }
            }
            

           

            return result;	
    }
    
    
    
    public static int[][][] getRGBColor(String file){
        BufferedImage image = null;

    try{
        image = ImageIO.read(ImageIO.createImageInputStream(new FileInputStream(file)));
    } 
    catch(Exception IOException){
        System.out.println("Invalid file input \" " +file+" \", remember to add the file type. I.E \".png\"");
        
        
    }

        int width   = image.getWidth();
        int height  = image.getHeight();
        


        int[][][] result = new int[height][width][3];

        for(int i = 0; i < width ; i++){
            for(int j = 0; j < height; j++){
                
                result[j][i] = convertToRGB(image.getRGB(i, j));

            }
        }
        

       

        return result;
    }
    
   
    
    public static int[] convertToRGB(int RGB){
        //return RGB in usable int with int[0] red, int[1] green, int[2] blue
        int[] result = new int[3];
        
        int blue = RGB & 0xff;
        int green = (RGB & 0xff00) >> 8;
        int red = (RGB & 0xff0000) >> 16;

        result[0] = red;
        result[1] = green;/// make it an int instead
        result[2] = blue;

        return result;
    }
    
    public static int convertTorgb(int ARGB) {
    	
    	 int blue = ARGB & 0xff;
         int green = (ARGB & 0xff00) >> 8;
         int red = (ARGB & 0xff0000) >> 16;
         
         return blue+green*1000+red*1000000;
         
    }

        
    public static void main(String args[]){
        try {
        int[][] RGBMap = ImageReader.getrgbColor("result.png");
    	
        editWholeImage(RGBMap);
        }
        catch(Exception e) {
        	System.out.println("failed");
        }
          
    }
    

}
