package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.CLIUI;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Getter
@Component
public class MyCLI implements CLIUI{
    private Scanner scanner;

    public MyCLI(){
        this.scanner = new Scanner(System.in);
    }
    @Override
    public double requestClientAmount() throws RuntimeException{
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Number format error: " + e.getMessage());
            throw e;
        } catch (NullPointerException e) {
            System.out.println("The string cannot be null: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public String requestClientAccountNumber() { return String.valueOf(scanner.nextLine()); }
    @Override
    public AccountType requestAccountType() throws IllegalArgumentException{
        try{
            return AccountType.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e){
            System.out.println("Invalid account type value");
            throw e;
        }
    }
}
