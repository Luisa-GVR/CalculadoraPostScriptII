import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static BufferedWriter registro;
    private static void registrarBitacora(String mensaje){
        try{
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHora = fecha.format(new Date());
            registro = new BufferedWriter(new FileWriter("bitacora.txt",true));
            registro.write(fechaHora + " - " + mensaje + "\n");
            registro.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        Stack<String> stringStack = new Stack<>();

        boolean entrar;
        boolean definiendoValor = false;
        boolean valorYaDefinido = false;
        double valorDefinido = 0;
        String nombreDefinicion = "";
        double var1;
        double var2;
        HashMap<String, Double> definiciones = new HashMap<>();
        HashMap<String, Double> tempDefiniciones = new HashMap<>();

        boolean valorNoAceptado = false;


        try{
            registro = new BufferedWriter(new FileWriter("bitacora.txt",true));
        while (exit) {
            System.out.println("Escribe el calculo en postcript");
            String input = sc.nextLine();

            String[] stackSentence = input.split(" ");


            for (int i = 0; i < stackSentence.length; i++) {
                stackSentence[i] = stackSentence[i].toLowerCase();
            }


            for (String word : stackSentence) {
                entrar = true; //reset

                if (!definiendoValor) {
                    if (word.equals("add")) { //suma
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 + var2));

                    }
                    if (word.equals("sub")) { //resta
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var2 - var1));

                    }

                    if (word.equals("div")) { //division
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 ));

                    }

                    if (word.equals("mul")) { //multiplicacion
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 * var2));

                    }

                    if (word.equals("dup")) { //duplicar
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1));
                        stringStack.push(String.valueOf(var1));
                    }

                    if (word.equals("exch")) { //intercambiar
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1));
                        stringStack.push(String.valueOf(var2));
                    }

                    if (word.equals("pop")) { //pop
                        stringStack.pop();
                    }

                    if (word.equals("eq")){ // equals
                        entrar = false;
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        if (var1 == var2){
                            stringStack.push("true");
                        } else {
                            stringStack.push("false");
                        }

                    }

                    if (word.startsWith("/")){
                        definiendoValor = true;
                        nombreDefinicion = word.substring(1, word.length());
                    }


                    if (word.equals("pstack")){ // Imprime
                        if (!stringStack.isEmpty()) {
                            System.out.println(stringStack.peek());
                        } else {
                            registrarBitacora("No hay valores en la pila");
                            System.out.println("No hay valores en la pila");
                        }

                    }

                    if (definiciones.containsKey(word)){
                        System.out.println(definiciones.get(word));

                    }



                    if (word.equals("quit")){
                        System.exit(3);
                    }

                    if (entrar) {
                        stringStack.push(word);

                    }


                } else { //definimos el valor
                    if (!valorYaDefinido) {
                        valorDefinido = Double.parseDouble(word);
                        valorYaDefinido = true;
                    } else {
                        if (word.equals("def")) {
                            definiciones.put(nombreDefinicion, valorDefinido);
                            valorYaDefinido = false;
                            definiendoValor = false;
                        } else {
                            registrarBitacora("No agrego 'def' a la hora de definir su valor");
                            valorYaDefinido = false;
                            definiendoValor = false;
                            // Restaurar el HashMap temporal
                            definiciones.putAll(tempDefiniciones);
                            tempDefiniciones.clear();
                        }
                    }
                }
            }
        }
        } catch (IOException e){
            e.printStackTrace();;
        } finally {
            try{
                if (registro != null){
                    registro.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
