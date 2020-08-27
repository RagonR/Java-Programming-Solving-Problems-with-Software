import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int amountOfPoints = 0;
        
        for(Point currPt: s.getPoints()){
            amountOfPoints = amountOfPoints + 1;
        }
        return amountOfPoints;
    }

    public double getAverageLength(Shape s) {
        int amountOfPoints = 0; 
        double totalPerim = 0;
        Point prevPt = s.getLastPoint();
        
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            prevPt = currPt;
            totalPerim = totalPerim + currDist;            
            amountOfPoints = amountOfPoints + 1;
        }
        return totalPerim/amountOfPoints;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            prevPt = currPt;
            if(largestSide < currDist){
                largestSide = currDist;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        int largestX = -1000;
        
        for(Point currPt: s.getPoints()){
            if(largestX < currPt.getX()){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
            if (largestPerimeter < getPerimeter(s)){
                largestPerimeter = getPerimeter(s);
            } 
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        File LargestPenFile = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
            if (largestPerimeter < getPerimeter(s)){
                largestPerimeter = getPerimeter(s);
                LargestPenFile = f;
            } 
        }
        return LargestPenFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double avarage = getAverageLength(s);
        double largest = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Points in shape = " + points);
        System.out.println("Avarage side length = " + avarage);  
        System.out.println("Largest side length = " + largest);   
        System.out.println("Largest X = " + largestX);  
    }
    
    public void testPerimeterMultipleFiles() {
         double LargestPerimeter = getLargestPerimeterMultipleFiles();
         String LargestPenFile = getFileWithLargestPerimeter();
         System.out.println("Largest Perimeter = " + LargestPerimeter);
         System.out.println("File name = " + LargestPenFile);
    }

    public void testFileWithLargestPerimeter() {
         String LargestPenFile = getFileWithLargestPerimeter();
         System.out.println("File name = " + LargestPenFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
    }
}
