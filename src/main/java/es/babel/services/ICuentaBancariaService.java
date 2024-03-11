package es.babel.services;

import es.babel.model.CuentaBancaria;

import java.util.List;

public interface ICuentaBancariaService {

    CuentaBancaria crearCuentaBancaria(String IBAN, String nombreCliente);

}
