

-- One admin user, named admin1 with passwor 4dm1n and authority admin
-- Ten player users, named player1 with passwor 0wn3r
INSERT INTO authorities(id,authority) VALUES 
(1,'ADMIN'),
(2,'PLAYER');

INSERT INTO appusers(id,username,password,authority) VALUES 
(1,'admin1','$2a$10$nMmTWAhPTqXqLDJTag3prumFrAJpsYtroxf0ojesFYq0k4PmcbWUS',1),
(4,'player1','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(5,'player2','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(6,'player3','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(7,'player4','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(8,'player5','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(9,'player6','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(10,'player7','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(11,'player8','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(12,'player9','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(13,'player10','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(14,'jescarcon','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(15,'NPQ9307','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(16,'JMQ0481','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(17,'LCY2956','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(18,'HGK2646','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2),
(19,'XDW2012','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);

INSERT INTO achievements (id,name,description,badge_image,threshold,metric) VALUES
 (1,'Principiante','Juega tu primera partida','imagen1',1,'GAMES_PLAYED'),
 (2,'Aprobado','Gana tu primera partida','imagen1',1,'GAMES_WON'),
 (3,'Graduado','Gana 10 partidas','imagen1',10,'GAMES_WON'),
 (4,'Experimentado','Juega 20 partidas','imagen1',20,'GAMES_PLAYED'),
 (5,'Doctor','Gana 40 partidas','imagen1',10,'GAMES_WON'),
 (6,'Verde Novato','Juega 5 partidas como equipo verde','imagen1',5,'GAMES_AS_GREEN'),
 (7,'Púrpura Novato','Juega 5 partidas como equipo púrpura','imagen1',5,'GAMES_AS_PURPLE'),
 (8,'Verde Experto','Juega 25 partidas como equipo verde','imagen1',25,'GAMES_AS_GREEN'),
 (9,'Púrpura Experto','Juega 25 partidas como equipo púrpura','imagen1',25,'GAMES_AS_PURPLE'),
 (10,'Victoria Verde','Gana tu primera partida como equipo verde','imagen1',1,'VICTORIES_AS_GREEN'),
 (11,'Victoria Púrpura','Gana tu primera partida como equipo púrpura','imagen1',1,'VICTORIES_AS_PURPLE'),
 (12,'Bacteria Pro','Coloca 5 bacterias en una partida','imagen1',5,'MAX_BACTERY_PLAYED'),
 (13,'Sarcina Pro','Coloca 3 sarcinas en una partida','imagen1',3,'MAX_SARCINE_PLAYED'),
 (14,'Resistencia','Completa una partida de 30 turnos','imagen1',30,'MAX_TURNS_PLAYED'),
 (15,'Maratonista','Completa una partida de 60 turnos','imagen1',60,'MAX_TURNS_PLAYED');

INSERT INTO players (id,first_name,last_name,avatar,profile_info,is_online,user_id) VALUES
(1, 'nombre1', 'apellido1', 'avatar1', 'info1', false, 4),
(2, 'nombre2', 'apellido2', 'avatar2', 'info2',false, 5);

INSERT INTO player_statistics(
  id, friends, first_connection, last_connection, first_game_played, last_game_played,
  games_played, games_won, games_as_green, games_as_purple, favourite_team,
  victories_as_green, victories_as_purple, max_bactery_played_as_green, max_bactery_played_as_purple,
  max_sarcine_played_as_green, max_sarcine_played_as_purple, max_turns_played_as_green, max_turns_played_as_purple, player_id
) VALUES 
(1, 0, '2025-01-01 00:00:00', '2025-01-01 00:00:00', '2025-01-01 00:00:00', '2025-01-01 00:00:00',  0, 0, 0, 0, 'GREEN', 0, 0, 0, 0, 0, 0, 0, 0, 1),
(2, 0, '2025-01-01 00:00:00', '2025-01-01 00:00:00', '2025-01-01 00:00:00', '2025-01-01 00:00:00',  0, 0, 0, 0, 'PURPLE', 0, 0, 0, 0, 0, 0, 0, 0, 2);

INSERT INTO players_friends (player_id, friend_id) VALUES
(1, 2),
(2, 1);

INSERT INTO players_achievements (player_id, achievements_id) VALUES
(1, 1),
(2, 1);
