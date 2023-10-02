import java.util.*;
import java.io.*;
/**
 * Read all words from a file and add them to a map
 * whose keys are the first letters of the words and
 * whose values are sets of words that start with
 * that same letter. Then print out the word sets in
 * alphabetical order. Update the map by modifying
 * Worked Example 15.1. 
*/
public class FirstLetterMap
{
    public static void main(String[] args)
    {
        String filename = "src/test1.txt";

        try (Scanner in = new Scanner(new File(filename)))
        {

            // Create your map here
            Map<Character, Set<String>> words = new HashMap<>();

            while (in.hasNext())
            {
                String newWord = clean(in.next());
                Character ch = newWord.charAt(0);

                // Update the map here
                // Modify Worked Example 15.1
                Set<String> key = words.get(ch);
                
                if (key == (null)) {
                    Set<String> set = new HashSet<>();
                    set.add(newWord);
                    words.put(ch, set);
                }
                else {
                    key.add(newWord);
                    words.put(ch, key);
                }  


            }

            // Print the map here in this form
            // a: [a, able, aardvark]
            for (Character x : words.keySet()) {
                System.out.print(x + ": " );
                System.out.println(words.get(x));
            }

            
        } catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }
    }

    public static String clean(String s)
    {
        String r = "";
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (Character.isLetter(c))
            {
                r = r + c;
            }
        }
        return r.toLowerCase();
    }
}
