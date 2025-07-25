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
                if (personajeDAO.addPersonaje(new Personaje("Obi Wan Kenobi", "Rebelde", "Jedi", 1))) {
                    JOptionPane.showMessageDialog(null, "Personaje creado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el personaje");
                }
                break;
            case 2:
                ArrayList<String> data = new ArrayList<String>();
                data.add("" + 1);

                ArrayList<Personaje> listaPersonajes = personajeDAO.getPersonaje("state", data);

                for (Personaje personaje : listaPersonajes) {
                    System.out.println("\nNombre: " + personaje.getNombre() + "\nFaccion: " + personaje.getFaccion() + "\nDescripcion: " + personaje.getDescripcion());
                }
                break;
            case 3:
                if (personajeDAO.updatePersonaje(new Personaje(7, "Leia Organa", "Rebelde", "Reina de la Galaxia"))) {
                    JOptionPane.showMessageDialog(null, "Personaje actualizado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el personaje");
                }
                break;
            case 4:
                int confirm = JOptionPane.showConfirmDialog(null, "Desea eliminar el personaje?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (personajeDAO.deletePersonaje(11)) {
                        JOptionPane.showMessageDialog(null, "Personaje eliminado con éxito");

                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el personaje");
                    }
                }

                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                break;
        }
    }
}

