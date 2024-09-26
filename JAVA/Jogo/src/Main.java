import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CenaDAO cenaDAO = new CenaDAO();
        ComandoService comandoService = new ComandoService();
        SaveDAO saveDAO = new SaveDAO();
        ItemService itemService = new ItemService();
        InventarioDAO inventarioDAO = new InventarioDAO(); // Inicializa o DAO de inventário

        int cenaAtualId = 4; // ID da cena inicial
        int idJogo = 1; // ID do jogo atual

        while (true) {
            Cena cenaAtual = cenaDAO.getCenaById(cenaAtualId);
            if (cenaAtual == null) {
                System.out.println("Parabéns. Fim do jogo.");
                break;
            }

            System.out.println(cenaAtual.getDescricao());

            // Obter comandos disponíveis da cena atual
            List<String> comandos = cenaDAO.getComandosByCenaId(cenaAtualId);

            System.out.print("Digite seu comando: ");
            String comando = scanner.nextLine().trim();

            // Verificar o comando HELP
            if (comando.equalsIgnoreCase("HELP")) {
                List<String> comandosDisponiveis = comandoService.getComandosDisponiveis();
                System.out.println("Comandos disponíveis: " + comandosDisponiveis);
                continue;
            }

            // Verificar o comando SAVE
            if (comando.equalsIgnoreCase("SAVE")) {
                saveDAO.saveGame(cenaAtualId);
                continue;
            }

            // Verificar o comando GET
            if (comando.startsWith("GET ")) {
                String itemNome = comando.substring(4).trim();
                boolean coletado = itemService.coletarItem(itemNome, idJogo);
                if (!coletado) {
                    System.out.println("Item não pode ser coletado ou não encontrado.");
                }
                continue;
            }

            // Verificar o comando CHECK
            if (comando.startsWith("CHECK ")) {
                String itemNome = comando.substring(6).trim(); // Obter o nome do item após "CHECK "
                String descricao = itemService.checkItem(itemNome);
                System.out.println(descricao); // Exibir a descrição do item
                continue;
            }

            // Verificar o comando INVENTORY
            if (comando.equalsIgnoreCase("INVENTORY")) {
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

            // Verificar o comando RESTART
            if (comando.equalsIgnoreCase("RESTART")) {
                cenaAtualId = 4; // Redefine a cena atual para a cena inicial
                inventarioDAO.limparInventario(idJogo); // Limpa o inventário do jogador
                System.out.println("O jogo foi reiniciado. Você está de volta à cena inicial.");
                continue;
            }

            // Verificar se o comando está disponível
            if (comandos.contains(comando)) {
                cenaAtualId = cenaAtual.getIdProximaCena();
            } else {
                System.out.println("Comando inválido. Tente novamente.");
            }
        }
        scanner.close();
    }
}
