package com.ljq.assets.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljq.assets.entity.AssetClass;
import com.ljq.assets.entity.AssetsInformation;
import com.ljq.assets.service.IAssetClassService;
import com.ljq.assets.service.IAssetsInformationService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 资产类别 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-19
 */
@RestController
@RequestMapping("/assetClassController")
@Api(value = "资产类别 前端控制器", tags = " 资产类别", description = " 资产类别组")
@SuppressWarnings({ "rawtypes" })
public class AssetClassController {

	@Resource
	private IAssetClassService iAssetClassService;

	@Resource
	private IAssetsInformationService iAssetsInformationService;

	@ApiOperation(value = "添加资产类别", notes = "传入：分类名称，")
	@PostMapping("/addAssetClass")
	public ServiceResult addAssetClass(AssetClass assetClass) {
		boolean res = iAssetClassService.save(assetClass);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("添加资产类别成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("添加资产类别失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据ID查询资产类别", notes = "传入：分类名称，上级分类，使用年限，计量单位")
	@PostMapping("/quetyAssetClassById")
	public ServiceResult quetyAssetClassById(AssetClass assetClass) {
		boolean res = iAssetClassService.save(assetClass);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("添加资产类别成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("添加资产类别失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "更新ID查询资产类别", notes = "传入：ID，分类名称，上级分类，使用年限，计量单位")
	@PostMapping("/updateAssetClassById")
	public ServiceResult updateAssetClassById(AssetClass assetClass) {
		boolean res = iAssetClassService.updateById(assetClass);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("添加资产类别成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("添加资产类别失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "删除ID查询资产类别", notes = "传入：ID，分类名称，上级分类，使用年限，计量单位")
	@PostMapping("/deleteAssetClassById")
	public ServiceResult updateAssetClassById(int id) {
		int res = iAssetClassService.count(new QueryWrapper<AssetClass>().eq("pid", id));
		if (res == 0) {
			int res2 = iAssetsInformationService.count(new QueryWrapper<AssetsInformation>().eq("assetCategoryId", id));
			if (res2 == 0) {
				iAssetClassService.removeById(id);
				ServiceResult serviceResult = ServiceResult.success("删除成功");
				return serviceResult;
			} else {
				ServiceResult serviceResult = ServiceResult.failure("此资产类别还在被使用着");
				return serviceResult;
			}
		} else {
			ServiceResult serviceResult = ServiceResult.failure("此节点下存在未删除的子节点，不能删除");
			return serviceResult;
		}
	}

}
