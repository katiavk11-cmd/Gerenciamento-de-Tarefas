import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArquivoTarefas {
    private static final String NOME_ARQUIVO = "tarefas.txt";

    public void salvar(List<Tarefa> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefa t : lista) {
                // Aqui usamos o getDescricao() que agora existe na classe Tarefa
                writer.println(t.getTitulo() + ";" + t.getDescricao() + ";" +
                        t.getDataEntrega() + ";" + t.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Tarefa> carregar() {
        List<Tarefa> lista = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    Tarefa t = new Tarefa(partes[0], partes[1], LocalDate.parse(partes[2]));
                    t.setStatus(Status.valueOf(partes[3]));
                    lista.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
        return lista;
    }
}