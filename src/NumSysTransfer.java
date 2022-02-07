public class NumSysTransfer {
    private static int startingNumSys;
    private static int numSysToTransfer;
    private static String numInDeci;
    private static int value = 0;
    private static int power = 1;
    private static String result = "";
    //private static String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String convFromBaseToDeci(String num, int base) {
        if (base < 2 || base > 36)
            return null;
        String numAsString = "";
        int digit;
        for (int i = 0; i < num.length(); i++) {
            digit = digitToVal(num.charAt(i));
            if (digit == 46) {
                digit = 0;
                numAsString += ".";
            } else if (digit == 45) {
                digit = 0;
                numAsString += "-";
            } else
                numAsString += Integer.toString(digit);
        }
        int integerOfNum = (int) Double.parseDouble(numAsString);
        for (int i = Integer.toString(integerOfNum).length() - 1; i >= 0; i--) {
            digit = digitToVal(Integer.toString(integerOfNum).charAt(i));
            if (digit == 45) {
                digit = 0;
                result = "-" + value;
            } else {
                value += digit * power;
                power *= base;
                result = "" + value;
            }
        }
        result += ".0";
        num = num.substring(Integer.toString(integerOfNum).length() + 1);
        int denominator = (int) Math.pow(10, num.length());

        for (int i = 0; i < num.length() && i < 3; i++) {
            double numeratorInDouble = Double.parseDouble(Character.toString(num.charAt(i))) * Math.pow(base, -(i + 1));
            if (result.contains("-"))
                result = Double.toString(Double.parseDouble(result) - numeratorInDouble);
            else
                result = Double.toString(Double.parseDouble(result) + numeratorInDouble);
        }
        return result.substring(0, Integer.toString(integerOfNum).length() + num.length() + 1); //повертаємо дріб у 10-вій системі
    }

    private static String convFromDeciToBase(String numInDeci, int base) {

        result = "";
        power = 1;
        value = 0;
        String numInRandomBase = "";
        if (numInDeci.contains(".")) {
            double numInDeciDouble = Double.parseDouble(numInDeci);
            int integerOfNum = (int) numInDeciDouble;
//            while (integerOfNum != 0) {
//                numInRandomBase += valToDigit(Math.abs(integerOfNum) % base);
//                integerOfNum /= base;
//            }
            for (int i = 0; i < numInDeci.length(); i++) {
                char digit = valToDigit(numInDeci.charAt(i));
                if (digit == 45) {
                    digit = 0;
                    numInRandomBase += "-";
                } else if (digit == 46) {
                    digit = 0;
                    numInRandomBase += ".";
                } else
                    numInRandomBase += Integer.toString(digit);
            }
            result = numInRandomBase;
        }
        return result;
    }

    //result+=s;
//        if ((int) numInDeciDouble < 0)
//            result += "-";
//        result = new
//
//                StringBuilder(result).
//
//                reverse().
//
//                toString();
//
//        numInDeci = numInDeci.substring(result.length() + 1);
//        result += ".";
//        int numerator = Integer.parseInt(numInDeci);
//        int denominator = (int) Math.pow(10, numInDeci.length());
//
//        for (
//                int i = 0; i < numInDeci.length() && i < 3; i++) {
//            numerator *= base;
//            result += symbols.charAt(numerator / denominator);
//            numerator %= denominator;
//            if (numerator == 0)
//                break;
//        }
//        return result;
//        } else {
//            s = "";
//            int numInDeciInt = Integer.parseInt(numInDeci);
//            while (numInDeciInt != 0) {
//                s += valToDigit(Math.abs(numInDeciInt) % base);
//                numInDeciInt /= base;
//            }
//            result += s;
//            if (Integer.parseInt(numInDeci) < 0)
//                result += "-";
//            return new StringBuffer(result).reverse().toString();
//        }


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
        else if (num == 46)
            return 0;
        else if (num == 45)
            return 0;
        else
            return (char) (num + 55);

    }

    private static String numSysTransfer(int startingNumSys, int numSysToTransfer, String num) {
//        if (convFromBaseToDeci(num, startingNumSys).contains(".")) {
//            double numInDeciDouble = Double.parseDouble(convFromBaseToDeci(num, startingNumSys)); // result of convFromBaseToDeci
//            return convFromDeciToBase(numInDeciDouble, numSysToTransfer);
//        } else {
//            int numInDeciInt = Integer.parseInt(convFromBaseToDeci(num, startingNumSys));
//            return convFromDeciToBase(numInDeciInt, numSysToTransfer);
//        }
        return convFromDeciToBase(convFromBaseToDeci(num, startingNumSys), numSysToTransfer);
    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Введіть початкову систему числення: ");
//        startingNumSys = sc.nextInt(); //система числення з якої переводимо
//        System.out.print("Введіть систему числення в яку перевести: ");
//        numSysToTransfer = sc.nextInt(); //система числення в яку переводимо
//        System.out.print("Введіть число яке потрібно перевести: ");
//        num = sc.next();
        //System.out.println(convFromBaseToDeci("A.564", 12));
        System.out.println(numSysTransfer(15, 7, "-12.564"));
    }
}