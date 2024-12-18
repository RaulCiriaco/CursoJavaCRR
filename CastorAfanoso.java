import java.util.Scanner;

public class CastorAfanoso {

    // Define los estados
    private static final String ESTADO_A = "A";
    private static final String ESTADO_PARADA = "@";
    
    // Define la cinta y posición del cabezal
    private char[] cinta;
    private int posicionCabezal;
    private String estadoActual;
    
    // Constructor que inicializa la cinta y el estado
    public CastorAfanoso(String cintaInicial) {
        this.cinta = cintaInicial.toCharArray();
        this.posicionCabezal = 1;  // Empieza en la primera posición
        this.estadoActual = ESTADO_A;
    }
    
    // Método que ejecuta un paso de la máquina
    public void ejecutarPaso() {
        if (estadoActual.equals(ESTADO_PARADA)) {
            System.out.println("La máquina está en el estado de parada.");
            return;
        }

        System.out.print("Cinta antes: ");
        imprimirCinta();

        char simboloActual = cinta[posicionCabezal];
        if (estadoActual.equals(ESTADO_A)) {
            if (simboloActual == '0') {
                cinta[posicionCabezal] = '1';  // Escribe 1 en lugar de 0
                estadoActual = ESTADO_PARADA;  // Cambia al estado de parada
                System.out.println("Lee 0, escribe 1, va a @" );
            } else if (simboloActual == '1') {
                cinta[posicionCabezal] = '1';  // Escribe 1 (aunque ya es 1)
                estadoActual = ESTADO_PARADA;  // Cambia al estado de parada
                System.out.println("Lee 1, escribe 1, va a @" );
            }
        }

        System.out.print("Cinta después: ");
        imprimirCinta();
        System.out.println("Estado actual: " + estadoActual);
    }
    
    // Método que imprime la cinta y la posición del cabezal
    private void imprimirCinta() {
        for (int i = 0; i < cinta.length; i++) {
            if (i == posicionCabezal) {
                System.out.print("[" + cinta[i] + "]");
            } else {
                System.out.print(" " + cinta[i] + " ");
            }
        }
        System.out.println();
    }

    // Método principal para ejecutar toda la máquina
    public void ejecutar() {
        while (!estadoActual.equals(ESTADO_PARADA)) {
            ejecutarPaso();
            System.out.println();
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar longitud de la cinta
        System.out.print("Ingrese la longitud de la cinta: ");
        int longitud = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        // Solicitar los valores de la cinta
        String cintaInicial;
        while (true) {
            System.out.print("Ingrese los valores de la cinta (0 y 1, longitud " + longitud + "): ");
            cintaInicial = scanner.nextLine();
            
            // Validar que la longitud de la entrada sea correcta y solo contenga 0 y 1
            if (cintaInicial.length() == longitud && cintaInicial.matches("[01]+")) {
                break;
            } else {
                System.out.println("Entrada inválida. Asegúrese de ingresar una secuencia de 0s y 1s con la longitud especificada.");
            }
        }

        // Crear y ejecutar la máquina de Turing
        CastorAfanoso maquina = new CastorAfanoso(cintaInicial);
        maquina.ejecutar();
        scanner.close();
}
}