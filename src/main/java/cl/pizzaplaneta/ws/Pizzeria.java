/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pizzaplaneta.ws;

import cl.pizzaplaneta.dao.ProductoDao;
import cl.pizzaplaneta.dao.VentaDao;
import cl.pizzaplaneta.modelos.Venta;
import java.util.Date;
import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import org.hibernate.HibernateException;

/**
 *
 * @author Michel M. <michel@febos.cl>
 */
@WebService(serviceName = "Pizzeria")
public class Pizzeria {

    @WebResult(name = "producto")
    @WebMethod(operationName = "registrarProducto")
    public String registrarProducto(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") long precio
    ) {

        if (nombre == null || nombre.equals("")) {
            return "debe introducir nombre";
        }
        if (descripcion == null || descripcion.equals("")) {
            return "debe introducir descripcion";
        }
        cl.pizzaplaneta.modelos.Producto producto = new cl.pizzaplaneta.modelos.Producto();
        producto.setId(UUID.randomUUID().toString());
        producto.setNombre(nombre);
        producto.setPrecioUnitario(precio);
        producto.setDescripcion(descripcion);

        System.out.println("Grabando nombre: " + producto.getNombre());
        System.out.println("Grabando desc: " + producto.getDescripcion());
        System.out.println("Grabando precio: " + producto.getPrecioUnitario());

        System.out.println("Iniciando transaccion");
        ProductoDao pDao = new ProductoDao();
        pDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
        try {
            System.out.println("Preparando grabar");
            pDao.insert(producto); //intento insertar el campo en la BD
            System.out.println("Commit");

            pDao.commit(); //si no da error, hago commit
            return "Grabo";

        } catch (HibernateException exception) {
            exception.printStackTrace();
            pDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
            System.out.println("ROllback");
            return "Error: " + exception.getLocalizedMessage();

        }

    }
    /**
     * This is a sample web service operation
     *
     * @param resultado
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(
            @WebParam(name = "venta") String resultado
    ) {

        VentaDao vDao = new VentaDao();

        Venta venta = new Venta();
//        venta.setClienteId("ID_CLIENTE_TEST");
//        venta.setFecha(new Date());
//        venta.setFolio(1);
//        venta.setId(UUID.randomUUID().toString());
//        venta.setTotal(13900);

        vDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
        try {

            vDao.insert(venta); //intento insertar el campo en la BD
            vDao.commit(); //si no da error, hago commit
        } catch (Exception e) {
            e.printStackTrace();
            vDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
        }

        return "prueba";
    }
}
