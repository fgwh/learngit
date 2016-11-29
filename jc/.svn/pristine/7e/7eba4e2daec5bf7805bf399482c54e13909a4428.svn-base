package com.hgsoft.parse;

import java.io.File;
import java.util.LinkedList;

import org.apache.commons.logging.Log;

import com.hgsoft.txt.parse.entity.Template;

public interface Handle {
	LinkedList<ConsumptionEntry> parse(String datafilePath,Template template,String encoding);
	
	LinkedList<ConsumptionEntry> parse(String datafilePath,Template template);
	
	LinkedList<ConsumptionEntry> parse(File datafilePath,Template template);
	
	LinkedList<ConsumptionEntry> parse(File datafilePath, Template template,String encoding);
	
	void writeLog(Exception e, Log logger, Class<? extends Object> clazz);
}
