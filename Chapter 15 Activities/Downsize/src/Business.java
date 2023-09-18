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
        //creates iterator to go through the linked list with 
        ListIterator <String> iterator = employeeNames.listIterator();

        //creates counter to count number of employee names passed over by iterator
        //this is the total number of employees in the list 
        int numEmployees = 0;

        //gets the number of employees in the linked list
        while(iterator.hasNext()){

            iterator.next();
            
            numEmployees++;

            if (numEmployees % n == 0){
                iterator.remove();
            }
            
        }

        System.out.println(employeeNames);


    }
}
