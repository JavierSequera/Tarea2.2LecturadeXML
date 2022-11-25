import java.util.ArrayList;
import java.util.List;

public class Pruebas {
    public static List<Chat> mostrarChat(int id){
        int m = 0;
        String nombreChat = null;
        int idParticipante1 = 0, idParticipante2 = 0;

        List<Chat> ListaChats = new ArrayList<>();
        String consulta = "SELECT * FROM Chat WHERE idParticipante1 = "+id+" OR idParticipante2 = "+id;
        String[] datos = new String[]{"nombreChat","idParticipante1","idParticipante2"};
        List<String> ListaDatos = new ArrayList<>();
        ListaDatos.add("Departamento1");
        ListaDatos.add("1");
        ListaDatos.add("2");
        ListaDatos.add("Departamento2");
        ListaDatos.add("1");
        ListaDatos.add("4");
        ListaDatos.add("Departamento3");
        ListaDatos.add("2");
        ListaDatos.add("3");

        for (int i = 0; i < ListaDatos.size()/datos.length; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j){
                    case 0: nombreChat = ListaDatos.get(m);
                        break;
                    case 1: idParticipante1 = Integer.parseInt(ListaDatos.get(m));
                        break;
                    case 2: idParticipante2 = Integer.parseInt(ListaDatos.get(m));
                    break;
                }
                m++;
            }
            ListaChats.add(new Chat(nombreChat, idParticipante1, idParticipante2));
        }
        return ListaChats;
    }

    public static void main(String[] args) {
        List<Chat>chats = mostrarChat(1);
        for (int i = 0; i < chats.size(); i++) {
            System.out.print(chats.get(i).getNombreChat() +" ");
            System.out.print(chats.get(i).getIdParticipante1() +" ");
            System.out.print(chats.get(i).getIdParticipante2() +" ");
            System.out.println();
        }
    }
}
