public class Cena {
    private int id;
    private String descricao;
    private int idProximaCena;
    private String comandoCorreto;

    public Cena(int id, String descricao, int idProximaCena, String comandoCorreto) {
        this.id = id;
        this.descricao = descricao;
        this.idProximaCena = idProximaCena;
        this.comandoCorreto = comandoCorreto;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdProximaCena() {
        return idProximaCena;
    }

    public String getComandoCorreto() {
        return comandoCorreto;
    }
}
