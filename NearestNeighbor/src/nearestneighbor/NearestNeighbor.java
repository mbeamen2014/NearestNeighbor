/**
 * Algorithms
 * TSP Solver
 * Nearest Neighbor version
 *  
 */


package nearestneighbor;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class NearestNeighbor {
private static final int NUMFILES = 8;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
       //Loop iterates 8 times, once for each input file available for this project.  
       for (int j = 0; j < NUMFILES; j++){
          String fileName = intro();// intro() returns file name selected.
          Scanner fileScan = new Scanner(new File(fileName)).useDelimiter("\\s+");

          //Read in file and add city ID, X value, and Y value to ArrayList
          ArrayList<Double> list = new ArrayList<>();
          for (int i = 0; i<3; i++)
          {
           fileScan.nextLine(); //Skip text at beginning of file.
          }
          while (fileScan.hasNext())
          {
           try
            {
             if (fileScan.hasNextDouble())
             {
               list.add(fileScan.nextDouble());
             }
             else
             {
               fileScan.next();
             }
            }
            catch (NumberFormatException e)
            {
                     continue;
            }
         }  
      //ArrayList of City objects
      ArrayList<City> city = new ArrayList<>();
      //Traverse list ArrayList and pass values to City(id, x, y) to add new City objects to ArrayList.
      for (int i = 0; i < (list.size()); i += 3){
         city.add(new City(list.get(i), list.get(i+1), list.get(i+2)));
      }
      //Create the adjacency matrix containing all of the distances from city to city.
       city.get(0).createMatrix(city);
      //Run nearest neighbor tours to find the shortest tour
       city.get(0).doTours(city);
      }
    }
    
    //Prompt method. Returns name of input file.
    public static String intro(){
       
       String file = "";
       Scanner keyboard = new Scanner(System.in);
       System.out.println("Select an input file: \n" +
                           "\t1) a280.tsp\n" +
                           "\t2) berlin52.tsp\n" +
                           "\t3) eil51.tsp\n" +
                           "\t4) eil76.tsp\n" +
                           "\t5) kroA100.tsp\n" +
                           "\t6) kroC100.tsp\n" +
                           "\t7) kroD100.tsp\n" +
                           "\t8) rd100.tsp");
       int choice = keyboard.nextInt();
       switch (choice){
          case 1: file = "a280.tsp";
                  break;
          case 2: file = "berlin52.tsp";
                  break;
          case 3: file = "eil51.tsp";
                  break;
          case 4: file = "eil76.tsp";
                  break;
          case 5: file = "kroA100.tsp";
                  break;
          case 6: file = "kroC100.tsp";
                  break;
          case 7: file = "kroD100.tsp";
                  break;
          case 8: file = "rd100.tsp";
                  break;   
          default: file  = "a280.tsp";
                  break;
       }
       return file;
   }
}
