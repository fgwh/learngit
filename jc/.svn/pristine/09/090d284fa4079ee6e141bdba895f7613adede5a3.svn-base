package com.hgsoft.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字工具类
 */
public class NumberTool {
    private static Log log = LogFactory.getLog(NumberTool.class);
    private static final int DEFAULT_PRICESION = 3;
    protected NumberTool(){}

    public static short parseShort(String pValue) {
        if(pValue == null) return 0;
        try {
            return Short.parseShort(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    public static Short parseShortObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Short(Short.parseShort(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    public static int parseInt(String pValue) {
        if(pValue == null) return 0;
        try {
            return Integer.parseInt(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    public static Integer parseInteger(String pValue) {
        if(pValue == null) return null;
        try {
            return new Integer(Integer.parseInt(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    public static long parseLong(String pValue) {
        if(pValue == null) return 0;
        try {
            return Long.parseLong(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    public static Long parseLongObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Long(Long.parseLong(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    public static double parseDouble(String pValue) {
        if(pValue == null) return 0;
        try {
            return Double.parseDouble(pValue);
        } catch (Exception ex) {
            log.error(ex);
            return 0;
        }
    }

    public static Double parseDoubleObj(String pValue) {
        if(pValue == null) return null;
        try {
            return new Double(Double.parseDouble(pValue));
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }


    // 按给定的格式格式化。
    public static String format(double d, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }
    
    
    // 按给定的格式格式化。
    public static String format(Double d, String pattern) {
        if(d != null) {
            return format(d.doubleValue(), pattern);
        }else {
            return null;
        }
    }

    // 指定精度格式化字符串, 不显示末尾多余的0。
    public static String format(double d, int precision) {
        if (precision < 0) {
            return String.valueOf(d);
        }
        StringBuffer sb = new StringBuffer("#");
        if (precision > 0) {
            sb.append(".");
        }
        for (int i = 0; i < precision; i++) {
            sb.append("#");
        }
        return format(d, sb.toString());
    }
    
    // 指定精度格式化字符串, 不显示末尾多余的0。
    public static String format(Double d, int precision) {
        if(d != null) {
            return format(d.doubleValue(), precision);
        }else {
            return null;
        }
    }

    // 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。
    public static String forcibleFormat(double d, int precision) {
        if (precision < 0) {
            return String.valueOf(d);
        }
        StringBuffer sb = new StringBuffer("#");
        if (precision > 0) {
            sb.append(".");
        }
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }
        return format(d, sb.toString());
    }
    
    // 指定精度格式化字符串, 不删除后面的0。 如果不够用0补齐。
    public static String forcibleFormat(Double d, int precision) {
        if(d != null) {
            return forcibleFormat(d.doubleValue(), precision);
        }else {
            return null;
        }
    }

    // 四舍五入.
    public static double round(double d, int precision) {
        //return parseDouble(format(d, precision));
    	//return Math.round(d * Math.pow(10, precision))/Math.pow(10, precision);
    	if (Double.NaN==d){
    		return d;
    	}
    	BigDecimal bd = new BigDecimal("0");
    	//由于BigDecimal(double val) 方法不精确，把double转成String再传进去    	
    	try {
    	bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
    	} catch (Exception e){
    		return 0d;
//    		e.printStackTrace();
    	}
		return bd.doubleValue();
    }
    
    // 四舍五入.
    public static Double round(Double d, int precision) {
        if(d != null) {
            return new Double(round(d.doubleValue(), precision));
        }else {
            return null;
        }
    }

    public static int intValue(Integer integer) {
        if (integer != null) {
            return integer.intValue();
        } else {
            return 0;
        }
    }

    public static double doubleValue(Double d) {
        if (d != null) {
            return d.doubleValue();
        } else {
            return 0;
        }
    }
    
    /**
	 * double 与 double 相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * double 与 double 相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * double 与 double 相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * double 与 double 相除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, DEFAULT_PRICESION, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 返回double
	 * 
	 * @param
	 * @return
	 */
	public static double round(double d) {
		return round(d, DEFAULT_PRICESION);
	}

	public static void main(String args[]){
		/*System.out.println(NumberTool.round(1.045, 2));
		System.out.println(com.hgsoft.security.util.NumberTool.round(4.015, 2));
		System.out.println(new BigDecimal(0.1));*/
	}
}
