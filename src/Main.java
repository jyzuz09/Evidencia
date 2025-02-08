//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;


public class Main {
    private static SistemaCitas sistema = new SistemaCitas();
    private static Scanner scanner = new Scanner(System.in);

    //FOLDER db

    public static void main(String[] args) {

        //método db


        Administrador admin = new Administrador("admin1", "adminpass");
        sistema.agregarUsuario(admin);

        Doctor doctor = new Doctor("0000", "0000", "default", "default");
        Paciente paciente = new Paciente("0000", "0000", "default", 0);


        //Menú login

        boolean ciclo = true;
        while (ciclo) {
            MenuPrincipal();
            int entrar = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (entrar) {
                case 1: //admin
                    // Menú de interacción con el administrador
                    if (verificarAccesoAdministrador(admin)) {
                        boolean continuar = true;
                        while (continuar) {
                            mostrarMenuAdministrador();
                            int opcion = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer del scanner

                            switch (opcion) {
                                case 1:
                                    System.out.print("Ingrese ID del Doctor: ");
                                    String id = scanner.nextLine();
                                    if (obtenerDoctorPorIdaux(id)) {
                                        System.out.print("ID ocupado: ");
                                    } else {
                                        registrarDoctor(id);
                                    }
                                    break;
                                case 2:
                                    System.out.print("Ingrese ID del Paciente: ");
                                    String id1 = scanner.nextLine();
                                    if (obtenerPacientePorIdaux(id1)) {
                                        System.out.print("ID ocupado: ");
                                    } else {
                                        registrarPaciente(id1);
                                    }
                                    break;
                                case 3:
                                    crearCita();
                                    break;
                                case 4:
                                    mostrarCitas();
                                    break;
                                case 7:
                                    continuar = false;
                                    System.out.println("Saliendo del sistema...");
                                    break;
                                case 6:
                                    mostrarDoctores();
                                    break;
                                case 5:
                                    mostrarPacientes();
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                            }
                        }
                    } else {
                        System.out.println("Acceso denegado. Credenciales incorrectas.");
                    }

                    break;
                case 2:
                    System.out.println("Ingrese ID de Doctor:");
                    String id = scanner.nextLine();
                    if (verificarAccesoDoctor(id, doctor)) {
                        obtenerDoctorPorId(id).verCitas(sistema);

                    }
                    break;


                case 3:
                    System.out.println("Ingrese ID de Paciente:");
                    String id1 = scanner.nextLine();
                    if (verificarAccesoPaciente(id1, paciente)) {
                        obtenerPacientePorId(id1).verCitas(sistema);

                    }
                    break;
                case 4:
                    ciclo = false;
                    break;


            }
        } //guardarRegistros
    }

    /// //NEW ADDED TXT////

    // Método para cargar los registros desde los archivos CSV


    // Cargar los doctores desde el archivo CSV


    // Cargar los pacientes desde el archivo CSV

    // Cargar las citas desde el archivo CSV

