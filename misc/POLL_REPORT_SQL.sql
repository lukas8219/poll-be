SELECT
	p.id as poll_id,
    pv.decision,
    pv.voted_by,
    u.email
FROM poll.polls as p 
	INNER JOIN poll.poll_votes as pv ON p.id = pv.poll_id
    INNER JOIN poll.users as u ON pv.voted_by = u.id
WHERE expires_at > NOW() AND reported_at IS NULL
GROUP BY poll_id, pv.decision, pv.voted_by, u.email

-- CHANGE EXPIRES AT operator to <