package servicios;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Ahorros;
import modelos.Corriente;
import modelos.Credito;
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
                datos[fila][0] = c instanceof Ahorros ? "Ahorros" : c instanceof Corriente ? "Corriente" : "Crédito";
                datos[fila][1] = c.getNumero();
                datos[fila][2] = c.getTitular();

                DecimalFormat df = new DecimalFormat("#,##0.00");
                datos[fila][3] = df.format(c.getSaldo());
                datos[fila][4] = c instanceof Corriente ? df.format(((Corriente) c).getSobregiro()) : "";
                datos[fila][5] = c instanceof Credito ? df.format(((Credito) c).getValorPrestado()) : "";
                datos[fila][6] = c instanceof Credito ? df.format(((Credito) c).getTasa()) : "";
                datos[fila][7] = c instanceof Credito ? df.format(((Credito) c).getPlazo()) : "";
                datos[fila][8] = c instanceof Credito ? df.format(((Credito) c).getCuota()) : "";
                fila++;
            }

        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblCuentas.setModel(dtm);

    }

}
