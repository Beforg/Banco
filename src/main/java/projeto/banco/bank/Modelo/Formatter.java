package projeto.banco.bank.Modelo;

import java.util.Random;

public class Formatter {

    public static String setNumCard() {
        StringBuilder numCard = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            numCard.append(random.nextInt(10));
        }
        return formatarNumCard(numCard.toString());
    }
    public static String formatarNumCard(String numCard) {
        if (numCard.length() != 16) {
            throw new IllegalArgumentException("O CPF deve ter 11 dígitos.");
        }
        
        return numCard.substring(0, 4) + " " +
                numCard.substring(4, 8) + " " +
                numCard.substring(8, 12) + " " +
                numCard.substring(12);
    }
}
