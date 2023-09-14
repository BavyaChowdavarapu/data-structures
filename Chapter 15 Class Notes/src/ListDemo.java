import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/
public class ListDemo
{
    public static void main(String[] args)
    {
        LinkedList<String> staff = new LinkedList<>();
        staff.addLast("Diana");
        staff.addLast("Harry");
        staff.addLast("Romeo");
        staff.addLast("Tom");

        //the list is currently:DHRT
        /*
        the listIterator method creates a new list iterator that is positioned at the head of the list
        the "|" is used to represent the iterator position
        
        */ 
        ListIterator<String> iterator = staff.listIterator(); //|DHRT

        //the next method advances the iterator to the next element in the list 
        iterator.next();// D|HRT
        //the next method also returns the element that the iterator is passing over 
        String name = iterator.next(); //DH|RT
        System.out.println(name);
        System.out.println("expected: harry)");

        //the add method for iterators inserts an element at the iterator postion
        //the iterator is then postitioned after the element that was added 

        iterator.add("Juliet");//DHJ|RT
        iterator.add("Nina");//DHJN|RT

        //this is the iterator add method --> different from the linked lists add method 

        //the remove method removes the element returned by the last call to next or previous 
        //remove method can only be called once after calling next or previous 
        //remove method can't be called after calling add
        //THIS IS FOR THE ITERATOR REMOVE METHOD, NOT THE LINKED LISTS REMOVE METHOD

        iterator.next();//DHJNR|T
        iterator.remove(); //romeo will be removed so list will be: DHJN|T

        System.out.println(staff);
        System.out.println("Expected: [Diana, Harry, Juliet, Nina, Tom]");

        //set method updates the element returned by the last call to next or previous 

        iterator.previous(); //DHJ|NT
        iterator.set("Albert"); //DHJ|AT

        //the hasNext method returns true if the list has another element 
        //it is often used in the condition of a while loop

        iterator = staff.listIterator(); // |DHJAT
        while(iterator.hasNext()){
            String n = iterator.next();
            if (n.equals("Juliet")){ //dhj|at
            iterator.remove();                //dh|at
            }
        }
        //dhat|

        //enhanced for loops work with linked lists 
        for (String n: staff){
            System.out.print(n + " ");
        }
        System.out.println("Expected: [Diana, Harry, Albert, Tom]");

        //concurrent modification exception
        //cant modify a linked like while also using an iterator uness you use the iterator to do it 
        iterator = staff.listIterator();
        while(iterator.hasNext()){
            String n = iterator.next();
            if (n.equals("Harry")){
                staff.remove("Diana");
            }
        }
        //doesn't work because gived runtime error from concurrent modification error (changing list while iterating through it)
        //just like in apcs where you can't remove something in a for each loop

    }
}