import java.util.Random;
import java.util.Scanner;

public class JuegoAdivinanzaAlternativo {
    private static final int LIMITE_SUPERIOR = 100;
    private static final int LIMITE_INFERIOR = 1;
    private static final int INTENTOS_MAXIMOS = 10;
    
    public static void main(String[] args) {
        iniciarJuego();
    }
    
    private static void iniciarJuego() {
        // Configuración inicial
        Random generador = new Random();
        Scanner entrada = new Scanner(System.in);
        int numeroSecreto = generador.nextInt(LIMITE_SUPERIOR) + LIMITE_INFERIOR;
        
        // Variables de control del juego
        int intentosRealizados = 0;
        int numeroIngresado;
        String resultado = "";
        
        // Mostrar instrucciones
        imprimirInstrucciones();
        
        // Bucle principal del juego
        do {
            // Incrementar contador antes de solicitar entrada
            intentosRealizados++;
            
            // Mostrar intentos restantes
            System.out.println("Intento " + intentosRealizados + " de " + INTENTOS_MAXIMOS);
            
            // Obtener la entrada del usuario
            numeroIngresado = solicitarNumero(entrada);
            
            // Evaluar el intento y obtener resultado
            resultado = evaluarIntento(numeroIngresado, numeroSecreto);
            
            // Mostrar el resultado de la evaluación
            System.out.println(resultado);
            
        } while (!juegoTerminado(resultado, intentosRealizados));
        
        // Mostrar mensaje final si perdió
        if (intentosRealizados >= INTENTOS_MAXIMOS && !resultado.contains("Felicidades")) {
            System.out.println("Lo siento, has agotado tus intentos. El número era " + numeroSecreto);
        }
        
        // Cerrar recursos
        entrada.close();
    }
    
    private static void imprimirInstrucciones() {
        System.out.println("¡Bienvenido al juego de adivinanza!");
        System.out.println("Adivina el número entre " + LIMITE_INFERIOR + " y " + LIMITE_SUPERIOR + ". Tienes " + INTENTOS_MAXIMOS + " intentos.");
    }
    
    private static int solicitarNumero(Scanner scanner) {
        System.out.print("Introduce tu número: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, introduce un número válido.");
            scanner.next(); // Descarta la entrada no válida
            System.out.print("Introduce tu número: ");
        }
        return scanner.nextInt();
    }
    
    private static String evaluarIntento(int numeroIngresado, int numeroSecreto) {
        if (numeroIngresado == numeroSecreto) {
            return "¡Felicidades! Adivinaste el número.";
        } else if (numeroIngresado < numeroSecreto) {
            return "El número es mayor.";
        } else {
            return "El número es menor.";
        }
    }
    
    private static boolean juegoTerminado(String resultado, int intentosRealizados) {
        // El juego termina si adivinó o si agotó los intentos
        return resultado.contains("Felicidades") || intentosRealizados >= INTENTOS_MAXIMOS;
    }
}