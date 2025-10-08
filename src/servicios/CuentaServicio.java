package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Ahorros;
import modelos.Corriente;
import modelos.Credito;
import modelos.CreditoRotativo;
import modelos.Cuenta;
import modelos.TipoCuenta;

public class CuentaServicio {

    public static String[] encabezados = new String[] { "Tipo", "Número", "Titular", "Saldo",
            "Sobregiro", "Valor Prestado", "Tasa", "Plazo", "Cuota" };

    private static List<Cuenta> cuentas = new ArrayList<>();

    public static Cuenta getCuenta(int posicion) {
        if (posicion >= 0 && posicion < cuentas.size()) {
            return cuentas.get(posicion);
        } else {
            throw new IllegalArgumentException("Posición no válida");
        }
    }

    public static Cuenta agregar(String titular, String numero, TipoCuenta tipo,
            double saldoInicial, double sobregiro, int plazo) {
        Cuenta cuenta = null;
        switch (tipo) {
            case AHORROS:
                cuenta = new Ahorros(titular, numero, saldoInicial);
                break;
            case CORRIENTE:
                cuenta = new Corriente(titular, numero, saldoInicial, sobregiro);
                break;
            case CREDITO:
                cuenta = new Credito(titular, numero, saldoInicial, sobregiro, plazo);
                break;
            case CREDITO_ROTATIVO:
                cuenta = new CreditoRotativo(titular, numero, saldoInicial, sobregiro, plazo);
                break;
        }
        cuentas.add(cuenta);
        return cuenta;
    }

    public static void quitar(int posicion) {
        cuentas.remove(posicion);
    }

    public static void mostrar(JTable tblCuentas) {
        String[][] datos = null;
        if (cuentas.size() > 0) {
            datos = new String[cuentas.size()][encabezados.length];
            int fila = 0;
            for (Cuenta c : cuentas) {
                datos[fila] = c.mostrarDatos();
                fila++;
            }

        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblCuentas.setModel(dtm);

    }

}
