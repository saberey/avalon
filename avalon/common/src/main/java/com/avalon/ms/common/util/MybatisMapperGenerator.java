package com.avalon.ms.common.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis 生成器，根据mapperconfig.xml定义内容进行xml及bean的生成
 * 
 * @description:TODO
 * @author: saber
 * @time: 2017年10月9日 上午11:04:00
 * @version
 */
public class MybatisMapperGenerator {

	public static void main(String[] args) throws Exception{
		 	List<String> warnings = new ArrayList<String>();
	        boolean overwrite = true;//是否覆盖原来的文件

	        File currentDir = new File(".");
	        System.out.println(currentDir.getAbsolutePath());

	        File configFile = new File("src/main/java/com/avalon/ms/common/util/mapperconfig.xml");
	        ConfigurationParser cp = new ConfigurationParser(warnings);
	        Configuration config = cp.parseConfiguration(configFile);
	        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	        myBatisGenerator.generate(null);
	}
}
