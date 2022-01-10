
--insertion of contact details
insert into contact_details (phone, email) VALUES ('1909819445','lnorfolk0@examiner.com');
insert into contact_details (phone, email) VALUES ('1909819445','lnorfolk0@examiner.com');
insert into contact_details (phone, email) VALUES ('9925065212','rfrears2@hp.com');
insert into contact_details (phone, email) VALUES ('2777416595','emckiddin4@businessinsider.com');
insert into contact_details (phone, email) VALUES ('2066248740','lmaypes5@spotify.com');
insert into contact_details (phone, email) VALUES ('4299552346','jbenes6@webs.com');
insert into contact_details (phone, email) VALUES ('5354148833','kmccallion7@cam.ac.uk');
insert into contact_details (phone, email) VALUES ('2483630695','ndongles8@reddit.com');
insert into contact_details (phone, email) VALUES ('2523268216','rmarchant9@yolasite.com');
insert into contact_details (phone, email) VALUES ('9524669146','pwalkdena@npr.org');
insert into contact_details (phone, email) VALUES ('2222770104','ekertonb@over-blog.com');
insert into contact_details (phone, email) VALUES ('9814056632','fmcmurdoc@oakley.com');
insert into contact_details (phone, email) VALUES ('1827923171','framalheted@woothemes.com');
insert into contact_details (phone, email) VALUES ('7949355223','lgrunnille@walmart.com');
insert into contact_details (phone, email) VALUES ('3133923474','hlicciardiellof@yelp.com');
insert into contact_details (phone, email) VALUES ('5154834479','lhissieg@storify.com');
insert into contact_details (phone, email) VALUES ('1474042143','asangarh@printfriendly.com');
insert into contact_details (phone, email) VALUES ('8622207755','bmanhoodi@miitbeian.gov.cn');
insert into contact_details (phone, email) VALUES ('1161578142','rtallmanj@japanpost.jp');
insert into contact_details (phone, email) VALUES ('3952177243','fdiblek@house.gov');
insert into contact_details (phone, email) VALUES ('7555465225','ipinardl@squarespace.com');

