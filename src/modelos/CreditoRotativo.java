package modelos;

import java.text.DecimalFormat;

import interfaces.IPagable;
import interfaces.IRetirable;

public class CreditoRotativo extends Cuenta implements IRetirable, IPagable {

    private double valorPrestamo;
    private double tasa;
    private int plazo;

    public CreditoRotativo(String titular, String numero, double valorPrestamo, double tasa, int plazo) {
        super(titular, numero, valorPrestamo);
        this.valorPrestamo = valorPrestamo;
        this.tasa = tasa;
        this.plazo = plazo;
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

    @Override
    public double getCuota() {
        double t = tasa / 100;
        return (valorPrestamo - getSaldo()) * Math.pow(1 + t, plazo) * t / (Math.pow(1 + t, plazo) - 1);
    }

    @Override
    public boolean pagar(double cantidad) {
        if (getSaldo() < valorPrestamo) {
            var intereses = (tasa / 100) * (valorPrestamo - getSaldo());
            var abonoCapital = cantidad - intereses;
            incrementarSaldo(abonoCapital);
            System.out.println("Pago existoso. Nuevo saldo disponible :" + getSaldo());
            return true;
        } else {
            System.out.println("No hay deuda a pagar");
            return false;
        }

    }

    @Override
    public String[] mostrarDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Crédito Rotativo",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "",
                df.format(valorPrestamo),
                df.format(tasa),
                String.valueOf(plazo),
                ""
        };
    }

}
