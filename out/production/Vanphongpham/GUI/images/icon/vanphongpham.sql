-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 07, 2021 at 06:23 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

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
  `dongia` decimal(14,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`stt`, `mahd`, `masp`, `soluong`, `khuyenmai`, `dongia`) VALUES
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
(466, 0, 7, 6, '0', '10000'),
(467, 0, 2, 6, '2500', '25000'),
(468, 0, 4, 6, '0', '10000'),
(469, 0, 4, 1, '0', '10000'),
(470, 0, 3, 1, '3000', '15000'),
(471, 0, 2, 1, '2500', '25000'),
(472, 0, 4, 1, '0', '10000'),
(473, 0, 3, 1, '3000', '15000'),
(474, 0, 2, 1, '2500', '25000'),
(475, 0, 4, 1, '0', '10000'),
(476, 0, 3, 1, '3000', '15000'),
(477, 0, 2, 2, '2500', '25000'),
(478, 0, 4, 1, '0', '10000'),
(479, 0, 3, 1, '3000', '15000'),
(480, 0, 2, 1, '2500', '25000'),
(481, 0, 4, 1, '0', '10000'),
(482, 0, 3, 2, '3000', '15000'),
(483, 0, 2, 1, '2500', '25000'),
(484, 0, 4, 1, '0', '10000'),
(485, 0, 3, 2, '3000', '15000'),
(486, 0, 2, 3, '2500', '25000'),
(487, 0, 4, 1, '0', '10000'),
(488, 0, 3, 2, '3000', '15000'),
(489, 0, 2, 3, '2500', '25000'),
(490, 59, 3, 2, '3000', '15000'),
(491, 59, 5, 60, '0', '30000'),
(492, 59, 4, 70, '0', '10000'),
(493, 60, 4, 5, '0', '10000'),
(494, 60, 2, 3, '2500', '25000'),
(495, 61, 4, 3, '0', '10000'),
(496, 61, 2, 3, '2500', '25000'),
(497, 62, 4, 3, '0', '10000'),
(498, 62, 2, 3, '2500', '25000'),
(499, 63, 4, 3, '0', '10000'),
(500, 63, 2, 3, '2500', '25000'),
(501, 64, 2, 15, '2500', '25000'),
(502, 64, 3, 10, '3000', '15000'),
(503, 65, 7, 1, '0', '10000'),
(504, 65, 8, 1, '0', '15000'),
(505, 65, 10, 1, '0', '25000'),
(506, 65, 9, 5, '0', '30000'),
(512, 68, 4, 2, '0', '10000'),
(513, 68, 3, 2, '3000', '15000'),
(514, 69, 26, 1, '0', '30000'),
(515, 69, 31, 1, '0', '30000'),
(516, 69, 38, 2, '0', '70000'),
(517, 69, 41, 3, '0', '80000'),
(518, 69, 51, 2, '0', '350000'),
(519, 69, 57, 2, '0', '450000'),
(520, 69, 58, 3, '0', '550000'),
(521, 69, 5, 1, '0', '30000'),
(522, 69, 2, 3, '2500', '25000'),
(523, 70, 9, 13, '0', '30000'),
(524, 70, 2, 1, '2500', '25000'),
(525, 71, 6, 4, '0', '35000'),
(526, 71, 3, 100, '0', '0'),
(527, 71, 2, 100, '0', '0'),
(528, 71, 60, 1000, '25000', '0'),
(529, 71, 6, 1, '0', '0'),
(530, 71, 60, 100, '40000', '0'),
(531, 71, 4, 10, '0', '0'),
(532, 71, 60, 100, '30000', '0'),
(533, 71, 6, 100, '0', '0'),
(534, 0, 9, 100, '0', '0'),
(535, 0, 60, 100, '20000', '0'),
(536, 0, 10, 20, '0', '0'),
(537, 0, 60, 1000, '10000', '0'),
(538, 7, 60, 1000, '10000', '0'),
(539, 7, 9, 1, '0', '0'),
(540, 8, 8, 100, '0', '0'),
(541, 8, 60, 100, '10000', '0'),
(542, 9, 11, 1000, '0', '0'),
(543, 9, 60, 1000, '10000', '0'),
(544, 72, 60, 5000, '0', '350000'),
(545, 72, 3, 1000, '3000', '15000'),
(546, 73, 11, 35, '0', '100000'),
(547, 73, 10, 49, '0', '25000'),
(548, 73, 28, 1, '0', '10000');

