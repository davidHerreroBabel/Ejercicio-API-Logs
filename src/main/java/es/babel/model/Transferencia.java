package es.babel.model;

import lombok.Data;

@Data
public class Transferencia {
    private CuentaBancaria cuentaBancariaEmisor;
    private CuentaBancaria cuentaBancariaReceptor;
    private Double importe;

    private Transferencia() {}

}
