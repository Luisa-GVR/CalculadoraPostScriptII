import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el calculo en postcript");
        String input = sc.nextLine();

        String[] stackSentence = input.split(" ");

        Stack <String> stringStack = new Stack<>();

        for (String word : stackSentence) {
            System.out.println(word);

            stringStack.push(word);
        }

    }
}
