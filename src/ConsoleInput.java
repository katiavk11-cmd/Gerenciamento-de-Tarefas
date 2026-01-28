import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ConsoleInput {
    // Definimos o Scanner como final para evitar reatribuição
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        // Usamos Optional para garantir que nunca retornamos um valor nulo inesperado
        return Optional.ofNullable(scanner.nextLine()).orElse("").trim();
    }

    public static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println(Cores.erro("Erro: Digite um número inteiro válido."));
            }
        }
    }

    public static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                // Parsing seguro com java.time
                return LocalDate.parse(entrada, fmt);
            } catch (DateTimeParseException e) {
                System.out.println(Cores.erro("Formato inválido! Use o padrão dd/mm/aaaa (Ex: 25/12/2026)"));
            }
        }
    }
}