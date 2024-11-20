package edu.ktu.ds.lab2.utils;

import java.util.Comparator;

/**
 * Sorted object collections - realization of a set with AVL-tree.
 *
 * @param <E> Set element type. The Comparable <E> interface must be satisfied, or
 *            an object satisfying the Comparator <E> interface must be passed
 *            through the class constructor.
 * 
 * @author darius.matulis@ktu.lt
 * @užduotis Review and clarify the methods provided.
 */
public class AvlSet<E extends Comparable<E>> extends BstSet<E>
        implements SortedSet<E> {

    public AvlSet()
    {

    }

    public AvlSet(Comparator<? super E> c)
    {
        super(c);
    }

    /**
     * A new element is added to the set.
     *
     * @param element
     */
    @Override
    public void add(E element)
    {
        if (element == null)
        {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }
        root = addRecursive(element, (AVLNode<E>) root);

    }

    /**
     * The private recursive method is used in the add method;
     *
     * @param element
     * @param node
     * @return
     */
    private AVLNode<E> addRecursive(E element, AVLNode<E> node)
    {
        if (node == null)
        {
            size++;
            return new AVLNode<>(element);
        }
        int cmp = c.compare(element, node.element);

        if (cmp < 0)
        {
            node.setLeft(addRecursive(element, node.getLeft()));
            if ((height(node.getLeft()) - height(node.getRight())) == 2) {
                int cmp2 = c.compare(element, node.getLeft().element);
                node = (cmp2 < 0) ? rightRotation(node) : doubleRightRotation(node);
            }
        }
        else if (cmp > 0)
        {
            node.setRight(addRecursive(element, node.getRight()));
            if ((height(node.getRight()) - height(node.getLeft())) == 2)
            {
                int cmp2 = c.compare(node.getRight().element, element);
                node = (cmp2 < 0) ? leftRotation(node) : doubleLeftRotation(node);
            }
        }
        node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        return node;
    }

    private int getBalance(AVLNode<E> n) {
        return height(n.getLeft()) - height(n.getRight());
    }

    private AVLNode<E> getMinVal(AVLNode<E> node) {
        while(node.getLeft() != null) {
            node=node.getLeft();
        }
        return node;
    }
    private int max(int right, int left) {

        // if right is greater return right
        if (right >= left) {

            return right;
        }
        else {
            return left;
        }
    }
    /**
     * Removes an item from a set.
     *
     * @param element
     */
    @Override
    public void remove(E element)
    {
        if(element == null) {
            throw new IllegalArgumentException("Element is Empty!!");
        }
        root = removeRecursive(element, (AVLNode<E>) root);
    }

    private AVLNode<E> removeRecursive(E element, AVLNode<E> Node)
    {
        if (Node == null)
        {
            return Node;
        }

        int cmp = c.compare(element, Node.element);

        if (cmp < 0)
        {
            Node.setLeft(removeRecursive(element, Node.getLeft()));
        }
        else if (cmp > 0)
        {
            Node.setRight(removeRecursive(element, Node.getRight()));
        }
        else {
            if ((Node.getLeft() == null) || (Node.getRight() == null))
            {
                AVLNode<E> temp = null;
                if (temp == Node.getLeft())
                    temp = Node.getRight();
                else
                    temp = Node.getLeft();

                if (temp == null)
                {
                    temp = Node;
                    Node = null;
                }
                else
                    Node = temp;
            }
            else
            {
                AVLNode<E> temp = getMinVal(Node.getRight());
                Node.element = temp.element;
                Node.setRight(removeRecursive(temp.element, Node.getRight()));
            }
            size--;
        }

        if (Node == null)
            return Node;

        Node.height = max(height(Node.getLeft()), height(Node.getRight())) + 1;

        int balance = getBalance(Node);

        if (balance > 1 && getBalance(Node.getLeft()) >= 0)
            return rightRotation(Node);

        if (balance > 1 && getBalance(Node.getLeft()) < 0)
        {
            root.left = leftRotation(Node.getLeft());
            return rightRotation(Node);
        }

        if (balance < -1 && getBalance(Node.getRight()) <= 0)
            return leftRotation(Node);

        if (balance < -1 && getBalance(Node.getRight()) > 0)
        {
            root.right = rightRotation(Node.getRight());
            return leftRotation(Node);
        }

        return Node;
    }

    // Additional private methods used to implement operations with the set
    // AVL-tree;

    //           n2
    //          /                n1
    //         n1      ==>      /  \
    //        /                n3  n2
    //       n3

    private AVLNode<E> rightRotation(AVLNode<E> n2)
    {
        AVLNode<E> n1 = n2.getLeft();
        n2.setLeft(n1.getRight());
        n1.setRight(n2);
        n2.height = Math.max(height(n2.getLeft()), height(n2.getRight())) + 1;
        n1.height = Math.max(height(n1.getLeft()), height(n2)) + 1;
        return n1;
    }

    private AVLNode<E> leftRotation(AVLNode<E> n1)
    {
        AVLNode<E> n2 = n1.getRight();
        n1.setRight(n2.getLeft());
        n2.setLeft(n1);
        n1.height = Math.max(height(n1.getLeft()), height(n1.getRight())) + 1;
        n2.height = Math.max(height(n2.getRight()), height(n1)) + 1;
        return n2;
    }

    //            n3               n3
    //           /                /                n2
    //          n1      ==>      n2      ==>      /  \
    //           \              /                n1  n3
    //            n2           n1
    //
    private AVLNode<E> doubleRightRotation(AVLNode<E> n3)
    {
        n3.left = leftRotation(n3.getLeft());
        return rightRotation(n3);
    }

    private AVLNode<E> doubleLeftRotation(AVLNode<E> n1)
    {
        n1.right = rightRotation(n1.getRight());
        return leftRotation(n1);
    }

    private int height(AVLNode<E> n)
    {

        return (n == null) ? -1 : n.height;
    }

    /**
     * Internal class of the collection node
     *
     * @param <N> node element data type
     */
    protected class AVLNode<N> extends BstNode<N>
    {
        protected int height;

        protected AVLNode(N element)
        {
            super(element);
            this.height = 0;
        }

        protected void setLeft(AVLNode<N> left) {
            this.left = left;
        }

        protected AVLNode<N> getLeft() {
            return (AVLNode<N>) left;
        }

        protected void setRight(AVLNode<N> right) {
            this.right = right;
        }

        protected AVLNode<N> getRight() {
            return (AVLNode<N>) right;
        }
    }
}
