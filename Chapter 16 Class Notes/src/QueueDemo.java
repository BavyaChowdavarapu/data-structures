public class QueueDemo
{
    public static void main(String[] args)
    {
        
        CircularArrayQueue queue = new CircularArrayQueue();

        queue.add("Tom");
        queue.add("Diana");
        queue.add("Harry");
        System.out.println(queue.remove()); // remove Tom
        queue.add("Romeo");
        System.out.println(queue.remove()); // remove Diana
        queue.add("Juliet");
        queue.add("Maria");
        //adding three more names makes it so harry is overwritten 
        //we can account for this by making the array grow if needed --> necessary to avoid overwtriting but less efficient 
        queue.add("Bob");
        queue.add("Henry");
        queue.add("Ben");
        //growifncessary method prevents harry from being overwritten 

        while(!queue.empty())
        {
            System.out.println(queue.remove());
        }
        
        System.out.println("Expected output: Tom, Diana, Harry, Romeo, Juliet, Maria");
        
    }
}
