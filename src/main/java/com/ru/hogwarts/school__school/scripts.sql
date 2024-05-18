
select * from student;

select * from faculty;

select * from student,faculty

         where student.faculty_id = faculty.id

           and faculty.name = 'Гриффиндор';

select * from faculty, student

         where student.faculty_id = faculty.id

           and student.name = 'Малфой';

