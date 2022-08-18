package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/** 创建BaseEntity，保存基础审计信息，所有Entity集成BaseEntity */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity implements Serializable {

  /** 创建者 */
  @CreatedBy private Long createBy;

  /** 创建时间 */
  @Column(columnDefinition = "TIMESTAMP")
  @CreatedDate
  private Date createTime;

  /** 更新者 */
  @LastModifiedBy private Long updateBy;

  /** 更新时间 */
  @Column(columnDefinition = "TIMESTAMP")
  @LastModifiedDate
  private Date updateTime;

  /** 备注 */
  private String remark;

  /** 开始时间 */
  @Transient private String beginTime;

  /** 结束时间 */
  @Transient private String endTime;

  /** 请求参数 */
  @Transient private Map<String, Object> params;

  @Transient private int pageNum;

  @Transient private int pageSize;
}
