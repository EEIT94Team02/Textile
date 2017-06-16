/*
  1. 一般使用者：帳號「juan336830@outlook.com」，密碼「Aa!12345」(預設密碼皆相同)。
     系統管理員：帳號「textile@gmail.com」，密碼「Aa!12345」。

  2. PRIMARY KEY預設就是NOT NULL，不用加NOT NULL；
     FOREIGN KEY會檢查是不是別人的PRIMARY KEY，也不用加NOT NULL。

  3. 改表格結構要先知會大家，新增資料列則不用，但要注意Foreign Key存不存在。

  -- 這個檔案Git好像常常沒更新到，要注意= =。
*/
USE textile
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- 黃 -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE reportImg
GO

DROP TABLE report
GO

DROP TABLE theme
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- 周 -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE announcement
GO

DROP TABLE sociallist
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- 陳 -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
DROP TABLE activity_member
GO

DROP TABLE activity
GO

DROP TABLE photo
GO

DROP TABLE photo_album
GO

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- 李 -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=-=-=-=- 賴 -=-=-=-=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 會員資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('2016-06-20 17:35:23.075','Y','Y','N','juan336830@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','Juan Salmon','2004-05-08','A190886062','M','臺北市中山區復興北路166號','0989836513','鄉民的長度','30cm',0,0,0,2,1,0,3,2,0,1,'Juan Salmon是個天然黑系鄰家哥哥，外表可能看不出來、但是他其實是一位偵探。(?!有紅色的刺蝟頭，眼睛是金色。平常身上可能帶著鐮刀、狼牙棒、護身符或是手槍。(這樣子沒問題嗎...'),
('2016-05-21 21:15:38.670','Y','Y','N','zachary637087@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Zachary Evans','2002-10-18','A181112559','M','臺北市中山區台北市中山區遼寧街128巷1號','0948538925','你家住','海邊嗎？',0,0,0,2,4,0,3,0,6,7,'Zachary Evans有著橙色的雙辮，膚色是偏黃，瞳色橘紅色。身高169cm，主要的穿著為運動內衣，配件是兔耳和筆電。'),
('2016-01-06 05:26:07.612','Y','Y','N','mercedes796589@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','Mercedes Wright','1987-09-19','A174290155','M','臺北市內湖區內湖路一段520號','0961571685','8+9=','義氣',0,0,21,5,1,1,0,3,5,3,'Mercedes Wright有著黃色的妹妹頭短髮，膚色是偏紫，瞳色一藍一綠。身高200cm，主要的穿著為文字T-shirt，配件是漁夫帽和PSP。'),
('2016-08-03 16:15:46.416','Y','Y','N','essie822611@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Essie Moore','1988-01-21','A230134834','F','臺北市大安區和平東路二段134號','0961650433','我媽的體重','87kg',95,401,8,4,3,1,1,0,10,2,'Essie Moore有著淺紫色的日系中長髮，膚色是偏紅，瞳色一藍一綠。身高175cm，主要的穿著為斗篷，配件是翅膀和兔子娃娃。'),
('2016-07-17 09:33:23.500','Y','Y','N','stacy699613@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Stacy Evans','2009-05-13','A294319000','F','臺北市北投區裕民六路128號','0981913403','我爸爸的信用卡帳號','3856-2874-1083-9487',125,232,0,1,1,0,1,2,1,4,'Stacy Evans有著黃色的兩側剃掉的短髮，膚色是偏白，瞳色深紫色。身高150cm，主要的穿著為長版外套，配件是兔耳和小刀。'),
('2016-10-17 07:23:04.596','Y','Y','N','angie520319@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','曾郁婷','1987-04-05','A222205888','F','臺北市松山區撫遠街304號','0920555740','我的寵物','海綿寶寶',239,282,15,5,4,1,4,1,0,7,'曾郁婷有著茶色的中分短髮，膚色是偏黑，瞳色深綠色。身高185cm，主要的穿著為禮服裙/西裝，配件是貓耳和手銬。'),
('2016-06-30 17:56:45.190','Y','Y','N','erik461982@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','林志翔','1997-12-21','A114775155','M','臺北市信義區信義路五段7號','0971254769','我的綽號','小廢狗',614,773,12,5,2,0,3,1,8,2,'林志翔有著棕色的單馬尾，膚色是偏白，瞳色黑色。身高179cm，主要的穿著為燈籠褲，配件是牙套和熊娃娃。'),
('2016-05-24 16:25:45.602','Y','Y','N','rick13726@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','楊昶瑋','2007-11-22','F133045935','M','新北市永和區得和路202號','0975932716','我的第一次','2017-03-18',6305,31,0,1,4,0,4,0,7,4,'楊昶瑋有著黑色的兩側剃掉的長髮，膚色是偏紫，瞳色藍黑色。身高168cm，主要的穿著為卡通圖案的睡衣，配件是皮帶項圈和PSP。'),
('2016-09-19 22:53:04.448','Y','Y','N','dunbar417060@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','黃志良','2009-02-27','F187467790','M','新北市永和區永平路153號','0942410746','我的女朋友','左手',7311,498,0,1,4,0,3,3,11,2,'黃志良有著深咖啡色的黑人頭，膚色是偏白，瞳色鮮紅色。身高162cm，主要的穿著為長版外套，配件是棒球帽和手機。'),
('2016-07-19 20:51:38.168','Y','Y','N','patrick727330@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','吳宜婷','1982-06-16','F294242170','F','新北市三重區新北大道一段11號','0955506086','我的男朋友','抱枕',2367,478,19,6,1,1,2,3,2,5,'吳宜婷有著橘紅色的雙辮，膚色是偏白，瞳色深藍色。身高172cm，主要的穿著為運動內衣/露肚臍背心，配件是棒球帽和酒杯。'),
('2016-01-01 08:32:09.712','Y','Y','N','textile@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','系統管理員','1989-06-04','A123456789','F','106台北市大安區復興南路一段390號 2,3,15樓','0936589241','系統管理員','sa',0,0,21,5,1,1,0,3,5,3,'我很有權力。')
GO

