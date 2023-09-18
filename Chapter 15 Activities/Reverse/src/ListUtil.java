import java.util.LinkedList;
import java.util.*;

/**
 * This class supplies a utility method to reverse the entries in a linked list.
*/
public class ListUtil
{
    /**
     * Reverses the elements in a linked list
     *
     * @param strings the linked list to reverse
    */
    public static void reverse(LinkedList<String> strings)
    {
        //creates counter to keep track of how many elements should be in the list 
        int counter = strings.size();

        //creates a string object to temporarily hold the value that the iterator removes from the end of the list
        String temp; 

        //creates an iterator to go through the linked list 
        //starting position of both iterators is before the first element of the linked list 
        ListIterator <String> iterator = strings.listIterator();
        ListIterator <String> iterator2 = strings.listIterator();

        //gets the iterators to the end of the linked list
        while(iterator.hasNext()){
            iterator.next();
        }
        

        //moves iterator from the back of the linked list to the front of the linked list 
        //for every element the iterator passes over, it is added back to the start of the list
        //and removed from the end of the list 
        while(counter > 0){
            //finish this part
            //gives concurrentmodification error
            
            temp = iterator.previous();
            iterator.remove();
            iterator2.add(temp);
            counter--;

        }

        //prints out reversed linked list 
        System.out.println(strings);
    }
}