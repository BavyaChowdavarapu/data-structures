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

        //creates an iterator to go through the linked list 
        //starting position of both iterators is before the first element of the linked list 
        ListIterator <String> iterator = strings.listIterator();
        ListIterator <String> iterator2 = strings.listIterator(counter);


        //moves iterator from the back of the linked list to the front of the linked list 
        //for every element the iterator passes over, it is added back to the start of the list
        //and removed from the end of the list 
        for (int i = 1; i <= (counter/2); i++){
            String a = iterator.next();
            String b = iterator2.previous();

            iterator.set(b);
            iterator2.set(a);
        }

        //prints out reversed linked list 
        System.out.println(strings);
    }
}