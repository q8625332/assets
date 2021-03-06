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
 * 资产与文件的关系
 * </p>
 *
 * @author ljq
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("assetsAndDocuments")
public class AssetsAndDocuments implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("资产信息ID")
	@TableField("assId")
	private Integer assId;

	@ApiModelProperty("文件表ID")
	@TableField("filId")
	private Integer filId;

}
