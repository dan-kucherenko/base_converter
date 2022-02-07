public class NumSysTransfer {
    private static int startingNumSys;
    private static int numSysToTransfer;
    private static String numInDeci;

    private static String convFromBaseToDeci(String num, int base) {
        if (base < 2 || base > 36)
            return null;
        int value = 0;
        int power = 1;
        String result = "";
        if (num.contains(".")) { //перевірка на ціле число
//            if (base > 10) {
//                int digitLength;
//                for (int i = 0; i < num.length(); i++) {
//                    int digit = digitToVal(num.charAt(i));
//                    if (digit == 46) {
//                        digit = 0;
//                        digitLength = Integer.toString(digit).length();
//                        //result = num.substring(digitLength - 1);
//                        num = result;
//                    } else {
//                        digitLength = Integer.toString(digit).length();
//                        result = num.substring(0, i) + Integer.toString(digit) + num.substring(digitLength);
//                        num = result;
//                    }
//                    if (digitLength > 1)
//                        i = digitLength - 1;
//                    else
//                        i = i;
//                }
//            }
            int integerOfNum = (int) Double.parseDouble(num);
            for (int i = Integer.toString(integerOfNum).length() - 1; i >= 0; i--) {
                int digit = digitToVal(Integer.toString(integerOfNum).charAt(i));
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

        } else {
            for (int i = num.length() - 1; i >= 0; i--) {
                int digit = digitToVal(num.charAt(i));
                if (digit >= base && digit != 45)
                    return null;
                else if (digit == 45) {
                    digit = 0;
                    result = "-" + value;
                } else {
                    value += digit * power;
                    power *= base;
                    result = "" + value;
                }
            }
            return result;
        }
    }

    private static String convFromDeciToBase(String numInDeci, int base) {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";

        if (numInDeci.contains(".")) {
            double numInDeciDouble = Double.parseDouble(numInDeci);
            result = (int) numInDeciDouble + ".";

            if (numInDeciDouble < 0)//число від'ємне -> прибираємо 3 перших знаки (-,0,.)
                numInDeci = numInDeci.substring(3);
            else    //число додатнє -> прибираємо 2 перших знаки(0,.)
                numInDeci = numInDeci.substring(2);

            int numerator = Integer.parseInt(numInDeci);
            int denominator = (int) Math.pow(10, numInDeci.length());

            for (int i = 0; i < numInDeci.length() && i < 3; i++) {
                numerator *= base;
                result += symbols.charAt(numerator / denominator);
                numerator %= denominator;
                if (numerator == 0)
                    break;
            }
            return result;
        } else {
            String s = "";
            int numInDeciInt = Integer.parseInt(numInDeci);
            while (numInDeciInt >= 1 || numInDeciInt <= -1) {
                s += valToDigit(Math.abs(numInDeciInt) % base);
                numInDeciInt /= base;
            }
            result += s;
            if (Integer.parseInt(numInDeci) < 0)
                result += "-";
            return new StringBuffer(result).reverse().toString();
        }
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
        else if (num == 46)
            return (char) 0;
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
        //System.out.println(convFromBaseToDeci("122.564", 9));
        System.out.println(numSysTransfer(9, 7, "122.564"));
    }
}