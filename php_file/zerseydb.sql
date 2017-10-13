-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2017 at 04:03 AM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zerseydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `event_detail`
--

CREATE TABLE `event_detail` (
  `google_id` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `event_date` varchar(10) NOT NULL,
  `event_time` varchar(10) NOT NULL,
  `image_url` varchar(200) NOT NULL,
  `video_url` varchar(200) NOT NULL,
  `event_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_detail`
--

INSERT INTO `event_detail` (`google_id`, `category`, `title`, `description`, `event_date`, `event_time`, `image_url`, `video_url`, `event_id`) VALUES
('115633635996443709007', 'Seminars and Conferences', 'title ', 'desp', '13/10/17', '1:59 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2Fe1698f4e-30eb-404a-9689-c1b845de6d69.png?alt=media&token=e0a31a92-945d-4a01-82fd-df95fd9ea581', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Videos%2Fe37e7296-aceb-4de0-b6dd-8f617901a0ff.mp3?alt=media&token=f33180b6-339d-4c1a-8f8f-b1ace52dd3db', 'e1698f4e-30eb-404a-9689-c1b845de6d69'),
('115633635996443709007', 'Executive Retreats and Incentive Programs', 'ttt', 'yyyy', '01/10/17', '5:26 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2Fb3b726f6-ad57-4a61-a56b-db96ef2057a1.png?alt=media&token=edce0e0d-d190-4ddb-a380-35ef4ddaeb91', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Videos%2F3c9a6186-32f0-4fab-a99c-766cca0dc244.mp3?alt=media&token=ea3b5ad0-f877-4340-8389-afc0a5be4111', 'b3b726f6-ad57-4a61-a56b-db96ef2057a1'),
('115633635996443709007', 'Trade Shows', 'fff', 'pp', '28/10/17', '2:30 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F393a5031-3db8-4727-9f65-9a01743e8bc2.png?alt=media&token=18a47611-49f2-4c37-85f6-f206c7bbe277', 'empty', '393a5031-3db8-4727-9f65-9a01743e8bc2'),
('115633635996443709007', 'Trade Shows', 'fff', 'pp', '28/10/17', '2:30 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F9c9d92dc-5af2-4c61-ad34-085dc2609134.png?alt=media&token=6ea7dc24-0b14-4b47-a2c7-b1b80ffc264c', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Videos%2F12075f1a-cf3e-41f5-8011-5035a0060b58.mp3?alt=media&token=4f41740d-eff1-40b2-9ead-ee4907c7a57e', '9c9d92dc-5af2-4c61-ad34-085dc2609134'),
('115633635996443709007', 'Product Launch Events', 'mega event', 'hello this is this', '14/10/17', '5:18 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F6ba2f102-c31e-49d2-b8f8-2456440ba382.png?alt=media&token=e8525785-0af3-4a05-bc79-a3e4cec42a04', 'empty', '6ba2f102-c31e-49d2-b8f8-2456440ba382'),
('115633635996443709007', 'Seminars and Conferences', 'fjdjdndn', 'bdnxxnncnc', '28/10/17', '9:28 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2Ff915e801-ed0d-427b-b795-a1028889e62c.png?alt=media&token=21afe957-5aed-43b3-8a72-409cf7a1c35d', 'empty', 'f915e801-ed0d-427b-b795-a1028889e62c'),
('115633635996443709007', 'Executive Retreats and Incentive Programs', 'sghdd', 'dhdd', '13/10/17', '5:53 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2Fc47821ae-3bd0-4f14-abdd-d677631b0d6c.png?alt=media&token=92e944e8-2545-4cb3-b012-bc75bd0fd21f', 'empty', 'c47821ae-3bd0-4f14-abdd-d677631b0d6c'),
('115633635996443709007', 'Executive Retreats and Incentive Programs', 'tujgxx', 'fhjfxd', '13/10/17', '5:56 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F802dfa78-5736-4e23-a031-6b14edd790cc.png?alt=media&token=d5ff663e-5a19-4e57-ba1a-e4342e2ec45d', 'empty', '802dfa78-5736-4e23-a031-6b14edd790cc'),
('115633635996443709007', 'Product Launch Events', 'Prashant', 'prashant', '13/10/17', '5:57 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F38fb4870-35ea-4344-9848-557c70ad9338.png?alt=media&token=993ff0a7-fad0-4d74-a4c7-1b6f2827cc11', 'empty', '38fb4870-35ea-4344-9848-557c70ad9338'),
('115633635996443709007', 'Trade Shows', 'pppp', 'pppp', '27/10/17', '6:57 AM', 'https://firebasestorage.googleapis.com/v0/b/zersey-7c4ce.appspot.com/o/Images%2F98bb8abb-cba3-44c6-be13-00a48939bc68.png?alt=media&token=6471bc84-2131-4b18-8d3c-1e49ce57c5aa', 'empty', '98bb8abb-cba3-44c6-be13-00a48939bc68');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `google_id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`google_id`, `name`, `email`) VALUES
('', '', ''),
('115633635996443709007', 'Prashant Agrawal', 'agrawalprashant1995@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
