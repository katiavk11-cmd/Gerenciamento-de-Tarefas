import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class RemoverTarefa {
    // O nome correto deve ser 'executar'
    public void executar(List<Tarefa> lista) {
        if (lista.isEmpty()) {
            System.out.println(Cores.alerta("A lista está vazia. Nada para deletar."));
            return;
        }

        System.out.println("\n" + Cores.VERMELHO + "--- REMOVER TAREFA ---" + Cores.RESET);

        // Exibição funcional dos índices e títulos
        IntStream.range(0, lista.size())
                .forEach(i -> System.out.println(i + " - " + lista.get(i).getTitulo()));

        int indice = ConsoleInput.lerInteiro("\nDigite o número da tarefa que deseja apagar: ");

        // Pipeline de validação e confirmação
        validarIndice(lista, indice).ifPresentOrElse(
                i -> solicitarConfirmacaoERemover(lista, i),
                () -> System.out.println(Cores.erro("Índice inválido. Operação cancelada."))
        );
    }

    private void solicitarConfirmacaoERemover(List<Tarefa> lista, int indice) {
        Tarefa tarefa = lista.get(indice);

        System.out.println(Cores.alerta("\n⚠️  TEM CERTEZA?"));
        System.out.println("Você está prestes a excluir: " + Cores.NEGRITO + tarefa.getTitulo() + Cores.RESET);
        String resposta = ConsoleInput.lerTexto("Digite 'S' para confirmar ou qualquer tecla para cancelar: ");

        if (resposta.equalsIgnoreCase("S")) {
            lista.remove(indice);
            System.out.println(Cores.sucesso("Tarefa removida com sucesso!"));
        } else {
            System.out.println(Cores.AZUL + "Remoção cancelada pelo usuário." + Cores.RESET);
        }
    }

    private Optional<Integer> validarIndice(List<Tarefa> lista, int indice) {
        return (indice >= 0 && indice < lista.size())
                ? Optional.of(indice)
                : Optional.empty();
    }
}