-- --------------------------------------------------------

--
-- Table structure for table `chitietkm`
--

CREATE TABLE `chitietkm` (
  `makm` varchar(50) NOT NULL,
  `masp` int(11) NOT NULL,
  `phantramkm` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietkm`
--

INSERT INTO `chitietkm` (`makm`, `masp`, `phantramkm`) VALUES
('quockhanh', 2, 10),
('quockhanh', 3, 20);

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
(1, 2, 10, '20000', '200000'),
(2, 3, 10, '30000', '300000'),
(1, 2, 10, '20000', '200000'),
(2, 3, 10, '30000', '300000'),
(10, 7, 1000, '20000', '20000000'),
(10, 60, 1000, '20000', '20000000'),
(11, 10, 10000, '20000', '200000000'),
(11, 10, 10000, '20000', '200000000'),
(11, 69, 1000, '20000', '20000000'),
(12, 3, 100000, '20000', '2000000000'),
(12, 69, 1000, '20000', '20000000'),
(13, 2, 1000, '5000', '5000000'),
(13, 69, 9999, '5000', '49995000'),
(14, 69, 99999, '5000', '499995000'),
(15, 69, 99, '5000', '495000'),
(16, 8, 11, '20000', '220000'),
(17, 8, 11, '20000', '220000'),
(18, 8, 11, '20000', '220000'),
(19, 4, 25, '10000', '250000'),
(20, 4, 100, '20000', '2000000'),
(21, 8, 100, '20000', '2000000'),
(24, 9, 1000, '8000', '8000000'),
(25, 9, 1000, '7000', '7000000'),
(26, 11, 1000, '15000', '15000000'),
(26, 70, 1000, '15000', '15000000'),
(27, 71, 100, '15000', '1500000'),
(27, 5, 45, '2000', '90000'),
(28, 72, 1000, '1000', '1000000'),
(29, 2, 1000, '40000', '40000000'),
(31, 24, 100, '10000', '1000000'),
(31, 23, 100, '10000', '1000000'),
(31, 22, 100, '10000', '1000000'),
(31, 21, 100, '10000', '1000000'),
(31, 20, 100, '10000', '1000000'),
(31, 19, 200, '10000', '2000000'),
(31, 18, 100, '10000', '1000000'),
(31, 17, 100, '10000', '1000000'),
(32, 34, 100, '10000', '1000000'),
(32, 33, 100, '10000', '1000000'),
(32, 32, 100, '10000', '1000000'),
(32, 31, 100, '10000', '1000000'),
(32, 30, 100, '10000', '1000000'),
(32, 29, 100, '10000', '1000000'),
(32, 28, 100, '10000', '1000000'),
(32, 27, 100, '10000', '1000000'),
(33, 9, 100, '20000', '2000000'),
(33, 8, 100, '20000', '2000000'),
(34, 52, 100, '300000', '30000000'),
(34, 51, 200, '300000', '60000000'),
(34, 50, 100, '300000', '30000000'),
(34, 49, 100, '300000', '30000000'),
(34, 48, 100, '300000', '30000000'),
(34, 47, 100, '300000', '30000000'),
(34, 46, 100, '300000', '30000000'),
(34, 45, 100, '300000', '30000000'),
(35, 57, 100, '300000', '30000000'),
(35, 56, 100, '300000', '30000000'),
(35, 55, 100, '300000', '30000000'),
(35, 54, 100, '300000', '30000000'),
(35, 53, 100, '300000', '30000000'),
(36, 44, 1000, '100000', '100000000'),
(36, 43, 1000, '100000', '100000000'),
(36, 42, 1000, '100000', '100000000'),
(36, 41, 1000, '100000', '100000000'),
(36, 40, 1000, '100000', '100000000'),
(36, 39, 1000, '100000', '100000000'),
(36, 38, 1000, '100000', '100000000'),
(36, 37, 1000, '100000', '100000000'),
(36, 36, 1000, '100000', '100000000'),
(36, 35, 1000, '100000', '100000000'),
(37, 58, 3, '300000', '900000'),
(38, 73, 10000, '3000', '30000000'),
(38, 72, 25, '3000', '75000'),
(38, 4, 25, '3000', '75000');

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
(59, 'a', 1, '2021-09-04', '2530000', '6000', '0', '2524000'),
(60, 'a', 1, '2021-09-04', '125000', '7500', '0', '117500'),
(61, 'a', 1, '2021-09-04', '105000', '7500', '0', '97500'),
(62, 'a', 1, '2021-09-04', '105000', '7500', '0', '97500'),
(63, 'a', 1, '2021-09-04', '140000', '10000', '0', '130000'),
(64, 'a', 77, '2021-09-04', '525000', '67500', '0', '457500'),
(65, 'a', 69, '2021-09-04', '200000', '0', '0', '200000'),
(68, 'a', 1, '2021-09-06', '50000', '6000', '0', '44000'),
(69, 'a', 69, '2021-09-06', '3795000', '7500', '0', '3787500'),
(70, 'a', 4, '2021-09-06', '415000', '2500', '0', '412500'),
(71, 'a', 5, '2021-09-06', '140000', '0', '0', '140000'),
(72, 'a', 2, '2021-09-07', '1765000000', '3000000', '0', '1762000000'),
(73, 'a', 1, '2021-09-07', '4735000', '0', '0', '4735000');

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
(2, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333335', 0),
(3, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333334', 0),
(4, 'Nguyễn', 'D', 'Nam', '2021-05-01', 'Vietnam', '0366766663', 40),
(5, 'Nguyễn', 'E', 'Nữ', '2021-05-01', 'Vietnam', '0366346662', 50),
(6, 'Nguyễn', 'F', 'Nam', '2021-05-01', 'Vietnam', '0362366661', 60),
(7, 'Nguyễn', 'G', 'Nữ', '2021-05-01', 'Vietnam', '0366566660', 70),
(8, 'Nguyễn Văn', 'H', 'Nam', '2021-05-01', 'Vietnam', '0366766669', 80),
(9, 'Hoàng', 'I', 'Nữ', '2021-05-01', 'Vietnam', '0366786668', 90),
(10, 'Nguyễn', 'K', 'Nam', '2021-05-01', 'Vietnam', '0366766667', 10),
(11, 'Nguyễn', 'L', 'Nữ', '2021-05-01', 'Vietnam', '0366126666', 10),
(40, 'Hoang', 'Vy', 'Nam', '2001-05-01', 'Vietnam', '0333333331', 0),
(69, 'Hoàng Hà', 'Vy', '', '0002-11-30', 'Tứ Hạ', '0335326048', 0),
(70, 'Nguyễn Văn', 'D', '', '0002-11-30', 'Tứ Hạ', '0934567654', 0),
(77, 'Hoàng', 'Vyyy', '', '0002-11-30', 'Vietnam', '0335326049', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `makm` varchar(50) NOT NULL,
  `tenkm` varchar(200) NOT NULL,
  `ngaybd` date NOT NULL,
  `ngaykt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`makm`, `tenkm`, `ngaybd`, `ngaykt`) VALUES
