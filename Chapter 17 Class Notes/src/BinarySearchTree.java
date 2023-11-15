/**
    This class implements a binary search tree whose
    nodes hold objects that implement the Comparable
    interface.
*/
public class BinarySearchTree
{   
    private Node root;

    /**
        Constructs an empty tree.
    */
    public BinarySearchTree()
    {   
        this.root = null;
    }
    
    /**
        Inserts a new node into the tree.
        @param obj the object to insert
    */
    public void add(Comparable obj) 
    {   
        Node newNode = new Node();
        newNode.data = obj;
        newNode.left = null;
        newNode.right = null;

        //doesn't necessarily create a balanced tree 
        //adds the object to the current tree so we need to check if it is null 
        if (this.root == null){
            this.root = newNode;

        } else {
            this.root.addNode(newNode);
        }
    }

    /**
        Tries to find an object in the tree.
        @param obj the object to find
        @return true if the object is contained in the tree
    */
    public boolean find(Comparable obj)
    {
        //pick a spot to start looking 
        Node current = this.root;

        while (current != null){
            int diff = obj.compareTo(current.data){

                if (diff == 0){
                    return true;
                } else if (diff < 0){
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            
        }

        //if we get through the whole body of this method and don't find it, return false 
        return false;
    }
    
    /**
        Tries to remove an object from the tree. Does nothing
        if the object is not contained in the tree.
        @param obj the object to remove
    */
    public void remove(Comparable obj)
    {
        Node toBeRemoved = this.root;
        Node parent = null;
        boolean found = false; 

        while (!found && toBeRemoved != null){
            //if toberemoved is null, we reached a leaf 
            //goes through the tree and looks for the node we want to remove 
            int diff = obj.compareTo(toBeRemoved.data){
                if (diff == 0){
                    found = true;
                }
                else {

                    parent = toBeRemoved; 
                    if (diff < 0){
                        toBeRemoved = toBeRemoved.left;
                        
                    } 
                    
                    else {
                        toBeRemoved = toBeRemoved.right;
                    }
                }

            }
        }

        if (!found){
            return;
        }

        //when one side is null --> case 1 and 2 (at least one chil is null)
        if (toBeRemoved.left == null || toBeRemoved.right == null){
            Node newChild;

            if (toBeRemoved.left == null){
                newChild = toBeRemoved.right;
            }
            else {
                newChild = toBeRemoved.left;
            }

            if (parent == null){
                //root was removed
                this.root = newChild;
            } else if (parent.left == toBeRemoved){
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
            return;
        }

        //case three --> there are two children of the node that will be removed 
        //find the smallest element of the right subtree 
        //smallest = least element 
        Node leastParent = toBeRemoved;
        Node least = toBeRemoved.right;
        while (least.left != null){
            leastParent = least;
            least = least.left;
        }

        //move the data
        toBeRemoved.data = least.data;

        //unlink the child 
        if (leastParent == toBeRemoved){
            leastParent.right = least.left;

        }else {
            leastParent.left = least.right;
        }
    }
    
    /**
        Prints the contents of the tree in sorted order.
    */
    public void print()
    {   
        
    }   

    /**
        Prints a node and all of its descendants in sorted order.
        @param parent the root of the subtree to print
    */
    private static void print(Node parent)
    {   
        
    }

    /**
        A node of a tree stores a data item and references
        to the left and right child nodes.
    */
    static class Node
    {   
        //binary search trees need to be comparable objects 
        public Comparable data;
        public Node left;
         public Node right;

        /**
            Inserts a new node as a descendant of this node.
            @param newNode the node to insert
        */
        public void addNode(Node newNode)
        {   
            //adds a new node as a decendant of the current node 
            //need to determine if it should go to the left or the right of the current node 
            int diff = newNode.data.compareTo(data);
            //compares the new node to the current node 
            //compareto >0, right node 
            //comparetp < 0, left node 

            if (diff < 0){
                if (left == null){
                    left = newNode;
                    //recursively call the addnode method to figure out where the new node goes in the else statement 
                } else {
                    left.addNode(newNode);
                }



            } else if (diff > 0){
                if (right == null){
                    right = newNode;
                    //recursively call the addnode method to figure out where the new node goes in the else statement 
                } else {
                    right.addNode(newNode);
                }

                //if the difference is 0, we don't add it to the tree --> no duplicates 
            } 
        }
    }
}



