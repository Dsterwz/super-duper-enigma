import java.util.Arrays;

public class Task2 {
    // Класс, в котором все таски из файла task2.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("repeat"))
            System.out.println(repeat(args[1], Integer.valueOf(args[2])));

        else if (args[0].trim().equals("differenceMaxMin")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                numbers[i] = Integer.parseInt(items[i]);
            }
            System.out.println(differenceMaxMin(numbers));
        }

        else if (args[0].trim().equals("isAvgWhole")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                numbers[i] = Integer.parseInt(items[i]);
            }
            System.out.println(isAvgWhole(numbers));
        }

        else if (args[0].trim().equals("cumulativeSum")) {
            String[] items = args[1].replaceAll("\\[|\\]|\\s", "").split(",");
            int[] numbers = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                numbers[i] = Integer.parseInt(items[i]);
            }
            System.out.println(Arrays.toString(cumulativeSum(numbers)));
        }

        else if (args[0].trim().equals("getDecimalPlaces")) {
            System.out.println(getDecimalPlaces(args[1]));
        }

        else if (args[0].trim().equals("Fibonacci")) {
            System.out.println(Fibonacci(Integer.parseInt(args[1])));
        }

        else if (args[0].trim().equals("isValid")) {
            System.out.println(isValid(args[1]));
        }

        else if (args[0].trim().equals("isStrangePair")) {
            if (args.length > 1)
                System.out.println(isStrangePair(args[1], args[2]));
            else
                System.out.println(isStrangePair("", ""));
        }

        else if (args[0].trim().equals("isPrefix")) {
            System.out.println(isPrefix(args[1], args[2]));
        }

        else if (args[0].trim().equals("isSuffix")) {
            System.out.println(isSuffix("arachnophobia", "-phobia"));
            System.out.println(isSuffix("vocation", "-logy"));
        }

        else if (args[0].trim().equals("boxSeq")) {
            System.out.println(boxSeq(Integer.parseInt(args[1])));
        }

    }

    // Метод, который повторяет каждый символ в строке n раз.
    private static String repeat(String s, int n) {
        String content = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < n; j++)
                content += s.charAt(i);
        }
        return content;
    }

    // Метод, который принимает массив и возвращает разницу между
    // самыми большими и самыми маленькими числами.
    private static int differenceMaxMin(int[] arr) {
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;

        for (int c : arr) {
            if (c > maxNumber)
                maxNumber = c;
            if (c < minNumber)
                minNumber = c;
        }
        return maxNumber - minNumber;
    }

    // Метод, который принимает массив в качестве аргумента и возвращает
    // true или false в зависимости от того, является ли среднее значение всех
    // элементов массива целым числом или нет.
    private static boolean isAvgWhole(int[] arr) {
        int a = 0;

        for (int c : arr)
            a += c;
        return a % arr.length == 0;

    }

    // Метод, который берет массив целых чисел и возвращает массив, в
    // котором каждое целое число является суммой самого себя + всех предыдущих
    // чисел в массиве.
    private static int[] cumulativeSum(int[] arr) {
        int[] massiv4ik = new int[arr.length];
        int a = 0;

        for (int i = 0; i < arr.length; i++) {
            a += arr[i];
            massiv4ik[i] = a;
        }
        return massiv4ik;
    }

    // Метод, который возвращает число десятичных знаков, которое имеет
    // число (заданное в виде строки). Любые нули после десятичной точки
    // отсчитываются в сторону количества десятичных знаков.
    private static int getDecimalPlaces(String n) {
        char[] z = n.toCharArray();
        int c = 0;

        for (int i = 0; i < z.length; i++) {
            if (z[i] == ".".charAt(0)) {
                c = i;
                break;
            }
        }
        if (c == 0)
            return 0;
        else
            return z.length - (c + 1);
    }

    // Метод, который при заданном числе возвращает соответствующее
    // число Фибоначчи.
    private static int Fibonacci(int n) {
        int[] fibArray = { 0, 1 };
        int fibNum = 0;

        for (int i = 0; i < n; i++) {
            fibNum = fibArray[0] + fibArray[1];
            fibArray[0] = fibArray[1];
            fibArray[1] = fibNum;
        }
        return fibNum;
    }

    /*
     * Метод определяет почтовые индексы.
     * Действительный почтовый индекс выглядит следующим образом:
     * 
     * – Должно содержать только цифры (не допускается использование нецифровых
     * цифр).
     * 
     * – Не должно содержать никаких пробелов.
     * – Длина не должна превышать 5 цифр.
     */
    private static boolean isValid(String n) {
        return n.matches("\\d{5}");
    }

    /*
     * Метод определяет странные пары.
     * Пара строк образует странную пару, если оба из следующих условий истинны:
     * – Первая буква 1-й строки = последняя буква 2-й строки.
     * – Последняя буква 1-й строки = первая буква 2-й строки
     */
    private static boolean isStrangePair(String a, String b) {
        if (a != null && b != null &&
                (a.isEmpty() || b.isEmpty()))
            return a.equals(b);
        else
            return a.charAt(0) == b.charAt(b.length() - 1) &&
                    a.charAt(a.length() - 1) == b.charAt(0);

    }

    // Метод, который определяет префиксы в слове.
    private static boolean isPrefix(String a, String b) {
        b = b.replace("-", "");
        return a.split(b).length == 2;
    }

    // Метод, который определяет суффиксы в слове.
    private static boolean isSuffix(String a, String b) {
        b = b.replace("-", "");
        return a.endsWith(b);
    }

    // Метод, который принимает число (шаг) в качестве аргумента и
    // возвращает количество полей на этом шаге последовательности.
    private static int boxSeq(int n) {
        int a = 0;

        for (int i = 1; i <= n; i++)
            if (i % 2 != 0)
                a += 3;
            else
                a -= 1;
        return a;
    }

}