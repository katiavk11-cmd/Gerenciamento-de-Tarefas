import java.util.List;
import java.time.LocalDate;

public class FiltrarTarefas {

    public void porTitulo(List<Tarefa> lista) {
        String termo = ConsoleInput.lerTexto("Digite o título para busca: ").toLowerCase();
        boolean encontrou = false;

        System.out.println("\n--- RESULTADO DA BUSCA ---");
        for (Tarefa t : lista) {
            // Comparamos o título da tarefa (em minúsculo) com o termo buscado
            if (t.getTitulo().toLowerCase().contains(termo)) {
                System.out.println(t);
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhuma tarefa encontrada com esse título.");
    }

    public void porData(List<Tarefa> lista) {
        LocalDate dataBusca = ConsoleInput.lerData("Digite a data (dd/mm/aaaa): ");
        boolean encontrou = false;

        System.out.println("\n--- TAREFAS PARA " + dataBusca + " ---");
        for (Tarefa t : lista) {
            if (t.getDataEntrega().equals(dataBusca)) {
                System.out.println(t);
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhuma tarefa agendada para este dia.");
    }
}