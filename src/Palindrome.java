import lib.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Created by Alexis Espinoza on 12/28/15.
 */
public class Palindrome {
    public static void main(String args[]) {
        String inputString = new String();
        BufferedReader bufferRead;
        BufferedReader bufferReadSubMenu;

        while (!inputString.equals("X")) {
            System.out.println(" ");
            System.out.println("********************************");
            System.out.println("************Menu Option*********");
            System.out.println("********************************");
            System.out.println("Press 1 to check if a sentence is Palindrome");
            System.out.println("Press 2 to load words or phrases from text file and check palindromeness per Strings");
            System.out.println("Press X to exit the program");
            System.out.println("********************************");
            System.out.println("********************************");
            try {
                bufferRead = new BufferedReader(new InputStreamReader(System.in));// Reads user input from console
                inputString = bufferRead.readLine();
                switch (inputString) {
                    case "1":System.out.println("Type in a word or phrase");
                             bufferReadSubMenu = new BufferedReader(new InputStreamReader(System.in));
                             isPalindrome(bufferReadSubMenu.readLine());
                             pressEnterToContinue(System.in);
                             break;//
                    case "2":massivePalidromeEval();//Loads file with sentences located in src/input/file.txt
                             pressEnterToContinue(System.in);
                             break;
                    case "X":System.out.println("Enjoy the day!!");
                             System.exit(0);
                             break;
                    default: System.out.println("Wrong input");
                             break;

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Returns  true or false if the string word or phrase is palindrome
     * This method will also output  whether or not the string is a palindrome
     * A palindrome string must be at least one in length.
     * The evaluation of the potential palindrome string starts
     * on both ends of the string(First and last character)
     * in every cycle, both characters are evaluated for equality. If both are equal then their indexes are incremented and
     * lowered respectively until the first index(i) >=last index(j). If at some  point
     * the characters in the String are different then the variable sentenceIdPalindromeSoFar turns false
     * and thus the output of the method is false meaning the String is not Palindrome. Otherwise the String will be Palindrome
     *
     * @param  sentence a word or phrase to evaluate for palindromeness
     * @return boolean  if the string word or phrase is palindrome or not
     */
    private static boolean isPalindrome(String sentence){
        boolean sentenceIdPalindromeSoFar=false;
        sentence=preProcess(sentence);// gets rid of the non-alphanumeric characters and white spaces
        if(sentence.length()>0){ // First condition. A string's length must be greater than one and cannot be a white space
                                 // nor a non-alphanumeric character
            int j=sentence.length()-1;
            int i=0;
            sentenceIdPalindromeSoFar=true;
            while((i<j)&&(sentenceIdPalindromeSoFar)){
                if(sentence.charAt(i)!=sentence.charAt(j)){// checking equality for
                                                          // both pivot (Firts and Last) of the preprocessed string
                    sentenceIdPalindromeSoFar=false;
                }
                i++;// increments the lower pivot
                j--; //decrements the higher pivot
            }
        }
        if(sentenceIdPalindromeSoFar)
            System.out.print("The input sentence is PALINDROME. ");
        else
            System.out.print("The input sentence is NOT PALINDROME. ");
        return sentenceIdPalindromeSoFar;
    }
    /**
     * Returns a word or phrase without non-alphanumeric characters.
     * All letters in the string are lowercased and white spaces are supressed
     *
     * @param  sentence a word or phrase to evaluate for palindromeness
     * @return lowercased sentence without non-alphanumeric characters and supressed white spaces
     */
    private static String preProcess(String sentence){
        return sentence.replaceAll("\\P{Alnum}", "").replaceAll("\\s","").toLowerCase();
    }
    /**
     * Reads input("ENTER") from console
     *
     * @param  in  input form console
     *
     */
    private static void pressEnterToContinue(InputStream in)throws IOException{
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press ENTER to continue");
        bufferRead.readLine();
    }
    /**
     * Evaluates Strings for Palindromeness tossed in a file located at src/input/file.txt
     * You can change the content of this file while the program is running in order to evaluate
     * other sets of strings
     */
    private static void massivePalidromeEval() {
        String sentence = null;
        ArrayList<String> arrayList = FileHandler.FileRead("inputFileLocation");
        //Cycle through every sentence and check for Palindromeness for each of them
        for (int i = 0; i < arrayList.size(); i++) {
            sentence = arrayList.get(i);
            System.out.print(sentence + ".--> ");
            isPalindrome(sentence);
            System.out.println("\n");
        }
    }
}
