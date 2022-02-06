public class NumSysTransfer {
    private static int startingNumSys;
    private static int numSysToTransfer;
    private static String numInDeci;

    private static String convFromBaseToDeci(String num, int base) {
        if (base < 2 || base > 36)
            return null;
        String result = "";
        int value = 0;
        int power = 1;

        if (num.contains(".")) { //перевірка на ціле число
            StringBuilder sb;
            if (Double.parseDouble(num) < 0) {  //число менше від'ємне -> прибираємо 3 перших знаки (-,0,.)
                sb = new StringBuilder((int) Double.parseDouble(num) + ".0");
                num = num.substring(3);
            } else {   //число додатне -> прибираємо 2 перших знаки(0,.)
                sb = new StringBuilder((int) Double.parseDouble(num) + ".0");
                num = num.substring(2);
            }
            int denominator = (int) Math.pow(10, num.length());

            for (int i = 0; i < num.length() && i < 3; i++) {
                double numeratorInDouble = Double.parseDouble(Character.toString(num.charAt(i))) * Math.pow(base, -(i + 1));
                numeratorInDouble += Math.abs(Double.parseDouble(sb.toString()));
                if (sb.toString().contains("-"))
                    sb.replace(1, sb.length(), Double.toString(numeratorInDouble));
                else
                    sb.replace(0, sb.length(), Double.toString(numeratorInDouble));
            }
            sb.delete(5, sb.length());
            return sb.toString(); //повертаємо дріб у 10-вій системі
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
                    power = power * base;
                    result = "" + value;
                }
            }
            return result;
        }
    }

    private static String convFromDeciToBase(double numInDeci, int base) {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        int remaining;
        String numInDeciString = Double.toString(numInDeci);
        if (numInDeciString.contains(".")) {
            StringBuilder sb;
            if (numInDeci < 0) {
                sb = new StringBuilder((int) Double.parseDouble(numInDeciString) + ".");
                numInDeciString = numInDeciString.substring(3);
            } else {
                sb = new StringBuilder((int) Double.parseDouble(numInDeciString) + ".");
                numInDeciString = numInDeciString.substring(2);
            }
            int numerator = Integer.parseInt(numInDeciString);
            int denominator = (int) Math.pow(base, numInDeciString.length());

            for (int i = 0; i < numInDeciString.length() && i < 3; i++) {
                numerator *= base;
                sb.append(symbols.charAt((int) (numerator / denominator)));
                numerator %= denominator;
                if (numerator == 0) {
                    break;
                }
            }
            sb.delete(5, sb.length());
            return sb.toString();
        } else {
            while (numInDeci < -1 || numInDeci > 1) {
                remaining = (int) numInDeci % base;
                if (base >= 10) {
//                    switch (remaining) {
//                        case 10:
//                            result += 'A';
//                            break;
//                        case 11:
//                            result += 'B';
//                            break;
//                        case 12:
//                            result += 'C';
//                            break;
//                        case 13:
//                            result += 'D';
//                            break;
//                        case 14:
//                            result += 'E';
//                            break;
//                        case 15:
//                            result += 'F';
//                            break;
//                        case 16:
//                            result += 'G';
//                            break;
//                        case 17:
//                            result += 'H';
//                            break;
//                        case 18:
//                            result += 'I';
//                            break;
//                        case 19:
//                            result += 'J';
//                            break;
//                        case 20:
//                            result += 'K';
//                            break;
//                        case 21:
//                            result += 'L';
//                            break;
//                        case 22:
//                            result += 'M';
//                            break;
//                        case 23:
//                            result += 'N';
//                            break;
//                        case 24:
//                            result += 'O';
//                            break;
//                        case 25:
//                            result += 'P';
//                            break;
//                        case 26:
//                            result += 'Q';
//                            break;
//                        case 27:
//                            result += 'R';
//                            break;
//                        case 28:
//                            result += 'S';
//                            break;
//                        case 29:
//                            result += 'T';
//                            break;
//                        case 30:
//                            result += 'U';
//                            break;
//                        case 31:
//                            result += 'V';
//                            break;
//                        case 32:
//                            result += 'W';
//                            break;
//                        case 33:
//                            result += 'X';
//                            break;
//                        case 34:
//                            result += 'Y';
//                            break;
//                        case 35:
//                            result += 'Z';
//                            break;
//                        default:
//                            result += remaining;
//                            break;
//                    }
                    result += symbols.charAt(remaining);
                } else
                    result += remaining;

                numInDeci /= base;
            }
        }
        if (numInDeci < 0)
            result = result + "-";
        return new StringBuffer(result).reverse().toString();
    }

    private static int digitToVal(char c) {
        if (c >= '0' && c <= '9')
            return c - 48;
        else if (c == '-')
            return c;
        else if (c == ',')
            return c;
        else
            return c - 55;
    }

    private static String numSysTransfer(int startingNumSys, int numSysToTransfer, String num) {
        double numInDeci = Double.parseDouble(convFromBaseToDeci(num, startingNumSys)); // result of convFromBaseToDeci
        return convFromDeciToBase(numInDeci, numSysToTransfer);
    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Введіть початкову систему числення: ");
//        startingNumSys = sc.nextInt(); //система числення з якої переводимо
//        System.out.print("Введіть систему числення в яку перевести: ");
//        numSysToTransfer = sc.nextInt(); //система числення в яку переводимо
//        System.out.print("Введіть число яке потрібно перевести: ");
//        num = sc.next();
        //System.out.println(convFromBaseToDeci("2.463", 7));
        System.out.println(numSysTransfer(7, 12, "2.463"));
    }
}