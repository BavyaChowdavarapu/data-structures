/**
    A binary tree in which each node has two children.
*/
public class BinaryTree
{
    private Node root;

    /**
        Constructs an empty tree.
    */
    public BinaryTree()
    {
        this.root = null;
        
    } 

    /**
        Constructs a tree with one node and no children.
        @param rootData the data for the root
    */
    public BinaryTree(Object rootData) 
    {
        this.root = new Node();
        this.root.data = rootData;
        this.root.left = null;
        this.root.right = null;
    }

    /**
        Constructs a binary tree.
        @param rootData the data for the root
        @param left the left subtree
        @param right the right subtree
    */
    public BinaryTree(Object rootData, BinaryTree left, BinaryTree right)
    {
        this(rootData);//sets this.root.data to rootData b/c we have a constructor with this as a parameter 
        this.root.left = left.root;
        this.root.right = right.root; 
    }
    
    static class Node
    {
        public Object data;
        public Node left;
        public Node right;
    }

    /**
        Returns the height of the subtree whose root is the given node.
        @param n a node or null
        @return the height of the subtree, or 0 if n is null
    */
    private static int height(Node n) //include the root in the calculation of the height of the root 
    //this method is static because it is a helper method --> not directly used for anything
    {
        if (n == null){
            return 0;
        }
        else {
            return 1 + Math.max(BinaryTree.height(n.left), BinaryTree.height(n.right));//finds the max between the height of the left side of the tree 
            //and the height of the right side of the tree but this doesn't include the root so need to add the 1
        }
    }

    /**
        Returns the height of this tree.
        @return the height
    */
    public int height()//nonstatic version of the previous method
    {
        return BinaryTree.height(this.root);
    }

    /**
        Checks whether this tree is empty.
        @return true if this tree is empty
    */
    public boolean isEmpty()
    {
        if (this.root == null){
            return true;
        }

        return false;
    }

    /**
        Gets the data at the root of this tree.
        @return the root data
    */
    public Object data()
    {
        return this.root.data;
    }
    
    /**
        Gets the left subtree of this tree.
        @return the left child of the root
    */
    public BinaryTree left() 
    { 
        BinaryTree subtree = new BinaryTree();
        subtree.root = this.root.left;
        return subtree;
    }

    /**
        Gets the right subtree of this tree.
        @return the right child of the root
    */
    public BinaryTree right() 
    { 
        BinaryTree subtree = new BinaryTree();
        subtree.root = this.root.right;
        return subtree;
    }
}
