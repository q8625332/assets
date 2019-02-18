package com.ljq.assets.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 导航表
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("navigationTable")
public class NavigationTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("导航栏名称")
	@TableField("navName")
	private String navName;

	@ApiModelProperty("导航栏地址")
	@TableField("navPath")
	private String navPath;

	@ApiModelProperty("上级id")
	private Integer pid;

	@ApiModelProperty("上级id")
	private Integer type;

	@ApiModelProperty("层级")
	private Integer level;

}
