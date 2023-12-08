/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Clasificacion;
import modelo.Competencia;
import modelo.CompetenciaRol;
import modelo.Factura;
import modelo.ItemFactura;
import modelo.Persona;
import modelo.Producto;
import modelo.Proveedores;
import modelo.Rol;
import modelo.Tipo_Pago;
import modelo.Usuario;
import modelo.UsuarioRol;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "peticiones")
public class peticiones {

    Persona persona = new Persona();
    List<Persona> listaPersonas = persona.personas;
    Usuario nuevoUsuario = new Usuario();
    List<Usuario> listausuarios = nuevoUsuario.usuarios;
    // List<Rol> listaRoles = new ArrayList<>();
    Rol rol = new Rol();
    ArrayList<Rol> rolesexistentes = rol.roles;
    List<Factura> listaFacturas = new ArrayList<>();
    List<ItemFactura> listaitemFacturas = new ArrayList<>();
    ArrayList<Clasificacion> clasiexistentes = new ArrayList<>();
    Competencia compi = new Competencia();
    ArrayList<Competencia> competenciaexistentes = compi.competencias;
    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<Clasificacion> clasificaciones = new ArrayList<>();
    ArrayList<Proveedores> proveedores = new ArrayList<>();
    ArrayList<Tipo_Pago> lista_tipos_pagos = new ArrayList<>();

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registrarTipoPago")
    public Tipo_Pago registrarTipoPago(
            @WebParam(name = "idTipoPago") Integer idTipoPago,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "descripcion") String descripcion) {

        // Verificar si el ID de tipo de pago ya existe
        if (existeTipoPagoConId(idTipoPago)) {
            System.out.println("Error: El ID de tipo de pago ya está en uso.");
            return null;
        }

        // Crear una nueva instancia de Tipo_Pago
        Tipo_Pago nuevoTipoPago = new Tipo_Pago(idTipoPago, tipo, descripcion);

        // Agregar el nuevo tipo de pago a la lista
        lista_tipos_pagos.add(nuevoTipoPago);

        System.out.println("Registro exitoso: Tipo de pago registrado correctamente.");
        return nuevoTipoPago;
    }

    // Método para verificar la existencia de un tipo de pago con un ID específico
    private boolean existeTipoPagoConId(Integer idTipoPago) {
        for (Tipo_Pago tipoPago : lista_tipos_pagos) {
            if (tipoPago.getId_tipo_pago().equals(idTipoPago)) {
                return true;
            }
        }
        return false;
    }

    @WebMethod(operationName = "estadorol")
    public Boolean estadorol(@WebParam(name = "nombrerol") String nombrerol) {
        Rol rol = new Rol();

        ArrayList<Rol> estadospornombre = rol.getRoles();

        for (Rol rols : estadospornombre) {
            if (rols.getRol().equals(nombrerol)) {
                return rols.isEstado();
            }

        }
        return null;
    }

    @WebMethod(operationName = "siexisteComp")
    public Boolean siexisteComp(@WebParam(name = "idcomp") String idcomp) {
        Competencia competencia = new Competencia();

        ArrayList<Competencia> competenciasExistentes = competencia.getCompetencias();

        for (Competencia comps : competenciasExistentes) {
            if (comps.getCompetencias().equals(idcomp)) {
                return true;
            }

        }
        return false;
    }
    private ArrayList<UsuarioRol> bd_tabla_usuario_rol = new ArrayList<>();
    private ArrayList<CompetenciaRol> bd_tabla_competencia_rol = new ArrayList<>();

    @WebMethod(operationName = "siexisterol")
    public Boolean siexisterol(@WebParam(name = "nombre") String nombre) {

        Rol rol2 = new Rol(1, "admin", true);
        Rol rol3 = new Rol(2, "cliente", true);
        Rol rol4 = new Rol(3, "empleado", true);
        Rol rol5 = new Rol(4, "vendedor", true);
        Rol rol6 = new Rol(5, "secretaria", true);
        rol.roles.add(rol2);
        rol.roles.add(rol3);
        rol.roles.add(rol4);
        rol.roles.add(rol5);
        rol.roles.add(rol6);

        for (Rol rols : rolesexistentes) {
            if (rols.getRol().equals(nombre)) {
                return true;
            }

        }
        return false;
    }

    @WebMethod(operationName = "registrarProveedorYClasificacion")
    public String registrarProveedorYClasificacion(
            @WebParam(name = "idProveedor") int idProveedor,
            @WebParam(name = "ruc") String ruc,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "pais") String pais,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "moneda") String moneda,
            @WebParam(name = "grupoClasificacion") String grupoClasificacion) {

        // Verificar si el ID de proveedor o clasificación ya existe
        if (existeProveedorConId(idProveedor) || existeClasificacionConId(idProveedor)) {
            return "Error: El ID de proveedor o clasificación ya está en uso.";
        }

        // Crear una nueva instancia de Clasificacion
        Clasificacion nuevaClasificacion = new Clasificacion(idProveedor, grupoClasificacion);

        // Crear una nueva instancia de Proveedores
        Proveedores nuevoProveedor = new Proveedores(idProveedor, ruc, telefono, pais, correo, moneda, nuevaClasificacion);

        // Agregar la nueva clasificación a la lista de clasificaciones
        clasificaciones.add(nuevaClasificacion);

        // Agregar el nuevo proveedor a la lista de proveedores
        proveedores.add(nuevoProveedor);

        return "Registro exitoso: Proveedor y clasificación registrados correctamente.";
    }

    // Método para verificar la existencia de un proveedor con un ID específico
    private boolean existeProveedorConId(int idProveedor) {
        for (Proveedores proveedor : proveedores) {
            if (proveedor.getId_proveedor() == idProveedor) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar la existencia de una clasificación con un ID específico
    private boolean existeClasificacionConId(int idClasificacion) {
        for (Clasificacion clasificacion : clasificaciones) {
            if (clasificacion.getId_clasificacion() == idClasificacion) {
                return true;
            }
        }
        return false;
    }

    @WebMethod(operationName = "registrarCompetenciaRol")
    public String registrarCompetenciaRol(
            @WebParam(name = "idRol") int idRol,
            @WebParam(name = "nombreRol") String nombreRol,
            @WebParam(name = "estadoRol") boolean estadoRol,
            @WebParam(name = "idCompetencia") int idCompetencia,
            @WebParam(name = "nombreCompetencia") String nombreCompetencia,
            @WebParam(name = "estadoCompetencia") boolean estadoCompetencia,
            @WebParam(name = "idClasificacion") int idClasificacion,
            @WebParam(name = "grupoClasificacion") String grupoClasificacion) {

        try {

            Rol rolExistente = null;
            for (Rol rol : rolesexistentes) {
                if (rol.getId_rol() == idRol) {
                    rolExistente = rol;
                    break;
                }
            }

            if (rolExistente == null) {
                rolExistente = new Rol(idRol, nombreRol, estadoRol);
                rolesexistentes.add(rolExistente);
            }

            // Verificar si la competencia ya existe
            Competencia competenciaExistente = null;
            for (Competencia competencia : competenciaexistentes) {
                if (competencia.getId_competencia() == idCompetencia) {
                    competenciaExistente = competencia;
                    break;
                }
            }

            if (competenciaExistente == null) {
                competenciaExistente = new Competencia(idCompetencia, nombreCompetencia, estadoCompetencia);
                competenciaexistentes.add(competenciaExistente);
            }

            Clasificacion clasificacion = new Clasificacion(idClasificacion, grupoClasificacion);
            clasiexistentes.add(clasificacion);

            CompetenciaRol competenciaRol = new CompetenciaRol(rolExistente, competenciaExistente);
            bd_tabla_competencia_rol.add(competenciaRol);

            return "Registro exitoso: Rol, Competencia y Clasificación registrados correctamente.";
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
            return "Error al registrar la relación Competencia-Rol.";
        }
    }

    @WebMethod(operationName = "registrarUsuarioPersonaRol")
    public Boolean registrarUsuarioPersonaRol(
            @WebParam(name = "idUsuario") int idUsuario,
            @WebParam(name = "idPersona") int idPersona,
            @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "passwordUsuario") String passwordUsuario,
            @WebParam(name = "nombrePersona") String nombrePersona,
            @WebParam(name = "apellidoPersona") String apellidoPersona,
            @WebParam(name = "dniPersona") String dniPersona,
            @WebParam(name = "celularPersona") String celularPersona,
            @WebParam(name = "correoPersona") String correoPersona,
            @WebParam(name = "idRol") int idRol,
            @WebParam(name = "nombreRol") String nombreRol,
            @WebParam(name = "estadoRol") boolean estadoRol) {

        Persona nuevaPersona = new Persona(idPersona, nombrePersona, apellidoPersona, dniPersona, celularPersona, correoPersona);

        Rol nuevoRol = new Rol(idRol, nombreRol, estadoRol);
        // Verificar si el rol ya existe
        Rol rolExistente = null;
        for (Rol rol : rolesexistentes) {
            if (rol.getId_rol() == idRol) {
                rolExistente = rol;
                break;
            }
        }

        if (rolExistente == null) {
            rolExistente = new Rol(idRol, nombreRol, estadoRol);
            rolesexistentes.add(rolExistente);
        }
        Usuario nuevoUsuario2 = new Usuario(idUsuario, idPersona, nombreUsuario, passwordUsuario, nuevaPersona);

        if (nuevoUsuario.existeUsuario(nombreUsuario)) {
            return false;
        } else {

            listausuarios.add(nuevoUsuario2);
            listaPersonas.add(nuevaPersona);
            rolesexistentes.add(nuevoRol);
            UsuarioRol usuariorol = new UsuarioRol(nuevoUsuario2, nuevoRol);
            bd_tabla_usuario_rol.add(usuariorol);

            return true;
        }
    }

    @WebMethod(operationName = "registrarrol")
    public String registrarrol(
            @WebParam(name = "rolnombre") String rolnombre,
            @WebParam(name = "estado") boolean estado,
            @WebParam(name = "id") int id) {

        try {
            // Crear una nueva instancia de Rol
            Rol rol = new Rol(id, rolnombre, estado);

            // Verificar si el rol ya existe
            for (Rol rols : rolesexistentes) {
                if (rols.getId_rol() == id) {
                    return "El rol con el mismo ID ya existe. No se puede registrar.";
                }
            }

            // Agregar el nuevo rol a la lista
            rolesexistentes.add(rol);
            return "Éxito al registrar el rol";
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprime la traza de la excepción (puedes cambiar esto según tus necesidades)
            return "Error al registrar el rol";
        }
    }

    @WebMethod(operationName = "loginUsuario")
    public String loginUsuario(
            @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "passwordUsuario") String passwordUsuario) {

        String rol = "";
        Usuario usuarioEncontrado = null;
        for (UsuarioRol usuario : bd_tabla_usuario_rol) {
            if (usuario.getId_usuario().getUser().equals(nombreUsuario) && usuario.getId_usuario().getPassword().equals(passwordUsuario)) {
                usuarioEncontrado = usuario.getId_usuario();
                rol = usuario.getId_rol().getRol();

            }
        }

        // Verificar si se encontró el usuario
        if (usuarioEncontrado != null) {
            // Obtener el nombre de usuario
            String nombre = usuarioEncontrado.getPersona().getNombre();

            // Devolver información
            return "Login exitoso. Usuario: " + nombre + " y su rol es: " + rol;
        } else {
            // Usuario no encontrado
            return "Usuario o contraseña incorrectos. Por favor, verifica tus credenciales.";
        }
    }

    @WebMethod(operationName = "buscarP")
    public int buscarP(@WebParam(name = "dni") String dni) {

        if (persona.buscarPorDni(dni) != -1) {
            return persona.buscarPorDni(dni);
        }

        return -1;
    }

    @WebMethod(operationName = "buscarProductoPorId")
    public Producto buscarProductoPorId(@WebParam(name = "idProducto") int idProducto) {
        for (Producto producto : productos) {
            if (producto.getId_producto() == idProducto) {
                return producto;
            }
        }
        return null;
    }

    @WebMethod(operationName = "registrarFacturaConItems")
    public String registrarFacturaConItems(
            @WebParam(name = "idFactura") Integer idFactura,
            @WebParam(name = "ruc") String ruc,
            @WebParam(name = "idPersona") int idPersona,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "idTipoPago") int idTipoPago,
            @WebParam(name = "descuento") Double descuento,
            @WebParam(name = "total") Double total,
            @WebParam(name = "itemsFactura") List<ItemFactura> itemsFactura) {

        try {
            // Transformar la cadena de fecha a tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = sdf.parse(fecha);

            // Crear la nueva factura
            Factura nuevaFactura = new Factura(idFactura, ruc, idPersona, fechaDate, idTipoPago, descuento, total);

            // Agregar la nueva factura a la lista
            listaFacturas.add(nuevaFactura);

            // Asignar la factura a cada ítem y agregar los ítems a la lista
            for (ItemFactura itemFactura : itemsFactura) {
                // Asignar la factura al ítem
                itemFactura.setId_fcatura(itemFactura.getId_fcatura());

                // Agregar el ítem a la lista
                listaitemFacturas.add(itemFactura);
            }

            return "Factura y items registrados con éxito.";
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
            return "Error al registrar la factura y los items.";
        }
    }

    //listados
    @WebMethod(operationName = "listarPersonas")
    public List<Persona> listarPersonas() {
        return listaPersonas;
    }

    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> listarUsuarios() {
        return listausuarios;
    }
    @WebMethod(operationName = "listarClasificacion")
    public List<Clasificacion> listarClasificacion() {
        return clasificaciones;
    }
    @WebMethod(operationName = "listarProveedor")
    public List<Proveedores> listarProveedor() {
        return proveedores;
    }

    @WebMethod(operationName = "buscarRUCProveedorPorID")
    public String buscarRUCProveedorPorID(@WebParam(name = "idProveedor") int idProveedor) {
        for (Proveedores proveedor : proveedores) {
            if (proveedor.getId_proveedor() == idProveedor) {
                return proveedor.getRuc();
            }
        }

        return "Proveedor no encontrado";
    }

    @WebMethod(operationName = "buscarGrupoClasificacionPorID")
    public String buscarGrupoClasificacionPorID(@WebParam(name = "idClasificacion") int idClasificacion) {
        for (Clasificacion clasificacion : clasificaciones) {
            if (clasificacion.getId_clasificacion() == idClasificacion) {
                return clasificacion.getGrupo();
            }
        }
        // Si no se encuentra la clasificación, puedes devolver un mensaje de error o manejarlo según tus necesidades.
        return "Clasificación no encontrada";
    }
