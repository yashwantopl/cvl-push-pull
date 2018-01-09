update loan_applications.fs_loan_application_master lm set lm.campaign_code = null where lm.application_id in (
SELECT rt.application_id FROM loan_applications.fs_retail_applicant_details rt where upper(rt.pan) in (
'AIKPV4360Q',
'FEPPK3923G',
'AAFPQ0177M',
'AVDPJ6625Q',
'AILPB4040L',
'AIGPY4998R',
'DFLPS3391B',
'BKJPB9542C',
'ALJPP3704G')
);