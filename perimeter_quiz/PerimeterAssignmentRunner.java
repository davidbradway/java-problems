import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Count the number of points in a shape and return it
        int total = 0;
        for (Point p : s.getPoints()){
            total = total + 1;
        }
        return total;
    }

    public double getAverageLength(Shape s) {
        // Return the calculated average of all the sides' lengths
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Return the longest side in the Shape S.
        
        // Start with largestSide = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // largestSide is the answer
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Return the largest x value over all the points in the Shape s.
                
        // Start with largestX = 0
        double largestX = s.getLastPoint().getX();
        
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }
        }
        // largestSide is the answer
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Return the the largest perimeter over all the shapes
        // in the files you have selected.

        // This method creates a DirectoryResource 
        // (so you can select multiple files) 
        DirectoryResource dr = new DirectoryResource();

        double largestPerimeter = 0;
        
        // Iterate over these files. 
        for (File f : dr.selectedFiles()) {
            
            // For each File f, it converts the file into a FileResource
            FileResource fr = new FileResource(f);
        
            // Then it creates a Shape from the FileResource
            Shape s = new Shape(fr);

            // Calculate the shape's perimeter.
            double length = getPerimeter(s);

            if (length > largestPerimeter) {
                largestPerimeter = length;
            }
        }        
        return largestPerimeter;
    }

    public File getFileWithLargestPerimeter() {
        // Return the File that has the largest perimeter, 
        // so it has return type File

        // This method creates a DirectoryResource 
        // (so you can select multiple files) 
        DirectoryResource dr = new DirectoryResource();

        double largestPerimeter = 0;
        File largestPerimeterF = null;
        
        // Iterate over these files. 
        for (File f : dr.selectedFiles()) {
            
            // For each File f, it converts the file into a FileResource
            FileResource fr = new FileResource(f);
        
            // Then it creates a Shape from the FileResource
            Shape s = new Shape(fr);

            // Calculate the shape's perimeter.
            double length = getPerimeter(s);

            if (length > largestPerimeter) {
                largestPerimeter = length;
                largestPerimeterF = f;
            }
        }        
        return largestPerimeterF;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int total = getNumPoints(s);
        System.out.println("numPoints = " + total);
        
        double avg = getAverageLength(s);
        System.out.println("AverageLength = " + avg);
        
        double side = getLargestSide(s);
        System.out.println("LargestSide = " + side);
        
        double x = getLargestX(s);
        System.out.println("LargestX = " + x);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largestPerimeter = "+ largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        File test = getFileWithLargestPerimeter();
        System.out.println(test.getName());
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

    // This method prints names of all files in a chosen folder that 
    // you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
