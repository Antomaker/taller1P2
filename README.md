# Taller1P2
Evaluación de conocimientos hasta el corte 2

# 🏦 Sistema de Gestión Bancaria (SGB)

> **Asignatura:** Programación Orientada a Objetos
> **Tema:** Clases Abstractas, Interfaces, Excepciones, Herencia y Polimorfismo

---

## 📋 Objetivo de Aprendizaje

Desarrollar una aplicación Java que integre los siguientes conceptos de POO:

- Clases abstractas con métodos abstractos y concretos
- Interfaces y contratos de comportamiento
- Excepciones personalizadas (checked y unchecked)
- Herencia en múltiples niveles
- Polimorfismo (sobrescritura y sobrecarga)
- Encapsulamiento con validaciones en setters
- Arrays estáticos de tamaño fijo para gestión de datos
- Diagramas UML (Clases y Casos de Uso)

---

## 🎯 Descripción del Problema

Un banco necesita digitalizar su sistema de gestión de clientes, cuentas y transacciones. El sistema debe permitir:

**Gestión de Clientes:**
- Registrar clientes naturales y empresariales con datos personales y documentos
- Asociar múltiples cuentas a un cliente (máximo 5 cuentas por cliente)
- Consultar el historial de transacciones (máximo 20 por cuenta)

**Gestión de Cuentas:**
- Manejar diferentes tipos de cuenta: corriente, ahorros y crédito
- Calcular intereses y cargos según el tipo de cuenta
- Bloquear y desbloquear cuentas según criterios de seguridad

**Gestión de Transacciones:**
- Registrar depósitos, retiros y transferencias validando saldos
- Aplicar comisiones automáticamente según el tipo de cuenta y operación
- Cambiar estados de transacción respetando las transiciones válidas

**Gestión de Personal:**
- Contratar cajeros, asesores financieros y gerentes de sucursal
- Calcular salarios con bonificaciones por metas y antigüedad
- Asignar turnos y sucursales

---

## 📖 Historias de Usuario (Product Backlog)

A continuación se listan **todas** las historias de usuario del sistema. Cada historia debe estar implementada y demostrada en la clase principal.

| ID | Historia | Criterios de Aceptación | Prioridad | Story Points |
|:---|:---|:---|:---:|:---:|
| **HU-01** | Como **cajero**, quiero registrar un cliente natural con sus datos personales para aperturar una cuenta en el sistema | - El sistema valida que el documento de identidad no esté repetido. El sistema lanza `CapacidadExcedidaException` si el banco alcanzó su límite de clientes. Los datos nulos o vacíos lanzan "DatoInvalidoException". El email debe contener @ para ser válido y la fecha no debe ser futura.
|:---|:---|:---:|:---:|
| **HU-02** | Como **cajero**, quiero registrar un cliente empresarial con su NIT y razón social para vincularlo y permitirle operar en el sistema del banco| Alta | 5 |
|:---|:---|:---:|:---:|
| **HU-03** | Como **cajero**, quiero abrir una cuenta a un cliente existente para que pueda operar en el banco | Alta | 3 |
|:---|:---|:---:|:---:|
| **HU-04** | Como **cajero**, quiero registrar depósitos en una cuenta para aumentar el saldo del cliente | Alta | 8 |
|:---|:---|:---:|:---:|
---
---

## 🏗️ Requisitos de Diseño UML

### 1. Diagrama de Casos de Uso

Debes identificar y modelar los casos de uso para los siguientes actores:

- **Cajero** — registra clientes, abre cuentas, procesa depósitos y retiros
- **Asesor Financiero** — realiza transferencias, consulta clientes, gestiona estados y bloqueos
- **Gerente de Sucursal** — aprueba créditos, calcula nómina, registra empleados
- **Cliente** — actor externo que recibe notificaciones
- **Sistema Auditor** — consulta historial de modificaciones

### 2. Diagrama de Clases

El diagrama debe incluir **obligatoriamente:**

