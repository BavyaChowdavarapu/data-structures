import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
    This program demonstrates a map that maps names to colors.
*/
public class MapDemo
{
    public static void main(String[] args)
    {
        /* the map interface is generic. the irst type is the type of the key and the second type is the type of the value */
        Map<String, Color> favColors = new HashMap<>();

        favColors.put("Hyder",Color.RED);
        favColors.put("Athena", Color.MAGENTA);
        favColors.put("Bavya", Color.BLUE);

        //two different keys can have the same value 
        favColors.put("Mitchell", Color.RED);

        //one key can not have two values 
        favColors.put("Athena", Color.ORANGE);

        System.out.println(favColors.get("Athena"));
        //this prints out the actual rgb values of that color (orange here) 
        //this changed the value of athena's key

        //using put for a key that exists changes the value 

        //dictionaries are map examples (but in python)

        //create a set of all of the keys in a map 
        Set<String> keys = favColors.keySet();
        //this returns all of the keys from this set 

        for (String key : keys){
            System.out.println(key + "(" + key.hashCode() +  "): " + favColors.get(key));
            //prints out the keys with the hashcodes and the values 
        }

        //maps are not ordered 
    }
}
