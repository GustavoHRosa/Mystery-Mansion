import java.util.Arrays;
import java.util.List;

public class ComandoService {
    public List<String> getComandosDisponiveis() {
        return Arrays.asList("HELP", "USE [ITEM]", "CHECK [ITEM]", "GET [ITEM]",
                "INVENTORY", "USE [INVENTORY_ITEM] WITH [SCENE_ITEM]",
                "SAVE", "LOAD", "RESTART");
    }
}

