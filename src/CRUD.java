import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class CRUD {
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

    public static void insertarDatos(String rutafichero, Statement statement){
        File fichero = new File(rutafichero);
        FileReader fr;
        BufferedReader br;
        String linea;

        try{

            fr = new FileReader(fichero);
            br = new BufferedReader(fr);

            while((linea = br.readLine()) != null){
                statement.execute(linea);
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static boolean nombraColumna(ResultSet rs, String columnName) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            for (int x = 1; x <= columns; x++) {
                if (columnName.equals(rsmd.getColumnName(x))) {
                    return true;
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return false;
    }



    public static void listar(Statement statement, String consulta) {
        try {
            ResultSet rs = statement.executeQuery(consulta);

            while (rs.next()) {
                if (nombraColumna(rs, "idAlumnado")) {
                    System.out.print("idAlumnado: " + rs.getString("idAlumnado") + " ");
                }
                if (nombraColumna(rs, "Nombre")) {
                    System.out.print("Nombre: " + rs.getString("Nombre") + " ");
                }
                if (nombraColumna(rs, "Apellido")) {
                    System.out.print("Apellido: " + rs.getString("Apellido") + " ");
                }
                if (nombraColumna(rs, "FechaNacimiento")) {
                    System.out.print("FechaNacimiento: " + rs.getString("FechaNacimiento") + " ");
                }
                if (nombraColumna(rs, "id")) {
                    System.out.print("id: " + rs.getString("id") + " ");
                }
                if (nombraColumna(rs, "idProfesorado")) {
                    System.out.print("idProfesorado: " + rs.getString("idProfesorado") + " ");
                }
                if (nombraColumna(rs, "Asignatura")) {
                    System.out.print("Asignatura: " + rs.getString("Asignatura") + " ");
                }
                if (nombraColumna(rs, "Curso")) {
                    System.out.print("Curso: " + rs.getString("Curso") + " ");
                }
                System.out.println();
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void modificar(Statement statement, String[] campos, String nombre){

        String sql = "UPDATE "+nombre+" SET ";
        for (int i = 0; i < campos.length; i++) {
            sql+= campos[i];
            if(i < campos.length-2){
                sql += ",";
            }
            if(i < campos.length-1){
                sql += " ";
            }
        }
        sql = sql+";";
        System.out.println(sql);

        try {
            statement.execute(sql);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void borrar(Statement statement, String tabla, String condicion){
        String sql = "DELETE FROM " +tabla +" WHERE " +condicion +";";
        try{
            statement.execute(sql);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void transaccion(Connection con) throws SQLException {
        con.setAutoCommit(false);	// Se desactiva el AutoCommit para poder realizar la transacción
        Statement st = con.createStatement();
        st.execute("USE ad2223_fjsequera");
        String sql;
        sql="drop table if exists cliente;";
        st.execute(sql);	// Se elimina la tabla si existiera
        System.out.println("Elimina la tabla");
        sql="CREATE TABLE cliente(id int primary key, nombre varchar(45));";
        st.execute(sql);	// Se crea la tabla
        System.out.println("Crea la tabla");
        sql="insert into cliente VALUES (1,'UNO');";
        st.executeUpdate(sql);	// Inserta el registro 1
        System.out.println("Inserta registro 1");
        try{
            con.commit();		// Comienza la transacción
            sql="insert into cliente VALUES (2,'DOS');";
            st.executeUpdate(sql);	// Inserta el registro 2
            System.out.println("Inserta registro 2");
            sql="insert into cliente VALUES (3,'TRES');";
            st.executeUpdate(sql);	// Inserta el registro 3
            System.out.println("Inserta registro 3");
            sql="insert into cliente VALUES (3,'CUATRO');";
            st.executeUpdate(sql);	// Intenta insertar el registro 3 en vez de 4
            System.out.println("No inserta registro al exister el ID 3");
        }catch(SQLException e) {
            con.rollback();
            System.out.println("rollback");
            // Deshace las dos últimas inserciones (2 y 3) ya que la última lanzó el error
        }
        con.setAutoCommit(true);	// Se vuelve a activar el AutoCommit
    }


}

