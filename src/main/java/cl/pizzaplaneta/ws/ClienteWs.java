/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pizzaplaneta.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author ricardo
 */
@WebService(serviceName = "Venta")
public class ClienteWs {

    /**
     * This is a sample web service operation
     */
    @WebResult(name="pedido")
    @WebMethod(operationName = "registrarPedido")
    public String registrarPedido(
            @WebParam(name = "name") String txt
    ) {
        return "Hello " + txt + " !";
    }
    
    /**
     * TODO:
     * consultarCliente
     * registrarCliente
     * registrarProducto
     * consultarPizza
     * registrarVenta
     */
}
