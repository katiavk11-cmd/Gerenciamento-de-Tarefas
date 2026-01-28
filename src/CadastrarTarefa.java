import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public class CadastrarTarefa {
    public void executar(List<Tarefa> lista) {
        System.out.println("\n--- " + Cores.AZUL + "NOVO CADASTRO" + Cores.RESET + " ---");

        String titulo = ConsoleInput.lerTexto("Título: ");
        String descricao = ConsoleInput.lerTexto("Descrição: ");

        // Uso do Java Time API para garantir prazos consistentes
        LocalDate prazoFinal = obterDataValida();

        Tarefa nova = new Tarefa(titulo, descricao, prazoFinal);

        // Adição segura à lista
        Optional.ofNullable(nova).ifPresent(lista::add);

        System.out.println(Cores.sucesso(">>> Tarefa cadastrada com sucesso!"));
    }

    /**
     * Centraliza a lógica de validação de data usando as melhores práticas do java.time
     */
    private LocalDate obterDataValida() {
        while (true) {
            LocalDate dataDigitada = ConsoleInput.lerData("Prazo de entrega (dd/mm/aaaa): ");
            LocalDate hoje = LocalDate.now();

            // Validação semântica clara com isBefore
            if (dataDigitada.isBefore(hoje)) {
                System.out.println(Cores.erro("Erro: A data limite (" + dataDigitada + ") não pode ser anterior a hoje (" + hoje + ")!"));
                continue;
            }
            return dataDigitada;
        }
    }
}