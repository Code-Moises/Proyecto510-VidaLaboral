import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Contrato {
    private String nombreContrato;
    private boolean estadoContrato;  
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private static final LocalDate fechaHoy = LocalDate.now();  // Fecha actual por defecto

    // Constructor que recibe nombre y fecha de inicio
    public Contrato(String nombreContrato, LocalDate fechaInicio) {
        this.nombreContrato = nombreContrato;
        this.fechaInicio = fechaInicio;
        this.estadoContrato = true;  
    }

    // Constructor que recibe nombre y usa la fecha de hoy como fecha de inicio
    public Contrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
        this.fechaInicio = fechaHoy;
        this.estadoContrato = true;  
    }

    // Método para finalizar el contrato
    protected void finalizar() {
        this.fechaFinal = fechaHoy;  // Establecer la fecha de finalización como la fecha de hoy
        this.estadoContrato = false; // Cambiar el estado a finalizado
    }

    // Sobrecarga del método finalizar para especificar una fecha final
    protected void finalizar(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
        this.estadoContrato = false;
    }

    // Método para calcular la duración en días
    public long duracion(LocalDate fechaInforme) {
        LocalDate fechaFin = (estadoContrato && fechaFinal == null) ? fechaInforme : fechaFinal;
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    // Getter y Setter
    public String getNombreContrato() {
        return nombreContrato;
    }

    public boolean isEstadoContrato() {
        return estadoContrato;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }
}