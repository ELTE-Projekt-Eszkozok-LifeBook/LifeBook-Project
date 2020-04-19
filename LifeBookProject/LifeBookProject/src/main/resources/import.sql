INSERT INTO Diary(text, image, video, current_mood, date) VALUES ('It was a wonderful day :D',null, null, 'HAPPY', '2020-04-10');
INSERT INTO Diary(text, image, video, current_mood, date) VALUES ('Dear my diary...', null, null, 'SAD', '2020-04-11');
INSERT INTO Diary(text, image, video, current_mood, date) VALUES ('My head has been acheing til morning so I am in a really bad mood now.', null, null, 'DEPRESSED', '2020-04-06');
INSERT INTO Diary(text, image, video, current_mood, date) VALUES ('I went fishing with my cousins but... I figured out it will not become my hobby', null, null, 'OK', '2020-04-12');
INSERT INTO Diary(text, image, video, current_mood, date) VALUES ('Nothing special...', null, null, 'OK', '2020-04-10');

INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Apple', 'fruit', TRUE, 'DAILY', '1 piece');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Orange', 'fruit', TRUE, 'MONTHLY', '1 plate');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Fried chicken', 'main course', TRUE, 'WEEKLY', '3 pieces');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Caesar salad', 'salad', TRUE, 'DAILY', '1 portion');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Hot chocolate', 'hot drink', FALSE, 'WEEKLY', '3 dl');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Water', 'cool drink', FALSE, 'DAILY', '1,5 liter');
INSERT INTO Eating(name, type, is_food, frequency, portion) VALUES ('Coffee', 'hot drink', FALSE, 'DAILY', '1 dl');

INSERT INTO Sport(name, regularity, duration, start_time, is_official) VALUES ('basketball', 'WEEKLY', 3.0, '2015-09-10', TRUE);
INSERT INTO Sport(name, regularity, duration, start_time, is_official) VALUES ('dancing', 'WEEKLY', 3.0, '2019-11-30', FALSE);
INSERT INTO Sport(name, regularity, duration, start_time, is_official) VALUES ('running', 'DAILY', 1.5, '2018-01-05', FALSE);
INSERT INTO Sport(name, regularity, duration, start_time, is_official) VALUES ('skiing', 'YEARLY', 20.0, '2010-03-16', FALSE);

INSERT INTO Financialstats(category, date, amount, description) VALUES ('INCOME', '2020-04-10', 15000, 'Bursa Hungarica');
INSERT INTO Financialstats(category, date, amount, description) VALUES ('INCOME', '2020-04-11', 23400, 'Scholarship');
INSERT INTO Financialstats(category, date, amount, description) VALUES ('COST', '2020-04-11', 5000, 'Buying new T-shirt');

INSERT INTO Timetable(event, frequency, date, time, note) VALUES ('Birthday Party', 'OCCASIONAL', '2020-03-16', '17:00:00', 'Booked 2 rooms in Escape Room; should call Stifler!');
INSERT INTO Timetable(event, frequency, date, time, note) VALUES ('Hip-hop training', 'WEEKLY', '2020-02-12', '18:00:00', 'Must buy season ticket!');
INSERT INTO Timetable(event, frequency, date, time, note) VALUES ('MI exam', 'OCCASIONAL', '2020-06-06', '12:30:00', 'I dont want it :(((');
INSERT INTO Timetable(event, frequency, date, time, note) VALUES ('Meeting with my friends', 'OCCASIONAL', '2020-08-31', '12:00:00', 'I am looking forward to it yeeey >3<');
INSERT INTO Timetable(event, frequency, date, time, note) VALUES ('Boardgame Nights', 'MONTHLY', '2020-05-22', '15:00:00', 'Because we love GAMES!!!');

INSERT INTO Todo(todo_text, checked, important, category) VALUES ('At weekend: dusting, vaccuuming, floor washing and ironing!', TRUE, TRUE, 'HOUSEHOLD');
INSERT INTO Todo(todo_text, checked, important, category) VALUES ('Give in the next homework of Computer Graphics!', FALSE, TRUE, 'UNIVERSITY');
INSERT INTO Todo(todo_text, checked, important, category) VALUES ('Buying another bag of dog food!', FALSE, TRUE, 'SHOPPING');