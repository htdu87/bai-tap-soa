-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2020 at 03:22 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `t2d_qlkh`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DANG_NHAP` (IN `usr` VARCHAR(50), IN `pwd` VARCHAR(100))  NO SQL
SELECT * FROM nguoi_dung WHERE TEN_DANG_NHAP = usr and MAT_KHAU = pwd AND KHOA = 0$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_DS_KHACH_HANG` (IN `keyword` VARCHAR(250))  NO SQL
SELECT * FROM khach_hang
WHERE MA_KHACH_HANG like concat('%',keyword,'%')
	OR TEN_KHACH_HANG like concat('%',keyword,'%')
    OR SO_DT like concat('%',keyword,'%')
ORDER BY KH_ID DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LAY_KHACH_HANG` (IN `idkh` INT)  NO SQL
SELECT * FROM khach_hang WHERE KH_ID=idkh$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LUU_KHACH_HANG` (IN `idkh` INT, IN `makh` VARCHAR(50), IN `tenkh` VARCHAR(250), IN `diachi` VARCHAR(250), IN `sdt` VARCHAR(50), IN `idnd` INT)  NO SQL
IF idkh > 0 THEN
	UPDATE khach_hang SET khach_hang.MA_KHACH_HANG=makh, khach_hang.TEN_KHACH_HANG=tenkh,khach_hang.DIA_CHI=diachi,khach_hang.SO_DT=sdt,ND_ID=idnd WHERE khach_hang.KH_ID=idkh;
ELSE
	INSERT INTO khach_hang(MA_KHACH_HANG,TEN_KHACH_HANG,DIA_CHI,SO_DT, ND_ID) VALUES(makh,tenkh,diachi,sdt,idnd);
END IF$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XOA_KHACH_HANG` (IN `idkh` INT)  NO SQL
DELETE FROM khach_hang WHERE KH_ID=idkh$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `KH_ID` int(11) NOT NULL,
  `MA_KHACH_HANG` varchar(50) NOT NULL,
  `TEN_KHACH_HANG` varchar(250) NOT NULL,
  `DIA_CHI` varchar(250) DEFAULT NULL,
  `SO_DT` varchar(25) DEFAULT NULL,
  `ND_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`KH_ID`, `MA_KHACH_HANG`, `TEN_KHACH_HANG`, `DIA_CHI`, `SO_DT`, `ND_ID`) VALUES
(1, 'KH0001', 'HUỲNH VĂN HẢI', 'Khánh Bình Tây, Trần Văn Thời, Cà Mau', '0948238942', 1),
(3, 'KH0002', 'LÊ VĂN PHĂNG', 'Tân Phú, Thới Bình, Cà Mau', '0948993428', 1),
(4, 'KH0003', 'LÊ QUỐC BẢO', 'Tân Phú, Thới Bình, Cà Mau', '0945039943', 1),
(6, 'KH0004', 'LÊ THU THẢO', 'Tân Duyệt, Đầm Dơi, Cà Mau', '0878445356', 1),
(8, 'KH0005', 'NGUYỄN QUỲNH ANH', 'Trí Phải, Thới Bình, Cà Mau', '0974938425', 1),
(9, 'KH0006', 'ĐỖ THÚY DUY', 'Khánh An, U Minh, Cà Mau', '0978642345', 1),
(13, 'KH0007', 'LÊ THỊ DIỄM', 'Trí Lực, Thời Bình Cà Mau', '0987423095', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `ND_ID` int(11) NOT NULL,
  `TEN_DANG_NHAP` varchar(50) NOT NULL,
  `MAT_KHAU` varchar(100) NOT NULL,
  `HO_TEN` varchar(250) NOT NULL,
  `KHOA` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`ND_ID`, `TEN_DANG_NHAP`, `MAT_KHAU`, `HO_TEN`, `KHOA`) VALUES
(1, 'duht', '02665EEC1156DCB352068B7764F498B0', 'Huỳnh Thanh Dư', 0),
(2, 'thaonv', '00EA8722DCFBFDFB009836C404AC5317', 'Nguyễn Văn Thảo', 0),
(3, 'ducvp', 'A15BCC0D6C1DD315EBFF182E9A23905F', 'Võ Phước Đức', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`KH_ID`),
  ADD UNIQUE KEY `KHACH_HANG_UNQ` (`MA_KHACH_HANG`),
  ADD KEY `FK_NGUOI_DUNG_ND_ID` (`ND_ID`),
  ADD KEY `MA_KHACH_HANG` (`MA_KHACH_HANG`),
  ADD KEY `TEN_KHACH_HANG` (`TEN_KHACH_HANG`),
  ADD KEY `SO_DT` (`SO_DT`);

--
-- Indexes for table `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`ND_ID`),
  ADD UNIQUE KEY `NGUOI_DUNG_UNQ` (`TEN_DANG_NHAP`),
  ADD KEY `MAT_KHAU` (`MAT_KHAU`),
  ADD KEY `KHOA` (`KHOA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `KH_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  MODIFY `ND_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD CONSTRAINT `FK_NGUOI_DUNG_ND_ID` FOREIGN KEY (`ND_ID`) REFERENCES `nguoi_dung` (`ND_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
