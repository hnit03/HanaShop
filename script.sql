USE [HanaShop]
GO
ALTER TABLE [dbo].[UserDetails] DROP CONSTRAINT [FK_UserDetails_Registration]
GO
ALTER TABLE [dbo].[ProductHistory] DROP CONSTRAINT [FK_ProductHistory_Registration]
GO
ALTER TABLE [dbo].[ProductHistory] DROP CONSTRAINT [FK_ProductHistory_Product]
GO
ALTER TABLE [dbo].[Product] DROP CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[BillDetails] DROP CONSTRAINT [FK_BillDetails_Product]
GO
ALTER TABLE [dbo].[BillDetails] DROP CONSTRAINT [FK_BillDetails_Bill]
GO
ALTER TABLE [dbo].[Bill] DROP CONSTRAINT [FK_Bill_Registration]
GO
/****** Object:  Table [dbo].[UserDetails]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[UserDetails]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[ProductHistory]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[ProductHistory]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[Product]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[Category]
GO
/****** Object:  Table [dbo].[BillDetails]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[BillDetails]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP TABLE [dbo].[Bill]
GO
USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 1/23/2021 5:59:11 PM ******/
DROP DATABASE [HanaShop]
GO
/****** Object:  Database [HanaShop]    Script Date: 1/23/2021 5:59:11 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HanaShop.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[BillID] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[TotalPrice] [float] NULL,
	[numOfProduct] [int] NULL,
	[orderTime] [nvarchar](50) NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BillDetails]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillDetails](
	[bdID] [nvarchar](50) NOT NULL,
	[BillID] [nvarchar](50) NOT NULL,
	[productID] [nvarchar](50) NOT NULL,
	[Quantity] [int] NULL,
 CONSTRAINT [PK_BillDetails] PRIMARY KEY CLUSTERED 
(
	[bdID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [nvarchar](50) NOT NULL,
	[categoryName] [nvarchar](250) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productID] [nvarchar](50) NOT NULL,
	[productName] [nvarchar](50) NOT NULL,
	[image] [nvarchar](250) NOT NULL,
	[description] [nvarchar](250) NULL,
	[price] [float] NOT NULL,
	[createDate] [nvarchar](50) NOT NULL,
	[categoryID] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProductHistory]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductHistory](
	[HistoryID] [nvarchar](50) NOT NULL,
	[productID] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[action] [nvarchar](50) NULL,
	[date] [nvarchar](50) NULL,
 CONSTRAINT [PK_ProductHistory_1] PRIMARY KEY CLUSTERED 
(
	[HistoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[userID] [nvarchar](50) NOT NULL,
	[password] [varchar](30) NULL,
	[fullname] [nvarchar](250) NULL,
	[isAdmin] [bit] NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserDetails]    Script Date: 1/23/2021 5:59:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserDetails](
	[id] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[Phone] [int] NULL,
	[Address] [nvarchar](250) NULL,
 CONSTRAINT [PK_UserDetails] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'0EDA835C-B43A-4603-8372-EE84371F103F', N'nhinh', 30000, 1, N'2021-01-23 10:31:31')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'286D4B9F-C76C-40A9-B577-2B39E5CC2622', N'nhinhse141072@fpt.edu.vn', 30000, 1, N'2021-01-23 10:36:38')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'52E477E7-CDA6-4CA5-8ADB-0D30A4FA496E', N'nhinhse141072@fpt.edu.vn', 75, 1, N'2021-01-23 05:56:51')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'5BA51FA8-80F6-4BBA-95D5-28C7C5EADA3F', N'nhinhse141072@fpt.edu.vn', 110000, 1, N'2021-01-23 10:06:36')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'63C03BAC-BCB4-4B18-8554-FF6C4AFA8C66', N'nhinh', 60000, 1, N'2021-01-23 09:57:07')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'67F5ED4C-472F-4903-8C91-17EDB3115DC2', N'nhinh', 30000, 1, N'2021-01-23 10:13:23')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'8234C6F3-1B22-4BE0-8BEC-A17ED1862A6E', N'nhinhse141072@fpt.edu.vn', 50000, 1, N'2021-01-23 10:05:47')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'8A49151C-5789-4E3B-A8C6-D1458314B4A3', N'nhinhse141072@fpt.edu.vn', 40000, 1, N'2021-01-23 10:08:45')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'96A6D0BC-573A-4D50-AB75-F4EB3743B69E', N'nhinhse141072@fpt.edu.vn', 30000, 1, N'2021-01-23 10:31:38')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'A86DBFC0-C1C1-42C7-BD21-E86065115B3A', N'nhinh', 45000, 1, N'2021-01-23 10:44:35')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'A94B5571-A5E7-4345-BF50-0D3C921D6E17', N'nhinh', 40000, 1, N'2021-01-23 10:08:40')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'B50B629F-A760-45F3-B7B9-0A6AB6D716AE', N'nhinhse141072@fpt.edu.vn', 45000, 1, N'2021-01-23 10:11:44')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'CD349BB9-1ECD-4E84-BFBA-C20D4CAF80F9', N'nhinh', 75000, 2, N'2021-01-23 10:36:23')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'CD54DB11-FEDE-4AEB-ADCF-6372E206B924', N'nhinhse141072@fpt.edu.vn', 30000, 1, N'2021-01-23 10:13:04')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'D3A7A591-EFE3-4598-8846-102EE00488F4', N'nhinh', 30, 1, N'2021-01-23 12:43:55')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'E0C0595B-3FB5-4339-A9B3-C5530B179028', N'nhinh', 50000, 1, N'2021-01-23 10:06:38')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'EA157CB3-B77B-4100-81F4-E7E7BF1E674F', N'nhinh', 60000, 1, N'2021-01-23 10:39:50')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'FBC2D7C3-FFFE-4A81-95A2-5697A88E241F', N'nhinhse141072@fpt.edu.vn', 60000, 1, N'2021-01-23 10:39:54')
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice], [numOfProduct], [orderTime]) VALUES (N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'nhinhse141072@fpt.edu.vn', 60000, 9, N'2021-01-23 09:57:10')
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'295F6CDF-F708-412A-972E-C68E19489D14', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 2)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'341D1B30-C235-4951-86CC-139D99520A0A', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 2)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'34DBC89B-97C4-4EED-82FF-74316C7C4C91', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 3)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'3F70259B-B8DA-4674-9A73-6B6B07BA8D3B', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 5)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'40A6F12F-CBC1-428B-A846-DDCB7F75EFDC', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 2)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'485478AF-981C-4CCE-9B54-965C51094DCA', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 3)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'53A51153-52E2-456A-A977-2FC118BEE533', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10004', 3)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'542B30B7-DE0B-4EAA-BBC2-5DC1EA53BD53', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 11)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'56435EE4-12D9-4620-B8DB-D673EC93F5DD', N'63C03BAC-BCB4-4B18-8554-FF6C4AFA8C66', N'10002', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'676E3607-D8A7-457B-80AD-93EAE885DB7B', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 5)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'7701C0F9-6929-4440-8850-35CE3A72DF47', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'7705CB2B-E8A6-4B3E-998A-E0D22DB5704E', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 2)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'834FD24D-7D0F-4323-BE8D-2A5BDF2D55A7', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'86F0BBB6-CB35-47FD-8103-ABC6CBC397E9', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 5)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'AD4ED5D1-F7AA-406B-A36C-C61ADF5BC65B', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'B3FB74EA-D3BB-41C8-8DAC-574C1FB4AC7B', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10001', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'C012B3B3-77B6-4F63-81BC-A44FBB47E117', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 3)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'D096E6C6-3E42-4A0E-9705-19C8AA514D98', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 4)
INSERT [dbo].[BillDetails] ([bdID], [BillID], [productID], [Quantity]) VALUES (N'F1DA9B9D-2EB3-4362-81A5-31230157A573', N'FFDCFBB7-5612-473E-8EF7-7DF4355905A5', N'10002', 3)
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0001', N'Fast Food')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0002', N'Drink')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0003', N'Cookies')
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10001', N'Aquafina ', N'Aquafina.jpg', N'Water', 1, N'2000-01-20', N'C0002', 1, 5)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10002', N'Pepsi Lime', N'PepsiLime.png', N'Drink', 15, N'2020-01-20', N'C0002', 1, 0)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10003', N'Milk Coffee', N'Milk Coffee.png', N'Drink', 20, N'2021-01-20', N'C0002', 0, 35)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10004', N'PEACH JELLY TEA  ', N'PEACH JELLY TEA  .png', N'Drink', 25, N'2021-01-20', N'C0002', 1, 46)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10005', N'PEACH JELLY TEA  ', N'PEACH JELLY TEA  .png', N'Drink', 25, N'2021-01-20', N'C0002', 1, 44)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'10006', N'PEACH LEMONGRASS TEA', N'PEACHLEMONGRASSTEA.png', N'Drink', 30, N'2021-01-20', N'C0002', 1, 49)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'32EEE78D-0743-442B-8A92-C5E46E3F75A2', N'Mousse Cacao', N'Mousse Cacao.png', N'dessert', 25.1, N'2021-01-23', N'C0001', 0, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'517C37A9-A9DA-424C-AE91-07F1B305777A', N'Xa Xiu Bread', N'XaXiuBread.png', N'Bread', 25, N'2021-01-23', N'C0001', 1, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'76C1395F-2766-4834-AB52-82C550FE4118', N'Mirinda', N'Mirinda.png', N'Drink', 25, N'2021-01-23', N'C0001', 1, 0)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'8E9CF515-4BF6-4685-8E95-16882DF1D943', N'Socola', N'Socola.png', N'dessert', 15, N'2021-01-23', N'C0001', 1, 25)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'9F2F5906-B74C-422E-A9F0-D41C26905636', N'Kolakem', N'Kolakem.png', N'Kem', 15, N'2021-01-23', N'C0002', 1, 15)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (N'FA79D490-7553-47FC-8113-80DB485A332B', N'Mousse Peach', N'MoussePeach.png', N'dessert', 25.200000762939453, N'2021-01-23', N'C0003', 1, 25)
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'12743C9F-1773-45B6-A36E-BFE2C6C4D4A6', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'14B7F072-9710-4037-AFA9-08C52A03F564', N'517C37A9-A9DA-424C-AE91-07F1B305777A', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'1C8D8970-03DD-41AB-AE5D-175727B28CEF', N'76C1395F-2766-4834-AB52-82C550FE4118', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'2EAFBB83-44C5-42B6-8C9F-1F5F0190C0E1', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'45D1525F-930F-4386-8BBB-5CF1F3C056CD', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'522FF73C-F06F-42A5-8E63-A554E7DAE5A8', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'8C5C4936-B86F-4421-8FED-02836469A73E', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'8EDE70DE-D63D-4D89-AE98-DADED1FB7920', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'A5500E54-2EA9-4CBC-AA99-E4DA9BD3666F', N'10002', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'B6DAF4B0-3429-431B-975B-33CE4D47FE5C', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'B70870BB-953F-4514-93A7-8FB67B631655', N'517C37A9-A9DA-424C-AE91-07F1B305777A', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'C5A97C16-91A4-4115-9B2B-EC7C3668695C', N'10003', N'admin', N'Update status', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'D1DFAF17-CA11-4FAE-9481-831A8D73C946', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'E380492F-33BF-485A-8DB5-B05E44C2B547', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[ProductHistory] ([HistoryID], [productID], [userID], [action], [date]) VALUES (N'FC5A8089-0743-4D6D-B32D-4D58C161467A', N'10001', N'admin', N'Update Details', N'2021-01-23')
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'admin', N'admin', N'Admin', 1)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'hoangnhinguyen33@gmail.com', N'123456', N'Hoàng Nhi Nguyễn', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'languagedocument@gmail.com', N'123456', N'Huỳnh Tấn Hội', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'nhinh', N'123456', N'Hoàng Nhi', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'nhinhse141072@fpt.edu.vn', N'123456', N'Nguyễn Hoàng Nhi', 0)
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'057C3688-7783-42D1-BE7D-5C935DFB7E8A', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'083398FE-868A-4BB8-977A-CE60ADB14543', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'1F5B6B97-C54B-49F7-9641-9D2D0C4897FF', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'212CF35C-CEC8-435F-8F1F-E23CBB2B9528', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'23F4970C-1DD9-49DA-9658-AEF0338ACE8D', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'348642BE-9B63-40D7-8D4D-8060932BC39A', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'3E2F2131-229D-46C0-8B1D-A0B72D56F84B', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'4D8FF6AB-282D-4EBE-8FD0-47C825B4162B', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'5243FE22-003E-48E6-88AA-0F63C813C496', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'5CAAF633-74C0-4FFC-B0F2-34675A9F136B', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'63C43938-C3B1-4512-BC39-48578FB537D2', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'7C0278CA-1C1F-4F4A-8B71-091D75E5AC21', N'languagedocument@gmail.com', N'Nguyễn Hoàng Nhi', 1685306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'7D34E89D-811B-4E22-A6C7-12C00B0A52F0', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'7F0F25A8-EE43-4DAE-B04A-33163629D533', N'languagedocument@gmail.com', N'Hoàng Nhi', 385306264, N'76/18 Nguyên Hồng')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'93A5A49E-1ECF-4157-921E-FF1EDD4DD36E', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'9ECA1E39-C6CA-462B-A5AC-3E794BE585BE', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'A5056543-5CA3-4156-9914-98072E879D90', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'A942FD73-57D5-482C-8F03-0FC6A92ECDF6', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'AF2D94EB-1F43-42C3-BE2F-2B2F2F84B1B2', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'C4293E58-D53A-46ED-8132-B30201DC7F44', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'C92596FE-1639-4BB8-A95D-17F1B6D2751A', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'D0159FBB-E999-462D-B75C-10F143C42043', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'D5CC11C0-3F55-42AB-8789-1FE7A929EBAB', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'DCF7E325-896F-44A1-B895-9F041EE37F2A', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'DF9A5CE4-AE98-4C30-A565-45F20263A816', N'languagedocument@gmail.com', N'Nguyễn Hoàng Nhi', 1685306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'ED0073E6-264F-4938-87F5-1ECE4E5D3E38', N'nhinhse141072@fpt.edu.vn', N'Nguyễn Hoàng Nhi', 385306264, N'Quảng Ngãi')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'F4910E6D-A6EE-4F25-8EF2-E1C273068960', N'languagedocument@gmail.com', N'Nguyễn Hoàng Nhi', 1685306264, N'76/18 Nguyên Hồng')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'FDE272D0-A3DA-4C97-920E-D69B2F8A94EE', N'nhinh', N'ss', 11, N'ss')
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (N'FEF30052-27BC-4904-9950-D004B0770880', N'nhinh', N'ss', 11, N'ss')
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Registration]
GO
ALTER TABLE [dbo].[BillDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillDetails_Bill] FOREIGN KEY([BillID])
REFERENCES [dbo].[Bill] ([BillID])
GO
ALTER TABLE [dbo].[BillDetails] CHECK CONSTRAINT [FK_BillDetails_Bill]
GO
ALTER TABLE [dbo].[BillDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillDetails_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[BillDetails] CHECK CONSTRAINT [FK_BillDetails_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[ProductHistory]  WITH CHECK ADD  CONSTRAINT [FK_ProductHistory_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[ProductHistory] CHECK CONSTRAINT [FK_ProductHistory_Product]
GO
ALTER TABLE [dbo].[ProductHistory]  WITH CHECK ADD  CONSTRAINT [FK_ProductHistory_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[ProductHistory] CHECK CONSTRAINT [FK_ProductHistory_Registration]
GO
ALTER TABLE [dbo].[UserDetails]  WITH CHECK ADD  CONSTRAINT [FK_UserDetails_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[UserDetails] CHECK CONSTRAINT [FK_UserDetails_Registration]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
