# Занятие 3 онлайн проекта <a href="https://github.com/JavaWebinar/topjava04">Topjava</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFfllmQWR2cE90UGp5RERHaE95cnVDNmZTRFJCejVhM191NDZlREwzeDdXdmc">Материалы урока</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFWTdkTEhBNGQxRDA">HW2: делаем API еды</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFbHZaNnB6X05GbG8">1_ HW2.patch</a>**

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFWTdkTEhBNGQxRDA">HW2: Optional</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFUmdMWEk0M2x2d0k">2_ HW2_ Optional.patch</a>**

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFOU8wWlpPVE05STA">Коротко о жизненном цикле Spring контекста.</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFWDZwZ3NOYXgyc1E">3_ bean_ life_ cycle.patch</a>**
-  <a href="http://habrahabr.ru/post/222579/">Spring изнутри. Этапы инициализации контекста.</a>
-  Ресурсы:
   -  <a href="http://vk.com/javawebinar?z=video-58538268_169373158%2Fvideos-58538268">Евгений Борисов. Spring, часть 1</a>
   -  <a href="http://vk.com/javawebinar?z=video-58538268_169373162%2Fvideos-58538268">Евгений Борисов. Spring, часть 2</a>
   -  <a href="http://www.slideshare.net/taemonz/spring-framework-core-23721778">Презентация Spring framework core</a>

##  <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFODlkU1B0QnNnSGs">Тестирование через JUnit.</a>
- ВНИМЕНИЕ: перед накаткой патча создайте каталог test (из корня проекта путь \src\test), иначе часть файлов попадет в src\main
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFcnpGNHEydXo5NGc">4_ Add Junit.patch</a>** 

-  Перенос mock реализации в test.
-  <a href="http://junit.org/">JUnit 4</a>
-  <a href="http://habrahabr.ru/post/120101/">Тестирование в Java. JUnit</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFai1veG9qaFZlZ2s">Spring Test</a>
-  Интеграция Spring и JUnit.
-  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#testing">Spring Testing</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFVlNYczhnSU9JdXc">Базы данных. Обзор NoSQL и Java persistence solution без ORM.</a>
-  <a href="https://ru.wikipedia.org/wiki/PostgreSQL">PostgreSQL</a>.
-  <a href="http://alexander.holbreich.org/2013/03/nosql-or-rdbms/">NoSQL or RDBMS.</a><a
                href="http://habrahabr.ru/post/77909/">Обзор NoSQL систем</a>. <a href="http://blog.nahurst.com/visual-guide-to-nosql-systems">CAP</a>
-  Обзор Java persistence solution без ORM: <a
                href="http://commons.apache.org/proper/commons-dbutils/">commons-dbutils</a>,
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html">Spring
                JdbcTemplate</a>, <a href="http://en.wikipedia.org/wiki/MyBatis">MyBatis</a>, <a href="http://www.jdbi.org/">JDBI</a>
- Ресурсы:
  - <a href="https://ru.wikipedia.org/wiki/Реляционная_СУБД">Реляционная СУБД</a>
  - <a href="http://habrahabr.ru/post/103021/">Реляционные базы</a>
  - <a href="http://ru.wikipedia.org/wiki/Java_Database_Connectivity">JDBC</a>
  - <a href="https://www.youtube.com/playlist?list=PLIU76b8Cjem5qdMQLXiIwGLTLyUHkTqi2">Обущающее видео по JDBC</a>
                
## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFQWtHYU1qTDlMWVE">Настройка Database в IDEA.</a>
-  <a href="http://habrahabr.ru/company/JetBrains/blog/204064/">Настройка Database в IDEA</a> и запуск SQL.

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFMGNWUXhaVzdlU0k">Скрипты инициализации базы. Spring Jdbc Template.</a>
-  Скрипты инициализация базы

-  Подключение <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html">Spring Jdbc</a>.
-  Конфигурирование DataSource. <a href="http://www.mkyong.com/spring/spring-propertyplaceholderconfigurer-example/">property-placeholder</a>
   (проверьте, что в контекст Spring проекта включены оба файла конфигурации: https://drive.google.com/open?id=0B9Ye2auQ_NsFYThYOFNHbnNzd0E).
-  Имплементация UserRepository через Spring Jdbc Template.

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFWXlqQW1pcl9fUlE">Подготовка тестовых данных и тестирование UserService.</a>
-  Подготовка тестовых данных в UserServiceTest. Добавление TestUser и ModelMatcher
-  Тестирование UserService.
-  Ресурсы:
   - <a href="http://www.youtube.com/watch?v=YzOTZTt-PR0">Николай Алименков — Босиком по граблям Hibernate</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFVmZaSm9UMktXUnc">Логирование тестов.</a>
   
## Домашнее задание HW03
