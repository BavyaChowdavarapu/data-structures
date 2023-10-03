import java.util.LinkedList;
import java.util.Queue;

/**
 * This program simulates a print queue. Note that documents are printed
 * in the same order as they are submitted.
*/
public class QueueDemo
{
    public static void main(String[] args)
    {
        //create print queue of strings (using a linked list because linked lists are queues)
        Queue<String> jobs = new LinkedList<>();

        //add some print jobs 
        jobs.add("Joe: Expense Report 2023");
        jobs.add("Cathy: Top Secret Document #5");

        System.out.println("Printing: " + jobs.remove()); //prints joe's line b/c queue is fifo

        jobs.add("Cathy: Really Top Secret Document #2");
        jobs.add("Joe: grocery list");
        jobs.add("Cathy: Can I Get Fired For This?");
        

        System.out.println("Printing: " + jobs.remove());

        jobs.add("Boss: Cathy's Termination Letter");

        //printing the rest of the jobs in the queue
        int length = jobs.size();
        while (length > 0){
            System.out.println("Printing: " + jobs.remove());
            length--;
        }
    }
}
