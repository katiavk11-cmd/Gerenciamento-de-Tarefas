import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class ListarTarefas {

    public void exibir(List<Tarefa> lista) {
        System.out.println("\n" + Cores.AZUL + "==========================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + "   LISTA DE TAREFAS (ORDENADAS POR PRAZO)   " + Cores.RESET);
        System.out.println(Cores.AZUL + "==========================================" + Cores.RESET);

        // --- MELHORIA: OPTIONAL PARA TRATAR LISTA VAZIA ---
        // Em vez de um 'if' manual, usamos Optional para um fluxo mais funcional
        Optional.ofNullable(lista)
                .filter(l -> !l.isEmpty())
                .ifPresentOrElse(
                        this::processarExibicao,
                        () -> System.out.println(Cores.alerta("Sua lista está vazia no momento."))
                );
    }

    /**
     * Método auxiliar para manter a pipeline de Stream limpa
     */
    private void processarExibicao(List<Tarefa> lista) {
        // --- MELHORIA: STREAM PIPELINE ---
        lista.stream()
                // Ordenação robusta usando Method Reference
                .sorted(Comparator.comparing(Tarefa::getDataEntrega))
                // Exibição formatada
                .forEach(t -> System.out.println(" • " + t));

        System.out.println("\n" + Cores.AZUL + "Total de tarefas: " + lista.size() + Cores.RESET);
    }
}