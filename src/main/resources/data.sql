CALL NEXT VALUE FOR user_sequence;
INSERT INTO tbl_user (birthday,created_at,email,password,user_name,pin,id) VALUES ( '1999-06-11', '2022-12-05', 'kayaom17@itu.edu.tr','123456','erenkaya',1234,1);
CALL NEXT VALUE FOR user_sequence;
INSERT INTO tbl_user (birthday,created_at,email,password,user_name,pin,id) VALUES ( '1999-06-11', '2022-12-05', 'erenkaya@erenkaya.com.tr','123456','erenkjaer',1234,2);
CALL NEXT VALUE FOR tweet_sequence;
INSERT INTO tbl_tweet (content,created_at,owner_id,id) VALUES('Hi I am new to twitter!','2022-12-05 21:20:20.275953',1,1);
CALL NEXT VALUE FOR tweet_sequence;
INSERT INTO tbl_tweet (content,created_at,owner_id,id) VALUES('Hi I am new to twitter!','2022-12-05 21:20:20.275953',2,2);
