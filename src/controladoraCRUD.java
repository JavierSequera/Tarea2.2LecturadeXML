import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class controladoraCRUD {
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        Connection con = null;
        Statement statement;
        String UsarBD = "USE ad2223_fjsequera";
        String decision = "si";
        try{

            do {

                int opcion = menu();

                switch (opcion) {
                    case 1:
                        System.out.println("Introduce el usuario");
                        String usuario = s.next();
                        System.out.println("Introduce la contraseña");
                        String contraseña = s.next();
                        con = CRUD.conexion(usuario, contraseña);
                        break;
                    case 2:
                        statement = con.createStatement();
                        statement.execute(UsarBD);
                        System.out.println("Escoja la tabla que desea crear:\n 1.Alumnos\n 2.Profesores\n 3.Matrícula");
                        int opcionTabla = s.nextInt();
                        switch (opcion) {
                            case 1:
                                CRUD.CrearTabla("Alumnos", statement, new String[]{"idAlumnado int PRIMARY KEY AUTO_INCREMENT",
                                        "Nombre varchar(45)", "Apellidos varchar(45)", "FechaNacimiento date", "Antiguedad int"});
                                break;
                            case 2:
                                CRUD.CrearTabla("Profesores", statement, new String[]{"idProfesorado int PRIMARY KEY AUTO_INCREMENT",
                                        "Nombre varchar(45)", "Apellidos varchar(45)", "FechaNacimiento date", "Antiguedad int"});
                                break;
                            case 3:
                                CRUD.CrearTabla("Matrícula", statement, new String[]{"id int PRIMARY KEY AUTO_INCREMENT", "idProfesorado int",
                                        "idAlumnado int", "Asignatura varchar (45)", "Curso int", "FOREIGN KEY (idProfesorado) REFERENCES Profesores (idProfesorado) ON DELETE CASCADE ON UPDATE CASCADE",
                                        "FOREIGN KEY (idAlumnado) REFERENCES Alumnos (idAlumnado) ON DELETE CASCADE ON UPDATE CASCADE"});
                                break;
                        }
                        break;
                    case 3:
                        statement = con.createStatement();
                        statement.execute(UsarBD);
                        System.out.println("Introduce el nombre del fichero para insertar datos en la BBDD");
                        String fichero = s.next();
                        CRUD.insertarDatos(fichero, statement);
                        break;

                    case 4:
                        statement = con.createStatement();
                        statement.execute(UsarBD);
                        System.out.println("Introduzca la consulta");
                        String sentencia = s.next();
                        CRUD.listar(statement, sentencia);
                        break;

                    case 5:
                        statement = con.createStatement();
                        statement.execute(UsarBD);
                        System.out.println("Introduzca el nombre de la tabla a modificar");
                        String nombre = s.next();
                        System.out.println("Introduzca el campo a modificar");
                        String campo = s.next();
                        System.out.println("Introduzca la sentencia Where ");
                        String where = s.next();
                        CRUD.modificar(statement, new String[]{campo, where}, nombre);
                        break;
                    case 6:
                        statement = con.createStatement();
                        statement.execute(UsarBD);
                        System.out.println("Introduce el nombre de la tabla");
                        String tabla = s.next();
                        System.out.println("Introduzca la condición de borrado");
                        String condicion = s.next();
                        CRUD.borrar(statement, tabla, condicion);
                        break;
                    default:
                        System.out.println("La opción es incorrecta");
                        break;
                }

                System.out.println("¿Desea continuar manipulando la BBDD?");
                decision = s.next();

            }while (decision.toLowerCase().equals("si"));

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private static int menu(){
        Scanner s = new Scanner(System.in);
        System.out.println("Escoja la opción:\n"+
                "1. Conectarse a la base de datos.\n"+
                "2. Crear tablas.\n"+
                "3. Insertar datos en la tabla.\n"+
                "4. Listar\n"+
                "5. Modificar tabla\n"+
                "6. Borrar algo de la tabla");
        int opcion = s.nextInt();

        return opcion;
    }
}
