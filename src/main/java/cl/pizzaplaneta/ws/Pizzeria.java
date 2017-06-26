/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pizzaplaneta.ws;

import cl.pizzaplaneta.dao.VentaDao;
import cl.pizzaplaneta.modelos.Venta;
import java.util.Date;
import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Michel M. <michel@febos.cl>
 */
@WebService(serviceName = "Pizzeria")
public class Pizzeria {

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
