--Initialwerte fuer die Datenbank


--Loesche zunaechst alles aus den Tabellen Post und USer_
DELETE FROM POST;
DELETE FROM USER_;
DELETE FROM POST_COMMENTS;
DELETE FROM COMMENT;

--WICHTIG: Entgegen der Syntax as dem Video darf bei Values NICHT das attribut davor stehen, sondern eben nur die reinen Werte!!
--Fuege neue User hinzu
--MERKE: Die Hash-Werte wurden mit hashgenerator.de generiert. Andere Tools lieferten kein sha512...
--Passwort setzt sich wie folgt zusammen:
--luan: HalloLiebeBunteSchoeneWeiteWelt + test --> Zu hash generiert: test=pw, HalloLiebe... = salt
--Rovi: HiTest Hallo liebe bunte schoene Welt --> Zu Hash generiert: test2=pw, HalloLiebe... = salt
INSERT INTO USER_ (ID, EMAIL, PASSWORD) VALUES
(1,'luan','af68aaf7efd626d4487944e7fd745da3086cbda6d286f845976f2489ec164256ddddf4ca9b712d3e355ff062414e9a7a95cf2584483ce2631d312a030ca4a0ff'),
(2,'Rovi','bd303ac1403fc469e8ec8c4b052fed48ba536a815faf5a50a3c6b7c804a4860d81fa276c5a673ef0c6cb14e6ca45bffbe5214cc5a1ecd4a0bbee3a22bd48031b');

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