truncate table vote cascade;

insert into vote(id, election, candidate_id, time_voted, voter_id) values
(1, 'NATIONAL', 100, '2024-06-04T15:03:03.792009700', 104),
(2, 'STATE', 200, '2024-06-04T15:03:03.792009700', 101),
(3, 'LOCAL_GOVERNMENT', 300, '2024-06-04T15:03:03.792009700', 102),
(4, 'OTHERS', 400, '2024-06-04T15:03:03.792009700', 103);