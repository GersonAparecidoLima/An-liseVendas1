import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o caminho do arquivo ao usuário
        System.out.print("Entre o caminho do arquivo: ");
        String filePath = scanner.nextLine();

        // Lista de vendas
        List<Sale> sales = new ArrayList<>();

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignora a primeira linha (cabeçalho)
                if (line.startsWith("month")) continue;

                // Divide a linha do CSV em partes
                String[] values = line.split(",");
                if (values.length < 5) {
                    System.out.println("Linha inválida: " + line);
                    continue;
                }

                try {
                    Integer month = Integer.valueOf(values[0].trim());
                    Integer year = Integer.valueOf(values[1].trim());
                    String seller = values[2].trim();
                    Integer quantity = Integer.valueOf(values[3].trim());
                    Double total = Double.valueOf(values[4].trim());

                    // Cria um objeto Sale e adiciona à lista
                    sales.add(new Sale(month, year, seller, quantity, total));
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao processar linha: " + line);
                    continue;
                }
            }
            
            // Análise 1: Cinco primeiras vendas de 2016 de maior preço médio
            System.out.println("\nCinco primeiras vendas de 2016 de maior preço médio:");
            sales.stream()
                    .filter(sale -> sale.getYear() == 2016) // Filtra apenas as vendas de 2016
                    .sorted(Comparator.comparingDouble(Sale::averagePrice).reversed()) // Ordena por preço médio decrescente
                    .limit(5) // Limita para as 5 primeiras
                    .forEach(System.out::println); // Exibe os resultados
            
            // Análise 2: Valor total vendido pelo vendedor Logan nos meses 1 e 7 de qualquer ano
            Double totalLogan = sales.stream()
                    .filter(sale -> sale.getSeller().equals("Logan") && (sale.getMonth() == 1 || sale.getMonth() == 7))
                    .mapToDouble(Sale::getTotal)
                    .sum();

            System.out.printf("\nValor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f\n", totalLogan);
            
        } catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo '" + filePath + "' não foi encontrado.");
            System.out.println("Verifique o caminho e tente novamente.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + filePath);
            e.printStackTrace();
        }
    }
}
