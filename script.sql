USE [HanaShop]
GO
ALTER TABLE [dbo].[UserDetails] DROP CONSTRAINT [FK_UserDetails_Registration]
GO
ALTER TABLE [dbo].[Product] DROP CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[BillDetails] DROP CONSTRAINT [FK_BillDetails_Product]
GO
ALTER TABLE [dbo].[BillDetails] DROP CONSTRAINT [FK_BillDetails_Bill]
GO
ALTER TABLE [dbo].[Bill] DROP CONSTRAINT [FK_Bill_Registration]
GO
/****** Object:  Table [dbo].[UserDetails]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[UserDetails]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[Product]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[Category]
GO
/****** Object:  Table [dbo].[BillDetails]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[BillDetails]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP TABLE [dbo].[Bill]
GO
USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 1/18/2021 11:11:09 AM ******/
DROP DATABASE [HanaShop]
GO
/****** Object:  Database [HanaShop]    Script Date: 1/18/2021 11:11:09 AM ******/
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
/****** Object:  Table [dbo].[Bill]    Script Date: 1/18/2021 11:11:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[BillID] [int] NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[TotalPrice] [float] NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BillDetails]    Script Date: 1/18/2021 11:11:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillDetails](
	[BillID] [int] NOT NULL,
	[productID] [int] NOT NULL,
	[Quantity] [int] NULL,
 CONSTRAINT [PK_BillDetails] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC,
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 1/18/2021 11:11:09 AM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 1/18/2021 11:11:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productID] [int] NOT NULL,
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
/****** Object:  Table [dbo].[Registration]    Script Date: 1/18/2021 11:11:09 AM ******/
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
/****** Object:  Table [dbo].[UserDetails]    Script Date: 1/18/2021 11:11:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserDetails](
	[id] [int] NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[Phone] [nvarchar](20) NULL,
	[Address] [nvarchar](50) NULL,
 CONSTRAINT [PK_UserDetails_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Bill] ([BillID], [userID], [TotalPrice]) VALUES (10000, N'nhinh', 12)
INSERT [dbo].[BillDetails] ([BillID], [productID], [Quantity]) VALUES (10000, 10001, 1)
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0001', N'Rice')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0002', N'Drink')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C0003', N'Snack')
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (10000, N'Aquafina', N'Aquafina.jpg', N'Nước lọc', 12, N'13/01/2021', N'C0002', 1, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (10001, N'Pepsi', N'Pepsi.png', N'Nước Ngọt', 12, N'14/01/2021', N'C0002', 1, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (10002, N'7 Up', N'7 Up.png', N'Nước Ngọt', 13, N'14/01/2021', N'C0002', 1, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (10003, N'Kolakem', N'Kolakem.png', N'Kem', 15, N'18/01/2021', N'C0002', 1, 50)
INSERT [dbo].[Product] ([productID], [productName], [image], [description], [price], [createDate], [categoryID], [status], [quantity]) VALUES (10004, N'Kolakem', N'Kolakem.png', N'Kem', 15, N'18/01/2021', N'C0002', 1, 50)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'admin', N'admin', N'Admin', 1)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'hoangnhinguyen33@gmail.com', NULL, N'Hoàng Nhi', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'languagedocument@gmail.com', NULL, N'Huỳnh Tấn Hội', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'nhinh', N'123456', N'Hoàng Nhi', 0)
INSERT [dbo].[Registration] ([userID], [password], [fullname], [isAdmin]) VALUES (N'nhinhse141072@fpt.edu.vn', NULL, N'Nguyễn Hoàng Nhi', 0)
INSERT [dbo].[UserDetails] ([id], [userID], [fullname], [Phone], [Address]) VALUES (10000, N'nhinh', N'Nguyễn Hoàng Nhi', N'1685306264', N'Quảng Ngãi')
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
ALTER TABLE [dbo].[UserDetails]  WITH CHECK ADD  CONSTRAINT [FK_UserDetails_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[UserDetails] CHECK CONSTRAINT [FK_UserDetails_Registration]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
