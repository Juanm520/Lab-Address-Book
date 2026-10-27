import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
    private Map<String, Contacto> contactos;
    private Set<String> grupos;
    private Map<String, Set<String>> contactosPorGrupo;
    private List<Contacto> historial;

    public Agenda() {
        this.contactos = new TreeMap<>();
        this.grupos = new TreeSet<>();
        this.contactosPorGrupo = new TreeMap<>();
        this.historial = new ArrayList<>();
    }

    public void agregarContacto(Contacto nuevo_contacto) {

        // Determina si existe el contacto.
        boolean existeContacto = this.contactos.containsKey(nuevo_contacto.getNombre());

        if (existeContacto) {
            System.out.print("Error: Ya existe un contacto con el nombre '" + nuevo_contacto.getNombre() + "'");
            return ;
        }
        this.contactos.put(nuevo_contacto.getNombre(), nuevo_contacto);
        this.historial.add(nuevo_contacto);
        System.out.print("Contacto agregado: " + nuevo_contacto.getNombre());
    }

    public Contacto buscarContacto(String nombre) {
        // Retorna o el contacto, o null, si no encuentra el contacto.
        return this.contactos.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {
        // Determina si existe el contacto.
        boolean existeContacto = this.contactos.containsKey(nombre);

        if (! existeContacto) {
            System.out.print("Contacto no encontrado: " + nombre);
            return false;
        }

        this.contactos.remove(nombre);

        for (Set<String> miembros : this.contactosPorGrupo.values()) {
            miembros.remove(nombre);
        }

        System.out.print("Contacto eliminado: " + nombre);
        return true;
    }

    public void listarContactos() {
        System.out.println("Lista de Contactos (Alfabetico): ");

        for (Contacto contacto : this.contactos.values()) {
            System.out.print(contacto);
        }
    }

    public void listarHistorial() {
        System.out.println("Historial de llamadas (Primera a Ultima):");
        for (Contacto contacto : this.historial) {
            System.out.println(contacto);
        }
    }

    public void agregarGrupo(String nombreGrupo) {
        // Determina si existe el grupo
        boolean existeGrupo = this.grupos.contains(nombreGrupo);

        if (existeGrupo) {
            throw new IllegalArgumentException("El grupo ya existe.");
        }

        this.grupos.add(nombreGrupo);
        this.contactosPorGrupo.put(nombreGrupo, new TreeSet<>());
        System.out.print("Grupo '" + nombreGrupo + "' creado");
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        // Determina si existe el contacto.
        boolean existeContacto =this.contactos.containsKey(nombreContacto);
        if (! existeContacto) {
            System.out.print("Error: El contacto '" + nombreContacto + "' no existe");
            return ;
        }

        // Determina si existe el grupo.
        boolean existeGrupo =this.grupos.contains(nombreGrupo);

        if (! existeGrupo) {
            System.out.print("Error: El grupo '" + nombreGrupo + "' no existe");
            return ;
        }

        this.contactosPorGrupo.get(nombreGrupo).add(nombreContacto);

        System.out.print("Contacto agregado al grupo '" + nombreGrupo + "'");
    }

    public void listarGrupos() {
        System.out.print("Lista de Grupos (Alfabetico):");
        for (String grupo : this.grupos) {
            System.out.println(grupo);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        // Determina si existe el grupo.
        boolean existeGrupo = this.grupos.contains(nombreGrupo);
        if (! existeGrupo) {
            System.out.print("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        Set<String> miembros = this.contactosPorGrupo.get(nombreGrupo);

        if (miembros.isEmpty()) {
            System.out.print("El grupo '" + nombreGrupo + "' no tiene contactos");
            return;
        }

        System.out.print("Contactos en '" + nombreGrupo + "':");

        for (String nombre : miembros) {
            System.out.print(this.contactos.get(nombre));
        }
    }
}
