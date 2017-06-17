--Initialwerte fuer die Datenbank


--Loesche zunaechst alles aus den Tabellen Post und USer_
DELETE FROM POST_COMMENTS;
DELETE FROM COMMENT;
DELETE FROM POST;
DELETE FROM USER_;




--WICHTIG: Entgegen der Syntax as dem Video darf bei Values NICHT das attribut davor stehen, sondern eben nur die reinen Werte!!
--Fuege neue User hinzu
--MERKE: Die Hash-Werte wurden mit hashgenerator.de generiert. Andere Tools lieferten kein sha512...
--Passwort setzt sich wie folgt zusammen:
--luan: <salt-hash-Wert>test, ohne leerzeichen, hintereinander
--Rovi: <salt-hash-Wert>test2, ohne leerzeichen, hintereinander
INSERT INTO USER_ (ID, EMAIL, PASSWORD) VALUES
(1,'luan','af68aaf7efd626d4487944e7fd745da3086cbda6d286f845976f2489ec164256ddddf4ca9b712d3e355ff062414e9a7a95cf2584483ce2631d312a030ca4a0ff'),
(2,'Rovi','d88e24c23234217b68b209559ffb1b654aadec940298c784d30cc44ba268ebd99b826794facd9ad4137abcac8896e9ee552dae2c6b2749b907dae6156fdf6f7e');

--Fuege neue Posts hinzu
INSERT INTO POST (id, title, CREATED_AT, AUTHOR_ID) VALUES
(1,'Luans Post', parsedatetime('2017-06-05 17:55', 'yyyy-MM-dd HH:mm') ,1),
(2,'Rovis Post', parsedatetime('2017-06-05 17:55', 'yyyy-MM-dd HH:mm') ,2);

--Fuege Kommentare hinzu
INSERT INTO COMMENT (ID, CREATED_AT, AUTHOR_ID, TEXT) VALUES
(1, parsedatetime('2017-06-05 19:20', 'yyyy-MM-dd HH:mm'), 1, 'comment-1'),
(2, parsedatetime('2017-06-05 19:23', 'yyyy-MM-dd HH:mm'), 1, 'comment-2'),
(3, parsedatetime('2017-06-05 19:24', 'yyyy-MM-dd HH:mm'), 2, 'comment-3');

--Kreuztabelle für Kommentare: Linking der Posts zu den Kommentaren
--Zum Post 1 gehört Kommentar 1;
--Zum Post 1 gehört Kommentar 2
--Zum Post 2 gehört Kommentar 3
INSERT INTO POST_COMMENTS (POST_ID, COMMENTS_ID) VALUES
(1, 1),
(1, 2),
(2, 3);