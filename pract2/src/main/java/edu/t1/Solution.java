package edu.t1;

import edu.t1.data.Employee;
import edu.t1.data.JobTitle;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution {

    /**
     * Задача: Реализуйте удаление из листа всех дубликатов
     */
    public static void printDistinct(List<Integer> integers) {
        List<Integer> distinctIntegers = integers.stream().distinct().toList();
        System.out.println("Уникальный список: " + distinctIntegers + "\n");
    }

    /**
     * Задача: Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
     */
    public static void printThirdMax(List<Integer> integers) {
        Integer thirdMax = integers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .skip(2)
                .findAny()
                .orElseThrow();
        System.out.println("3-е наибольшее число: " + thirdMax + "\n");
    }

    /**
     * Задача: Найдите в списке целых чисел 3-е наибольшее «уникальное» число
     * (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
     */
    public static void printThirdMaxDistinct(List<Integer> integers) {
        Integer thirdMaxDistinct = integers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .skip(2)
                .findAny()
                .orElseThrow();
        System.out.println("3-е наибольшее уникальное число: " + thirdMaxDistinct + "\n");
    }

    public static void printByJob(List<Employee> employees, JobTitle jobTitle) {
        List<Employee> engineers = employees.stream()
                .filter(employee -> jobTitle.equals(employee.jobTitle()))
                .sorted(Comparator.comparing(Employee::dateOfBirth))
                .toList();
        System.out.println("Все сотрудники с должностью '" + jobTitle.getJobTitleName() + ": ");
        engineers.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Задача: Имеется список объектов типа Сотрудник (имя, возраст, должность),
     * необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
     * @param employees список сотрудников
     * @param jobTitle должность
     * @param limit требуемая длина списка
     */
    public static void printOldestByJobWithLimit(List<Employee> employees, JobTitle jobTitle, Integer limit) {
        List<Employee> agedEngineers = employees.stream()
                .filter(employee -> jobTitle.equals(employee.jobTitle()))
                .sorted(Comparator.comparing(Employee::dateOfBirth))
                .limit(limit)
                .toList();
        System.out.println(limit + " старших сотрудника должности '" + jobTitle.getJobTitleName()
                + "': " + agedEngineers + "\n");
    }

    /**
     * Задача: Имеется список объектов типа Сотрудник (имя, возраст, должность),
     * посчитайте средний возраст сотрудников с должностью «Инженер»
     * @param employees список сотрудников
     * @param jobTitle должность
     */
    public static void printAvgAgeByJob(List<Employee> employees, JobTitle jobTitle) {
        double engineersAverageAge = employees.stream()
                .filter(employee -> jobTitle.equals(employee.jobTitle()))
                .map(employee -> Period.between(employee.dateOfBirth(), LocalDate.now()).get(ChronoUnit.YEARS))
                .mapToLong(Long::longValue)
                .average()
                .orElseThrow();
        System.out.println("Средний возраст сотрудников должности '" + jobTitle.getJobTitleName()
                + "': " + (int) engineersAverageAge + "\n");
    }

    /**
     * Задача: Найдите в списке слов самое длинное
     * @param list список слов
     */
    public static void printLongestWord(List<String> list) {
        String longestName = list.stream()
                .max(Comparator.comparing(String::length))
                .orElseThrow();
        System.out.println("Самое длинное слово: " + longestName + "\n");
    }

    private static Map<String, Integer> countWords(List<Employee> list) {
        String stringOfWords = String.join(" ", list.stream()
                .map(employee -> employee.name().toLowerCase())
                .toList());
        String[] words = stringOfWords.split(" ");
        Map<String, Integer> wordsCount = new HashMap<>();
        Arrays.stream(words).forEach(word -> wordsCount.merge(word, 1, Integer::sum));
        System.out.println("Строка имен сотрудников в нижнем регистре: " + stringOfWords);
        return wordsCount;
    }

    /**
     * Задача: Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
     * Постройте хеш-мапы, в которой будут храниться пары: слово - сколько раз оно встречается во входной строке
     * @param list список сотрудников
     */
    public static void printCountedWordsMap(List<Employee> list) {
        Map<String, Integer> wordsCount = countWords(list);
        System.out.println("Количество имен: " + wordsCount + "\n");
    }

    /**
     * Задача: Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
     * если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
     * @param list список сотрудников
     */
    public static void printSortedWords(List<Employee> list) {
        Map<String, Integer> wordsCount = countWords(list);
        List<String> sortedWords = wordsCount.keySet()
                .stream()
                .sorted((s1, s2) -> {
                    if (s1.length() == s2.length()) {
                        return s1.compareTo(s2);
                    }
                    return s1.length() - s2.length();
                })
                .toList();

        System.out.println("Отсортированные имена: " + sortedWords);
        System.out.println();
    }

    /**
     * Задача: Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом,
     * найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
     * @param wordsArray массив строк
     */
    public static void printLongestWordFromArray(String[] wordsArray) {
        System.out.println("Исходный массив строк: " + Arrays.toString(wordsArray));
        String someLongestWord = Arrays.stream(wordsArray)
                .map(s -> Arrays.stream(s.split(" ")).toList())
                .flatMap(List::stream)
                .sorted(Comparator.comparing(String::length).reversed())
                .findAny()
                .orElseThrow();
        System.out.println("Самое длинное слово: " + someLongestWord);
    }
}
