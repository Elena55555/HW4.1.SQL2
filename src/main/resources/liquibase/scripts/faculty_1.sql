--liquibase formatted sql
--changeset elena_kastalskaya:1
CREATE index faculty_name_color_index on faculty(name,color);