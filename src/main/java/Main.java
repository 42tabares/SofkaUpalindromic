import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        String s = "Mr Owl ate my metal worm";
        System.out.println(checkPalindrome(s));

        Scanner sc = new Scanner(System.in);
        System.out.println("Put in a series of words or sentences separated by |\n>");
        String stringSet=sc.nextLine();
        palindroCheker(stringSet);
    }

    public static void palindroCheker(String stringSet){

        Flux<String> separatedStrings = Flux.fromIterable(Arrays.stream(stringSet.split("\\|")).toList());

        Flux<String> words = separatedStrings.filter(word -> word.split(" ").length == 1);
        Flux<String> sentences = separatedStrings.filter(word -> word.split(" ").length > 1);

        Predicate<String> isPalindrome = s -> checkPalindrome(s);
        Flux<String> palindroWords = words.filter(isPalindrome);
        Flux<String> palindroSentences = sentences.filter(isPalindrome);

        palindroWords.subscribe(word -> System.out.println("This is a palindromic word: " + palindromize(word)));
        palindroSentences.subscribe(sentence -> System.out.println("This is a palindromic Sentence: " + palindromize(sentence)));

        //check if last is CapsLock!
        System.out.println("There are " + words.toStream().count() + " words");
        System.out.println("There are " + sentences.toStream().count()  + " sentences");


        /* Unused code but still pretty cool: collects into a map which key is the word and value is boolean if palindromic
        Map<String, Boolean> wordsCheck = words.collect(Collectors.toMap(word -> word, word -> reverse(word).equals(word)));
        Map<String, Boolean> sentencesCheck = sentences.collect(Collectors.toMap(sentence -> sentence,sentence -> reverse(sentence).equals(sentence)));
        */
    }

    public static Boolean checkPalindrome(String string){
        String adapted = string.toLowerCase().replace(" ","");
        return new StringBuilder(adapted).reverse().toString().equals(adapted);
    }

    public static String palindromize(String string){
        String adapted = string.replace(" ","");
        return new StringBuilder(adapted).reverse().toString();
    }
}
