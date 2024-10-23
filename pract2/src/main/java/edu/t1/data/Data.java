package edu.t1.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Data {

    private static final Random random = new Random();

    public static String getRandomName() {
        return "%s %s %s".formatted(
                lastNames.get(random.nextInt(lastNames.size())),
                firstNames.get(random.nextInt(firstNames.size())),
                middleNames.get(random.nextInt(middleNames.size()))
        );
    }

    public static JobTitle getRandomJobTitle() {
        JobTitle[] jobTitles = JobTitle.values();
        return jobTitles[random.nextInt(jobTitles.length)];
    }

    public static Employee getRandomEmployee() {
        return new Employee(
                getRandomName(),
                LocalDate.of(
                        random.nextInt(40) + 1960,
                        random.nextInt(12) + 1,
                        random.nextInt(27) + 1
                ),
                getRandomJobTitle()
        );
    }

    public static Integer getRandomInteger() {
        return random.nextInt(10);
    }

    public static final List<String> firstNames = List.of(
            "Алексей",
            "Артём",
            "Вадим",
            "Владимир",
            "Валентин",
            "Данил",
            "Денис",
            "Дмитрий",
            "Егор",
            "Кирилл",
            "Леонид",
            "Максим",
            "Матвей",
            "Никита",
            "Олег",
            "Павел",
            "Пётр",
            "Роман",
            "Сергей",
            "Станислав"
    );

    private static final List<String> middleNames = List.of(
            "Александрович",
            "Алексеевич",
            "Анатольевич",
            "Андреевич",
            "Антонович",
            "Аркадьевич",
            "Борисович",
            "Валентинович",
            "Валериевич",
            "Васильевич",
            "Викторович",
            "Владимирович",
            "Вячеславович",
            "Геннадиевич",
            "Георгиевич",
            "Григорьевич",
            "Данилович",
            "Дмитриевич",
            "Евгеньевич",
            "Егорович",
            "Иванович",
            "Игоревич",
            "Ильич",
            "Кириллович",
            "Константинович",
            "Леонидович",
            "Львович",
            "Максимович",
            "Михайлович",
            "Николаевич",
            "Олегович",
            "Павлович",
            "Петрович",
            "Романович",
            "Семенович",
            "Сергеевич",
            "Станиславович",
            "Степанович"
    );

    private static final List<String> lastNames = List.of(
            "Иванов",
            "Смирнов",
            "Кузнецов",
            "Попов",
            "Васильев",
            "Петров",
            "Соколов",
            "Михайлов",
            "Новиков",
            "Фёдоров",
            "Морозов",
            "Волков",
            "Алексеев",
            "Лебедев",
            "Семёнов",
            "Егоров",
            "Павлов",
            "Козлов",
            "Степанов",
            "Николаев"
    );
}
