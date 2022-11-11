import javax.management.MBeanAttributeInfo;
import java.sql.*;
import java.util.Scanner;
import java.util.Set;

public class conectartseBD {

    private static Connection con;

    private static String[] campos;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String conexionURL = "jdbc:mysql://dns11036.phdns11.es?"+"user=fjsequera&password=xav03rose";
            con = DriverManager.getConnection(conexionURL);
            System.out.println(con.toString());
            /*
            setPassword
            Statement statement = con.createStatement();
            System.out.println(con.toString());
            String sql2 = "USE ad2223_fjsequera";
            statement.executeUpdate(sql2);
            campos = new String[]{"ID int PRIMARY KEY AUTO_INCREMENT", "nombre varchar (255)", "apellidos varchar (255)", "edad int"};
            String dropTable = "DROP TABLE fjsequera";

            PreparedStatement pstmt = con.prepareStatement("SELECT nombre, apellidos FROM fjsequera WHERE nombre LIKE ?");
            pstmt.setString(1, "%");


            PreparedStatement pstmt2 = con.prepareStatement("SELECT nombre, apellidos FROM fjsequera WHERE nombre LIKE ? AND apellidos LIKE ? ORDER BY edad DESC");


            PreparedStatement pstmt3 = con.prepareStatement("SELECT nombre, apellidos, edad FROM fjsequera WHERE edad BETWEEN ? AND ?");
            pstmt3.setInt(1, s.nextInt());
            pstmt3.setInt(2, s.nextInt());



            leerNombreJ(pstmt3);

            //Mostrar ordenado por apellidos

             */




        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void crearTabla(Statement statement, String[] campos, String tabla) throws SQLException {

        String sql = "CREATE TABLE "+tabla+" (";
        for (int i = 0; i < campos.length; i++) {
            sql+= campos[i];
            if(i < campos.length-1){
                sql += ", ";
            }
        }

        sql = sql + "),";
        statement.execute(sql);
    }

    public static void leerEdad(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM fjsequera ORDER BY edad");
    }

    public static void leerPorApellido(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT nombre, apellidos FROM fjsequera ORDER BY apellidos");
        while(res.next()){
            System.out.println("Nombre: "+ res.getString("nombre")+ " | "+"Apellido: "+ res.getString("apellidos"));
        }
    }

    public static void leerMayor30(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT nombre, apellidos FROM fjsequera WHERE edad > 30");
        while(res.next()){
            System.out.println("Nombre: "+ res.getString("nombre")+ " | "+"Apellido: "+ res.getString("apellidos"));
        }
    }

    public static void leerNombreJ(PreparedStatement statement) throws SQLException {
        ResultSet res = statement.executeQuery();
        while(res.next()){
            System.out.println("Nombre: "+ res.getString("nombre")+ " | "+"Apellido: "+ res.getString("apellidos"));
        }
    }

    public static void leerNombreCApellidoA(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT nombre, apellidos FROM fjsequera WHERE nombre LIKE 'C%' AND apellidos LIKE 'A%' ORDER BY edad DESC");
        while(res.next()){
            System.out.println("Nombre: "+ res.getString("nombre")+ " | "+"Apellido: "+ res.getString("apellidos"));
        }
    }



