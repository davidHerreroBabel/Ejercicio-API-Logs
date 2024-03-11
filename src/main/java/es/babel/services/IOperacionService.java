package es.babel.services;

import es.babel.model.Operacion;

public interface IOperacionService {

    Operacion setOperacion(String IBAN, String tipoOperacion, Double cantidad) throws Exception;

}
