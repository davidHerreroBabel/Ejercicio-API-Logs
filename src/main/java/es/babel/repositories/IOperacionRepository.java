package es.babel.repositories;

import es.babel.model.CuentaBancaria;
import es.babel.model.Operacion;

import java.util.List;

public interface IOperacionRepository {

    void addOperacion(Operacion operacion);

    List<Operacion> getOperaciones(String IBAN);
}
