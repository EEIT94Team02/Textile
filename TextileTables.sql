/*
  1. �@��ϥΪ̡G�b���ujuan336830@outlook.com�v�A�K�X�uAa!12345�v(�w�]�K�X�ҬۦP)�C
     �t�κ޲z���G�b���utextile@gmail.com�v�A�K�X�uAa!12345�v�C

  2. PRIMARY KEY�w�]�N�ONOT NULL�A���Υ[NOT NULL�F
     FOREIGN KEY�|�ˬd�O���O�O�H��PRIMARY KEY�A�]���Υ[NOT NULL�C

  3. ���浲�c�n�����|�j�a�A�s�W��ƦC�h���ΡA���n�`�NForeign Key�s���s�b�C

  -- �o���ɮ�Git�n���`�`�S��s��A�n�`�N= =�C
*/
USE textile
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- �� -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE reportImg
GO

DROP TABLE report
GO

DROP TABLE theme
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- �P -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE announcement
GO

DROP TABLE sociallist
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- �� -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE activity_member
GO

DROP TABLE activity
GO

DROP TABLE photo
GO

DROP TABLE photo_album
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- �� -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE deposit
GO

DROP TABLE gift_detail
GO

DROP TABLE deal_detail
GO

DROP TABLE gift
GO

DROP TABLE deal
GO

DROP TABLE item
GO

DROP TABLE product
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- �� -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE logs
GO

DROP TABLE secure
GO

DROP TABLE chatroom_log
GO

DROP TABLE chatroom_member
GO

DROP TABLE chatroom
GO

DROP TABLE interest
GO

DROP TABLE interest_detail
GO

DROP TABLE member
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �|����� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE member(
	mId int IDENTITY(1,1) PRIMARY KEY,
	mCreateTime datetime NOT NULL,
	mValidEmail varchar(1) NOT NULL,
	mValidPhone varchar(1) NOT NULL,
	mValidManager varchar(1) NOT NULL,
	mKeepLogin varchar(1) NOT NULL,
	mEmail varchar(50) UNIQUE NOT NULL,
	mPassword varchar(50) NOT NULL,
	mName Nvarchar(15) NOT NULL,
	mBirthday date NOT NULL,
	mIdentityCardNumber varchar(10) NOT NULL,
	mGender varchar(1) NOT NULL,
	mAddress_County Nvarchar(10) NOT NULL,
	mAddress_Region Nvarchar(10) NOT NULL,
	mAddress Nvarchar(30) NOT NULL,
	mPhoneNumber varchar(10) NOT NULL,
	mHintPassword Nvarchar(20) NOT NULL,
	mHintAnswer Nvarchar(20) NOT NULL,
	mScores int NOT NULL,
	mPoints int NOT NULL,
	mCareer tinyint NOT NULL,
	mEducation tinyint NOT NULL,
	mEconomy tinyint NOT NULL,
	mMarriage tinyint NOT NULL,
	mFamily tinyint NOT NULL,
	mBloodType tinyint NOT NULL,
	mConstellation tinyint NOT NULL,
	mReligion tinyint NOT NULL,
	mselfIntroduction Nvarchar(300),
	mPhotono varchar(20) NOT NULL
)

