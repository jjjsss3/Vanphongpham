-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2021 at 06:56 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vanphongpham`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `stt` int(11) NOT NULL,
  `mahd` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `khuyenmai` decimal(12,0) NOT NULL,
  `thanhtien` decimal(14,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`stt`, `mahd`, `masp`, `soluong`, `khuyenmai`, `thanhtien`) VALUES
(1, 1, 2, 10, '0', '0'),
(2, 1, 3, 11, '0', '0'),
(3, 1, 4, 12, '0', '0'),
(4, 2, 5, 13, '0', '0'),
(5, 2, 6, 14, '0', '0'),
(6, 2, 7, 15, '0', '0'),
(7, 3, 8, 16, '0', '0'),
(8, 3, 9, 17, '0', '0'),
(9, 3, 10, 18, '0', '0'),
(10, 4, 11, 19, '0', '0'),
(11, 4, 12, 20, '0', '0'),
(12, 4, 13, 21, '0', '0'),
(13, 5, 14, 22, '0', '0'),
(14, 5, 15, 23, '0', '0'),
(15, 5, 16, 24, '0', '0'),
(16, 6, 17, 25, '0', '0'),
(17, 6, 18, 26, '0', '0'),
(18, 6, 19, 27, '0', '0'),
(19, 7, 20, 28, '0', '0'),
(20, 7, 21, 2, '0', '0'),
(21, 7, 22, 4, '0', '0'),
(22, 8, 23, 1, '0', '0'),
(23, 8, 24, 2, '0', '0'),
(24, 8, 25, 3, '0', '0'),
(25, 9, 26, 4, '0', '0'),
(26, 9, 27, 5, '0', '0'),
(27, 9, 28, 6, '0', '0'),
(28, 10, 29, 7, '0', '0'),
(29, 10, 30, 8, '0', '0'),
(30, 10, 31, 9, '0', '0'),
(31, 11, 32, 10, '0', '0'),
(32, 11, 33, 11, '0', '0'),
(33, 11, 34, 12, '0', '0'),
(34, 12, 35, 13, '0', '0'),
(35, 12, 36, 14, '0', '0'),
(36, 12, 37, 15, '0', '0'),
(37, 13, 38, 16, '0', '0'),
(38, 13, 39, 17, '0', '0'),
(39, 13, 40, 18, '0', '0'),
(40, 14, 41, 19, '0', '0'),
(41, 14, 42, 20, '0', '0'),
(42, 14, 43, 21, '0', '0'),
(43, 15, 44, 22, '0', '0'),
(44, 15, 45, 23, '0', '0'),
(45, 16, 46, 24, '0', '0'),
(46, 16, 47, 25, '0', '0'),
(47, 16, 48, 26, '0', '0'),
(48, 17, 49, 27, '0', '0'),
(49, 17, 50, 28, '0', '0'),
(50, 17, 51, 29, '0', '0'),
(51, 18, 52, 30, '0', '0'),
(52, 18, 53, 31, '0', '0'),
(53, 18, 54, 32, '0', '0'),
(54, 19, 55, 33, '0', '0'),
(55, 19, 56, 34, '0', '0'),
(56, 19, 57, 35, '0', '0'),
(57, 20, 58, 36, '0', '0'),
(58, 20, 59, 37, '0', '0'),
(59, 20, 60, 38, '0', '0'),
(60, 21, 61, 10, '0', '0'),
(61, 21, 62, 11, '0', '0'),
(62, 21, 1, 12, '0', '0'),
(63, 22, 2, 13, '0', '0'),
(64, 22, 3, 14, '0', '0'),
(65, 22, 4, 15, '0', '0'),
(66, 23, 5, 16, '0', '0'),
(67, 23, 6, 17, '0', '0'),
(68, 23, 7, 18, '0', '0'),
(69, 24, 8, 19, '0', '0'),
(70, 24, 9, 20, '0', '0'),
(71, 25, 10, 21, '0', '0'),
(72, 25, 11, 22, '0', '0'),
(73, 25, 12, 23, '0', '0'),
(74, 26, 13, 24, '0', '0'),
(75, 26, 14, 25, '0', '0'),
(76, 26, 15, 26, '0', '0'),
(77, 27, 16, 27, '0', '0'),
(78, 27, 17, 28, '0', '0'),
(79, 27, 18, 29, '0', '0'),
(80, 28, 19, 30, '0', '0'),
(81, 28, 20, 31, '0', '0'),
(82, 28, 21, 32, '0', '0'),
(83, 29, 22, 33, '0', '0'),
(84, 29, 23, 34, '0', '0'),
(85, 29, 24, 35, '0', '0'),
(86, 30, 25, 36, '0', '0'),
(87, 30, 26, 37, '0', '0'),
(88, 30, 27, 38, '0', '0'),
(89, 31, 28, 39, '0', '0'),
(90, 31, 29, 10, '0', '0'),
(91, 31, 30, 1, '0', '0'),
(92, 32, 31, 2, '0', '0'),
(93, 32, 32, 3, '0', '0'),
(94, 32, 33, 4, '0', '0'),
(95, 33, 34, 5, '0', '0'),
(96, 33, 35, 6, '0', '0'),
(97, 33, 36, 7, '0', '0'),
(98, 34, 37, 8, '0', '0'),
(99, 34, 38, 9, '0', '0'),
(100, 34, 39, 10, '0', '0'),
(101, 35, 40, 11, '0', '0'),
(102, 35, 41, 12, '0', '0'),
(103, 35, 42, 13, '0', '0'),
(104, 36, 43, 14, '0', '0'),
(105, 36, 44, 15, '0', '0'),
(106, 36, 45, 16, '0', '0'),
(107, 37, 46, 17, '0', '0'),
(108, 37, 47, 18, '0', '0'),
(109, 37, 48, 19, '0', '0'),
(110, 38, 49, 20, '0', '0'),
(111, 38, 50, 21, '0', '0'),
(112, 38, 51, 22, '0', '0'),
(113, 39, 52, 23, '0', '0'),
(114, 39, 53, 24, '0', '0'),
(115, 39, 54, 25, '0', '0'),
(116, 40, 55, 26, '0', '0'),
(117, 40, 56, 27, '0', '0'),
(118, 40, 57, 28, '0', '0'),
(119, 41, 58, 29, '0', '0'),
(120, 41, 59, 10, '0', '0'),
(121, 41, 60, 1, '0', '0'),
(122, 42, 61, 2, '0', '0'),
(123, 42, 62, 10, '0', '0'),
(124, 42, 1, 4, '0', '0'),
(125, 43, 2, 5, '0', '0'),
(126, 43, 3, 6, '0', '0'),
(127, 43, 4, 7, '0', '0'),
(128, 43, 5, 8, '0', '0'),
(129, 44, 6, 9, '0', '0'),
(130, 44, 7, 10, '0', '0'),
(131, 44, 8, 11, '0', '0'),
(132, 45, 9, 12, '0', '0'),
(133, 45, 10, 13, '0', '0'),
(134, 45, 11, 14, '0', '0'),
(135, 46, 12, 15, '0', '0'),
(136, 46, 13, 16, '0', '0'),
(137, 46, 14, 17, '0', '0'),
(138, 47, 15, 18, '0', '0'),
(139, 47, 16, 19, '0', '0'),
(140, 47, 17, 20, '0', '0'),
(141, 48, 18, 21, '0', '0'),
(142, 48, 19, 22, '0', '0'),
(143, 48, 20, 23, '0', '0'),
(144, 49, 21, 24, '0', '0'),
(145, 49, 22, 25, '0', '0'),
(146, 49, 23, 26, '0', '0'),
(147, 50, 24, 27, '0', '0'),
(148, 50, 25, 28, '0', '0'),
(149, 50, 26, 29, '0', '0'),
(150, 51, 27, 30, '0', '0'),
(151, 51, 28, 31, '0', '0'),
(152, 51, 29, 32, '0', '0'),
(153, 52, 30, 33, '0', '0'),
(154, 52, 31, 34, '0', '0'),
(155, 52, 32, 35, '0', '0'),
(156, 53, 33, 36, '0', '0'),
(157, 53, 34, 37, '0', '0'),
(158, 53, 35, 38, '0', '0'),
(159, 54, 36, 39, '0', '0'),
(160, 54, 37, 40, '0', '0'),
(161, 54, 38, 41, '0', '0'),
(162, 55, 39, 42, '0', '0'),
(163, 55, 40, 43, '0', '0'),
(164, 55, 41, 44, '0', '0'),
(466, 57, 2, 50, '0', '5000000'),
(467, 57, 3, 50, '0', '3750000'),
(468, 57, 9, 100, '0', '3000000');

-- --------------------------------------------------------

--
-- Table structure for table `chitietkm`
--

CREATE TABLE `chitietkm` (
  `makm` varchar(20) NOT NULL,
  `masp` int(11) NOT NULL,
  `phantramkm` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietkm`