--=-=-=-=-=-=-=-=-=-=-=- 賴 興趣資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE interest(
	iId int IDENTITY(1,1) PRIMARY KEY,
	iClass tinyint NOT NULL,
	iName Nvarchar(10) UNIQUE NOT NULL,
)
GO

INSERT INTO interest VALUES
(1,'單車'),
(1,'賽車'),
(1,'登山'),
(1,'健行'),
(1,'瑜珈'),
(1,'氣功'),
(1,'釣魚'),
(1,'衝浪'),
(2,'籃球'),
(2,'棒球'),
(2,'網球'),
(2,'羽球'),
(2,'桌球'),
(2,'慢跑'),
(2,'游泳'),
(2,'重訓'),
(3,'品酒'),
(3,'品茶'),
(3,'咖啡'),
(3,'中式'),
(3,'日式'),
(3,'韓式'),
(3,'美式'),
(3,'歐式'),
(4,'閱讀'),
(4,'寫作'),
(4,'攝影'),
(4,'書法'),
(4,'繪畫'),
(4,'漫畫'),
(4,'舞蹈'),
(4,'戲劇'),
(5,'工藝'),
(5,'園藝'),
(5,'雕刻'),
(5,'建築'),
(5,'室內'),
(5,'傢俱'),
(5,'平面'),
(5,'服裝'),
(6,'亞洲流行'),
(6,'西洋流行'),
(6,'金屬搖滾'),
(6,'饒舌歌曲'),
(6,'西洋古典'),
(6,'中國國樂'),
(6,'臺灣民謠'),
(6,'日本老歌'),
(7,'電影影劇'),
(7,'動漫小說'),
(7,'寵物豢養'),
(7,'桌遊棋藝'),
(7,'電腦手遊'),
(7,'程式設計'),
(7,'組裝模型'),
(7,'收集古玩'),
(8,'車聚'),
(8,'環島'),
(8,'唱KTV'),
(8,'觀光旅遊'),
(8,'逛街購物'),
(8,'社團活動'),
(8,'宗教活動'),
(8,'極限運動'),
(1,'滑板'),
(1,'扯鈴'),
(1,'滑雪'),
(2,'保齡球'),
(2,'高爾夫球'),
(2,'足球'),
(2,'橄欖球'),
(2,'排球'),
(3,'泰式'),
(3,'印度'),
(3,'南洋'),
(4,'小說'),
(8,'高空彈跳')
GO

--=-=-=-=-=-=-=-=-=-=-=- 賴 興趣明細資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 聊天室資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom(
	cId bigint IDENTITY(1,1) PRIMARY KEY,
	cCreateTime datetime NOT NULL,
	cClass Nvarchar(2) NOT NULL
)
GO

