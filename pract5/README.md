# Практическая работа №5

## Описание
Разработка Rest сервиса

В рамках данного задания необходимо разработать и развернуть Rest-сервис, обеспечивающий доступ к клиентским продуктам.

В проекте должны присутствовать:
- DAO компонент, работающий с БД
- @Service, обращающийся к DAO
- @RestController, работающий с вышеуказанным сервисом

Приложение реализуется средствами Spring WebMVC.

Доступ к данным предполагается осуществлять с использованием HikariCP как средства создания подключений и СУБД Postgresql либо h2. Необходимо определить компонент для обеспечения доступа к Connection через HikariCP, который будет использоваться DAO в процессе обращения к данным. Само DAO осуществляет доступ к данным через механизмы Java JDBC, либо Spring JdbcTemplate.

Приложение должно содержать скрипт для разворачивания тестовой базы и её наполнения. Скрипт может быть выполнен любым удобным способом, например файлом schema.sql и необходимыми настройками в application.properties \ application.yaml. Применение Docker контейнеров с постгресом приветствуется.

Все настройки должны быть вынесены в application.properties \ application.yaml и внедряться средствами Spring (@Value)

В базе данных должна быть представлена следующая сущность:

```
UserProduct(
    id: PK, bigserial
    номер_счета: varchar (255), unique
    баланс: BigDecimal
    тип_продукта: счет или карта
)
```


Разработанный контроллер должен предоставлять следующие ресурсные методы:

- `{id}/getAllProducts` : возвращает все продукты клиента с идентификатором указанным в path
- Запрос доступен по Get методу

Возможные коды ответов:
- 200, в теле JSON со списком продуктов
- 404 : клиент не найден

---

- `/getProduct?id` : возвращает продукт с id указанным в параметрах запроса
- Запрос доступен по Get методу

Возможные коды ответов:
- 200, в теле JSON с одним продуктом
- 404 : продукт не найден




