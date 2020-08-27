import edu.duke.*;

public class PerimeterRunner {
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0;
        Point prevPt = s.getLastPoint();
        
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    public int getNumPoints (Shape s){
        int amountOfPoints = 0;
        
        for(Point currPt: s.getPoints()){
            amountOfPoints = amountOfPoints + 1;
        }
        return amountOfPoints;
    }
    
    public double getAverageLength (Shape s){
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
    
    public double getLargestSide (Shape s){
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
    
    public double getLargestX (Shape s){
        int largestX = -1000;
        
        for(Point currPt: s.getPoints()){
            if(largestX < currPt.getX()){
                largestX = currPt.getX();
            }
        }
        return largestX;
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
    
    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }  
    
}