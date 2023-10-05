import java.util.Stack;
import java.util.Scanner;

/*
 * A homeowner rents out parking spaces in a driveway during special events. 
 * The driveway is a “last-in, first-out” stack. Of course, when a car owner 
 * retrieves a vehicle that wasn’t the last one in, the cars blocking it must
 *  temporarily move to the street so that the requested vehicle can leave.
 *  Write a program that models this behavior, using one stack for the 
 * driveway and one stack for the street. 
 * 
 * Use integers as license plate 
 * numbers. Positive numbers add a car, negative numbers remove a car, 
 * zero stops the simulation. Print out the stack after each operation is 
 * complete. Implement your algorithm in the Driveway class. Test your 
 * algorithm with the DrivewayDemo class. Test with at least the following input:
 * 
 * 
 * Only use the push and pop methods on the stacks. If you want to check for 
 * duplicate cars or check that the specified car is in the driveway, you can 
 * use the contains method. Use an enhanced for loop when printing the stacks.
 */


/**
 * Class for simulating a driveway and a street, using stacks
 * of cars with license plate numbers as representation.
*/
public class Driveway
{
    /**
      * Stack representing the cars in the driveway.
    */
    private Stack<Integer> driveway;
    /**
      * Stack representing the cars in the street.
    */
    private Stack<Integer> street;

    /**
      * Constructor.
    */
    public Driveway()
    {
        driveway = new Stack<>();
        street = new Stack<>(); 
    }

    /**
      * Add the given license plate to the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void add(int licensePlate)
    {
        driveway.push(licensePlate);
        System.out.println("License plate: " + licensePlate + " has been added to the driveway");
    }

    /**
      * Remove the given license plate from the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void remove(int licensePlate)
    {
        Integer peeked = driveway.peek();
        
        //checking if the license plate the user wants to remove is the one at the top of the stack 
        //if the license plate number is the same as the number at the top of the stack, 
        //it is removed. if the license plate is in the middle or beginning of the stack, all of the 
        //cars are moved to the street stack then the license plate car is removed then the cars 
        //in the street are put back into the driveway 
        if (licensePlate == peeked){
          driveway.pop();
          System.out.println("License plate: " + licensePlate + " has been removed from the driveway");
        }
        else {
          //looking at each element at the top of the stack and adds it to the street stack if 
          //it is not the license plate we are looking for 
          int length = driveway.size();
          for (int i = 0; i < length; i++){
            if (driveway.peek() == licensePlate){
              driveway.pop();
              System.out.println("License plate: " + licensePlate + " has been removed from the driveway");
            }
            else {
              street.push(driveway.pop());
            }
          }

          //adds the cars from the street back into the driveway 
          while (street.size() > 0){
            driveway.push(street.pop());
          }
        }
    }

    /**
      * Prints the driveway and street details to the screen.
    */
    public void print()
    {
        System.out.println("In Driveway, starting at first in (one license plate per line):");
        while (driveway.size() > 0){
          System.out.println(driveway.pop());
        }
        

        System.out.println("In Street, starting at first in (one license plate per line):");
        // Print the cars in the street here
        while (street.size() > 0){
          System.out.println(street.pop());
        }

    }
}
