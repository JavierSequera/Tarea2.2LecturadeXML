public class Chat {
    private String nombreChat;
    private int idParticipante1;
    private int idParticipante2;




    public Chat(){
    }

    public Chat(String nombre, int idParticipante1, int idParticipante2){
        this.nombreChat = nombre;
        this.idParticipante1 = idParticipante1;
        this.idParticipante2 = idParticipante2;
    }


    public String getNombreChat() {
        return nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    public int getIdParticipante1() {
        return idParticipante1;
    }

    public void setIdParticipante1(int idParticipante1) {
        this.idParticipante1 = idParticipante1;
    }

    public int getIdParticipante2() {
        return idParticipante2;
    }

    public void setIdParticipante2(int idParticipante2) {
        this.idParticipante2 = idParticipante2;
    }

    //TODO
    public static Chat crearChat(String nombreChat, String nombreParticipante){
        return new Chat();
    }


    //TODO

}
