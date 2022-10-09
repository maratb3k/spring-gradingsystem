INSERT INTO subjects(id, name) VALUES
(nextval('subjects_id_seq'), 'SubjectName1'),
(nextval('subjects_id_seq'), 'SubjectName2');

INSERT INTO users(id, username, password, roles) VALUES
(nextval('users_id_seq'), 'UserName', '$2a$04$K.sCk/r6TTk9Zrp4Am.fAOBYHSYYCnVfpOfzdl5CcfDzilCvsaOeC', 'admin'),
(nextval('users_id_seq'), 'UserName2', '$2a$04$fs0jBaCx/NGa5WD.pJi63.j0f2JHXO8UibajhntFGYSZzHXvXpQi6', 'teacher'),
(nextval('users_id_seq'), 'TeacherName1', '$2a$04$rCoKDW31DbQJWlSfgePVM.uVBhuAOUMKq/2rlWV0WCcre4/xw3z7a', 'teacher'),
(nextval('users_id_seq'), 'TeacherName2', '$2a$04$TRQCX9mnSJFBEITdF7C5QOB9S1XCJDHtZWB/ydIXLBIkmN1lvdtqW', 'teacher'),
(nextval('users_id_seq'), 'AdminName1', '$2a$04$emo0FCYa2kVZ8U5S5yF8TuCOY0GvkxYRMi.fMziEUOzmm6TP2OqZK', 'admin'),
(nextval('users_id_seq'), 'StudentName1', '$2a$04$N0pdEVg72Oi3maxvgkt8F.gQG/okQ2bg6wfxyM4bPTHn5A5ocYHzS', 'student'),
(nextval('users_id_seq'), 'StudentName2', '$2a$04$4uHOBx8MavoNx66ytoQOquCwNM6/cIr5CBKmVEpWzZd98wA068GFC', 'student');

INSERT INTO admins(id, name, surname, role, user_id) VALUES
(nextval('admins_id_seq'), 'AdminName1', 'AdminSurname1', 'admin', 5);

INSERT INTO teachers(id, name, surname, role, user_id) VALUES
(nextval('teachers_id_seq'), 'TeacherName1', 'TeacherSurname1', 'teacher', 3),
(nextval('teachers_id_seq'), 'TeacherName2', 'TeacherSurname2', 'teacher', 4);

INSERT INTO groups(id, subject_id, day_of_week, group_time, teacher_id) VALUES
(nextval('groups_id_seq'), 1, 'MONDAY', '10:00', 89),
(nextval('groups_id_seq'), 2, 'FRIDAY', '11:00', 90);

INSERT INTO students(id, name, surname, role, robucks, group_id, user_id) VALUES
(nextval('students_id_seq'), 'StudentName1', 'StudentSurname1', 'student', 5, 89, 6),
(nextval('students_id_seq'), 'StudentName2', 'StudentSurname2', 'student', 6, 90, 7);


select * from users;