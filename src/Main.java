
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class Main {
    private static SistemaCitas sistema = new SistemaCitas();
    private static Scanner scanner = new Scanner(System.in);
    private static final String DB_FOLDER = "db";  // Carpeta donde se guardarán los archivos

    public static void main(String[] args) {

        //db folder
        File dbFolder = new File(DB_FOLDER);
        if (!dbFolder.exists()) {
            dbFolder.mkdir();  // Crear la carpeta db si no existe
        }
        // Cargar los registros previos desde los archivos CSV
        cargarRegistros();


        Administrador admin = new Administrador("admin1", "adminpass");
        sistema.agregarUsuario(admin);

        Doctor doctor = new Doctor("0000", "0000", "default", "default");
        Paciente paciente = new Paciente("0000", "0000", "default", 0);


        //Menú login

        boolean ciclo = true;
        while(ciclo) {
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
                                    if(obtenerDoctorPorIdaux(id)){
                                        System.out.print("ID ocupado: ");
                                    }else {
                                        registrarDoctor(id);
                                    }
                                    break;
                                case 2:
                                    System.out.print("Ingrese ID del Paciente: ");
                                    String id1 = scanner.nextLine();
                                    if(obtenerPacientePorIdaux(id1)){
                                        System.out.print("ID ocupado: ");
                                    }else {
                                        registrarPaciente(id1);
                                    }
                                    break;
                                case 3:
                                    crearCita();
                                    break;
                                case 4:
                                    mostrarCitas();
                                    break;
                                case 10:
                                    continuar = false;
                                    System.out.println("Saliendo del sistema...");
                                    break;
                                case 6:
                                    mostrarDoctores();
                                    break;
                                case 5:
                                    mostrarPacientes();
                                    break;
                                case 7:
                                    System.out.print("Ingrese Folio de la cita a eliminar: ");
                                    String folioCita = scanner.nextLine();
                                    eliminarCita1(folioCita);
                                    break;
                                case 8:
                                    System.out.print("Ingrese ID del Paciente a eliminar: ");
                                    String idPacienteEliminar = scanner.nextLine();
                                    eliminarPaciente(idPacienteEliminar);
                                    break;
                                case 9:
                                    System.out.print("Ingrese ID del Doctor a eliminar: ");
                                    String idDoctorEliminar = scanner.nextLine();
                                    eliminarDoctor(idDoctorEliminar);
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
                    if(verificarAccesoDoctor(id, doctor)){
                        obtenerDoctorPorId(id).verCitas(sistema);

                    }
                    break;


                case 3:
                    System.out.println("Ingrese ID de Paciente:");
                    String id1 = scanner.nextLine();
                    if(verificarAccesoPaciente(id1, paciente)){
                        obtenerPacientePorId(id1).verCitas(sistema);

                    }
                    break;
                case 4:
                    ciclo=false;
                    break;


            }
        } guardarRegistros();
    }
    /////NEW ADDED////
    public static void escribirTXT(String archivo, String mensaje) {
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.append(mensaje);
            writer.append("\n");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios en el archivo CSV: " + e.getMessage());
        }
    }

    // Método para cargar los registros desde los archivos CSV
    public static void cargarRegistros() {
        CargarRegistroDoctores();
        CargarRegistroPacientes();
        CargarRegistroCitas();
    }

    // Cargar los doctores desde el archivo CSV
    public static void CargarRegistroDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FOLDER + "/doctor_registros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 3) { // ID, Nombre, Especialidad
                    String id = campos[0];
                    String nombre = campos[1];
                    String especialidad = campos[2];
                    Doctor doctor = new Doctor(id, "default", nombre, especialidad);
                    sistema.agregarUsuario(doctor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los doctores desde el archivo CSV: " + e.getMessage());
        }
    }


    // Cargar los pacientes desde el archivo CSV
    public static void CargarRegistroPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FOLDER + "/paciente_registros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 3) { // ID, Nombre, Edad
                    String id = campos[0];
                    String nombre = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    Paciente paciente = new Paciente(id, "default", nombre, edad);
                    sistema.agregarUsuario(paciente);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los pacientes desde el archivo CSV: " + e.getMessage());
        }
    }

    // Cargar las citas desde el archivo CSV
    public static void CargarRegistroCitas() {
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FOLDER + "/cita_registros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 6) { // Folio, Fecha, Hora, Doctor, Paciente, Motivo
                    String folio = campos[0];
                    String fecha = campos[1];
                    String hora = campos[2];
                    String idDoctor = campos[3];
                    String idPaciente = campos[4];
                    String motivo = campos[5];

                    Doctor doctor = obtenerDoctorPorId(idDoctor);
                    Paciente paciente = obtenerPacientePorId(idPaciente);

                    if (doctor != null && paciente != null) {
                        Cita cita = new Cita(folio, fecha, hora, doctor, paciente, motivo);
                        sistema.agregarCita(cita);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las citas desde el archivo CSV: " + e.getMessage());
        }
    }

    // Guardar los registros en los archivos CSV
    public static void guardarRegistros() {
        // Guardar los doctores
        try (FileWriter writer = new FileWriter(DB_FOLDER + "/doctor_registros.csv")) {
            for (Usuario usuario : sistema.getUsuarios()) {
                if (usuario instanceof Doctor) {
                    Doctor doctor = (Doctor) usuario;
                    writer.append(doctor.getId()).append(",")
                            .append(doctor.getNombre()).append(",")
                            .append(doctor.getEspecialidad()).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los doctores en el archivo CSV: " + e.getMessage());
        }

        // Guardar los pacientes
        try (FileWriter writer = new FileWriter(DB_FOLDER + "/paciente_registros.csv")) {
            for (Usuario usuario : sistema.getUsuarios()) {
                if (usuario instanceof Paciente) {
                    Paciente paciente = (Paciente) usuario;
                    writer.append(paciente.getId()).append(",")
                            .append(paciente.getNombre()).append(",")
                            .append(String.valueOf(paciente.getEdad())).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los pacientes en el archivo CSV: " + e.getMessage());
        }

        // Guardar las citas
        try (FileWriter writer = new FileWriter(DB_FOLDER + "/cita_registros.csv")) {
            for (Cita cita : sistema.getCitas()) {
                writer.append(cita.getFolio()).append(",")
                        .append(cita.getFecha()).append(",")
                        .append(cita.getHora()).append(",")
                        .append(cita.getDoctor().getNombre()).append(",")
                        .append(cita.getPaciente().getNombre()).append(",")
                        .append(cita.getMotivo()).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las citas en el archivo CSV: " + e.getMessage());
        }
    }

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
        System.out.println("7. Eliminar Cita");
        System.out.println("8. Eliminar Paciente");
        System.out.println("9. Eliminar Doctor");
        System.out.println("10. Salir");
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

        //NEW ADDED//
        escribirTXT("doctor_registros.csv", "Doctor Registrado" + "ID: " + id + ", Nombre: " + nombre + ", Especialidad: " + especialidad);
    }


    public static void eliminarDoctor (String id) {
        Doctor doctorAEliminar = obtenerDoctorPorId(id);

        if (doctorAEliminar != null) {
            sistema.eliminarUsuario(doctorAEliminar);
            System.out.println("Doctor eliminado correctamente.");
            guardarRegistros();  // Actualizacion del archivo de doctores
        } else {
            System.out.println("Doctor con ID " + id + " no encontrado.");
        }
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

        escribirTXT("paciente_registros.csv","Paciente Registrado" + " ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
    }

    // Método para eliminar paciente mediante ID
    public static void eliminarPaciente (String id) {
        Paciente pacienteAEliminar = obtenerPacientePorId(id);

        if (pacienteAEliminar != null) {
            sistema.eliminarUsuario(pacienteAEliminar);
            System.out.println("Paciente eliminado correctamente.");
            guardarRegistros();  // Actualizacion del archivo de pacientes
        } else {
            System.out.println("Paciente con ID " + id + " no encontrado.");
        }
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
        String folio = generarFolio(5);

        if (doctor != null && paciente != null) {
            admin.crearCita(sistema, folio, fecha, hora, doctor, paciente, motivo);
            System.out.println("Cita creada correctamente.");

            escribirTXT("cita_registros.csv","Cita creada" + " Folio: " + folio + ", Fecha: " + fecha + ", Hora: " + hora + ", Doctor: " + doctor.getNombre() + ", Paciente: " + paciente.getNombre());
        } else {
            System.out.println("Doctor o Paciente no encontrados.");
        }
    }

    // Método para eliminar cita mediante folio
    public static void eliminarCita1 (String folio) {
        Cita citaAEliminar = null;
        for (Cita cita : sistema.getCitas()) {
            if (cita.getFolio().equals(folio)) {
                citaAEliminar = cita;
                break;
            }
        }

        if (citaAEliminar != null) {
            sistema.eliminarCita(citaAEliminar);
            System.out.println("Cita eliminada correctamente.");
            guardarRegistros();  // Actualización del archivo de citas
        } else {
            System.out.println("Cita con folio " + folio + " no encontrada.");
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
    public static boolean obtenerDoctorPorIdaux(String id){
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
            System.out.println("Cita: " + cita.getFolio() +" "+ cita.getFecha() + " " + cita.getHora() + " | Doctor: " + cita.getDoctor().getNombre() + " | Paciente: " + cita.getPaciente().getNombre() + " | Motivo de cita: " + cita.getMotivo());
        }
    }


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


