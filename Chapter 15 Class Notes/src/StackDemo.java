import java.util.Stack;

/**
 * This program simulates an undo stack. Note that operations
 * must be undone in the opposite order in which they are first
 * issued.
*/
public class StackDemo
{
    public static void main(String[] args)
    {
        Stack<String> commands = new Stack<>(); //you don't need to put a size but they have a limit to how much they can hold

        //push commands onto the top of the stack 
        commands.push("Insert: 'Hello'");
        commands.push("Insert','");
        commands.push("Insert: ' '");
        commands.push("Insert: 'World'");
        commands.push("Insert: '?'");
        commands.push("Delete: '?'");
        commands.push("Insert: '!'");

        //print stack; the top of the stack is at the far right 
        System.out.println(commands);

        //simulate the user pressing the undo button 4 times 
        int counter = 0;
        while (counter <4){
            String command = commands.pop();
            System.out.println("Undo: " + command);
        }
    }
}
