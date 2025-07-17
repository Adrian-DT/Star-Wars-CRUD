package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonajeDAO {

    private PreparedStatement preparedStatement;

    public boolean addPersonaje(Personaje personaje) {
        boolean state = false;
        Connection connect = null;

        try {
            // Obtenemos la conexión con la base de datos
            connect = ConnectionPool.getInstance().getConnection();

            if (connect != null) {
                // Creamos la sentencia SQL
                String sql = "INSERT INTO personaje (nombre, faccion, descripcion) VALUES (?, ?, ?)";
                // Peparamos la sentencia
                preparedStatement = connect.prepareStatement(sql);
                // Añadimos los parámetros de la sentencia, en este caso todos son String, asi que usamos setString
                // pasando por el primer argumento el indice y por el segundo el valor
                preparedStatement.setString(1, personaje.getNombre());
                preparedStatement.setString(2, personaje.getFaccion());
                preparedStatement.setString(3, personaje.getDescripcion());

                // Comprobamos el estado de la ejecución de la sentencia SQL
                // Modificamos el del bool para conocer el resultado de la ejecución
                int resultado = preparedStatement.executeUpdate();
                state = resultado > 0 ? true : false;

                // Ejecutamos la sentencia
                preparedStatement.executeUpdate();
                // Personaje personajeJDBC = new Personaje(connect);
                // state = personajeJDBC.addPersonaje(personaje);
            } else {
                System.out.println("No se pudo conectar a la base de datos");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos en un finally para que se ejecute en cualquier caso
            try {
                // Pasamos el objeto de tipo conection a la clase ConnectionPool para cerrarla
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return state;
    }

}
