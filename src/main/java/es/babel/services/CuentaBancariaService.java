package es.babel.services;

import es.babel.model.Cliente;
import es.babel.model.CuentaBancaria;
import es.babel.utils.Log;

import java.util.List;

public class CuentaBancariaService implements ICuentaBancariaService {

    @Override
    public CuentaBancaria crearCuentaBancaria(String IBAN, String nombreCliente) {
        Cliente cliente = new Cliente(nombreCliente);
        return new CuentaBancaria(IBAN, cliente);
    }

}
