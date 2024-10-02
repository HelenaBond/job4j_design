SELECT
    k.*
FROM kid k
INNER JOIN school s ON k.school_id = s."id"
WHERE k.birthday > '2017-12-31'
    AND s.focus = 'biology';

SELECT
    k.fullname AS "kid name",
    k.average ave,
    s.focus
FROM kid k
INNER JOIN school s ON k.school_id = s."id"
WHERE k.average > 80
ORDER BY ave DESC;

SELECT
    k.fullname AS "Kid Name",
    k.average AS "Average Score",
    s.focus AS "School Focus",
    s.rating AS "School Rating"
FROM kid k
INNER JOIN school s ON k.school_id = s.id
WHERE s.rating IS NULL
ORDER BY k.fullname;
