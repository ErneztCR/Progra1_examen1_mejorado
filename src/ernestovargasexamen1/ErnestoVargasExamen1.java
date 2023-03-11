package ernestovargasexamen1;

/**
 *
 * @author ervargas
 */

import java.util.Scanner;

public class ErnestoVargasExamen1 {

    static final int TAM_VECTOR = 10;
    static int[] entradasSolNorteSur = new int[TAM_VECTOR];
    static int[] entradasSombraEsteOeste = new int[TAM_VECTOR];
    static int[] entradasPreferencial = new int[TAM_VECTOR];
    static int acumuladoSolNorteSur = 0;
    static int acumuladoSombraEsteOeste = 0;
    static int acumuladoPreferencial = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n==== Programa de ventas de entradas ====\n");
            System.out.println("1. Ingresar datos de venta");
            System.out.println("2. Inicializar arreglo");
            System.out.println("3. Estadísticas");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            if (scanner.hasNextInt()) { // verificar si hay una entrada entera disponible
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        ingresarDatosVenta();
                        break;
                    case 2:
                        inicializarArreglo();
                        break;
                    case 3:
                        mostrarEstadisticas();
                        break;
                    case 4:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Entrada invalida."); // mostrar un mensaje de error si la entrada no es un entero
                scanner.next(); // consumir la entrada inválida para evitar un bucle infinito
            }
        }
        scanner.close(); // cerrar el Scanner fuera del ciclo principal
    }

    public static void ingresarDatosVenta() {
        Scanner scanner = new Scanner(System.in);
        boolean ventaValida = false;
        while (!ventaValida) {
            System.out.print("\nIngrese número de factura: ");
            int numFactura = scanner.nextInt();
            scanner.nextLine(); // consumir la nueva línea pendiente
            if (numFactura < 1 || numFactura > TAM_VECTOR) {
                System.out.println("ERROR: Número de factura inválido. Debe ser entre 1 y " + TAM_VECTOR + ".");
                continue;
            }
            System.out.print("\nIngrese cédula: ");
            String cedula = scanner.nextLine();
            System.out.print("\nIngrese nombre del comprador: ");
            String nombre = scanner.nextLine();
            System.out.println("\nIngrese localidad:");
            System.out.println("1. Sol Norte/Sur");
            System.out.println("2. Sombra Este/Oeste");
            System.out.println("3. Preferencial");
            int localidad = scanner.nextInt();
            System.out.print("\nIngrese cantidad de entradas: ");
            int cantEntradas = scanner.nextInt();
            scanner.nextLine(); // consumir la nueva línea pendiente
    
            double precioEntrada = 0; // Inicializar precioEntrada con un valor predeterminado
    
            if (cantEntradas <= 4) {
                double subtotal;
                double cargosServicios = cantEntradas * 1000; // calcular cargos de servicio
                String nombreLocalidad;
    
                switch (localidad) {
                    case 1:
                        precioEntrada = 10500;
                        nombreLocalidad = "Sol Norte/Sur";
                        subtotal = cantEntradas * precioEntrada; // calcular subtotal
                        entradasSolNorteSur[numFactura - 1] += cantEntradas;
                        acumuladoSolNorteSur += subtotal + cargosServicios; // agregar subtotal con cargos por servicio al acumulado
                        break;
                    case 2:
                        precioEntrada = 20500;
                        nombreLocalidad = "Sombra Este/Oeste";
                        subtotal = cantEntradas * precioEntrada; // calcular subtotal
                        entradasSombraEsteOeste[numFactura - 1] += cantEntradas;
                        acumuladoSombraEsteOeste += subtotal + cargosServicios; // agregar subtotal con cargos por servicio al acumulado
                        break;
                    case 3:
                        precioEntrada = 25500;
                        nombreLocalidad = "Preferencial";
                        subtotal = cantEntradas * precioEntrada; // calcular subtotal
                        entradasPreferencial[numFactura - 1] += cantEntradas;
                        acumuladoPreferencial += subtotal + cargosServicios; // agregar subtotal con cargos por servicio al acumulado
                        break;
                    default:
                        System.out.println("\nERROR: Localidad inválida.\n");
                        return;
                }

                double total = subtotal + (cantEntradas * 1000);
                System.out.println(" ");
                System.out.println(String.format("%75s", "Info. de Factura"));
                System.out.println("=====================================================================================================================================");
                System.out.println(String.format("%-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s", "N. Factura", "Cedula", "Comprador", "Localidad", "Cantidad", "Subtotal", "Cargos", "Total a pagar"));
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format("%-16s %-16s %-16s %-19s %-12s %-16s %-19s %-20s", numFactura, cedula, nombre, nombreLocalidad, cantEntradas, "¢" + subtotal, "¢" + cargosServicios, "¢" + total));
                System.out.println("=====================================================================================================================================\n");
                ventaValida = true;
            } else {
                System.out.println("\nERROR:  No se pueden comprar más de 4 entradas.\n");
            }
        }
    }

    public static void inicializarArreglo() {
        entradasSolNorteSur = new int[TAM_VECTOR];
        entradasSombraEsteOeste = new int[TAM_VECTOR];
        entradasPreferencial = new int[TAM_VECTOR];
        acumuladoSolNorteSur = 0;
        acumuladoSombraEsteOeste = 0;
        acumuladoPreferencial = 0;
        System.out.println("\nArreglo inicializado.\n");
    }

    public static void mostrarEstadisticas() {

        double totalventasConCargos = acumuladoSolNorteSur + acumuladoSombraEsteOeste + acumuladoPreferencial;

        System.out.println(" ");
        System.out.println(String.format("%40s", "Estadisticas de Ventas")); // este formato "%50s" sirve para
        // rellenar espacios,
        System.out.println( // en este caso se especifica la longitud total de la cadena de texto 50
                            // caracteres.
                "=======================================================");
        System.out.println(String.format("\033[1m%-20s %-20s %-20s", "Tipo de entrada", "Entradas Vendidas",
                "Total Ganado\033[0m"));
        System.out.println(
                "-------------------------------------------------------");
        System.out.println(String.format("%-28s %-16s %-20s", "Sol Norte/Sur", sumarEntradas(entradasSolNorteSur), "¢" +
                acumuladoSolNorteSur));
        System.out.println(
                "-------------------------------------------------------");
        System.out.println(String.format("%-28s %-16s %-20s", "Sombra Este/Oeste",
                sumarEntradas(entradasSombraEsteOeste), "¢" + acumuladoSombraEsteOeste));
        System.out.println(
                "-------------------------------------------------------");
        System.out.println(String.format("%-28s %-16s %-20s", "Preferencial", sumarEntradas(entradasPreferencial), "¢" +
                acumuladoPreferencial));
        System.out.println(
                "=======================================================");
        System.out.println(String.format("\033[1m%-44s %-13s", "TOTAL VENTAS", "¢" + totalventasConCargos + "\033[0m"));
        System.out.println(
                "                                            ---------- ");
    }

    public static int sumarEntradas(int[] arreglo) {
        int total = 0;
        for (int i = 0; i < arreglo.length; i++) {
            total += arreglo[i];
        }
        return total;
    }
}
