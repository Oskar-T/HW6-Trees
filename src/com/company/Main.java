package com.company;

public class Main {
    public static void main (String[] args) {

        String mobyURL = "http://gutenberg.org/files/2701/2701-0.txt";
        String[] words =
                ReadViaURL.readWords(mobyURL, true, true, true);

        // 1
        System.out.println("Amount of words " + words.length);
        // 2
        BinarySearchTree<String> binarySearchTree = new <String>BinarySearchTree();
        binarySearchTree.add(words);
        // 3
        int length = binarySearchTree.getSize();
        System.out.println("Size " + length);
        // This tells that this text has 193976 words repeating
        // 4
        // x = log2(n+1)
        // x = log2(21468)
        // x = 14.4
        System.out.println("Max Depth is: " + (length-1));
        System.out.println("Min Depth is: " + 15);
        // 5
        System.out.println(binarySearchTree.getDepth());
        // 6
        int counter = 0;
        for (String s : binarySearchTree.inorder()) {
            System.out.println(s);
            counter+=1;
            if(counter == 10) {
                break;
            }
        }

    }



}