     public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }



    public static void mostrarDatos(Statement statement, String consulta) throws SQLException {
        ResultSet res = statement.executeQuery(consulta);

        while(res.next()) {
            if (hasColumn(res, "ID")) {
                System.out.print("ID: " + res.getInt("ID") + " ");
            }
            if (hasColumn(res, "nombre")) {
                System.out.print("Nombre: " + res.getString("nombre") + " ");
            }
            if (hasColumn(res, "apellido")) {
                System.out.print("Apellido: " + res.getString("apellido") + " ");
            }
            if (hasColumn(res, "edad")) {
                System.out.print("Edad: " + res.getString("edad") + " ");
            }
            System.out.println();
        }
    }


    // ALTER TABLE ad2223.tabla ADD

    public static void insertarDatos(Statement statement) throws SQLException {
        String datos = "insert into ad2223.fjsequera (nombre, apellidos, edad) values ('Patrizius', 'Amis', 100),"+
        "('Maddalena', 'Ferdinand', 3),"+
        "('Celestine', 'Wadham', 34),"+
                "('Tiffani', 'Dugood', 55),"+
        "('Renaud', 'Dufer', 3),"+
         "('Dot', 'Martyntsev', 86),"+
         "('Lewes', 'Prickett', 30),"+
         "('Darbee', 'Benck', 52),"+
         "('Boote', 'Fitzroy', 37),"+
         "('Rollo', 'Salterne', 23),"+
         "('Pooh', 'Degoey', 62),"+
         "('Lisa', 'Pepi', 11),"+
         "('Elysia', 'Gosenell', 100),"+
         "('Ingunna', 'Leverson', 43),"+
         "('Nelli', 'Blyth', 64),"+
         "('Fanny', 'Lefever', 5),"+
         "('Alexia', 'Thorne', 8),"+
         "('Rodrick', 'Ivannikov', 23),"+
         "('Marven', 'Laugheran', 28),"+
         "('Jehu', 'Washington', 69),"+
         "('Eilis', 'Dyzart', 9),"+
         "('Elton', 'Woodington', 94),"+
         "('Rebekah', 'Sillick', 9),"+
         "('Jephthah', 'Rigard', 26),"+
         "('Morgana', 'Earles', 57),"+
         "('Hollis', 'Pattinson', 13),"+
         "('Emilio', 'Simioni', 13),"+
         "('Hervey', 'Maggill Andreis', 58),"+
         "('Rana', 'Anstiss', 83),"+
         "('Tillie', 'Smiths', 16),"+
         "('Ludvig', 'Top', 8),"+
         "('Lucy', 'Waitland', 33),"+
         "('Nara', 'Delucia', 26),"+
         "('Hobie', 'Yancey', 71),"+
         "('Judye', 'Mullane', 100),"+
         "('Demetri', 'Wixon', 47),"+
         "('Laverne', 'Hyland', 97),"+
         "('Kimmi', 'Gounard', 89),"+
         "('Patricia', 'Macoun', 56),"+
         "('Olva', 'MacAlroy', 31),"+
         "('Kary', 'Sanders', 41),"+
         "('Arny', 'Gahagan', 92),"+
         "('Brien', 'Yewman', 85),"+
         "('Jsandye', 'Bangle', 51),"+
         "('Sophronia', 'O Hanlon', 82),"+
         "('Roddy', 'Crother', 47),"+
         "('Mort', 'Edison', 20),"+
         "('Nanete', 'Bacon', 55),"+
         "('Miof mela', 'Pucknell', 29),"+
         "('Gilemette', 'Boik', 77),"+
         "('Zak', 'Cunniam', 59),"+
         "('Clem', 'Escalera', 13),"+
         "('Melisa', 'O Lochan', 48),"+
         "('Perice', 'Bizley', 99),"+
         "('Donovan', 'McElree', 90),"+
         "('Janek', 'Shelliday', 64),"+
         "('Gilly', 'Alder', 58),"+
         "('Edita', 'Bartomieu', 44),"+
         "('Roz', 'Tenney', 61),"+
         "('Colas', 'Hacker', 62),"+
         "('Myles', 'Cormode', 33),"+
         "('Alasteir', 'Bille', 28),"+
         "('Neely', 'Cragell', 69),"+
         "('Robinette', 'Smallwood', 74),"+
         "('Wes', 'Carless', 2),"+
         "('Marty', 'Rickeard', 78),"+
         "('Stuart', 'Armfirld', 45),"+
         "('Valery', 'Fossord', 11),"+
         "('Eli', 'Lade', 75),"+
         "('Caril', 'Nevin', 55),"+
         "('Cletus', 'Jorger', 90),"+
         "('Manny', 'Cowpe', 14),"+
         "('August', 'Dell', 37),"+
         "('Orlan', 'Aggas', 14),"+
         "('Osmund', 'Tripney', 97),"+
         "('Ware', 'Cornish', 47),"+
         "('Thom', 'Ovenell', 98),"+
         "('Jeannette', 'Howkins', 72),"+
         "('Adlai', 'Figger', 27),"+
         "('Matias', 'Tomaszewski', 16),"+
         "('Martynne', 'Runnalls', 60),"+
         "('Corabella', 'Hrinchenko', 7),"+
         "('Kiri', 'McNay', 90),"+
         "('Rafaello', 'Regina', 32),"+
         "('Juliane', 'Willcocks', 52),"+
         "('Jacinthe', 'Cockings', 25),"+
         "('Ciro', 'Fountain', 15),"+
         "('Lavinia', 'Bailey', 93),"+
         "('Myranda', 'Matieu', 12),"+
         "('Stinky', 'Antham', 9),"+
         "('Linnea', 'Hamor', 94),"+
         "('Daphne', 'Hindrick', 96),"+
         "('Legra', 'Brundall', 28),"+
         "('Alicea', 'Pollicote', 14),"+
         "('Sarette', 'Simmonds', 30),"+
         "('Gerhardt', 'Jones', 92),"+
         "('Wildon', 'Wailes', 50),"+
         "('Gary', 'Smooth', 14),"+
         "('Patten', 'Canape', 74),"+
         "('Arte', 'Pottle', 20),"+
         "('Candida', 'Applegarth', 53),"+
         "('Flori', 'Twitchett', 9),"+
         "('Barde', 'Ipsly', 85),"+
         "('Oralee', 'Kenwell', 26),"+
         "('Jordon', 'Barrand', 42),"+
         "('Kelbee', 'Ryves', 30),"+
         "('Heddi', 'Dorcey', 18),"+
         "('Sully', 'Tatlock', 34),"+
         "('Catherina', 'Adamik', 11),"+
         "('Morey', 'Dawson', 53),"+
         "('Penny', 'Lermouth', 83),"+
         "('Sarah', 'Blowing', 57),"+
         "('Micki', 'Jagson', 41),"+
         "('Corri', 'Strainge', 41),"+
         "('Chrystal', 'Haysom', 58),"+
         "('Liz', 'Gaythor', 24),"+
         "('Kane', 'Garrould', 45),"+
         "('Arnaldo', 'Bullocke', 45),"+
         "('Petra', 'Phette', 53),"+
         "('Lenna', 'Grenshields', 22),"+
         "('Waneta', 'Gourlie', 79),"+
         "('Verine', 'Sara', 38),"+
         "('Kingsly', 'Raveau', 64),"+
         "('Jimmy', 'Orchard', 44),"+
         "('Eran', 'Weedon', 92),"+
         "('Reinhold', 'Gilkes', 39),"+
         "('Trish', 'Byrd', 26),"+
         "('Celestyna', 'Delves', 100),"+
         "('Alejandro', 'Westmerland', 28),"+
         "('Alfy', 'Jest', 22),"+
         "('Merl', 'Rulten', 39),"+
         "('Lira', 'Maudling', 69),"+
         "('Riordan', 'Held', 75),"+
         "('Kipp', 'Moogan', 79),"+
         "('Noak', 'Cuffin', 18),"+
         "('Rori', 'Beardon', 1),"+
         "('Carolus', 'Mille', 56),"+
         "('Dominick', 'Hatliff', 65),"+
         "('Sonny', 'Feldhorn', 94),"+
         "('Annetta', 'Antonchik', 67),"+
         "('Carmina', 'Bowes', 70),"+
         "('Angel', 'Dikles', 83),"+
         "('Enrika', 'Deighan', 9),"+
         "('Arly', 'Garfirth', 33),"+
         "('Fitzgerald', 'Withur', 6),"+
         "('Ricard', 'MacAloren', 22),"+
         "('Rey', 'Dumbrall', 33),"+
         "('Brady', 'Bolland', 35),"+
         "('Gray', 'Sanchez', 18),"+
         "('Brade', 'Perche', 18),"+
         "('Elyse', 'Skip', 86),"+
         "('Vivianna', 'Klewi', 1),"+
         "('Maiga', 'Witton', 95),"+
         "('Thebault', 'Bicheno', 20),"+
         "('Royce', 'Chamney', 44),"+
         "('Billye', 'Cutmore', 41),"+
         "('Brandais', 'Jurries', 18),"+
         "('Audra', 'Moultrie', 29),"+
         "('Joanna', 'Pigford', 97),"+
         "('Germana', 'Gullivent', 7),"+
         "('Sada', 'Wratten', 99),"+
         "('Efren', 'Lapping', 15),"+
         "('Tremaine', 'Luscombe', 39),"+
         "('Colver', 'Hawk', 100),"+
         "('Byron', 'Davy', 25),"+
         "('Domenico', 'Giacovelli', 21),"+
         "('Rhett', 'Dawley', 46),"+
         "('Burgess', 'Rubinowitsch', 73),"+
         "('Karlan', 'Dubble', 41),"+
         "('Skipper', 'Lody', 92),"+
         "('Cameron', 'Georgeon', 39),"+
         "('Knox', 'Willcott', 13),"+
         "('Simmonds', 'Bwye', 15),"+
         "('Jozef', 'McLay', 99),"+
         "('Sher', 'Colliber', 67),"+
         "('Kimmie', 'Orgee', 25),"+
         "('Bren', 'Metzig', 92),"+
         "('Ariella', 'Bollins', 100),"+
         "('Pincus', 'Wickrath', 30),"+
         "('Alta', 'Cassells', 37),"+
         "('Fawne', 'Sharnock', 85),"+
         "('Veradis', 'Lorenzetti', 97),"+
         "('Griff', 'Boriston', 24),"+
         "('Eleni', 'Rawstorn', 67),"+
         "('Doro', 'Etienne', 77),"+
         "('Mata', 'Christofe', 22),"+
         "('Noellyn', 'Kubas', 29),"+
         "('Mort', 'Devon', 71),"+
         "('Otho', 'Cady', 24),"+
         "('Keith', 'Mockford', 88),"+
         "('Tommie', 'Nurcombe', 70),"+
         "('Rickard', 'Tanby', 62),"+
         "('Vittorio', 'Gerger', 75),"+
         "('Annabel', 'Gwyer', 32),"+
         "('Paxton', 'Beidebeke', 57),"+
         "('Georgena', 'Flacknoe', 5),"+
         "('Robbin', 'Mulgrew', 89),"+
         "('Kleon', 'Eustis', 77),"+
         "('Howie', 'Cliffe', 10),"+
         "('Rudie', 'Huckle', 64);";

        statement.execute(datos);
        statement.close();
    }


}
