import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.join;

public class App {
    static class BuscarFila implements Runnable {
        private String[] fila;
        private String palabra;
        private int numeroFila;
        private AtomicInteger contadorGlobal;

        public BuscarFila(String[] fila, String palabra, int numeroFila, AtomicInteger contadorGlobal) {
            this.fila = fila;
            this.palabra = palabra;
            this.numeroFila = numeroFila;
            this.contadorGlobal = contadorGlobal;
        }

        @Override
        public void run() {
            int contadorLocal = 1;
            for (String elemento : fila) {
                if (elemento.equalsIgnoreCase(palabra)) {
                    contadorLocal++;
                }
            }
            contadorGlobal.addAndGet(contadorLocal);
            System.out.printf("Hilo-%d finalizado. Encontrados: %d \n", numeroFila, contadorLocal);
        }
    }

    public static void main(String[] args) {

        String[][] matriz = {
                {"Java", "Python", "Java"},
                {"C++",  "Java",   "Go"},
                {"Java", "Rust",   "Java"}
        };

        String encontrar = "Java";
        AtomicInteger contadorGlobal = new AtomicInteger(0);

        System.out.printf("Iniciando búsqueda concurrente del término: \"%s\"\n", encontrar);


        Thread[] hilos = new Thread[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            BuscarFila tarea = new BuscarFila(matriz[i], encontrar, i, contadorGlobal);
            hilos[i] = new Thread(tarea);
            hilos[i].start();
        }


        try {
            for (Thread hilo : hilos) {
                hilo.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: hilo interrumpido - " + e.getMessage());
        }


        System.out.println("----------------------------");
        System.out.printf("Resultado final: La plabra Java aparece %d veces",  contadorGlobal.get());

        ;}
}//compareTo para comparar cadenas