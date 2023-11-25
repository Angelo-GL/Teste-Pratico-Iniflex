package org.teste;

import org.teste.model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//3 – Deve conter uma classe Principal para executar as seguintes ações:
public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela.
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 12, 5), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        //3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        /*3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
            * informação de data deve ser exibido no formato dd/mm/aaaa;
            * informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
         */
        funcionarios.forEach(System.out::println);

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10"))));

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosAgrPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6 – Imprimir os funcionários, agrupados por função.
        funcionariosAgrPorFuncao.forEach((funcao, list) -> {
            System.out.println("Função:"+ funcao);
            list.forEach(System.out::println);
        });

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        int[] mesAniversario = {10,12};
        List<Funcionario> aniversariantesOutDez = funcionarios.stream().filter(funcionario -> Arrays.stream(mesAniversario)
                .anyMatch(mes -> funcionario.getDataNascimento().getMonthValue() == mes)).collect(Collectors.toList());
        System.out.println("Aniversariantes dos meses 10 e 12: "+ aniversariantesOutDez);

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maisVelho  = Collections.max(funcionarios, Comparator.comparing(funcionario ->
                funcionario.getDataNascimento().until(LocalDate.now()).getYears()));
        System.out.println("Funcionario mais velhor: "+ maisVelho.getNome() + ", Idade: "+ maisVelho.getDataNascimento().until(LocalDate.now()).getYears());

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        List<Funcionario> funcionariosOrdenados = funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).collect(Collectors.toList());
        System.out.println("Funcionário em ordem Alfabetica: "+funcionariosOrdenados);

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarial = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        decimalFormat.setDecimalFormatSymbols(symbols);
        System.out.println("Total salarial R$:"+decimalFormat.format(totalSalarial));

        System.out.println("<------------------------------------------------------------------------------------------------------->");

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        Map<Funcionario, BigDecimal> salariosMinimos = new HashMap<>();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> salariosMinimos.put(funcionario, funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN)));
        System.out.println("Quantidade de salários mínimos ganhos: "+ salariosMinimos);
    }
}