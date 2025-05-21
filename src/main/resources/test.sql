INSERT INTO student_management.group_entity (id, name, created_at)
WITH RECURSIVE group_sequence AS (
     SELECT 1 AS n
     UNION ALL
     SELECT n + 1 FROM group_sequence WHERE n < 1000
)
SELECT
    n + 1000 AS id,
    CONCAT(
        ELT(MOD(n-1, 8) + 1, 'CS', 'MATH', 'PHYS', 'CHEM', 'BIO', 'ENG', 'HIST', 'ECON'),
        '-',
        LPAD(n, 4, '0')
    ) AS name,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS created_at
FROM group_sequence
