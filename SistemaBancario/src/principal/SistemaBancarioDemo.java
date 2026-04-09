
package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.banco.Banco;
import modelo.banco.Transaccion;
import modelo.cuentas.CuentaAhorros;
import modelo.cuentas.CuentaCorriente;
import modelo.cuentas.CuentaCredito;
import modelo.empleados.AsesorFinanciero;
import modelo.empleados.Cajero;
import modelo.empleados.GerenteSucursal;
import modelo.enums.EstadoTransaccion;
import modelo.enums.TipoDocumento;
import modelo.enums.Turno;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.EstadoTransaccionInvalidoException;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;

public class SistemaBancarioDemo {
    
    // Para formatear las fechas y horas
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        
        // DEMOSTRACIÓN DE ESCENARIOS
        
        Banco banco = new Banco("Banco Comfenalco");
    
        // ===================================================================
        //  Registrar al menos 2 clientes naturales y 1 empresarial
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 1 
                           =======================================""");

        ClienteNatural clienteN1 = new ClienteNatural("CN-001", "Miguel", "Benitez", LocalDate.parse("2007-06-14"), 
                                               "benitez@gmail.com", TipoDocumento.CEDULA, "1043974154");
        try {
            banco.registrarCliente(clienteN1);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        ClienteNatural clienteN2 = new ClienteNatural("CN-002", "Delany", "Mendoza", LocalDate.parse("2007-08-29"), 
                                                "mendoza@gmail.com", TipoDocumento.NIT, "123456789");
        try {
            banco.registrarCliente(clienteN2);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        ClienteEmpresarial clienteE1= new ClienteEmpresarial("CE-001", "Belany", "Mendoza", LocalDate.parse("2006-05-15"), 
                "belanym@gmail.com", "123456", "Inversiones Mendoza S.A.S.", "Mario");
        try {
            banco.registrarCliente(clienteE1);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        // Todo esto es para corroborar que los clientes fueron creados correctamente
        for (Cliente c : banco.getClientes()) {
            if (c == null) {
                break;
            }
            System.out.println("---------------------------------------");
            System.out.println(c.obtenerResumen());
        }
        
        System.out.println("---------------------------------------");

        // ===================================================================
        //  Abrir al menos una cuenta de cada tipo (corriente, ahorros, crédito)
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 2
                           =======================================""");
        try {
            banco.abrirCuenta("CN-001", new CuentaAhorros("CA-001", 5000000, "Sistema", 0.12, 50));
            banco.abrirCuenta("CN-002", new CuentaCorriente("CC-001", 3000000, "Sistema", 1000000, 25000));
            banco.abrirCuenta("CE-001", new CuentaCredito("CCR-001", 8000000, "Sistema", 4000000, 0.018));
        } catch (ClienteNoEncontradoException | CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        // Todo esto es para corroborar que las cuentas fueron creadas correctamente
        System.out.println("---------------------------------------");
        System.out.println("           CUENTAS CREADAS");
        for (Cliente c : banco.getClientes()) {
            if (c == null) { break; }

            Cuenta[] cuentas = c.getCuentas();
            for (int i = 0; i < c.getTotalCuentas(); i++) {
                Cuenta cuenta = cuentas[i];
                System.out.println(cuenta.obtenerResumen());
            }
        }

        // ===================================================================
        //  Realizar un depósito exitoso y capturar CuentaBloqueadaException 
        //  al intentar depositar en una cuenta bloqueada
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 3
                           =======================================""");
        
        // Depósito de 50.000 exitoso
        try {
            System.out.println("---------------------------------------");
            System.out.println(" Deposito de " + clienteN1.getNombreCompleto() + " ($500.000)");
            System.out.println();
            Cuenta cuenta = clienteN1.buscarCuenta("CA-001");
            System.out.println("Saldo antes  : $" + cuenta.getSaldo());
            
            clienteN1.depositarACuenta("CA-001", 500000);
            System.out.println("Saldo actual : $" + cuenta.getSaldo());
        } catch (CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // Captura de error
        try {
            System.out.println("---------------------------------------");
            System.out.println("     Captura de error de deposito");
            Cuenta cuenta = clienteN1.buscarCuenta("CA-001");
            cuenta.setBloqueada(true);
            clienteN1.depositarACuenta("CA-001", 25000);
        } catch (CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        } 
        
        // Para volver a abrir la cuenta para los próximos escenarios, jeje
        Cuenta cuentaCN1 = clienteN1.buscarCuenta("CA-001");
        cuentaCN1.setBloqueada(false);
        
        // ===================================================================
        //  Realizar un retiro exitoso y capturar SaldoInsuficienteException 
        // al intentar retirar más del saldo disponible
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 4
                           =======================================""");
        // Retiro de 100.000 exitoso
        try {
            System.out.println("---------------------------------------");
            System.out.println("  Retiro de " + clienteN2.getNombreCompleto() + " ($100.000)");
            System.out.println();
            Cuenta cuenta = clienteN2.buscarCuenta("CC-001");
            System.out.println("Saldo antes  : $" + cuenta.getSaldo());
            
            cuenta.retirar(100000);
            System.out.println("Saldo actual : $" + cuenta.getSaldo());
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // Captura de error
        try {
            System.out.println("---------------------------------------");
            System.out.println("      Captura de error de retiro");
            Cuenta cuenta = clienteN2.buscarCuenta("CC-001");
            cuenta.retirar(10000000);
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Realizar una transferencia entre dos cuentas
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 5
                           =======================================""");

        try {
            Cuenta origen = clienteN1.buscarCuenta("CA-001");
            Cuenta destino = clienteN2.buscarCuenta("CC-001");
            double monto = 300000;
            
            Transaccion transferencia = new Transaccion( "T-001", origen, destino, 300000,
                                                        EstadoTransaccion.PENDIENTE, 
                                                        "Transferencia de 300.000 de Miguel a Delany");

            origen.retirar(monto);
            destino.depositar(monto);

            transferencia.cambiarEstado(EstadoTransaccion.PROCESANDO);
            transferencia.cambiarEstado(EstadoTransaccion.COMPLETADA);
            origen.agregarAlHistorial(transferencia);
            destino.agregarAlHistorial(transferencia);

            System.out.println(transferencia.generarComprobante());

        } catch (SaldoInsuficienteException | CuentaBloqueadaException | CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Recorrer un array Empleado[] con instancias de los 3 tipos e 
        //  imprimir el resultado de calcularSalario() en cada uno
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 6
                           =======================================""");
        try {
            banco.registrarEmpleado(new Cajero("C-001", "Carlos", "Torres", LocalDate.parse("1990-05-10"),
                                               "torres@banco.com", "LEG-001", LocalDate.parse("2020-01-15"),
                                               2500000, Turno.MAÑANA, "Sucursal Centro"));

            banco.registrarEmpleado(new AsesorFinanciero("A-001", "Laura", "Gomez", LocalDate.parse("1988-03-22"),
                                                         "gomez@banco.com", "LEG-002", LocalDate.parse("2018-06-01"),
                                                         3500000, 500000, 10000000));

            banco.registrarEmpleado(new GerenteSucursal("G-001", "Ricardo", "Perez", LocalDate.parse("1980-11-05"),
                                                        "perez@banco.com", "LEG-003", LocalDate.parse("2010-03-10"),
                                                        6000000, "Sucursal Norte", 500000000));  
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        

        // Recorrer e imprimir salario de cada uno
        Empleado[] empleados = banco.getEmpleados();
        for (int i = 0; i < banco.getTotalEmpleados(); i++) {
            Empleado e = empleados[i];
            System.out.println("---------------------------------------");
            System.out.println("Empleado :  " + e.getNombreCompleto());
            System.out.println("Tipo     :  " + e.obtenerTipo());
            System.out.println("Salario  :  $" + e.calcularSalario());
            System.out.println("Bono     :  $" + e.calcularBono());
        }
        
        // ===================================================================
        //  Recorrer un array Cuenta[] con los 3 tipos e imprimir el resultado 
        //  de calcularInteres() en cada una
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 7
                           =======================================""");
        
        Cuenta[] cuentas = banco.getCuentas();
        for (int i = 0; i < banco.getTotalCuentas(); i++) {
            Cuenta cuenta = cuentas[i];
            System.out.println(cuenta.obtenerResumen());
            System.out.println("Interes Calculado : $" + cuenta.calcularInteres());
        }
        
        // ===================================================================
        //  Intentar cambiar el estado de una transacción con una transición 
        //  inválida y capturar EstadoTransaccionInvalidoException
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 8
                           =======================================""");
        try {
            System.out.println("---------------------------------------");
            System.out.println(" Captura de error de EstadoTransaccion");
            
            Cuenta origen = clienteN1.buscarCuenta("CA-001");
            Cuenta destino = clienteN2.buscarCuenta("CC-001");
            
            Transaccion t = new Transaccion("T-002", origen, destino, 100000,
                                            EstadoTransaccion.PENDIENTE, "Prueba transicion invalida");

            t.cambiarEstado(EstadoTransaccion.PROCESANDO);
            t.cambiarEstado(EstadoTransaccion.COMPLETADA);
            t.cambiarEstado(EstadoTransaccion.PROCESANDO); // <- Error: COMPLETADA no puede volver a PROCESANDO

        } catch (EstadoTransaccionInvalidoException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Intentar aprobar un crédito desde un Cajero y capturar 
        //  PermisoInsuficienteException
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 9
                           =======================================""");
        
        try {
            System.out.println("---------------------------------------");
            System.out.println("   Captura de error al AprobarCredito");
            
            // Obtener el gerente y el cajero del banco
            GerenteSucursal gerente = (GerenteSucursal) banco.getEmpleados()[2];
            Cajero cajero = (Cajero) banco.getEmpleados()[0];
            CuentaCredito ccr = new CuentaCredito("CCR-002", 7000000, "Sistema", 4000000, 0.018);

            // El cajero intenta aprobar un crédito — solo el gerente puede
            gerente.aprobarCredito(cajero, ccr);

        } catch (PermisoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Llamar a notificar() en un cliente que acepta notificaciones y 
        //  en uno que no
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 10
                           =======================================""");
        
        // Cliente que acepta notificaciones (por defecto true)
        clienteN1.notificar("El deposito de $500.000 fue procesado exitosamente.");

        // Cliente que no acepta notificaciones
        clienteN2.setAceptaNotificaciones(false);
        clienteN2.notificar("Su depesito de $300.000 fue procesado exitosamente.");
        
        // ===================================================================
        //  Llamar a registrarModificacion() sobre una cuenta y luego imprimir 
        //  obtenerUltimaModificacion() y obtenerUsuarioModificacion()
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 11
                           =======================================""");
        
        Cuenta cuenta = clienteN1.buscarCuenta("CA-001");
        
        cuenta.registrarModificacion("Asesor Financiero");

        System.out.println("Ultima modificacion: " + cuenta.obtenerUltimaModificacion().format(formato));
        System.out.println("Usuario modificador: " + cuenta.obtenerUsuarioModificacion());
        
        // ===================================================================
        //  Calcular e imprimir la nómina total del banco
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 12
                           =======================================""");
        
        double nominaTotal = banco.calcularNominaTotal();
        System.out.println("Nomina total del banco: $" + nominaTotal);
        
    }
}
