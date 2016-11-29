package com.hgsoft.main.jcManange.entity;

public class PK implements java.io.Serializable{
	
    private String vehPlate;
	
	private int vehPlateColor;

	public String getVehPlate() {
		return vehPlate;
	}

	public void setVehPlate(String vehPlate) {
		this.vehPlate = vehPlate;
	}

	public int getVehPlateColor() {
		return vehPlateColor;
	}

	public void setVehPlateColor(int vehPlateColor) {
		this.vehPlateColor = vehPlateColor;
	}
	
	
	 @Override  
	 public boolean equals(Object o) {  
	  if(o instanceof PK) {  
	    PK pk = (PK)o;  
	   if(this.vehPlate.equals(pk.getVehPlate())&&this.vehPlateColor == pk.getVehPlateColor()) {  
	      return true;  
	   }  
	  }  
	  return false;  
	 }  

}
