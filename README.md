# Учебное расписание (Sheduler)

<br/><br/>

## Описание страниц

<br/>

С любой страницы можно перейти на главную и на предыдущие

<br/>

**Главная страница**
1) Прежде всего авторизация
2) После авторизации становится доступной главная страница, на которой отображено расписание на сегодня (ST)
3) Переход на страницу со списком курсов
4) Переход на страницу со списком преподавателей
5) Переход на страницу со списком студентов
6) Кнопка найти свободную аудиторию
8) Составить расписание (A)

<br/>

**Страница курсов**
1) Кнопка добавить курс (TA)
2) Кнопка мои курсы (ST)
3) В конце страницы список всех курсов
4) Перед списком курсов фильтр - все/спец/обязательные, фильтр по номеру курса, фильтр по кафедре, по умолчанию все
5) Напротив спецкурса кнопка добавить(плюсик) (S)
<br/>

**Страница мои курсы**
1) Список курсов (в начале текущие, затем пройденные)
2) Кнопка выбрать спецкурс -> страница курсов с фильтром спецкурс

<br/>

**Страница курса**
1) Информация о курсе (преподаватель, аудитория, объем) 
2) Время и даты проведения
3) Напротив каждого курса - редактировать/удалить (TA)

<br/>

**Страница преподавателей**
1) Кнопка мои преподаватели (S) - актуальные преподаватели
2) Список преподавателей вуза
3) Фильтр по кафедре, курсу
   
<br/>

**Страница преподавателя**
1) Информация о преподавателе - для связи, информация о преподающихся курсах и текущем распиании(где найти?))
2) Добавить/удалить преподавателя (A) ??
   
<br/>

**Страница студентов**
1) кнопка моя группа (S)
2) Кнопка мой курс (S)
3) Список строк с последующим переходом на список студентов (ячейка для выбора курса, потока, группы) (T)
5) Список преподавателей вуза (информация для связи, информация о преподающихся курсах и текущем распиании(где найти?))
6) Фильтр по курсу, потоку, группе
8) Добавить/удалить студента(A) ??
   
<br/>

**Страница студента** (S)
1) кнопка моя группа (S) ??
2) Кнопка мой курс (S) ??
3) Личная информация (имя, группа, курс, почта, достижения)
   
<br/>

**Страница аудиторий**
1) Список аудиторий
2) Функция "найти свободную аудиторию" - возможность выбора дня/времени
<br/>

**Страница аудитории**
1) Расписание аудитории на неделю
2) Кнопка "свободна"
3) Информация о вместительности, формате(поточная, кабинет) и расположении
<br/>

## Схема базы данных
![](pic/data.png)

## Сценарии использования

**Для студента (S)**
1) Студент имеет возможность залогиниться в системе, добавив свои личные данные, в режиме "студент"
2) На главной странице будет отображено расписание сегодняшнего дня
3) В расписании можно нажать на преподавателя, курс или аудиторию и узнать подробную информацию о них
4) Можно нажать на кнопку "расписание недели"
5) Можно получить расписание на заданный интервал времени
6) Можно изменять собственные данные
7) Можно перейти на страницу "мои спецкурсы"
   
<br/>

**Для преподавателя (T)**
1) Преподаватель имеет возможность залогиниться в системе, добавив свои личные данные, в режиме "преподаватель"
2) На главной странице отображено расписание
3) В расписании можно нажать на группу/курс и открыть список учеников (с возможностью отмечания)
4) Можно нажать на кнопку "расписание недели"
5) Можно получить расписание на заданный интервал времени
6) Можно изменять собственные данные
7) Преподаватель может создать курс и изменять информацию о нем
8) Преподаватель может создать спецкурс, изменять информацию о нем, добавлять слушателей
<br/>

**Для администратора университета (A)**
1) Возможность залогиниться
2) Можно создать курс
3) Изменять информацию об аудиториях, курсах
4) Создавать ячейки в расписании и менять их

<br/>

**Общее**
1) Архив расписаний за прошлые года (выбрать год, курс, группу)
2) Получение списков студентов по потокам и группам
3) Получение списков преподавателей

<br/>

<br/>