- La jerarquía completa de herencia (ver sección siguiente)
- Las 4 interfaces con sus métodos y las líneas de realización (`<<implements>>`)
- La jerarquía de excepciones (checked y unchecked)
- Los enums del sistema
- Las relaciones de composición, asociación y dependencia entre clases
- Multiplicidades en todas las relaciones

**Jerarquía de herencia:**

```
Persona (abstracta)
 ├── Empleado (abstracta)
 │    ├── Cajero
 │    ├── AsesorFinanciero
 │    └── GerenteSucursal
 └── Cliente (abstracta)
      ├── ClienteNatural
      └── ClienteEmpresarial

Cuenta (abstracta)
 ├── CuentaCorriente
 ├── CuentaAhorros
 └── CuentaCredito
```

---

## 🔷 Interfaces del Sistema

Debes crear las siguientes interfaces en el paquete `modelo.interfaces`. Cada método debe tener implementación real en las clases que las usen — no se aceptan cuerpos vacíos `{}`.

### `Consultable`
Contrato para cualquier entidad que pueda ser consultada en el sistema.

| Método | Descripción |
|:---|:---|
| `String obtenerResumen()` | Retorna un texto con los datos principales de la entidad |
| `boolean estaActivo()` | Indica si la entidad está operativa |
| `String obtenerTipo()` | Retorna una cadena que identifica el tipo concreto |

### `Transaccionable`
Contrato para cuentas que soportan operaciones financieras. Sus métodos deben declarar las excepciones correspondientes con `throws`.

| Método | Descripción |
|:---|:---|
| `void depositar(double monto)` | Aumenta el saldo de la cuenta |
| `void retirar(double monto)` | Disminuye el saldo validando disponibilidad |
| `double calcularComision(double monto)` | Retorna el valor de la comisión según el tipo de cuenta |
| `double consultarSaldo()` | Retorna el saldo disponible actual |

### `Auditable`
Contrato para entidades que registran historial de cambios.

| Método | Descripción |
|:---|:---|
| `LocalDateTime obtenerFechaCreacion()` | Retorna la fecha en que se creó la entidad |
| `LocalDateTime obtenerUltimaModificacion()` | Retorna la fecha del último cambio registrado |
| `String obtenerUsuarioModificacion()` | Retorna el legajo del usuario que hizo el último cambio |
| `void registrarModificacion(String usuario)` | Actualiza la fecha y el usuario de modificación |

### `Notificable`
Contrato para entidades que pueden recibir notificaciones del banco.

| Método | Descripción |
|:---|:---|
| `void notificar(String mensaje)` | Envía (imprime) un mensaje al cliente |
| `String obtenerContacto()` | Retorna el email o teléfono de contacto |
| `boolean aceptaNotificaciones()` | Indica si el cliente tiene activadas las notificaciones |

**¿Qué clase implementa qué interfaz?**

| Clase | Interfaces que debe implementar |
|:---|:---|
| `ClienteNatural` | `Consultable`, `Notificable`, `Auditable` |
| `ClienteEmpresarial` | `Consultable`, `Notificable`, `Auditable` |
| `CuentaCorriente` | `Consultable`, `Transaccionable`, `Auditable` |
| `CuentaAhorros` | `Consultable`, `Transaccionable`, `Auditable` |
| `CuentaCredito` | `Consultable`, `Transaccionable`, `Auditable` |
| `AsesorFinanciero` | `Consultable`, `Auditable` |
| `GerenteSucursal` | `Consultable`, `Auditable` |
| `Banco` | `Auditable` |

---

## 🔴 Excepciones Personalizadas

Debes crear la siguiente jerarquía de excepciones en el paquete `modelo.excepciones`.

### Jerarquía

```
Exception
 └── SistemaBancarioException          ← checked, clase base
      ├── SaldoInsuficienteException
      ├── CuentaBloqueadaException
      ├── ClienteNoEncontradoException
      └── CapacidadExcedidaException

RuntimeException
 └── BancoRuntimeException             ← unchecked, clase base
      ├── DatoInvalidoException
      ├── EstadoTransaccionInvalidoException
      └── PermisoInsuficienteException
```

