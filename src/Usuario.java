class Usuario {
    protected String id;
    protected String contraseña;
    protected String rol;

    public Usuario(String id, String contraseña, String rol) {
        this.id = id;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getRol() {
        return rol;
    }
}