('giaiphongdatnuoc', '30 thang 4, 1 thang 5', '2021-04-28', '2021-05-04'),
('quockhanh', 'Mừng lễ Quốc khánh', '2021-08-31', '2021-09-30');

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
  `manv` varchar(50) NOT NULL,
  `ngay` date NOT NULL,
  `mancc` int(11) NOT NULL,
  `tongtien` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`mapnh`, `manv`, `ngay`, `mancc`, `tongtien`) VALUES
(1, 'nv01', '2021-09-06', 1, '10000'),
(2, 'nv01', '2021-09-05', 2, '200000'),
(3, 'a', '2021-09-07', 4, '3000000'),
(4, 'a', '2021-09-07', 4, '3000000'),
(5, 'a', '2021-09-07', 3, '1500000'),
(6, 'a', '2021-09-07', 7, '5500000'),
(7, 'a', '2021-09-07', 7, '2002000'),
(8, 'a', '2021-09-07', 2, '1000000'),
(9, 'a', '2021-09-07', 6, '10000000'),
(10, 'a', '2021-09-07', 1, '40000000'),
(11, 'a', '2021-09-07', 3, '220000000'),
(12, 'a', '2021-09-07', 6, '2020000000'),
(13, 'a', '2021-09-07', 3, '54995000'),
(14, 'a', '2021-09-07', 5, '499995000'),
(15, 'a', '2021-09-07', 5, '495000'),
(16, 'a', '2021-09-07', 4, '2220000'),
(17, 'a', '2021-09-07', 4, '2220000'),
(18, 'a', '2021-09-07', 4, '2220000'),
(19, 'a', '2021-09-07', 6, '1250000'),
(20, 'a', '2021-09-07', 4, '22000000'),
(21, 'a', '2021-09-07', 5, '22000000'),
(22, 'a', '2021-09-07', 6, '3960000'),
(23, 'a', '2021-09-07', 4, '1000000'),
(24, 'a', '2021-09-07', 1, '16000000'),
(25, 'a', '2021-09-07', 3, '14000000'),
(26, 'a', '2021-09-07', 1, '30000000'),
(27, 'a', '2021-09-07', 3, '1590000'),
(28, 'a', '2021-09-07', 2, '1000000'),
(29, 'a', '2021-09-07', 8, '40000000'),
(30, 'a', '2021-09-07', 2, '0'),
(31, 'a', '2021-09-07', 7, '9000000'),
(32, 'a', '2021-09-07', 8, '8000000'),
(33, 'a', '2021-09-07', 5, '4000000'),
(34, 'a', '2021-09-07', 5, '270000000'),
(35, 'a', '2021-09-07', 6, '150000000'),
(36, 'a', '2021-09-07', 1, '1000000000'),
(37, 'a', '2021-09-07', 2, '900000'),
(38, 'a', '2021-09-07', 1, '30150000');

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
  `soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `maloai`, `anh`, `dongia`, `soluong`) VALUES
(2, 'Bút gel xóa được Baoke PC3188', 1, 'images/sanpham/2.png', '25000', 2169),
(3, 'Bút nước Aihao Tím', 1, 'images/sanpham/3.png', '15000', 99178),
(4, 'Ruột bút Uni Ball Pen Refills SXR-7', 1, 'images/sanpham/4.png', '10000', 100),
(5, 'Mực dấu chìm Suremark', 1, 'images/sanpham/5.png', '30000', 100),
(6, 'Bình mực SHINY INK 100ml', 1, 'images/sanpham/6.png', '35000', 101),
(7, 'Bút bi Zebra 301 - 0.7mm', 1, 'images/sanpham/7.png', '10000', 1039),
(8, 'Mực lông dầu HS đen', 1, 'images/sanpham/8.png', '15000', 229),
(9, 'Băng keo cách điện NANO', 2, 'images/sanpham/9.png', '30000', 282),
(10, 'Keo trám khe silicone - SILIRUB AC - 280ml', 2, 'images/sanpham/10.png', '25000', 10000),
(11, 'Đạn nhựa súng bắn mác 75mm', 2, 'images/sanpham/11.png', '100000', 2000),
(12, 'Kéo Cắt Thùng', 2, 'images/sanpham/12.png', '25000', 44),
(13, 'Kéo Thiên Long SC020 - 20cm', 2, 'images/sanpham/13.png', '20000', 17),
(14, 'Băng keo bấm giá ', 2, 'images/sanpham/14.png', '80000', 100),
(15, 'Màng cuốn PE (60cm, 3.1kg)', 2, 'images/sanpham/15.png', '50000', 23),
(16, 'Dao Rọc Giấy Lớn Jsd Jinshidun', 2, 'images/sanpham/16.png', '30000', 235),
(17, 'Giấy A4 Excel 70 Gsm', 3, 'images/sanpham/17.png', '55000', 145),
(18, 'Giấy kraft (tờ) size A0', 3, 'images/sanpham/18.png', '10000', 144),
(19, 'Giấy in bill cho máy POS 57x38mm', 3, 'images/sanpham/19.png', '45000', 232),
(20, 'Decal A3 đế xanh giấy nhám (xấp 100 tờ)', 3, 'images/sanpham/20.png', '30000', 121),
(21, 'Giấy Note Deli 3x3 A023', 3, 'images/sanpham/21.png', '15000', 143),
(22, 'Giấy Thủ Công Có Keo Lớn 17 x 20 cm', 3, 'images/sanpham/22.png', '15000', 178),
(23, 'Giấy A1 Flip Chart', 3, 'images/sanpham/23.png', '25000', 154),
(24, 'Giấy in bill VISA các ngân hàng', 3, 'images/sanpham/24.png', '45000', 132),
(25, 'Tập 96 trang Hiệp Phong', 4, 'images/sanpham/25.png', '20000', 12),
(26, 'Bao thư Xi măng quấn dây A3 đáy 10cm', 4, 'images/sanpham/26.png', '30000', 44),
(27, 'Sổ lò xo A5 bìa nhựa grand 140 trang', 4, 'images/sanpham/27.png', '20000', 132),
(28, 'Bao thư vàng A4 Toppoint có miếng lột', 4, 'images/sanpham/28.png', '10000', 164),
(29, 'Tập 200 trang ABC Hòa Bình (4 ô ly)', 4, 'images/sanpham/29.png', '20000', 134),
(30, 'Tấm lót viết chữ A5', 4, 'images/sanpham/30.png', '15000', 134),
(31, 'Sổ cùi xé 2 liên 7x10cm - Lốc 10 cuốn', 4, 'images/sanpham31.png', '30000', 155),
(32, 'Sổ liên xô mỏng 100 trang - 9x15.6cm', 4, 'images/sanpham/32.png', '10000', 108),
(33, 'Tập caro Subject 300 trang - 20x29.5cm', 4, 'images/sanpham/33.png', '20000', 110),
(34, 'Sổ tay ghi chú lò xo hình kute 8x10.5cm', 4, 'images/sanpham/34.png', '25000', 143),
(35, 'Từ Điển Thuốc Biệt Dược Và Cách Sử Dụng', 5, 'images/sanpham/35.png', '80000', 1056),
(36, 'Tuyệt Sắc Đan Dược Sư: Quỷ Vương Yêu Phi', 5, 'images/sanpham/36.png', '85000', 1065),
(37, 'Nàng Dâu Cực Phẩm', 5, 'images/sanpham/37.png', '90000', 1012),
(38, 'Chào Buổi Sáng: Ông Xã Cool Ngầu', 5, 'images/sanpham/38.png', '70000', 1008),
(39, 'Xà Vương Quấn Thân Bà Xã, Sinh Quả Trứng!', 5, 'images/sanpham/39.png', '75000', 1002),
(40, 'Xin Chào, Người Thừa Kế', 5, 'images/sanpham/40.png', '65000', 1005),
(41, 'Sinh Con Thời Mạt Thế', 5, 'images/sanpham/41.png', '80000', 1031),
(42, 'HUYỀN TRÂN CÔNG CHÚA', 5, 'images/sanpham/42.png', '70000', 1010),
(43, 'Cuộc chiến tranh bắt buộc', 5, 'images/sanpham/43.png', '95000', 1011),
(44, 'Một nửa đàn ông là đàn bà', 5, 'images/sanpham/44.png', '60000', 1023),
(45, 'Máy tính Casio MX 12B', 6, 'images/sanpham/45.png', '350000', 101),
(46, 'Máy tính Casio MX 120B', 6, 'images/sanpham/46.png', '400000', 110),
(47, 'Máy tính Casio FX-570ES Plus', 6, 'images/sanpham/47.png', '500000', 110),
(48, 'Máy tính Casio DC-12M', 6, 'images/sanpham/48.png', '300000', 115),
(49, 'Máy tính Casio MJ-12VCB ', 6, 'images/sanpham/49.png', '350000', 112),
(50, 'Máy tính Casio WM', 6, 'images/sanpham/50.png', '450000', 112),
(51, 'Máy tính Casio DX 120B', 6, 'images/sanpham/51.png', '350000', 211),
(52, 'Máy tính Casio GX 12B', 6, 'images/sanpham/52.png', '250000', 115),
(53, 'Pin sony z5', 7, 'images/sanpham/53.png', '500000', 114),
(54, 'Pin sky a900 7900m', 7, 'images/sanpham/54.png', '550000', 110),
(55, 'Pin sky a880 7700m', 7, 'images/sanpham/55.png', '400000', 101),
(56, 'Pin sky a860 7500m', 7, 'images/sanpham/56.png', '550000', 110),
(57, 'Pin sky a840 7300m', 7, 'images/sanpham/57.png', '450000', 121),
(58, 'Pin Sky A840', 7, 'images/sanpham/58.png', '550000', 100),
(60, 'Pin sky a760-a770 6800m', 7, '', '350000', 405),
(69, 'Mực lông dầu HS đỏ', 2, 'images/sanpham/69.png', '30000', 2000),
(70, 'Mực lông dầu HS hồng', 2, 'images/sanpham/70.png', '30000', 1000),
(71, 'Bình mực SHINY INK 200ml', 2, 'images/sanpham/71.png', '20000', 100),
(72, 'Ruột bút Uni Ball Pen Refills SXR-9', 2, 'images/sanpham/72.png', '2000', 1025),
(73, 'Ruột bút Uni Ball Pen Refills SXR-8', 3, 'images/sanpham/73.png', '5000', 10000);

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
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=549;

--
-- AUTO_INCREMENT for table `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `machucvu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

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
  MODIFY `mapnh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `maquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
