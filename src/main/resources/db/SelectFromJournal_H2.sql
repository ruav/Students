SELECT j.ID, j.STUDENT_ID, j.LECTURE_ID, j.RECORD_DATE,
  s.NAME, s.SURNAME, s.SEX, s.GROUPNUMBER,
  l.TOPIC, l.DURATION
FROM Journal j
  INNER JOIN Students s ON j.STUDENT_ID = s.ID
  INNER JOIN Lectures l ON j.LECTURE_ID = l.ID;

SELECT j.ID, j.STUDENT_ID, j.LECTURE_ID, j.RECORD_DATE,
  s.NAME, s.SURNAME, s.SEX, s.GROUPNUMBER,
  l.TOPIC, l.DURATION
FROM Journal j
  INNER JOIN Students s ON j.STUDENT_ID = s.ID
  INNER JOIN Lectures l ON j.LECTURE_ID = l.ID
WHERE j.ID = 1;