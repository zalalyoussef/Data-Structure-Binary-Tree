package edu.ktu.ds.lab2.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * Sorted object collections - realization of a set with a binary search tree.
 *
 * @param <E> Set element type. The Comparable <E> interface must be satisfied, or
 *            an object satisfying the Comparator <E> interface must be passed through the
 *            class constructor.
 *
 * @author darius.matulis@ktu.lt
 * @u?duotis Review and clarify the methods provided.
 */
public class BstSet<E extends Comparable<E>> implements SortedSet<E>, Cloneable
{


   // private int height;

    protected class BstNode<N>
    {


        // Element
        protected N element;
        // Pointer to the left subtree
        protected BstNode<N> left;
        // Pointer to the right subtree
        protected BstNode<N> right;
        protected int height;



        protected BstNode()
        {
        }

        protected BstNode(N element)
        {
            this.element = element;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public BstNode<E> findMax()
        {
            if(right != null)
            {
                return right.findMax();
            }
            return this.findMax();
        }
    }

    int sb;
    boolean bal = false;
    // Tree root node
    protected BstNode<E> root = null;
    // Tree size
    protected int size = 0;
    // Arrow to comparator
    protected Comparator<? super E> c = null;

    /**
     * A set object is created for BS-tree keys using Comparable <E>
     */
    public BstSet()
    {
        this.c = Comparator.naturalOrder();
    }

    /**
     * A set object is created for BS-tree keys using Comparator <E>
     *
     * @param c Comparator
     */
    public BstSet(Comparator<? super E> c)
    {
        this.c = c;
    }

    /**
     * Checking that the set is empty.
     *
     * @return Returns true if the set is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * @return Returns the number of items in the set.
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * The set is cleared.
     */
    @Override
    public void clear()
    {
        root = null;
        size = 0;
    }

    private int height(BstNode<E> n)
    {

        return (n == null) ? -1 : n.height;
    }

   /* public boolean removeAll(BstSet<E> c)
    {
        boolean rem =false;
        for(E ele : c)
        {
            if(contains((E) c))
            {
                rem=true;
                remove(ele);
            }
        }
        return true;
    }*/

    private int getBalance(BstNode<E> n)
    {

        Ks.ounn("Balance Factor of node"+n.element.toString()+"   is  "+(height(n.left) - height(n.right)));
       // Ks.oun(height(n.left) - height(n.right));
        return height(n.left) - height(n.right);
    }

    public boolean Bal_Set(int bal)
    {
       boolean Check= CheckBalance(this.root, bal);
        return Check;
    }

    @Override
    public E last()
    {
        BstNode<E> Node =root;

        while(Node!=null)
        {
            Node=Node.right;
            if(Node.right==null)
            {
                return Node.element;
            }
        }
        return  Node.element;
    }

    @Override
    public E ceiling(E ele)
    {

        if(ele == null)
        {
           return null;
        }
        BstNode<E> node = root;
       while(node != null) {
           int cmp=c.compare(ele,node.element);

          if(cmp<0) {
              node=node.left;
          }
          else if(cmp>0) {
               node=node.right;
           } else {
             node=(node.right!=null)? node.right:node;
             return node.element;
          }
       }
        return node.element;
    }


    public boolean CheckBalance(BstNode<E> node, int bal)
    {
        if (node == null)
            return true;

        if (getBalance(node) <= bal && CheckBalance(node.left,bal) && CheckBalance(node.right,bal))
            return true;

        return false;
    }

 /*   public E floor(E ele)
    {

        if(ele == null)
        {
            return null;
        }
        BstNode<E> node = root;
        while(node != null)
        {
            int cmp=c.compare(ele,node.element);

            if(cmp<0) {
                node=node.left;
            }
            else if(cmp>0) {
                node=node.right;
            } else {
                node=(node.left!=null)? node.left:node;
                while(node!= null)
                {
                    if(node.right==null)
                    return node.element;
                    else
                       node=node.right;
                }

            }
        }
        return node.element;
    }
*/

    private BstNode<E> addRecursive(E element, BstNode<E> node)
    {
        if (node == null)
        {
            size++;
            return new BstNode<>(element);
        }

        int cmp = c.compare(element, node.element);

        if (cmp < 0)
        {
            node.left = addRecursive(element, node.left);
        }
        else if (cmp > 0) {
            node.right = addRecursive(element, node.right);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }
    /**
     * Checks if an element exists in the set.
     *
     * @param element - Set element.
     * @return Returns true if an element exists in the set.
     */
    @Override
    public boolean contains(E element)
    {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }

        return get(element) != null;
    }

    /**
     * It is checked whether all the elements of both sets exist in the set
     *
     * @param set set
     * @return
     */
    @Override
    public boolean containsAll(Set<E> set)
    {
       boolean found= true;
       for(E s : set)
       {
           if(!contains(s))
           {
               found = false;
           }
       }
        return found ;
    }

