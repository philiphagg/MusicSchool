drop table if exists contact_details cascade;
CREATE TABLE contact_details (
 id INT GENERATED ALWAYS AS IDENTITY,
 phone VARCHAR(500),
 email VARCHAR(500)
);

ALTER TABLE contact_details ADD CONSTRAINT PK_contact_details PRIMARY KEY (id);

drop table if exists instrument_for_rent cascade;
CREATE TABLE instrument_for_rent (
 id INT GENERATED ALWAYS AS IDENTITY,
 instrument_type VARCHAR(500) NOT NULL,
 brand VARCHAR(500),
 quantity_in_stock INT NOT NULL,
 price INT NOT NULL
);

ALTER TABLE instrument_for_rent ADD CONSTRAINT PK_instrument_for_rent PRIMARY KEY (id);

drop table if exists instruments cascade;
CREATE TABLE instruments (
 id INT GENERATED ALWAYS AS IDENTITY,
 instrument VARCHAR(500)
);

ALTER TABLE instruments ADD CONSTRAINT PK_instruments PRIMARY KEY (id);

drop table if exists personal_details cascade;
CREATE TABLE personal_details (
 id INT GENERATED ALWAYS AS IDENTITY,
 ssn VARCHAR(500),
 first_name VARCHAR(500),
 last_name VARCHAR(500),
 street_name VARCHAR(500),
 street_number VARCHAR(500),
 zip VARCHAR(500),
 city VARCHAR(500)
);

ALTER TABLE personal_details ADD CONSTRAINT PK_personal_details PRIMARY KEY (id);

drop table if exists price cascade;
CREATE TABLE price (
 id INT GENERATED ALWAYS AS IDENTITY,
 skill_level VARCHAR(500) NOT NULL,
 lesson_type VARCHAR(500) NOT NULL,
 price VARCHAR(500) NOT NULL,
 sibling_discount VARCHAR(500) NOT NULL,
 start_time VARCHAR(500)
);

ALTER TABLE price ADD CONSTRAINT PK_price PRIMARY KEY (id);

drop table if exists student cascade;
CREATE TABLE student (
 id INT GENERATED ALWAYS AS IDENTITY,
 personal_details_id INT NOT NULL,
 parent_contact_detail_id INT NOT NULL,
 contract_details_id INT NOT NULL,
 sibling_bool VARCHAR(500)
);

ALTER TABLE student ADD CONSTRAINT PK_student PRIMARY KEY (id);

drop table if exists instructor cascade;
CREATE TABLE instructor (
 id INT GENERATED ALWAYS AS IDENTITY,
 skill VARCHAR(500),
 paylevel VARCHAR(500),
 ensemble_instructor VARCHAR(500),
 contact_details_id INT NOT NULL,
 personal_details_id INT NOT NULL
);

ALTER TABLE instructor ADD CONSTRAINT PK_instructor PRIMARY KEY (id);

drop table if exists instructor_instrument cascade;
CREATE TABLE instructor_instruments (
 instructor_id INT NOT NULL,
 instrument_id VARCHAR(500) NOT NULL
 
);

ALTER TABLE instructor_instruments ADD CONSTRAINT PK_instructor_instruments PRIMARY KEY (instrument_id,instructor_id);

drop table if exists lease cascade;
CREATE TABLE lease (
 id INT GENERATED ALWAYS AS IDENTITY,
 start_date TIMESTAMP(6) NOT NULL,
 end_date TIMESTAMP(6),
 instrument_id INT NOT NULL,
 student_id INT
);

ALTER TABLE lease ADD CONSTRAINT PK_lease PRIMARY KEY (id);

drop table if exists lesson cascade;
CREATE TABLE lesson (
 id INT GENERATED ALWAYS AS IDENTITY,
 start_time TIMESTAMP(6) NOT NULL,
 end_time TIMESTAMP(6) NOT NULL,
 level VARCHAR(500) NOT NULL,
 room VARCHAR(500) NOT NULL,
 availability VARCHAR(500),
 price_id INT NOT NULL,
 instructor_id INT NOT NULL
);

