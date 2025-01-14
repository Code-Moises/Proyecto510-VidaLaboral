import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        // Crear trabajador
        Trabajador trabajador = new Trabajador("Juan", "Pérez", "Gómez", "12345678A", LocalDate.of(1980, 5, 20));

        // Mostrar información del trabajador
        System.out.println(trabajador.infoTrabajador());

        // Crear contratos
        Contrato contrato1 = new Contrato("Contrato A");
        contrato1.finalizar();  // Finalizado

        Contrato contrato2 = new Contrato("Contrato B", LocalDate.of(2023, 1, 1));  // En vigor

        Contrato contrato3 = new Contrato("Contrato C", LocalDate.of(2019, 4, 11));
        contrato3.finalizar(LocalDate.of(2024, 8, 1)); //finalizado 

        // Agregar contratos a la vida laboral del trabajador
        trabajador.getVidaLaboral().addContrato(contrato1);
        trabajador.getVidaLaboral().addContrato(contrato2);
        trabajador.getVidaLaboral().addContrato(contrato3);

        // Generar y mostrar informe de la vida laboral
        LocalDate fechaInforme = LocalDate.now();  // Fecha de hoy
        String informe = trabajador.getVidaLaboral().informeVidaLaboral(fechaInforme);
        System.out.println(informe);

        // Mostrar información sobre la jubilación
        System.out.println(trabajador);  // Muestra la información sobre jubilación
    }
}