    /**
     * A new element is added to the set.
     *
     * @param element - Set element.
     */
    @Override
    public void add(E element)
    {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }

        root = addRecursive(element, root);
    }

    /**
     * The elements of both sets are added to the existing set, if both sets have the same element, it is not placed.
     *
     * @param set additional set
     */
    @Override
    public void addAll(Set<E> set)
    {
        for(E data : set)
        {
            if(!contains(data))
            {
                root=addRecursive(data,root);
            }

        }
    }



    /**
     * Removes an item from a set.
     *
     * @param element - Set element.
     */
    @Override
    public void remove(E element)
    {
       root = removeRecursive(element, root);
    }

    /**
     * Only the elements that are in the set are left in the set.
     *
     * @param set aib?
     */
    @Override
    public void retainAll(Set<E> set) throws IllegalArgumentException
    {
       for(E s : set)
       {
           if(!contains(s))
           {
               set.remove(s);
           }
       }
    }


    private BstNode<E> removeRecursive(E element, BstNode<E> node)
    {
       // int key = element.compareTo(node.element);
        int key = c.compare(element, node.element);

        if (node == null)
        {
            return null;
        }
        else if (key <0)
        {
            root.left = removeRecursive(element, node.left);
        }
        else if (key > 0)
        {
            root.right = removeRecursive(element,node.right);
        }
        else {

            if (node.left == null && node.right == null)
            {
                root = null;
            }
            else if (node.left == null ||node.right == null)
            {
                node = node.left == null ? node.right : node.left;
            }
            else {
                BstNode predecessorNode = node.left.findMax();
                swapData(predecessorNode, node);
                node.left = removeRecursive((E) predecessorNode.element, node.left);
            }
            size--;
        }
        return node;
    //    return null;
    }
//swap method
   private void swapData(BstNode nodeA, BstNode nodeB)
    {
   Object temp = nodeA.element;
    nodeA.element= nodeB.element;
    nodeB.element = temp;
     }


