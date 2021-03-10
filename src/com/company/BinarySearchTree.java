package com.company;

import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>>
{
    public BinarySearchTree ()
    {
        node = null;
        left = null;
        right = null;
        size = 0;
        depth = 0;
    }

    public int getSize ()
    {
        return size;
    }

    public int getDepth ()
    {
        return depth;
    }

    public void add (T value)
    {
        addTo (this, value);
    }

    public void add (T[] values)
    {
        for (T v : values)
            add (v);
    }

    protected static <V extends Comparable<V>> BinarySearchTree<V> addTo (BinarySearchTree<V> tree,
                                                                          V value)
    {
        if (tree == null)
        {
            tree = new BinarySearchTree<V> ();
            tree.node = value;
        }
        else
        {
            if (tree.node == null)
                tree.node = value;
            else
            {
                int cmpResult = value.compareTo (tree.node);
                if (cmpResult < 0)
                    tree.left = addTo (tree.left, value);
                else if (cmpResult > 0)
                    tree.right = addTo (tree.right, value);
                // note: if cmpResult==0, value already in tree, no change
            }
        }

        // update size
        tree.size = 1
                + (tree.left == null ? 0 : tree.left.getSize ())
                + (tree.right == null ? 0 : tree.right.getSize ());

        // update depth
        tree.depth = 1 + Math.max (tree.left == null ? 0 : tree.left.getDepth (),
                tree.right == null ? 0 : tree.right.getDepth ());

        return tree;
    }

    public boolean find (T value)
    {
        BinarySearchTree<T> tree = this;
        boolean found = false;

        while (!found && tree != null && tree.node != null)
        {
            int cmpResult = value.compareTo (tree.node);
            if (cmpResult == 0)
                found = true;
            else if (cmpResult < 0)
                tree = tree.left;
            else
                tree = tree.right;
        }

        return found;
    }

    public Iterable<T> inorder ()
    {
        List<T> list = new ArrayList<T> ();
        inorder (this, list);
        return list;
    }

    protected static <V extends Comparable<V>> void inorder (BinarySearchTree<V> tree, List<V> list)
    {
        if (tree.left != null)
            inorder (tree.left, list);

        if (tree.node != null)
            list.add (tree.node);

        if (tree.right != null)
            inorder (tree.right, list);
    }

    public Iterable<T> postorder ()
    {
        List<T> list = new ArrayList<T> ();
        postorder (this, list);
        return list;
    }

    protected static <V extends Comparable<V>> void postorder (BinarySearchTree<V> tree, List<V> list)
    {
        if (tree.left != null)
            postorder (tree.left, list);

        if (tree.right != null)
            postorder (tree.right, list);

        if (tree.node != null)
            list.add (tree.node);
    }

    public Iterable<T> preorder ()
    {
        List<T> list = new ArrayList<T> ();
        preorder (this, list);
        return list;
    }

    protected static <V extends Comparable<V>> void preorder (BinarySearchTree<V> tree, List<V> list)
    {
        if (tree.node != null)
            list.add (tree.node);

        if (tree.left != null)
            preorder (tree.left, list);

        if (tree.right != null)
            preorder (tree.right, list);
    }

    private T node;
    private BinarySearchTree<T> left, right;
    private int size, depth;
}
