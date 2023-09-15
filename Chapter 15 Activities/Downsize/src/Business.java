import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Business utility methods.
*/
public class Business
{

    
    /**
      * Removes every nth element from the linked list
      *
      * @param employeeNames the linked list to remove from
      * @param n                 the parameter to determine "nth"
     */
    public static void downsize(LinkedList<String> employeeNames, int n)
    {
        //creates copy of the employeeNames linked list
        LinkedList<String> names = new LinkedList<>();

        //creates iterator to go through the linked list with 
        ListIterator <String> iterator = names.listIterator();

        //creates counter to count number of employee names passed over by iterator
        //this is the total number of employees in the list 
        int numEmployees = 0;

        //gets the number of employees in the linked list
        while(iterator.hasNext()){
            numEmployees++;
        }

        







    }
}
