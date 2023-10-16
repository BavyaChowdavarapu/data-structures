import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
/**
 * Write a program that checks whether a sequence of HTML tags
 * is properly nested. For each opening tag, such as <p>, there
 * must be a closing tag </p>. A tag such as <p> may have other
 * tags inside, for example <p> <ul> <li> </li> </ul> <a> </a> </p>
 * <p>
 * The inner tags must be closed before the outer ones.
 * Your program should process a file containing tags.
 * For simplicity, assume that the tags are separated by
 * spaces, and that there is no text inside the tags.
*/
public class HTMLChecker
{
    public static void main(String[] args)
    {
        String filename = "Chapter 15 Activities/HTMLChecker/src/TagSample1.html";
        //String filename = "Chapter 15 Activities/HTMLChecker/src/TagSample2.html";
        //String filename = "Chapter 15 Activities/HTMLChecker/src/TagSample3.html";

        //creates stack for the HTML tags
        Stack<String> tags = new Stack<>();
        
        try (Scanner in = new Scanner(new File(filename)))
        {
            // Your code goes here

            //splits string into tokens at space points
            in.useDelimiter(" ");

            
            while(in.hasNext()) {
                
                String token = in.next();

                //adds the token to the stack if it is an opening tag
                if (!token.contains("/")) {
                    tags.push(token);
                }
                else {
                    //comparison of opening to closing tag and removes if they are a pair 
                    if (tags.peek().substring(1, tags.peek().indexOf(">")).equals(token.substring(2, token.indexOf(">"))))
                        tags.pop();
                }
            }

            //checks if there is the same number of <x> as there are </x> and returns true or false respectively
            if(tags.size() == 0)
                System.out.println("True");
            else
                System.out.println("False");

        }


         catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }

    }
}
