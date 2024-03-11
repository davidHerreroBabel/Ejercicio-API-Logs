package es.babel.repositories;

import es.babel.model.CuentaBancaria;

import java.util.List;
import java.util.Map;

public interface ICuentaBancariaRepository {

    void addCuentaBancaria(CuentaBancaria cuentaBancaria);

    Map<String, List<CuentaBancaria>> getCuentasBancarias(String nombreCliente);

    void actualizarCuentaBancaria(String nombreCliente, String IBAN, Double cantidad);
}
