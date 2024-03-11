package es.babel.model;

import es.babel.enums.MovimientoEnum;
import lombok.Data;

@Data
public class Operacion {
    private String iban;
    private MovimientoEnum movimiento;
    private Double cantidad;

}
