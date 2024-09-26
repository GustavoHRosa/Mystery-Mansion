import java.util.List;

public class ItemService {
    private ItemDAO itemDAO;
    private InventarioDAO inventarioDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
        this.inventarioDAO = new InventarioDAO();
    }

    public boolean coletarItem(String itemNome, int idJogo) {
        Item item = itemDAO.getItemByNome(itemNome);
        if (item != null && item.isPodeSerColetado()) {
            inventarioDAO.adicionarItemAoInventario(item.getId(), idJogo);
            System.out.println("Adicionando " + itemNome + " ao inventário.");
            return true;
        }
        return false;
    }

    public String checkItem(String itemNome) {
        Item item = itemDAO.getItemByNome(itemNome);
        if (item != null) {
            return item.getDescricao();
        }
        return "Item não encontrado.";
    }

    public List<Item> listarInventario(int idJogo) {
        return inventarioDAO.getItensDoInventario(idJogo);
    }
}
