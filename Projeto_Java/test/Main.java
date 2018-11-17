import Dominio.Enum.ETipoServico;

public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();


        System.out.println(ETipoServico.getById(6).getTipo());


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime / 1000.00 + " sec.");
    }

}
