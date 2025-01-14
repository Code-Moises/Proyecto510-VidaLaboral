import java.time.LocalDate;
import java.time.Period;

public class Trabajador {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private LocalDate fechaNacimiento;
    private VidaLaboral vidaLaboral;
    private static final int DIAS_PARA_COTIZAR_35_ANOS = 12783; // 35 años en días

    public Trabajador(String nombre, String apellido1, String apellido2, String dni, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.vidaLaboral = new VidaLaboral();
    }

    public VidaLaboral getVidaLaboral() {
        return vidaLaboral;
    }

    // Información del trabajador
    public String infoTrabajador() {
        Period period = Period.between(fechaNacimiento, LocalDate.now());
        return "<> " + nombre + " " + apellido1 + " " + apellido2 +
                " nacid@ el " + fechaNacimiento + ", tiene " + period.getYears() + " años, " +
                period.getMonths() + " meses, " + period.getDays() + " días";
    }

    // Método para calcular la jubilación
    public Period faltaJubilacion() {
        LocalDate fechaJubilacion = fechaNacimiento.plusYears(65);
        return Period.between(LocalDate.now(), fechaJubilacion);
    }

    // Método para calcular los días faltantes para cotizar 35 años
    public Period faltaCotizacion() {
        int diasCotizados = vidaLaboral.diasCotizados();
        int diasFaltantes = DIAS_PARA_COTIZAR_35_ANOS - diasCotizados;

        LocalDate fechaFutura = LocalDate.now().plusDays(diasFaltantes);
        return Period.between(LocalDate.now(), fechaFutura);
    }

    // Método para calcular el porcentaje de pensión de jubilación
    public double porcentajePension() {
        int diasCotizados = vidaLaboral.diasCotizados();
        
        if (diasCotizados >= DIAS_PARA_COTIZAR_35_ANOS) {
            return 100.0;  // Si ya ha cotizado los 35 años
        }

        return (diasCotizados / (double) DIAS_PARA_COTIZAR_35_ANOS) * 100;
    }

    // Método para calcular la fecha prevista para obtener el 100% de pensión
    public LocalDate fechaJubilacionCompleta() {
        int diasCotizados = vidaLaboral.diasCotizados();
        int diasFaltantes = DIAS_PARA_COTIZAR_35_ANOS - diasCotizados;

        return LocalDate.now().plusDays(diasFaltantes);
    }

    // Método toString para imprimir la información sobre jubilación
    @Override
    public String toString() {
        Period periodoJubilacion = faltaJubilacion();

        return "Para los 65 años le faltan " + periodoJubilacion.getYears() + " años, " +
                periodoJubilacion.getMonths() + " meses, " + periodoJubilacion.getDays() + " días" +
                "\n<> Si sigues trabajando a los 65 años habrás cotizado un total de " + vidaLaboral.diasCotizados() + " días." + 
                "\n   Para cotizar 35 años (12783 días) le faltan " + faltaCotizacion().getYears() + " años, " +
                faltaCotizacion().getMonths() + " meses, " + faltaCotizacion().getDays() + " días." + 
                "\n   Si te jubilas a los 65 tendrás " + String.format("%.0f",porcentajePension()) + "% de pensión de jubilación." +
                "\n   La fecha prevista para obtener el 100% de jubilación es: " + fechaJubilacionCompleta() +
                "\n<> Si no trabajas más y te jubilas a los 65 tendrás un " + String.format("%.0f",porcentajePension()) + "% de pensión de jubilación.";
    }
}
