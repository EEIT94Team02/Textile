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
	mEmailValid varchar(1) NOT NULL,
	mPhoneValid varchar(1) NOT NULL,
	mKeepLogin varchar(1) NOT NULL,
	mEmail varchar(50) UNIQUE NOT NULL,
	mPassword varchar(50) NOT NULL,
	mName Nvarchar(15) UNIQUE NOT NULL,
	mBirthday date NOT NULL,
	mIdentityCardNumber varchar(10) NOT NULL,
	mGender varchar(1) NOT NULL,
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
	mselfIntroduction Nvarchar(300)
)

INSERT INTO member VALUES
('2016-06-20 17:35:23.075','Y','Y','N','juan336830@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','Juan Salmon','2004-05-08','A190886062','M','�O�_�����s�ϴ_���_��166��','0989836513','�m��������','30cm',0,0,0,2,1,0,3,2,0,1,'Juan Salmon�O�ӤѵM�¨t�F�a�����A�~��i��ݤ��X�ӡB���O�L���O�@�찻���C(?!�����⪺����Y�A�����O����C���`���W�i��a���I�M�B�T���ΡB�@���ũάO��j�C(�o�ˤl�S���D��...'),
('2016-05-21 21:15:38.670','Y','Y','N','zachary637087@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Zachary Evans','2002-10-18','A181112559','M','�O�_�����s�ϥx�_�����s�Ͽ���128��1��','0948538925','�A�a��','����ܡH',0,0,0,2,4,0,3,0,6,7,'Zachary Evans���۾�⪺���|�A����O�����A��������C����169cm�A�D�n����۬��B�ʤ���A�t��O�ߦթM���q�C'),
('2016-01-06 05:26:07.612','Y','Y','N','mercedes796589@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','Mercedes Wright','1987-09-19','A174290155','M','�O�_������Ϥ�����@�q520��','0961571685','8+9=','�q��',0,0,21,5,1,1,0,3,5,3,'Mercedes Wright���۶��⪺�f�f�Y�u�v�A����O�����A����@�Ť@��C����200cm�A�D�n����۬���rT-shirt�A�t��O���ҴU�MPSP�C'),
('2016-08-03 16:15:46.416','Y','Y','N','essie822611@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Essie Moore','1988-01-21','A230134834','F','�O�_���j�w�ϩM���F���G�q134��','0961650433','�ڶ����魫','87kg',95,401,8,4,3,1,1,0,10,2,'Essie Moore���۲L���⪺��t�����v�A����O�����A����@�Ť@��C����175cm�A�D�n����۬����O�A�t��O�ͻH�M�ߤl�����C'),
('2016-07-17 09:33:23.500','Y','Y','N','stacy699613@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Stacy Evans','2009-05-13','A294319000','F','�O�_���_��ϸΥ�����128��','0981913403','�ڪ������H�Υd�b��','3856-2874-1083-9487',125,232,0,1,1,0,1,2,1,4,'Stacy Evans���۶��⪺�ⰼ�c�����u�v�A����O���աA����`����C����150cm�A�D�n����۬������~�M�A�t��O�ߦթM�p�M�C'),
('2016-10-17 07:23:04.596','Y','Y','N','angie520319@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','�����@','1987-04-05','A222205888','F','�O�_���Q�s�ϼ�����304��','0920555740','�ڪ��d��','�����_�_',239,282,15,5,4,1,4,1,0,7,'�����@���ۯ��⪺�����u�v�A����O���¡A����`���C����185cm�A�D�n����۬�§�A��/��ˡA�t��O�ߦթM��R�C'),
('2016-06-30 17:56:45.190','Y','Y','N','erik461982@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','�L�ӵ�','1997-12-21','A114775155','M','�O�_���H�q�ϫH�q�����q7��','0971254769','�ڪ��︹','�p�o��',614,773,12,5,2,0,3,1,8,2,'�L�ӵ����۴Ħ⪺�氨���A����O���աA����¦�C����179cm�A�D�n����۬��OŢ�ǡA�t��O���M�M�������C'),
('2016-05-24 16:25:45.602','Y','Y','N','rick13726@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','����޳','2007-11-22','F133045935','M','�s�_���éM�ϱo�M��202��','0975932716','�ڪ��Ĥ@��','2017-03-18',6305,31,0,1,4,0,4,0,7,4,'����޳���۶¦⪺�ⰼ�c�������v�A����O�����A�����Ŷ¦�C����168cm�A�D�n����۬��d�q�Ϯת��Φ�A�t��O�ֱa����MPSP�C'),
('2016-09-19 22:53:04.448','Y','Y','N','dunbar417060@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','���Ө}','2009-02-27','F187467790','M','�s�_���éM�ϥå���153��','0942410746','�ڪ��k�B��','����',7311,498,0,1,4,0,3,3,11,2,'���Ө}���۲`�@�ئ⪺�¤H�Y�A����O���աA�����A����C����162cm�A�D�n����۬������~�M�A�t��O�βy�U�M����C'),
('2016-07-19 20:51:38.168','Y','Y','N','patrick727330@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','�d�y�@','1982-06-16','F294242170','F','�s�_���T���Ϸs�_�j�D�@�q11��','0955506086','�ڪ��k�B��','��E',2367,478,19,6,1,1,2,3,2,5,'�d�y�@���۾���⪺���|�A����O���աA����`�Ŧ�C����172cm�A�D�n����۬��B�ʤ���/�S�{���I�ߡA�t��O�βy�U�M�s�M�C'),
('2016-01-01 08:32:09.712','Y','Y','N','textile@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','�t�κ޲z��','1989-06-04','A123456789','F','106�x�_���j�w�ϴ_���n���@�q390�� 2,3,15��','0936589241','�t�κ޲z��','sa',0,0,21,5,1,1,0,3,5,3,'�ګܦ��v�O�C')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ������ -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE interest(
	iId int IDENTITY(1,1) PRIMARY KEY,
	iClass tinyint NOT NULL,
	iName Nvarchar(10) UNIQUE NOT NULL,
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
	i_dOther varchar(60) NOT NULL,
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
(1,0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}'),
(2,0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}'),
(3,165,'{''key'',[65]}',51,'{''key'',[]}',150,'{''key'',[74]}',45,'{''key'',[]}',61,'{''key'',[]}',71,'{''key'',[]}',247,'{''key'',[]}',138,'{''key'',[]}',63,'{''key'',[]}'),
(4,0,'{''key'',[]}',213,'{''key'',[]}',140,'{''key'',[]}',22,'{''key'',[]}',0,'{''key'',[]}',50,'{''key'',[]}',0,'{''key'',[]}',2,'{''key'',[]}',0,'{''key'',[]}'),
(5,92,'{''key'',[]}',176,'{''key'',[]}',0,'{''key'',[]}',125,'{''key'',[]}',18,'{''key'',[]}',0,'{''key'',[]}',52,'{''key'',[]}',0,'{''key'',[]}',5,'{''key'',[]}'),
(6,199,'{''key'',[]}',96,'{''key'',[]}',149,'{''key'',[]}',0,'{''key'',[]}',46,'{''key'',[]}',151,'{''key'',[]}',102,'{''key'',[]}',56,'{''key'',[]}',22,'{''key'',[]}'),
(7,244,'{''key'',[]}',210,'{''key'',[]}',32,'{''key'',[]}',99,'{''key'',[]}',168,'{''key'',[]}',0,'{''key'',[]}',80,'{''key'',[]}',77,'{''key'',[]}',138,'{''key'',[]}'),
(8,137,'{''key'',[]}',0,'{''key'',[]}',120,'{''key'',[]}',207,'{''key'',[]}',0,'{''key'',[]}',159,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}'),
(9,171,'{''key'',[]}',89,'{''key'',[]}',207,'{''key'',[]}',116,'{''key'',[]}',130,'{''key'',[]}',57,'{''key'',[]}',208,'{''key'',[]}',218,'{''key'',[]}',128,'{''key'',[]}'),
(10,167,'{''key'',[]}',225,'{''key'',[]}',179,'{''key'',[]}',0,'{''key'',[]}',141,'{''key'',[]}',47,'{''key'',[]}',0,'{''key'',[]}',210,'{''key'',[]}',190,'{''key'',[]}'),
(11,0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}',0,'{''key'',[]}')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ��ѫǸ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom(
	cId bigint IDENTITY(1,1) PRIMARY KEY,
	cCreateTime datetime NOT NULL,
	cClass Nvarchar(2) NOT NULL
)
GO

