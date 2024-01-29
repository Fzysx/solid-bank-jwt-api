# Bank App Repository

Bank App Repository - это пример простого банковского приложения на основе Spring Boot.
Используется cборка Spring приложения через авто-конфигурацию. Для скрытия очевидных операций и
для улучшения читабельности кода использован lombok.
Для работы с данными используется:
- СУБД h2
- Spring Boot JDBC
- Spring Boot JPA
- Миграции БД с flywaydb

## Требования

- Java 17
- Maven 3.8.x

## Запуск проекта

1. **Клонировать репозиторий:**
    ```bash
    git clone https://github.com/singularity-camp/backend-3-solid-bank-app-boot-Fzysx.git
    ```

2. **Перейти в каталог проекта:**
    ```bash
    cd backend-3-solid-bank-app-boot-Fzysx
    ```
3. **Перейти на ветку feature/repository**
   ```bash
    git checkout feature/repository
    ```
4. **Запуск приложения:**
    ```bash
    mvn spring-boot:run
    ```

## Использование

- В приложении реализованы базовые банковские операции, такие как создание аккаунта, депозит и снятие денег. Вы можете использовать консольный интерфейс для выполнения этих операций.
