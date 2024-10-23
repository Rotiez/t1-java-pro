package edu.t1;

import edu.t1.data.Data;
import edu.t1.data.Employee;
import edu.t1.data.JobTitle;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // Список рандомных сотрудников
        List<Employee> employees = Stream.generate(Data::getRandomEmployee)
                .limit(30)
                .toList();
        System.out.println("Исходный список сотрудников: ");
        employees.forEach(System.out::println);
        System.out.println();

        // Список рандомных чисел
        List<Integer> integers = IntStream.generate(Data::getRandomInteger)
                .limit(20)
                .boxed()
                .toList();
        System.out.println("Исходный список чисел: " + integers + "\n");

        Solution.printDistinct(integers);
        Solution.printThirdMax(integers);
        Solution.printThirdMaxDistinct(integers);

        Solution.printByJob(employees, JobTitle.ENGINEER);
        Solution.printOldestByJobWithLimit(employees, JobTitle.ENGINEER, 3);
        Solution.printAvgAgeByJob(employees, JobTitle.ENGINEER);

        Solution.printLongestWord(Data.firstNames);
        Solution.printCountedWordsMap(employees);
        Solution.printSortedWords(employees);
        Solution.printLongestWordFromArray(
            new String[] {
                "яблоко банан апельсин вишня малина",
                "огурец помидор картошка сыр хлеб",
                "творог рис молоко яйцо баклажан",
                "арбуз дыня персик груша слива",
                "виноград манго капуста шпинат укроп"
            }
        );
    }
}