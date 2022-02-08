import java.util.Scanner;

public class NumSysTransfer {
    private static int startingNumSys;
    private static int numSysToTransfer;
    private static String num;
    private static int value = 0;
    private static int power = 1;
    private static String result = "";

    private static String convFromBaseToDeci(String num, int base) {
        if (base < 2 || base > 36)
            return null;
        String integerOfNum = "";
        int digit;

        if (num.contains("."))
            integerOfNum = num.substring(0, num.indexOf("."));
        else
            integerOfNum = num;
        for (int i = integerOfNum.length() - 1; i >= 0; i--) {
            digit = digitToVal(integerOfNum.charAt(i));
            if (digit == 45) {
                digit = 0;
                result = "-" + value;
            } else if (digit == 46) {
                digit = 0;
                result = "" + value;

            } else {
                value += digit * power;
                power *= base;
                result = value + "";
            }
        }

        if (num.contains(".")) {
            result += ".0";
            num = num.substring(integerOfNum.length() + 1);
            int denominator = (int) Math.pow(10, num.length());

            for (int i = 0; i < num.length() && i < 3; i++) {
                double numeratorInDouble = (double) (digitToVal(num.charAt(i))) * Math.pow(base, -(i + 1));
                if (result.contains("-"))
                    result = Double.toString(Double.parseDouble(result) - numeratorInDouble);
                else
                    result = Double.toString(Double.parseDouble(result) + numeratorInDouble);
            }
            return result.substring(0, integerOfNum.length() + num.length() + 2);
        }
        return result;

    }

    private static String convFromDeciToBase(String numInDeci, int base) {
        result = "";
        power = 1;
        value = 0;
        String numInRandomBase = "";

        double numInDeciDouble = Double.parseDouble(numInDeci);
        int integerOfNum = (int) numInDeciDouble;
        while (integerOfNum != 0) {
            numInRandomBase += valToDigit(Math.abs(integerOfNum) % base);
            integerOfNum /= base;
        }
        result = new StringBuilder(numInRandomBase).reverse().toString();
        if (Double.parseDouble(numInDeci) < 0)
            result = "-" + result;
        if (numInDeci.contains(".")) {
            result += ".";
            numInRandomBase = numInDeci.substring(result.length());
            int numerator = Integer.parseInt(numInRandomBase);
            int denominator = (int) Math.pow(10, numInRandomBase.length());
            numInRandomBase = "";
            for (int i = 0; i < numInDeci.length() && i < 3; i++) {
                numerator *= base;
                numInRandomBase += valToDigit(numerator / denominator);
                numerator %= denominator;
                if (numerator == 0)
                    break;
            }
            result += numInRandomBase;
        }
        return result;
    }

    private static int digitToVal(char c) {
        if (c >= '0' && c <= '9')
            return c - 48;
        else if (c == '-')
            return c;
        else if (c == '.')
            return c;
        else
            return c - 55;
    }

    private static char valToDigit(int num) {
        if (num >= 0 && num <= 9)
            return (char) (num + 48);
        else
            return (char) (num + 55);
    }

    private static String numSysTransfer(int startingNumSys, int numSysToTransfer, String num) {
        return convFromDeciToBase(convFromBaseToDeci(num, startingNumSys), numSysToTransfer);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть початкову систему числення: ");
        startingNumSys = sc.nextInt(); //система числення з якої переводимо
        System.out.print("Введіть систему числення в яку потрібно перевести: ");
        numSysToTransfer = sc.nextInt(); //система числення в яку переводимо
        System.out.print("Введіть число яке потрібно перевести: ");
        num = sc.next(); //число яке переводимо
        System.out.println("Ваше число " + num + " з системи числення " + startingNumSys + " у системі числення " + numSysToTransfer + " дорівнює: " + numSysTransfer(startingNumSys, numSysToTransfer, num));
    }
}