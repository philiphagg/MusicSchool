-- second query of task 3
create view average_of_lessons_per_year as
select *
from (
         select extract(year from l.start_time) as month,
                count(distinct l.id) / 12.0     as "number of lessons"
         from lesson as l
         group by extract(year from l.start_time)
     ) as "lessons"
         full outer join (
    select extract(year from l.start_time) as month,
           count(distinct l.id) / 12.0     as "ave(12m)individual lessons"
    from individual_lesson as l
    group by extract(year from l.start_time)
) as individual_lesson
                         using (month)
         full outer join (
    select extract(year from gl.start_time) as month,
           count(distinct gl.id) / 12.0     as "ave(12m)group lesson"
    from group_lesson as gl
    group by extract(year from gl.start_time)
) as group_lesson
                         using (month)
         full outer join (
    select extract(year from en.start_time) as month,
           count(distinct en.id) / 12.0     as "ave(12m)ensembles"
    from ensemble as en
    group by extract(year from en.start_time)
) as ensemble
                         using (month);



-- first query of task 3
create view number_of_lessons_per_month_2022 as
WITH year_as_constraint (y) as (
    values (2022)
)

select *
from (
         select extract(month from l.start_time) as month,
                count(distinct l.id)             as "number of lessons"
         from lesson as l,
              year_as_constraint --comma is an cross join
         where extract(year from l.start_time) = y
         group by extract(month from l.start_time)
     ) as lessons
         full outer join (
    select extract(month from l.start_time) as month,
           count(distinct l.id)             as "number of individual lessons"
    from individual_lesson as l,
         year_as_constraint --comma is an cross join
    where extract(year from l.start_time) = y
    group by extract(month from l.start_time)
) as individual_lesson
                         using (month)
         full outer join (
    select extract(month from gl.start_time) as month,
           count(distinct gl.id)             as "number of group lesson"
    from group_lesson as gl,
         year_as_constraint --comma is an cross join
    where extract(year from gl.start_time) = y
    group by extract(month from gl.start_time)
) as group_lesson
                         using (month)
         full outer join (
    select extract(month from en.start_time) as month,
           count(distinct en.id)             as "number of ensembles"
    from ensemble as en,
         year_as_constraint --comma is an cross join
    where extract(year from en.start_time) = y
    group by extract(month from en.start_time)
) as ensemble
                         using (month);



-- first query of task 3
create view number_of_lessons_per_month_current_year as
WITH year_as_constraint (y) as (
    values (date_part('year', CURRENT_DATE))
)

select *
from (
         select extract(month from l.start_time) as month,
                count(distinct l.id)             as "number of lessons"
         from lesson as l,
              year_as_constraint --comma is an cross join
         where extract(year from l.start_time) = y
         group by extract(month from l.start_time)
     ) as lessons
         full outer join (
    select extract(month from l.start_time) as month,
           count(distinct l.id)             as "number of individual lessons"
    from individual_lesson as l,
         year_as_constraint --comma is an cross join
    where extract(year from l.start_time) = y
    group by extract(month from l.start_time)
) as individual_lesson
                         using (month)
         full outer join (
    select extract(month from gl.start_time) as month,
           count(distinct gl.id)             as "number of group lesson"
    from group_lesson as gl,
         year_as_constraint --comma is an cross join
    where extract(year from gl.start_time) = y
    group by extract(month from gl.start_time)
) as group_lesson
                         using (month)
         full outer join (
    select extract(month from en.start_time) as month,
           count(distinct en.id)             as "number of ensembles"
    from ensemble as en,
         year_as_constraint --comma is an cross join
    where extract(year from en.start_time) = y
    group by extract(month from en.start_time)
) as ensemble
                         using (month);


create materialized view work_load_instructors as
select count(distinct (instructor_id)) as instructor_id,
       pd.first_name,
       pd.last_name,
       count(distinct (l.id))          as "number of lessons"
from lesson as l
         join instructor on l.instructor_id = instructor.id
         join personal_details pd on instructor.personal_details_id = pd.id
where extract(month from l.start_time) = date_part('month', CURRENT_DATE)
group by instructor, pd.first_name, pd.last_name
having count(distinct (l.id)) > 1;


create materialized view ensembles_next_week as
select en.genre,
       en.start_time,
       en.max_capacity,
       (15 - count(*)) as "seats left",
       case
           when en.availability = '0' then 'Ensemble is full'
           when en.availability = '1' then 'Bookable class'
           end         as availability,
       case
           when (15 - count(booking.student_id)) < 3 then 'few seats left'
           when (15 - count(booking.student_id)) > 2 then 'a lot of seats left'
           end         as test
from ensemble as en
         join booking on en.id = lesson_id
where extract(week from start_time) = date_part('week', CURRENT_DATE) + 1
group by en.genre, en.start_time, en.max_capacity, en.availability
order by start_time, genre;