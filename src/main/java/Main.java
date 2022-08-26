import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Put in a series of words or sentences separated by |\n>");
        String stringSet=sc.nextLine();
        palindroCheker(stringSet);
    }

    public static void palindroCheker(String stringSet){

        //Adaptar a flux
        List<String> separatedStrings = Arrays.stream(stringSet.split("\\|")).toList();

        Stream<String> words = separatedStrings.stream().filter(word -> word.split(" ").length == 1);
        Stream<String> sentences = separatedStrings.stream().filter(word -> word.split(" ").length > 1);

        List<String> palindroWords = words.filter(word -> reverse(word).equals(word)).toList();
        List<String> palindroSentences = sentences.filter(sentence -> reverse(sentence).equals(sentence)).toList();

        long wordsNumber = separatedStrings.stream().filter(word -> word.split(" ").length == 1).count();

        //subscribe -> with sout
        //check if last is CapsLock!
        System.out.println("There are " + wordsNumber + " words");
        System.out.println("There are " + (separatedStrings.size() - wordsNumber) + " sentences");
        System.out.println("Palindromic words: " + palindroWords);
        System.out.println("Palindromic sentences: " + palindroSentences);

        /* Unused code but still pretty cool: collects into a map which key is the word and value is boolean if palindromic
        Map<String, Boolean> wordsCheck = words.collect(Collectors.toMap(word -> word, word -> reverse(word).equals(word)));
        Map<String, Boolean> sentencesCheck = sentences.collect(Collectors.toMap(sentence -> sentence,sentence -> reverse(sentence).equals(sentence)));
        */
    }

    public static String reverse(String string){
        String inverse = new StringBuilder(string).reverse().toString();
        return inverse;
    }
}