ALTER TABLE lesson ADD CONSTRAINT PK_lesson PRIMARY KEY (id);

drop table if exists booking cascade;
CREATE TABLE booking (
 id INT GENERATED ALWAYS AS IDENTITY,
 student_id INT,
 lesson_id INT
);

ALTER TABLE booking ADD CONSTRAINT PK_booking PRIMARY KEY (id);

drop table if exists ensemble cascade;
CREATE TABLE ensemble (
 id INT NOT NULL,
 max_capacity VARCHAR(500),
 min_attendence VARCHAR(500),
 genre VARCHAR(500)
)inherits(lesson);

ALTER TABLE ensemble ADD CONSTRAINT PK_ensemble PRIMARY KEY (id);

drop table if exists group_lesson cascade;
CREATE TABLE group_lesson (
 id INT NOT NULL,
 max_capaity VARCHAR(500),
 min_attendence VARCHAR(500),
 instrument_type VARCHAR(500)
)inherits(lesson);

ALTER TABLE group_lesson ADD CONSTRAINT PK_group_lesson PRIMARY KEY (id);

drop table if exists individual_lesson cascade;
CREATE TABLE individual_lesson (
 id INT NOT NULL,
 instrument_type VARCHAR(500)
)inherits(lesson);

ALTER TABLE individual_lesson ADD CONSTRAINT PK_individual_lesson PRIMARY KEY (id);


ALTER TABLE student ADD CONSTRAINT FK_student_0 FOREIGN KEY (personal_details_id) REFERENCES personal_details (id);
ALTER TABLE student ADD CONSTRAINT FK_student_1 FOREIGN KEY (parent_contact_detail_id) REFERENCES contact_details (id);
ALTER TABLE student ADD CONSTRAINT FK_student_2 FOREIGN KEY (contract_details_id) REFERENCES contact_details (id);


ALTER TABLE instructor ADD CONSTRAINT FK_instructor_0 FOREIGN KEY (contact_details_id) REFERENCES contact_details (id);
ALTER TABLE instructor ADD CONSTRAINT FK_instructor_1 FOREIGN KEY (personal_details_id) REFERENCES personal_details (id);


ALTER TABLE instructor_instruments ADD CONSTRAINT FK_instructor_instruments_0 FOREIGN KEY (instrument_id) REFERENCES instruments (id);
ALTER TABLE instructor_instruments ADD CONSTRAINT FK_instructor_instruments_1 FOREIGN KEY (instructor_id) REFERENCES instructor (id);


ALTER TABLE lease ADD CONSTRAINT FK_lease_0 FOREIGN KEY (instrument_id) REFERENCES instrument_for_rent (id);
ALTER TABLE lease ADD CONSTRAINT FK_lease_1 FOREIGN KEY (student_id) REFERENCES student (id);


ALTER TABLE lesson ADD CONSTRAINT FK_lesson_0 FOREIGN KEY (price_id) REFERENCES price (id);
ALTER TABLE lesson ADD CONSTRAINT FK_lesson_2 FOREIGN KEY (instructor_id) REFERENCES instructor (id);


ALTER TABLE booking ADD CONSTRAINT FK_booking_0 FOREIGN KEY (student_id) REFERENCES student (id);
ALTER TABLE booking ADD CONSTRAINT FK_booking_1 FOREIGN KEY (lesson_id) REFERENCES lesson (id);


ALTER TABLE ensemble ADD CONSTRAINT FK_ensemble_0 FOREIGN KEY (id) REFERENCES lesson (id);


ALTER TABLE group_lesson ADD CONSTRAINT FK_group_lesson_0 FOREIGN KEY (id) REFERENCES lesson (id);


ALTER TABLE individual_lesson ADD CONSTRAINT FK_individual_lesson_0 FOREIGN KEY (id) REFERENCES lesson (id);


