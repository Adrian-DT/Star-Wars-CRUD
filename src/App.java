import java.util.ArrayList;

import javax.swing.JOptionPane;
import model.Personaje;
import model.PersonajeDAO;

public class App {
    public static void main(String[] args) {
        PersonajeDAO personajeDAO = new PersonajeDAO();

        int option = Integer.parseInt(JOptionPane.showInputDialog(
            "1 - Insertar Personaje\n" +
            "2 - Listar Personajes\n" +
            "3 - Actualizar Personaje\n" +
            "4 - Eliminar Personaje\n" +
            "5 - Buscar Personaje\n" +
            "6 - Salir"));

        switch (option) {
            case 1:
                if (personajeDAO.addPersonaje(new Personaje("Darth Vader", "Imperio", "Lord Sith"))) {
                    JOptionPane.showMessageDialog(null, "Personaje creado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el personaje");
                }
                break;
            case 2:
                ArrayList<String> data = new ArrayList<String>();
                data.add("Imperio");

                ArrayList<Personaje> listaPersonajes = personajeDAO.getPersonaje("faccion", data);

                for (Personaje personaje : listaPersonajes) {
                    System.out.println("\nNombre: " + personaje.getNombre() + "\nFaccion: " + personaje.getFaccion() + "\nDescripcion: " + personaje.getDescripcion());
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                break;
        }
    }
}