--insertion of personal details for student and instructors
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('0412171811','Lauree','Norfolk','Aftonbryggan','1','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('0512171811','Viki','Lock','Aftonbryggan','2','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('0612171811','Ringo','Frears','Aftonbryggan','3','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('0712171811','Obidiah','Gouthier','Aftonbryggan','4','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9812171811','Ed','McKiddin','Aftonbryggan','5','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9912171811','Lil','Maypes','Aftonbryggan','6','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9712171811','Joellen','Benes','Aftonbryggan','7','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9612171811','Krystyna','McCallion','Aftonbryggan','8','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9512171811','Nancy','Dongles','Aftonbryggan','9','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9412171811','Robenia','Marchant','Aftonbryggan','10','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9312171811','Paolo','Walkden','Aftonbryggan','11','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9212171811','Eduino','Kerton','Aftonbryggan','12','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9112171811','Ford','McMurdo','Aftonbryggan','13','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('9012171811','Ferdinanda','Ramalhete','Aftonbryggan','14','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8912171811','Leslie','Grunnill','Aftonbryggan','15','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8812171811','Hyman','Licciardiello','Aftonbryggan','16','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8712171811','Linnea','Hissie','Aftonbryggan','17','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8612171811','Armstrong','Sangar','Aftonbryggan','18','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8512171811','Brock','Manhood','Aftonbryggan','19','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8412171811','Remington','Tallman','Aftonbryggan','20','11942', 'Storvreta');
insert into personal_details (ssn, first_name, last_name, street_name, street_number, zip, city)VALUES ('8412171811','Barbabas','Lamport','Aftonbryggan','20','11942', 'Storvreta');

--insertion of students
insert into student (personal_details_id, parent_contact_detail_id, contract_details_id, sibling_bool) VALUES (6,1,6,'1');
insert into student (personal_details_id, parent_contact_detail_id, contract_details_id, sibling_bool) VALUES (7,2,7,'0');
insert into student (personal_details_id, parent_contact_detail_id, contract_details_id, sibling_bool) VALUES (8,3,8,'0');
insert into student (personal_details_id, parent_contact_detail_id, contract_details_id, sibling_bool) VALUES (9,4,9,'1');
insert into student (personal_details_id, parent_contact_detail_id, contract_details_id, sibling_bool) VALUES (10,5,10,'0');

--insertion of instructors
insert into instructor (skill, paylevel, ensemble_instructor, contact_details_id, personal_details_id) VALUES ('1','1','1',11,11);
insert into instructor (skill, paylevel, ensemble_instructor, contact_details_id, personal_details_id) VALUES ('2','2','0',12,12);
insert into instructor (skill, paylevel, ensemble_instructor, contact_details_id, personal_details_id) VALUES ('3','3','0',13,13);
insert into instructor (skill, paylevel, ensemble_instructor, contact_details_id, personal_details_id) VALUES ('4','4','1',14,14);
insert into instructor (skill, paylevel, ensemble_instructor, contact_details_id, personal_details_id) VALUES ('5','5','1',15,15);

--insertion of instruments
insert into instruments (instrument) values ('piano');
insert into instruments (instrument) values ('guitar');
insert into instruments (instrument) values ('base guitar');
insert into instruments (instrument) values ('drums');
insert into instruments (instrument) values ('Bagpipes');
insert into instruments (instrument) values ('Banjo');
insert into instruments (instrument) values ('cello');
insert into instruments (instrument) values ('harp');
insert into instruments (instrument) values ('flute');
insert into instruments (instrument) values ('clarinette');
insert into instruments (instrument) values ('cymbals');
insert into instruments (instrument) values ('accordion');
insert into instruments (instrument) values ('maracas');
insert into instruments (instrument) values ('tambourine');
insert into instruments (instrument) values ('triangle');
insert into instruments (instrument) values ('ukelele');

--insertion of instructor_instruments
insert into instructor_instruments (instructor_id,instrument_id) VALUES (1,12), (1,2), (2,3), (2,4), (3,1), (3,16), (4,1), (4,5), (5,10), (5,11);

--insertion of price 
insert into price (skill_level, lesson_type, price, sibling_discount, start_time) VALUES ('1','individual','250','0.25','2022'),
                                                                                         ('2','individual','300','0.25','2022'),
                                                                                         ('3','individual','400','0.25','2022'),
                                                                                         ('4','individual','500','0.25','2022'),
                                                                                         ('5','individual','600','0.25','2022'),
                                                                                         ('1','group','450','0.25','2022'),
                                                                                         ('2','group','450','0.25','2022'),
                                                                                         ('3','group','450','0.25','2022'),
                                                                                         ('4','group','850','0.25','2022'),
                                                                                         ('5','group','1000','0.25','2022'),
                                                                                         ('1','ensemble','300','0.25','2022'),
                                                                                         ('2','ensemble','350','0.25','2022'),
                                                                                         ('3','ensemble','800','0.25','2022'),
                                                                                         ('4','ensemble','10000','0.25','2022'),
                                                                                         ('5','ensemble','15000','0.25','2022');
insert into price (skill_level, lesson_type, price, sibling_discount, start_time) VALUES ('1','individual','100','0.25','2021'),
                                                                                         ('2','individual','200','0.25','2021'),
                                                                                         ('3','individual','300','0.25','2021'),
                                                                                         ('4','individual','700','0.25','2021'),
                                                                                         ('5','individual','800','0.25','2021'),
                                                                                         ('1','group','300','0.25','2021'),
                                                                                         ('2','group','400','0.25','2021'),
                                                                                         ('3','group','500','0.25','2021'),
                                                                                         ('4','group','600','0.25','2021'),
                                                                                         ('5','group','700','0.25','2021'),
                                                                                         ('1','ensemble','800','0.25','2021'),
                                                                                         ('2','ensemble','900','0.25','2021'),
                                                                                         ('3','ensemble','1000','0.25','2021'),
                                                                                         ('4','ensemble','2000','0.25','2021'),
                                                                                         ('5','ensemble','3000','0.25','2021');

--insertion of individual lessons
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-08 19:00:00','2022-01-08 20:00:00', 1,'100','1', 1,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-09 19:00:00','2022-01-08 20:00:00', 1,'100','1', 2,2);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-03-10 19:00:00','2022-01-08 20:00:00', 1,'100','1', 3,3);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-05-11 19:00:00','2022-01-08 20:00:00', 1,'100','1', 4,4);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-06-12 19:00:00','2022-01-08 20:00:00', 1,'100','1', 5,5);

--adding indiviual lessons with inheritence
insert into individual_lesson SELECT *, 'banjo' as instrument_type from lesson where id=1;
insert into individual_lesson SELECT *, 'piano' as instrument_type from lesson where id=2;
insert into individual_lesson SELECT *, 'gitar' as instrument_type from lesson where id=3;
insert into individual_lesson SELECT *, 'harp' as instrument_type from lesson where id=4;
insert into individual_lesson SELECT *, 'drums' as instrument_type from lesson where id=5;



--insertion of group lessons
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-13 19:00:00','2022-01-08 20:00:00', 1,'100','1', 6,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-03-14 19:00:00','2022-01-08 20:00:00', 2,'100','1', 7,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-02-15 19:00:00','2022-01-08 20:00:00', 3,'100','1', 8,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-04-16 19:00:00','2022-01-08 20:00:00', 4,'100','1', 9,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-12-17 19:00:00','2022-01-08 20:00:00', 5,'100','1', 10,1);

