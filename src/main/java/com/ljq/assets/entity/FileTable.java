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
 * 文件表
 * </p>
 *
 * @author ljq
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fileTable")
public class FileTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	@TableId(type = IdType.AUTO)
	private int id;

	@ApiModelProperty("文件名称")
	@TableField("fileName")
	private String fileName;

	@ApiModelProperty("文件路径")
	@TableField("filePath")
	private String filePath;

	@ApiModelProperty("访问路径")
	@TableField("accessPath")
	private String accessPath;

	@ApiModelProperty("创建时间")
	@TableField("creationTime")
	private LocalDate creationTime;

}
