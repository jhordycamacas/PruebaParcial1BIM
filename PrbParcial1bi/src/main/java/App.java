import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.join;

public class App {
    static class BuscadorFila implements Runnable {
        private String[] fila;
        private String palabra;
        private int numeroFila;
        private AtomicInteger contadorGlobal;

        public BuscadorFila(String[] fila, String palabra, int numeroFila, AtomicInteger contadorGlobal) {
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
            System.out.println("Hilo-" + numeroFila + " finalizado. Encontrados: " + contadorLocal);
        }
    }

    public static void main(String[] args) {

        String[][] matriz = {
                {"Java", "Python", "Java"},
                {"C++",  "Java",   "Go"},
                {"Java", "Rust",   "Java"}
        };

        String palabraBuscada = "Java";
        AtomicInteger contadorGlobal = new AtomicInteger(0);

        System.out.println("Iniciando búsqueda concurrente del término: \"" + palabraBuscada + "\"");


        Thread[] hilos = new Thread[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            BuscadorFila tarea = new BuscadorFila(matriz[i], palabraBuscada, i, contadorGlobal);
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