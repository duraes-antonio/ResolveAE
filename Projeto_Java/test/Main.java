import Dominio.Enum.ETipoInfoProfissional;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        long startTime = System.currentTimeMillis();


        System.out.println(ETipoInfoProfissional.POS_GRADUACAO.getId());
        System.out.println(ETipoInfoProfissional.GRADUACAO.getId());


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
    }
}
