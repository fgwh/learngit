package com.hgsoft.util;


import com.hgsoft.main.laneenlist.entity.LaneEnList;
import com.hgsoft.main.laneexlist.entity.LaneExList;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 出口流水和入口流水工具类
 * @author 郭志强
 * @time 2016/9/12 16:06
 */
public class LaneEnAndExUtils {

    public static void encapsulationLanExParams(LaneExList laneExList, List<Object> params) {
    	
        params.add(laneExList.getExRecordNo());
        params.add(laneExList.getLaneExSerialNo());
        params.add(laneExList.getLaneEnSerialNo());
        params.add(laneExList.getCardNetRoadID());
        params.add(laneExList.getIcinCardId());
        
        params.add(laneExList.getEnNetRoadId());
        params.add(laneExList.getEnRoadId());
        
        params.add(laneExList.getEnStationId());
        params.add(laneExList.getEnLaneId());
        params.add(laneExList.getEnLaneType());
        params.add(laneExList.getEnTime());
        params.add(laneExList.getEnOperatorID());
        params.add(laneExList.getEnShiftID());
        
        
        params.add(laneExList.getEnVehicleClass());
        params.add(laneExList.getEnVehicleStatus());
        params.add(laneExList.getEnVehiclePlate());
        
        params.add(laneExList.getEnVehicleFlag());
        params.add(laneExList.getEnETCTermCode());
        
        params.add(laneExList.getExNetRoadId());
        params.add(laneExList.getExRoadId());
        params.add(laneExList.getExStationId());
        params.add(laneExList.getExLaneId());
        params.add(laneExList.getExLaneType());
        params.add(laneExList.getExTime());
        
        params.add(laneExList.getExOperatorID());
        params.add(laneExList.getExOpCardNo());
        params.add(laneExList.getExOpCardID());
        
        params.add(laneExList.getSquadDate());
        params.add(laneExList.getExShiftID());
        
        params.add(laneExList.getExCpcsnNo());
        
        params.add(laneExList.getExCPCInID());
       	        		
        params.add(laneExList.getExVehicleClass());
        params.add(laneExList.getExVehicleStatus());
        params.add(laneExList.getImageSerialNo());
        params.add(laneExList.getCardBoxNo());
        params.add(laneExList.getCardTrunkNo());
        params.add(laneExList.getAppVer());
        
        params.add(laneExList.getExVehiclePlate());
        params.add(laneExList.getExVehIdentifyPlate());
        params.add(laneExList.getTollType());
        params.add(laneExList.getCashMoney());
        
        params.add(laneExList.getInvoiceID());
        params.add(laneExList.getETCMoney());
        params.add(laneExList.getFreeMoney());
        params.add(laneExList.getOfficeMoney());
        params.add(laneExList.getUnpayMoney());
        
        params.add(laneExList.getVehicleClassMoney());
        
        params.add(laneExList.getDownVehicleClassMoney());
        params.add(laneExList.getOfficeCardSnNo());
        params.add(laneExList.getOwnerCode1());
        params.add(laneExList.getOwnerCode2());
        params.add(laneExList.getOwnerCode3());
        params.add(laneExList.getOwnerCode4());
        params.add(laneExList.getDealStatus());
        		
        params.add(laneExList.getDeviceStatus());
        params.add(laneExList.getRecordType());
        
        params.add(laneExList.getExVehicleFlag());
        params.add(laneExList.getExICCIssuer());
        
        params.add(laneExList.getExCpucardSnNo());
        params.add(laneExList.getExCpucardId());
        params.add(laneExList.getExCpucardType());
        
        params.add(laneExList.getExCpuCardPlate());
        params.add(laneExList.getExCpuCardPlateColor());
        params.add(laneExList.getExCpuCardUserType());
        
        params.add(laneExList.getWeightFlag());
        params.add(laneExList.getTicketType());
        params.add(laneExList.getPayCardType());
        
        params.add(laneExList.getVehCount());
        params.add(laneExList.getAxisNum());
        params.add(laneExList.getAxisGroupNum());
        params.add(laneExList.getTotalWeight());
        params.add(laneExList.getTotalWeightLimit());
        
        params.add(laneExList.getAxisType());
        params.add(laneExList.getAxisWeightDetail());
        params.add(laneExList.getOverLoadWeight());
        
        params.add(laneExList.getPreviousAxisType());
        params.add(laneExList.getPreviousTotalWeight());
        params.add(laneExList.getTruckLimitVerNo());
        params.add(laneExList.getTruckLimitPriceVerNo());
        
        params.add(laneExList.getPreVehType());
        
        params.add(laneExList.getPreVehMoney());
        params.add(laneExList.getPayCardBalanceBefore());
        params.add(laneExList.getPayCardBalanceAfter());
        params.add(laneExList.getOBUNum());
        params.add(laneExList.getOBUID());
        params.add(laneExList.getOBEState());
        params.add(laneExList.getOBUPlate());
        params.add(laneExList.getOBUPlateColor());
        params.add(laneExList.getExETCTradeNo());
        params.add(laneExList.getExETCTac());
        params.add(laneExList.getExETCTermTradNo());
        		
        params.add(laneExList.getExEtctermCode());
        
        params.add(laneExList.getRebateType());
        params.add(laneExList.getRebateVerNo());
        params.add(laneExList.getRebateRate());
        params.add(laneExList.getPreRebateFee());
        params.add(laneExList.getRebateFee());
        params.add(laneExList.getFlagType());
        
        params.add(laneExList.getOriginalPath());
        params.add(laneExList.getRealPath());
        params.add(laneExList.getRoadComb());
        
        params.add(laneExList.getMoneyComb());
        
        params.add(laneExList.getRoadSstationComb());
        params.add(laneExList.getRoadEstationComb());
        params.add(laneExList.getRealPath01());
        
        params.add(laneExList.getTollRateVer());
        params.add(laneExList.getListName());
        params.add(laneExList.getVoidSerialNo());
        
        params.add(laneExList.getManualFlag());
        params.add(laneExList.getVerifyCode());
        
        params.add(laneExList.getCPCCurrentVol());
        params.add(laneExList.getMiles());
        params.add(laneExList.getTranslationMiles());
        params.add(laneExList.getTollCardFreeListVerNo());
        
        params.add(laneExList.getSpare1());
        params.add(laneExList.getSpare2());
        params.add(laneExList.getSpare3());
        params.add(laneExList.getSpare4());
        params.add(laneExList.getUploadTime());
        /*params.add(laneExList.getDealStatus());
        params.add(laneExList.getEnVehicleFlag());
        params.add(laneExList.getEnOperatorID());
        params.add(laneExList.getExOperatorID());
        params.add(laneExList.getOBUPlate());*/
    	
    }


