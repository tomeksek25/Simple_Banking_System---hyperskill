package banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountRepository accountRepository = new AccountRepository("jdbc:sqlite:" + args[1]);


        label:
        while(true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            int number = scanner.nextInt();
            if (number == 1){
                Account account = BankingService.createBankingAccount();
                System.out.println("Your card has been created");
                System.out.printf("Your card number:%n%s%n", account.getAccountNumber());
                System.out.printf("Your card PIN:%n%s%n", account.getPIN());
                account.setId(accountRepository.generateId());
                accountRepository.save(account);
            } else if (number == 2){
                System.out.println("Enter your card number:");
                scanner.nextLine();
                String num = scanner.nextLine();
                System.out.println("Enter your PIN:");
                String PIN = scanner.nextLine();
                Optional<Account> account = accountRepository.verify(num, PIN);

                if (account.isPresent()) {
                    System.out.println("You have successfully logged in!");
                    while(true) {
                        System.out.println("1. Balance");
                        System.out.println("2. Add income");
                        System.out.println("3. Do transfer");
                        System.out.println("4. Close account");
                        System.out.println("5. Log out");
                        System.out.println("0. Exit");
                        number = scanner.nextInt();
                        if (number == 1) {
                            System.out.println("Balance: " + account.get().getBalance());
                        } else if (number == 2) {
                            System.out.println("Enter income:");
                            int income = scanner.nextInt();
                            account.get().setBalance(account.get().getBalance() + income);
                            accountRepository.changeBalance(account.get().getAccountNumber(), income);
                            System.out.println("Income was added!");

                        } else if (number == 3) {
                            System.out.println("Transfer");
                            System.out.println("Enter card number:");
                            num  = scanner.nextLine();
                            if (account.get().getAccountNumber().equals(num)) {
                                System.out.println("You can't transfer money to the same account!");
                            } else if (BankingService.checkCardNumber(num)) {
                                if (accountRepository.changeBalance(num, 0)) {
                                    System.out.println("Enter how much money you want to transfer:");
                                    int money = scanner.nextInt();
                                    if (account.get().getBalance() >= money) {
                                        accountRepository.changeBalance(num, money);
                                        accountRepository.changeBalance(account.get().getAccountNumber(), -money);
                                        account.get().setBalance(account.get().getBalance() + money);
                                        System.out.println("Success!");
                                    } else {
                                        System.out.println("Not enough money!");
                                    }
                                } else {
                                    System.out.println("Such a card does not exist.");
                                }
                            } else {
                                System.out.println("Probably you made mistake in the card number. Please try again!");
                            }


                        } else if (number == 4) {
                            accountRepository.delete(account.get());
                            System.out.println("The account has been closed!");
                            break;

                        } else if (number == 5){
                            break;
                        } else if (number == 0) {
                            System.out.println("Bye!");
                            break label;
                        }
                    }
                } else {
                    System.out.println("Wrong card number or PIN");
                }

            } else if (number == 0){
                System.out.println("Bye!");
                break;
            }
        }
    }

}
