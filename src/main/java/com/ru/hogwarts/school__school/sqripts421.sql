select * from student;

ALTER TABLE student ADD CONSTRAINT age_3constraint  check ( age>=16 );
ALTER TABLE student  ALTER COLUMN name SET NOT  NULL;
ALTER TABLE student  ADD CONSTRAINT name_unique1 UNIQUE (name);
ALTER TABLE faculty ADD CONSTRAINT   name_color_unique UNIQUE (name,color);
ALTER TABLE student ALTER column age SET DEFAULT 20;