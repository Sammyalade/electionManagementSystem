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



truncate table nomination_form cascade;
INSERT INTO nomination_form(id, status, position_contested, date_submitted, date_approved, election_category)
VALUES
    (200, 'SUBMITTED', 'president', '2024-06-04T15:03:03.792009700', '2024-09-04T15:04:03.792009700', 'NATIONAL'),
    (201, 'SUBMITTED', 'governor', '2024-06-04T15:03:03.792009700', '2024-09-04T15:04:03.792009700', 'STATE'),
    (202, 'APPROVED', 'president', '2024-06-04T15:03:03.792009700', '2024-09-04T15:04:03.792009700', 'LOCAL_GOVERNMENT'),
    (203, 'APPROVED', 'governor', '2024-06-04T15:03:03.792009700', '2024-09-04T15:04:03.792009700', 'LOCAL_GOVERNMENT');



TRUNCATE TABLE election CASCADE;

INSERT INTO election (id, start_time, end_time, election_status, election_category, election_result_id)
VALUES
    (300, '2024-06-04T15:03:03.792009700', '2024-06-04T17:03:03.792009700', 'NOT_STARTED', 'NATIONAL', NULL),
    (301, '2024-06-04T15:03:03.792009700', '2024-06-04T17:03:03.792009700', 'CONCLUDED', 'STATE', NULL),
    (302, '2024-06-04T15:03:03.792009700', '2024-06-04T17:03:03.792009700', 'IN_PROGRESS', 'LOCAL_GOVERNMENT', NULL),
    (303, '2024-06-04T15:03:03.792009700', '2024-06-04T17:03:03.792009700', 'CONCLUDED', 'LOCAL_GOVERNMENT', NULL);
