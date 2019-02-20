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
 * 资产类别
 * </p>
 *
 * @author ljq
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("assetClass")
public class AssetClass implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("分类名称")
	@TableField("ClassificationName")
	private String ClassificationName;

	@ApiModelProperty("上级分类")
	private Integer pid;

	@ApiModelProperty("使用年限")
	@TableField("serviceLife")
	private Integer serviceLife;

	@ApiModelProperty("计量单位")
	@TableField("unitOfMeasurement")
	private String unitOfMeasurement;

}
