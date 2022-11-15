import java.util.Arrays;

public class Task4 {
    // Класс, в котором все таски из файла task3.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("bessie"))
            System.out.println(bessie(
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    args[3]));

        else if (args[0].trim().equals("mSplit"))
            System.out.println(Arrays.toString(mSplit(args[1])));

        else if (args[0].trim().equals("toCamelCase"))
            System.out.println(toCamelCase(args[1]));

        else if (args[0].trim().equals("toSnakeCase"))
            System.out.println(toSnakeCase(args[1]));

        else if (args[0].trim().equals("overTime")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            double[] numbers = new double[items.length];
            for (int i = 0; i < items.length; i++)
                numbers[i] = Double.parseDouble(items[i]);
            System.out.println(overTime(numbers));

        }

        else if (args[0].trim().equals("BMI"))
            System.out.println(BMI(args[1], args[2]));

        else if (args[0].trim().equals("bugger"))
            System.out.println(bugger(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("toStarShorthand"))
            System.out.println(toStarShorthand(args[1]));

        else if (args[0].trim().equals("doesRhyme"))
            System.out.println(doesRhyme(args[1], args[2]));

        else if (args[0].trim().equals("trouble"))
            System.out.println(trouble(
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[1])));

        else if (args[0].trim().equals("countUniqueBooks"))
            System.out.println(countUniqueBooks(args[1], args[2]));
        // System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", "$"));
    }

    // Метод для текстового процессора Бесси.
    private static String bessie(int n, int k, String essay) {
        String[] words = essay.split("\\s");
        int line = 0;
        String content = "";

        for (int i = 0; i < n; i++) {
            line += words[i].length();
            if (line <= k)
                content += words[i] + "\s";
            else {
                content = content.trim() + "\n"
                        + words[i] + "\s";
                line = words[i].length();
            }
        }

        return content;
    }

    // Метод группирует строку в кластер скобок.
    private static String[] mSplit(String s) {
        int openBracket = 0;
        int closeBracket = 0;
        char[] brackets = s.toCharArray();
        String content = "";

        for (int i = 0; i < brackets.length; i++) {
            if (brackets[i] == "(".charAt(0)) {
                openBracket++;
                content += brackets[i];
            } else if (brackets[i] == ")".charAt(0)) {
                closeBracket++;
                content += brackets[i];
            }
            if (openBracket == closeBracket)
                content += '\s';
        }
        return content.split("\\s");
    }

    // Метод преобразует в camelCase.
    private static String toCamelCase(String s) {
        String[] zak = s.split("_");
        String content = zak[0];
        for (int i = 1; i < zak.length; i++) {
            content += zak[i].substring(0, 1)
                    .toUpperCase() + zak[i].substring(1);
        }
        return content;
    }

    // Метод преобразует в snake_case.
    private static String toSnakeCase(String s) {
        String content = "";
        char[] zak = s.toCharArray();
        for (int i = 0; i < zak.length; i++) {
            if (String.valueOf(zak[i]).matches("[A-Z]")) {
                content += "_";
            }
            content += String.valueOf(zak[i]).toLowerCase();
        }

        return content;
    }

    // Метод вычисляет сверхурочную работу и оплату, связанную
    // со сверхурочной работой.
    private static String overTime(double[] schedule) {
        /*
         * double startJob = schedule[0] - schedule[0] * 100 % 100 / 100
         * + schedule[0] * 100 % 100 / 60;
         * double endJob = schedule[1] - schedule[1] * 100 % 100 / 100
         * + schedule[1] * 100 % 100 / 60;
         */
        double startJob = schedule[0];
        double endJob = schedule[1];
        double loan = 0;
        if (endJob < 17 && endJob > 9) {
            loan += (endJob - startJob) * schedule[2];
        } else {
            if (endJob > 17) {
                loan += (17 - schedule[0]) * schedule[2];
                loan += (endJob - 17)
                        * schedule[2] * schedule[3];

            } else if (schedule[1] < 9) {

                loan += (endJob - 5)
                        * schedule[2] * schedule[3];
            }
            loan += (17 - startJob) * schedule[2];
        }
        return "$" + String.valueOf(loan);
        // return startJob;
    }

    // Методинимать вес и рост (в килограммах, фунтах, метрах
    // или дюймах) и возвращать ИМТ и связанную с ним категорию.
    private static String BMI(String weight, String height) {
        double bMI = 0;
        String content = "";
        String[] massa = weight.split("\s");
        String[] vysota = height.split("\s");
        bMI += Double.parseDouble(massa[0]);
        if (massa[1].equals("pounds"))
            bMI /= 2.205;
        bMI /= (Double.parseDouble(vysota[0])
                * Double.parseDouble(vysota[0]));
        if (vysota[1].equals("inches"))
            bMI *= 39.37 * 39.37;
        content += String.valueOf(bMI) + " ";
        if (bMI > 25)
            content += "Overweight";
        else if (bMI < 18.5)
            content += "Underweight";
        else
            content += "Normal weight";
        return content;
    }

    /*
     * Метод принимает число и возвращает его мультипликативное
     * постоянство, которое представляет собой количество раз,
     * которое вы должны умножать цифры в num, пока не достигнете
     * одной цифры.
     */
    private static int bugger(int n) {
        int cal = 0;
        char[] numbers = String.valueOf(n).toCharArray();

        while (numbers.length > 1) {
            int kolbasa = 1;
            for (int i = 0; i < numbers.length; i++)
                kolbasa *= Integer.parseInt(String.valueOf(numbers[i]));
            numbers = String.valueOf(kolbasa).toCharArray();
            cal += 1;
        }
        return cal;
    }

    // Метод преобразует строку в звездную стенографию.
    private static String toStarShorthand(String word) {
        int cal = 1;
        String content = "";
        word += (" ");
        char[] wordius = word.toCharArray();

        for (int i = 1; i < wordius.length; i++) {
            if (wordius[i] != wordius[i - 1]) {
                content += wordius[i - 1];
                if (cal != 1)
                    content += "*" + String.valueOf(cal);
                cal = 1;
            } else
                cal += 1;
        }
        /*
         * if (wordius[wordius.length - 2] == wordius[wordius.length - 1]) {
         * content += wordius[wordius.length - 1]
         * + "*" + String.valueOf(cal);
         * } else {
         * content += wordius[wordius.length - 1];
         * }
         */
        return content;
    }

    // Метод возвращает true, если две строки рифмуются, и false в
    // противном случае.
    private static boolean doesRhyme(String first, String second) {
        String[] endOfOne = first.split("\s");
        char[] endOne = endOfOne[endOfOne.length - 1].toLowerCase().toCharArray();
        String[] endOfTwo = second.split("\s");
        char[] endTwo = endOfTwo[endOfTwo.length - 1].toLowerCase().toCharArray();
        String alpha = "";
        String omega = "";

        for (int i = 0; i < endOne.length; i++) {
            if (String.valueOf(endOne[i]).matches("[aeiouy]"))
                alpha += endOne[i];
        }

        for (int i = 0; i < endTwo.length; i++) {
            if (String.valueOf(endTwo[i]).matches("[aeiouy]"))
                omega += endTwo[i];
        }
        return alpha.equals(omega);
    }

    /*
     * Метод принимает два целых числа и возвращает true, если
     * число повторяется три раза подряд в любом месте в num1 и то же самое число
     * повторяется два раза подряд в num2.
     */
    private static boolean trouble(int first, int second) {
        char[] fNum = (String.valueOf(first) + " s").toCharArray();
        char[] sNum = (String.valueOf(second) + " s").toCharArray();
        char checkus = "a".charAt(0);
        int cal1 = 1;
        int cal2 = 1;

        for (int i = 1; i < fNum.length; i++) {
            if (fNum[i - 1] == fNum[i]) {
                cal1 += 1;
                if (cal1 == 3) {
                    checkus = fNum[i];
                    cal1 = 1;
                }
            } else
                cal1 = 1;

        }

        for (int i = 1; i < sNum.length; i++) {
            if (sNum[i - 1] == sNum[i]) {
                cal2 += 1;
                if (cal2 == 2 && sNum[i] == checkus)
                    return true;
            }
        }
        return false;
    }

    // Методвозвращает общее количество уникальных символов
    // (книг, так сказать) между всеми парами концов книги.
    private static int countUniqueBooks(String polka, String kniga) {
        char[] shelf = polka.toCharArray();
        int cal = 0;
        int f = 1;
        int uniqueBooks = 0;

        for (int i = 0; i < shelf.length - 1; i++) {
            if (shelf[i] == kniga.charAt(0)) {
                if (cal < 2 && shelf[i] == shelf[i + 1]) {
                    f++;
                }
                if (f < 2) {
                    uniqueBooks++;
                }
            }

        }
        return uniqueBooks;
    }

}