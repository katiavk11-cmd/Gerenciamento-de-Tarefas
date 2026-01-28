import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;
import java.util.Comparator;

public class FiltrarTarefas {

    private static final DateTimeFormatter fmtBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void porTitulo(List<Tarefa> lista) {
        String busca = ConsoleInput.lerTexto("Digite parte do título: ").toLowerCase();
        // Uso de Predicate para tornar a lógica de filtro um objeto de primeira classe
        filtrarEExibir(lista, t -> t.getTitulo().toLowerCase().contains(busca),
                "Nenhuma tarefa com o título: " + busca);
    }

    public void porData(List<Tarefa> lista) {
        LocalDate dataBusca = ConsoleInput.lerData("Digite a data (dd/mm/aaaa): ");
        filtrarEExibir(lista, t -> t.getDataEntrega().equals(dataBusca),
                "Nenhuma tarefa para a data: " + dataBusca.format(fmtBR));
    }

    public void porStatus(List<Tarefa> lista) {
        System.out.println("\n--- FILTRAR POR STATUS ---");
        System.out.println("1. PENDENTE | 2. EM ANDAMENTO | 3. CONCLUÍDA");
        int opcao = ConsoleInput.lerInteiro("Opção: ");

        // O switch expression já retorna o valor diretamente para o Optional ou variável
        Status statusBusca = switch (opcao) {
            case 1 -> Status.PENDENTE;
            case 2 -> Status.EM_ANDAMENTO;
            case 3 -> Status.CONCLUIDA;
            default -> null;
        };

        if (statusBusca == null) {
            System.out.println(Cores.erro("Opção de status inválida!"));
            return;
        }

        filtrarEExibir(lista, t -> t.getStatus() == statusBusca,
                "Nenhuma tarefa com o status: " + statusBusca);
    }

    /**
     * Refatoração Funcional: Centraliza o processamento do Stream.
     */
    private void filtrarEExibir(List<Tarefa> lista, Predicate<Tarefa> filtro, String mensagemErro) {
        // Criamos o fluxo, filtramos e já ordenamos de forma funcional
        List<Tarefa> resultados = lista.stream()
                .filter(filtro)
                .sorted(Comparator.comparing(Tarefa::getDataEntrega)) // Melhor prática de ordenação
                .toList(); // Substitui o Collectors.toList() nas versões recentes do Java

        if (resultados.isEmpty()) {
            System.out.println("\n" + Cores.alerta(mensagemErro));
            return;
        }

        System.out.println(String.format("\n%s--- %d TAREFA(S) ENCONTRADA(S) ---%s",
                Cores.VERDE, resultados.size(), Cores.RESET));

        resultados.forEach(System.out::println);
    }
}