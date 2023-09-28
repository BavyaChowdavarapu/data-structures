import java.util.Scanner;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * A program to add, remove, modify or print
 * student names and grades.
*/
public class Gradebook
{
    public static void main(String[] args)
    {
        //making the menue for the user to choose what to do 
        System.out.println("Would you like to Add a student (A), Remove a student (R), Modify a grade (M), or Print all of the grades(P)?");

        //making the scanner object 
        Scanner in = new Scanner(System.in);

        Map<String, String> students = new HashMap<>();

        Set<String> keys = students.keySet();



        boolean done = false;
        while(!done)
        {
            System.out.println("A)dd R)emove M)odify P)rint Q)uit");
            String input = in.next().toUpperCase();
            if (input.equals("Q"))
            {
                done = true;
            } else if (input.equals("A"))
            {
                System.out.println("What is the name of the student?");
                String name = in.next();
                System.out.println("What is the grade of the student?");
                String grade = in.next();


                students.put(name, grade);


            } else if (input.equals("R"))
            {
                System.out.println("What is the name of the student you want to remove?");
                String name = in.next();

                students.remove(name);
                

            } else if (input.equals("M"))
            {
                System.out.println("What is the name of the student you want to modify?");
                String name = in.next();

                System.out.println("What is the new grade of the student?");
                String grade = in.next();

                students.put(name, grade);

            } else if (input.equalsIgnoreCase("P"))
            {
                for (String key : keys){
                    System.out.println(key + ":" + students.get(key));
                }
            } else
            {
                done = true;
            }
        }
    }
}
