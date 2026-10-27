import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion = 0;

        while (opcion != 10) {
            System.out.println("\n=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos (orden alfabetico)");
            System.out.println("5. Listar historial (orden de inserción)");
            System.out.println("6. Agregar grupo");
            System.out.println("7. Agregar contacto a grupo");
            System.out.println("8. Listar grupos");
            System.out.println("9. Mostrar contactos por grupo");
            System.out.println("10. Salir");
            System.out.print("Elige una opcion: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("--- AGREGAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    agenda.agregarContacto(new Contacto(nombre, telefono, email));
                    break;

                case 2:
                    System.out.println("--- BUSCAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombreBuscar = scanner.nextLine();
                    Contacto encontrado = agenda.buscarContacto(nombreBuscar);
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombreBuscar);
                    }
                    break;

                case 3:
                    System.out.println("--- ELIMINAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombreEliminar = scanner.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;

                case 4:
                    agenda.listarContactos();
                    break;

                case 5:
                    agenda.listarHistorial();
                    break;

                case 6:
                    System.out.println("--- AGREGAR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();
                    agenda.agregarGrupo(nombreGrupo);
                    break;

                case 7:
                    System.out.println("--- AGREGAR CONTACTO A GRUPO ---");
                    System.out.print("Nombre del contacto: ");
                    String contactoGrupo = scanner.nextLine();
                    System.out.print("Nombre del grupo: ");
                    String grupoDestino = scanner.nextLine();
                    agenda.agregarContactoAGrupo(contactoGrupo, grupoDestino);
                    break;

                case 8:
                    agenda.listarGrupos();
                    break;

                case 9:
                    System.out.println("--- MOSTRAR CONTACTOS POR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String grupoMostrar = scanner.nextLine();
                    agenda.mostrarContactosPorGrupo(grupoMostrar);
                    break;

                case 10:
                    System.out.println("Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Error: Opción inválida. Elige entre 1 y 10.");
            }
        }

        scanner.close();
    }
}
