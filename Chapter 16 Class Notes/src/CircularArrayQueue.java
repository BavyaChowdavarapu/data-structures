import java.util.NoSuchElementException;

/**
    An implementation of a queue as a circular array.
*/
public class CircularArrayQueue
{
    private Object[] elements;
    private int head;
    private int tail;
    private int currentSize;
    //private data



    /**
        Constructs an empty queue.
    */
    public CircularArrayQueue(){
        final int INITIAL_SIZE = 5;
        this.elements = new Object[INITIAL_SIZE];

        //difference between variable currentSize and INITIAL_SIZE:
        //currentsize keeps track of how many elements are actively in the queue 
            //this number can be different compared to the INITIAL_SIZE variable 
        //initialsize is the capacity of the ciruclararrayqueue
        this.head = 0;
        this.tail = 0;
        this.currentSize = 0;
    }



    /**
        Checks whether this queue is empty.
        @return true if this queue is empty
    */
    public boolean empty(){
        return (this.currentSize == 0);
    }


    /**
        Adds an element to the tail of this queue.
        @param newElement the element to add
    */
    public void add(Object element){
        this.growIfNecessary();
        this.currentSize++;
        this.elements[this.tail] = element;
        this.tail++;
        this.tail %= this.elements.length; //if it's divisible by the length of the array, it is 0
        //alternate option: use if conditional for setting the tail back to zero after hitting the end of the list 
    }




    /**
        Removes an element from the head of this queue.
        @return the removed element
    */
    public Object remove(){
        if (this.empty()){
            throw new NoSuchElementException();
        }

        this.currentSize--;
        Object element = this.elements[this.head];
        this.head = (this.head + 1) % this.elements.length;
        //if the head is not at the end of the list, it will be at the remainder of 
        //the modulus operation 
        return element;
    }




    /**
        Grows the element array if the current size equals the capacity.
    */
    private void growIfNecessary()
    {
        //increases the size of the array if we add too many elements (prevents overwriting pre-existing values)
        if(this.currentSize == this.elements.length)
        {
            Object[] newElements = new Object[2 * this.elements.length];
            for(int i = 0; i < this.elements.length; i++)
            {
                newElements[i] = this.elements[(head + i) % this.elements.length];
            }
            this.elements = newElements;
            this.head = 0;
            this.tail = this.currentSize;
        }
        
    }




}//CircularArrayQueue