INSERT INTO member VALUES
('2016-01-01 08:32:09.712','Y','Y','Y','N','system@textile.com','O4iF036PE3TjpWwHPPCSSQ==','�t�Τ��i','1989-06-04','A123456789','M','�O�_��','�j�w��','�_���n���@�q390�� 2,3,15��','0987654321','�t��','system',0,0,0,0,0,0,0,0,0,0,'�ڤ��|�^���A�����D�C','19700101999999990001'),
('2016-01-01 08:32:09.712','Y','Y','Y','N','textile@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�t�κ޲z��','1989-06-04','A123456789','F','�O�_��','�j�w��','�_���n���@�q390�� 2,3,15��','0987654321','�t�κ޲z��','manager',0,0,0,0,0,0,0,0,0,0,'�ګܦ��v�O�C','19700101999999990002'),
('2016-06-20 17:35:23.075','Y','Y','N','N','juan336830@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','Juan Salmon','2004-05-08','A190886062','M','�O�_��','���s��','�_���_��166��','0989836513','�m��������','30cm',0,0,0,2,1,0,3,2,0,1,'Juan Salmon�O�ӤѵM�¨t�F�a�����A�~��i��ݤ��X�ӡB���O�L���O�@�찻���C(?!�����⪺����Y�A�����O����C���`���W�i��a���I�M�B�T���ΡB�@���ũάO��j�C(�o�ˤl�S���D��...','19700101999999990001'),
('2016-05-21 21:15:38.670','Y','Y','N','N','zachary637087@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Zachary Evans','2002-10-18','A181112559','M','�O�_��','���s��','�x�_�����s�Ͽ���128��1��','0948538925','�A�a��','����ܡH',0,0,0,2,4,0,3,0,6,7,'Zachary Evans���۾�⪺���|�A����O�����A��������C����169cm�A�D�n����۬��B�ʤ���A�t��O�ߦթM���q�C','19700101999999990001'),
('2016-01-06 05:26:07.612','Y','Y','N','N','mercedes796589@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','Mercedes Wright','1987-09-19','A174290155','M','�O�_��','�����','������@�q520��','0961571685','8+9=','�q��',0,0,21,5,1,1,0,3,5,3,'Mercedes Wright���۶��⪺�f�f�Y�u�v�A����O�����A����@�Ť@��C����200cm�A�D�n����۬���rT-shirt�A�t��O���ҴU�MPSP�C','19700101999999990001'),
('2016-08-03 16:15:46.416','Y','Y','N','N','essie822611@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Essie Moore','1988-01-21','A230134834','F','�O�_��','�j�w��','�M���F���G�q134��','0961650433','�ڶ����魫','87kg',95,401,8,4,3,1,1,0,10,2,'Essie Moore���۲L���⪺��t�����v�A����O�����A����@�Ť@��C����175cm�A�D�n����۬����O�A�t��O�ͻH�M�ߤl�����C','19700101999999990002'),
('2016-07-17 09:33:23.500','Y','Y','N','N','stacy699613@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Stacy Evans','2009-05-13','A294319000','F','�O�_��','�_���','�Υ�����128��','0981913403','�ڪ������H�Υd�b��','3856-2874-1083-9487',125,232,0,1,1,0,1,2,1,4,'Stacy Evans���۶��⪺�ⰼ�c�����u�v�A����O���աA����`����C����150cm�A�D�n����۬������~�M�A�t��O�ߦթM�p�M�C','19700101999999990002'),
('2016-10-17 07:23:04.596','Y','Y','N','N','angie520319@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','�����@','1987-04-05','A222205888','F','�O�_��','�Q�s��','������304��','0920555740','�ڪ��d��','�����_�_',239,282,15,5,4,1,4,1,0,7,'�����@���ۯ��⪺�����u�v�A����O���¡A����`���C����185cm�A�D�n����۬�§�A��/��ˡA�t��O�ߦթM��R�C','19700101999999990002'),
('2016-06-30 17:56:45.190','Y','Y','N','N','erik461982@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','�L�ӵ�','1997-12-21','A114775155','M','�O�_��','�H�q��','�H�q�����q7��','0971254769','�ڪ��︹','�p�o��',614,773,12,5,2,0,3,1,8,2,'�L�ӵ����۴Ħ⪺�氨���A����O���աA����¦�C����179cm�A�D�n����۬��OŢ�ǡA�t��O���M�M�������C','19700101999999990001'),
('2016-05-24 16:25:45.602','Y','Y','N','N','rick13726@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','����޳','2007-11-22','F133045935','M','�s�_��','�éM��','�o�M��202��','0975932716','�ڪ��Ĥ@��','2017-03-18',6305,31,0,1,4,0,4,0,7,4,'����޳���۶¦⪺�ⰼ�c�������v�A����O�����A�����Ŷ¦�C����168cm�A�D�n����۬��d�q�Ϯת��Φ�A�t��O�ֱa����MPSP�C','19700101999999990001'),
('2016-09-19 22:53:04.448','Y','Y','N','N','dunbar417060@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','���Ө}','2009-02-27','F187467790','M','�s�_��','�éM��','�å���153��','0942410746','�ڪ��k�B��','����',7311,498,0,1,4,0,3,3,11,2,'���Ө}���۲`�@�ئ⪺�¤H�Y�A����O���աA�����A����C����162cm�A�D�n����۬������~�M�A�t��O�βy�U�M����C','19700101999999990001'),
('2016-07-19 20:51:38.168','Y','Y','N','N','patrick727330@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','�d�y�@','1982-06-16','F294242170','F','�s�_��','�T����','�s�_�j�D�@�q11��','0955506086','�ڪ��k�B��','��E',2367,478,19,6,1,1,2,3,2,5,'�d�y�@���۾���⪺���|�A����O���աA����`�Ŧ�C����172cm�A�D�n����۬��B�ʤ���/�S�{���I�ߡA�t��O�βy�U�M�s�M�C','19700101999999990002'),
('2016-07-01 11:49:06.811','Y','Y','N','N','jasmine192264@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�d�a��','1995-05-28','A237146181','F','�O�_��','�h�L��','��L��437��','0990946077','�Q���ժ��ܡH','�O',2620,1406,15,1,3,1,7,3,2,2,'','19700101999999990002'),
('2016-10-24 21:47:35.784','Y','Y','N','N','emmalee38205@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','���_��','1998-09-18','A256206362','F','�O�_��','�h�L��','�h�Ӹ�150��','0927988284','�Q���ժ��ܡH','�O',9099,756,15,4,2,0,1,1,5,1,'','19700101999999990002'),
('2016-03-18 11:05:05.794','Y','Y','N','N','kaylee101096@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','�B�M�','2003-07-15','A248917945','F','�O�_��','�_���','������45��','0990890368','�Q���ժ��ܡH','�O',5868,1750,0,2,3,0,4,1,3,6,'','19700101999999990002'),
('2016-01-28 18:40:48.730','Y','Y','N','N','lorenzo264047@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','���h�B','2001-01-18','A144042294','M','�O�_��','�j�P��','�j�s��51��','0992884010','�Q���ժ��ܡH','�O',7184,728,0,3,0,0,0,0,10,1,'','19700101999999990001'),
('2016-09-22 22:24:18.608','Y','Y','N','N','leah968646@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','�L�Q��','2001-09-30','A297046200','F','�O�_��','�U�ذ�','������10��','0991294546','�Q���ժ��ܡH','�O',7510,107,0,3,0,0,3,0,6,2,'','19700101999999990002'),
('2016-03-05 15:16:22.360','Y','Y','N','N','mandy540248@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','���S�d','1993-05-11','F268549153','F','�s�_��','�L�f��','���d��1�q101��','0992387644','�Q���ժ��ܡH','�O',1908,363,15,0,3,3,7,3,1,1,'','19700101999999990002'),
('2016-11-30 20:31:24.637','Y','Y','N','N','ashlee199295@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','������','2000-04-07','F206625312','F','�s�_��','���Ѱ�','���q���@�q255-15��','0910283531','�Q���ժ��ܡH','�O',2057,159,0,3,0,1,8,1,0,0,'','19700101999999990002'),
('2016-11-10 18:21:23.007','Y','Y','N','N','genesis272709@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','���Y��','2003-09-30','F207843252','F','�s�_��','���Ѱ�','�足��179��3��','0921825551','�Q���ժ��ܡH','�O',6334,571,0,2,0,0,0,3,6,4,'','19700101999999990002'),
('2016-11-01 07:05:42.836','Y','Y','N','N','aliza901435@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','���ͯ\','1982-02-28','F234068096','F','�s�_��','�K����','�ժ��]��200��','0914199677','�Q���ժ��ܡH','�O',3228,1339,15,0,4,4,2,0,11,0,'','19700101999999990002'),
('2016-11-29 10:28:41.487','Y','Y','N','N','anita135913@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�d�خS','1992-10-01','F286775811','F','�s�_��','�T�۰�','��s���G�W��69��','0980711710','�Q���ժ��ܡH','�O',3918,900,0,3,4,0,4,2,6,6,'','19700101999999990002'),
('2016-11-29 10:28:41.487','Y','Y','N','N','dominique329710@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','���ش�','2008-11-19','F182985275','M','�s�_��','�U����','�D�樽���]�[52��2��','0997334220','�Q���ժ��ܡH','�O',8423,1246,0,1,3,0,9,0,7,5,'','19700101999999990001'),
('2016-05-10 19:25:07.119','Y','Y','N','N','derek442200@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','���l��','1980-03-03','F145383137','M','�s�_��','�����','�ئ���59��2��','0906593803','�Q���ժ��ܡH','�O',3241,1906,16,1,3,3,0,3,11,3,'','19700101999999990001'),
('2016-08-16 02:15:56.426','Y','Y','N','N','lauren745872@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','�Ӥߩ�','2005-06-12','F223272306','F','�s�_��','�����','��n���G�q�Q�f�T��','0915297106','�Q���ժ��ܡH','�O',4342,1071,0,2,0,0,8,3,2,4,'','19700101999999990002'),
('2016-05-27 18:03:22.572','Y','Y','N','N','jose606930@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�L�{�Q','2007-03-02','C128054509','M','�򶩥�','�H�q��','���H��26��','0905119958','�Q���ժ��ܡH','�O',6275,1969,0,1,0,0,9,0,11,6,'','19700101999999990001'),
('2016-06-23 22:57:01.628','Y','Y','N','N','jaquan695678@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','��ģ�w','1994-06-18','C141511543','M','�򶩥�','�H�q��','�H�G��204��','0971183084','�Q���ժ��ܡH','�O',2673,578,15,5,0,2,4,3,2,2,'','19700101999999990001'),
('2016-01-18 10:28:49.104','Y','Y','N','N','kurtis936144@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�i�H��','1991-12-17','C147455839','M','�򶩥�','���R��','��y��48��175��3��','0990618643','�Q���ժ��ܡH','�O',2757,1584,10,2,4,3,2,1,8,2,'','19700101999999990001'),
('2016-10-17 15:01:10.826','Y','Y','N','N','julia37529@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','�L����','1986-12-29','C241168562','F','�򶩥�','�C����','�R�H��37��20��','0920277171','�Q���ժ��ܡH','�O',8896,160,9,1,1,1,8,3,9,3,'','19700101999999990002'),
('2016-07-23 09:27:10.940','Y','Y','N','N','diamond178068@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','���ج�','1988-09-09','C241922468','F','�򶩥�','�C����','����259��','0960594440','�Q���ժ��ܡH','�O',1708,1776,12,6,4,0,8,3,5,4,'','19700101999999990002')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ������ -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE interest(
	iId int IDENTITY(1,1) PRIMARY KEY,
	iClass tinyint NOT NULL,
	iName Nvarchar(15) UNIQUE NOT NULL,
)
GO

