public class Cronometro {

    private static long startTime;
    private static long stopTime;
    private static String linha = "- - - - - - - - - - - - - - -";

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void stop() {
        stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.printf(
                "\nTEMPO de EXECUÇÃO:\t %.6f segundos\n%s\n",
                elapsedTime /1000.00, linha);
    }
}
