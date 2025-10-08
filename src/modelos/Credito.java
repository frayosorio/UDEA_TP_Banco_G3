package modelos;

import java.text.DecimalFormat;

import interfaces.IPagable;
import interfaces.IRetirable;

public class Credito extends Cuenta implements IRetirable, IPagable {

    private double valorPrestado;
    private double tasa;
    private int plazo;
    private double valorRetirado;

    public Credito(String titular, String numero, double valorPrestado, double tasa, int plazo) {
        super(titular, numero, 0);
        this.valorPrestado = valorPrestado;
        this.tasa = tasa;
        this.plazo = plazo;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (valorRetirado + cantidad <= valorPrestado) {
            valorRetirado += cantidad;
            return true;
        } else {
            System.out.println("Ya fue retirado todo el capital del préstamo o no hay saldo disponible");
            return false;
        }
    }

    @Override
    public double getCuota() {
        double t = tasa / 100;
        return valorPrestado * Math.pow(1 + t, plazo) * t / (Math.pow(1 + t, plazo) - 1);
    }

    @Override
    public boolean pagar(double cantidad) {
        if (getSaldo() < valorPrestado) {
            var intereses = (tasa / 100) * (valorPrestado - getSaldo());
            var abonoCapital = cantidad - intereses;
            incrementarSaldo(abonoCapital);
            System.out.println("Pago existoso. Nuevo saldo de la deuda:" + (valorPrestado - getSaldo()));
            return true;
        } else {
            System.out.println("Ya la deuda está pagada");
            return false;
        }
    }

    @Override
    public String[] mostrarDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Crédito",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "",
                df.format(valorPrestado),
                df.format(tasa),
                String.valueOf(plazo),
                df.format(getCuota())
        };
    }

    public double getValorPrestado() {
        return valorPrestado;
    }

    public double getTasa() {
        return tasa;
    }

    public double getValorRetirado() {
        return valorRetirado;
    }

    public int getPlazo() {
        return plazo;
    }

}