//

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registrarProductos")
    public Boolean registrarProductos(@WebParam(name = "id") int id, @WebParam(name = "stock") int stock, @WebParam(name = "unidad") String unidad, @WebParam(name = "categoria") int categoria, @WebParam(name = "proveedor") int proveedor, @WebParam(name = "iva") boolean iva) {
        try {
            Producto producto = new Producto(proveedor, stock, proveedor, unidad, categoria, proveedor, iva);
            for (Producto productor: productos) {
                if (productor.getId_producto()==id) {
                    return false;
                }
            }
            productos.add(producto);
            return true;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarClasificacionporNombre")
    public Integer buscarClasificacionporNombre(@WebParam(name = "nombre") String nombre) {
        for(Clasificacion clasificacion : clasificaciones){
            if (clasificacion.getGrupo().toLowerCase()== nombre.toLowerCase()) {
                return clasificacion.getId_clasificacion();
            }
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarProveedorporRUC")
    public Integer buscarProveedorporRUC(@WebParam(name = "ruc") String ruc) {
        for (Proveedores proveedor: proveedores) {
            if (proveedor.getRuc().equals(ruc)) {
                return proveedor.getId_proveedor();
            }
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registrarproveedor")
    public Boolean registrarproveedor(@WebParam(name = "id") int id, @WebParam(name = "ruc") String ruc, @WebParam(name = "telefono") String telefono, @WebParam(name = "pais") String pais, @WebParam(name = "correo") String correo, @WebParam(name = "moneda") String moneda) {
        try {
            Proveedores proveedor = new Proveedores(id, ruc, telefono, pais, correo, moneda);
            for (Proveedores proveedors :proveedores) {
                if (proveedors.getRuc().equals(ruc)) {
                    return false;
                }
            }
            proveedores.add(proveedor);
            return true;
        } catch (Exception e) {
            return null;
        }
        
    }

}
