package com.hgsoft.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.jcManange.service.AbnorCarService;
import com.hgsoft.main.jcManange.service.AbnormalCarDetailService;
import com.hgsoft.util.DateUtil;

public class AbnorCarJob extends JobLog implements Job {
	private static WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();

	private static Logger logger = Logger.getLogger(AbnorCarJob.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf_Year = new SimpleDateFormat("yyyy");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		this.getAbnorCarData(qrtzLog, new Date());
		saveQrtzLog(qrtzLog);// 记日志
	}

	/**
	 * 获取可疑车辆信息
	 */
	public void getAbnorCarData(QrtzLog qrtzLog, Date date) {
		Long d = System.currentTimeMillis();
		qrtzLog.setCreatetime(new Date());
		try {
			// 分月获取
			List<Object[]> timeList = getPreOneMonth(date);// sdf.parse(s)
			int totalSize = 0;
			for (int i = 0; i < timeList.size(); i++) {
				Object[] obj = timeList.get(i);
				totalSize += saveAbnorCarData(obj[0].toString(), obj[1].toString(), totalSize);
			}
			qrtzLog.setDescription(totalSize + "条异常信息保存到可疑车辆信息表成功");
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
			logger.info(totalSize + "条异常信息保存到可疑车辆信息表成功");
		} catch (Exception e) {
			qrtzLog.setDescription("保存到可疑车辆信息表失败");
			logger.info("保存到可疑车辆信息表失败");
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
			e.printStackTrace();
		}
		Long b = System.currentTimeMillis();
		System.out.println("耗时：" + (b - d));
	}

	/**
	 * 保存可疑车辆信息
	 */
	public int saveAbnorCarData( String beginDate, String endDate, int totalSize) {
		AbnormalCarDetailService abnormalCarDetailService = (AbnormalCarDetailService) applicationContext
				.getBean("abnormalCarDetailService"); 

		List<Object[]> axisNumList = null, identifyPointOneList = null, identifyPointTwoList = null,
				pourCarEeceptionList = null, eTCVehTypeList = null, eTCVehPlateList = null;

		String year = beginDate.substring(0, 4);

		// 倒卡
		pourCarEeceptionList = abnormalCarDetailService.getPourCarException(year, beginDate, endDate);
		// ETC车型
		eTCVehTypeList = abnormalCarDetailService.getETCVehTypeException(year, beginDate, endDate);
		// ETC车牌
		eTCVehPlateList = abnormalCarDetailService.getETCVehPlateException(year, beginDate, endDate);
		// 收费标识点异常(有收费无标识)
		identifyPointOneList = abnormalCarDetailService.identifyPointOne(year, beginDate, endDate);
		// 收费标识点异常2
		identifyPointTwoList = abnormalCarDetailService.identifyPointTwo(year, beginDate, endDate);
		// 车型与轴组类型异常
		axisNumList = abnormalCarDetailService.getAxisNumException(year, beginDate, endDate);

		abnormalCarDetailService.saveAbnorCarList(pourCarEeceptionList, "1");
		abnormalCarDetailService.saveAbnorCarList(eTCVehTypeList, "2");
		abnormalCarDetailService.saveAbnorCarList(eTCVehPlateList, "3"); 
		abnormalCarDetailService.saveAbnorCarList(identifyPointOneList, "4");
		abnormalCarDetailService.saveAbnorCarList(identifyPointTwoList, "5");

		abnormalCarDetailService.saveAbnorCarList(axisNumList, "6");

		totalSize = pourCarEeceptionList.size() + eTCVehTypeList.size() + eTCVehPlateList.size() +identifyPointOneList.size()+identifyPointTwoList.size()+ axisNumList.size();

		// 去重
		int deleteSize = abnormalCarDetailService.deleteByReply();
		
		return totalSize - deleteSize;

	}

	/**
	 * 拆分日期
	 * 
	 * @param preMonth
	 * @return
	 */
	public static List<Object[]> getPreOneMonth(Date date) {

		List<Object[]> monthList = new ArrayList<Object[]>();
		String[] monthArray;
		Date beforeDate = null;
		Date afterDate = null;
		for (int i = 3; i > 0; i--) {
			Calendar calendar = Calendar.getInstance();
			/*
			 * if (null == date) { //calendar.set(Calendar.MONTH,
			 * calendar.get(Calendar.MONTH) - i); calendar.setTime(new Date());
			 * calendar.add(Calendar.DATE, -i*30); beforeDate =
			 * calendar.getTime(); calendar.setTime(new Date());
			 * //calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			 * calendar.add(Calendar.DATE, (1-i)*30); afterDate =
			 * calendar.getTime(); } else {
			 */
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -i * 30);
			beforeDate = calendar.getTime();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, (1 - i) * 30);
			afterDate = calendar.getTime();
			/* } */

			String beforeYear = sdf_Year.format(beforeDate);
			String afterYear = sdf_Year.format(afterDate);

			if (beforeYear.equals(afterYear)) {// 如果两个年份相等 则直接返回开始日期和结束日期
				monthArray = new String[2];
				monthArray[0] = sdf.format(beforeDate);
				monthArray[1] = sdf.format(afterDate);
				monthList.add(monthArray);
			} else {// 如果两个年份不相等 则需要返回四个日期
				monthArray = new String[2];
				monthArray[0] = sdf.format(beforeDate);
				monthArray[1] = DateUtil.getLastDay(beforeDate);
				monthList.add(monthArray);

				monthArray = new String[2];
				monthArray[0] = DateUtil.getFirstDay(afterDate);
				monthArray[1] = sdf.format(afterDate);
				monthList.add(monthArray);
			}
		}

		return monthList;

	}

	public static void main(String[] args) throws Exception {
		String s = "2016-01-26";
		/*
		 * List<Object[]> aa = getPreOneMonth(sdf.parse(s));//sdf.parse(s) for
		 * (int i = 0; i < aa.size(); i++) { Object[] b = aa.get(i); for (int j
		 * = 0; j < b.length; j++) { System.out.println(b[j]); } }
		 */
		System.out.println(s.substring(0, 4));

		// System.out.println(getLastDay("2016-03-30",0,-1,0));;
	}
}
