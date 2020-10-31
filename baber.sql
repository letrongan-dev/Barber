-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 31, 2020 lúc 01:55 PM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `baber`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `combo_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name_stylist` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `appointment`
--

INSERT INTO `appointment` (`id`, `combo_id`, `date`, `message`, `name`, `name_stylist`, `phone`, `time`, `id_user`) VALUES
(2, 2, '2020-10-28 00:00:00', '', 'Lê Trọng Ân', 'Trần Thị Đẹp', '0386956572', '12:30am', 4),
(3, 1, '2020-10-30 13:29:56', 'null', 'Nguyễn Văn A', 'null', '0918482456', '1:30pm', 0),
(6, 1, '2020-10-30 13:29:56', '', 'Nguyễn Văn A', '', '0918482456', '1:30pm', 0);

--
-- Bẫy `appointment`
--
DELIMITER $$
CREATE TRIGGER `delete_bill_table` AFTER DELETE ON `appointment` FOR EACH ROW BEGIN
DECLARE v_appointment_id INTEGER;
set v_appointment_id = old.id;

delete from bill where appointment_id = v_appointment_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_to_bill_table` AFTER INSERT ON `appointment` FOR EACH ROW BEGIN
DECLARE combo_id INTEGER;
DECLARE user_id INTEGER;
DECLARE appointment_id INTEGER;
DECLARE customer char(255);
DECLARE phone char(10);
SET combo_id = New.combo_id;
SET user_id = NEW.id_user;
SET appointment_id = New.id;
SET customer = new.name;
SET phone = new.phone;
INSERT INTO bill (combo_id,user_id,appointment_id,customer,phone)VALUES (combo_id,user_id,appointment_id,customer,phone);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_to_bill_table` AFTER UPDATE ON `appointment` FOR EACH ROW BEGIN
DECLARE v_user_id INTEGER;
DECLARE v_appointment_id INTEGER;
set v_user_id = new.id_user;
set v_appointment_id = (select app.id from appointment app inner join bill b on app.id = b.appointment_id );

