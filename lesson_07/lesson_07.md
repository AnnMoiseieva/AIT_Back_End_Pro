wait()...notify()
Conditional
Maven
JSON (библиотека Jackson)

//-------------------------------------------------------
Maven
Maven — это система управления проектами и инструмент автоматизации 
сборки для Java (хотя сейчас поддерживает и другие JVM-языки, 
и не только).

--помогает подключать сторонние библиотеки (зависимости)
--cтандартизует структуру проекта
--собирает проект (компиляция, упаковка jar/war/ear)

Управление конфигурацией сборки осуществляется через файл pom.xml

Для того, что бы подключить библиотеку к проекту, необходимо

1. найти ее в репозитории (например, центральный репозиторий Maven 
https://mvnrepository.com)
2. cкопировать зависимость в блок pom.xml проекта.
3. 
Например:

 <dependencies>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.18.2</version>
        </dependency>

    </dependencies>

jackson.fasterxml.com

Сериализация и десериализация
В контексте JSON:
Сериализацией или маршалингом — мы будем называть процесс, 
преобразования объекта в JSON
Дсериализацией или демаршалингом — мы будем называть процесс 
преобразования JSON в объект

Для работы с JSON существует очень много библиотек (см. тут )
Одной из самых популярных является FasterXML Jackson.

Объект в JSON (сериализация) и JSON в объект (десериализация)
Для конвертации объекта в JSON и наоборот, JSON в объект используем ObjectMapper

//////////////////////////////////////////////////////////////
        // Создали объект ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

/*  Object -> JSON  */
// получить строку-JSON из объекта
String strJson = mapper.writeValueAsString(person1);
// получить файл-JSON из объекта
mapper.writeValue(new File("person.json"),person1);

/*  JSON -> Object  */
// получить объект из JSON строки
Person p1 = mapper.readValue(strJson, Person.class);
// получить объект из JSON файла
Person p2 = mapper.readValue(new File("person.json"), Person.class);
////////////////////////////////////////////////////////////////

ВАЖНО!! Для выгрузки у класса должны быть геттеры 
(см. Сериализация: приватные поля), для загрузки - 
должен быть конструктор без параметров. 
И то и другое поведение можно изменить (см. ниже)