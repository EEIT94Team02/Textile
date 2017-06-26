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
('2016-01-01 08:32:09.712','Y','Y','Y','N','system@textile.com','O4iF036PE3TjpWwHPPCSSQ==','系統公告','1989-06-04','A123456789','M','臺北市','大安區','復興南路一段390號 2,3,15樓','0987654321','系統','system',0,0,0,0,0,0,0,0,0,0,'我不會回答你的問題。','19700101999999990001'),
('2016-01-01 08:32:09.712','Y','Y','Y','N','textile@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','系統管理員','1989-06-04','A123456789','F','臺北市','大安區','復興南路一段390號 2,3,15樓','0987654321','系統管理員','manager',0,0,0,0,0,0,0,0,0,0,'我很有權力。','19700101999999990002'),
('2016-06-20 17:35:23.075','Y','Y','N','N','juan336830@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','Juan Salmon','2004-05-08','A190886062','M','臺北市','中山區','復興北路166號','0989836513','鄉民的長度','30cm',0,0,0,2,1,0,3,2,0,1,'Juan Salmon是個天然黑系鄰家哥哥，外表可能看不出來、但是他其實是一位偵探。(?!有紅色的刺蝟頭，眼睛是金色。平常身上可能帶著鐮刀、狼牙棒、護身符或是手槍。(這樣子沒問題嗎...','19700101999999990001'),
('2016-05-21 21:15:38.670','Y','Y','N','N','zachary637087@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Zachary Evans','2002-10-18','A181112559','M','臺北市','中山區','台北市中山區遼寧街128巷1號','0948538925','你家住','海邊嗎？',0,0,0,2,4,0,3,0,6,7,'Zachary Evans有著橙色的雙辮，膚色是偏黃，瞳色橘紅色。身高169cm，主要的穿著為運動內衣，配件是兔耳和筆電。','19700101999999990001'),
('2016-01-06 05:26:07.612','Y','Y','N','N','mercedes796589@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','Mercedes Wright','1987-09-19','A174290155','M','臺北市','內湖區','內湖路一段520號','0961571685','8+9=','義氣',0,0,21,5,1,1,0,3,5,3,'Mercedes Wright有著黃色的妹妹頭短髮，膚色是偏紫，瞳色一藍一綠。身高200cm，主要的穿著為文字T-shirt，配件是漁夫帽和PSP。','19700101999999990001'),
('2016-08-03 16:15:46.416','Y','Y','N','N','essie822611@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Essie Moore','1988-01-21','A230134834','F','臺北市','大安區','和平東路二段134號','0961650433','我媽的體重','87kg',95,401,8,4,3,1,1,0,10,2,'Essie Moore有著淺紫色的日系中長髮，膚色是偏紅，瞳色一藍一綠。身高175cm，主要的穿著為斗篷，配件是翅膀和兔子娃娃。','19700101999999990002'),
('2016-07-17 09:33:23.500','Y','Y','N','N','stacy699613@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','Stacy Evans','2009-05-13','A294319000','F','臺北市','北投區','裕民六路128號','0981913403','我爸爸的信用卡帳號','3856-2874-1083-9487',125,232,0,1,1,0,1,2,1,4,'Stacy Evans有著黃色的兩側剃掉的短髮，膚色是偏白，瞳色深紫色。身高150cm，主要的穿著為長版外套，配件是兔耳和小刀。','19700101999999990002'),
('2016-10-17 07:23:04.596','Y','Y','N','N','angie520319@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','曾郁婷','1987-04-05','A222205888','F','臺北市','松山區','撫遠街304號','0920555740','我的寵物','海綿寶寶',239,282,15,5,4,1,4,1,0,7,'曾郁婷有著茶色的中分短髮，膚色是偏黑，瞳色深綠色。身高185cm，主要的穿著為禮服裙/西裝，配件是貓耳和手銬。','19700101999999990002'),
('2016-06-30 17:56:45.190','Y','Y','N','N','erik461982@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','林志翔','1997-12-21','A114775155','M','臺北市','信義區','信義路五段7號','0971254769','我的綽號','小廢狗',614,773,12,5,2,0,3,1,8,2,'林志翔有著棕色的單馬尾，膚色是偏白，瞳色黑色。身高179cm，主要的穿著為燈籠褲，配件是牙套和熊娃娃。','19700101999999990001'),
('2016-05-24 16:25:45.602','Y','Y','N','N','rick13726@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','楊昶瑋','2007-11-22','F133045935','M','新北市','永和區','得和路202號','0975932716','我的第一次','2017-03-18',6305,31,0,1,4,0,4,0,7,4,'楊昶瑋有著黑色的兩側剃掉的長髮，膚色是偏紫，瞳色藍黑色。身高168cm，主要的穿著為卡通圖案的睡衣，配件是皮帶項圈和PSP。','19700101999999990001'),
('2016-09-19 22:53:04.448','Y','Y','N','N','dunbar417060@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','黃志良','2009-02-27','F187467790','M','新北市','永和區','永平路153號','0942410746','我的女朋友','左手',7311,498,0,1,4,0,3,3,11,2,'黃志良有著深咖啡色的黑人頭，膚色是偏白，瞳色鮮紅色。身高162cm，主要的穿著為長版外套，配件是棒球帽和手機。','19700101999999990001'),
('2016-07-19 20:51:38.168','Y','Y','N','N','patrick727330@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','吳宜婷','1982-06-16','F294242170','F','新北市','三重區','新北大道一段11號','0955506086','我的男朋友','抱枕',2367,478,19,6,1,1,2,3,2,5,'吳宜婷有著橘紅色的雙辮，膚色是偏白，瞳色深藍色。身高172cm，主要的穿著為運動內衣/露肚臍背心，配件是棒球帽和酒杯。','19700101999999990002'),
('2016-07-01 11:49:06.811','Y','Y','N','N','jasmine192264@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','吳家桓','1995-05-28','A237146181','F','臺北市','士林區','文林路437號','0990946077','被測試的嗎？','是',2620,1406,15,1,3,1,7,3,2,2,'','19700101999999990002'),
('2016-10-24 21:47:35.784','Y','Y','N','N','emmalee38205@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','陳奇香','1998-09-18','A256206362','F','臺北市','士林區','士商路150號','0927988284','被測試的嗎？','是',9099,756,15,4,2,0,1,1,5,1,'','19700101999999990002'),
('2016-03-18 11:05:05.794','Y','Y','N','N','kaylee101096@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','劉映璇','2003-07-15','A248917945','F','臺北市','北投區','振興街45號','0990890368','被測試的嗎？','是',5868,1750,0,2,3,0,4,1,3,6,'','19700101999999990002'),
('2016-01-28 18:40:48.730','Y','Y','N','N','lorenzo264047@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','陳逸伯','2001-01-18','A144042294','M','臺北市','大同區','大龍街51號','0992884010','被測試的嗎？','是',7184,728,0,3,0,0,0,0,10,1,'','19700101999999990001'),
('2016-09-22 22:24:18.608','Y','Y','N','N','leah968646@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','林淑芬','2001-09-30','A297046200','F','臺北市','萬華區','成都路10號','0991294546','被測試的嗎？','是',7510,107,0,3,0,0,3,0,6,2,'','19700101999999990002'),
('2016-03-05 15:16:22.360','Y','Y','N','N','mandy540248@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','朱又苓','1993-05-11','F268549153','F','新北市','林口區','粉寮路1段101號','0992387644','被測試的嗎？','是',1908,363,15,0,3,3,7,3,1,1,'','19700101999999990002'),
('2016-11-30 20:31:24.637','Y','Y','N','N','ashlee199295@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','李雅毓','2000-04-07','F206625312','F','新北市','五股區','民義路一段255-15號','0910283531','被測試的嗎？','是',2057,159,0,3,0,1,8,1,0,0,'','19700101999999990002'),
('2016-11-10 18:21:23.007','Y','Y','N','N','genesis272709@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','陳欣珊','2003-09-30','F207843252','F','新北市','五股區','西雲路179巷3號','0921825551','被測試的嗎？','是',6334,571,0,2,0,0,0,3,6,4,'','19700101999999990002'),
('2016-11-01 07:05:42.836','Y','Y','N','N','aliza901435@yahoo.com.tw','O4iF036PE3TjpWwHPPCSSQ==','陳凱珮','1982-02-28','F234068096','F','新北市','八里區','博物館路200號','0914199677','被測試的嗎？','是',3228,1339,15,0,4,4,2,0,11,0,'','19700101999999990002'),
('2016-11-29 10:28:41.487','Y','Y','N','N','anita135913@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','吳佩娟','1992-10-01','F286775811','F','新北市','三芝區','圓山村二坪頂69號','0980711710','被測試的嗎？','是',3918,900,0,3,4,0,4,2,6,6,'','19700101999999990002'),
('2016-11-29 10:28:41.487','Y','Y','N','N','dominique329710@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','陳建智','2008-11-19','F182985275','M','新北市','萬里區','磺潭里公館崙52之2號','0997334220','被測試的嗎？','是',8423,1246,0,1,3,0,9,0,7,5,'','19700101999999990001'),
('2016-05-10 19:25:07.119','Y','Y','N','N','derek442200@outlook.com','O4iF036PE3TjpWwHPPCSSQ==','黃子豪','1980-03-03','F145383137','M','新北市','汐止區','建成路59巷2號','0906593803','被測試的嗎？','是',3241,1906,16,1,3,3,0,3,11,3,'','19700101999999990001'),
('2016-08-16 02:15:56.426','Y','Y','N','N','lauren745872@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','譚心怡','2005-06-12','F223272306','F','新北市','石碇區','碇南路二段松柏崎巷','0915297106','被測試的嗎？','是',4342,1071,0,2,0,0,8,3,2,4,'','19700101999999990002'),
('2016-05-27 18:03:22.572','Y','Y','N','N','jose606930@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','林宛貴','2007-03-02','C128054509','M','基隆市','信義區','正信路26號','0905119958','被測試的嗎？','是',6275,1969,0,1,0,0,9,0,11,6,'','19700101999999990001'),
('2016-06-23 22:57:01.628','Y','Y','N','N','jaquan695678@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','陳耀德','1994-06-18','C141511543','M','基隆市','信義區','信二路204號','0971183084','被測試的嗎？','是',2673,578,15,5,0,2,4,3,2,2,'','19700101999999990001'),
('2016-01-18 10:28:49.104','Y','Y','N','N','kurtis936144@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','張信輝','1991-12-17','C147455839','M','基隆市','仁愛區','獅球路48巷175之3號','0990618643','被測試的嗎？','是',2757,1584,10,2,4,3,2,1,8,2,'','19700101999999990001'),
('2016-10-17 15:01:10.826','Y','Y','N','N','julia37529@facebook.com','O4iF036PE3TjpWwHPPCSSQ==','林允亞','1986-12-29','C241168562','F','基隆市','七堵區','崇信街37巷20號','0920277171','被測試的嗎？','是',8896,160,9,1,1,1,8,3,9,3,'','19700101999999990002'),
('2016-07-23 09:27:10.940','Y','Y','N','N','diamond178068@gmail.com','O4iF036PE3TjpWwHPPCSSQ==','王佩珊','1988-09-09','C241922468','F','基隆市','七堵區','實踐路259號','0960594440','被測試的嗎？','是',1708,1776,12,6,4,0,8,3,5,4,'','19700101999999990002')
GO

