class Paciente extends Usuario {
    private String nombre;
    private int edad;

    public Paciente(String id, String contraseña, String nombre, int edad) {
        super(id, contraseña, "paciente");
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void verCitas(SistemaCitas sistema) {
        for (Cita cita : sistema.getCitas()) {
            if (cita.getPaciente().equals(this)) {
                System.out.println("Folio: " + cita.getFolio() + " | Cita: " + cita.getFecha() + " " + cita.getHora() + " | con doctor: " + cita.getDoctor().getNombre() + " | Motivo: " + cita.getMotivo());
            }
        }
    }
    public boolean verificarAcceso(String id, String contraseña, SistemaCitas sistema) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getRol().equals("paciente")) {
                return true;
            }
        }
        return false;
    }
}