### Atributos mínimos requeridos por excepción

| Excepción | Atributos adicionales obligatorios |
|:---|:---|
| `SistemaBancarioException` | `codigoError: String`, `timestamp: LocalDateTime` |
| `SaldoInsuficienteException` | `saldoActual: double`, `montoSolicitado: double` |
| `ClienteNoEncontradoException` | `idCliente: String` |
| `CapacidadExcedidaException` | `capacidadMaxima: int` |
| `DatoInvalidoException` | `campo: String`, `valorRecibido: Object` |
| `EstadoTransaccionInvalidoException` | *(mensaje descriptivo con estado origen y destino)* |
| `PermisoInsuficienteException` | *(mensaje descriptivo con la acción denegada)* |

> Todas las excepciones deben tener constructor con mensaje descriptivo, getters para sus atributos adicionales y sobrescribir `toString()` cuando agreguen información relevante.

### ¿Dónde lanzar cada excepción?

| Método | Excepción que debe lanzar |
|:---|:---|
| `Banco.abrirCuenta(...)` | `CapacidadExcedidaException` |
| `Banco.registrarCliente(...)` | `CapacidadExcedidaException` |
| `Banco.buscarCliente(String id)` | `ClienteNoEncontradoException` |
| `Banco.registrarEmpleado(...)` | `CapacidadExcedidaException` |
| `CuentaCorriente.retirar(double)` | `SaldoInsuficienteException`, `CuentaBloqueadaException` |
| `CuentaAhorros.retirar(double)` | `SaldoInsuficienteException`, `CuentaBloqueadaException` |
| `CuentaCredito.retirar(double)` | `CuentaBloqueadaException` |
| Cualquier `depositar(double)` | `CuentaBloqueadaException` |
| `Transaccion.cambiarEstado(...)` | `EstadoTransaccionInvalidoException` |
| Todos los setters con validación | `DatoInvalidoException` |
| `GerenteSucursal.aprobarCredito(...)` cuando lo llama otro tipo de empleado | `PermisoInsuficienteException` |

---

## 🔵 Clases Abstractas

### `Persona` — abstracta

**Atributos privados:** `id`, `nombre`, `apellido`, `fechaNacimiento (LocalDate)`, `email`

**Métodos abstractos que las subclases deben implementar:**

| Método | Propósito |
|:---|:---|
| `int calcularEdad()` | Calcula la edad con base en `fechaNacimiento` y la fecha actual |
| `String obtenerTipo()` | Retorna una cadena que identifica el tipo concreto de persona |
| `String obtenerDocumentoIdentidad()` | Retorna el documento formateado según el tipo de persona |

**Métodos concretos heredables:**

| Método | Propósito |
|:---|:---|
| `String getNombreCompleto()` | Retorna `nombre + " " + apellido` |

**Reglas de validación en setters:** `id` no puede ser nulo ni vacío. `email` debe contener `@`. `fechaNacimiento` no puede ser una fecha futura.

---

### `Empleado` — abstracta, extiende `Persona`

**Atributos privados:** `legajo`, `fechaContratacion (LocalDate)`, `salarioBase`, `activo`

**Métodos abstractos:**

| Método | Propósito |
|:---|:---|
| `double calcularSalario()` | Calcula el salario mensual incluyendo bonificaciones — diferente en cada tipo de empleado |
| `double calcularBono()` | Calcula el bono según metas o especialidad — diferente en cada tipo de empleado |

**Métodos concretos heredables:**

| Método | Propósito |
|:---|:---|
| `int calcularAntigüedad()` | Retorna los años transcurridos desde `fechaContratacion` |

**Regla:** `salarioBase` debe ser mayor a 0. `fechaContratacion` no puede ser una fecha futura.

---

### `Cuenta` — abstracta

**Atributos privados:** `numeroCuenta`, `saldo`, `bloqueada`, `fechaCreacion (LocalDateTime)`, `ultimaModificacion (LocalDateTime)`, `usuarioModificacion`

