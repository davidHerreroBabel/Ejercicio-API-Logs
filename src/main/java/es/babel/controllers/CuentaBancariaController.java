package es.babel.controllers;

import es.babel.model.Cliente;
import es.babel.model.CuentaBancaria;
import es.babel.model.Operacion;
import es.babel.repositories.CuentaBancariaRepository;
import es.babel.repositories.ICuentaBancariaRepository;
import es.babel.repositories.IOperacionRepository;
import es.babel.repositories.OperacionRepository;
import es.babel.services.CuentaBancariaService;
import es.babel.services.ICuentaBancariaService;
import es.babel.services.IOperacionService;
import es.babel.services.OperacionService;
import es.babel.utils.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cuentaBancaria")
public class CuentaBancariaController {

    ICuentaBancariaService cuentaBancariaService = new CuentaBancariaService();
    IOperacionService operacionService = new OperacionService();
    ICuentaBancariaRepository cuentaBancariaRepository = CuentaBancariaRepository.getInstance();
    IOperacionRepository operacionRepository = OperacionRepository.getInstance();

    @PostMapping("/inicializar")
    public void inicializarCuentaBancaria(@RequestBody Cliente cliente) throws Exception {
        String IBAN = "ES04";
        CuentaBancaria cuentaBancaria = cuentaBancariaService.crearCuentaBancaria(IBAN, cliente.getNombre());
        cuentaBancariaRepository.addCuentaBancaria(cuentaBancaria);

        Operacion operacionIngresonicial = operacionService.
                setOperacion(IBAN, "Ingreso", 3000.0);
        Operacion operacionRetiradaInicial = operacionService.
                setOperacion(IBAN, "Retirada", 200.0);

        operacionRepository.addOperacion(operacionIngresonicial);
        operacionRepository.addOperacion(operacionRetiradaInicial);

        cuentaBancariaRepository.actualizarCuentaBancaria(cliente.getNombre(), IBAN,
                operacionIngresonicial.getCantidad());
        cuentaBancariaRepository.actualizarCuentaBancaria(cliente.getNombre(), IBAN,
                operacionRetiradaInicial.getCantidad());

        Log.info("Cuenta inicializada");
    }

    @GetMapping("/all")
    public Map<String, List<CuentaBancaria>> getCuentasBancarias
            (@RequestParam("nombre") String nombreCliente) {
        return cuentaBancariaRepository.getCuentasBancarias(nombreCliente);
    }

    @PostMapping("/alta")
    public void altaCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria) {
        cuentaBancariaRepository.addCuentaBancaria(cuentaBancaria);
        Log.info("Cuenta dada de alta");
    }

}