    public static void encapsulationLanEnParams(LaneEnList laneEnList, List<Object> params) {
        params.add(laneEnList.getEnRecordNo());
        params.add(laneEnList.getLaneEnSerialNo());
        params.add(laneEnList.getCardNetRoadId());
        params.add(laneEnList.getIcinCardId());
        params.add(laneEnList.getEnNetRoadId());
        params.add(laneEnList.getEnRoadId());
        params.add(laneEnList.getEnStationId());
        params.add(laneEnList.getEnLaneId());
        params.add(laneEnList.getEnLaneType());
        params.add(laneEnList.getEnTime());
        params.add(laneEnList.getEnOperatorId());
        params.add(laneEnList.getEnOpCardNo());
        params.add(laneEnList.getEnOpCardId());
        params.add(laneEnList.getEnShiftId());
        params.add(laneEnList.getEnCpcsnNo());
        params.add(laneEnList.getEnCpcinId());
        params.add(laneEnList.getEnVehicleClass());
        params.add(laneEnList.getEnVehicleStatus());
        params.add(laneEnList.getEnVehiclePlate());
        params.add(laneEnList.getEnVehIdentifyPlate());
        params.add(laneEnList.getEnVehicleFlag());
        params.add(laneEnList.getEnIccissuer());
        params.add(laneEnList.getEnCpucardSnNo());
        params.add(laneEnList.getEnCpucardId());
        params.add(laneEnList.getEnCpucardType());
        params.add(laneEnList.getEnEtctermCode());
        params.add(laneEnList.getEnObunum());
        params.add(laneEnList.getEnEtctradNo());
        params.add(laneEnList.getEnEtctermTradNo());
        params.add(laneEnList.getEnEtctac());
        params.add(laneEnList.getSquadDate());
        params.add(laneEnList.getImageSerialNo());
        params.add(laneEnList.getCardBoxNo());
        params.add(laneEnList.getCardTrunkNo());
        params.add(laneEnList.getAppVer());
        params.add(laneEnList.getDealStatus());
        params.add(laneEnList.getDeviceStatus());
        params.add(laneEnList.getRecordType());
        params.add(laneEnList.getVehCount());
        params.add(laneEnList.getTicketType());
        params.add(laneEnList.getListName());
        params.add(laneEnList.getBillNo());
        params.add(laneEnList.getVoidSerialNo());
        params.add(laneEnList.getVerifyCode());
        params.add(laneEnList.getCpccurrentVol());
        params.add(laneEnList.getSpare1());
        params.add(laneEnList.getSpare2());
        params.add(laneEnList.getSpare3());
        params.add(laneEnList.getSpare4());
        params.add(laneEnList.getUploadTime());
    }


}
