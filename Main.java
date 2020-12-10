/*EPAM_Module5_Task9
 1. Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
 2. Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.
 3. Занести стихотворения одного автора в список. Провести сортировку по возрастанию длин строк.
 4. Задать два стека, поменять информацию местами.
 5. Определить множество на основе множества целых чисел. Создать методы для определения пересечения и объединения множеств.
 6. С использованием множества выполнить попарное суммирование произвольного конечного ряда чисел по следующим правилам:
 на первом этапе суммируются попарно рядом стоящие числа, на втором этапе суммируются результаты первого этапа и т. д.
 до тех пор, пока не останется одно число.
 7. Не используя вспомогательных объектов, переставить отрицательные элементы данного списка в конец, а положительные —
 в начало списка.
 8. Задана строка, состоящая из символов «(», «)», «[», «]», «{», «}». Проверить правильность расстановки скобок.
 Использовать стек.
 9. Задан файл с текстом на английском языке. Выделить все различные слова. Слова, отличающиеся только регистром букв,
 считать одинаковыми. Использовать класс HashSet.
 10. Задан файл с текстом на английском языке. Выделить все различные слова. Для каждого слова подсчитать частоту его
 встречаемости. Слова, отличающиеся регистром букв, считать различными. Использовать класс HashMap.*/

import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader reader;
    public static BufferedReader fileReader;
    public static BufferedWriter fileWriter;
    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        firstTask();
        secondTask();
        thirdTask();
        forthTask();
        fifthTask();
        sixthTask();
        seventhTask();
        eighthTask();
        ninthTask();
        tenthTask();
        cleanUp();
    }

    public static void firstTask() throws IOException {
        System.out.println("1. Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.");
        try {
            System.out.println("Введите путь к файлу откуда читаем:");
            fileReader = new BufferedReader(new FileReader(reader.readLine()));
            ArrayList<String> list = new ArrayList<>();
            while (fileReader.ready()) {
                list.add(fileReader.readLine());
            }
            System.out.println("Введите путь к файлу куда пишем:");
            fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
            for (int i = list.size() - 1; i >= 0; i--) {
                fileWriter.write(list.get(i) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void secondTask() {
        System.out.println("\n2. Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.");
        Stack<Integer> stack = new Stack<>(); //Стек реализует принцип «last in - first out», т.е. «последним пришёл - первым вышел».
        // Аналогия из реального мира - это стопка книг на столе (сначала берём верхнюю).
        try {
            int number = Integer.parseInt(reader.readLine());
            while (number != 0) {
                stack.add(number % 10);
                number /= 10;
            }
            for (int i : stack) {
                System.out.print(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void thirdTask() {
        System.out.println("\n3. Занести стихотворения одного автора в список. Провести сортировку по возрастанию длин строк.");
        ArrayList<String> poetry = new ArrayList<>();
        try {
            System.out.println("Введите путь к файлу где лежит стихотворение:");
            fileReader = new BufferedReader(new FileReader(reader.readLine()));
            while (fileReader.ready()) {
                poetry.add(fileReader.readLine());
            }
            Comparator<String> myComparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length(); //пишем свой компаратор. он возвращеает разницу между сторками.
                }
            };
            poetry.sort(myComparator);
            for (String s : poetry) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void forthTask() {
        System.out.println("\n4. Задать два стека, поменять информацию местами.");
        Stack<String> firstStack = new Stack<>(); //Стек реализует принцип «last in - first out», т.е. «последним пришёл - первым вышел».
        firstStack.push("One");
        firstStack.push("Two");
        firstStack.push("Three");
        System.out.println("Первый стек: " + firstStack.toString());

        Stack<String> secondStack = new Stack<>();
        secondStack.push("A");
        secondStack.push("B");
        secondStack.push("C");
        System.out.println("Второй стек: " + secondStack.toString());

        ArrayList<String> firstList = new ArrayList<>(firstStack);
        ArrayList<String> secondList = new ArrayList<>(secondStack);

        firstStack.clear();
        firstStack.addAll(secondList);

        secondStack.clear();
        secondStack.addAll(firstList);

        System.out.println("\nПервый стек содержит объекты второго: ");
        for (String s : firstStack) {
            System.out.println(s);
        }
        System.out.println("\n" +
                "Второй стек содержит объекты первого: ");
        for (String s : secondStack) {
            System.out.println(s);
        }
    }

    public static void fifthTask() {
        System.out.println("\n5. Определить множество на основе множества целых чисел. " +
                "Создать методы для определения пересечения и объединения множеств.");
        try {
            System.out.println("Вводите целые числа первого множества, для окончания наберите exit:");
            String s;
            Set<Integer> firstSet = new HashSet<>();
            while (!(s = reader.readLine()).equals("exit")) {
                firstSet.add(Integer.parseInt(s));
            }
            System.out.println("Вводите целые числа второго множества, для окончания наберите exit:");
            Set<Integer> secondSet = new HashSet<>();
            while (!(s = reader.readLine()).equals("exit")) {
                secondSet.add(Integer.parseInt(s));
            }
            System.out.print("Объединение данных множеств:");
            Set<Integer> union = new HashSet<>(firstSet);
            union.addAll(secondSet);
            System.out.println(union.toString());

            System.out.print("Пересечения данных множеств:");
            Set<Integer> intersections = new HashSet<>(firstSet);
            intersections.retainAll(secondSet);
            System.out.println(intersections.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sixthTask() {
        System.out.println("\n6. С использованием множества выполнить попарное суммирование произвольного конечного ряда " +
                "\nчисел по следующим правилам: на первом этапе суммируются попарно рядом стоящие числа, на втором этапе " +
                "\nсуммируются результаты первого этапа и т. д. до тех пор, пока не останется одно число.");
        try {
            System.out.println("Вводите целые числа, для окончания введите любую букву:");
            String s;
            //Set<Integer> set = new HashSet<>();
            ArrayList<Integer> list = new ArrayList<>();
            try {
                //while (!(s = reader.readLine()).equals("exit")) {
                while (true) {
                    s = reader.readLine();
                    list.add(Integer.parseInt(s));
                }
            } catch (NumberFormatException e) {
            } finally {
                if (list.size() == 0) {
                    System.out.println("Числа не были ввыдены");
                } else if (list.size() == 1) {
                    System.out.println(list.get(0));
                } else if (list.size() == 2) {
                    System.out.println(list.get(0) + list.get(1));
                } else {
                    int temporarySum;
                    while (list.size() > 1) {
                        temporarySum = list.get(0) + list.get(1);
                        //System.out.println("Сумма очередной пары: " + temporarySum);
                        list.remove(0);
                        list.remove(0);
                        list.add(temporarySum);
                    }
                    System.out.println("Итог суммирования: " + list.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seventhTask() {
        System.out.println("\n7. Не используя вспомогательных объектов, переставить отрицательные элементы данного списка " +
                "в конец, а положительные — в начало списка.");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(-23);
        list.add(57);
        list.add(-112);
        list.add(236);
        System.out.println("Данный список: " + list.toString());

        for (int i  = 0; i < list.size(); i++) {
            list.set(i, (list.get(i) * -1));
        }
        Collections.sort(list);
        for (int i  = 0; i < list.size(); i++) {
            list.set(i, (list.get(i) * -1));
        }
        for (int i : list) {
            System.out.println(i);
        }
    }

    public static void eighthTask() {
        System.out.println("\n8. Задана строка, состоящая из символов «(», «)», «[», «]», «{», «}». " +
                "Проверить правильность расстановки скобок. Использовать стек.");
        System.out.println("Введите строку для проверки:");
        try {
            String s = reader.readLine();
            Stack<Character> stack = new Stack<>();
            boolean correct = true;
            if (s != null) {
                for (char c : s.toCharArray()) {
                    if (c == '(' || c == '[' || c == '{') {
                        stack.push(c); //добавялет указанный элемент на вершину стэка.
                    } else if (c == ')' || c == ']' || c == '}') {
                        if (stack.empty()) {
                            correct = false;
                            System.out.println("Скобки расставлены неверно.");
                            break;
                        } else {                           //если скобки обратные и стек при этом не пустой;
                            char bracket = stack.pop();    //возвращает элемент, который находится на вершине стэка и удаляет его, принцип «last in - first out»
                            if (!(bracket == '(' && c == ')') && !(bracket == '[' && c == ']')
                                    && !(bracket == '{' && c == '}')) {
                                correct = false;
                                System.out.println("Скобки расставлены неверно.");
                                break;
                            }
                        }
                    }
                }
                if (stack.empty() && correct) {
                    System.out.println("Все верно.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ninthTask() {
        System.out.println("\n9. Задан файл с текстом на английском языке. Выделить все различные слова. " +
                "Слова, отличающиеся только регистром букв, считать одинаковыми. Использовать класс HashSet.");
        try {
            System.out.println("Введите путь к файлу откуда читаем:");
            fileReader = new BufferedReader(new FileReader(reader.readLine()));
            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }
            HashSet<String> set = new HashSet<>(Arrays.asList(stringBuilder.toString().toLowerCase().split(" ")));
            System.out.println("Список различающихся слов: ");
            for (String s : set) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tenthTask() {
        System.out.println("\n10. Задан файл с текстом на английском языке. Выделить все различные слова. " +
                "\nДля каждого слова подсчитать частоту его встречаемости. Слова, отличающиеся регистром букв, " +
                "\nсчитать различными. Использовать класс HashMap.");
        try {
            System.out.println("Введите путь к файлу откуда читаем:");
            fileReader = new BufferedReader(new FileReader(reader.readLine()));
            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }
            List<String> list = Arrays.asList(stringBuilder.toString().split(" "));
            HashMap<String, Integer> map = new HashMap<>();

            for (String s : list) {
                map.put(s, Collections.frequency(list, s));
            }
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                System.out.println("Слово: " + pair.getKey() + ", " + "кол-во повторений: " + pair.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanUp() throws IOException {
        try {
            reader.close();
            fileReader.close();
            fileWriter.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("\nNullPointerException. Видимо что-то из ридеров-райтеров " +
                    "так и не было инициализировано.");
        }
    }


}