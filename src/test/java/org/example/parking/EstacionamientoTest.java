package org.example.parking;

import org.example.parking.Vehiculo.Tipo;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class EstacionamientoTest {

    // @Autowired
    // public  Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    // vehiculosEstacionados.put("BH4GJGJ", ticket);

    @Test
    public void testRetirarVehiculo() throws Exception {
        //DONE test
        //Arrange
        Estacionamiento estacionamiento = new Estacionamiento();
        Vehiculo vehiculo = new Vehiculo("BH4GJGJ", "2008", Tipo.SUV);
        Cliente cliente = new Cliente("45834252", "Santiago");
        cliente.agregarVehiculo(vehiculo);
        Ticket ticket = new Ticket(cliente, vehiculo);

        estacionamiento.ingresarVehiculo("45834252", "Santiago", vehiculo);
        //Act
        Ticket result = estacionamiento.retirarVehiculo("BH4GJGJ");
        //Assert
        assertEquals(ticket.getVehiculo(), result.getVehiculo());
        assertEquals(ticket.getCliente(), result.getCliente());
        assertNotEquals(ticket.getHoraSalida(), result.getHoraSalida());
        assertEquals(cliente.getDni(), result.getCliente().getDni());
    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // DONE test
        //Arrange
        Vehiculo vehiculo = new Vehiculo("BH4GJGJ", "2008", Tipo.SUV);
        Cliente cliente = new Cliente("45834252", "Santiago");
        cliente.agregarVehiculo(vehiculo);
        Ticket ticket = new Ticket(cliente, vehiculo);
        ticket.setHoraSalida(ticket.getHoraEntrada().plusHours(2).plusMinutes(30));
        //Act
        double result = ticket.calcularPrecio();
        //Assert
        assertNotNull(result);
        assertEquals(390.0, result);
    }

}