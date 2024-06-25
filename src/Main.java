import entities.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 3.1
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2
        System.out.println("Deletando o nome João da Lista de Funcionários.");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        employees.removeIf(employee -> employee.getName().equals("João"));
        System.out.println();

        // 3.3
        System.out.println("Listando todos os Funcionários.");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        System.out.println();

        // 3.4
        System.out.println("Aumentando o salário em 10%");
        BigDecimal percentageOfWageIncrease = new BigDecimal("1.10");
        employees.forEach(employee -> employee.setWage(employee.getWage().multiply(percentageOfWageIncrease)));
        System.out.println();

        // 3.5 and 3.6
        System.out.println("Filtando e Listando Funcionários por Função");
        Map<String, List<Employee>> employeesByRole = employees.stream().collect(Collectors.groupingBy(Employee::getRole));
        employeesByRole.forEach((role, listEmployees) -> {
            System.out.println(role + ": ");
            listEmployees.forEach(employee -> System.out.println(" - " + employee.getName()));
        });
        System.out.println();

        // 3.8
        System.out.println("Listandos Funcionários que fazem aniversário em Outubro e Dezembro");
        employees.stream()
                .filter(employee -> employee.getBirthDate().getMonthValue() == 10 || employee.getBirthDate().getMonthValue() == 12)
                .forEach(employee -> System.out.println(employee.toString()));

        // 3.9
        System.out.println("Listandos Funcionários maior de idade.");
        Optional<Employee> employeesOverEighteen = employees.stream()
                .max(Comparator.comparing(employee -> Period.between(employee.getBirthDate(), LocalDate.now()).getYears()));

        employeesOverEighteen.ifPresent(employee -> System.out.println(employee.customizedToString()));
        System.out.println();

        // 3.10
        System.out.println("Listandos Funcionários em ordem alfabética.");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee -> System.out.println("Nome: " + employee.getName()));
        System.out.println();

        // 3.11
        System.out.println("Imprimir o total dos salários dos funcionários");
        BigDecimal totalWage = employees.stream()
                .map(Employee::getWage)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total do Salários: " + NumberFormat.getNumberInstance(Locale.getDefault()).format(totalWage));
        System.out.println();

        // 3.12
        System.out.println("Imprimir quantos salários mínimos ganha cada funcionário");
        BigDecimal minimumWage = new BigDecimal("1212.00");
        employees.forEach(employee -> {
            BigDecimal wageInMinimumWage = employee.getWage().divide(minimumWage, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + employee.getName() + ", Salário Mínimos: " + wageInMinimumWage);
        });
        System.out.println();
    }
}
