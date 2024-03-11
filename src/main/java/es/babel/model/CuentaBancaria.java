package es.babel.model;

import es.babel.utils.Log;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CuentaBancaria {
    private Cliente cliente;
    private String iban;
    private Double deposito;

    public CuentaBancaria() {

    }

    public CuentaBancaria(String iban, Cliente cliente) {
        this.cliente = cliente;
        this.iban = iban;
        this.deposito = 0.0;
        Log.info("Cuenta bancaria creada");
    }

    public void actualizarDeposito(Double cantidad) {
        this.deposito += cantidad;
        Log.info("Depósito actualizado");
    }

}