    // Guardar los registros en los archivos CSV
    public static void MenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Administrador");
        System.out.println("2. Doctor");
        System.out.println("3. Paciente");
        System.out.println("4. Salir");
        System.out.println("Selecciona una opcion: ");
    }


    // Verificación del acceso del administrador
    public static boolean verificarAccesoAdministrador(Administrador admin) {
        System.out.println("Ingrese ID de Administrador:");
        String id = scanner.nextLine();
        System.out.println("Ingrese Contraseña:");
        String contraseña = scanner.nextLine();
        return admin.verificarAcceso(id, contraseña, sistema);
    }

    public static boolean verificarAccesoDoctor(String id, Doctor doctor) {

        System.out.println("Ingrese Contraseña:");
        String contraseña = scanner.nextLine();
        return doctor.verificarAcceso(id, contraseña, sistema);


    }

    public static boolean verificarAccesoPaciente(String id, Paciente paciente) {

        System.out.println("Ingrese Contraseña:");
        String contraseña = scanner.nextLine();
        return paciente.verificarAcceso(id, contraseña, sistema);


    }

    // Menú de opciones para el administrador
    public static void mostrarMenuAdministrador() {
        System.out.println("\n--- Menú Administrador ---");
        System.out.println("1. Registrar Doctor");
        System.out.println("2. Registrar Paciente");
        System.out.println("3. Crear Cita");
        System.out.println("4. Ver Citas");
        System.out.println("5. Ver Pacientes");
        System.out.println("6. Ver Doctores");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Registrar un doctor
    public static void registrarDoctor(String id) {

        System.out.print("Ingrese Contraseña del Doctor: ");
        String contraseña = scanner.nextLine();
        System.out.print("Ingrese Nombre del Doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Especialidad del Doctor: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, contraseña, nombre, especialidad);
        sistema.agregarUsuario(doctor);
        System.out.println("Doctor registrado correctamente.");

        //NEW ADDED TXT//
    }

    // Registrar un paciente
    public static void registrarPaciente(String id) {

        System.out.print("Ingrese Contraseña del Paciente: ");
        String contraseña = scanner.nextLine();
        System.out.print("Ingrese Nombre del Paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Edad del Paciente: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Paciente paciente = new Paciente(id, contraseña, nombre, edad);
        sistema.agregarUsuario(paciente);
        System.out.println("Paciente registrado correctamente.");

        //escribirTXT
    }

    // Crear una cita
    public static void crearCita() {
        System.out.print("Ingrese la fecha de la cita (AAAA-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese la hora de la cita (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.print("Ingrese motivo de cita: ");
        String motivo = scanner.nextLine();


        System.out.println("Seleccione el Doctor:");
        mostrarDoctores();
        String idDoctor = scanner.nextLine();
        Doctor doctor = obtenerDoctorPorId(idDoctor);

        System.out.println("Seleccione el Paciente:");
        mostrarPacientes();
        String idPaciente = scanner.nextLine();
        Paciente paciente = obtenerPacientePorId(idPaciente);

        Administrador admin = new Administrador("admin1", "adminpass");
        sistema.agregarUsuario(admin);
        //String folio = generarFolio(5);

        if (doctor != null && paciente != null) {
            admin.crearCita(sistema, "FOLIO", fecha, hora, doctor, paciente, motivo);
            System.out.println("Cita creada correctamente.");

            //escribirTXT
        } else {
            System.out.println("Doctor o Paciente no encontrados.");
        }
    }

    // Mostrar todos los doctores
    public static void mostrarDoctores() {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Doctor) {
                System.out.println(usuario.getId() + " - " + ((Doctor) usuario).getNombre() + " - " + ((Doctor) usuario).getEspecialidad());
            }
        }
    }

    // Obtener doctor por ID
    public static Doctor obtenerDoctorPorId(String id) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Doctor && usuario.getId().equals(id)) {
                return (Doctor) usuario;
            }
        }
        return null;
    }

    public static boolean obtenerDoctorPorIdaux(String id) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Doctor && usuario.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Mostrar todos los pacientes
    public static void mostrarPacientes() {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Paciente) {
                System.out.println(usuario.getId() + " - " + ((Paciente) usuario).getNombre());
            }
        }
    }

    // Obtener paciente por ID
    public static Paciente obtenerPacientePorId(String id) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Paciente && usuario.getId().equals(id)) {
                return (Paciente) usuario;
            }
        }
        return null;
    }

    public static boolean obtenerPacientePorIdaux(String id) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario instanceof Paciente && usuario.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Mostrar todas las citas
    public static void mostrarCitas() {
        for (Cita cita : sistema.getCitas()) {
            System.out.println("Cita: " + cita.getFolio() + " " + cita.getFecha() + " " + cita.getHora() + " | Doctor: " + cita.getDoctor().getNombre() + " | Paciente: " + cita.getPaciente().getNombre() + " | Motivo de cita: " + cita.getMotivo());
        }
    }


    // generarFolio
    public static String generarFolio(int longitud) {
        // Definir los caracteres posibles para el folio
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder folio = new StringBuilder();

        // Generar el folio de longitud especificada
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            folio.append(caracteres.charAt(indiceAleatorio));
        }

        return folio.toString();

    }
}