--

INSERT INTO `chitietkm` (`makm`, `masp`, `phantramkm`) VALUES
('trungthu', 7, 10),
('trungthu', 9, 10),
('trungthu', 6, 10),
('trungthu', 5, 10),
('trungthu', 4, 10),
('trungthu', 3, 10),
('trungthu', 2, 10),
('namhocmoi', 56, 30),
('namhocmoi', 58, 30),
('namhocmoi', 60, 30),
('namhocmoi', 55, 30),
('namhocmoi', 54, 30),
('namhocmoi', 53, 30),
('namhocmoi', 52, 30),
('namhocmoi', 51, 30);

-- --------------------------------------------------------

--
-- Table structure for table `chitietpnh`
--

CREATE TABLE `chitietpnh` (
  `mapnh` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongianhap` decimal(10,0) NOT NULL,
  `thanhtien` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietpnh`
--

INSERT INTO `chitietpnh` (`mapnh`, `masp`, `soluong`, `dongianhap`, `thanhtien`) VALUES
(0, 12, 100, '30000', '3000000'),
(0, 11, 100, '30000', '3000000'),
(0, 10, 100, '30000', '3000000'),
(0, 9, 100, '30000', '3000000'),
(0, 7, 200, '30000', '6000000'),
(0, 6, 100, '30000', '3000000'),
(0, 5, 100, '30000', '3000000'),
(0, 4, 100, '30000', '3000000'),
(0, 3, 100, '30000', '3000000'),
(0, 2, 100, '30000', '3000000'),
(0, 45, 10000, '500000', '5000000000'),
(1, 60, 100, '500000', '50000000');

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `machucvu` int(11) NOT NULL,
  `tenchucvu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chucvu`
--

INSERT INTO `chucvu` (`machucvu`, `tenchucvu`) VALUES
(1, 'Quản lí'),
(2, 'Nhân viên bán hàng'),
(9, 'Nhân viên nhập kho'),
(10, 'Nhân viên 1234');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` int(11) NOT NULL,
  `manv` varchar(50) NOT NULL,
  `makh` int(11) NOT NULL,
  `ngay` date NOT NULL,
  `tongtien` decimal(14,0) NOT NULL,
  `tongkm` decimal(14,0) NOT NULL,
  `giamgiakh` decimal(14,0) NOT NULL,
  `thanhtien` decimal(14,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `manv`, `makh`, `ngay`, `tongtien`, `tongkm`, `giamgiakh`, `thanhtien`) VALUES
(1, 'nv01', 1, '2021-05-12', '100000', '10000', '0', '535000'),
(2, 'nv01', 2, '2021-04-01', '110000', '10000', '0', '1030000'),
(3, 'nv01', 3, '2021-03-12', '120000', '10000', '0', '1200000'),
(4, 'nv01', 4, '2021-02-12', '130000', '10000', '0', '2820000'),
(5, 'nv01', 5, '2021-01-12', '140000', '10000', '0', '3630000'),
(6, 'nv01', 6, '2021-05-12', '150000', '10000', '0', '2850000'),
(7, 'nv01', 7, '2021-04-12', '160000', '10000', '0', '930000'),
(8, 'nv01', 8, '2021-03-12', '170000', '10000', '0', '175000'),
(9, 'nv01', 9, '2021-02-12', '180000', '10000', '0', '280000'),
(10, 'nv01', 10, '2021-01-12', '190000', '10000', '0', '530000'),
(11, 'nv01', 11, '2021-05-12', '200000', '10000', '0', '620000'),
(12, 'nv01', 12, '2021-04-12', '210000', '10000', '0', '3580000'),
(13, 'nv01', 13, '2021-03-12', '220000', '10000', '0', '3565000'),
(14, 'nv01', 14, '2021-02-12', '230000', '10000', '0', '4915000'),
(15, 'nv01', 15, '2021-01-12', '240000', '10000', '0', '9370000'),
(16, 'nv01', 16, '2021-05-12', '250000', '10000', '0', '29900000'),
(17, 'nv01', 17, '2021-04-12', '260000', '10000', '0', '32200000'),
(18, 'nv01', 18, '2021-03-12', '270000', '10000', '0', '40600000'),
(19, 'nv01', 19, '2021-02-12', '280000', '10000', '0', '47650000'),
(20, 'nv01', 20, '2021-01-12', '290000', '10000', '0', '42400000'),
(21, 'nv01', 21, '2020-09-12', '300000', '10000', '0', '4750000'),
(22, 'nv01', 22, '2020-08-12', '310000', '10000', '0', '685000'),
(23, 'nv01', 23, '2020-07-12', '320000', '10000', '0', '1255000'),
(24, 'nv01', 24, '2020-06-12', '330000', '10000', '0', '885000'),
(25, 'nv01', 25, '2020-05-12', '340000', '10000', '0', '3300000'),
(26, 'nv01', 26, '2020-04-12', '350000', '10000', '0', '3780000'),
(27, 'nv01', 27, '2020-03-12', '360000', '10000', '0', '2640000'),
(28, 'nv01', 28, '2020-02-12', '370000', '10000', '0', '2760000'),
(29, 'nv01', 29, '2020-01-12', '380000', '10000', '0', '2920000'),
(30, 'nv01', 30, '2020-12-12', '390000', '10000', '0', '2590000'),
(31, 'nv01', 31, '2020-11-12', '400000', '10000', '0', '605000'),
(32, 'nv01', 32, '2020-10-12', '410000', '10000', '0', '170000'),
(33, 'nv01', 33, '2020-09-12', '420000', '10000', '0', '1200000'),
(34, 'nv01', 34, '2020-08-12', '430000', '10000', '0', '2100000'),
(35, 'nv01', 35, '2020-07-12', '440000', '10000', '0', '2585000'),
(36, 'nv01', 36, '2020-06-12', '450000', '10000', '0', '7830000'),
(37, 'nv01', 37, '2020-05-12', '460000', '10000', '0', '21500000'),
(38, 'nv01', 38, '2020-04-12', '470000', '10000', '0', '24150000'),
(39, 'nv01', 39, '2020-03-12', '480000', '10000', '0', '31500000'),
(40, 'nv01', 40, '2020-02-12', '490000', '10000', '0', '37850000'),
(41, 'nv01', 1, '2020-01-12', '500000', '10000', '0', '17850000'),
(42, 'nv01', 2, '2020-12-12', '510000', '10000', '0', '2900000'),
(43, 'nv01', 3, '2020-11-12', '520000', '10000', '0', '525000'),
(44, 'nv01', 4, '2020-10-12', '530000', '10000', '0', '580000'),
(45, 'nv01', 5, '2020-09-12', '540000', '10000', '0', '2085000'),
(46, 'nv01', 6, '2020-08-12', '550000', '10000', '0', '2055000'),
(47, 'nv01', 7, '2020-07-12', '560000', '10000', '0', '2570000'),
(48, 'nv01', 8, '2020-06-12', '570000', '10000', '0', '1890000'),
(49, 'nv01', 9, '2020-05-12', '580000', '10000', '0', '1385000'),
(50, 'nv01', 10, '2020-04-12', '590000', '10000', '0', '2645000'),
(51, 'nv01', 11, '2020-03-12', '600000', '10000', '0', '1550000'),
(52, 'nv01', 12, '2020-02-12', '610000', '10000', '0', '1865000'),
(53, 'nv01', 13, '2020-01-12', '620000', '10000', '0', '4685000'),
(54, 'nv01', 14, '2019-12-12', '630000', '10000', '0', '9785000'),
(55, 'nv01', 15, '2019-11-12', '640000', '10000', '0', '9465000'),
(57, 'a', 5, '2021-09-16', '5000000', '0', '0', '5000000');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `hokh` varchar(50) NOT NULL,
  `tenkh` varchar(10) NOT NULL,
  `gioitinh` varchar(4) NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `dtl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `hokh`, `tenkh`, `gioitinh`, `ngaysinh`, `diachi`, `sdt`, `dtl`) VALUES
