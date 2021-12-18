-- INSERT USERS

INSERT INTO poll.users VALUES(1, NOW(), 'lucas.c4d+dev@gmail.com', 'Lucas Dev 1', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');
INSERT INTO poll.users VALUES(2, NOW(), 'lucas.c4d+dev1@gmail.com', 'Lucas Dev 2', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');
INSERT INTO poll.users VALUES(3, NOW(), 'lucas.c4d+dev2@gmail.com', 'Lucas Dev 3', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');

-- INSERT POLLS

INSERT INTO poll.polls VALUES(1, 1, 'Uma votação para ir morar na praia', NOW(), NULL, 'Morar na praia');
INSERT INTO poll.polls VALUES(2, 1, 'Uma votação para ir morar na praia', NOW(), NULL, 'Morar na praia');

-- INSERT POLL VOTES

INSERT INTO poll.poll_votes VALUES(1, 'FAVOR', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 1, 1);
INSERT INTO poll.poll_votes VALUES(2, 'FAVOR', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 2, 1);
INSERT INTO poll.poll_votes VALUES(3, 'AGAINST', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 3, 1);

INSERT INTO poll.poll_votes VALUES(4, 'AGAINST', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 1, 2);
INSERT INTO poll.poll_votes VALUES(5, 'AGAINST', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 2, 2);
INSERT INTO poll.poll_votes VALUES(6, 'FAVOR', DATE_SUB(NOW(), INTERVAL 5 MINUTE), 3, 2);