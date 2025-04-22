# diplom

## Развертывание проекта на Linux

#### Установка git (если не установлен)
```
sudo apt install git
```
Проверяем что git установлен
```
git --version
```

Настраиваем ключ доступа

```
git config --global user.name "Your name"
git config --global user.email "Your email"

shh-keygen -t rsa -b 4096 "Your email"

```
Копируем полученный код начиная с ssh-rsa и на сайте github в параметре settings/SSH and GPG keys нажимайте кнопку "New SSH key", называйте поле title как хотите и в поле ниже вставляйте скопированный ключ, сохраняем.

Проверка работы

```
ssh -T git@github.com
```
Должно появиться что-то по типу "Hi username! You've successfully authenticated...."

#### 1. **Java 21**

Для Linux (Ubuntu) использовать команду:

```
sudo apt update
sudo apt install openjdk-21-jdk
```

Проверить, что Java установлена корректно:

```
java -version
```

Настраиваем переменную JAVA_HOME, узнаем адрес установки файла:
```
sudo update-alternatives --config java
```
Команда покажет адрес папки языка

Открываем профиль shell (это может быть ~/.bashrc для bash терминала или ~/.zshrc для Zsh, проверить можно командой: ps -p $$) c помощью nano или vim
и добавляем необходимый код

```
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd (Путь до папки java-21-openjdk)
export PATH=$JAVA_HOME/bin:$PATH
```

Выполняем перезагрузку терминала
```
source ~/.zshrc (или ./.bashrc)
```
Проверяем переменную

```
echo $JAVA_HOME
```
должен появиться путь до папки openjdk

#### 2. Gradle
Установка Gradle на Linux (Ubuntu):

Перейди на сайт: https://gradle.org/releases/

Скачай последнюю версию binary-only. Скачанный файл будет иметь расширение .zip.

Из корневой директории ОС (/) выполнить
```
mkdir /opt/gradle
```
Перейти в Downloads
```
unzip -d /opt/gradle gradle-8.13-bin.zip
ls /opt/gradle/gradle-8.13
```

Конфигурация переменной среды PATH добавить в .zshrc или .bashrc:

```
export GRADLE_HOME=/opt/gradle/gradle-8.13
export PATH=$GRADLE_HOME/bin:$PATH
```

Проверка что всё работает

```
gradle -v
```
появится Gradle 8.13

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
sudo -u postgres psql
```
После подключения введите следующие команды для создания пароля для пользователя и базу данных.

Изменение пароля для пользователя postgres на 123 (Для примера я использовал его, можете создать своего пользователя и бд, а после указать свои значения в файле application-dev.yml и application-dev.yml)
```
ALTER USER postgres WITH PASSWORD '123';
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

## Развертывание проекта на Windows

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

чтобы настроить SSH-ключи для GitHub воспользуйся памяткой выше

### Установка JDK
Скачай и установи JDK с официального сайта: https://www.oracle.com/java/technologies/downloads/#jdk21-windows

в параметрах выбери JDK 21 (не JDK 24 обратите внимание!!!) установщик для Windows: x64 Installer.
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
