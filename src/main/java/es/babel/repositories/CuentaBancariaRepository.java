package es.babel.repositories;

import es.babel.model.CuentaBancaria;
import es.babel.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuentaBancariaRepository implements ICuentaBancariaRepository {

    Map<String, List<CuentaBancaria>> cuentasBancarias = new HashMap<>();

    private static CuentaBancariaRepository instanceCuentaBancariaRepository = null;

    public static synchronized CuentaBancariaRepository getInstance() {
        if (instanceCuentaBancariaRepository == null) {
            instanceCuentaBancariaRepository = new CuentaBancariaRepository();
        }
        return instanceCuentaBancariaRepository;
    }

    @Override
    public void addCuentaBancaria(CuentaBancaria cuentaBancaria) {
        String nombreCliente = cuentaBancaria.getCliente().getNombre();
        List<CuentaBancaria> newListaCuentasBancariasCliente = new ArrayList<>();
        if (cuentasBancarias.containsKey(nombreCliente)) {
            newListaCuentasBancariasCliente = cuentasBancarias.get(nombreCliente);
        }
        newListaCuentasBancariasCliente.add(cuentaBancaria);
        this.cuentasBancarias.put(nombreCliente, newListaCuentasBancariasCliente);
        Log.info("Cuenta Bancaria añadida");
    }

    @Override
    public Map<String, List<CuentaBancaria>> getCuentasBancarias(String nombreCliente) {
        return this.cuentasBancarias;
    }

    @Override
    public void actualizarCuentaBancaria(String nombreCliente, String IBAN, Double cantidad) {
        List<CuentaBancaria> listaCuentasBancariasCliente = this.cuentasBancarias.get(nombreCliente);
        for (CuentaBancaria cuentaBancaria : listaCuentasBancariasCliente) {
            if (cuentaBancaria.getIban().equals(IBAN)) {
                cuentaBancaria.actualizarDeposito(cantidad);
            }
        }
        Log.info("Cuenta Bancaria actualizada");
    }

}
