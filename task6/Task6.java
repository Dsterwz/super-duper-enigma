import java.util.Arrays;
import java.util.HashMap;

public class Task6 {
    // Класс, в котором все таски из файла task6.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("bell"))
            System.out.println(bell(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("translateWord"))
            System.out.println(translateWord(args[1]));

        else if (args[0].trim().equals("translateSentence"))
            System.out.println(translateSentence(args[1]));

        else if (args[0].trim().equals("validColor"))
            System.out.println(validColor(args[1]));

        else if (args[0].trim().equals("stripUrlParams")) {
            if (args.length == 3)
                System.out.println(stripUrlParams((args[1]),
                        args[2].replaceAll("\\[|\\]|\\s", "")
                                .split(",")));
            else if (args.length == 2)
                System.out.println(stripUrlParams((args[1])));
        }

        else if (args[0].trim().equals("getHashTags"))
            System.out.println(Arrays.toString(getHashTags(args[1])));

        else if (args[0].trim().equals("ulam"))
            System.out.println(ulam(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("longestNonrepeatingSubstring"))
            System.out.println(longestNonrepeatingSubstring(args[1]));

        else if (args[0].trim().equals("convertToRoman"))
            System.out.println(convertToRoman(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("formula"))
            System.out.println(formula(args[1]));

        else if (args[0].trim().equals("palindromeDescendant"))
            System.out.println(palindromeDescendant(Integer.parseInt(args[1])));
    }

    /*
     * Метод принимает число n и возвращает соответствующее число Белла.
     * Число Белла - это количество способов, которыми массив из n
     * элементов может быть разбит на непустые подмножества.
     */
    private static int bell(int n) {
        int[][] x = new int[n][n];
        x[0][0] = 1;
        for (int i = 1; i < n; i++) {
            x[i][0] = x[i - 1][i - 1];
            for (int j = 1; j < n; j++) {
                if (j <= i + 1)
                    x[i][j] = x[i][j - 1] + x[i - 1][j - 1];
            }
        }
        return x[n - 1][n - 1];
    }

    // Метод получает слово на английском и возвращает это
    // слово, переведенное на свинский латинский язык.
    private static String translateWord(String word) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).matches("[aeiouyAEIOUY]")) {
                if (i == 0)
                    result = word + "yay";
                else
                    result = word.substring(i) +
                            word.substring(0, i) + "ay";
                if (word.charAt(0) < 90) {
                    result = result.substring(0, 1)
                            .toUpperCase()
                            + result.substring(1).toLowerCase();
                }
                break;
            }
        }
        return result;
    }

    // Метод получает предложение на английском и возвращает это
    // предложение, переведенное на свинский латинский язык.
    private static String translateSentence(String sentence) {
        String result = "";
        String[] words = sentence.split("\s");

        for (String word : words)
            if (word.charAt(word.length() - 1) < 64) {
                result += translateWord(word.substring(0, word.length() - 1))
                        + word.substring(word.length() - 1) + "\s";
            } else
                result += translateWord(word) + "\s";

        return result.trim();
    }

    /*
     * Метод принимает строку (например, " rgb(0,0,0)") и
     * или rgba(1, 11, 111, 0.111) возвращает true, если
     * ее формат правильный, в противном случае возвращает false.
     */
    private static boolean validColor(String color) {
        return (color
                .matches(
                        "rgb\\((((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|(\\d\\d)|(\\d)),){2}((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|(\\d\\d)|(\\d))\\)")
                ||
                color.matches(
                        "rgba\\((((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|(\\d\\d)|(\\d)),){3}((0\\.\\d+)|1)\\)"));
    }

    // Метод принимает URL (строку), удаляет дублирующиеся
    // параметры запроса и параметры, указанные во втором аргументе.
    private static String stripUrlParams(String url, String[] paramsToStrip) {
        String content = "";
        if (url.matches("(https|http)://\\w+\\.[a-z]+")) {
            return url;
        } else if (url.matches("(https|http)://\\w+\\.[a-z]+\\S+"))
            content += url.substring(0, url.indexOf("?"));
        String[] params = url.substring(url.indexOf("?") + 1).split("&");
        HashMap<String, String> pars = new HashMap<>();

        for (String paramToStrip : paramsToStrip) {
            for (String param : params) {
                String key = param.substring(0, param.indexOf("="));
                if (!paramToStrip.equals(key))
                    pars.put(key,
                            param.substring(param.indexOf("=") + 1));
            }
        }
        return content + "?" + pars.entrySet().toString()
                .replaceAll("\\]|\\[|\\s", "")
                .replaceAll(",", "&");
    }

    // Метод принимает URL (строку), удаляет дублирующиеся
    // параметры запроса и параметры, указанные во втором аргументе.
    private static String stripUrlParams(String url) {
        String content = "";
        if (url.matches("(https|http)://\\w+\\.[a-z]+")) {
            return url;
        } else if (url.matches("(https|http)://\\w+\\.[a-z]+\\S+"))
            content += url.substring(0, url.indexOf("?"));
        String[] params = url.substring(url.indexOf("?") + 1).split("&");
        HashMap<String, String> pars = new HashMap<>();

        for (String param : params)
            pars.put(param.substring(0, param.indexOf("=")),
                    param.substring(param.indexOf("=") + 1));

        return content + "?" + pars.entrySet().toString()
                .replaceAll("\\]|\\[|\\s", "")
                .replaceAll(",", "&");
    }

    // Метод извлекает три самых длинных слова из заголовка
    // газеты и преобразует их в хэштеги.
    private static String[] getHashTags(String title) {
        String content = "";
        String[] words = title.replaceAll("[.,!?]", "").split("\\s");
        int maxLenghtPrev = Integer.MAX_VALUE;
        String maxWord = "";

        for (int i = 0; i < 3; i++) {
            int maxLength = 0;
            int maxIndex = 0;

            for (int j = 0; j < words.length; j++) {
                if ((words[j].length() > maxLength)
                        && ((words[j].length() < maxLenghtPrev)
                                || ((words[j].length() == maxLenghtPrev) && (!maxWord.equals(words[j]))))) {
                    maxLength = words[j].length();
                    maxIndex = j;
                }
            }
            if (maxLength == words[maxIndex].length()) {
                content += "#" + words[maxIndex].toLowerCase() + "\s";
                maxLenghtPrev = maxLength;
                maxWord = words[maxIndex];
            }
        }
        return content.split("\\s");
    }

    // Метод находит n-число Улама.
    private static int ulam(int n) {

        if (n <= 2)
            return n;

        else {
            int[] numbers = new int[n];
            numbers[0] = 1;
            numbers[1] = 2;

            int a = 3;
            int index = 2;

            while (true) {
                int r = 0;
                for (int i = 0; i < index; i++) {
                    for (int j = 0; j < index; j++) {
                        if ((numbers[i] + numbers[j] == a)
                                && (i != j))
                            r++;
                    }
                }

                if (r == 2) {
                    numbers[index] = a;
                    index++;
                    if (index == n)
                        break;
                }
                a++;
            }
            return numbers[n - 1];
        }
    }

    // Метод возвращает самую длинную неповторяющуюся
    // подстроку для строкового ввода.
    private static String longestNonrepeatingSubstring(String str) {
        String result = "";
        str += "\s";
        char[] word = str.toCharArray();
        int maxLength = 0;

        String unique = "";
        char begin = 0;

        for (int i = 1; i < word.length; i++) {
            if ((word[i] != word[i - 1])) {
                if (begin == 0 || word[i - 1] == begin) {
                    if (unique.length() > maxLength) {
                        result = unique;
                    }
                    begin = word[i - 1];
                    unique = String.valueOf(begin);
                } else {
                    char[] res = unique.toCharArray();
                    boolean flag = true;
                    for (char l : res) {
                        if (l == word[i - 1]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        unique += word[i - 1];
                }
            }
        }

        return result;
    }

    // Метод принимает арабское число и преобразует его в римское число.
    private static String convertToRoman(int n) {
        String result = "";

        if (n >= 1_000) {
            int a = n / 1_000;
            switch (a) {
                case 3:
                    result += "MMM";
                    break;
                case 2:
                    result += "MM";
                    break;
                case 1:
                    result += "MM";
                    break;
            }
            n -= a * 1000;
        }

        if (n >= 100) {
            int a = n / 100;
            switch (a) {
                case 9:
                    result += "CM";
                    break;
                case 8:
                    result += "DCCC";
                    break;
                case 7:
                    result += "DCC";
                    break;
                case 6:
                    result += "DC";
                    break;
                case 5:
                    result += "D";
                    break;
                case 4:
                    result += "CD";
                    break;
                case 3:
                    result += "CCC";
                    break;
                case 2:
                    result += "CC";
                    break;
                case 1:
                    result += "C";
                    break;
            }
            n -= a * 100;
        }

        if (n >= 10) {
            int a = n / 10;
            switch (a) {
                case 9:
                    result += "XC";
                    break;
                case 8:
                    result += "LXXX";
                    break;
                case 7:
                    result += "LXX";
                    break;
                case 6:
                    result += "LX";
                    break;
                case 5:
                    result += "L";
                    break;
                case 4:
                    result += "XL";
                    break;
                case 3:
                    result += "XXX";
                    break;
                case 2:
                    result += "XX";
                    break;
                case 1:
                    result += "X";
                    break;
            }
            n -= a * 10;
        }

        if (n > 0) {
            switch (n) {
                case 9:
                    result += "IX";
                    break;
                case 8:
                    result += "VIII";
                    break;
                case 7:
                    result += "VII";
                    break;
                case 6:
                    result += "VI";
                    break;
                case 5:
                    result += "V";
                    break;
                case 4:
                    result += "IV";
                    break;
                case 3:
                    result += "III";
                    break;
                case 2:
                    result += "II";
                    break;
                case 1:
                    result += "I";
                    break;
            }
        }

        return result;
    }

    //
    private static boolean formula(String formula) {
        if (formula.matches("\\d+[\\+\\-\\*\\/]{1}\\d+={1}\\d+")) {
            if (formula.indexOf("+") != -1)
                return Double.valueOf(formula.substring(formula.indexOf("+") + 1, formula.indexOf("=")))
                        + Double.valueOf(formula.substring(0, formula.indexOf("+"))) == Double
                                .valueOf(formula.substring(formula.indexOf("=") + 1));
            else if (formula.indexOf("-") != -1)
                return Double.valueOf(formula.substring(0, formula.indexOf("-"))) -
                        Double.valueOf(formula.substring(formula.indexOf("-") + 1, formula.indexOf("="))) == Double
                                .valueOf(formula.substring(formula.indexOf("=") + 1));
            else if (formula.indexOf("*") != -1)
                return Double.valueOf(formula.substring(formula.indexOf("*") + 1, formula.indexOf("=")))
                        * Double.valueOf(formula.substring(0, formula.indexOf("*"))) == Double
                                .valueOf(formula.substring(formula.indexOf("=") + 1));
            else if (formula.indexOf("/") != -1)
                return Double.valueOf(formula.substring(0, formula.indexOf("/")))
                        / Double.valueOf(formula.substring(formula.indexOf("/") + 1, formula.indexOf("="))) == Double
                                .valueOf(formula.substring(formula.indexOf("=") + 1));
            else
                return false;
        } else
            return false;
    }

    //
    private static boolean palindromeDescendant(int n) {
        char[] number = String.valueOf(n).toCharArray();

        int pol = number.length / 2;
        int cal = 0;

        while (true) {
            String buff = "";

            for (int i = 1; i < pol + 1; i++) {
                if (number[pol + i - 1] == number[pol - i]) {
                    cal++;
                }
            }

            if (cal == pol)
                return true;
            else {
                for (int i = 0; i < pol * 2; i += 2) {
                    buff += String.valueOf(Integer.valueOf(String.valueOf(number[i]))
                            + Integer.valueOf(String.valueOf(number[i + 1])));
                }
                // System.out.println(buff);

                number = buff.toCharArray();
                pol = number.length / 2;

            }
            if ((number.length == 2) && (number[1] == number[0]))
                return true;
            else if (number.length < 2)
                return false;

        }
    }

}