(1, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(2, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(3, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(4, 'Nguyễn', 'D', 'Nam', '2021-05-01', '', '0366766666', 40),
(5, 'Nguyễn', 'E', 'Nữ', '2021-05-01', '', '0366346666', 50),
(6, 'Nguyễn', 'F', 'Nam', '2021-05-01', '', '0362366666', 60),
(7, 'Nguyễn', 'G', 'Nữ', '2021-05-01', '', '0366566666', 70),
(8, 'Nguyễn Văn', 'H', 'Nam', '2021-05-01', '', '0366766666', 80),
(9, 'Nguyễnvnaw', 'I', 'Nữ', '2021-05-01', '', '0366786666', 90),
(10, 'Nguyễn', 'K', 'Nam', '2021-05-01', '', '0366766666', 10),
(11, 'Nguyễn', 'L', 'Nữ', '2021-05-01', '', '0366126666', 10),
(12, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(13, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(14, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(15, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(16, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(17, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(18, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(19, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(20, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(21, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(22, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(23, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(24, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(25, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(26, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(27, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(28, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(29, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(30, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(31, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(32, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(33, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(34, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(35, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(36, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(37, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(38, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(39, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0),
(40, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333333', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `makm` varchar(20) NOT NULL,
  `tenkm` varchar(100) NOT NULL,
  `ngaybd` date NOT NULL,
  `ngaykt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`makm`, `tenkm`, `ngaybd`, `ngaykt`) VALUES
('namhocmoi', 'Mừng khai giảng năm học 2021-2022', '2021-09-15', '2021-09-30'),
('trungthu', 'Trung thu', '2021-09-01', '2021-09-30');

-- --------------------------------------------------------

--
-- Table structure for table `loaihang`
--

CREATE TABLE `loaihang` (
  `maloai` int(11) NOT NULL,
  `tenloai` varchar(255) NOT NULL,
  `chittiet` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaihang`
--

INSERT INTO `loaihang` (`maloai`, `tenloai`, `chittiet`) VALUES
(1, 'Bút - Mực', 'but'),
(2, 'Băng keo - Dao - Kéo', 'bang'),
(3, 'Giấy tập - Giấy in', ''),
(4, 'Sổ - Tập - Bao thư', ''),
(5, 'Sách', ''),
(6, 'Máy tính CASIO', ''),
(7, 'Pin các loại', '');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(11) NOT NULL,
  `tenncc` varchar(255) NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `diachi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `sdt`, `diachi`) VALUES
(1, 'CÔNG TY TNHH THƯƠNG MẠI VÀ DỊCH VỤ SANG HÀ', '0866810845', 'Số 7, Đường T4B, P.Tây Thạnh, Q.Tân Phú,TP.HCM(Khu Công Nghiệp Tân Bình)'),
(2, 'Văn phòng phẩm Kim Biên Toàn Cầu', '0906860038', '525/46 Tô Hiến Thành , P 14, Q 10, HCM.'),
(3, 'Văn phòng phẩm An Phát', '0909420033', '47/2C Đường TMT13, Khu phố 2, Phường Trung Mỹ Tây, Quận 12, TPHCM'),
(4, 'Văn phòng phẩm Hồng Hà', '02837562158', 'C12/1 Ấp 3, Xã Tân Kiên, Huyện Bình Chánh, HCM'),
(5, 'Văn phòng phẩm Vinacom', '0835368668', '275/39/3 Bạch Đằng, Phường 15, Quận Bình Thạnh, TPHCM'),
(6, 'Văn phòng phẩm Ánh Sáng', '0862922400', '96 Đông Hồ, Phường 8, Quận Tân Bình, TPHCM'),
(7, 'Văn phòng phẩm An Thiên Phước', '0283602224', '33 đường số 17, Phường Hiệp Bình Chánh, Quận Thủ Đức, TPHCM'),
(8, 'Văn phòng phẩm An Lộc Việt', '0862896219', '479/37 Phan Văn Trị, Phường 5, Quận Gò Vấp, TPHCM');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` varchar(10) NOT NULL,
  `honv` varchar(50) NOT NULL,
  `tennv` varchar(10) NOT NULL,
  `gioitinh` varchar(4) NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `ngayvaolam` date NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `machucvu` int(1) NOT NULL,
  `matkhau` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `honv`, `tennv`, `gioitinh`, `ngaysinh`, `diachi`, `ngayvaolam`, `sdt`, `machucvu`, `matkhau`) VALUES
('a', 'Hoang Ha', 'Vy', 'Nam', '2001-01-01', 'Vietnam', '2001-01-01', '0333333333', 1, 'a'),
('nv01', 'Nguyễn Văn', 'Z', 'Nam', '2002-04-02', 'Vietnam', '2021-05-10', '0333333333', 2, '1234'),
('nv02', 'Nguyễn Văn', 'Z', 'Nam', '2002-04-02', 'Vietnam', '2021-05-10', '0333333333', 2, '1234'),
('nv03', 'Nguyễn Văn', 'Z', 'Nam', '2002-04-02', 'Vietnam', '2002-04-02', '0333333333', 2, '1234'),
('nv04', 'Nguyễn Văn', 'Z', 'Nam', '2003-04-02', 'Vietnam', '2003-04-02', '0333333333', 2, '1234'),
('nv05', 'Nguyễn Văn', 'Z', 'Nam', '2004-04-02', 'Vietnam', '2004-04-02', '0333333333', 2, '1234'),
('nv06', 'Nguyễn Văn', 'Z', 'Nam', '2006-04-02', 'Vietnam', '2021-05-10', '0333333333', 9, '1234'),
('nv7', 'Nguyễn Văn', 'Z', 'Nam', '2005-04-02', 'Vietnam', '2005-04-02', '0333333333', 10, '1234'),
('user21', 'Nguyễn Văn', 'A21', 'Nam', '2001-12-31', 'Phường 29, Phú Nhuận, HCM', '2001-12-31', '0333333353', 3, '1234'),
('user24', 'Nguyễn Văn', 'A24', 'Nam', '2001-12-31', 'Phường 32, Phú Nhuận, HCM', '2001-12-31', '0333333356', 3, '1234'),
('user26', 'Nguyễn Văn', 'A26', 'Nam', '2001-12-31', 'Phường 34, Phú Nhuận, HCM', '2001-12-31', '0333333358', 3, '1234'),
('user29', 'Nguyễn Văn', 'A29', 'Nam', '2001-12-31', 'Phường 37, Phú Nhuận, HCM', '2001-12-31', '0333333361', 3, '1234'),
('vy0bede', 'Hoàng Hà', 'Vy', 'Nam', '2002-07-02', 'Vietnam', '2021-01-01', '0333333333', 1, '1234');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `stt` int(11) NOT NULL,
  `machucvu` int(11) NOT NULL,
  `maquyen` int(11) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`stt`, `machucvu`, `maquyen`, `trangthai`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 1, 3, 1),
(4, 1, 4, 1),
(5, 1, 5, 1),
(6, 1, 6, 1),
(7, 1, 7, 1),
(8, 1, 8, 1),
(9, 1, 9, 1),
(10, 2, 1, 0),
(11, 2, 2, 0),
(12, 2, 3, 1),
(13, 2, 4, 1),
(14, 2, 5, 0),
(15, 2, 6, 0),
(16, 2, 7, 1),
(17, 2, 8, 1),
(18, 2, 9, 0),
(66, 9, 1, 0),
(67, 9, 2, 0),
(68, 9, 3, 0),
(69, 9, 4, 0),
(70, 9, 5, 1),
(71, 9, 6, 1),
(72, 9, 7, 1),
(73, 9, 8, 0),
(74, 9, 9, 0),
(75, 10, 1, 0),
(76, 10, 2, 0),
(77, 10, 3, 0),
(78, 10, 4, 0),
(79, 10, 5, 1),
(80, 10, 6, 0),
(81, 10, 7, 0),
(82, 10, 8, 0),
(83, 10, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `mapnh` int(11) NOT NULL,
  `manv` varchar(20) NOT NULL,
  `ngay` date NOT NULL,
  `mancc` int(11) NOT NULL,
  `tongtien` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`mapnh`, `manv`, `ngay`, `mancc`, `tongtien`) VALUES
(1, 'a', '2021-09-16', 6, '50000000');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `maquyen` int(11) NOT NULL,
  `tenquyen` varchar(255) NOT NULL,
  `chitiet` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`maquyen`, `tenquyen`, `chitiet`) VALUES
(1, 'PHÂN QUYỀN', 'phanquyen'),
(2, 'QUẢN LÍ NHÂN VIÊN', 'nhanvien'),
(3, 'QUẢN LÍ KHÁCH HÀNG', 'khachhang'),
(4, 'QUẢN LÍ SẢN PHẨM', 'sanpham'),
(5, 'QUẢN LÍ NHẬP HÀNG', 'nhaphang'),
(6, 'QUẢN LÍ NHÀ CUNG CẤP', 'nhacungcap'),
(7, 'QUẢN LÍ HÓA ĐƠN', 'hoadon'),
(8, 'QUẢN LÍ KHUYẾN MÃI', 'khuyenmai'),
(9, 'XEM THỐNG KÊ', 'thongke');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(255) NOT NULL,
  `maloai` int(11) NOT NULL,
  `anh` varchar(255) NOT NULL,
  `dongia` decimal(10,0) NOT NULL,
  `soluong` int(11) NOT NULL,
  `mancc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `maloai`, `anh`, `dongia`, `soluong`, `mancc`) VALUES
(2, 'Bút gel xóa được Baoke PC3188 ', 1, 'images/sanpham/2.png', '25000', 140, 1),
(3, 'Bút nước Aihao Tím', 1, 'images/sanpham/3.png', '15000', 130, 1),
(4, 'Ruột bút Uni Ball Pen Refills SXR-7', 1, 'images/sanpham/4.png', '10000', 170, 1),
(5, 'Mực dấu chìm Suremark', 1, 'images/sanpham/5.png', '30000', 160, 1),
(6, 'Bình mực SHINY INK 100ml', 1, 'images/sanpham/6.png', '35000', 150, 1),
(7, 'Bút bi Zebra 301 - 0.7mm', 1, 'images/sanpham/7.png', '10000', 240, 2),
(8, 'Mực lông dầu HS đen', 1, 'images/sanpham/8.png', '15000', 30, 2),
(9, 'Băng keo cách điện NANO', 2, 'images/sanpham/9.png', '30000', 100, 2),
(10, 'Keo trám khe silicone - SILIRUB AC - 280ml', 2, 'images/sanpham/10.png', '25000', 130, 2),
(11, 'Đạn nhựa súng bắn mác 75mm', 2, 'images/sanpham/11.png', '100000', 135, 2),
(12, 'Kéo Cắt Thùng', 2, 'images/sanpham/12.png', '25000', 144, 2),
(13, 'Kéo Thiên Long SC020 - 20cm', 2, 'images/sanpham/13.png', '20000', 17, 3),
(14, 'Băng keo bấm giá ', 2, 'images/sanpham/14.png', '80000', 100, 3),
(15, 'Màng cuốn PE (60cm, 3.1kg)', 2, 'images/sanpham/15.png', '50000', 23, 3),
(16, 'Dao Rọc Giấy Lớn Jsd Jinshidun', 2, 'images/sanpham/16.png', '30000', 235, 3),
(17, 'Giấy A4 Excel 70 Gsm', 3, 'images/sanpham/17.png', '55000', 45, 3),
(18, 'Giấy kraft (tờ) size A0', 3, 'images/sanpham/18.png', '10000', 44, 3),
(19, 'Giấy in bill cho máy POS 57x38mm', 3, 'images/sanpham/19.png', '45000', 32, 4),
(20, 'Decal A3 đế xanh giấy nhám (xấp 100 tờ)', 3, 'images/sanpham/20.png', '30000', 21, 4),
(21, 'Giấy Note Deli 3x3 A023', 3, 'images/sanpham/21.png', '15000', 43, 4),
(22, 'Giấy Thủ Công Có Keo Lớn 17 x 20 cm', 3, 'images/sanpham/22.png', '15000', 78, 4),
(23, 'Giấy A1 Flip Chart', 3, 'images/sanpham/23.png', '25000', 54, 4),
(24, 'Giấy in bill VISA các ngân hàng', 3, 'images/sanpham/24.png', '45000', 32, 4),
(25, 'Tập 96 trang Hiệp Phong', 4, 'images/sanpham/25.png', '20000', 12, 5),
(26, 'Bao thư Xi măng quấn dây A3 đáy 10cm', 4, 'images/sanpham/26.png', '30000', 45, 5),
(27, 'Sổ lò xo A5 bìa nhựa grand 140 trang', 4, 'images/sanpham/27.png', '20000', 32, 5),
(28, 'Bao thư vàng A4 Toppoint có miếng lột', 4, 'images/sanpham/28.png', '10000', 65, 5),
(29, 'Tập 200 trang ABC Hòa Bình (4 ô ly)', 4, 'images/sanpham/29.png', '20000', 34, 5),
(30, 'Tấm lót viết chữ A5', 4, 'images/sanpham/30.png', '15000', 34, 5),
(31, 'Sổ cùi xé 2 liên 7x10cm - Lốc 10 cuốn', 4, 'images/sanpham31.png', '30000', 56, 6),
(32, 'Sổ liên xô mỏng 100 trang - 9x15.6cm', 4, 'images/sanpham/32.png', '10000', 8, 6),
(33, 'Tập caro Subject 300 trang - 20x29.5cm', 4, 'images/sanpham/33.png', '20000', 10, 6),
(34, 'Sổ tay ghi chú lò xo hình kute 8x10.5cm', 4, 'images/sanpham/34.png', '25000', 43, 6),
(35, 'Từ Điển Thuốc Biệt Dược Và Cách Sử Dụng', 5, 'images/sanpham/35.png', '80000', 56, 6),
(36, 'Tuyệt Sắc Đan Dược Sư: Quỷ Vương Yêu Phi', 5, 'images/sanpham/36.png', '85000', 65, 6),
(37, 'Nàng Dâu Cực Phẩm', 5, 'images/sanpham/37.png', '90000', 12, 7),
(38, 'Chào Buổi Sáng: Ông Xã Cool Ngầu', 5, 'images/sanpham/38.png', '70000', 10, 7),
(39, 'Xà Vương Quấn Thân Bà Xã, Sinh Quả Trứng!', 5, 'images/sanpham/39.png', '75000', 2, 7),
(40, 'Xin Chào, Người Thừa Kế', 5, 'images/sanpham/40.png', '65000', 5, 7),
(41, 'Sinh Con Thời Mạt Thế', 5, 'images/sanpham/41.png', '80000', 34, 7),
(42, 'HUYỀN TRÂN CÔNG CHÚA', 5, 'images/sanpham/42.png', '70000', 10, 7),
(43, 'Cuộc chiến tranh bắt buộc', 5, 'images/sanpham/43.png', '95000', 11, 7),
(44, 'Một nửa đàn ông là đàn bà', 5, 'images/sanpham/44.png', '60000', 23, 7),
(45, 'Máy tính Casio MX 12B', 6, 'images/sanpham/45.png', '350000', 10001, 8),
(46, 'Máy tính Casio MX 120B', 6, 'images/sanpham/46.png', '400000', 10, 8),
(47, 'Máy tính Casio FX-570ES Plus', 6, 'images/sanpham/47.png', '500000', 10, 8),
(48, 'Máy tính Casio DC-12M', 6, 'images/sanpham/48.png', '300000', 15, 8),
(49, 'Máy tính Casio MJ-12VCB ', 6, 'images/sanpham/49.png', '350000', 12, 8),
(50, 'Máy tính Casio WM', 6, 'images/sanpham/50.png', '450000', 12, 8),
(51, 'Máy tính Casio DX 120B', 6, 'images/sanpham/51.png', '350000', 13, 8),
(52, 'Máy tính Casio GX 12B', 6, 'images/sanpham/52.png', '250000', 15, 8),
(53, 'Pin sony z5', 7, 'images/sanpham/53.png', '500000', 14, 1),
(54, 'Pin sky a900 7900m', 7, 'images/sanpham/54.png', '550000', 10, 1),
(55, 'Pin sky a880 7700m', 7, 'images/sanpham/55.png', '400000', 1, 1),
(56, 'Pin sky a860 7500m', 7, 'images/sanpham/56.png', '550000', 10, 1),
(57, 'Pin sky a840 7300m', 7, 'images/sanpham/57.png', '450000', 23, 1),
(58, 'Pin Sky A840', 7, 'images/sanpham/58.png', '550000', 100, 1),
(60, 'Pin sky a760-a770 6800m', 7, 'images/sanpham/60.png', '350000', 121, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`stt`);

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`machucvu`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`makm`);

--
-- Indexes for table `loaihang`
--
ALTER TABLE `loaihang`
  ADD PRIMARY KEY (`maloai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`stt`);

--
-- Indexes for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`mapnh`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`maquyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=469;

--
-- AUTO_INCREMENT for table `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `machucvu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `loaihang`
--
ALTER TABLE `loaihang`
  MODIFY `maloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  MODIFY `mapnh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `maquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
