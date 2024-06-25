package entities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Employee extends Person {
    private BigDecimal wage;
    private String role;

    public Employee(String name, LocalDate birthDate, BigDecimal wage, String role) {
        super(name, birthDate);
        this.wage = wage;
        this.role = role;
    }

    public Employee(BigDecimal wage, String role) {
        this.wage = wage;
        this.role = role;
    }

    public Employee() {
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String customizedToString() {
        return "Nome: " + this.getName() +
                ", Idade: " + Period.between(this.getBirthDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Nome: " + this.getName() +
                ", Data de Nascimento: " + this.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Salário: " + NumberFormat.getNumberInstance(Locale.getDefault()).format(this.getWage()) +
                ", Função: " + this.getRole();
    }
}