--adding group lessons with inheritence
insert into group_lesson SELECT *,'15' as max_capacity, '2' as min_attendence ,'piano' as instrument_type from lesson where id=6;
insert into group_lesson SELECT *,'15' as max_capacity, '2' as min_attendence ,'piano' as instrument_type from lesson where id=7;
insert into group_lesson SELECT *,'15' as max_capacity, '2' as min_attendence ,'piano' as instrument_type from lesson where id=8;
insert into group_lesson SELECT *,'15' as max_capacity, '2' as min_attendence ,'piano' as instrument_type from lesson where id=9;
insert into group_lesson SELECT *,'15' as max_capacity, '2' as min_attendence ,'piano' as instrument_type from lesson where id=10;

--insertion of ensembles lessons
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-12 19:00:00','2022-01-15 20:00:00', 1,'100','0', 1,1);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-07-12 19:00:00','2022-07-12 20:00:00', 2,'100','1', 2,4);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-08-14 19:00:00','2022-08-14 20:00:00', 3,'100','1', 3,4);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-18 19:00:00','2022-01-18 20:00:00', 4,'100','1', 4,5);
insert into lesson (start_time, end_time, level, room, availability, price_id, instructor_id) VALUES ('2022-01-18 19:00:00','2022-01-18 20:00:00', 5,'100','1', 5,1);

--adding ensembles with inheritence
insert into ensemble SELECT *,'15' as max_capacity, '1' as min_attendence ,'punk' as genre from lesson where id=11;
insert into ensemble SELECT *,'15' as max_capacity, '2' as min_attendence ,'rock' as genre from lesson where id=12;
insert into ensemble SELECT *,'15' as max_capacity, '2' as min_attendence ,'synth' as genre from lesson where id=13;
insert into ensemble SELECT *,'15' as max_capacity, '2' as min_attendence ,'country' as genre from lesson where id=14;
insert into ensemble SELECT *,'15' as max_capacity, '2' as min_attendence ,'choir' as genre from lesson where id=15;

--instruments for rent
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('piano', 'Propithecus verreauxi', 11,200);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('piano', 'Lemur fulvus', 17,300);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('guitar', 'Varanus komodensis', 16,250);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('guitar', 'Cacatua tenuirostris', 20,300);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('base guitar', 'Bassariscus astutus', 13,500);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('base guitar', 'Crotalus cerastes', 6,700);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('drums', 'Dacelo novaeguineae', 15,2000);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('drums', 'Merops nubicus', 7,3500);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('bagpipes', 'Anastomus oscitans', 7,9000);
insert into instrument_for_rent (instrument_type, brand, quantity_in_stock, price) VALUES ('banjo', 'Columba palumbus', 8,18000);

--insert lease
insert into lease (start_date, end_date, instrument_id, student_id) VALUES ('2022-01-08','2023-01-08',1,1);
insert into lease (start_date, end_date, instrument_id, student_id) VALUES ('2022-01-08','2023-01-08',2,1);
insert into lease (start_date, end_date, instrument_id, student_id) VALUES ('2022-01-08','2023-01-08',3,3);
insert into lease (start_date, end_date, instrument_id, student_id) VALUES ('2022-01-08','2023-01-08',4,4);
insert into lease (start_date, end_date, instrument_id, student_id) VALUES ('2022-01-08','2023-01-08',5,5);

--insert booking
insert into booking (student_id, lesson_id) VALUES (1,1);
insert into booking (student_id, lesson_id) VALUES (2,2);
insert into booking (student_id, lesson_id) VALUES (3,4);
insert into booking (student_id, lesson_id) VALUES (4,6);
insert into booking (student_id, lesson_id) VALUES (5,8);
insert into booking (student_id, lesson_id) VALUES (1,2);
insert into booking (student_id, lesson_id) VALUES (2,3);
insert into booking (student_id, lesson_id) VALUES (3,1);
insert into booking (student_id, lesson_id) VALUES (4,2);
insert into booking (student_id, lesson_id) VALUES (5,4);
insert into booking (student_id, lesson_id) VALUES (1,6);
insert into booking (student_id, lesson_id) VALUES (2,4);
insert into booking (student_id, lesson_id) VALUES (3,3);

insert into booking (student_id, lesson_id) VALUES (5,12);
insert into booking (student_id, lesson_id) VALUES (1,12);
insert into booking (student_id, lesson_id) VALUES (2,12);
insert into booking (student_id, lesson_id) VALUES (3,12);

insert into booking (student_id, lesson_id) VALUES (5,13);
insert into booking (student_id, lesson_id) VALUES (1,14);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (5,15);
insert into booking (student_id, lesson_id) VALUES (1,15);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (3,12);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (5,15);
insert into booking (student_id, lesson_id) VALUES (1,15);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (1,15);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (2,15);
insert into booking (student_id, lesson_id) VALUES (2,15);