INSERT INTO chatroom VALUES
('2017-01-23 07:08:24.912','個人'),
('2017-01-28 18:33:29.234','個人'),
('2017-01-11 22:45:35.055','個人'),
('2017-01-31 13:12:57.437','群組')
GO

--=-=-=-=-=-=-=-=-=-=-=- 賴 聊天室明細資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 聊天記錄資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 金鑰儲存資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 訊息記錄資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE logs(
	lId bigint IDENTITY(1,1) PRIMARY KEY,
	lCreateTime datetime NOT NULL,
	lLog Nvarchar(200) NOT NULL,
)
GO

--=-=-=-=-=-=-=-=-=-=-=- 李 商品資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('Heart', 20, '送禮用', '美美的愛心，隨時表達情意。', 1, (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\heart.jpg', SINGLE_BLOB) AS x), 35),
('Crystal', 10, '送禮用', '展現支持與鼓勵的最佳首選。', 1, (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\crystal.png', SINGLE_BLOB) AS x), 15),
('WaterDrop', 5, '送禮用', '想關心朋友，卻缺一個起手式嗎？', 1, (SELECT BulkColumn FROM OPENROWSET(BUlK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\waterdrop.png', SINGLE_BLOB) AS x), 7),
('LonerHeart', 5, '自用', '邊緣人自給自足', 0, (SELECT BulkColumn FROM OPENROWSET(BUlK 'C:\Textile\workspace\Textile\src\main\webapp\image\product\heartForLoner.jpg', SINGLE_BLOB) AS x), 7)
GO

--=-=-=-=-=-=-=-=-=-=-=- 李 道具資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 李 交易資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 李 送禮資料 -=-=-=-=-=-=-=-=-=-=-=--
-- 送禮資料不屬於會員被贈與者的子表，所以沒採用DELETE CASCADE。
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

--=-=-=-=-=-=-=-=-=-=-=- 李 交易明細資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 李 送禮明細資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 李 儲值明細資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 陳 相簿資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('2017-05-27 15:15:12','大頭貼','大頭貼','好友',1),
('2017-05-28 15:15:12','大頭貼','大頭貼','公開',2),
('2017-05-29 15:15:12','大頭貼','大頭貼','公開',3),
('2017-05-10 15:15:12','大頭貼','大頭貼','好友',4),
('2017-05-22 15:15:12','大頭貼','大頭貼','好友',5)

--=-=-=-=-=-=-=-=-=-=-=- 陳 照片資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('20170527000000010001','','Roger','tennis',1,'大頭貼','公開'),
('20170527000000020001','','Nadal','tennis',2,'大頭貼','公開'),
('20170527000000030001','','Nole','tennis',5,'大頭貼','公開'),
('20170527000000040001','','Sharapova','tennis',3,'大頭貼','公開'),
('20170527000000050001','','Makarova','tennis',4,'大頭貼','公開')
GO

--=-=-=-=-=-=-=-=-=-=-=- 陳 活動資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('2017-05-31 13:00:00', '2017-05-31 16:00:00' , '大安桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。','公開'),
('2017-05-10 13:00:00', '2017-05-10 16:00:00' , '板橋桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。','公開'),
('2017-05-20 13:00:00', '2017-05-20 16:00:00' , '林口桌遊團' , '台北市林口區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。','公開'),
('2016-05-31 13:00:00', '2016-05-31 16:00:00' , '天母桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。','公開'),
('2017-08-31 13:00:00', '2017-08-31 16:00:00' , '基隆桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。','公開')
GO

--=-=-=-=-=-=-=-=-=-=-=- 陳 活動明細資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE activity_member(
	activityno int FOREIGN KEY REFERENCES activity(activityno),
	mId int FOREIGN KEY REFERENCES member(mId),
	position Nvarchar(5) NOT NULL,
	PRIMARY KEY(activityno, mId)
)
GO

INSERT INTO activity_member VALUES
(4,00000001,'待確認'),
(5,00000001,'待確認'),
(4,00000002,'參與者'),
(4,00000005,'參與者'),
(4,00000003,'參與者'),
(4,00000004,'參與者'),
(1,00000001,'待確認'),
(1,00000002,'參與者'),
(1,00000003,'參與者'),
(2,00000003,'參與者'),
(2,00000001,'待確認'),
(3,00000001,'待確認')
GO

--=-=-=-=-=-=-=-=-=-=-=- 周 社交名單資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE sociallist(
	userId int FOREIGN KEY REFERENCES member(mId), --會員編號1
	acquaintenceId int FOREIGN KEY REFERENCES member(mId), --會員編號2
	s_type Nvarchar(2) NOT NULL,	--種類
	log_in datetime NOT NULL,		--登錄日期
	s_group Nvarchar(10) NOT NULL,	--群組
	PRIMARY KEY (userId, acquaintenceId)
)
GO

-- 配合聊天室，1、2、3互為好友。
INSERT INTO sociallist VALUES
(1,2,'好友','2017-02-28 07:00','eeit9411'),
(1,3,'好友','2017-02-28 07:00','eeit9411'),
(2,1,'好友','2017-02-28 07:00','eeit9411'),
(2,3,'好友','2017-02-28 07:00','eeit9411'),
(3,1,'好友','2017-02-28 07:00','eeit9411'),
(3,2,'好友','2017-02-28 07:00','eeit9411'),
(1,4,'黑單','2017-05-30 07:00','eeit9406'),
(1,5,'追蹤','2016-06-20 07:00','資策會'),
(1,6,'好友','2015-04-20 07:00','五五六六'),
(1,7,'黑單','2004-04-20 07:00','約會'),
(1,8,'黑單','2004-04-20 07:00','約會')
GO

--=-=-=-=-=-=-=-=-=-=-=- 周 公告資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE announcement(
	a_id int IDENTITY(1,1) PRIMARY KEY, --公告編號
	a_type Nvarchar(2) NOT NULL,		--種類
	gist Nvarchar(50) NOT NULL,			--主旨
	msg Nvarchar(max) NOT NULL,			--內容
	relTime	datetime NOT NULL,			--發布時間
	startTime datetime NOT NULL,		--開始時間	
	endTime	datetime NOT NULL			--結束時間
)
GO

INSERT INTO announcement VALUES
('系統','早安','早安阿!今天儲值了嗎?','2017-05-08 07:00','2017-05-08 07:00','2017-06-4 06:59'),
('新聞','月卡到期通知','你還剩三天 還不快去儲值!?','2017-05-29 07:00','2017-05-29 07:00','2017-06-1 06:59'),
('活動','季卡到期通知','你還剩一個月 還不快去儲值!?','2017-05-30 07:00','2017-05-30 07:00','2017-06-30 06:59'),
('系統','儲值優惠通知','都過了一個禮拜 你還沒儲值!?','2017-05-01 07:00','2017-05-01 07:00','2017-05-30 06:59'),
('活動','網咖時間到囉!','你還剩10分鐘 還不快去加錢!?','2017-05-31 05:50','2017-05-31 10:00','2017-05-31 06:00')
GO

--=-=-=-=-=-=-=-=-=-=-=- 黃 主題資料 -=-=-=-=-=-=-=-=-=-=-=--
-- SQL SERVER沒有boolean，bit 1 = True，0 = false
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

--=-=-=-=-=-=-=-=-=-=-=- 黃 回報資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE report(
	reptNo int IDENTITY(1,1) PRIMARY KEY, --使用流水號方式產生回報編號
	mId int FOREIGN KEY REFERENCES member(mId),	--會員編號
	reptDate datetime NOT NULL,					--回報日期
	reptType Nvarchar(20) NOT NULL,				--回報類別
	reptDetail Nvarchar(max) NOT NULL,			--回報內容
	replyDetail Nvarchar(max),			        --管理員回覆內容
	situation bit NOT NULL --目前回覆狀態，已回覆or未回覆。
)
GO

INSERT INTO report VALUES
(1,'2012-05-31 19:12:00.000','回報種類','問題內容測試','回覆內容測試',1),
(1,'2013-05-31 19:23:00.000','回報種類1','問題內容測試1','回覆內容測試1',1),
(2,'2014-06-30 15:04:00.000','回報種類2','問題內容測試2','回覆內容測試2',0),
(2,'2015-07-31 11:28:00.000','回報種類3','問題內容測試3','回覆內容測試3',0)

--=-=-=-=-=-=-=-=-=-=-=- 黃 回報圖檔資料 -=-=-=-=-=-=-=-=-=-=-=--
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





