truncate table candidates cascade;


insert into candidates(date_of_birth,id, biography, financial_disclosure_url, nomination_form_url, party_affiliation, password, position_contested, username) values
    (	'6/21/2002'	, 1 ,'biography','http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984758/bexm1vaipwdzkupz8xci.pdf'	,'http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984729/zl2uxpntxndetxrquhn1.pdf	','affiliacion'	,'1234'	,'presido'	,'jargoh')
--
-- contact_information_id	date_of_birth	election_id	id	biography	financial_disclosure_url	nomination_form_url	party_affiliation	password	position_contested	username
-- (1,	6/21/2002	,NULL	,1	,jargoh	,http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984758/bexm1vaipwdzkupz8xci.pdf	,http://res.cloudinary.com/dbjtncfmz/image/upload/v1718984729/zl2uxpntxndetxrquhn1.pdf	,affiliacion	,1234	,presido	,jargoh)