**Array estático interno:** `Transaccion[] historial` de tamaño máximo 20.

**Métodos abstractos:**

| Método | Propósito |
|:---|:---|
| `double calcularInteres()` | Calcula el interés mensual — fórmula diferente en cada tipo de cuenta |
| `double getLimiteRetiro()` | Retorna el límite máximo de retiro diario según el tipo |
| `String getTipoCuenta()` | Retorna la etiqueta del tipo de cuenta |

**Métodos concretos heredables:**

| Método | Propósito |
|:---|:---|
| `void verificarBloqueada()` | Lanza `CuentaBloqueadaException` si la cuenta está bloqueada |
| `void agregarAlHistorial(Transaccion t)` | Agrega al array interno; lanza `CapacidadExcedidaException` si está lleno |
| `Transaccion[] getHistorial()` | Retorna una **copia** del array (no el original) |

---

## 🟢 Atributos y Métodos por Clase Concreta

### Clases de Personal

| Clase | Atributos adicionales | Comportamiento especial |
|:---|:---|:---|
| `Cajero` | `turno: Turno`, `sucursalAsignada: String`, `transaccionesDia: int` | `calcularSalario()` = salarioBase + bono por transacciones del día |
| `AsesorFinanciero` | `comisionBase: double`, `metasMensuales: double`, `clientesAsignados: Cliente[]` (máx. 20) | `calcularSalario()` = salarioBase + comisión si supera meta |
| `GerenteSucursal` | `sucursal: String`, `presupuestoAnual: double`, `empleadosACargo: Empleado[]` (máx. 30) | `calcularSalario()` = salarioBase + bono por antigüedad + bono fijo de gerencia |

### Clases de Cliente

| Clase | Atributos adicionales |
|:---|:---|
| `ClienteNatural` | `tipoDocumento: TipoDocumento`, `numeroDocumento: String`, `cuentas: Cuenta[]` (máx. 5) |
| `ClienteEmpresarial` | `nit: String`, `razonSocial: String`, `representanteLegal: String`, `cuentas: Cuenta[]` (máx. 5) |

### Clases de Cuenta

| Clase | Atributos adicionales | Fórmula de interés |
|:---|:---|:---|
| `CuentaAhorros` | `tasaInteres: double`, `retirosMesActual: int`, `maxRetirosMes: int` | `saldo * tasaInteres / 12` |
| `CuentaCorriente` | `montoSobregiro: double`, `comisionMantenimiento: double` | No genera interés; cobra comisión mensual fija |
| `CuentaCredito` | `limiteCredito: double`, `tasaInteres: double`, `deudaActual: double` | `deudaActual * tasaInteres / 12` |

### Clase `Transaccion`

**Atributos:** `id`, `cuentaOrigen: Cuenta`, `cuentaDestino: Cuenta` (puede ser null en depósitos/retiros), `monto: double`, `estado: EstadoTransaccion`, `fecha: LocalDateTime`, `descripcion: String`

**Métodos obligatorios:**

| Método | Descripción |
|:---|:---|
| `void cambiarEstado(EstadoTransaccion nuevo)` | Valida la transición y lanza `EstadoTransaccionInvalidoException` si es inválida |
| `String generarComprobante()` | Retorna un texto con los datos de la transacción formateados |

### Clase `Banco`

**Atributos:** `nombre: String`, `empleados: Empleado[]` (máx. 50), `clientes: Cliente[]` (máx. 200), `cuentas: Cuenta[]` (máx. 500)

**Métodos obligatorios:**

| Método | Excepciones que puede lanzar |
|:---|:---|
| `void registrarCliente(Cliente c)` | `CapacidadExcedidaException` |
| `void registrarEmpleado(Empleado e)` | `CapacidadExcedidaException` |
| `void abrirCuenta(String idCliente, Cuenta c)` | `ClienteNoEncontradoException`, `CapacidadExcedidaException` |
| `Cliente buscarCliente(String id)` | `ClienteNoEncontradoException` |
| `double calcularNominaTotal()` | — (usa polimorfismo sobre `empleados[]`) |
| `void calcularInteresesMensuales()` | — (usa polimorfismo sobre `cuentas[]`) |

