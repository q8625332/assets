package com.ljq.assets.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljq.assets.entity.AssetAddress;
import com.ljq.assets.service.IAssetAddressService;
import com.ljq.assets.util.ServiceResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 资产地址 前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/assetAddressController")
@Api(value = "资产地址 前端控制器", tags = "资产地址表", description = "资产地址表组")
@SuppressWarnings({ "rawtypes" })
public class AssetAddressController {

	@Resource
	private IAssetAddressService iAssetAddressService;

	@ApiOperation(value = "添加资产位置", notes = "传入：位置名称，备注")
	@PostMapping("/addAssetAddress")
	public ServiceResult addAssetAddress(AssetAddress assetAddress) {
		boolean res = iAssetAddressService.save(assetAddress);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("添加资产位置成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("添加资产位置失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "根据ID查询资产位置", notes = "传入：id")
	@PostMapping("/queryAssetAddressById")
	public ServiceResult queryAssetAddressById(int id) {
		AssetAddress assetAddress = iAssetAddressService.getById(id);
		if (assetAddress != null) {
			ServiceResult serviceResult = ServiceResult.success(assetAddress);
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("查询资产位置失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "更新资产位置", notes = "传入：id，位置名称，备注")
	@PostMapping("/updateAssetAddressById")
	public ServiceResult updateAssetAddressById(AssetAddress assetAddress) {
		boolean res = iAssetAddressService.updateById(assetAddress);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("更新资产位置成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("更新资产位置失败");
			return serviceResult;
		}
	}

	@ApiOperation(value = "删除资产位置", notes = "传入：id")
	@PostMapping("/deleteAssetAddress")
	public ServiceResult deleteAssetAddress(int id) {
		boolean res = iAssetAddressService.removeById(id);
		if (res) {
			ServiceResult serviceResult = ServiceResult.success("删除资产位置成功");
			return serviceResult;
		} else {
			ServiceResult serviceResult = ServiceResult.failure("删除资产位置失败，地址在被使用着");
			return serviceResult;
		}
	}

}
