package com.example.demo.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;

/** 自定义数据库方言 */
public class AppOracleDialect extends H2Dialect {

  /**
   * 重写方言方法实现
   *
   * @return
   */
  @Override
  public String getQuerySequencesString() {
    return "select * from user_sequences";
  }

  /** 注册方法支持多数据库（eg:Mysql替换为Oracle，对于特殊函数使用Oracle语法进行解析） */
  // org.hibernate.dialect.Oracle12cDialect
  // 自定义数据库方言实现
  public AppOracleDialect() {
    super();
    registerFunction(
        "dayofyear", new SQLFunctionTemplate(IntegerType.INSTANCE, "to_number(to_char(?1,'ddd'))"));
  }
}
