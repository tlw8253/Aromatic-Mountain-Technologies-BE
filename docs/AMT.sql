-- amt_online_sys

-- DROP TABLE IF EXISTS ers_reimbursement;
DROP TABLE IF EXISTS amt_users;
-- DROP TABLE IF EXISTS ers_user_roles;
-- DROP TABLE IF EXISTS ers_reimbursement_status;
-- DROP TABLE IF EXISTS ers_reimbursement_type;


SELECT * FROM amt_users;
SELECT * FROM amt_user_roles;
-- SELECT * FROM ers_reimbursement;
-- SELECT * FROM ers_reimbursement_status;
-- SELECT * FROM ers_reimbursement_type;


SELECT * FROM ers_user_roles ur WHERE ur.user_role = 'SUPERMAN';
