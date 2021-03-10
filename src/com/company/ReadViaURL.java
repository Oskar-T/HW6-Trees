package com.company;

import java.util.Scanner;
import java.net.URL;
import java.util.ArrayList;

public class ReadViaURL
{
    public static String[] readWords (String urlAddress,
                                      boolean removePunctuation,
                                      boolean toLowercase,
                                      boolean removeDigits)
    {
        ArrayList<String> wordList = new ArrayList<> ();
        int counter = 1;
        try
        {
            URL url = new URL (urlAddress);

            try (Scanner input = new Scanner (url.openStream ()))
            {
                while (input.hasNext ())
                {
                    String word = input.next();
                    if (removePunctuation)
                        word = word.replaceAll ("\\p{Punct}", "");
                    if (word.length () > 0)
                    {
                        if (removeDigits)
                             word = word.replaceAll ("\\d", "");

                        if (word.length () > 0)
                        {
                            if (toLowercase)
                                word = word.toLowerCase ();
                            wordList.add (word);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println ("got exception: " + e);
                System.exit (1);
            }
        }
        catch (Exception e)
        {
            System.out.println ("got exception: " + e);
            System.exit (1);
        }
        return wordList.toArray (new String [0]);
    }
}
