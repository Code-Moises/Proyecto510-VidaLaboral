import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class VidaLaboral {
    private List<Contrato> contratos;

    // Constructor
    public VidaLaboral() {
        this.contratos = new ArrayList<>();
    }

    // Método para agregar un contrato
    public void addContrato(Contrato contrato) {
        contratos.add(contrato);
    }

    // Método para calcular los días totales cotizados
    public int diasCotizados() {
        int totalDias = 0;
        for (Contrato contrato : contratos) {
            totalDias += contrato.duracion(LocalDate.now());  // Usamos la fecha actual
        }
        return totalDias;
    }

    // Método para generar el informe de la vida laboral
    public String informeVidaLaboral(LocalDate fechaInforme) {
        int totalDiasTrabajados = 0;
        String informe = String.format("[-Esta vida laboral a fecha de hoy (%s) contiene %d contratos que son:\n", 
                                      fechaInforme, contratos.size());

        // Recorremos los contratos y generamos el informe
        for (int i = 0; i < contratos.size(); i++) {
            Contrato contrato = contratos.get(i);
            int diasContrato = (int) contrato.duracion(fechaInforme);  // Utilizamos el método duracion() de la clase Contrato
            totalDiasTrabajados += diasContrato;

            // Información básica sobre el contrato
            informe += String.format("# Contrato nº%d: El contrato %s tiene fecha de inicio %s, ", 
                                     i, contrato.getNombreContrato(), contrato.getFechaInicio());
            informe += String.format("\n  contrato finalizado en la fecha %s, ha tenido una duración de %d días.\n", 
                                     contrato.getFechaFinal() != null ? contrato.getFechaFinal() : fechaInforme, diasContrato);
            
            // Calcular el período en años, meses y días
            LocalDate fechaFinalUso = (contrato.getFechaFinal() != null) ? contrato.getFechaFinal() : fechaInforme;
            Period periodo = Period.between(contrato.getFechaInicio(), fechaFinalUso);
            informe += String.format("  Período de %d años, %d meses y %d días.\n", 
                                     periodo.getYears(), periodo.getMonths(), periodo.getDays());
        }

        // Añadimos el total de días trabajados
        informe += String.format("Total de días cotizados hasta la fecha: %d días. \n-]", totalDiasTrabajados);
        return informe;
    }

    // Métodos getter
    public List<Contrato> getContratos() {
        return contratos;
    }
}
