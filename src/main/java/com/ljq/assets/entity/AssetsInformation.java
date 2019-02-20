package com.ljq.assets.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
 * 资产信息
 * </p>
 *
 * @author ljq
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("assetsInformation")
public class AssetsInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("使用部门")
	@TableField("useDepartment")
	private String useDepartment;

	@ApiModelProperty("部门ID")
	@TableField("departmentId")
	private String departmentId;

	@ApiModelProperty("使用人")
	@TableField("userName")
	private String userName;

	@ApiModelProperty("使用人ID")
	@TableField("userId")
	private String userId;

	@ApiModelProperty("使用地")
	@TableField("siteOfUse")
	private String siteOfUse;

	@ApiModelProperty("使用地ID")
	@TableField("siteOfUseId")
	private Integer siteOfUseId;

	@ApiModelProperty("领用时间")
	@TableField("leadTime")
	private LocalDate leadTime;

	@ApiModelProperty("资产名称")
	@TableField("assetName")
	private String assetName;

	@ApiModelProperty("资产类别")
	@TableField("assetClass")
	private String assetClass;

	@ApiModelProperty("资产类别ID")
	@TableField("assetCategoryId")
	private Integer assetCategoryId;

	@ApiModelProperty("入库时间")
	@TableField("warehousingTime")
	private LocalDate warehousingTime;

	@ApiModelProperty("型号")
	@TableField("modelNumber")
	private String modelNumber;

	@ApiModelProperty("计量单位")
	@TableField("unitOfMeasurement")
	private String unitOfMeasurement;

	@ApiModelProperty("来源")
	private String source;

	@ApiModelProperty("来源ID")
	@TableField("sourceId")
	private Integer sourceId;

	@ApiModelProperty("渠道")
	private String channel;

	@ApiModelProperty("金额")
	@TableField("amountaOfMoney")
	private String amountaOfMoney;

	@ApiModelProperty("品牌")
	private String brand;

	@ApiModelProperty("购入时间")
	@TableField("purchaseTime")
	private LocalDate purchaseTime;

	@ApiModelProperty("使用期限")
	@TableField("serviceLife")
	private String serviceLife;

	@ApiModelProperty("SN号")
	@TableField("numberSN")
	private String numberSN;

	@ApiModelProperty("保修起始")
	@TableField("warrantyStart")
	private LocalDate warrantyStart;

	@ApiModelProperty("过保时间")
	@TableField("overGuaranteedTime")
	private LocalDate overGuaranteedTime;

	@ApiModelProperty("备注")
	private String remarks;

	@ApiModelProperty("额外配置")
	@TableField("additionalConfiguration")
	private String additionalConfiguration;

	@ApiModelProperty("资产状态")
	@TableField("assetStatus")
	private String assetStatus;

	@ApiModelProperty("状态标题ID")
	@TableField("staId")
	private int staId;
}
