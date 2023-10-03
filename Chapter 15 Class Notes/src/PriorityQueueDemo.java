import java.util.PriorityQueue;
import java.util.Queue;


/**
 * This program demonstrates a priority queue of to-do items. The
 * most important to-do items are removed first.
*/
public class PriorityQueueDemo
{
    public static void main(String[] args)
    {
        //create a priority queue of to do items 
        //a WorkOrder has a message id that is used to determine priority 
        //a priority queue can only store Comparable objects 
        //a WorkOrder is a Comparable object
        Queue<WorkOrder> toDo = new PriorityQueue<>();
        toDo.add(new WorkOrder(3, "Water plants"));
        toDo.add(new WorkOrder(2, "Make dinner"));
        toDo.add(new WorkOrder(2, "Walk dog"));
        toDo.add(new WorkOrder(9, "Play videogamnes"));
        toDo.add(new WorkOrder(1, "Study for the chapter 15 exam"));

        System.out.println(toDo);
        //prints out the elements in priority order with the lower values meaning a higher priority
        //objects added to a priority queue are stored in priority order
        //if two things have the same priority level it arbitrarily chooses one --> there is no specific rule for this situation 

        while (toDo.size() > 0){
            System.out.println(toDo.remove());
        }
    }
}
