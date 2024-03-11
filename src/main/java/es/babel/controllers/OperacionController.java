package es.babel.controllers;

import es.babel.model.CuentaBancaria;
import es.babel.model.Operacion;
import es.babel.model.Transferencia;
import es.babel.repositories.CuentaBancariaRepository;
import es.babel.repositories.ICuentaBancariaRepository;
import es.babel.repositories.IOperacionRepository;
import es.babel.repositories.OperacionRepository;
import es.babel.services.IOperacionService;
import es.babel.services.OperacionService;
import es.babel.utils.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/operacion")
public class OperacionController {

    IOperacionService operacionService = new OperacionService();
    IOperacionRepository operacionRepository = OperacionRepository.getInstance();
    ICuentaBancariaRepository cuentaBancariaRepository = CuentaBancariaRepository.getInstance();

    @PutMapping("/ingreso")
    public void realizarIngreso(@RequestBody CuentaBancaria cuentaBancaria) throws Exception {
        realizarOperacion(cuentaBancaria, "Ingreso", cuentaBancaria.getDeposito());
        Log.info("Ingreso completado");
    }

    @PutMapping("/retirada")
    public void realizarRetirada(@RequestBody CuentaBancaria cuentaBancaria) throws Exception {
        realizarOperacion(cuentaBancaria, "Retirada", cuentaBancaria.getDeposito());
        Log.info("Retirada completada");
    }

    @PostMapping("/transferencia")
    public void realizarTransferencia(@RequestBody Transferencia transferencia)
                                      throws Exception {
        CuentaBancaria cuentaBancariaEmisor = transferencia.getCuentaBancariaEmisor();
        CuentaBancaria cuentaBancariaReceptor = transferencia.getCuentaBancariaReceptor();
        realizarOperacion(cuentaBancariaEmisor, "Retirada", transferencia.getImporte());
        realizarOperacion(cuentaBancariaReceptor, "Ingreso", transferencia.getImporte());
        Log.info("Transferencia completada");
    }

    @GetMapping("/operacionesRealizadas")
    public List<Operacion> getOperacionesRealizadas(@RequestParam String iban) {
        return operacionRepository.getOperaciones(iban);
    }

    private void realizarOperacion(CuentaBancaria cuentaBancaria, String tipoOperacion,
                                   Double cantidad)
            throws Exception {
        Operacion operacion = operacionService.setOperacion(cuentaBancaria.getIban(),
                tipoOperacion, cantidad);
        operacionRepository.addOperacion(operacion);
        String nombreCliente = cuentaBancaria.getCliente().getNombre();
        cuentaBancariaRepository.actualizarCuentaBancaria(nombreCliente, cuentaBancaria.getIban(),
                operacion.getCantidad());
        Log.info("Operación completada");
    }

}
