INSERT INTO roles (authority) VALUES ('ROLE_ADMIN'),('ROLE_USER'), ('ROLE_DOCTOR');

insert into users_info (fio, number) values 
('Диденко В. С.', '+375252923211'),
('Белоножко А. Н.', '+375252923212'),
('Шматок С. С.', '+375252923213'),
('Симаков К. В.', '+375252923214'),
('Ковальчук В. А.', '+375252923215'),
('Батура В. С.', '+375252923216'),
('Павленок В. С.', '+375252923217'),
('Уваров С. С.', '+375252923218'),
('Земченко Е. А.', '+375252923219'),
('Кривицкий И. С.', '+375252923210');

INSERT INTO users (username, password, user_info_id, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES 
('doctor1', '$2a$10$DgDXzVzN4xIlzFsSInDR9.tuwpjKpww7dcvg0AqtqcEu/O9r7.98i', null, true, true, true, true), 
('doctor2', '$2a$10$/aKq4URiMx7SBLbsDK91nucUyEFK72qMYj/hPqwv/R.3w0H6fQwmy.', null, true, true, true, true), 
('doctor3', '$2a$10$mwRENApyVipW.vWIPM/rr.pkapdkd9bVpP46i5XjmTDGMxS3fOhk2', null, true, true, true, true),
('admin', '$2a$10$aBfklvHvGoOKmF/iBe/oseWvpFcmMG12dIQdiW3Qu18LFFZQ3wfKW', null, true, true, true, true),
('root3', '$2a$10$wnQB0wjOEDL5uxt2HuV9O.RQ/156qSN7sR0RTmvfSCtc9we.ehf5G', 1, true, true, true, true), 
('root4', '$2a$10$.VvVcJwDeDmE.04oA/rK1.vxHoNv3GS/7eXtrI.6WKWgS/I6bv71S', 2, true, true, true, true),
('root5', '$2a$10$5ytKFaqmZ7ID.LJPIgb6pu.ZHihmma.rJbhzt11AGPly8Ud/mm1a.', 3, true, true, true, true), 
('root6', '$2a$10$qLxbivOd9rXS9CAg2nvvHOnjnuoOjjBOGTLd0MNUjg4OhyE4SpPJe', 4, true, true, true, true),
('root7', '$2a$10$4okQun9f0xvW3qllJGu3cuu4ZUgrC6zRfw85UMxTRN1auKYG5z1wa', 5, true, true, true, true), 
('root8', '$2a$10$7X0KOVb5edM.Sig/RVNFTeL/X3oWrb9QKfikAUYwJpS.hWtwcOzrm', 6, true, true, true, true),
('root9', '$2a$10$ziRs9PLnBEzuS2E/KAykkec.W4CpUgluTJW8Ki6d2ZQWPREdTavwO', 7, true, true, true, true), 
('root10', '$2a$10$iUK.oL2LVyzyT73K32wSDOH.MoQGroMl5xlGpuP6Z0g.uJrDU3i..', 8, true, true, true, true),
('root11', '$2a$10$tinwuhhIOsSBEUgZsQdcWegI01ZaxtccvEGX.XSHeWKGnCs80Cwaa', 9, true, true, true, true), 
('root12', '$2a$10$A6MytDmVqFDtKMHOppzch.r8QIQgfsQvy/7Dddwu7eJfy1Il7vHE.', 10, true, true, true, true);


INSERT INTO users_roles (user_id, role_id) VALUES 
(1, 3), (2, 3), (3, 3), (4, 1), (5, 2), (6, 2), (7, 2), 
(8, 2), (9, 2), (10, 2), (11, 2), (12, 2), (13, 2), (14, 2);


INSERT INTO doctors (doctor_type, fio, start_work, end_work) 
VALUES   ('Хирург', 'Богословский В. В.', '14:00', '20:00'),
  ('Хирург', 'Диденко В. С.', '12:00', '18:00'),
  ('Терапевт', 'Богословская И. С.', '12:00', '18:00'),
  ('Терапевт', 'Иванова А. В.', '10:00', '16:00'),
  ('Лор', 'Сидорова О. П.', '09:00', '15:00'),
  ('Лор', 'Петрова К. И.', '11:00', '17:00'),
  ('Окулист', 'Смирнова Е. А.', '08:00', '14:00'),
  ('Окулист', 'Козлова Е. И.', '09:00', '15:00'),
  ('Дерматолог', 'Михайлова А. Н.', '13:00', '19:00'),
  ('Дерматолог', 'Васильева И. С.', '14:00', '20:00'),
  ('Ортопед', 'Сидоров С. М.', '10:00', '16:00'),
  ('Ортопед', 'Петров В. В.', '11:00', '17:00'),
  ('Невропатолог', 'Иванова Е. И.', '09:00', '15:00'),
  ('Невропатолог', 'Смирнова А. А.', '10:00', '16:00'),
  ('Кардиолог', 'Петров Н. А.', '08:30', '14:30'),
  ('Кардиолог', 'Козлов Н. Н.', '09:30', '15:30'),
  ('Психиатр', 'Иванов О. П.', '15:00', '21:00'),
  ('Психиатр', 'Сидорова А. С.', '16:00', '22:00');

