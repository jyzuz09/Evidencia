public class Cita {
    private String folio;
    private String fecha;
    private String hora;
    private Doctor doctor;
    private Paciente paciente;
    private String motivo;

    public Cita(String folio, String fecha, String hora, Doctor doctor, Paciente paciente, String motivo) {
        this.folio = folio;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.paciente = paciente;
        this.motivo = motivo;
    }

    public String getFolio() {
        return folio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getMotivo() {
        return motivo;
    }
}
