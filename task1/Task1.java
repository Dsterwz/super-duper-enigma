import java.lang.Math;

public class Task1 {
    // Класс, в котором все таски из файла task1.pdf

    public static void main(String[] args) {

        if (args[0].trim().equals("remainder"))
            System.out.println(remainder(args[1], args[2]));

        else if (args[0].trim().equals("triArea"))
            System.out.println(triArea(args[1], args[2]));

        else if (args[0].trim().equals("animals"))
            System.out.println(animals(args[1], args[2], args[3]));

        else if (args[0].trim().equals("profitableGamble"))
            System.out.println(profitableGamble(args[1], args[2], args[3]));

        else if (args[0].trim().equals("operation"))
            System.out.println(operation(args[1], args[2], args[3]));

        else if (args[0].trim().equals("ctoa"))
            System.out.println(ctoa(args[1]));

        else if (args[0].trim().equals("addUpTo"))
            System.out.println(addUpTo(args[1]));

        else if (args[0].trim().equals("nextEdge"))
            System.out.println(nextEdge(args[1], args[2]));

        else if (args[0].trim().equals("sumOfCubes"))
            System.out.println(sumOfCubes(args[1]));

        else if (args[0].trim().equals("abcmath"))
            System.out.println(abcmath(args[1], args[2], args[3]));
    }

    // Метод, вычислящий остаток от деления.
    private static double remainder(String a, String b) {
        return Double.valueOf(a) % Double.valueOf(b);
    }

    // Метод, вычислящий площадь треугольника по основанию и высоте.
    private static double triArea(String a, String h) {
        return Double.valueOf(a) * Double.valueOf(h) / 2;
    }

    // Метод, вычислящий кол-во ног животных.
    private static int animals(String chickens,
            String cows, String pigs) {
        return Integer.valueOf(chickens) * 2 +
                (Integer.valueOf(cows) + Integer.valueOf(pigs)) * 4;
    }

    // Метод, вычислящий стоит ли игра свеч.
    private static boolean profitableGamble(String prob,
            String prize, String pay) {
        return (Double.valueOf(prob) * Double.valueOf(prize) > Double.valueOf(pay));

    }

    // Метод, распознающий простые арифметические операции.
    private static String operation(String N, String a, String b) {
        if (Integer.valueOf(a) + Integer.valueOf(b) == Integer.valueOf(N))
            return "added";
        else if (Integer.valueOf(a) - Integer.valueOf(b) == Integer.valueOf(N))
            return "subtracted";
        else if (Integer.valueOf(a) * Integer.valueOf(b) == Integer.valueOf(N))
            return "multiplied";
        else if (Double.valueOf(a) / Integer.valueOf(b) == Double.valueOf(N))
            return "divided";
        else
            return "none";
    }

    // Метод, возвращающий ASCII-код символа.
    private static int ctoa(String symbol) {
        char c = symbol.charAt(0);
        return (int) c;
    }

    // Метод вычисляет сумму арфметической прогресии.
    private static String addUpTo(String c) {
        String content = "";
        int sum = 0;
        for (int i = 1; i < Integer.valueOf(c); i++) {
            content += String.valueOf(i) + " + ";
            sum += i;
        }
        sum += Integer.valueOf(c);
        content += c + " = " + String.valueOf(sum);
        return content;
    }

    // Метод вычисляет третью сторону треугольинка по теореме косинусов.
    private static int nextEdge(String a, String b) {
        return (int) Math.sqrt(Math.pow(Integer.valueOf(a), 2) +
                Math.pow(Integer.valueOf(b), 2) - 2 * Integer.valueOf(a)
                        * Integer.valueOf(b) * Math.cos(179));
    }

    // Метод вычисляет сумму кубов всех элементов массива чисел.
    private static int sumOfCubes(String array) {
        String[] items = array.replaceAll("\\[|\\]|\\s", "").split(",");
        int c = 0;
        for (int i = 0; i < items.length; i++)
            c += Math.pow(Integer.valueOf(items[i]), 3);
        return c;
    }

    // Метод кринжа.
    private static boolean abcmath(String a, String b, String c) {
        int accum = 0;
        for (int i = 0; i < Integer.valueOf(b); i++)
            accum = Integer.valueOf(a) * 2;
        if (accum % Integer.valueOf(c) == 0)
            return true;
        else
            return false;
    }
}