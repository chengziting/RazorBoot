drop table if exists users;
CREATE TABLE `razor`.`users` (
  `Id` varchar(36) NOT NULL ,
  `Name` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `Phone` VARCHAR(11) NULL,
  `Email` VARCHAR(45) NOT NULL,
  `QQ` VARCHAR(45) NULL,
  `Status` INT NOT NULL DEFAULT 0,
  `CreateDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdateDate` DATETIME NULL,
  `RoleId` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT FK_User_Role foreign key (RoleId) references roles(id) on delete no action on update no action,
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC));




  CREATE TABLE `roles` (
  `Id` VARCHAR (36) NOT NULL ,
  `Name` varchar(45) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '0',
  `CreateDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `userrole` (
  `Id` varchar(38) NOT NULL,
  `UserId` varchar(38) NOT NULL,
  `RoleId` varchar(38) NOT NULL,
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_ROLE_idx` (`RoeId`),
  KEY `FK_USERINFO_idx` (`UserId`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`RoeId`) REFERENCES `roles` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USERINFO` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists UserFile;
create table UserFile(
Id varchar(38) not null primary key,
FilePath longtext not null,
FileType varchar(20),
Status int not null default 0,
UserId varchar(38) not null,
CreateDate datetime not null default CURRENT_TIMESTAMP,
UpdateDate datetime,
constraint FK_FILE_USER foreign key (UserId) references Users (Id) on delete no action on update no action
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*
--init data
insert into roles(id,name) values('dd3bfcc7-3325-11e8-9dfb-64006a54771e','guest');
insert into roles(id,name) values('dd43b2a2-3325-11e8-9dfb-64006a54771e','user');
insert into roles(id,name) values('dd4b3168-3325-11e8-9dfb-64006a54771e','admin');

--insert a admin
--password:123
insert into users(id,name,password,email,roleid) values(uuid(),'admin','624D6EB59027452E467BF35FCD2296D7','admin@razor.com','dd4b3168-3325-11e8-9dfb-64006a54771e');
*/