---

## 🟡 Enums del Sistema

Debes crear los siguientes enums en el paquete `modelo.enums`:

| Enum | Valores |
|:---|:---|
| `EstadoTransaccion` | `PENDIENTE`, `PROCESANDO`, `COMPLETADA`, `RECHAZADA`, `REVERTIDA` |
| `TipoCuenta` | `CORRIENTE`, `AHORROS`, `CREDITO` |
| `Turno` | `MAÑANA`, `TARDE`, `NOCHE` |
| `TipoDocumento` | `CEDULA`, `PASAPORTE`, `NIT`, `CEDULA_EXTRANJERIA` |

**Transiciones de estado válidas para `EstadoTransaccion`:**

| Estado actual | Puede pasar a |
|:---|:---|
| `PENDIENTE` | `PROCESANDO`, `RECHAZADA` |
| `PROCESANDO` | `COMPLETADA`, `RECHAZADA` |
| `COMPLETADA` | `REVERTIDA` |
| `RECHAZADA` | *(estado final — no admite transición)* |
| `REVERTIDA` | *(estado final — no admite transición)* |

---

## 🔧 Restricciones Técnicas

| Aspecto | Requisito |
|:---|:---|
| Lenguaje | Java 17 o superior |
| Manejo de fechas | `java.time.LocalDate`, `java.time.LocalDateTime` — prohibido `java.util.Date` |
| Colecciones | **Arrays estáticos de tamaño fijo** — prohibido `List`, `ArrayList` o cualquier colección de `java.util` |
| Copia de arrays | Los getters de arrays deben retornar una copia con `System.arraycopy()`, nunca el array original |
| Constructores | Uso obligatorio de `super()` en todas las clases hijas |
| Frameworks | Prohibido Spring, Hibernate o cualquier framework externo |
| Librerías | Prohibido Lombok o cualquier librería de reducción de código |
| Tipo explícito | Prohibido el uso de `var` — declarar tipos explícitamente |

---

## 📁 Estructura de Paquetes

| Paquete | Archivos esperados |
|:---|:---|
| `modelo.abstractas` | `Persona.java`, `Empleado.java`, `Cuenta.java` |
| `modelo.personas` | `ClienteNatural.java`, `ClienteEmpresarial.java` |
| `modelo.empleados` | `Cajero.java`, `AsesorFinanciero.java`, `GerenteSucursal.java` |
| `modelo.cuentas` | `CuentaCorriente.java`, `CuentaAhorros.java`, `CuentaCredito.java` |
| `modelo.banco` | `Banco.java`, `Transaccion.java` |
| `modelo.interfaces` | `Consultable.java`, `Transaccionable.java`, `Auditable.java`, `Notificable.java` |
| `modelo.excepciones` | `SistemaBancarioException.java`, `BancoRuntimeException.java` y todas las subclases |
| `modelo.enums` | `EstadoTransaccion.java`, `TipoCuenta.java`, `Turno.java`, `TipoDocumento.java` |
| `principal` | `SistemaBancarioDemo.java` |

---

## 🖥️ Clase Principal — `SistemaBancarioDemo.java`

La clase principal debe demostrar **obligatoriamente** los siguientes escenarios. Cada bloque debe usar `try-catch` cuando el método lanzado sea una excepción checked.

