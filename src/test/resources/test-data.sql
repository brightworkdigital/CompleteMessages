INSERT INTO Person(person_id, email, name) VALUES (23, 'first@email.com', 'Will');
INSERT INTO Person(person_id, email, name) VALUES (24, 'another@email.com', 'Tori');
INSERT INTO Person(person_id, email, name) VALUES (25, 'more@email.com', 'T');

INSERT INTO Message (id, content, person_id) VALUES (200, 'First test message', (SELECT person_id FROM Person WHERE name = 'Will'));
INSERT INTO Message (id, content, person_id) VALUES (300, 'Second test message', (SELECT person_id FROM Person WHERE name = 'Will'));
INSERT INTO Message (id, content, person_id) VALUES (400, 'Select test message', (SELECT person_id FROM Person WHERE name = 'T'));

