import java.util.ArrayList;
import java.util.List;

class SistemaCitas {
    private List<Usuario> usuarios;
    private List<Cita> citas;

    public SistemaCitas() {
        usuarios = new ArrayList<>();
        citas = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}