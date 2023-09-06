import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el calculo en postcript");
        String input = sc.nextLine();

        String[] stackSentence = input.split(" ");

        for (int i = 0; i < stackSentence.length; i++) {
            stackSentence[i] = stackSentence[i].toLowerCase();
        }

        Stack<String> stringStack = new Stack<>();
        boolean entrar;
        int var1;
        int var2;

        for (String word : stackSentence) {
            entrar = true; //reset

            if (word.equals("add")) { //suma
                entrar = false;
                var1 = Integer.parseInt(stringStack.pop());
                var2 = Integer.parseInt(stringStack.pop());
                stringStack.push(String.valueOf(var1 + var2));

            }

            if (word.equals("div")) {
                entrar = false;
                var1 = Integer.parseInt(stringStack.pop());
                var2 = Integer.parseInt(stringStack.pop());
                stringStack.push(String.valueOf(var1 / var2));

            }

            if (word.equals("mul")) {
                entrar = false;
                var1 = Integer.parseInt(stringStack.pop());
                var2 = Integer.parseInt(stringStack.pop());
                stringStack.push(String.valueOf(var1 * var2));

            }
            if (entrar) {
                stringStack.push(word);

            }


        }

        System.out.println(stringStack.pop());
    }

}
