TRUNCATE TABLE voter CASCADE;
TRUNCATE TABLE candidate CASCADE;

INSERT INTO voter(id, first_name, last_name, password, date_Of_Birth, eligibility_Status)
VALUES
    (300, 'John', 'Doe', 'password', '2021-03-02', 'ELIGIBLE'),
    (301, 'Jude', 'Doe', 'password', '2021-03-02', 'NOT_ELIGIBLE'),
    (302, 'Joseph', 'Doe', 'password', '2021-03-02', 'NOT_ELIGIBLE'),
    (304, 'Abbey', 'Doe', 'password', '2021-03-02', 'ELIGIBLE');



INSERT INTO candidate(id, first_name, last_name, party_affiliation, biography, position_contested, date_of_birth) VALUES
(400, 'Bola', 'Tinubu', "PDP", "bala blu", "President", '1963-05-10'),
(401, 'Muhammed', 'Buhari', "APC", "transmission", "Vice_President", '1970-04-10'),
(402, 'Abiodun', 'Babangida', "NUC", "i will give you slap", "House_of_reps", '1990-02-07'),
(403, 'Michael', 'Benjamin', "ANP", "give us task", "Local_chairman", '1995-08-12'),



