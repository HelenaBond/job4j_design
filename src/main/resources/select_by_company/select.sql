-- 1
SELECT
	p.name person_name,
    c.name company_name
FROM person p
LEFT JOIN company c ON p.company_id = c.id
WHERE p.company_id <> 5 OR p.company_id IS NULL;

-- 2
SELECT
    c.name company_name,
    COUNT(p.company_id) person_count
FROM company c
JOIN person p ON c.id = p.company_id
GROUP BY c.id
HAVING COUNT(p.company_id) = (
    SELECT COUNT(p.company_id) person_count
    FROM person p
    GROUP BY p.company_id
    ORDER BY count(company_id) DESC
    LIMIT 1
);
