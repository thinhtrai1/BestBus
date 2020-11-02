-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th10 18, 2020 lúc 07:20 AM
-- Phiên bản máy phục vụ: 10.3.16-MariaDB
-- Phiên bản PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `id14985528_bestbus`
--
CREATE DATABASE IF NOT EXISTS `id14985528_bestbus` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id14985528_bestbus`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL DEFAULT 0,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tourId` int(11) NOT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `seatList` text COLLATE utf8_unicode_ci NOT NULL,
  `paymentMethod` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `paymentInformation` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `totalAmount` float NOT NULL DEFAULT 0,
  `qrCode` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ticket`
--

INSERT INTO `ticket` (`id`, `userId`, `name`, `email`, `phone`, `tourId`, `date`, `seatList`, `paymentMethod`, `paymentInformation`, `totalAmount`, `qrCode`) VALUES
(97, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[57]', 'AT_STATION', '', 42, 'c5f18640-14d5-4729-ade2-2e74b2b17eb57b46a516-e3cf-4f9a-8f12-7003a1c31eadf77c0eb2-3332-4ecc-926e-0823418ac191'),
(98, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[51]', 'AT_STATION', '', 42, 'ead1e768-64f5-4438-a63a-6088f6b5a575827e815f-d0ff-4dd7-beb6-b1386b77867aa3803636-fbc6-4b0f-89be-f569e2640c1c'),
(99, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[38]', 'AT_STATION', '', 42, '940682c8-8778-4d1a-aed1-d4824865af79d53ac738-5d56-425a-8f91-2940054e01f9780e349c-0021-48cb-9f10-d801564ba794'),
(100, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[8]', 'AT_STATION', '', 42, '869dca32-07d4-495d-b59a-d2991fc6194a633f40a3-cff5-448a-9791-408d88ce308c84553a32-ff19-41bd-8c94-ff5f369ffaba'),
(101, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[26,27]', 'AT_STATION', '', 84, '99046d87-47b0-4d43-8216-9031326791793f26268f-539b-43f6-944b-6b95783f4a080e1d4941-f203-4b71-be18-1697163b73da'),
(102, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[4,5]', 'AT_STATION', '', 84, 'dc497fd2-279c-4f0e-b4de-3efc90ba0484c17e43e8-254d-440f-96b2-2aad5075ebb64cad7f86-471b-44c6-ae07-aa56fef9d607'),
(103, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[94,95]', 'AT_STATION', '', 84, '7173e4ea-85df-4bb7-ade9-ca7ee19ae567e5d51483-f31f-4bd2-9518-56c0e3172c6181016ccf-b3f0-45c1-a935-bdf24ebda9d4'),
(104, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[72]', 'AT_STATION', '', 42, 'd1fe87eb-a10e-4a75-82fa-e01dc0cc31eb9e35ae91-2cb0-49b8-912e-1e8f8e644f5054684b40-0841-4da0-bebd-0d5062c0b4e6'),
(105, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[90]', 'AT_STATION', '', 42, '7035a22f-4b83-4ba2-8ccf-a43a72c0f95ab6c1f732-33a2-4a62-bb5f-4e6d143697e4ffd8edf6-356d-44fd-bd71-0397f90c668f'),
(106, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[73]', 'AT_STATION', '', 42, 'a55221eb-c13b-4dbe-a7b9-ee21ad14c0dabf60f1ca-f997-4e4b-bb80-3353b66d5dc1a09f0b8a-bee2-4e6c-a61e-0ab253862a9c'),
(107, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 1, '', '[48,49]', 'AT_STATION', '', 31.5, 'f2073cdb-d027-415f-bc24-199c982b467fa039f752-ab00-403e-ac38-82a364554c929f6fdc90-41ad-41dc-849f-233ad3495986'),
(108, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 1, '', '[64]', 'AT_STATION', '', 15.75, 'c66b8287-4010-4f97-b344-33746a34362060d254fc-87fb-498b-8b3a-feac252fe8b28453f374-eaa2-41b0-b2d6-6acc3ff22632'),
(109, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, '', '[91,96]', 'AT_STATION', '', 84, 'cfa2583ea29b439bfdfb3251143c9796eb86d4ad2bd3859ddcc25f095e45a6ca7cf0cca6fb4617d698006ef98b5faed4e86e'),
(110, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 1, '', '[29]', 'AT_STATION', '', 15.75, 'dcf4f350ddfa424584b2eca45b0960550281c5ad362c606fb8c023f0f513cf8bbbd56fcdc1900a6664a67026417b553b0836'),
(111, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, 'Sat, 10 Oct 2020', '[60,66]', 'AT_STATION', '', 84, 'aed491d0bfe221fe5d9e2e7ffce20e03bb38b5e8e3148817e3c070d2d516ac4c7b98ebaf6d7a58f18e020811ae26aaf68e68'),
(112, 5, 'Admin', 'admin@gmail.com', '0123456789', 2, 'Sat, 10 Oct 2020', '[23]', 'AT_STATION', '', 42, 'bc8488854e38524be3444cc51ef6ea9b100bccd1727f7dd91fbc4a8739b85fdd16960880ef5d78d46f4aadf3399cfb40ac7e'),
(113, 5, 'Admin', 'admin@gmail.com', '0123456789', 2, 'Sat, 10 Oct 2020', '[50]', 'AT_STATION', '', 42, 'f3246c9250390b77b190f3d9e40b6f1dae172aa6a9429662ab365586b7995babe3f77eb6529ef3ea3730c4db98764b8bd1cb'),
(114, 5, 'Admin', 'admin@gmail.com', '0123456789', 2, 'Sat, 10 Oct 2020', '[50]', 'AT_STATION', '', 42, 'b3a3985df7080b60f48e8a00dfae3ec20d9aa0aba7231e78f9d2f029885a4ab5269a612bc42ec0d418b8602134c5c0d9b9e4'),
(115, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Tue, 20 Oct 2020', '[60,61]', 'AT_STATION', '', 31.5, '17bdc593daab95380f7229aa47186d41bf7928307504f0a04a998b45e4c352484bcdea6294f85b36ef6290dd0a35253b2c9d'),
(116, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Mon, 12 Oct 2020', '[52]', 'CODE_SHIPPING', '', 15.75, 'a09a1d8fc44e5a5ffaaa2da8e5c483a63784e7835ecc4b83dca7a6d684a2f17b48a53243bfe8f108270c3f576bd5d82c20c7'),
(117, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 2, 'Mon, 05 Oct 2020', '[114]', 'CODE_SHIPPING', '', 42, 'cad1962dd6dfaaf3bbced527aefc54076ba0a0c71d901fa917b17c6365f5506c652fb400bba0fba5adfcebe796c853ddb2d9'),
(118, 3, '', '', '', 2, 'Sun,  11 Oct 2020', '[20,21,22]', 'AT_STATION', '', 5000, '98cafc2e60411a3acd7e43d50f196a7d78d022d2d6803f32e4d9ae632926b26712235e7dcba2bb9db51f7e7e115d5f010700'),
(122, 0, '', '', '', 2, 'Sun,  11 Oct 2020', '[20,21,22]', 'AT_STATION', '', 5000, 'ef5e9ae1be58f2917e7144d0750e98aacd1a097f35cd26ad5e6b7fdf39820baa9319a3e76b8a2d0f5349bc6fe2a157782a4d'),
(123, 0, '', '', '', 1, 'Sun, 11 Oct 2020', '[54]', 'AT_STATION', '', 15.75, '1e2162a72ed49fdaf217957d92e53acd552b5267b002a3eb71907c4b4e482c940774036402c1f5f64028aab7081724ac0127'),
(124, 1, 'Anonymous', 'ducthinhtrai@gmail.com', '', 1, 'Sun, 11 Oct 2020', '[20]', 'AT_STATION', '', 15.75, 'f0bb2b7f2dd300ff9d07538d9e1ce3af953cfc3d448ebb59dc07e6647ab1396ac7e5fa1b870148fa6572473fe8b84761900d'),
(125, 0, 'Thịnh đây', '', '0558688285', 1, 'Sun, 11 Oct 2020', '[23]', 'AT_STATION', '', 15.75, '710099c145cb2d6fd45ff0d50e3ec3a9ec18e9e21a0dddf5cc79e0bd0f36146fe5951b61da688068aad223763bdbb5a425ba'),
(126, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Tue, 13 Oct 2020', 'Array', 'CODE_SHIPPING', '', 15.75, 'de8769f03059c00ffbcb74e3e522c4e5220e23c1433760a183c923bd770ea4191fff7da670d58e3820a62cc8a3d3b1504d5d'),
(127, 0, '', '', '', 2, 'Sun,  11 Oct 2020', '[20,21,22]', 'AT_STATION', '', 5000, '87ac2a7e62a626de9bca505202cef8e7279370c6acfeabee44b70ddbbc12669bf42f9e29796f2b8ad26f075d6b3f8928b409'),
(128, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Tue, 13 Oct 2020', 'Array', 'CODE_SHIPPING', '', 15.75, 'aeca801e4217a764dc9f56f3e985147746735b55096470043796a08128bb8bfbff8c0244e02bc181b65d2301569ee684095b'),
(129, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Tue, 13 Oct 2020', '[13]', 'AT_STATION', '', 15.75, 'c00768687b3fc68cda2cb535d41e76db64db9e804cbb00f66ec93cf6f4995ed3a8d6260be7e098463dce113008be99451124'),
(130, 5, 'Admin', 'admin@gmail.com', '0123456789', 1, 'Thu, 15 Oct 2020', '[58]', 'AT_STATION', '', 15.75, '620f3d8a314bd9a71884739fc8d3e9760fda9e20032d50f29f36a557b1c2d47d3ca737f2ab805346fb19db1a4ab0f2bda447'),
(131, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 195, 'Fri, 16 Oct 2020', '[4,5,6,7,12,13,14,15,20,21,22,23,28,29,30,31,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,105,107,108,109,110]', 'AT_STATION', '', 12219.7, '3b7e96ba5551e250c8947305b909fce94cf90e8cfd69f695aac7324f9bccb4ac9b3814eab0b1e5eab593b04ecb58c3618bc1'),
(132, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 132, 'Fri, 16 Oct 2020', '[54,55,62,63]', 'CODE_SHIPPING', '', 1204.82, 'e198d22ec70a0e84dfa484629870530d1f58db9d2a5e243594c653fdfba7aa2711c0cab726a8ec3a74ce446c0bdb67b5b9cb'),
(133, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 177, 'Fri, 16 Oct 2020', '[5,6,7,13,14,15,18,19,20,21,23,24,25,26,27,32,33,34,35,40,41,44,45,46,52,53,54,55,60,61,62,63,68,69,71,74,75,78,79,86,87]', 'AT_STATION', '', 7518.58, '4edf8d7841d9e5c28dfde7b2ae21ae8b40af5667537818ddbb6ac85263ab1f55827cd1b3e47c55191b9c88a464b9002ce153'),
(134, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 127, 'Fri, 16 Oct 2020', '[44,45,52,53,61,62,69,70,80,81,88,89]', 'AT_STATION', '', 2773.44, '9e17efce102877b679ea91c23783dd9aba6a855ddddddef5a32947dd23f4af14add88d7117e4603246c6633fde69aa6476ba'),
(135, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 127, 'Fri, 16 Oct 2020', '[109,110,111,116,117,118,119,124,125,126,127,132,133,134,135]', 'AT_STATION', '', 3466.8, 'aaecb67a289019fb2658eb70848d485fdfa0aacc2fea0cc85d280a17b563c4b521e6692b28de93836a617df5af9cb46fe9e7'),
(136, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 54, 'Fri, 16 Oct 2020', '[18,22,23,24,25,26,28,29,30,31,34,35,40,41,60]', 'AT_STATION', '', 702, '795bca12e3fba8d407e27f2c5f4b90eaf2a03a00e1752f5ba18fb930a2be713b191cee9d853e36224704b7b5ec2c66fdb3f0'),
(137, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 113, 'Fri, 16 Oct 2020', '[24,25,26,27,32,33,48,49,50,51,52,53,54,55,56,57,58,59]', 'CODE_SHIPPING', '', 1965.6, '957cbd623856dbf84dd6609a2f5f641dea6db4f578bc9fb024d5e512db9c51a912fa3bffeeea6db241264bbc6ecb8d7882eb'),
(138, 1, 'Anopheles T', 'ducthinhtrai@gmail.com', '0123564978', 78, 'Fri, 16 Oct 2020', '[208,209]', 'CODE_SHIPPING', '', 713.95, 'dbabb9fa341dd46ff6cc52ddff640b9758ca5b52158ffb1f09f5e692f864d6fc2742c28ba30f69a7dd1d5e4f76e2e02f95bb');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `id` int(11) NOT NULL,
  `tourName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oldPrice` float NOT NULL DEFAULT 0,
  `price` float NOT NULL DEFAULT 0,
  `vat` float NOT NULL DEFAULT 0.05,
  `startTime` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Undefined',
  `time` float NOT NULL DEFAULT 0,
  `fromCity` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `toCity` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seatQuantity` int(3) NOT NULL DEFAULT 0,
  `count` int(11) NOT NULL DEFAULT 3,
  `seatSelected` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`id`, `tourName`, `oldPrice`, `price`, `vat`, `startTime`, `time`, `fromCity`, `toCity`, `seatQuantity`, `count`, `seatSelected`) VALUES