INSERT INTO chatroom VALUES
('2017-01-23 07:08:24.912','�ӤH'),
('2017-01-28 18:33:29.234','�ӤH'),
('2017-01-11 22:45:35.055','�ӤH'),
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
(1,2),
(2,1),
(2,3),
(3,2),
(3,3),
(4,1),
(4,2),
(4,3)
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
(1,1,'2017-03-12 14:03:02.157','Hello!'),
(1,1,'2017-03-12 14:03:10.411','Anyone there?'),
(1,2,'2017-03-12 14:03:58.012','Hi!'),
(1,2,'2017-03-12 14:04:15.591','Hmm?'),
(1,1,'2017-03-12 14:04:30.287','Nice to meet you.'),
(1,2,'2017-03-12 14:04:45.798','Oh! Nice to meet you, too.'),
(1,1,'2017-03-12 14:05:02.058','Where are you from?'),
(1,2,'2017-03-12 14:05:10.190','I was born in U.S.,'),
(1,2,'2017-03-12 14:05:14.570','but I live in Taipei now.'),
(1,1,'2017-03-12 14:05:20.604','Wow, we live in the same city.'),
(1,2,'2017-03-12 14:05:31.971','What a coincidence!'),
(1,1,'2017-03-12 14:05:44.099','Hahaha.'),
(1,2,'2017-03-12 14:06:08.131','And where are you from?'),
(1,1,'2017-03-12 14:06:17.840','I came from India.'),
(1,2,'2017-03-12 14:06:28.514','Nice country, Dude')
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
('BvSeoHWujR6bvc3w',0x2877D6C619544BDE2698C9935A65AE0A,'mEmail'),
('9Rhf5nb2r1tgVPrH',0xC690A5691C71EA446CBC2393CF8A5079,'mPassword'),
('rDVi5G8DEK2j71va',0xEEC0B2CAB3CA1A4A2DB8FAE58F748BEC,'mKeepLogin'),
('1q98PelBUqRtiBjE',0x2762E62E9295C00C2762A856949F3BEA,'cId')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� �T���O����� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE logs(
	lId bigint IDENTITY(1,1) PRIMARY KEY,
	lCreateTime datetime NOT NULL,
	lLog Nvarchar(200) NOT NULL,
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
(2, GETDATE(), 1750),
(1, GETDATE(), 4000),
(3, GETDATE(), 300)
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
(1, 2, GETDATE()),
(1, 5, GETDATE()),
(3, 9, GETDATE())
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
(1, 1, 50),
(1, 2, 50),
(1, 3, 50),
(2, 1, 200),
(3, 1, 15)
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
(3, 1, 10)
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
(1, GETDATE(), 1000, 1000 * 1.4),
(3, GETDATE(), 350, 350 * 1.4),
(1, GETDATE(), 500, 500 * 1.4)
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
('2017-05-27 15:15:12','�j�Y�K','�j�Y�K','�n��',1),
('2017-05-28 15:15:12','�j�Y�K','�j�Y�K','���}',2),
('2017-05-29 15:15:12','�j�Y�K','�j�Y�K','���}',3),
('2017-05-10 15:15:12','�j�Y�K','�j�Y�K','�n��',4),
('2017-05-22 15:15:12','�j�Y�K','�j�Y�K','�n��',5)

--=-=-=-=-=-=-=-=-=-=-=- �� �Ӥ���� -=-=-=-=-=-=-=-=-=-=-=--
Create Table photo(
	photono varchar(20) PRIMARY KEY,
	respath varchar(max) NOT NULL,
	photoname Nvarchar(10) NULL,
	interpretation Nvarchar(150) NULL,
	albumno int FOREIGN KEY REFERENCES photo_album(albumno),
	position Nvarchar(3) NOT NULL,
	visibility Nvarchar(2) NOT NULL
)
GO

INSERT INTO photo VALUES
('20170527000000010001','','Roger','tennis',1,'�j�Y�K','���}'),
('20170527000000020001','','Nadal','tennis',2,'�j�Y�K','���}'),
('20170527000000030001','','Nole','tennis',5,'�j�Y�K','���}'),
('20170527000000040001','','Sharapova','tennis',3,'�j�Y�K','���}'),
('20170527000000050001','','Makarova','tennis',4,'�j�Y�K','���}')
GO

--=-=-=-=-=-=-=-=-=-=-=- �� ���ʸ�� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE activity(
	activityno int IDENTITY(1,1) PRIMARY KEY,
	begintime datetime NOT NULL,
	endtime datetime NOT NULL,
	activityname Nvarchar(20) NOT NULL,
	place Nvarchar(30) NOT NULL,
	interpretation Nvarchar(max) NULL,
	visibility Nvarchar(5) NOT NULL
)
GO

INSERT INTO activity VALUES
('2017-05-31 13:00:00', '2017-05-31 16:00:00' , '�j�w��C��' , '�x�_���j�w�ϩ����F���T�q305��9F1' , '���a�O:100��'+char(13)+'���P�_:����'+char(13)+'���W�覡:��������ѥ[�Y�i!'+char(10)+char(13)+'���C�����쪺���a�w�����C��!���F�D���C����M�٦���L�C���i�H����~'+char(13)+'�����쪺�B�ͽХ[�JFB�߰ݡC'+char(13)+'���ެO�s���٬O�Ѥ�A���w��A�̥[�J�j�w��C�Τ@�_�ɨ�����W�C�����ֽ�C','���}'),
('2017-05-10 13:00:00', '2017-05-10 16:00:00' , '�O����C��' , '�x�_���j�w�ϩ����F���T�q305��9F1' , '���a�O:100��'+char(13)+'���P�_:����'+char(13)+'���W�覡:��������ѥ[�Y�i!'+char(10)+char(13)+'���C�����쪺���a�w�����C��!���F�D���C����M�٦���L�C���i�H����~'+char(13)+'�����쪺�B�ͽХ[�JFB�߰ݡC'+char(13)+'���ެO�s���٬O�Ѥ�A���w��A�̥[�J�j�w��C�Τ@�_�ɨ�����W�C�����ֽ�C','���}'),
('2017-05-20 13:00:00', '2017-05-20 16:00:00' , '�L�f��C��' , '�x�_���L�f�ϩ����F���T�q305��9F1' , '���a�O:100��'+char(13)+'���P�_:����'+char(13)+'���W�覡:��������ѥ[�Y�i!'+char(10)+char(13)+'���C�����쪺���a�w�����C��!���F�D���C����M�٦���L�C���i�H����~'+char(13)+'�����쪺�B�ͽХ[�JFB�߰ݡC'+char(13)+'���ެO�s���٬O�Ѥ�A���w��A�̥[�J�j�w��C�Τ@�_�ɨ�����W�C�����ֽ�C','���}'),
('2016-05-31 13:00:00', '2016-05-31 16:00:00' , '�ѥ���C��' , '�x�_���j�w�ϩ����F���T�q305��9F1' , '���a�O:100��'+char(13)+'���P�_:����'+char(13)+'���W�覡:��������ѥ[�Y�i!'+char(10)+char(13)+'���C�����쪺���a�w�����C��!���F�D���C����M�٦���L�C���i�H����~'+char(13)+'�����쪺�B�ͽХ[�JFB�߰ݡC'+char(13)+'���ެO�s���٬O�Ѥ�A���w��A�̥[�J�j�w��C�Τ@�_�ɨ�����W�C�����ֽ�C','���}'),
('2017-08-31 13:00:00', '2017-08-31 16:00:00' , '�򶩮�C��' , '�x�_���j�w�ϩ����F���T�q305��9F1' , '���a�O:100��'+char(13)+'���P�_:����'+char(13)+'���W�覡:��������ѥ[�Y�i!'+char(10)+char(13)+'���C�����쪺���a�w�����C��!���F�D���C����M�٦���L�C���i�H����~'+char(13)+'�����쪺�B�ͽХ[�JFB�߰ݡC'+char(13)+'���ެO�s���٬O�Ѥ�A���w��A�̥[�J�j�w��C�Τ@�_�ɨ�����W�C�����ֽ�C','���}')
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
(4,00000001,'�ݽT�{'),
(5,00000001,'�ݽT�{'),
(4,00000002,'�ѻP��'),
(4,00000005,'�ѻP��'),
(4,00000003,'�ѻP��'),
(4,00000004,'�ѻP��'),
(1,00000001,'�ݽT�{'),
(1,00000002,'�ѻP��'),
(1,00000003,'�ѻP��'),
(2,00000003,'�ѻP��'),
(2,00000001,'�ݽT�{'),
(3,00000001,'�ݽT�{')
GO

--=-=-=-=-=-=-=-=-=-=-=- �P ����W���� -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE sociallist(
	userId int FOREIGN KEY REFERENCES member(mId), --�|���s��1
	acquaintenceId int FOREIGN KEY REFERENCES member(mId), --�|���s��2
	s_type Nvarchar(2) NOT NULL,	--����
	log_in datetime NOT NULL,		--�n�����
	s_group Nvarchar(10) NOT NULL,	--�s��
	PRIMARY KEY (userId, acquaintenceId)
)
GO

-- �t�X��ѫǡA1�B2�B3�����n�͡C
INSERT INTO sociallist VALUES
(1,2,'�n��','2017-02-28 07:00','eeit9411'),
(1,3,'�n��','2017-02-28 07:00','eeit9411'),
(2,1,'�n��','2017-02-28 07:00','eeit9411'),
(2,3,'�n��','2017-02-28 07:00','eeit9411'),
(3,1,'�n��','2017-02-28 07:00','eeit9411'),
(3,2,'�n��','2017-02-28 07:00','eeit9411'),
(1,4,'�³�','2017-05-30 07:00','eeit9406'),
(1,5,'�l��','2016-06-20 07:00','�굦�|'),
(1,6,'�n��','2015-04-20 07:00','��������'),
(1,7,'�³�','2004-04-20 07:00','���|'),
(1,8,'�³�','2004-04-20 07:00','���|')
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
('�t��','���w','���w��!�����x�ȤF��?','2017-05-08 07:00','2017-05-08 07:00','2017-06-4 06:59'),
('�s�D','��d����q��','�A�ٳѤT�� �٤��֥h�x��!?','2017-05-29 07:00','2017-05-29 07:00','2017-06-1 06:59'),
('����','�u�d����q��','�A�ٳѤ@�Ӥ� �٤��֥h�x��!?','2017-05-30 07:00','2017-05-30 07:00','2017-06-30 06:59'),
('�t��','�x���u�f�q��','���L�F�@��§�� �A�٨S�x��!?','2017-05-01 07:00','2017-05-01 07:00','2017-05-30 06:59'),
('����','���@�ɶ����o!','�A�ٳ�10���� �٤��֥h�[��!?','2017-05-31 05:50','2017-05-31 10:00','2017-05-31 06:00')
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





