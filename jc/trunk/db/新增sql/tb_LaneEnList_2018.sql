USE [jc]
GO

/****** Object:  Table [dbo].[tb_LaneEnList_2018]    Script Date: 09/20/2016 09:20:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[tb_LaneEnList_2018](
	[EnRecordNo] [smallint] NOT NULL,
	[LaneEnSerialNo] [char](16) NOT NULL,
	[CardNetRoadID] [smallint] NOT NULL,
	[ICInCardID] [bigint] NOT NULL,
	[EnNetRoadID] [smallint] NOT NULL,
	[EnRoadID] [smallint] NOT NULL,
	[EnStationID] [int] NOT NULL,
	[EnLaneID] [smallint] NOT NULL,
	[EnLaneType] [tinyint] NOT NULL,
	[EnTime] [datetime] NOT NULL,
	[EnOperatorID] [bigint] NOT NULL,
	[EnOpCardNo] [char](10) NULL,
	[EnOpCardID] [bigint] NULL,
	[EnShiftID] [tinyint] NOT NULL,
	[EnCPCSnNo] [char](10) NULL,
	[EnCPCInID] [bigint] NULL,
	[EnVehicleClass] [tinyint] NOT NULL,
	[EnVehicleStatus] [tinyint] NOT NULL,
	[EnVehiclePlate] [char](12) NOT NULL,
	[EnVehIdentifyPlate] [char](12) NOT NULL,
	[EnVehicleFlag] [tinyint] NOT NULL,
	[EnICCIssuer] [smallint] NULL,
	[EnCPUCardSnNo] [char](16) NULL,
	[EnCPUCardID] [bigint] NULL,
	[EnCPUCardType] [tinyint] NOT NULL,
	[EnETCTermCode] [varchar](16) NOT NULL,
	[EnOBUNum] [char](16) NOT NULL,
	[EnETCTradNo] [char](8) NOT NULL,
	[EnETCTermTradNo] [int] NOT NULL,
	[EnETCTac] [char](8) NOT NULL,
	[SquadDate] [datetime] NOT NULL,
	[ImageSerialNo] [int] NOT NULL,
	[CardBoxNo] [int] NOT NULL,
	[CardTrunkNo] [int] NOT NULL,
	[AppVer] [varchar](20) NOT NULL,
	[DealStatus] [bigint] NOT NULL,
	[DeviceStatus] [int] NOT NULL,
	[RecordType] [smallint] NOT NULL,
	[VehCount] [smallint] NOT NULL,
	[TicketType] [tinyint] NOT NULL,
	[ListName] [smallint] NOT NULL,
	[BillNo] [int] NULL,
	[VoidSerialNo] [varchar](16) NULL,
	[VerifyCode] [int] NOT NULL,
	[CPCCurrentVol] [smallint] NOT NULL,
	[Spare1] [int] NULL,
	[Spare2] [int] NULL,
	[Spare3] [varchar](50) NULL,
	[Spare4] [varchar](50) NULL,
	[UploadTime] [datetime] NOT NULL,
 CONSTRAINT [PK_tb_LaneEnList_2018] PRIMARY KEY CLUSTERED 
(
	[LaneEnSerialNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