update bill set user_id = v_user_id where appointment_id = v_appointment_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `combo_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT '0',
  `total` double DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`id`, `appointment_id`, `combo_id`, `created_at`, `customer`, `note`, `phone`, `status`, `total`, `updated_at`, `user_id`) VALUES
(1, 2, 2, '2020-10-27 07:49:06', 'Lê Trọng Ân', NULL, '0386956572', 0, 0, '2020-10-27 07:49:06', 4),
(2, 2, 2, '2020-10-28 01:15:44', 'Lê Trọng Ân', NULL, '0386956572', 1, 490000, '2020-10-28 01:15:44', 4),
(3, 2, 2, '2020-10-28 01:32:50', 'Lê Trọng Ân', NULL, '0386956572', 1, 500000, '2020-10-28 01:32:50', 4),
(8, 6, 1, '2020-10-30 06:47:29', 'Nguyễn Văn A', NULL, '0918482456', 0, 0, '2020-10-30 06:47:29', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `img_blog` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT '0',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `blog`
--

INSERT INTO `blog` (`id`, `content`, `created_at`, `date`, `description`, `img_blog`, `slug`, `status`, `title`, `updated_at`) VALUES
(1, '<h2>1.&nbsp;<strong>Cơn sốt đầu cắt Moi</strong></h2>\r\n\r\n<p>Bắt nguồn từ một clip ngắn tr&ecirc;n mạng, cơn sốt đầu cắt moi đ&atilde; trở th&agrave;nh hiện tượng tr&ecirc;n mạng x&atilde; hội với h&agrave;ng triệu meme h&agrave;i hước, l&agrave;m khuynh đảo nam giới n&oacute;i ri&ecirc;ng cũng như của những t&iacute;n đồ y&ecirc;u c&aacute;i đẹp n&oacute;i chung.</p>\r\n\r\n<p>H&igrave;nh ảnh đầu Moi đang g&acirc;y sốt hay c&ograve;n gọi l&agrave; t&oacute;c Mullet &ndash; một kiểu t&oacute;c phổ biến rất được l&ograve;ng sao Việt cũng như loạt sao quốc tế như Đức Ph&uacute;c, Sơn T&ugrave;ng, Ng&ocirc; Kiến Huy, Noo Phước Thịnh, G-Dragon, Mino, Billie Eilish.</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/toc-moi-16-1.jpg\" /></p>\r\n	</li>\r\n	<li>\r\n	<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/toc-moi-23.jpg\" /></p>\r\n	</li>\r\n</ul>\r\n\r\n<p>Thế mới thấy đ&acirc;y kh&ocirc;ng phải l&agrave; kiểu t&oacute;c xấu như đa phần mọi người đang nghĩ v&agrave; vội bu&ocirc;ng lời ch&ecirc; bai.</p>\r\n\r\n<p>30Shine sẽ gi&uacute;p anh đi t&igrave;m kiểu t&oacute;c chuẩn moi ph&ugrave; hợp nhất, &ldquo;trending&rdquo; nhất.</p>\r\n\r\n<h2>2. Định nghĩa t&oacute;c moi chuẩn</h2>\r\n\r\n<p>Moi l&agrave; tiểu t&oacute;c cạo s&aacute;t hai b&ecirc;n, để d&agrave;i phần m&aacute;i &amp; g&aacute;y (thường l&agrave; cắt undercut), trong khi đ&oacute; phần t&oacute;c tr&ecirc;n của ch&uacute;ng được thả xuống một ch&uacute;t, tạo ra cảm gi&aacute;c như phần t&oacute;c n&agrave;y bị moi đi mất. Đ&acirc;y l&agrave; sự kết hợp giữa 2 kiểu t&oacute;c Ch&acirc;u &Acirc;u cổ điển: Frence Crop &amp; Mullet. Đầu Moi lu&ocirc;n được đ&aacute;nh gi&aacute; cao về sự tiện dụng cũng như thẩm mỹ n&ecirc;n vẫn được giới trẻ săn đ&oacute;n cũng ưa chuộng từ l&acirc;u.</p>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/61585944_2316906351858487_2222135144586674176_n-1.jpg\" /></p>\r\n\r\n<h2>3. T&oacute;c moi ph&ugrave; hợp với những khu&ocirc;n mặt n&agrave;o?</h2>\r\n\r\n<p>Kh&ocirc;ng phải tự dưng m&agrave; đầu moi lại trở th&agrave;nh xu hướng t&oacute;c được ưa chuộng nhiều năm liền. Kiểu t&oacute;c n&agrave;y gần như ph&ugrave; hợp với mọi khu&ocirc;n mặt, chỉ cần bạn nu&ocirc;i một m&aacute;i t&oacute;c c&oacute; độ d&agrave;i v&agrave; d&agrave;y vừa đủ để tạo n&ecirc;n một kiểu t&oacute;c moi đ&uacute;ng chuẩn.</p>\r\n\r\n<p>Ngo&agrave;i ra yếu tố kh&ocirc;ng k&eacute;m quan trọng để tạo n&ecirc;n một kiểu t&oacute;c chuẩn moi ch&iacute;nh l&agrave; người thợ cắt t&oacute;c. Đừng qu&ecirc;n lựa chọn một địa chỉ l&agrave;m t&oacute;c uy t&iacute;n, với những stylist tay nghề cứng để trao niềm tin nh&eacute;.</p>\r\n\r\n<p>Nếu đang th&iacute;ch kiểu t&oacute;c moi nhưng lo lắng sẽ trở th&agrave;nh chuyện cười như cậu bạn tr&ecirc;n mạng th&igrave; hay đến ngay 30Shine. C&aacute;c stylist 30Shine kh&ocirc;ng chỉ gi&uacute;p bạn cắt một kiểu t&oacute;c moi đ&uacute;ng chuẩn m&agrave; c&ograve;n gi&uacute;p bạn biến h&oacute;a kiểu t&oacute;c moi trở n&ecirc;n đa dạng, nhiều m&agrave;u sắc hơn ph&ugrave; hợp với từng khu&ocirc;n mặt cũng như nhu cầu đi học, đi l&agrave;m của mỗi anh em.</p>\r\n\r\n<h2>4. C&aacute;c mẫu t&oacute;c moi hot trend kh&ocirc;ng thể bỏ lỡ</h2>\r\n\r\n<h3>4.1 Layer moi nhuộm t&ocirc;ng trầm</h3>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/layer-moi-kieu-toc-danh-cho-mat-beo-dep-nhat-2020-30shine-4-1.jpg\" /></p>\r\n\r\n<p><em>Kiểu t&oacute;c H&agrave;n Quốc đơn giản rất ph&ugrave; hợp với c&aacute;c bạn học sinh, sinh vi&ecirc;n</em></p>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/layer-moi-kieu-toc-danh-cho-mat-beo-dep-nhat-2020-30shine-7-682x1024.jpg\" /></p>\r\n\r\n<p><img alt=\"\" src=\"http://blog.30shine.com/wp-content/uploads/2020/09/maxresdefault-1-1.jpg\" /></p>\r\n\r\n<h3>4.2 Layer moi uốn con s&acirc;u</h3>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/Screenshot_15.png\" /></p>\r\n\r\n<p><em>Uốn con s&acirc;u với c&aacute;c lọn t&oacute;c uốn thẳng l&agrave;m t&ocirc;n l&ecirc;n vẻ mạnh mẽ nam t&iacute;nh m&agrave;</em><br />\r\n<em>kh&ocirc;ng tốn thời gian tạo kiểu mỗi ng&agrave;y</em></p>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/THA8842-1024x684.jpg\" /></p>\r\n\r\n<p><em>Cầu thủ Đ&igrave;nh Trọng cũng đ&atilde; đ&iacute;ch th&acirc;n đến 30Shine để chơi lớn với moi uốn con s&acirc;u</em></p>\r\n\r\n<h3>4.3 Layer moi nhuộm thời trang</h3>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/Text-l%C3%A0-Ki%E1%BB%83u-T%C3%B3c-Layer-Moi_2-1024x576.jpg\" /></p>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/Artboard-4.png\" /></p>\r\n\r\n<p><em>Nổi bật, c&aacute; t&iacute;nh phi&ecirc;n bản với layer moi kh&oacute;i trắng</em></p>\r\n\r\n<h3>4.4 Layer moi highlight</h3>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/Layer-Moi-2-1024x576.jpg\" /></p>\r\n\r\n<p><em>Nhấn nh&aacute; với v&agrave;i đường ligt s&aacute;ng m&agrave;u sẽ tạo điểm nhấn cho phong c&aacute;ch của bạn</em></p>\r\n', '2020-10-28 03:41:07', '2020-10-28 03:41:07', 'Cụm từ “đầu cắt moi” top trending mạng xã hội gần đây thì ai ai cũng biết nhưng phần trăm anh em hiểu thế nào là chuẩn moi thì 30Shine tin chắc rằng cực ít. Hãy cùng 30Shine tìm hiểu xem đầu moi có gì mà hot đến vậy.', 'formen.jpg', 'li-giai-con-sot-dau-cat-moi-toc-moi-chuan-no-phai-the-nay', 1, 'LÍ GIẢI CƠN SỐT ĐẦU CẮT MOI, TÓC MOI CHUẨN NÓ PHẢI THẾ NÀY !!!', '2020-10-28 03:41:07'),
(2, '<p><a href=\"https://blog.30shine.com/\" rel=\"nofollow\">Home</a>&nbsp;&nbsp;&raquo;&nbsp;&nbsp;<a href=\"https://blog.30shine.com/category/bai-viet-noi-bat/\" rel=\"nofollow\">B&agrave;i viết nổi bật</a>&nbsp;&nbsp;&nbsp;&raquo;&nbsp;&nbsp; KH&Aacute;M PH&Aacute; NGAY DỊCH VỤ TẨY DA CHẾT SỦI BỌT ĐANG HOT NHẤT 30SHINE</p>\r\n\r\n<h1>KH&Aacute;M PH&Aacute; NGAY DỊCH VỤ TẨY DA CHẾT SỦI BỌT ĐANG HOT NHẤT 30SHINE</h1>\r\n\r\n<p><a href=\"https://blog.30shine.com/author/quoc-anh/\" rel=\"nofollow\" title=\"Đăng bởi Anh Quốc\">Anh Quốc</a>&nbsp;&nbsp;/&nbsp;&nbsp;Th&aacute;ng Ch&iacute;n 12, 2020&nbsp;&nbsp;/&nbsp;&nbsp;Kh&ocirc;ng c&oacute; phản hồi</p>\r\n\r\n<p>Kh&ocirc;ng chỉ quan t&acirc;m đến t&oacute;c tai, 30Shine vừa cho ra mắt dịch vụ TẨY DA CHẾT SỦI BỌT ho&agrave;n to&agrave;n mới, để c&aacute;nh m&agrave;y r&acirc;u Việt Nam ng&agrave;y c&agrave;ng ho&agrave;n thiện bản th&acirc;n từ m&aacute;i t&oacute;c chỉn chu cho đến l&agrave;n da trắng s&aacute;ng. Ưu đ&atilde;i nh&acirc;n dịp ra mắt chỉ 25K cho một dịch vụ, anh em mau qua 30Shine trải nghiệm ngay.</p>\r\n\r\n<p><img alt=\"\" src=\"https://blog.30shine.com/wp-content/uploads/2020/09/1-1024x1024.jpg\" style=\"height:300px; width:300px\" /></p>\r\n\r\n<p><strong>Tẩy da chết sủi bọt cực m&ecirc; &ndash; da trắng sạch miễn ch&ecirc;</strong></p>\r\n\r\n<p>Anh em sẽ được phục vụ bởi đ&ocirc;i b&agrave;n tay nhẹ nh&agrave;ng của đội ngũ Skinner xinh đẹp, kết hợp c&ugrave;ng m&aacute;y n&acirc;ng cơ Utrasonic H&agrave;n Quốc cho l&agrave;n da săn chắc, cải thiện c&aacute;c v&ugrave;ng da chảy xệ do tuổi t&aacute;c m&agrave; kh&ocirc;ng cần phải tốn nhiều thời gian đến c&aacute;c cơ sở l&agrave;m đẹp.</p>\r\n\r\n<p>L&aacute;ng mịn, sạch s&acirc;u b&atilde; nhờ, th&ocirc;ng tho&aacute;ng lỗ ch&acirc;n l&ocirc;ng, l&agrave;n da của anh đảm bảo trắng l&ecirc;n thấy r&otilde; chỉ với một lần tẩy da chết sủi bọt.</p>\r\n\r\n<p>Trải nghiệm chất kem bọt cực xốp v&agrave; mềm mịn gi&uacute;p lấy đi lớp da chết cực k&igrave; nhẹ nh&agrave;ng m&agrave; vẫn sạch s&acirc;u, kh&ocirc;ng g&acirc;y k&iacute;ch ứng ph&ugrave; hợp với cả l&agrave;n da nhạy cảm nhất.</p>\r\n\r\n<p>Anh em cứ qua 30Shine trải nghiệm dịch vụ l&agrave;m đẹp từ đầu t&oacute;c cho tới da mặt, đảm bảo đẹp trai kh&ocirc;ng cần cố gắng.</p>\r\n\r\n<p><strong>Tại sao n&ecirc;n tẩy da chết sủi bọt tại 30Shine</strong></p>\r\n\r\n<p>L&agrave;n da h&agrave;ng ng&agrave;y phải chịu rất nhiều t&aacute;c động g&acirc;y hại từ m&ocirc;i trường như kh&oacute;i bụi, nắng gắt, vận động thể thao &ndash; đ&acirc;y cũng ch&iacute;nh l&agrave; nguy&ecirc;n nh&acirc;n g&acirc;y mụn, t&aacute;i sạm da ảnh hưởng đến ngoại h&igrave;nh v&agrave; chất lượng cuộc sống của v&ocirc; v&agrave;n nam giới.</p>\r\n\r\n<p>Vậy n&ecirc;n việc tẩy tế b&agrave;o chết sủi bọt thường xuy&ecirc;n l&agrave; hoạt động chăm s&oacute;c da v&ocirc; c&ugrave;ng cần thiết, gi&uacute;p loại bỏ bụi bẩn, vi khuẩn v&agrave; những tế b&agrave;o chết dư thừa, th&uacute;c đẩy qu&aacute; tr&igrave;nh t&aacute;i tạo da. &nbsp;</p>\r\n\r\n<p>Tẩy da chết sủi bọt với hứa hẹn sẽ trở th&agrave;nh tuyệt chi&ecirc;u F5 ngoại h&igrave;nh cực đỉnh cho c&aacute;nh m&agrave;y r&acirc;u sẵn s&agrave;ng ghi điểm trước đối phương trong mọi cuộc hẹn. Kh&ocirc;ng tin th&igrave; đến 30Shine thử ngay đi.</p>\r\n\r\n<p><a href=\"https://30shine.com/\">Đặt lịch trải nghiệm ngay</a></p>\r\n', '2020-10-28 03:42:37', '2020-10-28 03:42:37', 'KHÁM PHÁ NGAY DỊCH VỤ TẨY DA CHẾT SỦI BỌT ĐANG HOT NHẤT 30SHINE', 'bg-1.jpg', 'kham-pha-ngay-dich-vu-tay-da-chet-sui-bot-dang-hot-nhat-30shine', 1, 'KHÁM PHÁ NGAY DỊCH VỤ TẨY DA CHẾT SỦI BỌT ĐANG HOT NHẤT 30SHINE', '2020-10-28 03:42:37');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `combo`
--

CREATE TABLE `combo` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int(11) NOT NULL,
  `step1` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `step2` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `step3` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `step4` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `step5` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `combo`
--

INSERT INTO `combo` (`id`, `name`, `price`, `step1`, `step2`, `step3`, `step4`, `step5`) VALUES
(1, 'HAIR STYLE', 100000, 'Hair Dryer', 'Hair Coloring ', 'Hair Cut ', 'Hair Dresser', 'Hair Spa'),
(2, 'MAKE UP', 500000, 'Makeup ', 'Professional Makeup ', 'Blush On ', 'Facial Massage ', 'Facial Spa'),
(3, 'BODY TREATMENT', 1000000, 'Massage', 'Spa', 'Foot Spa', 'Body Spa', 'Hair Spa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `description`, `name`) VALUES
(1, 'Quản lý hệ thống', 'ROLE_ADMINISTRATOR'),
(2, 'Quản lý ', 'ROLE_MANAGER'),
(3, 'Thợ tạo mẫu ', 'ROLE_STYLIST'),
(4, 'Khách hàng', 'ROLE_MEMBER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `address`, `avatar`, `code`, `email`, `name`, `password`, `phone`, `role_id`) VALUES
(2, '180 Cao lỗ, Phường 4', 'stylist-5.jpg', 'HC4464481644', 'teo@gmail.com', 'Nguyễn Văn Tèo', '$2a$12$O.EUKsFlsx./zI0n/IQxD.nfmTUmMPXYSje8HovtP.2sI4/zHV.3K', '0386956572', 3),
(3, '180 Cao lỗ, Phường 4', 'stylist-1.jpg', 'HC4866111185', 'buoi@gmail.com', 'Bành Thị Bưởi Nè', '$2a$12$FWa0T0/KJU717yMODAVqeeD8qBfeNhpLpBdAqdf5Sav886gT2viJS', '0386956572', 2),
(4, '123 đường số 1 khu dân cư ấp 5 Phong Phú', 'stylist-2.jpg', 'HC6164341511', 'dep@gmail.com', 'Trần Thị Đẹp', '$2a$12$LnslDibq/xUpx8qki4CEseHsqHEzcGcEDodxPD0vXXDzVbozmzAtq', '123456', 3),
(6, '180 Cao lỗ, Phường 4', 'person_4.jpg', 'HC3464144655', 'ti@gmail.com', 'Nguyễn Văn Tý', '$2a$12$p70VWQ1w/tNcZpVcUGt86OKGiSMTkgcrB9iRQ5f0NeSDlcbetVwIe', '0386956572', 4),
(13, '180 Cao lỗ, Phường 4', 'default-avatar.png', 'CUS - HC18664', 'cus@gmail.com', 'Customer', '$2a$12$dGjKvt511Bki83cv1kMzBuxDgJt8rvc481P6O9QeO70wBiIlqEaHa', '0386956572', 4),
(14, NULL, 'default-avatar.png', 'CUS - HC43555', 'testapi@gmail.com', 'Test', '$2a$12$Ke02259TEHx2JTQbm8JYMuowGsUB7dNtGuYkkT3oIo5P9M.iYkXxy', '123456', 4),
(15, '123 đường số 1 khu dân cư ấp 5 Phong Phú', '100940575_1174148759605255_6473163636658405376_o.jpg', 'HC3148168211', 'le.trong.an256@gmail.com', 'Lê Trọng Ân', '$2a$12$2IhsUhVGKPQWcQ.1j5kAfOjd5Lp7T2WRST5uslBXM5sCzxqyrSvdC', '0386956572', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8tdc87a9b86kwmy80fiwo4ybc` (`combo_id`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsixmr31g7vbs03rvq35g8x4b5` (`appointment_id`),
  ADD KEY `FKny7sqflypbfg7c36guxxh0ju7` (`combo_id`),
  ADD KEY `FKs78seu7p3wfrnuhulmtpg707u` (`user_id`);

--
-- Chỉ mục cho bảng `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
