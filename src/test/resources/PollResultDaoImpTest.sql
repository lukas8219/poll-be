-- INSERT USERS

INSERT INTO poll.users(id, created_at, email, name, password, phone_number) VALUES(1, NOW(), 'lucas.c4d+dev@gmail.com', 'Lucas Dev 1', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');
INSERT INTO poll.users(id, created_at, email, name, password, phone_number) VALUES(2, NOW(), 'lucas.c4d+dev1@gmail.com', 'Lucas Dev 2', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');
INSERT INTO poll.users(id, created_at, email, name, password, phone_number) VALUES(3, NOW(), 'lucas.c4d+dev2@gmail.com', 'Lucas Dev 3', '$2a$12$ajECRV3yCUdQ3FpBXznVs.UG2mCOvK78ggZT.KKUQFq0tDzjF6IWW', '51997917162');

-- INSERT POLLS

INSERT INTO poll.polls(id, created_by, description, expires_at, reported_at, subject) VALUES(1, 1, 'Uma votação para ir morar na praia', DATE_SUB(NOW(), INTERVAL 5 DAY), NULL, 'Morar na praia');
INSERT INTO poll.polls(id, created_by, description, expires_at, reported_at, subject) VALUES(2, 1, 'Uma votação para ir morar na praia', DATE_SUB(NOW(), INTERVAL 5 DAY), NULL, 'Morar na praia');

-- INSERT POLL VOTES

INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(1, 'FAVOR', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 1, 1);
INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(2, 'FAVOR', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 2, 1);
INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(3, 'AGAINST', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 3, 1);

INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(4, 'AGAINST', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 1, 2);
INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(5, 'AGAINST', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 2, 2);
INSERT INTO poll.poll_votes(id, decision, voted_at, voted_by, poll_id) VALUES(6, 'FAVOR', DATE_SUB(NOW(), INTERVAL 10 MINUTE), 3, 2);