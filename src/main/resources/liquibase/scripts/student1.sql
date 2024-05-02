--liquibase formatted sql
--changeset ekastalskaya:1
CREATE index student_name_index on student(name);
CREATE index faculty_name_color_index  on faculty(name,color);