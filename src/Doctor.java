
class Doctor extends Usuario {
    private String nombre;
    private String especialidad;

    public Doctor(String id, String contraseña, String nombre, String especialidad) {
        super(id, contraseña, "doctor");
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void verCitas(SistemaCitas sistema) {
        for (Cita cita : sistema.getCitas()) {
            if (cita.getDoctor().equals(this)) {
                System.out.println("Folio: " + cita.getFolio() + " | Cita: " + cita.getFecha() + " " + cita.getHora() + " | con paciente: " + cita.getPaciente().getNombre() + " | Motivo: " + cita.getMotivo());
            }
        }
    }
    public boolean verificarAcceso(String id, String contraseña, SistemaCitas sistema) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getRol().equals("doctor")) {
                return true;
            }
        }
        return false;
    }
}

