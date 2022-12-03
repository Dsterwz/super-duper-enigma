import java.util.Arrays;
import java.lang.Math;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Task5 {
    // Класс, в котором все таски из файла task3.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("encrypt"))
            System.out.println(Arrays.toString(encrypt(args[1])));

        else if (args[0].trim().equals("decrypt")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers = new int[items.length];
            for (int i = 0; i < items.length; i++)
                numbers[i] = Integer.parseInt(items[i]);
            System.out.println(decrypt(numbers));
        }

        else if (args[0].trim().equals("canMove"))
            System.out.println(canMove(args[1], args[2], args[3]));

        else if (args[0].trim().equals("canComplete"))
            System.out.println(canComplete(args[1], args[2]));

        else if (args[0].trim().equals("sumDigProd")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers = new int[items.length];
            for (int i = 0; i < items.length; i++)
                numbers[i] = Integer.parseInt(items[i]);
            System.out.println(sumDigProd(numbers));
        }

        else if (args[0].trim().equals("sameVowelGroup")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            System.out.println(Arrays.toString(sameVowelGroup(items)));
        }

        else if (args[0].trim().equals("validateCard"))
            System.out.println(validateCard(args[1]));

        else if (args[0].trim().equals("numToEng"))
            System.out.println(numToEng(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("numToRus"))
            System.out.println(numToRus(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("getSha256Hash"))
            System.out.println(getSha256Hash(args[1]));

        else if (args[0].trim().equals("correctTitle"))
            System.out.println(correctTitle(args[1]));

        else if (args[0].trim().equals("hexLattice"))
            System.out.println(hexLattice(Integer.parseInt(args[1])));

    }

    /*
     * Метод принимают строку и массив и возвращает закодированное
     * сообщение. Первая буква строки или первый элемент массива
     * представляет собой символьный код этой буквы.
     * Следующие элементы-это различия между символами.
     */
    private static int[] encrypt(String word) {

        if (!word.isEmpty()) {
            int[] result = new int[word.length()];
            result[0] = word.charAt(0);
            for (int i = 1; i < word.length(); i++) {
                result[i] = word.charAt(i) - word.charAt(i - 1);
            }
            return result;
        } else
            return null;

    }

    /*
     * Метод принимают строку и массив и возвращает закодированное
     * сообщение. Первая буква строки или первый элемент массива
     * представляет собой символьный код этой буквы.
     * Следующие элементы-это различия между символами.
     */
    private static String decrypt(int[] word) {
        // Переменная для результата.
        String content = "";
        // Хранилище для ascii-кода текущей буквы.
        int c = 0;

        if (word.length != 0) {
            c = word[1] + word[0];
            content += (char) word[0];
            content += (char) c;
            for (int i = 2; i < word.length; i++) {
                c = word[i] + c;
                content += (char) (c);
            }
        }
        return content;
    }

    /*
     * Метод принимает имя шахматной фигуры, ее положение и целевую
     * позицию. Возвращать true, если фигура может двигаться к цели,
     * и false, если она не может этого сделать
     */
    private static boolean canMove(String name,
            String start, String end) {

        int letters = Math.abs((int) start.charAt(0)
                - (int) end.charAt(0));
        int numbers = Math.abs((int) start.charAt(1)
                - (int) end.charAt(1));

        if ("King".equals(name)) {
            if (letters <= 1 && numbers >= 1)
                return true;

        } else if ("Queen".equals(name)) {
            if (((numbers == 0 && letters >= 0) ||
                    (numbers >= 0 && letters == 0)) ||
                    letters == numbers)
                return true;

        } else if ("Rook".equals(name)) {
            if ((numbers == 0 && letters >= 0) ||
                    (numbers >= 0 && letters == 0))
                return true;

        } else if ("Bishop".equals(name)) {
            if (letters == numbers)
                return true;

        } else if ("Knight".equals(name)) {
            if ((numbers == 2 && letters == 1) ||
                    (numbers == 1 && letters == 2))
                return true;

        } else if ("Pawn".equals(name)) {
            if (letters == 0 && numbers >= 1)
                return true;
        }

        return false;
    }

    /*
     * Метод, учитывая входную строку, определяет, может ли слово быть
     * завершено. Входная строка может быть завершена, если можно
     * добавить дополнительные буквы, и никакие буквы не должны быть
     * удалены, чтобы соответствовать слову. Кроме того, порядок букв
     * вовходной строке должен быть таким же, как и порядок букв в
     * последнем слове.
     */
    private static boolean canComplete(String a, String b) {
        if (!a.isEmpty() && !b.isEmpty()) {
            char[] w = a.toCharArray();
            char[] word = b.toCharArray();

            // Хранилище для индекса, чтобы проверить кальку.
            int mIndex = 0;
            // Счётчик для проверки кальки.
            int cal = w.length;

            for (int i = 0; i < word.length; i++) {
                for (int j = mIndex; j < w.length; j++) {
                    if (w[j] == word[i]) {
                        cal--;
                        mIndex = j;
                        break;
                    }
                }
            }
            return cal == 0;
        } else
            return a.equals(b);
    }

    /*
     * Метод принимает числа в качестве аргументов, складывает их
     * вместе и возвращает произведение цифр до тех пор, пока ответ
     * не станет длиной всего в 1 цифру.
     */
    private static int sumDigProd(int[] numbers) {
        int a = 0;
        for (int i = 0; i < numbers.length; i++) {
            a += numbers[i];
        }
        char[] przvdn = String.valueOf(a).toCharArray();

        while (przvdn.length > 1) {
            a = 1;
            for (int i = 0; i < przvdn.length; i++)
                a *= Integer.parseInt(String.valueOf(przvdn[i]));
            przvdn = String.valueOf(a).toCharArray();

        }

        return Integer.parseInt(String.valueOf(przvdn[0]));
    }

    // Метод выбирает все слова, имеющие все те же гласные (в любом
    // порядке и / или количестве), что и первое слово, включая первое слово.
    private static String[] sameVowelGroup(String[] words) {
        String result = words[0];

        String regex = "";
        for (int i = 0; i < result.length(); i++) {
            if (String.valueOf(result.charAt(i)).matches("[aeiouy]"))
                regex += result.charAt(i);
        }
        // Массив гласных в перовм слове.
        char[] letters = regex.toCharArray();
        Arrays.sort(letters);

        for (int i = 1; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            Arrays.sort(word);

            int f = 0;
            for (int j = 0; j < word.length; j++) {
                for (int k = f; k < letters.length; k++) {
                    if (letters[k] == word[j])
                        f++;
                }
            }
            if (f == letters.length)
                result += " " + words[i];

        }
        return result.split("\\s");
    }

    /*
     * Метод принимает число в качестве аргумента и возвращает true, если
     * это число является действительным номером кредитной карты, а в противном
     * случае-false. Номера кредитных карт должны быть длиной от 14 до 19
     * цифр и проходить тест Луна, описанный ниже:
     * 
     * – Удалите последнюю цифру (это"контрольная цифра").
     * – Переверните число.
     * – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение
     * имеет более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7)
     * – Добавьте все цифры.
     * – Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть
     * равен контрольной цифре из Шага 1.
     * 
     */
    private static boolean validateCard(String cardNumber) {
        char[] card = cardNumber.toCharArray();
        int[] cardReversed = new int[card.length - 1];
        int sum = 0;

        for (int i = 1; i < card.length; i++) {
            if (i % 2 != 0)
                cardReversed[i - 1] = Integer
                        .parseInt(String.valueOf(card[card.length - 1 - i])) * 2;
            else
                cardReversed[i - 1] = Integer
                        .parseInt(String.valueOf(card[card.length - 1 - i]));
            if (cardReversed[i - 1] > 9)
                sum += cardReversed[i - 1] % 10 + cardReversed[i - 1] / 10;
            else
                sum += cardReversed[i - 1];
        }

        return (10 - sum % 10 == Integer.parseInt(String.valueOf(card[card.length - 1])) ||
                (10 - sum % 100 == Integer.parseInt(String.valueOf(card[card.length - 1]))));
    }

    /*
     * Метод принимает положительное целое число от 0 до 999 включительно
     * и возвращает строковое представление этого целого числа, написанное на
     * английском языке.
     */
    private static String numToEng(int number) {
        String content = "";
        String[] digits = new String[] { "zero", "one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine" };

        // boolean flag = false;

        if (number > 99) {
            content += digits[number / 100] + " hundred ";
            number -= number / 100 * 100;
            // flag = true;
        }

        if (number > 19 && number <= 99) {
            switch (number / 10) {
                case 2:
                    content += "twenty ";
                    break;
                case 3:
                    content += "thirty ";
                    break;
                case 4:
                    content += "forty ";
                    break;
                case 5:
                    content += "fifty ";
                    break;
                case 8:
                    content += "eighty ";
                    break;
                default:
                    content += digits[number / 10] + "ty ";
                    break;

                // flag = true;
            }
            number -= number / 10 * 10;
        }

        if (number > 10 && number <= 19) {
            switch (number) {
                case 10:
                    content += "ten";
                    break;
                case 11:
                    content += "eleven";
                    break;
                case 12:
                    content += "twelve";
                    break;
                case 13:
                    content += "thirteen";
                    break;
                case 14:
                    content += "fourteen";
                    break;
                case 15:
                    content += "fifteen";
                    break;
                case 16:
                    content += "sixteen";
                    break;
                case 17:
                    content += "seventeen";
                    break;
                case 18:
                    content += "eighteen";
                    break;
                case 19:
                    content += "nineteen";
                    break;

            }
        } else if (number > 0 && number <= 9) {
            content += digits[number];
        }
        return content.trim();
    }

    /*
     * Метод принимает положительное целое число от 0 до 999 включительно
     * и возвращает строковое представление этого целого числа, написанное на
     * русском языке.
     */
    private static String numToRus(int number) {
        String content = "";
        String[] digits = new String[] { "ноль", "один", "два", "три",
                "четыре", "пять", "шесть", "семь", "восемь", "девять" };

        if (number > 99) {
            switch (number / 100) {
                case 1:
                    content += "сто ";
                    break;
                case 2:
                    content += "двести ";
                    break;
                case 3:
                    content += "триста ";
                    break;
                case 4:
                    content += "четыреста ";
                    break;

                default:
                    content += digits[number / 100] + "сот ";
                    break;
            }

            number -= number / 100 * 100;
        }

        if (number > 19 && number <= 99) {
            switch (number / 10) {
                case 2:
                    content += "двадцать  ";
                    break;
                case 3:
                    content += "тридцать ";
                    break;
                case 4:
                    content += "сорок ";
                    break;
                case 9:
                    content += "девяносто ";
                    break;
                default:
                    content += digits[number / 10] + "десят ";
                    break;
            }
            number -= number / 10 * 10;
        }

        if (number > 10 && number <= 19) {
            switch (number) {

                case 10:
                    content += "десять";
                    break;
                case 11:
                    content += "одиннадцать";
                    break;
                case 12:
                    content += "двенадцать";
                    break;
                case 13:
                    content += "тринадцать";
                    break;
                case 14:
                    content += "четырнадцать";
                    break;
                case 15:
                    content += "пятнадцать";
                    break;
                case 16:
                    content += "шестнадцать";
                    break;
                case 17:
                    content += "семнадцать";
                    break;
                case 18:
                    content += "восемнадцать";
                    break;
                case 19:
                    content += "девятнадцать";
                    break;

            }
        } else if (number >= 0 && number <= 9)
            content += digits[number];

        if (number == 0)
            content = digits[number];

        return content.trim();
    }

    // Метод возвращает безопасный хеш SHA-256 для данной строки.
    private static String getSha256Hash(String pwd) {
        String content = "";
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes());
            // return Arrays.toString(hash);
            // return String.valueOf(hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    content += '0';
                }
                content += hex;
            }
            return content;
        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }

        /*
         * // Константы, представляющие первые 32 бита дробных
         * // частей квадратных корней первых 8 простых чисел.
         * int h0 = 0x6A09E667;
         * int h1 = 0xBB67AE85;
         * int h2 = 0x3C6EF372;
         * int h3 = 0xA54FF53A;
         * int h4 = 0x510E527F;
         * int h5 = 0x9B05688C;
         * int h6 = 0x1F83D9AB;
         * int h7 = 0x5BE0CD19;
         * 
         * // Таблица констант (первые 32 бита дробных частей
         * // кубических корней первых 64 простых чисел.
         * int[] k = new int[] { 0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5,
         * 0x3956C25B, 0x59F111F1, 0x923F82A4,
         * 0xAB1C5ED5,
         * 0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3, 0x72BE5D74, 0x80DEB1FE,
         * 0x9BDC06A7, 0xC19BF174,
         * 0xE49B69C1, 0xEFBE4786, 0x0FC19DC6, 0x240CA1CC, 0x2DE92C6F, 0x4A7484AA,
         * 0x5CB0A9DC, 0x76F988DA,
         * 0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7, 0xC6E00BF3, 0xD5A79147,
         * 0x06CA6351, 0x14292967,
         * 0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13, 0x650A7354, 0x766A0ABB,
         * 0x81C2C92E, 0x92722C85,
         * 0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3, 0xD192E819, 0xD6990624,
         * 0xF40E3585, 0x106AA070,
         * 0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5, 0x391C0CB3, 0x4ED8AA4A,
         * 0x5B9CCA4F, 0x682E6FF3,
         * 0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208, 0x90BEFFFA, 0xA4506CEB,
         * 0xBEF9A3F7, 0xC67178F2 };
         * 
         * 
         * // Переменная для конечного результата.
         * String content = "";
         * // Хранилище для реверснутого байт-кода.
         * String temp = "";
         * byte[] password = pwd.getBytes();
         * 
         * // Перевод символов в двоичное представление.
         * for (int i = 0; i < password.length; i++)
         * temp += toBinary(password[i]);// + "\s";
         * 
         * content += temp + "1" +
         * "0".repeat(511 - temp.length() -
         * toBinary(pwd.length()).length());
         * content += toBinary(pwd.length());
         * return content.trim();
         */

    }

    /*
     * // Метод, переводящий число из десятичной системы исчисления
     * // в двоичную.
     * private static String toBinary(int n) {
     * String result = "";
     * String num = "";
     * 
     * while (n > 0) {
     * num += n % 2;
     * n /= 2;
     * }
     * num += "0".repeat(8 - num.length());
     * char[] numChar = num.toCharArray();
     * for (int j = 0; j < numChar.length; j++)
     * result += numChar[numChar.length - j - 1];
     * return result;
     * 
     * }
     */

    // Метод принимает строку и возвращает строку с правильным
    // регистром
    private static String correctTitle(String title) {
        String content = "";
        String[] words = title.split("\\s");
        for (int i = 0; i < words.length; i++) {
            if ("in".equals(words[i].toLowerCase()) ||
                    "at".equals(words[i].toLowerCase()) ||
                    "the".equals(words[i].toLowerCase()) ||
                    "of".equals(words[i].toLowerCase()))
                content += words[i].toLowerCase() + "\s";
            else {
                for (int j = 0; j < words[i].length(); j++) {
                    if (j == 0)
                        content += words[i].toUpperCase().charAt(j);
                    else
                        content += words[i].toLowerCase().charAt(j);
                }
                content += "\s";
            }
        }
        return content.trim();
    }

    // Метод принимает целое число n и возвращает "недопустимое", если
    // n не является центрированнымшестиугольным числом.
    private static String hexLattice(int n) {
        String content = "";
        String content2 = "";
        int hexNumLength = 0;
        for (int i = 0; i < n; i++) {
            if (3 * i * (i + 1) + 1 == n)
                hexNumLength = i + 1;
            else
                return "Invalid";
        }
        for (int i = 1; i < hexNumLength + 1; i++) {
            content += "o\s".repeat(hexNumLength * 2 - i)
                    + "\n" + "\s".repeat(i);
            content2 += "\s".repeat(hexNumLength - i)
                    + "o\s".repeat(hexNumLength + i - 1)
                    + "\n";
        }

        // content = content.repeat(hexNumLength);
        return content2 + content.substring((hexNumLength * 2 - 1) * 2 + 1);
    }

}