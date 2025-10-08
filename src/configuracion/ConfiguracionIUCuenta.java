package configuracion;

import java.util.Map;

import modelos.TipoCuenta;

public class ConfiguracionIUCuenta {

    private String textoSaldoInicial;
    private String textoSobregiro;
    private boolean mostrarSobregiro;
    private boolean mostrarPlazo;

    public ConfiguracionIUCuenta(String textoSaldoInicial, String textoSobregiro, boolean mostrarSobregiro,
            boolean mostrarPlazo) {
        this.textoSaldoInicial = textoSaldoInicial;
        this.textoSobregiro = textoSobregiro;
        this.mostrarSobregiro = mostrarSobregiro;
        this.mostrarPlazo = mostrarPlazo;
    }

    public String getTextoSaldoInicial() {
        return textoSaldoInicial;
    }

    public String getTextoSobregiro() {
        return textoSobregiro;
    }

    public boolean isMostrarSobregiro() {
        return mostrarSobregiro;
    }

    public boolean isMostrarPlazo() {
        return mostrarPlazo;
    }

    private static Map<TipoCuenta, ConfiguracionIUCuenta> configuraciones = Map.of(
            TipoCuenta.AHORROS, new ConfiguracionIUCuenta("Saldo Inicial:", null, false, false),
            TipoCuenta.CORRIENTE, new ConfiguracionIUCuenta("Saldo Inicial:", "Sobregiro:", true, false),
            TipoCuenta.CREDITO, new ConfiguracionIUCuenta("Valor prestado:", "Tasa", true, true),
            TipoCuenta.CREDITO_ROTATIVO, new ConfiguracionIUCuenta("Cupo préstampo:", "Tasa", true, true));

    public static Map<TipoCuenta, ConfiguracionIUCuenta> getConfiguraciones() {
        return configuraciones;
    }

            
}
