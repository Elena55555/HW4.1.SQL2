--liquibase formatted sql
--changeset elena_kastalskaya:1
CREATE index student_name_index on student (name);