--=-=-=-=-=-=-=-=-=-=-=- 賴 興趣資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE interest(
	iId int IDENTITY(1,1) PRIMARY KEY,
	iClass tinyint NOT NULL,
	iName Nvarchar(15) UNIQUE NOT NULL,
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 聊天室資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE chatroom(
	cId bigint IDENTITY(1,1) PRIMARY KEY,
	cCreateTime datetime NOT NULL,
	cClass Nvarchar(2) NOT NULL
)
GO

INSERT INTO chatroom VALUES
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-03-01 09:48:07.524','個人'),
('2017-01-23 07:08:24.912','個人'),
('2017-01-28 18:33:29.234','個人'),
('2017-01-11 22:45:35.055','個人'),
('2017-01-08 09:22:12.951','個人'),
('2017-01-05 17:08:28.374','個人'),
('2017-01-30 05:54:50.438','個人'),
('2017-01-24 13:47:44.128','個人'),
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 金鑰儲存資料 -=-=-=-=-=-=-=-=-=-=-=--
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

--=-=-=-=-=-=-=-=-=-=-=- 賴 訊息記錄資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE logs(
	lId bigint IDENTITY(1,1) PRIMARY KEY,
	lCreateTime datetime NOT NULL,
	lLog Nvarchar(max) NOT NULL,
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
(3, '2017-06-30', 350)GO

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
(1, 2, '2016-12-25'),
(1, 5, '2017-01-01'),
(3, 9, '2017-02-21'),
(5, 10, '2017-04-02'),
(2, 9, '2017-05-17'),
(9, 3, '2017-06-12')
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
(3, 1, 10),
(4, 1, 5),
(4, 2, 5),
(5, 1, 1),
(5, 2, 1),
(5, 3, 1),
(6, 1, 10)
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
('2017-05-27 15:15:12','NA','NA','私人',2),
('2017-05-27 15:15:12','大頭貼','大頭貼','公開',3),
('2017-05-28 15:15:12','大頭貼','大頭貼','公開',4),
('2017-05-29 15:15:12','大頭貼','大頭貼','公開',5),
('2017-05-10 15:15:12','大頭貼','大頭貼','好友',6),
('2017-05-22 15:15:12','大頭貼','大頭貼','公開',7),
('2017-05-10 15:15:12','大頭貼','大頭貼','好友',6),
('2017-05-10 15:15:12','大頭貼','大頭貼','公開',6),
('2017-05-10 15:15:12','大頭貼','大頭貼','好友',6)
GO

--=-=-=-=-=-=-=-=-=-=-=- 陳 照片資料 -=-=-=-=-=-=-=-=-=-=-=--
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
('20170527000000010001','\Textile\album\4\-1011803197.jpg','Roger','tennis',3,'大頭貼'),
('20170527000000020001','\Textile\album\5\-1082729221.jpg','Nadal','tennis',4,'大頭貼'),
('20170527000000030001','\Textile\album\6\-1082729222.jpg','Nole','tennis',5,'大頭貼'),
('20170527000000040001','\Textile\album\7\-1082729220.jpg','Sharapova','tennis',6,'大頭貼'),
('20170527000000050001','\Textile\album\6\-1098558611.jpg','Makarova','tennis',7,'大頭貼')
GO

--=-=-=-=-=-=-=-=-=-=-=- 陳 活動資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE activity(
	activityno int IDENTITY(1,1) PRIMARY KEY,
	begintime datetime NOT NULL,
	endtime datetime NOT NULL,
	activityname Nvarchar(20) NOT NULL,
	place Nvarchar(30) NOT NULL,
	interpretation Nvarchar(max) NULL
)
GO

INSERT INTO activity VALUES
('2017-07-31 13:00:00', '2017-07-31 16:00:00' , '大安桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。'),
('2017-07-10 13:00:00', '2017-07-10 16:00:00' , '板橋桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。'),
('2017-07-20 13:00:00', '2017-07-20 16:00:00' , '林口桌遊團' , '台北市林口區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。'),
('2016-05-31 13:00:00', '2016-05-31 16:00:00' , '天母桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。'),
('2017-08-31 13:00:00', '2017-08-31 16:00:00' , '基隆桌遊團' , '台北市大安區忠孝東路三段305號9F1' , '場地費:100元'+char(13)+'攜伴與否:不拘'+char(13)+'報名方式:直接到場參加即可!'+char(10)+char(13)+'對桌遊有興趣的玩家歡迎到場遊玩!除了主打遊戲當然還有其他遊戲可以玩喔~'+char(13)+'有興趣的朋友請加入FB詢問。'+char(13)+'不管是新手還是老手，都歡迎你們加入大安桌遊團一起享受的桌上遊戲的樂趣。')
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
(4,00000003,'待確認'),
(5,00000003,'參與者'),
(4,00000002,'參與者'),
(4,00000005,'參與者'),
(4,00000008,'參與者'),
(4,00000004,'參與者'),
(1,00000003,'待確認'),
(1,00000002,'參與者'),
(1,00000005,'參與者'),
(2,00000003,'發起人'),
(2,00000006,'參與者'),
(3,00000003,'待確認')
GO

--=-=-=-=-=-=-=-=-=-=-=- 周 社交名單資料 -=-=-=-=-=-=-=-=-=-=-=--
CREATE TABLE sociallist(
	userId int FOREIGN KEY REFERENCES member(mId), --會員編號1
	acquaintenceId int FOREIGN KEY REFERENCES member(mId), --會員編號2
	s_type Nvarchar(3) NOT NULL,			--種類
	log_in datetime NOT NULL,				--登錄日期
	s_group Nvarchar(10) DEFAULT '未分類',	--群組
	PRIMARY KEY (userId, acquaintenceId)
)
GO

-- 配合聊天室，1、2、3互為好友。
INSERT INTO sociallist VALUES
(10,3,'未確認','2011-05-22 07:00',DEFAULT),
(11,3,'未確認','2018-07-27 07:00',DEFAULT),
(12,3,'未確認','2018-07-27 07:00',DEFAULT),
(3,13,'追蹤','2010-08-11 07:00','姆咪姆咪'),
(3,14,'黑單','2016-11-02 07:00','資策會'),
(14,3,'黑單','2016-11-02 07:00','資策會'),
(3,4,'好友','2017-05-29 07:00', DEFAULT),
(4,3,'好友','2017-05-29 07:00', DEFAULT),
(3,5,'好友','2017-04-10 07:00','卍煞氣a小白卍'),
(5,3,'好友','2017-04-10 07:00','卍煞氣a小白卍'),
(4,5,'好友','2011-05-22 07:00','七七八八'),
(5,4,'好友','2011-05-22 07:00','七七八八'),
(3,6,'好友','2015-04-20 07:00','五五六六'),
(6,3,'好友','2015-04-20 07:00','五五六六'),
(3,7,'好友','2011-05-22 07:00','七七八八'),
(7,3,'好友','2011-05-22 07:00','七七八八'),
(3,8,'好友','2011-05-22 07:00','七七八八'),
(8,3,'好友','2011-05-22 07:00','七七八八'),
(3,9,'好友','2017-05-29 07:00', DEFAULT),
(9,3,'好友','2017-05-29 07:00', DEFAULT)
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
('系統','臨時維護公告','2017/05/08(五) 17:10 為伺服器穩定  我們將臨時停機維修 開機時間未定 敬請注意我們發布的公告 造成不變敬請見諒! 謝謝!','2017-05-08 17:10','2017-05-08 17:10','2017-05-09 07:00'),
('新聞','優惠來囉!','現在在商店街買滿2990, 就回饋20%點數給您喔! 心動就馬上行動吧!!','2017-05-29 07:00','2017-05-29 07:00','2017-06-12 07:00'),
('活動','夏日冰冰涼活動開跑囉!','夏日炎炎 找不到出去的好夥伴嗎? 來這裡就對了! 全店消暑優惠10%!!, 只要有在本網站打卡拍照  就有機會抽夏威夷3天2夜遊喔!! 心動就馬上行動吧!','2017-05-30 07:00','2017-05-30 07:00','2017-06-30 06:59'),
('新聞','新商品上架了!趕快去看看!!','為感謝各位玩家的支持 我們新增了一些好玩又有趣的新商品 有空就來逛逛商店 玩出新滋味吧!','2017-05-01 07:00','2017-05-30 07:00','2017-06-30 07:00'),
('活動','暑假特賣會!','暑假開始囉! 你還在等甚麼? 水晶或愛心打7折特賣!! 凡購買滿1000元 就能參加暑假抽抽樂活動 詳情活動請參照活動頁面 ','2016-05-31 05:50','2016-05-31 10:00','2016-07-15 07:00'),
('系統','維修公告','2017/06/25(日) 17:10 為伺服器穩定  我們將會執行例常性維修 開機時間未定 敬請注意我們發布的公告 造成不變敬請見諒! 謝謝!','2017-05-08 07:00','2017-05-08 07:00','2017-06-4 06:59'),
('系統','商品購買通知','您剛購買了30顆水晶 如有疑問 請回報給我們的客服 讓我們服務變得更好  謝謝!','2017-05-29 07:00','2017-05-29 07:00','2017-06-1 06:59')
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





