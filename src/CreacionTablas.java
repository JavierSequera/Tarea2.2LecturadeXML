import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreacionTablas {

    public static void main(String[] args) {
        Connection con = conexion("ad2223_amulero", "1234");
        Statement statement;
        try{
            statement = con.createStatement();
            statement.execute("USE ad2223_amulero");

        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    public static Connection conexion(String usuario, String contraseña){

        Connection con = null;
        String url = "jdbc:mysql://dns11036.phdns11.es?"+"user="+usuario+"&password="+contraseña;
        try{
            con = DriverManager.getConnection(url);
            if (con!=null){
                System.out.println("Conexión establecida");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return con;
    }

    public static void CrearTabla(String nombre, Statement statement, String[] campos){

        String sql = "CREATE TABLE "+nombre+" (";
        for (int i = 0; i < campos.length; i++) {
            sql+= campos[i];
            if(i < campos.length-1){
                sql += ", ";
            }
        }

        sql = sql + ");";
        try {

            statement.execute(sql);

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
