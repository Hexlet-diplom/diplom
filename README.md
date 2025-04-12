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

Для сборки проекта используйте Gradle из корневой директории проекта:

```
./gradlew build
```

#### 5. Запуск приложения

После сборки проекта вы можете запустить приложение с помощью следующей команды:

```
./gradlew bootRun
```

Приложение запустится, и вы сможете получить доступ к нему по адресу http://localhost:8080