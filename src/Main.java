import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
        scanner.close();
    }

    private static String calc(String input) throws Exception {

        HashMap<String, Integer> romanAlphabet = new HashMap<>();
        romanAlphabet.put("I", 1);
        romanAlphabet.put("II", 2);
        romanAlphabet.put("III", 3);
        romanAlphabet.put("IV", 4);
        romanAlphabet.put("V", 5);
        romanAlphabet.put("VI", 6);
        romanAlphabet.put("VII", 7);
        romanAlphabet.put("VIII", 8);
        romanAlphabet.put("IX", 9);
        romanAlphabet.put("X", 10);

        String[] parsedInput = input.split(" ");

        if (romanAlphabet.containsKey(parsedInput[0]) && romanAlphabet.containsKey(parsedInput[2])) {
            int result = calculate(romanAlphabet.get(parsedInput[0]), romanAlphabet.get(parsedInput[2]), parsedInput[1]);
            if (result < 1) {
                throw new Exception("Result in roman numerals can only be positive!");
            } else {
                return convertToRoman(result);
            }
        }
        else {
            int a, b;
            try {
                a = Integer.parseInt(parsedInput[0]);
                b = Integer.parseInt(parsedInput[2]);
            } catch (Exception e) {
                throw new Exception("Incorrect input!");
            }
            return calculate(a, b, parsedInput[1]) + "";
        }
    }

    private static String convertToRoman(int num) {
        String[] keys = new String[] { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] values = new int[] { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        StringBuilder res = new StringBuilder();
        int index = 0;
        while(index < keys.length)
        {
            while(num >= values[index])
            {
                int d = num / values[index];
                num = num % values[index];
                for (int i=0; i<d; i++) {
                    res.append(keys[index]);
                }
            }
            index++;
        }
        return res.toString();
    }

    private static int calculate(int a, int b, String operator) throws Exception {

        if (!((a + b >= 2) && (a + b) <= 20)) {
            throw new Exception("You must enter numbers in the range from 1 to 10 (inclusive)!");
        }

        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("Operator is incorrect!");
        };
    }
}