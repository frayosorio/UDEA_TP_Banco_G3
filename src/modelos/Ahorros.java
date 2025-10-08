package modelos;

import java.text.DecimalFormat;

import interfaces.IConsignable;
import interfaces.IRetirable;

public class Ahorros extends Cuenta implements IConsignable, IRetirable {
    private final double TASA_INTERES = 0.02;

    public Ahorros(String titular, String numero, double saldo) {
        super(titular, numero, saldo);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (getSaldo() >= cantidad) {
            setSaldo(getSaldo() - cantidad);
            System.out.println("Retiro existoso. Nuevo saldo:" + getSaldo());
            return true;
        } else {
            System.out.println("Fondos insuficientes para este retiro");
            return false;
        }
    }

    public void abonarInteres() {
        setSaldo(getSaldo() * (1 + TASA_INTERES));
        System.out.println("Intereses mensuales abonados. Nuevo saldo:" + getSaldo());
    }

    @Override
    public String[] mostrarDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Ahorros",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "", "", "", "", ""
        };
    }

    @Override
    public boolean consignar(double cantidad) {
        return incrementarSaldo(cantidad);
    }

}
