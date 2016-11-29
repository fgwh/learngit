USE [jc]
GO

/****** Object:  Table [dbo].[tb_LaneExList_2016]    Script Date: 09/20/2016 09:22:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[tb_LaneExList_2016](
	[ExRecordNo] [smallint] NOT NULL,
	[LaneExSerialNo] [char](16) NOT NULL,
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
	[EnShiftID] [tinyint] NOT NULL,
	[EnVehicleClass] [tinyint] NOT NULL,
	[EnVehicleStatus] [tinyint] NOT NULL,
	[EnVehiclePlate] [char](12) NOT NULL,
	[EnVehicleFlag] [tinyint] NOT NULL,
	[EnETCTermCode] [varchar](16) NOT NULL,
	[ExNetRoadID] [smallint] NOT NULL,
	[ExRoadID] [smallint] NOT NULL,
	[ExStationID] [int] NOT NULL,
	[ExLaneID] [smallint] NOT NULL,
	[ExLaneType] [tinyint] NOT NULL,
	[ExTime] [datetime] NOT NULL,
	[ExOperatorID] [bigint] NOT NULL,
	[ExOpCardNo] [char](10) NULL,
	[ExOpCardID] [bigint] NULL,
	[SquadDate] [datetime] NOT NULL,
	[ExShiftID] [tinyint] NOT NULL,
	[ExCPCSnNo] [char](10) NULL,
	[ExCPCInID] [bigint] NULL,
	[ExVehicleClass] [tinyint] NOT NULL,
	[ExVehicleStatus] [tinyint] NOT NULL,
	[ImageSerialNo] [int] NOT NULL,
	[CardBoxNo] [int] NOT NULL,
	[CardTrunkNo] [int] NOT NULL,
	[AppVer] [varchar](20) NOT NULL,
	[ExVehiclePlate] [char](12) NOT NULL,
	[ExVehIdentifyPlate] [char](12) NOT NULL,
	[TollType] [tinyint] NOT NULL,
	[CashMoney] [int] NOT NULL,
	[InvoiceID] [varchar](30) NOT NULL,
	[ETCMoney] [int] NOT NULL,
	[FreeMoney] [int] NOT NULL,
	[OfficeMoney] [int] NOT NULL,
	[UnpayMoney] [int] NOT NULL,
	[VehicleClassMoney] [int] NOT NULL,
	[DownVehicleClassMoney] [int] NOT NULL,
	[OfficeCardSnNo] [char](10) NOT NULL,
	[OwnerCode1] [bigint] NOT NULL,
	[OwnerCode2] [bigint] NOT NULL,
	[OwnerCode3] [bigint] NOT NULL,
	[OwnerCode4] [bigint] NOT NULL,
	[DealStatus] [bigint] NOT NULL,
	[DeviceStatus] [int] NOT NULL,
	[RecordType] [smallint] NOT NULL,
	[ExVehicleFlag] [tinyint] NOT NULL,
	[ExICCIssuer] [smallint] NULL,
	[ExCPUCardSnNo] [char](16) NULL,
	[ExCPUCardID] [bigint] NULL,
	[ExCPUCardType] [tinyint] NOT NULL,
	[ExCpuCardPlate] [varchar](12) NULL,
	[ExCpuCardPlateColor] [varchar](2) NULL,
	[ExCpuCardUserType] [tinyint] NULL,
	[WeightFlag] [tinyint] NOT NULL,
	[TicketType] [tinyint] NOT NULL,
	[PayCardType] [tinyint] NOT NULL,
	[VehCount] [smallint] NOT NULL,
	[AxisNum] [tinyint] NOT NULL,
	[AxisGroupNum] [tinyint] NOT NULL,
	[TotalWeight] [bigint] NOT NULL,
	[TotalWeightLimit] [bigint] NOT NULL,
	[AxisType] [varchar](50) NOT NULL,
	[AxisWeightDetail] [varchar](50) NOT NULL,
	[OverLoadWeight] [bigint] NOT NULL,
	[PreviousAxisType] [varchar](50) NOT NULL,
	[PreviousTotalWeight] [bigint] NOT NULL,
	[TruckLimitVerNo] [tinyint] NOT NULL,
	[TruckLimitPriceVerNo] [tinyint] NOT NULL,
	[PreVehType] [tinyint] NOT NULL,
	[PreVehMoney] [int] NOT NULL,
	[PayCardBalanceBefore] [bigint] NOT NULL,
	[PayCardBalanceAfter] [bigint] NOT NULL,
	[OBUNum] [varchar](16) NULL,
	[OBUID] [varchar](16) NULL,
	[OBEState] [varchar](4) NULL,
	[OBUPlate] [varchar](12) NULL,
	[OBUPlateColor] [varchar](2) NULL,
	[ExETCTradeNo] [int] NOT NULL,
	[ExETCTac] [varchar](8) NULL,
	[ExETCTermTradNo] [int] NOT NULL,
	[ExETCTermCode] [varchar](16) NOT NULL,
	[RebateType] [smallint] NOT NULL,
	[RebateVerNo] [smallint] NOT NULL,
	[RebateRate] [smallint] NOT NULL,
	[PreRebateFee] [int] NOT NULL,
	[RebateFee] [int] NOT NULL,
	[FlagType] [tinyint] NOT NULL,
	[OriginalPath] [varchar](256) NOT NULL,
	[RealPath] [varchar](256) NOT NULL,
	[RoadComb] [varchar](128) NOT NULL,
	[MoneyComb] [varchar](512) NOT NULL,
	[RoadSStationComb] [varchar](256) NOT NULL,
	[RoadEStationComb] [varchar](256) NOT NULL,
	[RealPath01] [varchar](256) NOT NULL,
	[TollRateVer] [int] NOT NULL,
	[ListName] [smallint] NOT NULL,
	[VoidSerialNo] [varchar](16) NULL,
	[ManualFlag] [tinyint] NOT NULL,
	[VerifyCode] [int] NOT NULL,
	[CPCCurrentVol] [smallint] NOT NULL,
	[Miles] [int] NOT NULL,
	[TranslationMiles] [int] NOT NULL,
	[TollCardFreeListVerNo] [smallint] NOT NULL,
	[Spare1] [int] NULL,
	[Spare2] [int] NULL,
	[Spare3] [varchar](50) NULL,
	[Spare4] [varchar](50) NULL,
	[UploadTime] [datetime] NOT NULL,
 CONSTRAINT [PK_tb_LaneExList_2016] PRIMARY KEY CLUSTERED 
(
	[LaneExSerialNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

