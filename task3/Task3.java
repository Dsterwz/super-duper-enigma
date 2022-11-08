import java.util.Arrays;

public class Task3 {
    // Класс, в котором все таски из файла task3.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("solutions"))
            System.out.println(solutions(
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3])));

        else if (args[0].trim().equals("findZip"))
            System.out.println(findZip(args[1]));

        else if (args[0].trim().equals("checkPerfect"))
            System.out.println(checkPerfect(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("flipEndChars"))
            System.out.println(flipEndChars(args[1]));

        else if (args[0].trim().equals("isValidHexCode"))
            System.out.println(isValidHexCode(args[1]));

        else if (args[0].trim().equals("same")) {
            String[] items1 = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            String[] items2 = args[2].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers1 = new int[items1.length];
            int[] numbers2 = new int[items2.length];
            for (int i = 0; i < items1.length; i++) {
                numbers1[i] = Integer.parseInt(items1[i]);
            }
            for (int i = 0; i < items2.length; i++) {
                numbers2[i] = Integer.parseInt(items2[i]);
            }
            System.out.println(same(numbers1, numbers2));
        }

        else if (args[0].trim().equals("isKaprekar"))
            System.out.println(isKaprekar(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("longestZero"))
            System.out.println(longestZero(args[1]));

        else if (args[0].trim().equals("nextPrime"))
            System.out.println(nextPrime(Integer.parseInt(args[1])));

        else if (args[0].trim().equals("rightTriangle"))
            System.out.println(rightTriangle(
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3])));

    }

    // Метод возвращает количество решений уравнения.
    private static int solutions(int a, int b, int c) {
        if (a * a - 4 * b * c > 0)
            return 2;
        else if (a * a - 4 * b * c == 0)
            return 1;
        else
            return 0;
    }

    // Метод ищет второе вхождение слова "zip".
    private static int findZip(String text) {
        char[] word = "zip".toCharArray();
        char[] charText = text.toCharArray();
        int c = 0;
        int answer = -1;

        for (int i = 0; i < text.length() - 2; i++) {
            if (charText[i] == word[0]
                    && charText[i + 1] == word[1]
                    && charText[i + 2] == word[2])
                c++;
            if (c == 2)
                answer = i + 1;
        }
        return answer;

    }

    // Метод проверяет число на совершенство.
    private static boolean checkPerfect(int n) {
        int cal = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                cal += i;
        }

        return cal == n;

    }

    /*
     * Метод принимает строку и возвращает новую строку с
     * заменой ее первого и последнего символов, за исключением трех условий:
     * – Если длина строки меньше двух, верните "несовместимо".".
     * – Если первый и последний символы совпадают, верните "два-это пара.".
     */

    private static String flipEndChars(String word) {
        if (word.length() < 2)
            return "Incompatible.";
        else {
            char[] temp = word.toCharArray();
            if (temp[0] == temp[temp.length - 1])
                return "Two's a pair.";
            else {
                char t = temp[0];
                temp[0] = temp[temp.length - 1];
                temp[temp.length - 1] = t;
                return String.valueOf(temp);

            }
        }
    }

    // Метод определяет, является ли строка допустимым шестнадцатеричным кодом.
    private static boolean isValidHexCode(String word) {
        return word.matches("^#[0-9A-Fa-f]{6}");
    }

    // Метод я возвращает true, если два массива имеют одинаковое
    // количество уникальных элементов, и false в противном случае.
    private static boolean same(int[] arr1, int[] arr2) {
        int c1 = 1;
        int c2 = 1;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length - 1; i++) {
            if (arr1[i] != arr1[i + 1])
                c1++;
        }

        for (int i = 0; i < arr2.length - 1; i++) {
            if (arr2[i] != arr2[i + 1])
                c2++;
        }
        return c1 == c2;
    }

    // Метод возвращает true, если это число Капрекара,
    // и false, если это не так.
    private static boolean isKaprekar(int n) {
        String nq = String.valueOf(n * n);

        if (n == 0 || n == 1)
            return true;

        else if (nq.length() % 2 != 0) {
            nq = "0".concat(nq);
        }

        String left = "";
        String right = "";

        for (int i = 0; i < nq.length() / 2; i++) {
            left += nq.charAt(i);
            right += nq.charAt(nq.length() / 2 + i);
        }

        return Integer.parseInt(right) + Integer.parseInt(left) == n;
    }

    // Метод возвращает самую длинную последовательность
    // последовательных нулей в двоичной строке.
    private static String longestZero(String zeros) {
        char[] charz = zeros.toCharArray();
        String z = "";
        String calmyk = "";

        for (int i = 0; i < charz.length; i++) {
            if (charz[i] == "0".charAt(0)) {
                z += "0";
                if (calmyk.length() < z.length())
                    calmyk = z;
            } else
                z = "";
        }

        return calmyk;
    }

    // Метод возвращает следующее простое число.
    // Если число простое, возвращает само число.
    private static int nextPrime(int n) {
        int primus = 2;
        int numba = 2;

        while (primus < n) {
            int c = 1;
            numba++;
            for (int i = 2; i < numba; i++) {
                if (numba % i == 0)
                    c++;
                if (c != 1)
                    break;
            }

            if (c == 1)
                primus = numba;
        }
        return primus;
    }

    // Метод определяет, являются ли они ребрами
    // прямоугольного треугольника.
    private static boolean rightTriangle(int x, int y, int z) {
        int[] edges = { x, y, z };
        Arrays.sort(edges);
        return edges[0] * edges[0] + edges[1] * edges[1] == edges[2] * edges[2];
    }

}