| # | Escenario a demostrar | Concepto evaluado |
|:---:|:---|:---|
| 1 | Registrar al menos 2 clientes naturales y 1 empresarial | Herencia, constructores con `super()` |
| 2 | Abrir al menos una cuenta de cada tipo (corriente, ahorros, crédito) | Polimorfismo, arrays estáticos |
| 3 | Realizar un depósito exitoso y capturar `CuentaBloqueadaException` al intentar depositar en una cuenta bloqueada | Excepciones checked |
| 4 | Realizar un retiro exitoso y capturar `SaldoInsuficienteException` al intentar retirar más del saldo disponible | Excepciones checked, atributos del error |
| 5 | Realizar una transferencia entre dos cuentas | Interfaces `Transaccionable` |
| 6 | Recorrer un array `Empleado[]` con instancias de los 3 tipos e imprimir el resultado de `calcularSalario()` en cada uno | Polimorfismo de sobrescritura |
| 7 | Recorrer un array `Cuenta[]` con los 3 tipos e imprimir el resultado de `calcularInteres()` en cada una | Polimorfismo de sobrescritura |
| 8 | Intentar cambiar el estado de una transacción con una transición inválida y capturar `EstadoTransaccionInvalidoException` | Excepciones unchecked |
| 9 | Intentar aprobar un crédito desde un `Cajero` y capturar `PermisoInsuficienteException` | Excepciones unchecked, control de acceso |
| 10 | Llamar a `notificar()` en un cliente que acepta notificaciones y en uno que no | Interfaces `Notificable` |
| 11 | Llamar a `registrarModificacion()` sobre una cuenta y luego imprimir `obtenerUltimaModificacion()` y `obtenerUsuarioModificacion()` | Interfaces `Auditable` |
| 12 | Calcular e imprimir la nómina total del banco | Polimorfismo, composición |

---

## 📁 Entregables

### Obligatorios

1. **Código fuente Java** organizado en la estructura de paquetes indicada
2. **Diagrama de Casos de Uso** (PNG, JPG o `.puml`) en la carpeta `/docs`
3. **Diagrama de Clases** (PNG, JPG o `.puml`) en la carpeta `/docs` — debe incluir interfaces, excepciones y enums
4. **Este README.md** con la tabla de historias de usuario completada

### Opcionales

- Documentación JavaDoc en todas las clases públicas
- Video corto (máximo 2 minutos) mostrando la ejecución y el manejo de excepciones en consola

---

## ✅ Lista de Verificación Pre-Entrega

Antes de entregar, verifica cada punto:

- [ ] Las clases `Persona`, `Empleado` y `Cuenta` son abstractas y tienen al menos un método abstracto cada una
- [ ] Todas las clases concretas implementan los métodos abstractos de sus clases padre
- [ ] Todas las clases hijas usan `super()` en su constructor
- [ ] Los getters de arrays retornan una copia con `System.arraycopy()` — nunca el array original
- [ ] Las 4 interfaces tienen implementación real en todas las clases indicadas — sin cuerpos vacíos `{}`
- [ ] Existe la jerarquía de excepciones checked con base en `SistemaBancarioException`
- [ ] Existe la jerarquía de excepciones unchecked con base en `BancoRuntimeException`
- [ ] Los setters lanzan `DatoInvalidoException` ante datos nulos, vacíos o fuera de rango
- [ ] `cambiarEstado()` en `Transaccion` lanza `EstadoTransaccionInvalidoException` en transiciones inválidas
- [ ] La clase principal demuestra todos los 12 escenarios de la tabla anterior
- [ ] Los diagramas UML coinciden con el código implementado — mismas clases, atributos y relaciones
- [ ] El código compila y ejecuta sin errores

---

## 🚀 Instrucciones de Entrega

1. Crea un repositorio privado en GitHub haciendo fork al repositorio de la asignatura
2. Sube el código respetando la estructura de paquetes indicada
3. Incluye los diagramas en la carpeta `/docs`
4. Mantén este archivo `README.md` actualizado con tus datos y el estado de la lista de verificación
5. Comparte el enlace al repositorio antes de la fecha límite

---

**Fecha límite de entrega:** Despues de semana santa nota pasada la tutoria colectiva 
**Modalidad:** grupos de proyecto de aula

---

*El objetivo no es que el sistema "funcione de cualquier manera" — es que cada concepto de POO esté aplicado correctamente y con intención. Las interfaces son contratos, las excepciones son mensajes precisos, y la herencia es una relación de especialización, no de reutilización de código.*
