DROP TABLE IF EXISTS `Account`;
DROP TABLE IF EXISTS `User`; # RESTRICT, 마지막에 삭제.

CREATE TABLE `User` (
    `Idx` INT NOT NULL AUTO_INCREMENT,
    `Name` CHAR(10) NOT NULL,
    `FRONT_PIN` CHAR(6) NOT NULL, # 주민등록번호 앞자리
    `BACK_PIN` CHAR(7) NOT NULL, # 주민등록번호 뒷자리
    `Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX `BACK_PIN`(`BACK_PIN`),
    PRIMARY KEY(`Idx`, `BACK_PIN`)
) COMMENT = "유저의 정보를 담은 테이블";

CREATE TABLE `Account` (
    `Idx` INT NOT NULL AUTO_INCREMENT,
    `BACK_PIN` CHAR(7) NOT NULL , # 사용자 구별용 주민등록번호 뒷자리
    `Money` BIGINT NOT NULL DEFAULT 0, # 가상 현금
    `Balance` BIGINT NOT NULL DEFAULT 0, # 가상 계좌 속 잔고
    `Account` BIGINT NOT NULL DEFAULT 0, # 계좌
    `Password` VARCHAR(255) NOT NULL DEFAULT "None", # 계좌 비밀번호
    PRIMARY KEY(`Idx`, `BACK_PIN`),
    FOREIGN KEY(`BACK_PIN`) REFERENCES `User`(`BACK_PIN`) ON UPDATE CASCADE ON DELETE RESTRICT
) COMMENT = "계좌 정보를 담은 테이블";