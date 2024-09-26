import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CenaDAO cenaDAO = new CenaDAO();
        ComandoService comandoService = new ComandoService();
        SaveDAO saveDAO = new SaveDAO();
        ItemService itemService = new ItemService();
        InventarioDAO inventarioDAO = new InventarioDAO();

        int cenaAtualId = 10;
        int idJogo = 1;

        while (true) {
            Cena cenaAtual = cenaDAO.getCenaById(cenaAtualId);
            if (cenaAtual == null) {
                System.out.println("Parabéns. Fim do jogo.");
                break;
            }

            System.out.println(cenaAtual.getDescricao());

            // comandos cena atual
            List<String> comandos = cenaDAO.getComandosByCenaId(cenaAtualId);

            System.out.print("Digite seu comando: ");
            String comando = scanner.nextLine().trim();

            // Comando help
            if (comando.equalsIgnoreCase("help")) {
                List<String> comandosDisponiveis = comandoService.getComandosDisponiveis();
                System.out.println("Comandos disponíveis: " + comandosDisponiveis);
                continue;
            }

            // Comando save
            if (comando.equalsIgnoreCase("save")) {
                saveDAO.saveGame(cenaAtualId);
                continue;
            }

            // Comando get
            if (comando.startsWith("get ")) {
                String itemNome = comando.substring(4).trim();
                boolean coletado = itemService.coletarItem(itemNome, idJogo);
                if (!coletado) {
                    System.out.println("Item não pode ser coletado ou não encontrado.");
                }
                continue;
            }

            // Comando check
            if (comando.startsWith("check ")) {
                String itemNome = comando.substring(6).trim(); // Obter o nome do item após "CHECK "
                String descricao = itemService.checkItem(itemNome);
                System.out.println(descricao); // Exibir a descrição do item
                continue;
            }

            // Comando inventario
            if (comando.equalsIgnoreCase("inventario")) {
                List<Item> itensInventario = itemService.listarInventario(idJogo);
                if (itensInventario.isEmpty()) {
                    System.out.println("Seu inventário está vazio.");
                } else {
                    System.out.println("Itens no seu inventário:");
                    for (Item item : itensInventario) {
                        System.out.println("- " + item.getNome() + ": " + item.getDescricao());
                    }
                }
                continue;
            }

            // Comando restart
            if (comando.equalsIgnoreCase("restart")) {
                cenaAtualId = 4;
                inventarioDAO.limparInventario(idJogo);
                System.out.println("O jogo foi reiniciado. Você está de volta à cena inicial.");
                continue;
            }

            // Ver se o comando está disponível
            if (comandos.contains(comando)) {
                cenaAtualId = cenaAtual.getIdProximaCena();
            } else {
                System.out.println("Comando inválido. Tente novamente.");
            }
        }
        scanner.close();
    }
}
