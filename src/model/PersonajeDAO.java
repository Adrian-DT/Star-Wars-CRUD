package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonajeDAO {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
                // Ejecutamos la sentencia y almacenamos el resultado para modificar el boolean para conocer el resultado de la ejecución
                int resultado = preparedStatement.executeUpdate();
                state = resultado > 0 ? true : false;

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

    public ArrayList<Personaje> getPersonaje(String filter, ArrayList<String> data) {
        ArrayList<Personaje> listaPersonajes = new ArrayList<Personaje>();
        Personaje personaje;


        Connection connect = null;

        try {
            // Obtenemos la conexión con la base de datos
            connect = ConnectionPool.getInstance().getConnection();

            if (connect != null) {
                
                String sql = "";
                // En función del filtro recibido por argumento, generamos una sentencia SQL u otra
                switch (filter) {
                    case "id":
                        sql = "SELECT * FROM personaje WHERE id = ?";
                        break;
                    case "nombre":
                    // Usamos REGEXP ? para que la busqueda sea case insensitive y obtengamos coincidencias
                        sql = "SELECT * FROM personaje WHERE nombre REGEXP ?";
                        break;
                    case "faccion":
                        sql = "SELECT * FROM personaje WHERE faccion REGEXP ?";
                        break;
                    case "descripcion":
                        sql = "SELECT * FROM personaje WHERE descripcion REGEXP ?";
                        break;
                    default:
                        sql = "SELECT * FROM personaje";
                        break;
                }
                // Preparamos la sentencia
                preparedStatement = connect.prepareStatement(sql);

                // En función del filter indicamos el value de la sentencia SQL
                switch (filter) {
                    case "id":
                        preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
                        break;
                    case "nombre":
                        preparedStatement.setString(1, data.get(0));
                        break;
                    case "faccion":
                        preparedStatement.setString(1, data.get(0));
                        break;
                    case "descripcion":
                        preparedStatement.setString(1, data.get(0));
                        break;
                }

                // Ejecutamos la sentencia y obtenemos el resultado
                resultSet = preparedStatement.executeQuery();

                // Recorremos el resultSet y obtenemos los datos
                while (resultSet.next()) {
                    personaje = new Personaje();
                    personaje.setId(resultSet.getInt("id"));
                    personaje.setNombre(resultSet.getString("nombre"));
                    personaje.setFaccion(resultSet.getString("faccion"));
                    personaje.setDescripcion(resultSet.getString("descripcion"));
                    listaPersonajes.add(personaje);
                }


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


        return listaPersonajes;
    }
}
