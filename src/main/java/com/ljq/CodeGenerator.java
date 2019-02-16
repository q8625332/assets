package com.ljq;

import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
* @Package:        com.util
* @ClassName:      CodeGenerator
* @Description:    java类作用描述
* @Author:         刘俊秦
* @CreateDate:     2018/9/16 0016 上午 11:12
* @Version:        1.0
*
*/
@SuppressWarnings("resource")
public class CodeGenerator {
	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("ljq");
		gc.setOpen(false);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		// 120.79.241.115:3306/application
		dsc.setUrl("jdbc:mysql://120.79.172.69:3306/assets?useUnicode=true&useSSL=false&characterEncoding=utf8");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("assets");
		dsc.setPassword("tfassets");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(scanner("模块名"));
		pc.setParent("com.ljq");
		mpg.setPackageInfo(pc);

		// 自定义配置
		// InjectionConfig cfg = new InjectionConfig() {
		// @Override
		// public void initMap() {
		// // to do nothing
		// }
		// };
		// List<FileOutConfig> focList = new ArrayList<>();
		// focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
		// @Override
		// public String outputFile(TableInfo tableInfo) {
		// // 自定义输入文件名称
		// return projectPath + "/src/main/resources/mapper/" +
		// pc.getModuleName()
		// + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
		// }
		// });
		// cfg.setFileOutConfigList(focList);
		// mpg.setCfg(cfg);
		// mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperEntityClass("com.ljq.rxrs.entity");
		strategy.setEntityLombokModel(true);
		// strategy.setSuperControllerClass("com.ljq.rxrs.Controller");
		strategy.setInclude(scanner("表名"));
		strategy.setSuperEntityColumns("id");
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
