truncate table candidates cascade;
truncate table address cascade;
truncate table voter cascade;
truncate table election cascade;


insert into voter(id, date_of_birth, eligibility_status, first_name, last_name, password, username) values
    (1,	'1/1/1990'	,'ELIGIBLE',	'InitialFirstName',	'InitialLastName',	'initialpassword',	'initialuser'	);




insert into candidates(date_of_birth, id, biography, financial_disclosure_url, nomination_form_url, party_affiliation, password, position_contested, username) values
    (	'6/21/2002'	, 1 ,'biography','http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984758/bexm1vaipwdzkupz8xci.pdf'	,'http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984729/zl2uxpntxndetxrquhn1.pdf	','affiliacion'	,'1234'	,'presido'	,'jargoh');


insert into election (id, election_category,	election_status,	election_title,	end_time,	start_time)values
(1	,'LOCAL_GOVERNMENT',	'SCHEDULED',	'millionaire',	'7/20/2024 0:00',	'6/23/2024 1:26');

insert into registered_candidates(election_id, candidate_id) values
          (1,1);