    private E get(E element)
    {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BstNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.element;
            }
        }

        return null;
    }

    /**
     * Returns an array of set elements.
     *
     * @return Returns an array of set elements.
     */
    @Override
    public Object[] toArray()
    {
        int i = 0;
        Object[] array = new Object[size];
        for (Object o : this) {
            array[i++] = o;
        }
        return array;
    }

    /**
     * Output of the set elements to the String string in Inorder order. Sets
     * items are output in ascending order by key.
     *
     * @return element string
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (E element : this)
        {
            sb.append(element.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Representation of a tree by symbols, see: unicode.org/charts/PDF/U2500.pdf
     * These are 4 possible terminal characters at the end of the tree branch
     */
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge = "\u25CF";
    private static final String vertical = "\u2502";
    private String horizontal;

    /* An additional method of deriving a set of elements into a single String string.
     * The string is formed by pushing the elements away from the edge,
     * depending on the level of the element in the tree. Can be used for printing to a screen or file
     * by studying the operation of tree algorithms.
     *
     * @author E. Kar?iauskas
     */
    @Override
    public String toVisualizedString(String dataCodeDelimiter)
    {
        horizontal = term[0] + term[0];
        return root == null ? ">" + horizontal : toTreeDraw(root, ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<E> node, String edge, String indent, String dataCodeDelimiter)
    {
        if (node == null)
        {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter));
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        sb.append(indent).append(edge).append(horizontal).append(term[t]).append(endEdge).append(
                split(node.element.toString(), dataCodeDelimiter)).append(System.lineSeparator());
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        sb.append(toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter));
        return sb.toString();
    }

    private String split(String s, String dataCodeDelimiter)
    {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0)
        {
            return s;
        }
        return s.substring(0, k);
    }

    /**
     * Creates and returns a copy of the set.
     *
     * @return Copy of the set.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        BstSet<E> cl = (BstSet<E>) super.clone();
        if (root == null) {
            return cl;
        }
        cl.root = cloneRecursive(root);
        cl.size = this.size;
        return cl;
    }

    private BstNode<E> cloneRecursive(BstNode<E> node)
    {
        if (node == null) {
            return null;
        }

        BstNode<E> clone = new BstNode<>(node.element);
        clone.left = cloneRecursive(node.left);
        clone.right = cloneRecursive(node.right);
        return clone;
    }




    /**
     * Returns a subset of the set to the element.
     *
     * @param element - Set element.
     * @return Returns a subset of the set to the element.
     */
    @Override
    public Set<E> headSet(E element)
    {
        Set<E> hset=new BstSet<E>();
        root= HeadRecusrsive(element,root,hset);
        return hset;
   }


    private BstNode<E> HeadRecusrsive(E element, BstNode<E> node, Set<E> headset)
    {
        if (node == null) {
            return null;
        }
        int cmp = c.compare(element,node.element);

        if (cmp>0) {
            headset.add(node.element);
            node.right = HeadRecusrsive(element, node.right,headset);
            node.left = HeadRecusrsive(element, node.left,headset);
        }
        else if (cmp<0) {

            //headset.add(node.element);
            node.left = HeadRecusrsive(element, node.left,headset);
            node.right= HeadRecusrsive(element,node.right,headset);

        } else {

            node.left = HeadRecusrsive(element, node.left,headset);
        }
        return node;

    }
    /**
     * Returns a subset of the set from element element1 to element2.
     *
     * @param element1 - the original element of a subset of the set.
     * @param element2 - the end element of a subset of the set.
     * @return Returns a subset of the set from element element1 to element2.
     */
    @Override
    public Set<E> subSet(E element1, E element2)
    {
        Set<E> tail=new BstSet<E>();
        root=subsetrecursive(element1,element2,root,tail);
        return tail;
    }
    private BstNode<E> subsetrecursive(E element1,E element2,BstNode<E> node,Set<E> headset )
    {
        if (node == null) {
            return null;
        }
        int cmp1 = c.compare(element1,node.element);
        int cmp2 = c.compare(element2,node.element);

        if ((cmp1<0)&&(cmp2>0)) {
            headset.add(node.element);
            node.right = subsetrecursive(element1,element2, node.right,headset);
            node.left = subsetrecursive(element1,element2, node.left,headset);
        }
        else if ((cmp1<0)&&(cmp2<0))
        {
            node.left = subsetrecursive(element1,element2, node.left,headset);
        }
        else {
            node.right = subsetrecursive(element1,element2, node.left,headset);
        }
        return node;
    }

    /**
     * Returns a subset of the set to the element.
     *
     * @param element - Set element.
     * @return Returns a subset of the set from the element.
     */
    @Override
    public Set<E> tailSet(E element)
    {
        Set<E> tset=new BstSet<E>();
        root= TailRecursive(element,root,tset);
        return tset;
    }



    private BstNode<E> TailRecursive(E element, BstNode<E> node, Set<E> headset )
    {
        if (node == null) {
            return null;
        }
        int cmp = c.compare(element,node.element);
        if (cmp>0)
        {
            node.left = TailRecursive(element, node.left,headset);//
            node.right = TailRecursive(element, node.right,headset);

        }
        else if (cmp<0) {

            headset.add(node.element);
            node.left = TailRecursive(element, node.left,headset);
            node.right= TailRecursive(element,node.right,headset);

        } else {

            node.right = TailRecursive(element, node.left,headset);
        }
        return node;
    }


    /**
     * Returns the direct iterator.
     *
     * @return Returns the direct iterator.
     */
    @Override
    public Iterator<E> iterator()
    {
        return new IteratorBst(true);
    }

    /**
     * Returns the reverse iterator.
     *
     * @return Returns the reverse iterator.
     */
    @Override
    public Iterator<E> descendingIterator()
    {
        return new IteratorBst(false);
    }

    /**
     * Internal object collection iterator class. Iterators: ascending and descending.
     * The collection is iterated by visiting each item once in in order.
     * All visited items are stored on the stack. The stack is used from the java.util package,
     * but you can create your own.

     */
    private class IteratorBst implements Iterator<E>
    {

        private Stack<BstNode<E>> stack = new Stack<>();
        // Specifies the direction of the iteration collection, true - ascending, false - descending
        private boolean ascending;
        // Specifies the parent of the current tree element. Required for disposal.
        private BstNode<E> parent = root;

        IteratorBst(boolean ascendingOrder)
        {
            this.ascending = ascendingOrder;
            this.toStack(root);
        }

        @Override
        public boolean hasNext()
        {
            return !stack.empty();
        }

        @Override
        public E next()
        {
            if (!stack.empty()) {
                // Returns the last item placed on the stack
                BstNode<E> n = stack.pop();
                // The top of the father is remembered. Need to remove () method
                parent = (!stack.empty()) ? stack.peek() : root;
                BstNode<E> node = (ascending) ? n.right : n.left;
                // The right-hand sub-tree n looks for the minimum element
                // and all items in the search path are placed in the stack
                toStack(node);
                return n.element;
            } else { // If the stack is empty
                return null;
            }
        }

        @Override
        public void remove()
        {
           while(hasNext() && next()!=null)
           {
               BstSet.this.remove(next());
           }
        }

        private void toStack(BstNode<E> n)
        {
            while (n != null) {
                stack.push(n);
                n = (ascending) ? n.left : n.right;
            }
        }
    }

    /**
     * Internal class of the collection node
     *
     * @param <N> node element data type
     */

}
