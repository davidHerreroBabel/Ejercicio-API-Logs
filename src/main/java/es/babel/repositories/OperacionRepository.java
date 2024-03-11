package es.babel.repositories;

import es.babel.model.Operacion;
import es.babel.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class OperacionRepository implements IOperacionRepository {

    List<Operacion> operaciones = new ArrayList<>();

    private static OperacionRepository instanceCuentaBancariaRepository = null;

    public static synchronized OperacionRepository getInstance() {
        if (instanceCuentaBancariaRepository == null) {
            instanceCuentaBancariaRepository = new OperacionRepository();
        }
        return instanceCuentaBancariaRepository;
    }

    @Override
    public void addOperacion(Operacion operacion) {
        this.operaciones.add(operacion);
        Log.info("Operación añadida");
    }

    @Override
    public List<Operacion> getOperaciones(String IBAN) {
        List<Operacion> operacionesIBAN = new ArrayList<>();
        for (Operacion operacion : operaciones) {
            if (operacion.getIban().equals(IBAN)) {
                operacionesIBAN.add(operacion);
            }
        }
        return operacionesIBAN;
    }

}
