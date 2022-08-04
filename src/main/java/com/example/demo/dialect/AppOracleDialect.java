package com.example.demo.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;

public class AppOracleDialect extends H2Dialect
{
    @Override
    public String getQuerySequencesString() {
        return "select * from user_sequences";
    }


    //自定义数据库方言实现
    public AppOracleDialect() {
        super();
        registerFunction("dayofyear", new SQLFunctionTemplate(IntegerType.INSTANCE, "to_number(to_char(?1,'ddd'))"));
    }

}