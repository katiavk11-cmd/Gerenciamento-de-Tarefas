import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArquivoTarefas {
    private static final String NOME_ARQUIVO = "tarefas.txt";

    public void salvar(List<Tarefa> lista) {
        // --- MELHORIA: STREAMS PARA ESCRITA ---
        // Transformamos a lista em um fluxo de strings e salvamos de uma vez
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            lista.stream()
                    .map(t -> String.join(";",
                            t.getTitulo(),
                            t.getDescricao(),
                            t.getDataEntrega().toString(),
                            t.getStatus().name()))
                    .forEach(writer::println);

        } catch (IOException e) {
            System.out.println(Cores.erro("Erro ao salvar: " + e.getMessage()));
        }
    }

    public List<Tarefa> carregar() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        // --- MELHORIA: PROGRAMAÇÃO FUNCIONAL (lines()) ---
        // Usamos o BufferedReader.lines() para processar o arquivo como um Stream
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            return reader.lines()
                    .map(linha -> linha.split(";"))
                    .filter(partes -> partes.length == 4)
                    .map(this::converterParaTarefa)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            System.out.println(Cores.erro("Erro ao carregar: " + e.getMessage()));
            return new ArrayList<>();
        }
    }

    // Método auxiliar para isolar a lógica de conversão (Single Responsibility)
    private Tarefa converterParaTarefa(String[] partes) {
        try {
            Tarefa t = new Tarefa(partes[0], partes[1], LocalDate.parse(partes[2]));
            t.setStatus(Status.valueOf(partes[3]));
            return t;
        } catch (Exception e) {
            // Caso uma linha esteja corrompida, retornamos uma tarefa vazia ou nula
            // que o filter poderia remover se necessário
            return null;
        }
    }
}