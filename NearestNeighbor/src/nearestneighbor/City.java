/**
 * Algorithms
 * TSP Solver - Nearest Neighbor version
 * City class
 */
package nearestneighbor;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 *
 * CLASS DESCRIPTION: City class with an id, x and y location
 * Added visited flags and setters and getters.
 * Added createMatrix() and doTours() methods 
 */
public class City 
{
   private double id;// City id. Read from input file.
   private double x, y; // x and y locations for each city. Read from file.
   private double[][] edges; //2D array for adjacency matrix.
   private boolean isVisited; //Visited flag
   DecimalFormat fmt = new DecimalFormat("0.####");//Formats output of doubles.

//Constructor. Accepts id, x, and y values.
public City (double id, double x, double y){
   this.id = id;
   this.x = x;
   this.y = y;
}
//Accessor. Returns int ID
public int getID(){
   return (int)id;
}
//Accessor. Returns double X value.
public double getX(){
   return x;
}
//Accessor. Returns double Y value.
public double getY(){
   return y;
}
//Set boolean flag to true if the city has been visited.
public void setVisited(boolean visited){
   isVisited = visited;
}
//Accessor. Returns boolean value of isVisited.
public boolean getVisited(){
   return isVisited;
}
//Accepts ArrayList and creates a 2D array (matrix) of edges between each city and every other city.
public void createMatrix(ArrayList<City> c)
{
   edges = new double[c.size()][c.size()]; //2D matrix of size n x n.
   int i, j;//loop control variables
   double xedge = 0.0, yedge = 0.0;//
   double distance = 0.0; 
   //Loop to build adjacency matrix of distances from each city to all cities.
   for (i = 0; i < edges.length; i++){
      for (j=0; j < edges[i].length; j++){
         if (i == j){
            edges[i][j] = Double.POSITIVE_INFINITY;
            
         }
         else{
            xedge = Math.pow(c.get(j).getX() - c.get(i).getX(), 2);
            yedge = Math.pow(c.get(j).getY() - c.get(i).getY(), 2);
            distance = Math.sqrt(xedge + yedge);
           
            edges[i][j] = distance;
            edges[j][i] = distance;
         }
      }
   }
}
//Conducts nearest neighbor tours starting at each city.
public void doTours(ArrayList<City> cities){
   
   int currentIndex = 0, nextCityIndex = 0;
   double shortestTour = Double.POSITIVE_INFINITY;//stores distance of shortest tour
   int[] tour = new int[cities.size()];//Array to store tour of city IDs.
   int[] bestTour = new int[tour.length];//Array to copy the shortest Tour from the tour[] array. Will be used to print tour
    int i, j, k, l; //loop control variables.
    
  //Outer loop that starts at each city 1 to n.
  for (i = 0; i < cities.size(); i++){
        double nearestDistance = 0.0, distance = 0.0;
        //Sets isVisited flags for all cities to false.
        for (l = 0; l < cities.size(); l++){
        cities.get(l).setVisited(false);
     }
     currentIndex = i;//Sets currentIndex to index of first city
     nearestDistance = Double.POSITIVE_INFINITY;//sets nearest distance to infinity.
     cities.get(i).setVisited(true);//sets isVisited flag of start city to true.
     
     //Inner loop. Traverses each row of the matrix.
     for (j = 0; j < cities.size(); j++){
           tour[j] = cities.get(currentIndex).getID();//Assigns city ID of current city to tour Array.
         //Inner loop. Compares current city against nearest distance.
         for (k = 0; k < cities.size(); k++){
            //If the current edge is not infinity (which indicates that the tour is at the current city, thus no distance)
            if ((edges[currentIndex][k] != Double.POSITIVE_INFINITY) && (cities.get(k).getVisited() != true)){
               //If the current distance is less that the nearestDistance, change nearestDistance to current city.
               if (edges[currentIndex][k] < nearestDistance){
               nearestDistance = edges[currentIndex][k];
               nextCityIndex = k;
               }
            }  
         }
           if (nearestDistance != Double.POSITIVE_INFINITY){
            distance += nearestDistance;//Append nearestDistance to distance of tour
            } 
        
         currentIndex = nextCityIndex;//set currentIndex as the index of the next city in the tour.
         cities.get(currentIndex).setVisited(true);//set isVisited flag of current city to true.
         nearestDistance = Double.POSITIVE_INFINITY;//Reset nearest distance to infinity for next tour through.
      }
   
     
     distance += edges[currentIndex][i];//Add distance back to start city to distance
     //If that distance is less than the shortest tour, assign distance to shortestTour.
      if (distance < shortestTour){
      shortestTour = distance;
      System.arraycopy(tour,0,bestTour,0,bestTour.length);
      }
   }
      //method call to print the tour cities and tour distance
      printTours(bestTour, shortestTour);
     
}

//Print tour array and total distance of the tour.
public void printTours(int[] t, double s){
   
     System.out.println("Shortest Tour Distance: " + fmt.format(s));
     for (int m = 0; m < t.length; m++){ 
     System.out.println(t[m]);
     }
     System.out.println(t[0] + "\n-1");

}

public String toString(){
   return("\nCity #: \t" + "X location\t" + "Y location\t\n" +
           getID() + "\t\t" + getX() + "\t\t" + getY());
   
}
}
