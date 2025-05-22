package org.example.parking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import org.example.parking.Vehiculo.Tipo;

@Data
@AllArgsConstructor
public class Ticket {
    private final Cliente cliente;
    private final Vehiculo vehiculo;
    private final LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Ticket(Cliente cliente, Vehiculo vehiculo) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.horaEntrada = LocalDateTime.now();
    }

    public void marcarSalida() {
        Random random = new Random();
        this.horaSalida = LocalDateTime.now().plusMinutes(random.nextInt(200)+1);
    }

    public long calcularMinutos() {
        return Duration.between(horaEntrada, horaSalida).toMinutes();
    }

    public double calcularPrecio() {
        // DONE implementar el metodo para calcular el importe a abonar segun el tipo de vehiculo
        // AUTO -> 100, SUV -> 130, PICKUP -> 180
        // el importe es por hora redondeando el tiempo hacia arriba,
        // por ejemplo si estuvo 45 minutos se le tarifa por 60, si estuvo 80 minutos se le tarifa por 120 minutos, etc...
        // retornar el importe final
        Duration timeInParking = Duration.between(horaEntrada, horaSalida);
        Long hoursToPay = timeInParking.toMinutes() / 60;
        Long minutes = timeInParking.toMinutes() % 60;

        if (minutes > 0) {
            hoursToPay++;
        }

        double priceToPay = 0;
        if (vehiculo.getTipo().equals(Tipo.AUTO)) {
            priceToPay = 100 * hoursToPay;
        } else if (vehiculo.getTipo().equals(Tipo.SUV)) {
            priceToPay = 130 * hoursToPay;
        } else if (vehiculo.getTipo().equals(Tipo.PICKUP)) {
            priceToPay = 180 * hoursToPay;
        }

        return priceToPay;
    }

}