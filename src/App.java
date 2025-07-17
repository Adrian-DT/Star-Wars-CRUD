import model.*;

public class App {
    public static void main(String[] args) throws Exception {


        PersonajeDAO model = new PersonajeDAO();

        if(model.addPersonaje(new Personaje("Luke Skywalker", "Rebel Alliance", "Jedi"))) {
            System.out.println("Personaje creado con exito");
        } else {
            System.out.println("No se pudo crear el personaje");
        }

    }
}
