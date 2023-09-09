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




        try{
            registro = new BufferedWriter(new FileWriter("bitacora.txt",true));
        while (exit) {
            boolean operacionRealizada = false;
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
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                                var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 + var2));
                        operacionRealizada = true;
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'add'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'add' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'add'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'add' - Error guardado en la bitacora");
                        }
                    }
                    if (word.equals("sub")) { //resta
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var2 - var1));
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'sub'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'sub' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'sub'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'sub' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("div")) { //division
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 ));
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'div'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'div' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'div'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'div' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("mul")) { //multiplicacion
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1 * var2));
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'mul'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'mul' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'mul'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'mul' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("dup")) { //duplicar
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1));
                        stringStack.push(String.valueOf(var1));
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'dup'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'dup' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'dup'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'dup' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("exch")) { //intercambiar
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        stringStack.push(String.valueOf(var1));
                        stringStack.push(String.valueOf(var2));
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'exch'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'exch' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'exch'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'exch' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("pop")) { //pop
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        stringStack.pop();
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'pop'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'pop' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'pop'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'pop' - Error guardado en la bitacora");
                        }
                    }

                    if (word.equals("eq")){ // equals
                        entrar = false;
                        if (stringStack.size() >=2){
                            if (Numerico(stringStack.peek()) && Numerico(stringStack.get(stringStack.size() - 2))) {
                        var1 = Double.parseDouble(stringStack.pop());
                        var2 = Double.parseDouble(stringStack.pop());
                        if (var1 == var2){
                            stringStack.push("true");
                        } else {
                            stringStack.push("false");
                        }
                        operacionRealizada = true;
                            }else {
                                registrarBitacora("No hay suficientes números en la pila para realizar la operación 'eq'");
                                System.out.println("No hay suficientes números en la pila para realizar la operación 'eq' - Error guardado en la bitacora");
                            }
                        }else {
                            registrarBitacora("No hay suficientes números en la pila para realizar la operación 'eq'");
                            System.out.println("No hay suficientes números en la pila para realizar la operación 'eq' - Error guardado en la bitacora");
                        }
                    }

                    if (word.startsWith("/")){
                        definiendoValor = true;
                        nombreDefinicion = word.substring(1, word.length());
                        operacionRealizada = true;
                    }


                    if (word.equals("pstack")){     //Imprime
                        if (!operacionRealizada){
                            registrarBitacora("Se escribió 'pstack' sin operaciones previas");
                            System.out.println("Se escribió 'pstack' sin operaciones previas - Error guardado en la bitacora");
                        } else if (!stringStack.isEmpty()) {
                            System.out.println(stringStack.peek());
                        }

                    } else

                    if (definiciones.containsKey(word)){
                        word= String.valueOf(definiciones.get(word));


                    }
                    if (Numerico(word)) {
                        entrar = false;
                        stringStack.push(word); // Agregar números a la pila
                        operacionRealizada = true;
                    }



                    if (word.equals("quit")){
                        System.exit(3);
                    }

                    if (entrar) {
                        stringStack.push(word);

                    }
                    if (!operacionRealizada && !word.equals("pstack") && !definiciones.containsKey(word) && !word.equals("add") && !word.equals("sub") && !word.equals("div")
                    && !word.equals("mul") && !word.equals("dup") && !word.equals("exch") && !word.equals("pop") && !word.equals("eq")) {
                        registrarBitacora("Texto inválido: " + word);
                        System.out.println("Texto inválido: " + word + " - Error guardado en la bitacora");
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
    private static boolean Numerico(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
