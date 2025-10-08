package modelos;

import java.text.DecimalFormat;

import interfaces.IConsignable;
import interfaces.IRetirable;

public class Corriente extends Cuenta implements IConsignable, IRetirable {
    private double sobregiro;

    public Corriente(String titular, String numero, double saldo, double sobregiro) {
        super(titular, numero, saldo);
        this.sobregiro = sobregiro;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (getSaldo() + sobregiro >= cantidad) {
            setSaldo(getSaldo() - cantidad);
            System.out.println("Retiro existoso. Nuevo saldo:" + getSaldo());
            return true;
        } else {
            System.out.println("Fondos insuficientes para este retiro");
            return false;
        }
    }

    @Override
    public String[] mostrarDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Corriente",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                df.format(sobregiro),
                "", "", "", ""

        };
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public boolean consignar(double cantidad) {
        return incrementarSaldo(cantidad);
    }

}
