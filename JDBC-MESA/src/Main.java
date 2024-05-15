import org.apache.log4j.Logger;

import java.sql.*;

public class Main {
    public static Logger LOGGER = Logger.getLogger(Main.class);
    public static String SQL_CREATE = "DROP TABLE IF EXISTS FIGURAS;" +
            "CREATE TABLE FIGURAS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(50) NOT NULL," +
            "COLOR VARCHAR(50) NOT NULL);";
    public static String SQL_INSERT = "INSERT INTO FIGURAS VALUES(DEFAULT,'CIRCULO','ROJO')," +
            "(DEFAULT,'CIRCULO','ROJO')," +
            "(DEFAULT,'CUADRADOS','AZUL')," +
            "(DEFAULT,'CUADRADOS','VERDE')," +
            "(DEFAULT,'CUADRADOS','ROJO');";

    public static String SQL_SELECT = "SELECT * FROM FIGURAS;";
    public static String SQL_SELECT_CIRCULO_ROJO = "SELECT * FROM FIGURAS WHERE NOMBRE='CIRCULO' AND COLOR='ROJO';";
    public static String SQL_DELETE = "DELETE FROM FIGURAS WHERE ID=5";
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            //CREAMOS TABLA ANIMALES
            statement.execute(SQL_CREATE);
            //INSERTAR DATOS EN LA TABLA
            statement.execute(SQL_INSERT);
            //CONSULTAR CIRCULO
            ResultSet resultSetSelectCirculosRojos = statement.executeQuery(SQL_SELECT_CIRCULO_ROJO);
            while (resultSetSelectCirculosRojos.next()){
                LOGGER.info("FIGURA: "+resultSetSelectCirculosRojos.getInt(1) + " - " +
                        resultSetSelectCirculosRojos.getString(2)+ " - "+
                        resultSetSelectCirculosRojos.getString(3));
            }
            LOGGER.info("=====================================");
            //CONSULTAR TODOS LOS DATOS
            ResultSet resultSetSelectTodo = statement.executeQuery(SQL_SELECT);
            while (resultSetSelectTodo.next()){
                LOGGER.info("FIGURA: "+resultSetSelectTodo.getInt(1) + " - " +
                        resultSetSelectTodo.getString(2)+ " - "+
                        resultSetSelectTodo.getString(3));
            }
            //ELIMINAR
            statement.execute(SQL_DELETE);
            LOGGER.info("=====================================");
            //CONSULTAR TODOS LOS DATOS
            ResultSet resultSetSelectSinElUltimo = statement.executeQuery(SQL_SELECT);
            while (resultSetSelectSinElUltimo.next()){
                LOGGER.info("FIGURA: "+resultSetSelectSinElUltimo.getInt(1) + " - " +
                        resultSetSelectSinElUltimo.getString(2)+ " - "+
                        resultSetSelectSinElUltimo.getString(3));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

    }
    public  static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase8","sa","sa");
    }
}