package com.hgsoft.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

/**
 * 自定义SQLServer dialect
 * 
 * @date 2015-6-18
 * @author wubiao
 *
 */
public class MySQLServerDialect extends SQLServerDialect {

	public MySQLServerDialect(){  
        super();  
        registerHibernateType(Types.CHAR, Hibernate.STRING.getName());// mapping char(n) to String    
    }  
}
