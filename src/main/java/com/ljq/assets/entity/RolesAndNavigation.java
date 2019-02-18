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
 * 角色与模块表
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rolesAndNavigation")
public class RolesAndNavigation implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("角色ID")
	@TableField("rolId")
	private Integer rolId;

	@ApiModelProperty("操作组ID")
	@TableField("navId")
	private Integer navId;

	@ApiModelProperty("操作ID")
	@TableField("navId2")
	private Integer navId2;

}