INSERT INTO interest VALUES
(1,'�樮'),
(1,'�ɨ�'),
(1,'�n�s'),
(1,'����'),
(1,'����'),
(1,'��\'),
(1,'����'),
(1,'�Į�'),
(2,'�x�y'),
(2,'�βy'),
(2,'���y'),
(2,'�вy'),
(2,'��y'),
(2,'�C�]'),
(2,'��a'),
(2,'���V'),
(3,'�~�s'),
(3,'�~��'),
(3,'�@��'),
(3,'����'),
(3,'�馡'),
(3,'����'),
(3,'����'),
(3,'�ڦ�'),
(4,'�\Ū'),
(4,'�g�@'),
(4,'��v'),
(4,'�Ѫk'),
(4,'ø�e'),
(4,'���e'),
(4,'�R��'),
(4,'���@'),
(5,'�u��'),
(5,'����'),
(5,'�J��'),
(5,'�ؿv'),
(5,'�Ǥ�'),
(5,'�í�'),
(5,'����'),
(5,'�A��'),
(6,'�Ȭw�y��'),
(6,'��v�y��'),
(6,'���ݷn�u'),
(6,'�Ǧ޺q��'),
(6,'��v�j��'),
(6,'������'),
(6,'�O�W����'),
(6,'�饻�Ѻq'),
(7,'�q�v�v�@'),
(7,'�ʺ��p��'),
(7,'�d����i'),
(7,'��C����'),
(7,'�q����C'),
(7,'�{���]�p'),
(7,'�ո˼ҫ�'),
(7,'�����j��'),
(8,'���E'),
(8,'���q'),
(8,'��KTV'),
(8,'�[���ȹC'),
(8,'�}���ʪ�'),
(8,'���ά���'),
(8,'�v�Ь���'),
(8,'�����B��'),
(1,'�ƪO'),
(1,'��a'),
(1,'�Ƴ�'),
(2,'�O�ֲy'),
(2,'�����Ҳy'),
(2,'���y'),
(2,'���V�y'),
(2,'�Ʋy'),
(3,'����'),
(3,'�L��'),
(3,'�n�v'),
(4,'�p��'),
(8,'���żu��')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ������Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE interest_detail(
	mId int UNIQUE FOREIGN KEY REFERENCES member(mId),
	i_dMain tinyint NOT NULL,
	i_dRecreation tinyint NOT NULL,
	i_dOtherRecreation varchar(40) NOT NULL,
	i_dExercises tinyint NOT NULL,
	i_dOtherExercises varchar(40) NOT NULL,
	i_dDiet tinyint NOT NULL,
	i_dOtherDiet varchar(40) NOT NULL,
	i_dArt tinyint NOT NULL,
	i_dOtherArt varchar(40) NOT NULL,
	i_dDesign tinyint NOT NULL,
	i_dOtherDesign varchar(40) NOT NULL,
	i_dMusic tinyint NOT NULL,
	i_dOtherMusic varchar(40) NOT NULL,
	i_dHobbies tinyint NOT NULL,
	i_dOtherHobbies varchar(40) NOT NULL,
	i_dActivities tinyint NOT NULL,
	i_dOtherActivities varchar(40) NOT NULL,
)
GO

INSERT INTO interest_detail VALUES
(1,0,0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]'),
(2,0,0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]',0,'[]'),
(3,215,118,'[65,66]',238,'[75]',0,'[]',193,'[76]',0,'[]',32,'[]',120,'[]',8,'[]'),
(4,249,85,'[]',41,'[68,69,70]',128,'[]',93,'[]',93,'[]',0,'[]',0,'[]',241,'[77]'),
(5,191,51,'[]',0,'[]',45,'[74]',61,'[]',71,'[]',247,'[]',138,'[]',63,'[]'),
(6,239,213,'[67]',140,'[]',97,'[74]',0,'[]',50,'[]',22,'[]',2,'[]',143,'[]'),
(7,189,176,'[]',0,'[]',125,'[]',18,'[]',57,'[]',52,'[]',0,'[]',5,'[]'),
(8,223,96,'[65]',149,'[68,71]',0,'[]',46,'[]',101,'[]',102,'[]',56,'[]',22,'[77]'),
(9,247,210,'[66]',32,'[70,72]',99,'[73]',168,'[]',0,'[]',80,'[]',77,'[]',138,'[]'),
(10,107,0,'[]',120,'[69,71,72]',207,'[73]',0,'[]',159,'[]',0,'[]',46,'[]',111,'[]'),
(11,255,89,'[]',207,'[69]',116,'[73,74]',130,'[76]',57,'[]',208,'[]',218,'[]',128,'[]'),
(12,219,225,'[]',179,'[70,71]',0,'[]',141,'[76]',47,'[]',0,'[]',210,'[]',190,'[77]'),
(13,238,28,'[]',123,'[]',23,'[]',0,'[]',255,'[]',45,'[]',31,'[]',0,'[]'),
(14,215,36,'[]',41,'[]',0,'[]',65,'[]',0,'[]',2,'[]',53,'[]',146,'[]'),
(15,127,0,'[]',33,'[]',221,'[]',32,'[]',6,'[]',44,'[]',18,'[]',96,'[]'),
(16,159,154,'[]',0,'[]',0,'[]',184,'[]',240,'[]',117,'[]',111,'[]',85,'[]'),
(17,159,255,'[]',0,'[]',0,'[]',52,'[]',150,'[]',23,'[]',69,'[]',115,'[]'),
(18,203,38,'[]',53,'[]',0,'[]',0,'[]',25,'[]',0,'[]',54,'[]',125,'[]'),
(19,254,147,'[]',178,'[]',59,'[]',1,'[]',4,'[]',108,'[]',55,'[]',0,'[]'),
(20,124,0,'[]',89,'[]',45,'[]',69,'[]',10,'[]',135,'[]',0,'[]',0,'[]'),
(21,93,0,'[]',32,'[]',0,'[]',7,'[]',13,'[]',44,'[]',0,'[]',255,'[]'),
(22,245,69,'[]',161,'[]',33,'[]',235,'[]',0,'[]',86,'[]',0,'[]',235,'[]'),
(23,254,53,'[]',24,'[]',111,'[]',37,'[]',48,'[]',43,'[]',56,'[]',0,'[]'),
(24,213,121,'[]',8,'[]',0,'[]',46,'[]',0,'[]',187,'[]',0,'[]',159,'[]'),
(25,19,0,'[]',0,'[]',0,'[]',159,'[]',0,'[]',0,'[]',158,'[]',88,'[]'),
(26,253,205,'[]',175,'[]',103,'[]',232,'[]',43,'[]',222,'[]',0,'[]',157,'[]'),
(27,118,0,'[]',13,'[]',254,'[]',38,'[]',0,'[]',85,'[]',101,'[]',0,'[]'),
(28,250,1,'[]',16,'[]',230,'[]',6,'[]',85,'[]',0,'[]',116,'[]',0,'[]'),
(29,181,61,'[]',0,'[]',189,'[]',5,'[]',0,'[]',91,'[]',0,'[]',131,'[]'),
(30,211,232,'[]',9,'[]',0,'[]',147,'[]',0,'[]',0,'[]',87,'[]',100,'[]')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ��ѫǸ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom(
	cId bigint IDENTITY(1,1) PRIMARY KEY,
	cCreateTime datetime NOT NULL,
	cClass Nvarchar(2) NOT NULL
)
GO

INSERT INTO chatroom VALUES
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-03-01 09:48:07.524','�ӤH'),
('2017-01-23 07:08:24.912','�ӤH'),
('2017-01-28 18:33:29.234','�ӤH'),
('2017-01-11 22:45:35.055','�ӤH'),
('2017-01-08 09:22:12.951','�ӤH'),
('2017-01-05 17:08:28.374','�ӤH'),
('2017-01-30 05:54:50.438','�ӤH'),
('2017-01-24 13:47:44.128','�ӤH'),
('2017-01-31 13:12:57.437','�s��')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ��ѫǩ��Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom_member(
	cId bigint FOREIGN KEY REFERENCES chatroom(cId),
	mId int FOREIGN KEY REFERENCES member(mId),
	PRIMARY KEY(cId, mId)
)
GO

INSERT INTO chatroom_member VALUES
(1,1),
(2,1),
(2,2),
(3,1),
(3,3),
(4,1),
(4,4),
(5,1),
(5,5),
(6,1),
(6,6),
(7,1),
(7,7),
(8,1),
(8,8),
(9,1),
(9,9),
(10,1),
(10,10),
(11,1),
(11,11),
(12,1),
(12,12),
(13,1),
(13,13),
(14,1),
(14,14),
(15,1),
(15,15),
(16,1),
(16,16),
(17,1),
(17,17),
(18,1),
(18,18),
(19,1),
(19,19),
(20,1),
(20,20),
(21,1),
(21,21),
(22,1),
(22,22),
(23,1),
(23,23),
(24,1),
(24,24),
(25,1),
(25,25),
(26,1),
(26,26),
(27,1),
(27,27),
(28,1),
(28,28),
(29,1),
(29,29),
(30,1),
(30,30),
(31,3),
(31,4),
(32,3),
(32,5),
(33,4),
(33,5),
(34,3),
(34,6),
(35,3),
(35,7),
(36,3),
(36,8),
(37,3),
(37,9),
(38,3),
(38,4),
(38,5),
(38,6)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ��ѰO����� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom_log(
	cId bigint FOREIGN KEY REFERENCES chatroom(cId),
	mId int FOREIGN KEY REFERENCES member(mId),
	cSendTime datetime NOT NULL,
	cContent Nvarchar(200) NOT NULL,
	PRIMARY KEY(cId, mId, cSendTime)
)
GO

INSERT INTO chatroom_log VALUES
(31,3,'2017-03-12 14:03:02.157','Hello!'),
(31,3,'2017-03-12 14:03:10.411','Anyone there?'),
(31,4,'2017-03-12 14:03:58.012','Hi!'),
(31,4,'2017-03-12 14:04:15.591','Hmm?'),
(31,3,'2017-03-12 14:04:30.287','Nice to meet you.'),
(31,4,'2017-03-12 14:04:45.798','Oh! Nice to meet you, too.'),
(31,3,'2017-03-12 14:05:02.058','Where are you from?'),
(31,4,'2017-03-12 14:05:10.190','I was born in U.S.,'),
(31,4,'2017-03-12 14:05:14.570','but I live in Taipei now.'),
(31,3,'2017-03-12 14:05:20.604','Wow, we live in the same city.'),
(31,4,'2017-03-12 14:05:31.971','What a coincidence!'),
(31,3,'2017-03-12 14:05:44.099','Hahaha.'),
(31,4,'2017-03-12 14:06:08.131','And where are you from?'),
(31,3,'2017-03-12 14:06:17.840','I came from India.'),
(31,4,'2017-03-12 14:06:28.514','Nice country, Dude')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ���_�x�s��� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE secure(
	sId int IDENTITY(1,1) PRIMARY KEY,
	sKey varchar(16) NOT NULL,
	sInitVector binary(16) NOT NULL,
	sTarget varchar(16) UNIQUE NOT NULL
)
GO

INSERT INTO secure VALUES
('M1jJfFc6H82TruU4',0x168CDF17D1E44748ADB710ABD4091C05,'mId'),
('BvSeoHWujR6bvc3w',0x2877D6C619544BDE2698C9935A65AE0A,'mEmail'),
('9Rhf5nb2r1tgVPrH',0xC690A5691C71EA446CBC2393CF8A5079,'mPassword'),
('rDVi5G8DEK2j71va',0xEEC0B2CAB3CA1A4A2DB8FAE58F748BEC,'mKeepLogin'),
('1q98PelBUqRtiBjE',0x2762E62E9295C00C2762A856949F3BEA,'cId'),
('Xw1AJUNBe91vsOYp',0xB9DC260AA1D870792AE680B42D8F3772,'albumno'),
('MUtB4SkRCTphfH8w',0x806A29EFD0761A34AB1E9618E8F6E24A,'photono')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �T���O����� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE logs(
	lId bigint IDENTITY(1,1) PRIMARY KEY,
	lCreateTime datetime NOT NULL,
	lLog Nvarchar(max) NOT NULL,
)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �ӫ~��� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE product(
	productId int IDENTITY(1,1) PRIMARY KEY,
	productName Nvarchar(20) NOT NULL,
	unitPrice int NOT NULL,
	category Nvarchar(20) NULL,
	intro Nvarchar(20) NULL,
	status BIT NOT NULL,
	img varbinary(max) NULL,
	rewardPoints int NOT NULL
)
GO

INSERT INTO product (productName, unitPrice, category, intro, status, img, rewardPoints) VALUES
('Heart', 20, '�e§��', '�������R�ߡA�H�ɪ�F���N�C', 1, (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\heart.jpg', SINGLE_BLOB) AS x), 35),
('Crystal', 10, '�e§��', '�i�{����P���y���̨έ���C', 1, (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\crystal.png', SINGLE_BLOB) AS x), 15),
('WaterDrop', 5, '�e§��', '�Q���ߪB�͡A�o�ʤ@�Ӱ_�⦡�ܡH', 1, (SELECT BulkColumn FROM OPENROWSET(BUlK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\waterdrop.png', SINGLE_BLOB) AS x), 7),
('LonerHeart', 5, '�ۥ�', '��t�H�۵��ۨ�', 0, (SELECT BulkColumn FROM OPENROWSET(BUlK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\heartForLoner.jpg', SINGLE_BLOB) AS x), 7)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �D���� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE item(
	memberId int,
	productId int,
	amount int NOT NULL,
	PRIMARY KEY(memberId, productId),
	CONSTRAINT FK_item_product FOREIGN KEY (productId) 
		REFERENCES dbo.product (productId) ON DELETE CASCADE,
	CONSTRAINT FK_item_member FOREIGN KEY (memberId)
		REFERENCES dbo.member (mId) ON DELETE CASCADE
)
GO

INSERT INTO item (memberId, productId, amount) VALUES
(1, 1, 10),
(1, 3, 33),
(2, 2, 22)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ������ -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE deal(
	dealId int IDENTITY(1,1) PRIMARY KEY,
	memberId int,
	dealDate datetime NOT NULL,
	totalCost int NOT NULL,
	CONSTRAINT FK_deal_member FOREIGN KEY (memberId)
		REFERENCES dbo.member (mId) ON DELETE CASCADE
)
GO

INSERT INTO deal (memberId, dealDate, totalCost) VALUES
(2, '2017-01-12', 1750),
(1, '2017-01-18', 4000),
(3, '2017-03-03', 300),
(4, '2017-04-11', 110),
(9, '2017-04-23', 500),
(5, '2017-04-30', 350),
(3, '2017-05-08', 60),
(8, '2017-05-14', 1700),
(1, '2017-06-01', 2000),
(9, '2017-06-07', 10),
(3, '2017-06-30', 350)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �e§��� -=-=-=-=-=-=-=-=-=-=-=--
-- �e§��Ƥ��ݩ�|���Q�ػP�̪��l��A�ҥH�S�ĥ�DELETE CASCADE�C
CREATE TABLE gift(
	giftId int IDENTITY(1,1) PRIMARY KEY,
	giverId int,
	recipientId int,
	giveDate datetime NOT NULL,
	CONSTRAINT FK_gift_giver FOREIGN KEY (giverId)
		REFERENCES dbo.member (mId) ON DELETE CASCADE,
	CONSTRAINT FK_gift_recipient FOREIGN KEY (recipientId)
		REFERENCES dbo.member (mId)
)
GO

INSERT INTO gift (giverId, recipientId, giveDate) VALUES
(1, 2, '2016-12-25'),
(1, 5, '2017-01-01'),
(3, 9, '2017-02-21'),
(5, 10, '2017-04-02'),
(2, 9, '2017-05-17'),
(9, 3, '2017-06-12')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ������Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE deal_detail(
	dealId int,
	productId int,
	amount int NOT NULL,
	PRIMARY KEY (dealId, productId),
	CONSTRAINT FK_dealDetail_deal FOREIGN KEY (dealId)
		REFERENCES dbo.deal (dealId) ON DELETE CASCADE,
	CONSTRAINT FK_dealDetail_product FOREIGN KEY (productId)
		REFERENCES dbo.product (productId) ON DELETE CASCADE
)
GO

INSERT INTO deal_detail (dealId, productId, amount) VALUES
(1, 2, 50),
(1, 3, 50),
(2, 1, 200),
(3, 1, 15),
(4, 1, 4),
(4, 2, 3),
(5, 3, 100),
(6, 1, 10),
(6, 2, 10),
(6, 3, 10),
(7, 1, 2),
(7, 2, 2),
(8, 1, 10),
(8, 2, 100),
(8, 3, 100),
(9, 1, 100),
(10, 1, 1),
(11, 1, 10),
(11, 2, 10),
(11, 3, 10)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �e§���Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE gift_detail(
	giftId int,
	productId int,
	amount int NOT NULL,
	PRIMARY KEY (giftId, productId),
	CONSTRAINT FK_giftDetail_gift FOREIGN KEY (giftId)
		REFERENCES dbo.gift (giftId) ON DELETE CASCADE,
	CONSTRAINT FK_giftDetail_product FOREIGN KEY (productId)
		REFERENCES dbo.product (productId) ON DELETE CASCADE
)
GO

INSERT INTO gift_detail (giftId, productId, amount) VALUES
(1, 2, 15),
(1, 3, 5),
(2, 1, 1),
(2, 2, 5),
(3, 1, 10),
(4, 1, 5),
(4, 2, 5),
(5, 1, 1),
(5, 2, 1),
(5, 3, 1),
(6, 1, 10)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �x�ȩ��Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE deposit(
	depositId int IDENTITY(1,1) PRIMARY KEY,
	memberId int,
	depositDate datetime NOT NULL,
	depositAmount int NOT NULL,
	virtualPoints int NOT NULL,
	CONSTRAINT FK_deposit_member FOREIGN KEY (memberId)
		REFERENCES dbo.member (mId) ON DELETE CASCADE
)
GO

INSERT INTO deposit (memberId, depositDate, depositAmount, virtualPoints) VALUES
(1, '2016-12-22', 1000, 1000 * 1.4),
(3, '2017-03-01', 2000, 2000 * 1.4),
(3, '2017-03-01', 2000, 2000 * 1.4),
(3, '2017-03-01', 2000, 2000 * 1.4),
(4, '2017-04-05', 100, 100 * 1.4),
(9, '2017-04-20', 500, 500 * 1.4),
(5, '2017-04-27', 2000, 2000 * 1.4),
(3, '2017-05-07', 100, 100 * 1.4),
(8, '2017-05-14', 2000, 2000 * 1.4),
(1, '2017-05-29', 1000, 1000 * 1.4),
(1, '2017-05-31', 1000, 1000 * 1.4),
(9, '2017-06-02', 100, 100 * 1.4),
(3, '2017-06-24', 150, 150 * 1.4),
(3, '2017-06-27', 150, 150 * 1.4),
(3, '2017-06-30', 100, 100 * 1.4)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ��ï��� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE photo_album(
	albumno int IDENTITY(1,1) PRIMARY KEY,
	createtime datetime NOT NULL,
	albumname Nvarchar(20) NOT NULL,
	introduction Nvarchar(150) NULL,
	visibility Nvarchar(2) NOT NULL,
	mId int FOREIGN KEY REFERENCES member(mId)
)
GO

INSERT INTO photo_album VALUES
('2017-03-29 15:15:12','NA','NA','�p�H',2),
('2017-05-27 15:15:12','�j�Y�K','�j�Y�K','���}',3),
('2017-05-28 15:15:12','���y','���y����','���}',3),
('2017-05-29 15:15:12','�Y�Y�ܳ�','��!�٭n��h��','�n��',3),
('2017-05-10 15:15:12','�j�۵M','�U�ذʪ�','���}',4),
('2017-05-22 15:15:12','�۩�','�̳̳̥i�R','���}',5),
('2017-05-10 15:15:12','�ؿv��','�ؿv��','�n��',6),
('2017-05-10 15:15:12','Baby','Baby','���}',7),
('2017-05-10 15:15:12','�j�Y�K','�j�Y�K','�p�H',8),
('2017-05-10 15:15:12','�ȹC','�ȹC�����Ӥ�','���}',9),
('2017-05-10 15:15:12','�����','��߮���','�n��',10),
('2017-05-10 15:15:12','�j�Y�K','�j�Y�K','���}',10)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �Ӥ���� -=-=-=-=-=-=-=-=-=-=-=--
Create Table photo(
	photono varchar(20) PRIMARY KEY,
	respath varchar(max) NOT NULL,
	photoname Nvarchar(10) NULL,
	interpretation Nvarchar(150) NULL,
	albumno int FOREIGN KEY REFERENCES photo_album(albumno),
	position Nvarchar(3) NOT NULL
)
GO

INSERT INTO photo VALUES
('19700101999999990001','\Textile\album\2\defaultman.png','default','default',1,'NA'),
('19700101999999990002','\Textile\album\2\defaultwoman.png','default','default',1,'NA'),
('20170527000000030001','\Textile\album\3\-1011803197.jpg','Roger','Roger',3,'�@��'),
('20170527000000030002','\Textile\album\3\-1082729221.jpg','Nadal','Roger',3,'�@��'),
('20170527000000030003','\Textile\album\3\-1082729222.jpg','Nole','Roger',3,'�@��'),
('20170527000000030004','\Textile\album\3\-1082729220.jpg','Sharapova','Roger',3,'�@��'),
('20170527000000030005','\Textile\album\3\-1098558611.jpg','Makarova','Roger',3,'�@��'),
('20170527000000030006','\Textile\album\3\-415909215.jpg','�D��','�D��',4,'�@��'),
('20170527000000030007','\Textile\album\3\-415909212.jpg','�R�q','�R�q',4,'�@��'),
('20170527000000030008','\Textile\album\3\-415909213.jpg','�f�X','�f�X',4,'�@��'),
('20170527000000040001','\Textile\album\4\-415909211.jpg','����','����',5,'�@��'),
('20170527000000040002','\Textile\album\4\-415909216.jpg','�s','�s',5,'�@��'),
('20170527000000040003','\Textile\album\4\-415909214.jpg','��','��',5,'�@��')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ���ʸ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE activity(
	activityno int IDENTITY(1,1) PRIMARY KEY,
	begintime datetime NOT NULL,
	endtime datetime NOT NULL,
	activityname Nvarchar(50) NOT NULL,
	place Nvarchar(50) NOT NULL,
	interpretation Nvarchar(max) NULL
)
GO

INSERT INTO activity VALUES
('2017-07-01 13:00:00','2017-07-01 16:50:00','2017�x�_����s�i','�x�_���H�q�ϪQ�ظ�6��','�ꤺ�ߤ@��X���~�����쪺�M�~�Űs�~����|�C�i�|���׶����h�ꤺ�~���W�s�~�N�z�ӡB�ꤺ�~�s���B�M�~�s�d�M�U�곻�Ŷ��s���סA�󦳦h���S����s�����@�P�i�X�A�P�ɵ��X�M�~���~�׾¡B���q�Ťj�v���y�ҵ{�P�״I���ʵ��A�O�x�W�Ҧ��s�~�����t�ӡB�j�q���ʶR�D�B�ΰs�~�M�~�H�h�P�Ѥ@�󪺤j�����ʥ��x�C'),
('2017-06-23 10:00:00', '2017-06-25 19:00:00' , '�x�_�W�߷�N���N�����|' , '�x�W�x�_���H�q�ϥ��_�n��133��' , 'Tagboat���ߦܤ��Q�T�~�ӡA�@���O���۪�J-���U�~�����N�a�B�P���|��[�s�諸���N�����Ӥ���l�O�C ���ܤ�����S���ʶR���N�~�g�窺�H�̡A�гy�P���N�a��y�B�F�����N�����n���B�A�ç�i�@�B�P�����ûP�ʶR�C ���N�a�N�|�J��P�U���U�˪��[���B�ǥ����V��y�A�i�ӡu�F�����N�B�ְJ���áv�A�óгy��n�����ҡC'),
('2017-07-02 13:00:00', '2017-07-02 17:00:00' , '�|�L�ͬ����N��}�G������v�J���P��@','�s�˿��|�L�m��s��789�������V�̼t���' , '�u�|�L�ͬ����N��}�v�O�s�˿���Ƨ����ʪ��������N���x�p�e�A�Ʊ�����X���ϿW�S���H��P���v�ߵ��A�z�L�@�t�C���ҵ{�A�H���N�����o�ʪ��Ϫ��C���C�����u������v�J���P��@�v�N�ѱM�~���v�a����ϩ~������㦳�G�Ʃʪ��|�L�����A�̫�ѩ~������X�߷R���Ӥ��s�@���������H���C'),
('2017-06-13 19:00:00', '2017-07-11 22:00:00' , '���H�g�A�]�p�ǡU�y��t�ֽs��Cubase','�x�_�������ϭ��y�n���@�q66��9��' , '�u�n���v�N�M�u��ı�v���v�@�˭��n�I���֤w�g���O�t���A�O�D�y���P�x�ɨ��C�o��ұq�u��¦��Сv�X�o�A�����S�����֩��l�]�i�H�ӾǡI���X�u���ֳn��Cubase�v�A���ɪ��q���A���ɪ��y�歵�֡A�u�n�Τ@�xPC��D����L �A�N�઱�X���֪��ֽ�I�`�J�L�X���оǡA��M���ݨ����ֽs���@�ɪ������C'),
('2017-03-14 13:00:00', '2017-03-14 23:00:00' , '�樭�p�ˬ���','�x�_���񱶹B�����R�бЫ�' , '�s���ɩ|�ʺA����}�l�o�A�A�٭n���L�ܡH�ֳ��I�I�u����W�̲��h�I�Y�N�B���A�����ۤv�d����m�a�I'),
('2017-06-10 13:00:00', '2017-06-10 23:00:00' , '�J���̯u�ꪺ�L' ,'�x�_���񱶹B�����R�бЫ�' , '�`�O�@���b�ۤ��H�`�O�����ᤰ��H�`�O�O����H�H�\��ǲ��p�˪��`�`���O�A�γ̻��P�r�֪��覡�{�ѷs�B�͡I���R�P�������R�Ь���A���լ��ʻ��P��y�A�n�n�a���A�I�L���R�иg��B�L�����ѥ[�A���P�b�R�ʩ��i���R�W�ۤv�B�{�ѥL�H�I�ӥ[�J�̮����B�̮ɩ|����ͬ���a�I'),
('2017-07-06 13:30:00', '2017-07-06 16:00:00' , '�Ѳ��޽L�⪺���׷һP�ߪk!' , '�x�_�����s�ϫn�ʪF���G�q36��7��' , '�x�����T�����ߪk�I�W�Z�ڤΤu�{�v�]��b�J�i�H���v�T���`�u�@�A�z�L��ꬰ�ۤv�[�~'),
('2017-03-08 01:00:00', '2017-04-18 00:00:00' , '�ּּs����C��' , '�x�_���H�q�Ϫ�L��132��24��1��' , '�H�q�ϳ̫K�y�����ɳ��a�Ŷ��A24�p�ɥi���ɡA�����֦�������C�A�i���ѹC���P�ҵ{�оǡA�O�j�p�B�ͥ𶢮T�֡B���ʻE�|�B�оǺt���B�U���]���̦n��ܡA���򥻰�A�P�X�W��B�BC�w��w�����Z�C'),
('2017-07-23 14:00:00', '2017-07-23 18:00:00' , '�R�M�H X �p��' , '�x�_���O���ϥ��v��31��(go�M��u�M���{)' , '�k�H���G�|�M�����k�H�n��z�A�ڳ��w�I�k�H���G�|�M�����k�H�n�g�H�A�WMAN�I ���w170cm�B�j�M�H�W(�t�j�M)�Ǿ����߰��d���H�A�u�@�W�L3�~�H�W�Aí�w�u�@�A�u�@�W�����O�j�I'),
('2017-07-16 09:00:00', '2017-07-16 18:00:00' , '�Ÿq�³H�Q' , ' �Ÿq���ӫO�����M�G���F�q8��1��(�Ÿq���H�O�o�i�ҳзs�ǰ|)' , '���P�Ϧa��F���}���Ư঳�Ħa���X�B�Q�λP���s�A�S�ܽХ�������B���s�Υ�����´�ѻP�A���X�a��F���@�P�|��u������ζ«ȪQ�йŸq�³H�Q�v�I'),
('2018-06-14 22:00:00', '2018-07-15 04:00:00' , '2018�@�ɬ�' , ' �Xù��������c�饧����|��' , '2018�~�@�ɬ׹w���ɱN��2015�~3���2017�~11�뤧���i��A����32�Ӥ�A�@�@851�����ɡC�|���ɱN��2018�~6��14���7��15�餧���i��C')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ���ʩ��Ӹ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE activity_member(
	activityno int FOREIGN KEY REFERENCES activity(activityno),
	mId int FOREIGN KEY REFERENCES member(mId),
	position Nvarchar(5) NOT NULL,
	PRIMARY KEY(activityno, mId)
)
GO

INSERT INTO activity_member VALUES
(9,00000003,'�o�_�H'),
(11,00000003,'�o�_�H'),
(1,00000004,'�o�_�H'),
(2,00000005,'�o�_�H'),
(3,00000008,'�o�_�H'),
(4,00000010,'�o�_�H'),
(5,00000012,'�o�_�H'),
(6,00000011,'�o�_�H'),
(7,00000011,'�o�_�H'),
(8,00000016,'�o�_�H'),
(10,00000006,'�o�_�H'),
(1,00000003,'�ݽT�{'),
(5,00000003,'�ݽT�{'),
(6,00000003,'�ݽT�{'),
(7,00000005,'�ݽT�{'),
(5,00000008,'�ݽT�{'),
(4,00000004,'�ݽT�{'),
(1,00000010,'�ݽT�{'),
(6,00000009,'�ݽT�{'),
(4,00000005,'�ݽT�{'),
(10,00000003,'�ѻP��'),
(5,00000006,'�ѻP��'),
(6,00000004,'�ѻP��'),
(8,00000009,'�ѻP��'),
(8,00000007,'�ѻP��'),
(4,00000011,'�ѻP��'),
(10,00000012,'�ѻP��'),
(11,00000010,'�ѻP��'),
(11,00000007,'�ѻP��'),
(5,00000005,'�ѻP��'),
(6,00000012,'�ѻP��'),
(10,00000009,'�ѻP��'),
(11,00000006,'�ѻP��'),
(3,00000005,'�ѻP��'),
(5,00000010,'�ѻP��'),
(5,00000013,'�ѻP��'),
(8,00000020,'�ѻP��'),
(6,00000017,'�ѻP��'),
(5,00000011,'�ѻP��'),
(5,00000018,'�ѻP��'),
(7,00000012,'�ѻP��'),
(7,00000015,'�ѻP��')
GO

--=-=-=-=-=-=-=-=-=-=-=- �P ����W���� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE sociallist(
	userId int FOREIGN KEY REFERENCES member(mId), --�|���s��1
	acquaintenceId int FOREIGN KEY REFERENCES member(mId), --�|���s��2
	s_type Nvarchar(3) NOT NULL,			--����
	log_in datetime NOT NULL,				--�n�����
	s_group Nvarchar(10) DEFAULT '������',	--�s��
	PRIMARY KEY (userId, acquaintenceId)
)
GO

-- �t�X��ѫǡA1�B2�B3�����n�͡C
INSERT INTO sociallist VALUES
(10,3,'���T�{','2011-05-22 07:00',DEFAULT),
(11,3,'���T�{','2018-07-27 07:00',DEFAULT),
(12,3,'���T�{','2018-07-27 07:00',DEFAULT),
(3,13,'�l��','2010-08-11 07:00','�i�}�i�}'),
(3,14,'�³�','2016-11-02 07:00','�굦�|'),
(14,3,'�³�','2016-11-02 07:00','�굦�|'),
(3,4,'�n��','2017-05-29 07:00', DEFAULT),
(4,3,'�n��','2017-05-29 07:00', DEFAULT),
(3,5,'�n��','2017-04-10 07:00','�÷ٮ�a�p����'),
(5,3,'�n��','2017-04-10 07:00','�÷ٮ�a�p����'),
(4,5,'�n��','2011-05-22 07:00','�C�C�K�K'),
(5,4,'�n��','2011-05-22 07:00','�C�C�K�K'),
(3,6,'�n��','2015-04-20 07:00','��������'),
(6,3,'�n��','2015-04-20 07:00','��������'),
(3,7,'�n��','2011-05-22 07:00','�C�C�K�K'),
(7,3,'�n��','2011-05-22 07:00','�C�C�K�K'),
(3,8,'�n��','2011-05-22 07:00','�C�C�K�K'),
(8,3,'�n��','2011-05-22 07:00','�C�C�K�K'),
(3,9,'�n��','2017-05-29 07:00', DEFAULT),
(9,3,'�n��','2017-05-29 07:00', DEFAULT)
GO

--=-=-=-=-=-=-=-=-=-=-=- �P ���i��� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE announcement(
	a_id int IDENTITY(1,1) PRIMARY KEY, --���i�s��
	a_type Nvarchar(2) NOT NULL,		--����
	gist Nvarchar(50) NOT NULL,			--�D��
	msg Nvarchar(max) NOT NULL,			--���e
	relTime	datetime NOT NULL,			--�o���ɶ�
	startTime datetime NOT NULL,		--�}�l�ɶ�	
	endTime	datetime NOT NULL			--�����ɶ�
)
GO

INSERT INTO announcement VALUES
('�t��','�{�ɺ��@���i','2017/05/08(��) 17:10 �����A��í�w  �ڭ̱N�{�ɰ������� �}���ɶ����w �q�Ъ`�N�ڭ̵o�������i �y�����ܷq�Ш���! ����!','2017-05-08 17:10','2017-05-08 17:10','2017-05-09 07:00'),
('�s�D','�u�f���o!','�{�b�b�ө���R��2990, �N�^�X20%�I�Ƶ��z��! �߰ʴN���W��ʧa!!','2017-05-29 07:00','2017-05-29 07:00','2017-06-12 07:00'),
('����','�L��B�B�D���ʶ}�]�o!','�L�骢�� �䤣��X�h���n�٦��? �ӳo�̴N��F! ���������u�f10%!!, �u�n���b���������d���  �N�����|��L�¦i3��2�]�C��!! �߰ʴN���W��ʧa!','2017-05-30 07:00','2017-05-30 07:00','2017-06-30 06:59'),
('�s�D','�s�ӫ~�W�[�F!���֥h�ݬ�!!','���P�¦U�쪱�a����� �ڭ̷s�W�F�@�Ǧn���S���쪺�s�ӫ~ ���ŴN�ӳ}�}�ө� ���X�s�����a!','2017-05-01 07:00','2017-05-30 07:00','2017-06-30 07:00'),
('����','�����S��|!','�����}�l�o! �A�٦b���ƻ�? �����ηR�ߥ�7��S��!! �Z�ʶR��1000�� �N��ѥ[�������֬��� �Ա����ʽаѷӬ��ʭ��� ','2016-05-31 05:50','2016-05-31 10:00','2016-07-15 07:00'),
('�t��','���פ��i','2017/06/25(��) 17:10 �����A��í�w  �ڭ̱N�|����ұ`�ʺ��� �}���ɶ����w �q�Ъ`�N�ڭ̵o�������i �y�����ܷq�Ш���! ����!','2017-05-08 07:00','2017-05-08 07:00','2017-06-4 06:59'),
('�t��','�ӫ~�ʶR�q��','�z���ʶR�F30������ �p���ð� �Ц^�����ڭ̪��ȪA ���ڭ̪A���ܱo��n  ����!','2017-05-29 07:00','2017-05-29 07:00','2017-06-1 06:59')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �D�D��� -=-=-=-=-=-=-=-=-=-=-=--
-- SQL SERVER�S��boolean�Abit 1 = True�A0 = false
CREATE TABLE theme( 
	themeNo int IDENTITY(1,1) PRIMARY KEY,
	themeStyle Nvarchar(max) NOT NULL,
	themeStatus bit NOT NULL,
	mId int FOREIGN KEY REFERENCES member(mId)
)
GO

INSERT INTO theme VALUES
('css/styles1.css',0,1),
('css/styles2.css',1,1),
('css/styles3.css',1,2),
('css/styles4.css',0,2)
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �^����� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE report(
	reptNo int IDENTITY(1,1) PRIMARY KEY, --�ϥάy�����覡���ͦ^���s��
	mId int FOREIGN KEY REFERENCES member(mId),	--�|���s��
	reptDate datetime NOT NULL,					--�^�����
	reptType Nvarchar(20) NOT NULL,				--�^�����O
	reptDetail Nvarchar(max) NOT NULL,			--�^�����e
	replyDetail Nvarchar(max),			        --�޲z���^�Ф��e
	situation bit NOT NULL --�ثe�^�Ъ��A�A�w�^��or���^�СC
)
GO

INSERT INTO report VALUES
(1,'2012-05-31 19:12:00.000','�^������','���D���e����','�^�Ф��e����',1),
(1,'2013-05-31 19:23:00.000','�^������1','���D���e����1','�^�Ф��e����1',1),
(2,'2014-06-30 15:04:00.000','�^������2','���D���e����2','�^�Ф��e����2',0),
(2,'2015-07-31 11:28:00.000','�^������3','���D���e����3','�^�Ф��e����3',0)

--=-=-=-=-=-=-=-=-=-=-=- �� �^�����ɸ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE reportImg(
	reptImgNo int IDENTITY(1,1) PRIMARY KEY,
	imgPath Nvarchar(max) NOT NULL,
	reptNo int FOREIGN KEY REFERENCES report(reptNo)
)
GO

INSERT INTO reportImg VALUES
('/images/snoopy.jpg',1),
('/images/autumn_fs.jpg',1)
GO





