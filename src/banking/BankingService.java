package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankingService {
    static Random random = new Random();

    public static Account createBankingAccount() {
        return new Account(createClientNumber(), createPINNumber());
    }

    private static String createClientNumber() {
        StringBuilder sb = new StringBuilder();
        List<Integer> lista = new ArrayList<>();
        lista.add(4);
        for(int i = 0; i < 5 ; i++) {
            lista.add(0);
        }
        for(int i = 0; i < 9 ; i++) {
            lista.add(random.nextInt(10));
        }

        lista.forEach(sb::append);
        sb.append(findLuhnNumber(sb.toString()));

        return sb.toString();
    }

    private static String createPINNumber() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4 ; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static boolean checkCardNumber(String number) {
        return number.charAt(number.length() - 1) == findLuhnNumber(number.substring(0, number.length() - 1));

    }
    private static int findLuhnNumber(String number) {
        int sum = 0;

        for(int i = 0; i < number.length(); i++) {
            int n = Integer.parseInt(String.valueOf(number.charAt(i)));
            if (i % 2 == 0) {
                sum += n >= 5 ? n * 2 - 9 : n * 2;
            } else {
                sum += n;
            }

        }
        return sum % 10 == 0 ? 0 : 10 - (sum % 10);
    }
}
