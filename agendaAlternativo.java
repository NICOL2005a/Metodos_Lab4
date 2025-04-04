
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaAlternativa {

    // Clase Student mejorada con encapsulamiento
    static class Student {

        private String nombre;
        private String boleta;
        private String grupo;

        // Constructor
        public Student(String nombre, String boleta, String grupo) {
            this.nombre = nombre;
            this.boleta = boleta;
            this.grupo = grupo;
        }

        // Getters y setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getBoleta() {
            return boleta;
        }

        public void setBoleta(String boleta) {
            this.boleta = boleta;
        }

        public String getGrupo() {
            return grupo;
        }

        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + "\nBoleta: " + boleta + "\nGrupo: " + grupo;
        }
    }

    // Clase para manejar la agenda
    static class StudentAgenda {

        private List<Student> students;
        private final int CAPACIDAD_MAXIMA = 10;

        public StudentAgenda() {
            students = new ArrayList<>();
        }

        public boolean agregarAlumno(Student student) {
            if (students.size() >= CAPACIDAD_MAXIMA) {
                return false;
            }
            students.add(student);
            return true;
        }

        public boolean editarAlumno(int indice, Student student) {
            if (indice < 0 || indice >= students.size()) {
                return false;
            }
            students.set(indice, student);
            return true;
        }

        public boolean borrarAlumno(int indice) {
            if (indice < 0 || indice >= students.size()) {
                return false;
            }
            students.remove(indice);
            return true;
        }

        public Student buscarAlumnoPorBoleta(String boleta) {
            for (Student student : students) {
                if (student.getBoleta().equals(boleta)) {
                    return student;
                }
            }
            return null;
        }

        public List<Student> obtenerTodosLosAlumnos() {
            return new ArrayList<>(students);
        }

        public int getTotalAlumnos() {
            return students.size();
        }

        public boolean estaVacia() {
            return students.isEmpty();
        }

        public boolean estaLlena() {
            return students.size() >= CAPACIDAD_MAXIMA;
        }
    }

    // Clase de interfaz de usuario
    static class AgendaUI {

        private Scanner scanner;
        private StudentAgenda agenda;

        public AgendaUI(Scanner scanner, StudentAgenda agenda) {
            this.scanner = scanner;
            this.agenda = agenda;
        }

        public void iniciar() {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEnteroConValidacion("Digite su opcion: ");

                procesarOpcion(opcion);

            } while (opcion != 6);
        }

        private void mostrarMenu() {
            System.out.println("\n\t Bienvenido a la agenda");
            System.out.println("\t 1) Agregar Alumno");
            System.out.println("\t 2) Editar Alumno");
            System.out.println("\t 3) Borrar Alumno");
            System.out.println("\t 4) Mostrar Agenda");
            System.out.println("\t 5) Buscar Alumno por Boleta");
            System.out.println("\t 6) Salir");
        }

        private void procesarOpcion(int opcion) {
            switch (opcion) {
                case 1:
                    nuevoAlumno();
                    break;
                case 2:
                    editarAlumno();
                    break;
                case 3:
                    borrarAlumno();
                    break;
                case 4:
                    mostrarAgenda();
                    break;
                case 5:
                    buscarAlumnoPorBoleta();
                    break;
                case 6:
                    System.out.println("Saliendo de la agenda");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }

        private void nuevoAlumno() {
            if (agenda.estaLlena()) {
                System.out.println("Agenda llena");
                return;
            }

            System.out.println("Digite el nombre del alumno");
            String nombre = scanner.nextLine();

            System.out.println("Digite la boleta del alumno");
            String boleta = scanner.nextLine();

            System.out.println("Digite el grupo del alumno");
            String grupo = scanner.nextLine();

            Student nuevoAlumno = new Student(nombre, boleta, grupo);

            if (agenda.agregarAlumno(nuevoAlumno)) {
                System.out.println("Alumno agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el alumno");
            }
        }

        private void editarAlumno() {
            if (agenda.estaVacia()) {
                System.out.println("No hay alumnos registrados para editar");
                return;
            }

            int indice = leerEnteroConValidacion("Ingrese el indice del alumno a editar (0 - "
                    + (agenda.getTotalAlumnos() - 1) + "): ");

            if (indice < 0 || indice >= agenda.getTotalAlumnos()) {
                System.out.println("Indice fuera de rango.");
                return;
            }

            List<Student> alumnos = agenda.obtenerTodosLosAlumnos();
            Student alumnoActual = alumnos.get(indice);

            String nombre = alumnoActual.getNombre();
            String boleta = alumnoActual.getBoleta();
            String grupo = alumnoActual.getGrupo();

            System.out.print("¿Quiere modificar el nombre del alumno? (1-si 0-no): ");
            if (leerEnteroConValidacion("") == 1) {
                System.out.print("Nuevo nombre para " + nombre + ": ");
                nombre = scanner.nextLine();
            }

            System.out.print("¿Quiere modificar la boleta? (1-si 0-no): ");
            if (leerEnteroConValidacion("") == 1) {
                System.out.print("La nueva boleta para " + nombre + " (actual: " + boleta + "): ");
                boleta = scanner.nextLine();
            }

            System.out.print("¿Quiere modificar el grupo? (1-si 0-no): ");
            if (leerEnteroConValidacion("") == 1) {
                System.out.print("El nuevo grupo para " + nombre + " (actual: " + grupo + "): ");
                grupo = scanner.nextLine();
            }

            Student alumnoEditado = new Student(nombre, boleta, grupo);

            if (agenda.editarAlumno(indice, alumnoEditado)) {
                System.out.println("Alumno actualizado:\n" + alumnoEditado);
            } else {
                System.out.println("No se pudo actualizar el alumno");
            }
        }

        private void borrarAlumno() {
            if (agenda.estaVacia()) {
                System.out.println("No hay alumnos registrados para borrar");
                return;
            }

            int indice = leerEnteroConValidacion("Digite el indice del alumno a borrar (0-"
                    + (agenda.getTotalAlumnos() - 1) + "): ");

            if (indice < 0 || indice >= agenda.getTotalAlumnos()) {
                System.out.println("Indice fuera de rango.");
                return;
            }

            if (agenda.borrarAlumno(indice)) {
                System.out.println("Alumno borrado correctamente");
            } else {
                System.out.println("No se pudo borrar el alumno");
            }
        }

        private void buscarAlumnoPorBoleta() {
            if (agenda.estaVacia()) {
                System.out.println("La agenda está vacía.");
                return;
            }

            System.out.print("Ingrese la boleta del alumno a buscar: ");
            String boletaBuscar = scanner.nextLine();

            Student alumnoEncontrado = agenda.buscarAlumnoPorBoleta(boletaBuscar);

            if (alumnoEncontrado != null) {
                System.out.println("\nAlumno encontrado:");
                System.out.println(alumnoEncontrado);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        }

        private void mostrarAgenda() {
            if (agenda.estaVacia()) {
                System.out.println("No hay alumnos registrados.");
                return;
            }

            List<Student> alumnos = agenda.obtenerTodosLosAlumnos();

            System.out.println("\nAgenda de Alumnos:");
            for (int i = 0; i < alumnos.size(); i++) {
                Student alumno = alumnos.get(i);
                System.out.println("Alumno " + i + ":");
                System.out.println(alumno);
                System.out.println("----------------------------");
            }
        }

        private int leerEnteroConValidacion(String mensaje) {
            int valor = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    System.out.print(mensaje);
                    valor = Integer.parseInt(scanner.nextLine());
                    entradaValida = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número entero.");
                }
            }

            return valor;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentAgenda agenda = new StudentAgenda();
        AgendaUI ui = new AgendaUI(scanner, agenda);

        ui.iniciar();

        scanner.close();
    }
}
