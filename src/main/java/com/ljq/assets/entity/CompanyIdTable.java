package com.ljq.assets.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司ID表
 * </p>
 *
 * @author ljq
 * @since 2019-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("companyIdTable")
public class CompanyIdTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId("id")
	private int id;

	@ApiModelProperty("企业ID")
	@TableField("corpId")
	private String corpId;

	@ApiModelProperty("创建时间")
	@TableField("creationTime")
	private LocalDateTime creationTime;

}
