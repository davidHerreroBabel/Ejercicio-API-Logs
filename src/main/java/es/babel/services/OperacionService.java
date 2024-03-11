package es.babel.services;

import es.babel.enums.MovimientoEnum;
import es.babel.model.Operacion;
import es.babel.utils.Log;

public class OperacionService implements IOperacionService {

    @Override
    public Operacion setOperacion(String iban, String tipoOperacion, Double cantidad) throws Exception {
        MovimientoEnum movimiento = getMovimiento(tipoOperacion);
        cantidad = actualizarSignoCantidad(cantidad, movimiento);
        return crearOperacion(iban, cantidad, movimiento);
    }

    private static Double actualizarSignoCantidad(Double cantidad, MovimientoEnum movimiento) {
        if (movimiento.equals(MovimientoEnum.RETIRADA)) {
            cantidad = cantidad * -1;
        }
        return cantidad;
    }

    private static Operacion crearOperacion(String iban, Double cantidad, MovimientoEnum movimiento) {
        Operacion operacion = new Operacion();
        operacion.setMovimiento(movimiento);
        operacion.setCantidad(cantidad);
        operacion.setIban(iban);
        Log.info("Operación creada");
        return operacion;
    }

    private static MovimientoEnum getMovimiento(String tipoOperacion) throws Exception {
        if (tipoOperacion.equals("Ingreso")) {
            return MovimientoEnum.INGRESO;
        }
        else if (tipoOperacion.equals("Retirada")) {
            return MovimientoEnum.RETIRADA;
        }
        else {
            Log.warn("Tipo de operación incorrecto");
            throw new Exception("Operación no valida");
        }
    }
}
