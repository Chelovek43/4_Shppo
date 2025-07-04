Nutrition Calculator (Рацион питания)

**Проект:** Консольное приложение на Java для расчёта рациона (БЖУ) с поддержкой справочника продуктов и гибкими настройками режимов питания. 

Функционал
Расчёт норм БЖУ на основе характеристик человека (вес, рост, активность).

Справочник продуктов (растительные/животные) с составом (белки, жиры, углеводы).

Комбинированные режимы питания с циклами разной длительности.


**Технологии и паттерны**

**Лабораторная 1**

Порождающие паттерны:

- Фабричный метод — создание продуктов/режимов питания.
- Строитель (Builder) — гибкая настройка рациона.

Структурные паттерны:

- Компоновщик (Composite) — иерархия продуктов/режимов.
- Декоратор (Decorator) — динамическое добавление характеристик (например, витамины).

Поведенческие паттерны:

- Стратегия (Strategy) — выбор алгоритма расчёта БЖУ.
- Наблюдатель (Observer) — уведомления о изменениях в рационе.



**Лабораторная 2 (Spring)**
DI/IoC:

Spring Beans для фабрик (@Component, @Service).
@Autowired для внедрения зависимостей.

AOP: Логирование методов Spring-бинов через @Aspect.

Конфигурация: @Configuration, @Scope (синглтоны/прототипы).


**Лабораторная 3 (Многопоточность)**
Модель консультации диетолога и клиента:

Threads — диетологи и клиенты как отдельные потоки.
synchronized, wait, notify — управление доступом к ресурсам (очередь консультаций).
Решение задачи «Спящие брадобреи»: Клиенты ждут, пока диетолог освободится.


**Для запуска:** Java 17+, Maven/Gradle, Spring Framework.
