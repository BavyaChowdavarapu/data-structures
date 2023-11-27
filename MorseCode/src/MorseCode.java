import java.util.TreeMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MorseCode
{
    private static final char DOT = '.';
    private static final char DASH = '-';

    private static TreeMap<Character, String> codeMap;
    private static TreeNode decodeTree;

    public static void main(String[] args)
    {
        MorseCode.start();  
        System.out.println(MorseCode.encode("Watson come here"));
        BTreePrinter.printNode(decodeTree);
    }

    public static void start()
    {
        codeMap = new TreeMap<Character, String>();
        decodeTree = new TreeNode(' ', null, null);  // autoboxing
        // put a space in the root of the decoding tree

        //addSymbol(key, value)
        addSymbol('A', ".-");
        addSymbol('B', "-...");
        addSymbol('C', "-.-.");
        addSymbol('D', "-..");
        addSymbol('E', ".");
        addSymbol('F', "..-.");
        addSymbol('G', "--.");
        addSymbol('H', "....");
        addSymbol('I', "..");
        addSymbol('J', ".---");
        addSymbol('K', "-.-");
        addSymbol('L', ".-..");
        addSymbol('M', "--");
        addSymbol('N', "-.");
        addSymbol('O', "---");
        addSymbol('P', ".--.");
        addSymbol('Q', "--.-");
        addSymbol('R', ".-.");
        addSymbol('S', "...");
        addSymbol('T', "-");
        addSymbol('U', "..-");
        addSymbol('V', "...-");
        addSymbol('W', ".--");
        addSymbol('X', "-..-");
        addSymbol('Y', "-.--");
        addSymbol('Z', "--..");
        addSymbol('0', "-----");
        addSymbol('1', ".----");
        addSymbol('2', "..---");
        addSymbol('3', "...--");
        addSymbol('4', "....-");
        addSymbol('5', ".....");
        addSymbol('6', "-....");
        addSymbol('7', "--...");
        addSymbol('8', "---..");
        addSymbol('9', "----.");
        addSymbol('.', ".-.-.-");
        addSymbol(',', "--..--");
        addSymbol('?', "..--..");
    }

    /**
     * Inserts a letter and its Morse code string into the encoding map
     * and calls treeInsert to insert them into the decoding tree.
     */
    private static void addSymbol(char letter, String code)
    {
        //stores the morse code as the key and the letter as the value 
        //this should be two lines in this method and the treeInsert 
        //assigns the keys and their values 

        codeMap.put(letter, code);
        treeInsert(letter, code);
    }

    /**
     * Inserts a letter and its Morse code string into the
     * decoding tree.  Each dot-dash string corresponds to a path
     * in the tree from the root to a node: at a "dot" go left, at a "dash" go
     * right.  The node at the end of the path holds the symbol
     * for that code string.
     */
    private static void treeInsert(char letter, String code)
    {
        //sets the value of the child of the previous node to this current node -----------------------------done

        
        //object used to traverse the tree
        TreeNode root = decodeTree; 
        
        //counter to keep track of the character index in the code 
        int count = 0;
        char ch;

        //while loop keeps running as long as the count is less than the string's length 
        while (count < code.length()){
            //gets the character at the index in the code
            ch = code.charAt(count);
            
            //if the next character in the code is a dot
            if(ch == DOT){
                //checks if the left child is null
                if(root.getLeft() == null){
                    //makes the null spot into an empty node
                    root.setLeft(new TreeNode(null));
                }
                //sets root to the empty node
                root = root.getLeft();
            } else {
                //if the next character in the code is a dash 
                
                //checks if the right child is null
                if(root.getRight() == null){
                    //makes the null spot into an empty node 
                    root.setRight(new TreeNode(null));
                }
                //sets root to the empty node 
                root = root.getRight();
            }
            
            //iterating the while loop
            count++;
        }
        
        //setting the value of the final position of root to the letter 
        root.setValue(letter);
    }

    /**
     * Converts text into a Morse code message.  Adds a space after a dot-dash
     * sequence for each letter.  Other spaces in the text are transferred directly
     * into the encoded message.
     * Returns the encoded message.
     */
    public static String encode(String text)
    {
        StringBuffer morse = new StringBuffer(400); 
        
        //string to hold the code of each letter of the text
        String code; 
        
        //int to hold counter for while loop
        int count = 0;
    
        //while loop iterates as long as counter is a valid character index
        while (count < text.length()){
            //if the character is a space 
            if(text.charAt(count) == ' '){ 
                morse.append(" "); 
            } 
            
            //getting the uppercase version of the letter from the codemap
            code = codeMap.get(text.toUpperCase().charAt(count)) + " ";
            
            //if the code is null, append a space 
            if(code.equals("null ")){
                code = "  ";
            }
            morse.append(code);
            
            //iterating the while loop
            count++;
        }
        
        return morse.toString();
    }

    /**
     * Converts a Morse code message into a text string.  Assumes that dot-dash
     * sequences for each letter are separated by one space.  Additional spaces are
     * transferred directly into text.
     * Returns the plain text message.
     */
    public static String decode(String morse) {
        StringBuffer text = new StringBuffer(700); 
        
        //object to traverse the treeMap with 
        TreeNode decodeThis = decodeTree;
        
        //counter for index in the morse code text
        int count = 0;
        
        //char to hold each character in the morse code 
        char ch;
        //while loop iterates as long as count is a valid index of the code 
        while(count < morse.length())
        {
            //sets ch to each character 
            ch = morse.charAt(count); 
            
            //if the character is a dot 
            if (ch == DOT)
            {
                //checks if the left child of the current position is null
                if (decodeThis.getLeft() == null)
                {
                    //this character is not in the map 
                    break;
                }
            
                //reassigns decodeThis to the left child
                decodeThis = decodeThis.getLeft();
            } else if (ch == DASH) //if the character is a dash 
            {
                //checks if the right child of the current position is null
                if (decodeThis.getRight() == null)
                {
                    //this character is not in the map
                    break;
                }
                
                //reassigns decodeThis to the right child
                decodeThis = decodeThis.getRight(); 
            } else 
            {
                //if the next character is not a dot or dash 
                
                //appends the text with the letter translation of the code
                text.append(decodeThis.getValue()); 
                decodeThis = decodeTree;
            }
            
            //iterating the while loop
            count++;
        }
        
        return text.toString();
    }
    
}


/**
 * BTreePrinter class courtesy of Karen Ge (@karenge1)
 */
class BTreePrinter {

    public static void printNode(TreeNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.getValue());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), 
            BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

