# diplom

## Начало работы
### Необходимые инструменты
Для развертывания и разработки проекта вам понадобятся следующие инструменты:
#### 1. **Java 21 или выше**

Для Linux (Ubuntu)  использовать команду:

```
sudo apt update
sudo apt install openjdk-21-jdk
```

Проверить, что Java установлена корректно:

```
java -version
```

#### 2. Gradle
Установка Gradle на Linux (Ubuntu):

```
sudo apt update
sudo apt install gradle
```

#### 3. PostgreSQL
Установка PostgreSQL на Linux (Ubuntu):

```
sudo apt update
sudo apt install postgresql postgresql-contrib
```
Запуск PostgreSQL:

```
sudo service postgresql start
```

### Развёртывание приложения

## Linux

#### 1. Клонируйте репозиторий

```bash
git clone git@github.com:Hexlet-diplom/diplom.git
cd diplom
```

#### 2. Настройка базы данных
Откройте терминал и выполните команду для подключения к PostgreSQL:

```
psql -U postgres
```
После подключения введите следующие команды для создания нового пользователя и базы данных.

Создание пользователя postgres с паролем 123 (Для примера я использовал его, можете создать своего пользователя и бд, а после указать свои значения в файле application-dev.yml и application-dev.yml)
```
CREATE USER postgres WITH PASSWORD '123';
```

Создание базы данных ezyskills и привязка её к пользователю postgres
```
CREATE DATABASE ezyskills WITH OWNER postgres;
```

Дайте пользователю все права на базу данных ezyskills
```
GRANT ALL PRIVILEGES ON DATABASE ezyskills TO postgres;
```
#### 3. Проверка

Подключитесь к базе данных ezyskills от имени пользователя postgres или воспользуйтесь PgAdmin для регистрации БД:

```
\c ezyskills postgres
```
Проверьте, что база данных существует:

```
\l
```
Вы должны увидеть базу данных ezyskills в списке.

#### 4. Сборка проекта

Для сборки проекта используйте терминал из корневой директории проекта:

```
./gradlew build
```

#### 5. Запуск приложения

После сборки проекта вы можете запустить приложение с помощью следующей команды:

```
./gradlew bootRun
```

Приложение запустится, и вы сможете получить доступ к нему по адресу http://localhost:8080

## Windows

### 1. Скачать установщик Git

Перейди на официальный сайт: https://git-scm.com/download/win

#### Установить Git
Запусти скачанный .exe файл.
На каждом шаге просто жми "Next", если не уверен — оставляй всё по умолчанию.

Открой PowerShell или cmd и введи:

```git --version```

Если всё установилось, ты увидишь, например:

```git version 2.44.0.windows.1```

нужно задать свои данные:

```
git config --global user.name "Твое Имя"
git config --global user.email "you@example.com"
```

чтобы настроить SSH-ключи для GitHub воспользуйся памяткой

### Установка JDK
Скачай и установи JDK с официального сайта: https://www.oracle.com/java/technologies/downloads/#jdk21-windows

в параметрах выбери JDK 21 установщик для Windows: x64 Installer.
Запусти скачанный .exe файл.

после установки, появится папка по пути: C:\Program Files\Java\jdk-21\

#### Настройка переменных сред

1. Открой меню "Пуск", найди "Переменные среды"
2. Нажми  кнопку "Переменные среды"
3. Вверху, в "Переменные пользователя" нажми Создать:

Имя переменной: JAVA_HOME

Значение: путь до папки JDK:

C:\Program Files\Java\jdk-21

Добавь Java в PATH

В тех же "Переменные среды":

Найди переменную Path в списке переменных пользователя.

Нажми Изменить...

Нажми Создать и добавь:

%JAVA_HOME%\bin

Проверь открой PowerShell, затем проверь:
```
java -version
javac -version
echo $env:JAVA_HOME
```
Если всё настроено — должно показать версию Java и путь.

### Установка Gradle

Перейди на сайт: https://gradle.org/releases/

Скачай последнюю версию binary-only. Скачанный файл будет иметь расширение .zip.

Создай новый каталог C:\Gradle. Перетащи папку с содержимым gradle-8.13 из архива в недавно созданную папку C:\Gradle.

### Установка PostgreSQL

Перейди на сайт: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Выбираем версию 17.4 установщик Windows x86-64

Запускаем exe файл и следуем инструкциям. Установка должна установить PostgreSQL и pgAdmin4.

При регистрации надо будет указать пользователя и пароль (я оставил postgres и 123), но вы можете указать свой и внести изменения в конфигурационном файле application.yml и application-dev.yml в параметрах datasource.


###  Клонируйте репозиторий

```
git clone git@github.com:Hexlet-diplom/diplom.git
cd diplom
```

### Настройка базы данных

1. Открой меню "Пуск", найди "SQL Shell" и запусти
2. Подключайтесь к базе данных с помощью сервер localhost, порт 5432 и указываем пароль от пользователя
3. Создание базы данных ezyskills
```
CREATE DATABASE ezyskills;
```
4. Подключитесь к базе данных ezyskills от имени пользователя postgres или воспользуйтесь PgAdmin для регистрации БД:

```
\c ezyskills postgres
```
Проверьте, что база данных существует:

```
\l
```
Вы должны увидеть базу данных ezyskills в списке.

### Сборка проекта

В visual studio  для сборки проекта используйте терминал powershell из корневой директории проекта ,но перед этим следует немного подождать чтобы студия могла идентифицировать язык java и сборщик проекта:

```
./gradlew build
```

#### 5. Запуск приложения

После сборки проекта вы можете запустить приложение с помощью следующей команды:

```
./gradlew bootRun
```

Приложение запустится, и вы сможете получить доступ к нему по адресу http://localhost:8080
