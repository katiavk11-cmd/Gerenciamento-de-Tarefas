import java.util.List;
import java.time.LocalDate;

public class CadastrarTarefa {
    public void executar(List<Tarefa> lista) {

        // 1. Validação do Título (Mínimo 5 letras)
        String titulo = "";
        while (titulo.trim().length() < 5) {
            titulo = ConsoleInput.lerTexto("Título da tarefa: ");

            if (titulo.trim().length() < 5) {
                System.out.println("(!) Erro: Título inválido.");
                System.out.println("Motivo: O nome deve ter pelo menos 5 caracteres reais.");
            }
        }

        // 2. Validação da Descrição (Não pode ficar vazia)
        String descricao = "";
        while (descricao.trim().isEmpty()) {
            descricao = ConsoleInput.lerTexto("Descrição da tarefa: ");

            if (descricao.trim().isEmpty()) {
                System.out.println("(!) Erro: A descrição não pode ficar em branco.");
                System.out.println("Motivo: É necessário detalhar o que deve ser feito.");
            }
        }

        // 3. Coleta da Data (Já validada no ConsoleInput)
        LocalDate data = ConsoleInput.lerData("Data de entrega (dd/mm/aaaa): ");

        // Criação e armazenamento
        Tarefa nova = new Tarefa(titulo, descricao, data);
        lista.add(nova);

        System.out.println("\n[OK] Tarefa '" + titulo + "' cadastrada com sucesso!");
    }
}