# Описание core модулей
## Модуль const
Модуль содержащий константы используемые по всему приложению.
## Модуль coroutines
Модуль содержащий все функции/расширения для работы с корутинами и флоу. Также здесь хранится
интерфейс для предоставления диспатчеров по всему проекту и корутин скоуп, который живет в рамках жизни
всего приложения.
## Модуль db
Модуль, который содержит базу данных.
### Модуль api
Модуль, который содержит интерфейсы для абстрации над dao.
### Модуль impl
Модуль, который содержит инстанс базы данных и все entity/dao для работы с ней.
## Модуль mvi
Модуль предоставляющий все классы для создания обертки фичи по паттерну MVI.
## Модуль navigation
Модуль предоставляющий скрины, которые могут использоваться по всему проекту. Функции/расширения для 
работы с навигацией.
## Модуль network
Модуль содержащий адаптеры, интерсептеры, класс result для работы с сервером.
## Модуль stdlib
Модуль предоставляющий остальным функции для упрощения работы с языком котлин
## Модуль uikit
Модуль предоставляющий ui компоненты для приложения и остальные ресурсы.
## Модуль view
Модель содержащий функции и классы для упрощения работы с ui частью android-а.