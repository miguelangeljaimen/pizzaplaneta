/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pizzaplaneta.ws;

import cl.pizzaplaneta.dao.ProductoDao;
import cl.pizzaplaneta.modelos.Producto;
import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author ricardo
 */
@WebService(serviceName = "Producto")
public class ProductoWs {

    /**
     * This is a sample web service operation
     */
    @WebResult(name = "producto")
    @WebMethod(operationName = "registrarProducto")
    public String registrarProducto(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") long precio
    ) {

        ProductoDao pDao = new ProductoDao();

        try {
            if (nombre == null || nombre.equals("")) {
                return "debe introducir nombre";
            }
            if (descripcion == null || descripcion.equals("")) {
                return "debe introducir descripcion";
            }
            Producto producto = new Producto();
            producto.setId(UUID.randomUUID().toString());
            producto.setNombre(nombre);
            producto.setPrecioUnitario(precio);
            producto.setDescripcion(descripcion); 

            System.out.println("Grabando nombre: " + producto.getNombre());
            System.out.println("Grabando desc: " + producto.getDescripcion());
            System.out.println("Grabando precio: " + producto.getPrecioUnitario());
            System.out.println("Iniciando transaccion");
            
            if(producto != null){
             pDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
            try {
                System.out.println("Preparando grabar");
                pDao.insert(producto); //intento insertar el campo en la BD
                pDao.commit(); //si no da error, hago commit
                System.out.println("Commit");
            } catch (Exception e) {
                e.printStackTrace();

                pDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
                System.out.println("ROllback");
                return "Error: " + e.getLocalizedMessage();

            }
            return "Grabo";
            } else { 
            
                return "Producto es null";
            }
            
           
        } catch (Exception e) {

            return "Error: " + e.getLocalizedMessage();

        }

    }

    /**
     * TODO: consultarCliente registrarCliente registrarProducto consultarPizza
     * registrarVenta
     */
}
