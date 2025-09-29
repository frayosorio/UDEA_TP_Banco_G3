package modelos;

public abstract class Cuenta {

    private String titular;
    private String numero;
    private double saldo;

    public Cuenta(String titular, String numero, double saldo) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método protegido para modificar el saldo (Encapsulamiento)
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método polimórfico
    public abstract boolean retirar(double cantidad);

    public boolean consignar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Consignación existosa. Nuevo saldo:" + saldo);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getNumero() + " - " + getTitular();
    }

}
