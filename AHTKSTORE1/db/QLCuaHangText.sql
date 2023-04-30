USE [master]
GO
/****** Tạo database LKDienTu ******/
CREATE DATABASE [LKDienTu]
GO
ALTER DATABASE [LKDienTu] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LKDienTu].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LKDienTu] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LKDienTu] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LKDienTu] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LKDienTu] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LKDienTu] SET ARITHABORT OFF 
GO
ALTER DATABASE [LKDienTu] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [LKDienTu] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LKDienTu] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LKDienTu] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LKDienTu] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LKDienTu] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LKDienTu] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LKDienTu] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LKDienTu] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LKDienTu] SET  ENABLE_BROKER 
GO
ALTER DATABASE [LKDienTu] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LKDienTu] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LKDienTu] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LKDienTu] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LKDienTu] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LKDienTu] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LKDienTu] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LKDienTu] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LKDienTu] SET  MULTI_USER 
GO
ALTER DATABASE [LKDienTu] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LKDienTu] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LKDienTu] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LKDienTu] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [LKDienTu] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LKDienTu] SET QUERY_STORE = OFF
GO
USE [LKDienTu]
GO
/****** Tạo Function Doanh thu theo ngày ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[Fun_DoanhThu_TheoNgay](
@Ngay1 date,@Ngay2 date)
returns float
begin
	declare @DoanhThu float
	select @DoanhThu=sum(TongTien) 
	from HoaDon
	where NgayBan between @Ngay1 and @Ngay2
	return @DoanhThu
end
GO
/****** Tạo Function tổng số lượng tồn ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[Fun_Hang_TongSoLuongTon]()
returns int
as
begin
	declare @tong int
	select @tong = (select SUM(SoLuongTon) from Hang)
	return @tong
end
GO
/****** Tạo Funtion Tổng số mặt hàng còn lại ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[Fun_Hang_TongSoMatHangConLai]()
returns int
 as
 begin
	declare @tong int
	select @tong = (select COUNT(MaHang) from Hang where SoLuongTon >=1)
	return @tong
end
GO
/****** Tạo Function hóa đơn theo ngày   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[Fun_HoaDon_TheoNgay](@Ngay1 date, @Ngay2 date)
returns int 
as
begin
	declare @SoLuong int
	select @SoLuong = (select count(*) from HoaDon where NgayBan between @Ngay1 and @Ngay2) 
	return @SoLuong
end
GO
/****** Tạo Function Lợi Nhuận theo ngày    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[Fun_LoiNhuan_TheoNgay](@Ngay1 date, @Ngay2 date)
returns float
as
begin
	declare @Lai float,@DoanhThu float
	select @DoanhThu = dbo.Fun_DoanhThu_TheoNgay (@Ngay1, @Ngay2)
	select @Lai=@DoanhThu-SUM(DonGiaNhap*SoLuong) from ChiTietHD inner join Hang
	on ChiTietHD.MaHang=Hang.MaHang inner join HoaDon
	on ChiTietHD.MaHD=HoaDon.MaHD
	where NgayBan between @Ngay1 and @Ngay2
	return @Lai
end
GO
/****** Tạo Table Hang     ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hang](
	[MaHang] [varchar](50) NOT NULL,
	[TenHang] [nvarchar](50) NULL,
	[MaHangSanXuat] [varchar](10) NULL,
	[SoLuongTon] [int] NULL,
	[DonGiaNhap] [int] NULL,
	[DonGiaBan] [int] NULL,
	[NgayNhapHang] [date] NULL,
	[AnhHang][nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo table Hãng sãn xuất  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HangSanXuat](
	[MaHangSanXuat] [varchar](10) NOT NULL,
	[TenHangSanXuat] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHangSanXuat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo View_Hang_HangSX    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[vw_Hang_HangSX]
as
select MaHang,TenHang,TenHangSanXuat,SoLuongTon,DonGiaNhap,DonGiaBan,NgayNhapHang 
from Hang inner join HangSanXuat
on Hang.MaHangSanXuat=HangSanXuat.MaHangSanXuat
GO
/******Tạo table Hóa đơn    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [varchar](50) NOT NULL,
	[MaNV] [varchar](50) NULL,
	[NgayBan] [date] NULL,
	[MaKhach] [varchar](50) NULL,
	[TongTien] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo Table Nhân Viên  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](50) NOT NULL,
	[TenNV] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](5) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](50) NULL,
	[SDT] [nvarchar](10) NULL,
	[ChucVu] [nvarchar](30) NULL,
	[AnhNV][nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo View_HoaDon_NhanVien   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[vw_HoaDon_NhanVien]
as
select MaHD,NhanVien.MaNV,TenNV,MaKhach,NgayBan,TongTien
from HoaDon inner join NhanVien
on HoaDon.MaNV=NhanVien.MaNV
GO
/****** Tạo View MatHang   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[vw_MatHang]
as
select MaHang,TenHang,SoLuongTon
from Hang
GO
/****** Tạo View Khách Hàng******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khach](
	[MaKhach] [varchar](50) NOT NULL,
	[TenKhach] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](5) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[SDT] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo View_ThongTinGiaoDichHang_KhachHang  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[vw_ThongTinGiaoDichHang_KhachHang]
as
	select Khach.MaKhach, TenKhach, NhanVien.MaNV, TenNV, NgayBan
	from NhanVien
	inner join HoaDon on HoaDon.MaNV = NhanVien.MaNV
	inner join Khach on Khach.MaKhach = HoaDon.MaKhach
GO
/****** Tạo View_TongSoHoaDon_NhanVien    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[vw_TongSoHoaDon_NhanVien]
as
	select NhanVien.MaNV, TenNV, count (MaHD) as N'Tổng số hóa đơn'
	from NhanVien inner join HoaDon on HoaDon.MaNV = NhanVien.MaNV
	group by NhanVien.MaNV, TenNV
GO
/****** Tạo Table admin ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admin](
	[ID] [varchar](10) NOT NULL,
	[Name] [nvarchar](30) NULL,
	[Password] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Tạo Table ChiTietHD     ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHD](
	[MaHD] [varchar](50) NOT NULL,
	[MaHang] [varchar](50) NOT NULL,
	[SoLuong] [int] NULL,
	[GiamGia] [float] NULL,
	[ThanhTien] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC,
	[MaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[admin] ([ID], [Name], [Password]) VALUES (N'1', N'admin', N'admin')
GO
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|1', N'MH1', 2, 3, 250260)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|1', N'MH13', 5, 3, 51846499)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|10', N'MH11', 2, 4, 5758080)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|10', N'MH15', 2, 4, 5884800)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|11', N'MH11', 2, 4, 5758080)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|11', N'MH12', 2, 4, 247680)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|12', N'MH14', 2, 4, 1459200)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|12', N'MH2', 2, 3, 38780600)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|12', N'MH5', 2, 4, 32640000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|13', N'MH1', 2, 4, 247680)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|13', N'MH11', 2, 4, 5758080)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|13', N'MH16', 2, 4, 900480)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|14', N'MH2', 2, 3, 38780600)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|15', N'MH1', 2, 4, 247680)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|2', N'MH12', 4, 2, 505680)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|2', N'MH3', 2, 4, 26880000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|3', N'MH4', 2, 4, 30720000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|3', N'MH5', 2, 4, 32640000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|4', N'MH6', 2, 3, 42680000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|4', N'MH9', 2, 3, 23280000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|5', N'MH1', 2, 3, 250260)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|5', N'MH11', 2, 3, 5818060)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|5', N'MH13', 5, 8, 49174000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|5', N'MH4', 2, 4, 30720000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|5', N'MH5', 2, 4, 32640000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|6', N'MH5', 1, 2, 16660000)
INSERT [dbo].[ChiTietHD] ([MaHD], [MaHang], [SoLuong], [GiamGia], [ThanhTien]) VALUES (N'HDB20201106|6', N'MH9', 1, 2, 11760000)
GO
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH1', N'Chuột Logitech B100 Black', N'HSX9', 36, 99000, 129000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH10', N'Màn hình Asus VG279Q', N'HSX3', 50, 6989000, 8999000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH11', N'Màn hình HP19KA', N'HSX5', 20, 1649000, 2999000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH12', N'Chuột Logitech B100 Black', N'HSX9', 94, 99000, 129000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH13', N'Laptop HP 15s ', N'HSX5', 0, 7989000, 10690000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH14', N'RAM Laptop Kingston 8G', N'HSX10', 48, 560000, 760000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH15', N'Ổ cứng gắn trong WD Black SN750 SSD 500GB', N'HSX11', 46, 2900000, 3065000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH16', N'USB 3.0 64GB Sandisk', N'HSX12', 48, 400000, 469000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH17', N'Chuột bluetooth Logitech M557', N'HSX9', 28, 500000, 620000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH18', N'Chuột game Logitech', N'HSX9', 28, 400000, 590000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH19', N'Ổ cứng ngoài HĐ Seagate 1TB ', N'HSX17', 30, 1500000, 1650000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH2', N'Laptop MacBook Air 13', N'HSX4', 14, 17000000, 19990000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH20', N'USB 3.0 OTG Type-C 32D Sandisk', N'HSX12', 28, 200000, 379000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH3', N'Laptop Dell Vostro ', N'HSX2', 28, 10000000, 14000000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH4', N'Laptop Lenovo IdeaPad', N'HSX6', 36, 15000000, 16000000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH5', N'Laptop HP 15s', N'HSX5', 31, 16000000, 17000000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH6', N'Laptop Asus VivoBook', N'HSX3', 38, 20000000, 22000000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH7', N'Laptop Acer Aspire', N'HSX1', 10, 23000000, 25000000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH8', N'Màn hình Asus VG279Q', N'HSX3', 10, 6989000, 8999000, CAST(N'2022-11-16' AS Date),N'Không có')
INSERT [dbo].[Hang] ([MaHang], [TenHang], [MaHangSanXuat], [SoLuongTon], [DonGiaNhap], [DonGiaBan], [NgayNhapHang],[AnhHang]) VALUES (N'MH9', N'Laptop Asus D409DA-EK151T', N'HSX3', 5, 10000000, 12000000, CAST(N'2022-11-16' AS Date),N'Không có')
GO
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX1', N'Acer')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX10', N'Kingston')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX11', N'Western Digital')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX12', N'SanDisk')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX13', N'Samsung')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX14', N'MSI')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX15', N'Huawei')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX16', N'Masstel')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX17', N'Seagate')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX2', N'Dell')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX3', N'Asus')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX4', N'Macbook')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX5', N'HP')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX6', N'Lenovo')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX7', N'Thinkpad')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX8', N'Sony')
INSERT [dbo].[HangSanXuat] ([MaHangSanXuat], [TenHangSanXuat]) VALUES (N'HSX9', N'Logitech')
GO
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|1', N'TCLKT1', CAST(N'2022-11-16' AS Date), N'KH10', 52096759)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|10', N'TCLKT6', CAST(N'2022-11-16' AS Date), N'KH2', 11642880)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|11', N'TCLKT6', CAST(N'2022-11-16' AS Date), N'KH4', 6005760)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|12', N'TCLKT7', CAST(N'2022-11-16' AS Date), N'KH5', 72879800)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|13', N'TCLKT7', CAST(N'2022-11-16' AS Date), N'KH6', 6906240)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|14', N'TCLKT7', CAST(N'2022-11-16' AS Date), N'KH7', 38780600)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|15', N'TCLKT7', CAST(N'2022-11-16' AS Date), N'KH8', 247680)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|2', N'TCLKT1', CAST(N'2022-11-16' AS Date), N'KH3', 27385680)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|3', N'TCLKT4', CAST(N'2022-11-16' AS Date), N'KH11', 63360000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|4', N'TCLKT4', CAST(N'2022-11-16' AS Date), N'KH14', 65960000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|5', N'TCLKT4', CAST(N'2022-11-16' AS Date), N'KH10', 118602320)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|6', N'TCLKT5', CAST(N'2022-11-16' AS Date), N'KH1', 28420000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|7', N'TCLKT5', CAST(N'2022-11-16' AS Date), N'KH12', 72500400)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|8', N'TCLKT5', CAST(N'2022-11-16' AS Date), N'KH13', 23767680)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201106|9', N'TCLKT6', CAST(N'2022-11-16' AS Date), N'KH14', 11887980)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201108|16', N'TCLKT1', CAST(N'2022-11-16' AS Date), N'KH22', 2299000)
INSERT [dbo].[HoaDon] ([MaHD], [MaNV], [NgayBan], [MaKhach], [TongTien]) VALUES (N'HDB20201108|17', N'TCLKT1', CAST(N'2022-11-16' AS Date), N'KH23', 965200)
GO
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH1', N'Trần Nam Hải', N'Nam', N'Thái Bình', N'0977019560')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH10', N'Trần Huy Văn', N'Nam', N'Hà Nội', N'0568875788')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH11', N'Phạm Yến Anh', N'Nữ', N'Ninh Bình', N'0988976890')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH12', N'Trần Hoàng Anh', N'Nam', N'Thái Bình', N'0986926299')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH13', N'Nguyễn Lan Anh', N'Nữ', N'Thanh Hóa', N'0388151679')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH14', N'Hoàng Thanh Linh', N'Nữ', N'Hải Dương', N'0388151789')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH15', N'Phạm Tùng Dương', N'Nam', N'Quảng Ninh', N'0765456809')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH16', N'Nguyễn Quốc Hà', N'Nam', N'Hồ Chí Minh', N'0987546124')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH17', N'Hoàng Như Yến', N'Nữ', N'Thanh Hóa', N'0765895968')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH18', N'Hoàng Ngọc Đông', N'Nam', N'Hà Nam', N'0678450479')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH19', N'Lâm Hoàng Thái', N'Nam', N'Nam Định', N'0956789346')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH2', N'Đinh Trường Sơn', N'Nam', N'Nam Định', N'0977019788')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH20', N'Đỗ Hoàng Dương', N'Nam', N'Hà Nội', N'0369240442')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH21', N'Nguyễn Mỹ Thành', N'Nữ', N'Hà Nội', N'0875682243')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH22', N'Lê Cẩm Nhung', N'Nữ', N'Cần Thơ', N'0973580025')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH23', N'Lê Thảo Uyên', N'Nữ', N'Nha Trang', N'0943656547')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH3', N'Phạm Thị Mỹ Linh', N'Nữ', N'Ninh Bình', N'0977019708')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH4', N'Phạm Sơn Tùng', N'Nam', N'Thái Nguyên', N'0768905689')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH5', N'Phạm Phương Nga', N'Nữ', N'Hà Nội', N'0976556798')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH6', N'Nguyễn Linh Chi', N'Nữ', N'Hà Nội', N'0765999878')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH7', N'Nguyễn Hoài Thanh', N'Nữ', N'Thái Bình', N'0789056789')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH8', N'Lê Việt Anh', N'Nam', N'Hà Nội', N'0868688776')
INSERT [dbo].[Khach] ([MaKhach], [TenKhach], [GioiTinh], [DiaChi], [SDT]) VALUES (N'KH9', N'Hoàng Huy Minh', N'Nam', N'Thái Bình', N'0536788999')
GO
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT1', N'Nguyễn Quốc Hiệu', N'Nam', CAST(N'2000-11-17' AS Date), N'Hà Nội', N'0339657164', N'Nhân viên',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT2', N'Hoàng Thị Mai', N'Nữ', CAST(N'2000-09-20' AS Date), N'Thái Bình', N'0977019702', N'Quản lý',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT3', N'Nguyễn Thị Hồng Hải', N'Nữ', CAST(N'2000-11-12' AS Date), N'Hà Nội', N'0976555687', N'Quản lý',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT4', N'Trần Hải Anh', N'Nữ', CAST(N'2000-12-09' AS Date), N'Hà Nam', N'0976555676', N'Nhân viên',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT5', N'Phạm Thế Bình', N'Nam', CAST(N'2000-10-06' AS Date), N'Nam Định', N'0976554975', N'Nhân viên',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT6', N'Nguyễn Nam Sơn', N'Nam', CAST(N'2000-11-04' AS Date), N'Quảng Ninh', N'0339657178', N'Nhân viên',N'Không có')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [ChucVu],[AnhNV]) VALUES (N'TCLKT7', N'Lê Hải Hoàng', N'Nam', CAST(N'1999-11-07' AS Date), N'Hải Phòng', N'0339657145', N'Nhân viên',N'Không có')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Unique_SDT_KH]     ******/
ALTER TABLE [dbo].[Khach] ADD  CONSTRAINT [Unique_SDT_KH] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF,IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Unique_SDT_NhanVien]    ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [Unique_SDT_NhanVien] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHD]  WITH CHECK ADD  CONSTRAINT [FK_Hang_ChiTietHD] FOREIGN KEY([MaHang])
REFERENCES [dbo].[Hang] ([MaHang])
GO
ALTER TABLE [dbo].[ChiTietHD] CHECK CONSTRAINT [FK_Hang_ChiTietHD]
GO
ALTER TABLE [dbo].[ChiTietHD]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_ChiTietHD] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[ChiTietHD] CHECK CONSTRAINT [FK_HoaDon_ChiTietHD]
GO
ALTER TABLE [dbo].[Hang]  WITH CHECK ADD  CONSTRAINT [FK_Hang_HangSX] FOREIGN KEY([MaHangSanXuat])
REFERENCES [dbo].[HangSanXuat] ([MaHangSanXuat])
GO
ALTER TABLE [dbo].[Hang] CHECK CONSTRAINT [FK_Hang_HangSX]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaKhach])
REFERENCES [dbo].[Khach] ([MaKhach])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[ChiTietHD]  WITH CHECK ADD  CONSTRAINT [check_SoLuong] CHECK  (([SoLuong]>=(0)))
GO
ALTER TABLE [dbo].[ChiTietHD] CHECK CONSTRAINT [check_SoLuong]
GO
ALTER TABLE [dbo].[Hang]  WITH CHECK ADD  CONSTRAINT [check_soLuongTon] CHECK  (([SoLuongTon]>=(0)))
GO
ALTER TABLE [dbo].[Hang] CHECK CONSTRAINT [check_soLuongTon]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [Check_GioiTinh] CHECK  (([GioiTinh]='Nam' OR [GioiTinh]=N'Nữ'))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [Check_GioiTinh]
GO
/****** Object:  StoredProcedure [dbo].[delete_HD]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[delete_HD]
@MaHD varchar(50)
as
begin 
	declare con_tro_CTHD cursor
	dynamic scroll
	for
		select MaHang from ChiTietHD
	open con_tro_CTHD
	declare @MaHang varchar(50)
	fetch next from con_tro_CTHD into @MaHang
	while(@@FETCH_STATUS=0)
		begin
			delete from ChiTietHD
			where MaHD=@MaHD and MaHang=@MaHang
			fetch next from con_tro_CTHD into @MaHang
		end
	close con_tro_CTHD
	deallocate con_tro_CTHD 
	delete from HoaDon
	where MaHD=@MaHD
end
GO
/****** Object:  StoredProcedure [dbo].[sp_SoLuongTon]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_SoLuongTon] 
@MaHang varchar(10)
as
begin
 select SoLuongTon as SoLuongTon from Hang
 where MaHang=@MaHang
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_Hang_NgayNhap]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_TimKiem_Hang_NgayNhap]
@Ngay date
as
begin
	select * from vw_Hang_HangSX where NgayNhapHang = @Ngay 
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_Hang_TenHangSX]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_TimKiem_Hang_TenHangSX]
@TenHangSX nvarchar(30)
as
begin
    select * from vw_Hang_HangSX
	where TenHangSanXuat=@TenHangSX
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_HoaDon_TenNV]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[sp_TimKiem_HoaDon_TenNV]
@TenNV nvarchar (50)
as
begin
    select * from vw_HoaDon_NhanVien
	where TenNV like N'%'+@TenNV
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_HoaDon_TheoNgay]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_TimKiem_HoaDon_TheoNgay]
@Ngay1 date, @Ngay2 date
as
begin
	select * from vw_HoaDon_NhanVien
	where NgayBan  between @Ngay1 and @Ngay2
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_KhachHang_SDT]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_TimKiem_KhachHang_SDT]
@SDT varchar(10)
as
begin
	select *
	from Khach
	where SDT=@SDT
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_KhachHang_TenKH]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[sp_TimKiem_KhachHang_TenKH]
@TenKH nvarchar (50)
as
begin
    select * from Khach
	where TenKhach like N'%'+ @TenKH+''
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_NhanVien_GioiTinh]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_TimKiem_NhanVien_GioiTinh]
@GT nvarchar(3)
as
begin
	select *
	from NhanVien
	where GioiTinh=@GT
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_NhanVien_HT]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[sp_TimKiem_NhanVien_HT]
@Hoten nvarchar(50)
as
begin
	select *
	from NhanVien
	where TenNV like N'%'+@Hoten
end
GO
/****** Object:  StoredProcedure [dbo].[sp_TimKiem_NhanVien_QQ]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[sp_TimKiem_NhanVien_QQ]
@QQ nvarchar(10)
as
begin
	select *
	from NhanVien
	where DiaChi=@QQ
end
GO
/****** Object:  Trigger [dbo].[Tr_CapNhatDatHang_ChitietHoaDon]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_CapNhatDatHang_ChitietHoaDon]
on [dbo].[ChiTietHD]
for update
as
begin
	update Hang
	set SoLuongTon=SoLuongTon-(select SoLuong from inserted where Hang.MaHang=inserted.MaHang)
	+ (select SoLuong from deleted where Hang.MaHang=deleted.MaHang)
	from (select MaHang from deleted) as D
	where Hang.MaHang=D.MaHang
end
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_CapNhatDatHang_ChitietHoaDon]
GO
/****** Object:  Trigger [dbo].[Tr_SoLuongTon_Hang]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_SoLuongTon_Hang]
on [dbo].[ChiTietHD]
for insert
as 
begin
	update Hang
	set SoLuongTon = SoLuongTon - (
		select SoLuong
		from inserted
		where inserted.MaHang = Hang.MaHang
	)
	from ChiTietHD, inserted 
	where Hang.MaHang = inserted.MaHang	
end
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_SoLuongTon_Hang]
GO
/****** Object:  Trigger [dbo].[Tr_SoLuongTon_XoaChiTietHoaDon]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_SoLuongTon_XoaChiTietHoaDon]
on [dbo].[ChiTietHD]
for delete
as
begin
	update Hang
	set SoLuongTon = SoLuongTon + (select SoLuong from deleted where deleted.MaHang = Hang.MaHang)
	from  deleted
	where Hang.MaHang = deleted.MaHang
end	
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_SoLuongTon_XoaChiTietHoaDon]
GO
/****** Object:  Trigger [dbo].[Tr_ThanhTien_ChiTietHoaDon]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_ThanhTien_ChiTietHoaDon]
on [dbo].[ChiTietHD]
for insert, update
as
if((select MaHang from inserted) is not null)
begin
	update ChiTietHD
	set ThanhTien=ChiTietHD.SoLuong*(1-(GiamGia/100))*DonGiaBan from Hang,(select MaHang,MaHD from inserted) as I
	where Hang.MaHang=ChiTietHD.MaHang and ChiTietHD.MaHang=I.MaHang and ChiTietHD.MaHD=I.MaHD
end
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_ThanhTien_ChiTietHoaDon]
GO
/****** Object:  Trigger [dbo].[Tr_TongTienHoaDon]     ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_TongTienHoaDon]
on [dbo].[ChiTietHD]
for insert, update
as
if((select MaHD from inserted)is not null)
begin
	update HoaDon 
	set TongTien=(select SUM(ThanhTien) from ChiTietHD
	where ChiTietHD.MaHD=HoaDon.MaHD)
	from (select MaHD from inserted) as I
	where HoaDon.MaHD=I.MaHD
end
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_TongTienHoaDon]
GO
/****** Object:  Trigger [dbo].[Tr_TongTienHoaDon_XoaChiTietHoaDon]   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[Tr_TongTienHoaDon_XoaChiTietHoaDon]
on [dbo].[ChiTietHD]
for delete
as
if((select MaHD from deleted)is not null)
begin
	update HoaDon 
	set TongTien=(select SUM(ThanhTien) from ChiTietHD
	where ChiTietHD.MaHD=HoaDon.MaHD)
	from (select MaHD from deleted) as I
	where HoaDon.MaHD=I.MaHD
end
GO
ALTER TABLE [dbo].[ChiTietHD] ENABLE TRIGGER [Tr_TongTienHoaDon_XoaChiTietHoaDon]
GO
USE [master]
GO
ALTER DATABASE [LKDienTu] SET  READ_WRITE 
GO
use LKDienTu;
select * from NhanVien
select * from hang