import java.util.*;
import java.io.*;
/**
 * Read all words from a file and add them to a
 * map whose keys are word lengths and whose values
 * are comma-separated strings of words of the same length.
 * Then print out those strings, in increasing order by
 * the length of their entries.
 * Modify Worked Example 15.1.
 */
public class StringLengthMap
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String filename = "src/test1.txt";

        try (Scanner in = new Scanner(new File(filename)))
        {

            // Create your map here
            Map<Integer, String> lengths = new HashMap<>();

            while (in.hasNext())
            {
                String nextWord = in.next();
                String word = clean(nextWord);
                Integer length = word.length();

                // Update the map here

                //if the key for the word (number of letters) is not already in the map, it adds the word
                if (lengths.get(length) == null) 
                {
                    lengths.put(length, word);
                }
                else 
                {
                    //if the key for the word (number of letters) is already in the map
                    String newWord = lengths.get(length); //gets the word from the map that has the same key
                    String totalWords = newWord + ", " + word; //concatenates the new word to the old word to the same key 
                    lengths.put(length, totalWords); //adds the concatenated words with the same key to the map
                }
            }

            // Print the strings, in increasing order of their length
            // Use this format: 1: i, a, i
            Set<Integer> keys = wordLengths.keySet();
            for (Integer key : keys)
            {
                System.out.println(key + "letter words: " + lengths.get(key));
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