(1, 'AA-00', 25, 15, 0.05, '07:30', 3.5, 'Hà Nội', 'Đà Nẵng', 65, 3, '[25,26,27,21,15,16,30,31,36,0,1,6,7,8,34,35,41,40,39,33,5,2,3,4,9,32,37,38,48,49,64,29,60,61,52,54,54,20,23,13,58]'),
(2, 'AA-01', 50, 40, 0.05, '22:00', 6.25, 'Singapore', 'New York', 120, 3, '[0,6,7,0,6,7,20,21,22,20,21,22,0,6,7,34,35,40,41,20,21,22,45,42,43,48,49,58,59,65,24,25,57,51,38,8,26,27,4,5,94,95,72,90,73,91,96,60,66,23,50,50,114,20,21,22,20,21,22,115,20,21,22,20,21,22]'),
(3, 'AA-02', 29, 19, 0.04, '14:30', 4, 'Huế', 'Thành phố Hồ Chí Minh', 60, 3, NULL),
(4, 'AA-04', 55, 55, 0.05, '08:50', 4, 'Huế', 'Bangkok', 85, 3, NULL),
(6, 'AA-05', 85, 60, 0.3, '14:30', 6, 'Cần Thơ', 'Shanghai', 65, 3, NULL),
(10, 'AA-09', 79, 79, 0.6, '20:20', 10, 'Đà Nẵng', 'California', 140, 4, NULL),
(11, 'AA-15', 214.9, 200, 0.05, '15:45', 24, 'Las Vegas', 'Bangkok', 155, 4, NULL),
(12, 'AA-13', 131.5, 123.5, 0.03, '20:00', 6, 'Taipei', 'Jakarta', 95, 3, NULL),
(13, 'AA-11', 79.5, 70.5, 0.03, '09:50', 5.5, 'Dubai', 'Singapore', 60, 3, NULL),
(14, 'AA-14', 191.65, 161.65, 0.03, '04:20', 2.25, 'Chicago', 'Las Vegas', 65, 3, NULL),
(15, 'AA-12', 241, 208.5, 0.03, '23:00', 10.4, 'Liverpool', 'Dubai', 85, 3, NULL),
(16, 'AA-19', 83, 58, 0.03, '04:45', 5, 'Shanghai', 'Tokyo', 60, 3, NULL),
(17, 'AA-16', 209, 187, 0.03, '21:15', 3, 'Taipei', 'Hà Nội', 60, 3, NULL),
(18, 'AA-18', 193.5, 176.5, 0.03, '23:30', 3.5, 'Hà Nội', 'Taipei', 65, 3, NULL),
(19, 'AA-17', 79.5, 61.5, 0.03, '17:00', 3.25, 'Seoul', 'Hà Nội', 40, 2, NULL),
(20, 'AA-20', 334.5, 332.5, 0.07, '13:15', 28, 'Beijing', 'New York', 210, 4, NULL),
(27, 'AA-21', 242.5, 208.5, 0.03, '13:30', 4.5, 'London', 'Berlin', 80, 3, NULL),
(28, 'AA-22', 303, 285, 0.07, '22:00', 26.5, 'Washington', 'Đà Nẵng', 175, 4, NULL),
(29, 'AA-23', 55, 25, 0.04, '16:15', 7.75, 'Moskva', 'London', 70, 3, NULL),
(30, 'AA-24', 170, 145, 0.06, '16:15', 21, 'Seoul', 'Paris', 150, 4, NULL),
(31, 'AA-25', 27, 12, 0.03, '08:15', 2.75, 'Shanghai', 'Shanghai', 35, 2, NULL),
(32, 'AA-33', 368, 331, 0.09, '05:00', 33.25, 'Las Vegas', 'Tokyo', 195, 4, NULL),
(33, 'AA-32', 258, 252.5, 0.07, '11:15', 23, 'Los Angeles', 'Bangkok', 160, 4, NULL),
(34, 'AA-34', 151.5, 148, 0.04, '02:00', 3.75, 'Abu Dhabi', 'Paris', 80, 3, NULL),
(35, 'AA-35', 35.5, 33.5, 0.04, '06:15', 5.75, 'Huế', 'Jakarta', 75, 3, NULL),
(36, 'AA-37', 201, 201, 0.04, '00:15', 9.25, 'Moskva', 'Singapore', 80, 3, NULL),
(37, 'AA-39', 205, 205, 0.04, '03:15', 5.25, 'Beijing', 'Seoul', 60, 3, NULL),
(38, 'AA-40', 11.5, 12, 0.03, '13:00', 2.25, 'TP.HCM', 'Cần Thơ', 60, 3, NULL),
(39, 'AA-36', 118, 106, 0.05, '18:00', 15, 'Rome', 'Singapore', 95, 3, NULL),
(40, 'AA-38', 84.5, 85, 0.03, '03:45', 4, 'Cairo', 'Cairo', 50, 3, NULL),
(41, 'AA-41', 24.5, 24.5, 0.03, '14:45', 1.5, 'Đà Nẵng', 'Đà Nẵng', 55, 3, NULL),
(42, 'AA-42', 171.5, 159, 0.05, '18:00', 14.5, 'Bangkok', 'Beijing', 115, 3, NULL),
(43, 'AA-44', 285.5, 212, 0.04, '19:15', 6.75, 'New York', 'Las Vegas', 75, 3, NULL),
(44, 'AA-43', 203, 179, 0.05, '04:45', 15.25, 'Paris', 'Bangkok', 110, 3, NULL),
(45, 'AA-45', 252.5, 156.5, 0.05, '22:45', 14, 'New Delhi', 'Taipei', 100, 3, NULL),
(46, 'AA-47', 309.5, 298, 0.07, '09:00', 22.75, 'Shanghai', 'Paris', 165, 4, NULL),
(48, 'AA-46', 177.5, 149.5, 0.04, '01:45', 9.25, 'Đà Nẵng', 'Cần Thơ', 90, 3, NULL),
(49, 'AA-49', 286.5, 237, 0.05, '07:00', 11.25, 'Abu Dhabi', 'Hội An', 110, 3, NULL),
(50, 'AA-48', 188.5, 149.5, 0.08, '10:15', 24.75, 'Beijing', 'Rome', 155, 4, NULL),
(51, 'AA-50', 227.5, 227.5, 0.05, '12:00', 10.5, 'New York', 'Moskva', 110, 3, NULL),
(52, 'AA-52', 185.5, 185.5, 0.04, '19:30', 4.25, 'Rome', 'Manchester', 80, 3, NULL),
(53, 'AA-56', 308.5, 285.5, 0.07, '22:15', 24.5, 'Seoul', 'Rome', 140, 4, NULL),
(54, 'AA-54', 49, 45, 0.04, '01:30', 9.5, 'Hội An', 'Tokyo', 80, 3, '[18,22,23,24,25,26,28,29,30,31,34,35,40,41,60]'),
(55, 'AA-55', 147.5, 147.5, 0.04, '14:30', 7, 'Hà Nội', 'Jakarta', 75, 3, NULL),
(56, 'AA-53', 222.5, 182, 0.08, '11:45', 28.75, 'Liverpool', 'Tokyo', 155, 4, NULL),
(57, 'AA-61', 236.5, 210.5, 0.05, '04:30', 11, 'Huế', 'Dubai', 105, 3, NULL),
(58, 'AA-59', 171.5, 126, 0.05, '16:00', 15.75, 'Moskva', 'Washington', 110, 3, NULL),
(59, 'AA-58', 325, 289, 0.07, '06:15', 25.75, 'Shanghai', 'Praha', 145, 4, NULL),
(60, 'AA-57', 84, 76, 0.04, '10:15', 7, 'Cần Thơ', 'Paris', 90, 3, NULL),
(61, 'AA-60', 127.5, 127.5, 0.04, '11:30', 7.25, 'Canberra', 'Huế', 80, 3, NULL),
(62, 'AA-62', 221.5, 204, 0.04, '12:30', 8, 'Jakarta', 'Cần Thơ', 55, 3, NULL),
(63, 'AA-66', 155.5, 155.5, 0.05, '11:00', 14.75, 'Washington', 'Berlin', 120, 3, NULL),
(64, 'AA-63', 216.5, 210.5, 0.04, '12:45', 7.5, 'New York', 'Praha', 80, 3, NULL),
(65, 'AA-65', 177, 177, 0.08, '20:30', 31, 'Osaka', 'Washington', 195, 4, NULL),
(66, 'AA-64', 211.5, 155.5, 0.06, '01:30', 14.25, 'Cairo', 'Jakarta', 115, 3, NULL),
(67, 'AA-67', 214, 164.5, 0.05, '06:30', 8, 'Canberra', 'Taipei', 105, 3, NULL),
(68, 'AA-69', 84.5, 48.5, 0.04, '22:45', 9.25, 'Singapore', 'Taipei', 90, 3, NULL),
(69, 'AA-70', 215.5, 215.5, 0.04, '14:00', 7.5, 'Praha', 'New York', 55, 3, NULL),
(70, 'AA-68', 241, 228, 0.08, '09:45', 28, 'Osaka', 'New York', 165, 4, NULL),
(71, 'AA-71', 73.5, 69.5, 0.04, '09:15', 7.75, 'Singapore', 'Cần Thơ', 45, 2, NULL),
(72, 'AA-72', 72.5, 69.5, 0.03, '15:15', 3.5, 'Rome', 'Rome', 35, 2, NULL),
(73, 'AA-73', 185, 137, 0.05, '22:45', 13, 'Singapore', 'Praha', 110, 3, NULL),
(74, 'AA-74', 263.5, 223.5, 0.05, '17:45', 12.25, 'Đà Nẵng', 'Dubai', 90, 3, NULL),
(75, 'AA-76', 93.5, 93.5, 0.05, '15:00', 12.5, 'Los Angeles', 'Abu Dhabi', 105, 3, NULL),
(76, 'AA-75', 151.5, 75, 0.06, '22:30', 17.25, 'Washington', 'New Delhi', 115, 3, NULL),
(77, 'AA-77', 274, 248, 0.05, '22:45', 14.5, 'Canberra', 'Osaka', 110, 3, NULL),
(78, 'AA-78', 471.5, 327.5, 0.09, '02:00', 34, 'Shanghai', 'Chicago', 215, 4, '[208,209]'),
(79, 'AA-79', 80.5, 62.5, 0.04, '04:15', 4.25, 'Praha', 'Moskva', 75, 3, NULL),
(80, 'AA-81', 82.5, 58.5, 0.04, '22:15', 5.5, 'Singapore', 'Đà Nẵng', 90, 3, NULL),
(81, 'AA-80', 252.5, 239.5, 0.05, '18:15', 13.5, 'Berlin', 'Bangkok', 105, 3, NULL),
(82, 'AA-82', 267.5, 219.5, 0.05, '02:00', 12.75, 'Canberra', 'Praha', 90, 3, NULL),
(83, 'AA-83', 223.5, 207.5, 0.04, '14:00', 6.25, 'Huế', 'Canberra', 95, 3, NULL),
(84, 'AA-84', 262.5, 216, 0.09, '00:45', 32.25, 'New York', 'Beijing', 175, 4, NULL),
(85, 'AA-85', 256, 197.5, 0.05, '19:00', 11, 'London', 'Las Vegas', 95, 3, NULL),
(86, 'AA-86', 365.5, 307.5, 0.08, '10:45', 30.25, 'Chicago', 'Seoul', 195, 4, NULL),
(87, 'AA-87', 209, 202, 0.04, '02:00', 6.75, 'Đà Nẵng', 'Seoul', 85, 3, NULL),
(88, 'AA-88', 153.5, 149, 0.04, '21:15', 10.25, 'Singapore', 'Taipei', 70, 3, NULL),
(89, 'AA-89', 208.5, 196.5, 0.03, '02:15', 2.25, 'Praha', 'London', 60, 3, NULL),
(90, 'AA-91', 161, 161, 0.06, '06:00', 17.25, 'Hội An', 'London', 115, 3, NULL),
(91, 'AA-90', 200.5, 156.5, 0.07, '08:30', 24, 'New York', 'Huế', 165, 4, NULL),
(92, 'AA-92', 97, 94, 0.04, '22:15', 8.5, 'TP.HCM', 'Moskva', 55, 3, NULL),
(93, 'AA-93', 175.5, 106.5, 0.07, '04:15', 23, 'London', 'Tokyo', 165, 4, NULL),
(94, 'AA-96', 180, 163, 0.06, '19:00', 19.75, 'Huế', 'London', 105, 3, NULL),
(95, 'AA-95', 228, 198, 0.05, '03:30', 13.5, 'Đà Nẵng', 'Berlin', 115, 3, NULL),
(96, 'AA-94', 263, 231, 0.04, '17:30', 8, 'Los Angeles', 'London', 90, 3, NULL),
(97, 'AA-97', 247, 179, 0.06, '02:30', 16, 'Moskva', 'Taipei', 120, 3, NULL),
(98, 'AA-98', 247.5, 219.5, 0.04, '22:15', 10.25, 'Praha', 'Dubai', 70, 3, NULL),
(99, 'AA-99', 113, 64, 0.05, '03:30', 16, 'Moskva', 'Chicago', 110, 3, NULL),
(100, 'AB-01', 173, 104, 0.07, '15:15', 20, 'Chicago', 'Jakarta', 160, 4, NULL),
(101, 'AB-00', 270, 234, 0.04, '06:15', 9.5, 'Hà Nội', 'Canberra', 95, 3, NULL),
(102, 'AB-03', 241, 157, 0.07, '05:00', 19, 'London', 'Seoul', 120, 3, NULL),
(103, 'AB-02', 136, 127, 0.03, '21:15', 2.75, 'Dubai', 'Abu Dhabi', 50, 3, NULL),
(104, 'AB-04', 87, 46.5, 0.04, '11:30', 8.75, 'TP.HCM', 'Berlin', 70, 3, NULL),
(105, 'AB-05', 137, 129.5, 0.03, '06:00', 1.25, 'Los Angeles', 'New York', 50, 3, NULL),
(106, 'AB-06', 201.5, 142, 0.06, '19:30', 14.75, 'Huế', 'Paris', 120, 3, NULL),
(107, 'AB-07', 295, 236.5, 0.05, '01:00', 13, 'Osaka', 'TP.HCM', 100, 3, NULL),
(108, 'AB-09', 232, 194, 0.06, '07:45', 18.5, 'Hội An', 'Liverpool', 155, 4, NULL),
(109, 'AB-08', 99, 75, 0.04, '02:00', 7.75, 'Tokyo', 'Hội An', 65, 3, NULL),
(110, 'AB-10', 139, 85, 0.06, '03:30', 17, 'Canberra', 'Washington', 125, 4, NULL),
(111, 'AB-11', 195.5, 189.5, 0.03, '14:00', 1.25, 'Huế', 'Hà Nội', 40, 2, NULL),
(112, 'AB-13', 95, 83, 0.03, '13:45', 3.25, 'Tokyo', 'Beijing', 45, 2, NULL),
(113, 'AB-16', 115.5, 105, 0.04, '01:00', 8, 'Hà Nội', 'Shanghai', 60, 3, '[24,25,26,27,32,33,48,49,50,51,52,53,54,55,56,57,58,59]'),
(114, 'AB-15', 292.5, 278.5, 0.08, '22:30', 29, 'Beijing', 'Liverpool', 175, 4, NULL),
(115, 'AB-14', 30, 23, 0.03, '20:30', 4.5, 'Las Vegas', 'Chicago', 60, 3, NULL),
(116, 'AB-12', 260, 232.5, 0.05, '19:30', 11, 'Dubai', 'Đà Nẵng', 95, 3, NULL),
(117, 'AB-17', 189, 181.5, 0.05, '18:30', 16.25, 'New Delhi', 'New York', 100, 3, NULL),
(118, 'AB-18', 85, 58, 0.04, '07:45', 4.75, 'Bangkok', 'TP.HCM', 85, 3, NULL),
(119, 'AB-32', 251.5, 202, 0.09, '04:30', 32.75, 'Shanghai', 'Las Vegas', 225, 4, NULL),
(120, 'AB-19', 102.5, 87.5, 0.04, '08:30', 5.25, 'Los Angeles', 'Liverpool', 50, 3, NULL),
(121, 'AB-20', 273, 181, 0.07, '14:45', 23.25, 'Manchester', 'Huế', 165, 4, NULL),
(122, 'AB-21', 180.5, 154.5, 0.05, '16:30', 11.5, 'Berlin', 'Bangkok', 80, 3, NULL),
(123, 'AB-23', 125, 97, 0.04, '14:15', 5.5, 'Berlin', 'Cairo', 85, 3, NULL),
(124, 'AB-25', 262.5, 218.5, 0.07, '03:00', 20.5, 'Bangkok', 'Washington', 165, 4, NULL),
(125, 'AB-22', 230, 180, 0.07, '13:15', 20.5, 'Singapore', 'Washington', 120, 3, NULL),
(126, 'AB-24', 196, 193, 0.03, '19:00', 2.25, 'Jakarta', 'Jakarta', 60, 3, NULL),
(127, 'AB-26', 304, 216, 0.07, '00:15', 23.75, 'Cairo', 'Seoul', 140, 4, '[44,45,52,53,61,62,69,70,80,81,88,89,109,110,111,116,117,118,119,124,125,126,127,132,133,134,135]'),
(128, 'AB-28', 141.5, 134, 0.03, '11:15', 1.75, 'Huế', 'Huế', 35, 2, NULL),
(129, 'AB-27', 310, 274, 0.06, '07:30', 16.5, 'Jakarta', 'Manchester', 120, 3, NULL),
(130, 'AB-30', 228, 199.5, 0.06, '21:45', 17.5, 'Rome', 'Đà Nẵng', 130, 4, NULL),
(131, 'AB-29', 172, 158, 0.06, '02:00', 15.5, 'Taipei', 'Abu Dhabi', 125, 4, NULL),
(132, 'AB-31', 394, 281.5, 0.07, '00:45', 25.75, 'Washington', 'Đà Nẵng', 180, 4, '[54,55,62,63]'),
(133, 'AB-34', 161, 157, 0.04, '03:30', 3.75, 'Đà Nẵng', 'Jakarta', 60, 3, NULL),
(134, 'AB-35', 242.5, 242.5, 0.05, '10:00', 10.75, 'Berlin', 'Jakarta', 105, 3, NULL),
(135, 'AB-33', 246.5, 174.5, 0.06, '19:45', 19.75, 'Moskva', 'Tokyo', 120, 3, NULL),
(136, 'AB-36', 115.5, 109.5, 0.05, '13:45', 11.5, 'Jakarta', 'Beijing', 110, 3, NULL),
(137, 'AB-37', 145, 138, 0.06, '16:00', 14.75, 'Seoul', 'Dubai', 125, 4, NULL),
(138, 'AB-38', 185.5, 160, 0.06, '16:30', 17.25, 'Seoul', 'Moskva', 145, 4, NULL),
(139, 'AB-40', 211.5, 155.5, 0.05, '15:15', 16, 'New Delhi', 'New York', 110, 3, NULL),
(140, 'AB-41', 129, 111, 0.03, '14:30', 3.75, 'Hội An', 'Jakarta', 70, 3, NULL),
(141, 'AB-39', 277.5, 162, 0.09, '17:15', 30.25, 'Shanghai', 'Chicago', 190, 4, NULL),
(142, 'AB-42', 88.5, 82.5, 0.03, '08:00', 1.25, 'Jakarta', 'Jakarta', 35, 2, NULL),
(143, 'AB-44', 290.5, 255.5, 0.06, '13:15', 17, 'Seoul', 'Dubai', 120, 3, NULL),
(144, 'AB-43', 85.5, 85.5, 0.05, '17:15', 14.75, 'Tokyo', 'Singapore', 120, 3, NULL),
(145, 'AB-46', 134, 120, 0.04, '16:15', 7.5, 'New Delhi', 'Canberra', 60, 3, NULL),
(146, 'AB-45', 222, 197, 0.05, '02:45', 8, 'New York', 'Berlin', 90, 3, NULL),
(147, 'AB-47', 190, 163, 0.06, '08:30', 20.75, 'Chicago', 'Cần Thơ', 120, 3, NULL),
(148, 'AB-50', 301.5, 289.5, 0.08, '04:00', 27.25, 'Cairo', 'Tokyo', 180, 4, NULL),
(149, 'AB-51', 56.5, 44.5, 0.03, '08:00', 2, 'Chicago', 'Chicago', 45, 2, NULL),
(150, 'AB-49', 50, 44, 0.04, '20:30', 8.25, 'Abu Dhabi', 'TP.HCM', 80, 3, NULL),
(151, 'AB-48', 228, 180, 0.05, '14:15', 13, 'New Delhi', 'Taipei', 120, 3, NULL),
(152, 'AB-52', 134, 131, 0.03, '15:45', 3.75, 'Singapore', 'Singapore', 45, 2, NULL),
(153, 'AB-54', 163.5, 105, 0.05, '22:45', 11.75, 'Đà Nẵng', 'Abu Dhabi', 110, 3, NULL),
(154, 'AB-53', 157, 139.5, 0.03, '06:45', 6, 'Jakarta', 'Canberra', 55, 3, NULL),
(155, 'AB-55', 107, 86, 0.05, '04:45', 12.75, 'Canberra', 'Cairo', 100, 3, NULL),
(156, 'AB-57', 152.5, 152.5, 0.06, '12:45', 18.5, 'Abu Dhabi', 'Shanghai', 150, 4, NULL),
(157, 'AB-56', 239, 228.5, 0.07, '11:30', 23.25, 'Rome', 'Taipei', 125, 4, NULL),
(158, 'AB-60', 208, 160.5, 0.06, '05:30', 19.75, 'Beijing', 'New Delhi', 125, 4, NULL),
(159, 'AB-70', 244, 237.5, 0.05, '18:30', 15, 'Huế', 'Abu Dhabi', 90, 3, NULL),
(160, 'AB-59', 169, 164.5, 0.04, '08:00', 10, 'Jakarta', 'Abu Dhabi', 70, 3, NULL),
(161, 'AB-58', 30.5, 27.5, 0.03, '16:15', 4.75, 'Moskva', 'Berlin', 45, 2, NULL),
(162, 'AB-71', 144, 142, 0.04, '03:30', 7, 'Praha', 'Liverpool', 80, 3, NULL),
(163, 'AB-61', 377.5, 287.5, 0.09, '11:15', 29.25, 'Washington', 'Shanghai', 210, 4, NULL),
(164, 'AB-63', 201.5, 143, 0.05, '19:45', 11, 'New York', 'Dubai', 85, 3, NULL),
(165, 'AB-64', 146, 131, 0.04, '18:30', 4.25, 'Cairo', 'London', 45, 2, NULL),
(166, 'AB-62', 199, 191, 0.03, '06:15', 5.75, 'Rome', 'London', 55, 3, NULL),
(167, 'AB-65', 242, 212, 0.05, '11:30', 11.25, 'Las Vegas', 'Praha', 75, 3, NULL),
(168, 'AB-67', 108.5, 66.5, 0.06, '14:30', 16.5, 'Abu Dhabi', 'Chicago', 135, 4, NULL),
(169, 'AB-68', 322.5, 310.5, 0.08, '09:30', 26.25, 'Las Vegas', 'Hội An', 180, 4, NULL),
(170, 'AB-72', 139, 126.5, 0.07, '13:30', 23.75, 'Đà Nẵng', 'Washington', 140, 4, NULL),
(171, 'AB-66', 81.5, 57.5, 0.04, '07:00', 6, 'Bangkok', 'Cần Thơ', 85, 3, NULL),
(172, 'AB-69', 151, 145, 0.03, '06:30', 5.5, 'Rome', 'Praha', 55, 3, NULL),
(173, 'AB-75', 274, 216.5, 0.07, '17:30', 21, 'Shanghai', 'London', 170, 4, NULL),
(174, 'AB-73', 288, 216, 0.06, '01:45', 17.5, 'Praha', 'Đà Nẵng', 115, 3, NULL),
(175, 'AB-76', 111, 102, 0.04, '15:30', 8, 'Los Angeles', 'Praha', 100, 3, NULL),
(176, 'AB-74', 165, 117, 0.06, '19:15', 16.25, 'Dubai', 'Osaka', 135, 4, NULL),
(177, 'AB-77', 222, 173, 0.06, '00:15', 16.25, 'Abu Dhabi', 'Chicago', 130, 4, '[5,6,7,13,14,15,18,19,20,21,23,24,25,26,27,32,33,34,35,40,41,44,45,46,52,53,54,55,60,61,62,63,68,69,71,74,75,78,79,86,87]'),
(178, 'AB-79', 108.5, 90.5, 0.04, '20:15', 7.25, 'Cairo', 'Abu Dhabi', 75, 3, NULL),
(179, 'AB-78', 256, 215.5, 0.08, '21:15', 26.25, 'Hà Nội', 'Washington', 165, 4, NULL),
(180, 'AB-81', 158, 151.5, 0.05, '19:00', 14.5, 'Hà Nội', 'New Delhi', 80, 3, NULL),
(181, 'AB-80', 199, 181, 0.05, '13:30', 14.25, 'Manchester', 'Dubai', 90, 3, NULL),
(182, 'AB-82', 225, 165, 0.05, '07:30', 12, 'Huế', 'Moskva', 95, 3, NULL),
(183, 'AB-83', 100, 93, 0.05, '15:00', 12.5, 'Huế', 'Moskva', 105, 3, NULL),
(184, 'AB-84', 164, 137, 0.04, '07:30', 7.5, 'Tokyo', 'Đà Nẵng', 70, 3, NULL),
(185, 'AB-87', 79.5, 63.5, 0.04, '20:00', 7.75, 'Cần Thơ', 'Singapore', 80, 3, NULL),
(186, 'AB-86', 310.5, 178.5, 0.09, '00:45', 33.75, 'Tokyo', 'Las Vegas', 195, 4, NULL),
(187, 'AB-85', 302, 225.5, 0.06, '21:30', 17, 'Huế', 'London', 130, 4, NULL),
(188, 'AB-88', 94, 79, 0.04, '07:15', 6.25, 'Dubai', 'Paris', 60, 3, NULL),
(189, 'AB-89', 155, 142.5, 0.04, '08:45', 5.75, 'Huế', 'Seoul', 50, 3, NULL),
(190, 'AB-91', 220.5, 209.5, 0.05, '23:30', 9.25, 'Abu Dhabi', 'Liverpool', 70, 3, NULL),
(191, 'AB-90', 56, 51, 0.03, '05:15', 2.5, 'Praha', 'Cairo', 55, 3, NULL),
(192, 'AB-92', 22, 18, 0.03, '22:00', 6.75, 'Praha', 'Cairo', 50, 3, NULL),
(193, 'AB-93', 408.5, 318.5, 0.09, '04:00', 31.75, 'Los Angeles', 'Shanghai', 175, 4, NULL),
(194, 'AB-97', 204, 185, 0.06, '08:00', 19.25, 'London', 'Taipei', 155, 4, NULL),
(195, 'AB-94', 188, 131, 0.06, '00:00', 20.75, 'Hội An', 'Cairo', 145, 4, '[4,5,6,7,12,13,14,23,28,29,30,31,36,42,43,44,47,48,49]'),
(196, 'AB-95', 63.5, 41, 0.04, '08:30', 7, 'Praha', 'Los Angeles', 95, 3, NULL),
(197, 'AB-96', 217.5, 178.5, 0.05, '11:45', 14.75, 'Shanghai', 'Singapore', 105, 3, NULL),
(198, 'AB-98', 179.5, 163, 0.09, '00:45', 31.75, 'Chicago', 'Shanghai', 190, 4, NULL),
(199, 'AB-99', 94.5, 86.5, 0.03, '16:00', 1.75, 'Shanghai', 'Shanghai', 65, 3, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Anonymous',
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `image`, `email`, `password`, `isAdmin`) VALUES
(1, 'Anopheles T', '0123564978', 'images/b9b3571590b766f7cedba5d1e5e7660c.png', 'ducthinhtrai@gmail.com', 'q', 0),
(2, '69 Men', NULL, NULL, 'dcmen69@yopmail.com', 'q', 0),
(3, 'Mến đây', NULL, NULL, 'dcmen70@yopmail.com', 'q', 0),
(4, 'D2', NULL, NULL, 'dcmen2@yopmail.com', 'q', 0),
(5, 'Admin', '0123456789', 'images/a660861eeef93297f53a53b9cfe5c6c5.jpg', 'admin@gmail.com', 'q', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
