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
    public Producto registrarProducto(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") String precio
    ) {
        Producto producto=new Producto();
        producto.setId(UUID.randomUUID().toString());
        producto.setNombre(nombre);
        producto.setPrecioUnitario(precio);
        producto.setDescripcion(descripcion);
        ProductoDao pdao =new ProductoDao();
        pdao.insert(producto);
        
        Producto p=pdao.getById(producto.getId());
        
        
        return p;
    }

    /**
     * TODO: consultarCliente registrarCliente registrarProducto consultarPizza
     * registrarVenta
     */
}
