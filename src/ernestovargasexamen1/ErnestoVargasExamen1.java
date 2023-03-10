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
            System.out.print("Ingrese número de factura: ");
            int numFactura = scanner.nextInt();
            scanner.nextLine(); // consumir la nueva línea pendiente
            if (numFactura < 1 || numFactura > TAM_VECTOR) {
                System.out.println("Número de factura inválido. Debe ser entre 1 y " + TAM_VECTOR + ".");
                continue;
            }
            System.out.print("Ingrese cédula: ");
            String cedula = scanner.nextLine();
            System.out.print("Ingrese nombre del comprador: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese localidad:");
            System.out.println("1. Sol Norte/Sur");
            System.out.println("2. Sombra Este/Oeste");
            System.out.println("3. Preferencial");
            int localidad = scanner.nextInt();
            System.out.print("Ingrese cantidad de entradas: ");
            int cantEntradas = scanner.nextInt();
            scanner.nextLine(); // consumir la nueva línea pendiente

            if (cantEntradas <= 4) {
                double precioEntrada;
                String nombreLocalidad;
                switch (localidad) {
                    case 1:
                        precioEntrada = 10500;
                        nombreLocalidad = "Sol Norte/Sur";
                        entradasSolNorteSur[numFactura - 1] += cantEntradas;
                        acumuladoSolNorteSur += cantEntradas * precioEntrada;
                        break;
                    case 2:
                        precioEntrada = 20500;
                        nombreLocalidad = "Sombra Este/Oeste";
                        entradasSombraEsteOeste[numFactura - 1] += cantEntradas;
                        acumuladoSombraEsteOeste += cantEntradas * precioEntrada;
                        break;
                    case 3:
                        precioEntrada = 25500;
                        nombreLocalidad = "Preferencial";
                        entradasPreferencial[numFactura - 1] += cantEntradas;
                        acumuladoPreferencial += cantEntradas * precioEntrada;
                        break;
                    default:
                        System.out.println("Localidad inválida.");
                        return;
                }
                double subtotal = cantEntradas * precioEntrada;
                double cargosServicios = cantEntradas * 1000;
                double total = subtotal + cargosServicios;
                System.out.println("Número de factura: " + numFactura);
                System.out.println("Cédula: " + cedula);
                System.out.println("Nombre del comprador: " + nombre);
                System.out.println("Localidad: " + nombreLocalidad);
                System.out.println("Cantidad de entradas: " + cantEntradas);
                System.out.println("Subtotal: " + subtotal);
                System.out.println("Cargos por servicios: " + cargosServicios);
                System.out.println("Total a pagar: " + total);
                ventaValida = true;
            } else {
                System.out.println("No se pueden comprar más de 4 entradas.");
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
        System.out.println("Arreglo inicializado.");
    }

    public static void mostrarEstadisticas() {
        System.out.println("Cantidad Entradas Localidad Sol Norte/Sur: " + sumarEntradas(entradasSolNorteSur));
        System.out.println("Acumulado Dinero Localidad Sol Norte/Sur: " + acumuladoSolNorteSur);
        System.out.println("Cantidad Entradas Localidad Sombra Este/Oeste: " + sumarEntradas(entradasSombraEsteOeste));
        System.out.println("Acumulado Dinero Localidad Sombra Este/Oeste: " + acumuladoSombraEsteOeste);
        System.out.println("Cantidad Entradas Localidad Preferencial: " + sumarEntradas(entradasPreferencial));
        System.out.println("Acumulado Dinero Localidad Preferencial: " + acumuladoPreferencial);
    }

    public static int sumarEntradas(int[] arreglo) {
        int total = 0;
        for (int i = 0; i < arreglo.length; i++) {
            total += arreglo[i];
        }
        return total;
    }
}
