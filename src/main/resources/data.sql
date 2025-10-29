

-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO authorities(id,authority) VALUES (1,'ADMIN');
INSERT INTO appusers(id,username,password,authority) VALUES (1,'admin1','$2a$10$nMmTWAhPTqXqLDJTag3prumFrAJpsYtroxf0ojesFYq0k4PmcbWUS',1);

-- Ten player users, named player1 with passwor 0wn3r
INSERT INTO authorities(id,authority) VALUES (2,'PLAYER');
INSERT INTO appusers(id,username,password,authority) VALUES (4,'player1','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (5,'player2','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (6,'player3','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (7,'player4','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (8,'player5','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (9,'player6','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (10,'player7','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (11,'player8','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (12,'player9','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (13,'player10','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);

INSERT INTO appusers(id,username,password,authority) VALUES (14,'jescarcon','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (15,'NPQ9307','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (16,'JMQ0481','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (17,'LCY2956','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (18,'HGK2646','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (19,'XDW2012','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);

INSERT INTO achievement (id,name,description,badge_image,threshold,metric) VALUES (1,'name1','descripcion1','https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg',10,'GAMES_PLAYED');

INSERT INTO player_statistics(
  id,
  friends,
  first_connection,
  last_connection,
  first_game_played,
  last_game_played,
  games_played,
  games_won,
  games_as_green,
  games_as_purple,
  favourite_team,
  victories_as_green,
  victories_as_purple,
  max_bactery_played_as_green,
  max_bactery_played_as_purple,
  max_sarcine_played_as_green,
  max_sarcine_played_as_purple,
  max_turns_played_as_green,
  max_turns_played_as_purple
) VALUES (
  1,
  10,
  '2025-01-10 09:15:00',
  '2025-06-05 18:30:00',
  '2025-02-01 14:00:00',
  '2025-06-04 20:45:00',
  20,
  8,
  12,
  8,
  'GREEN',
  5,
  3,
  4,
  5,
  2,
  3,
  40,
  38
);
/*
INSERT INTO player_statistics(
  id,
  friends,
  first_connection,
  last_connection,
  first_game_played,
  last_game_played,
  games_played,
  games_won,
  games_as_green,
  games_as_purple,
  favourite_team,
  victories_as_green,
  victories_as_purple,
  max_bactery_played_as_green,
  max_bactery_played_as_purple,
  max_sarcine_played_as_green,
  max_sarcine_played_as_purple,
  max_turns_played_as_green,
  max_turns_played_as_purple
) VALUES (
  2,
  3,
  '2024-11-20 11:00:00',
  '2025-05-01 16:10:00',
  '2024-12-05 13:25:00',
  '2025-04-30 19:00:00',
  7,
  2,
  2,
  5,
  'PURPLE',
  0,
  2,
  1,
  2,
  0,
  1,
  22,
  25
);

INSERT INTO player_statistics(
  id,
  friends,
  first_connection,
  last_connection,
  first_game_played,
  last_game_played,
  games_played,
  games_won,
  games_as_green,
  games_as_purple,
  favourite_team,
  victories_as_green,
  victories_as_purple,
  max_bactery_played_as_green,
  max_bactery_played_as_purple,
  max_sarcine_played_as_green,
  max_sarcine_played_as_purple,
  max_turns_played_as_green,
  max_turns_played_as_purple
) VALUES (
  3,
  0,
  '2025-03-03 08:00:00',
  '2025-07-01 21:20:00',
  '2025-03-10 10:10:00',
  '2025-06-30 22:00:00',
  15,
  7,
  9,
  6,
  'GREEN',
  4,
  3,
  3,
  4,
  1,
  2,
  36,
  34
);
*/