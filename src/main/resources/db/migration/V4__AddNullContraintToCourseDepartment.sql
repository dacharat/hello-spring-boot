ALTER TABLE course ALTER department SET NOT NULL;

CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');

ALTER TABLE student ALTER COLUMN gender TYPE gender USING (gender::gender);

ALTER TABLE student DROP CONSTRAINT IF EXISTS student_gender_check;