import java.util.NoSuchElementException;





/**
 * A linked list is a sequence of nodes with efficient
 * element insertion and removal. This class
 * contains a subset of the methods of the standard
 * java.util.LinkedList class.
*/
public class LinkedList
{
    //first refers to the first node in the list
    //if the list is empty, first is null 
    private Node first;

    /**
        Constructs an empty linked list.
    */
    public LinkedList(){
        this.first = null;
    }

    /**
        Returns the first element in the linked list.
        @return the first element in the linked list
    */
    public Object getFirst(){
        if (this.first == null){
            throw new NoSuchElementException();
        }
        return this.first.data;
    }

    /**
        Removes the first element in the linked list.
        @return the removed element
    */
    public Object removeFirst(){
        if (this.first == null){
            throw new NoSuchElementException();
        }
        Object element = this.first.data;
        this.first = this.first.next; //points to the next node over now 
        return element;
    }

    /**
        Adds an element to the front of the linked list.
        @param element the element to add
    */
    public void addFirst(Object element){
        Node newNode = new Node();
        newNode.data = element;
        newNode.next = this.first;
        this.first = newNode;
    }

    /**
        Returns an iterator for iterating through this list.
        @return an iterator for iterating through this list
    */
    public ListIterator listIterator(){
        return new LinkedListIterator();
    }

    //Class Node
    //why  is the class static: can be a separate class but it is only every going to be used by the linked list class realistically but we can make it static so we aren't required to 
    //make a constructor within the class 
    //node doesn't need access to anything in the linked list class 
    //doesn't need to be a static class but organizationally and for design choice, it is used like this here 
    static class Node{ 
        public Object data;
        public Node next;
    }

    class LinkedListIterator implements ListIterator
    {
        //private data
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        /**
            Constructs an iterator that points to the front
            of the linked list.
        */
        public LinkedListIterator(){
            position = null;
            previous = null;
            isAfterNext = false;
        }

        /**
            Moves the iterator past the next element.
            @return the traversed element
        */
        public Object next(){
            //if we try to call next when there are no elements after the iterator, it will throw the no such element exception
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            previous = position;
            isAfterNext = true;

            //the position is null when the iterator is first created 
            if (position == null){ //when at the end of the list, loops back to the beginning
                position = first;
            }
            else {
                position = position.next;
            }

            return position.data;
        }

        /**
            Tests if there is an element after the iterator position.
            @return true if there is an element after the iterator position
        */
        public boolean hasNext(){
            if (position == null){//when we're at the end of the list so we want to move the iterator to the beginning of the list again 
                //returns false if there are no elements in the list 
                //if there are elements in the list, the return statement will return true 
                return (first != null);
            }
            
            //now we need to check if the next position is null
            return position.next != null; //this checks if there are any elements in the list at all 
        }

        /**
            Adds an element before the iterator position
            and moves the iterator past the inserted element.
            @param element the element to add
        */
        public void add(Object element){
            //if the position is at the beginning of the list (if postition is null), then we can just call the addFirst() method 

            //checks if the iterator is at the start of the list 
            if (position == null){
                addFirst(element);
                position = first;
            }
            else { //the element is not at the start of the list 
                Node newNode = new Node();
                newNode.data = element;
                newNode.next = position.next;
                position.next = newNode;
                position = newNode;
            }


            isAfterNext = false;
        }

        /**
            Removes the last traversed element. This method may
            only be called after a call to the next() method.
        */
        public void remove(){
            if (!isAfterNext){
                throw new IllegalStateException();
            }

            //check if we're at the beginning of the list 
            if (position == first){
                removeFirst();
                position = null;
            }
            else{ 
                previous.next = position.next;//the element points to the one after the one that is deleted
                position = previous; //moves iterator to the previous node 
            }

            isAfterNext = false;
        }

        /**
            Sets the last traversed element to a different value.
            @param element the element to set
        */
        public void set(Object element){
            if (!isAfterNext){
                throw new IllegalStateException();
            }

            position.data = element;
            //dont need to set the isafternext variable to false because the set method isn't changing the postition of the iterator 
            //by adding or removing an element --> it is only changing the value of the element already at the position of the iterator 
        }



    }//LinkedListIterator
}//LinkedList
