SELECT
	p.id,
    CASE
		WHEN (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "FAVOR" and poll_id = p.id) > (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "AGAINST" AND poll_id = p.id) THEN "APPROVED"
        WHEN (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "FAVOR" and poll_id = p.id) < (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "AGAINST" AND poll_id = p.id) THEN "REFUSED"
        WHEN (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "FAVOR" and poll_id = p.id) = (SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "AGAINST" AND poll_id = p.id) THEN "TIE"
    END as result,
    pv.voted_by
FROM poll.polls as p 
	INNER JOIN poll.poll_votes as pv ON p.id = pv.poll_id
    INNER JOIN poll.users as u ON pv.voted_by = u.id
WHERE expires_at < NOW() AND reported_at IS NULL
GROUP BY p.id, result, pv.voted_by