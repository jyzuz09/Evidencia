class Administrador extends Usuario {
    public Administrador(String id, String contraseña) {
        super(id, contraseña, "administrador");
    }

    public void darAltaDoctor(SistemaCitas sistema, Doctor doctor) {
        sistema.agregarUsuario(doctor);
    }

    public void darAltaPaciente(SistemaCitas sistema, Paciente paciente) {
        sistema.agregarUsuario(paciente);
    }

    public void crearCita(SistemaCitas sistema, String folio, String fecha, String hora, Doctor doctor, Paciente paciente, String motivo) {
        Cita nuevaCita = new Cita(folio, fecha, hora, doctor, paciente, motivo);
        sistema.agregarCita(nuevaCita);
    }

    public boolean verificarAcceso(String id, String contraseña, SistemaCitas sistema) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña) && usuario.getRol().equals("administrador")) {
                return true;
            }
        }
        return false;
    }
}
