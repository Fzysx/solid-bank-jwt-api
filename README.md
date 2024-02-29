# Bank App JWT

Bank App JWT - это REST API приложение на основе Spring Boot. Реализована аутентификация и авторизация 
пользователей вместе с функционалом по работе со счетами.
Используется cборка Spring приложения через авто-конфигурацию. Для скрытия очевидных операций и
для улучшения читабельности кода использован lombok.
Для работы с данными используется:
- СУБД h2
- Spring Boot JDBC
- Spring Boot JPA
- Миграции БД с flywaydb

Операции с пользователями:

- Регистрация пользователя
- Аутентификация пользователя
  
При успешной аутентификации, пользователь должен получить JWT токен, который служит идентификатором для сессии.


К основным операциям, выполняемыми пользователем со счетами относятся:

- Получение информации о всех счетах
- Создание счета
- Пополнение счета (debit)
- Снятие денег со счета (withdraw)
- Получение списка всех операций по счету
- Перевод на счет пользователя (transfer)

При успешной аутентификации, пользователь должен получить JWT токен, который служит идентификатором для сессии.

## Документация
Основныe endpoints:

| Метод   | Путь                              | Описание                            |
|---------|-----------------------------------|------------------------------------|
| GET     | /accounts                         | Получение списка счетов            |
| POST    | /accounts                         | Создание нового счета              |
| GET     | /accounts/{account_id}            | Получение информации об одном счете|
| DELETE  | /accounts/{account_id}            | Удаление счета                     |
| POST    | /accounts/{account_id}/withdraw   | Снятие денег со счета              |
| POST    | /accounts/{account_id}/deposit    | Внесение денег на счет             |
| POST    | /accounts/{account_id}/transfer   | Отправка средств на другой счет (принимает в `body` - `destination_account_id`)           |
| GET     | /accounts/{account_id}/transactions | Получение списка всех транзакций   |
| POST     | /authenticate                     | Аутентификация пользователя   |
| POST     | /registration                     | Регистрация пользователя   |


Документация находится в Swagger после запуска приложения:
http://localhost:8080/swagger-ui/index.html

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
    git checkout feature/jwt
    ```
4. **Запуск приложения:**
    ```bash
    mvn spring-boot:run
    ```

