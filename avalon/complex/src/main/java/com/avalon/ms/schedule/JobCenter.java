package com.avalon.ms.schedule;

import java.util.UUID;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.avalon.ms.common.util.ConfigUtil;
import com.avalon.ms.schedule.job.MyElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class JobCenter {

	private static DriverManagerDataSource dataSource;
	static{
	        dataSource = new DriverManagerDataSource();
	        dataSource.setUsername(ConfigUtil.getProperty("config/datasource.properties", "jdbc.user"));
	        dataSource.setPassword(ConfigUtil.getProperty("config/datasource.properties", "jdbc.password"));
	        dataSource.setDriverClassName(ConfigUtil.getProperty("config/datasource.properties", "jdbc.driver.class"));
	        dataSource.setUrl(ConfigUtil.getProperty("config/datasource.properties", "jdbc.url"));
	}



  public static void main(String[] args) {
	    UUID  uuid = UUID.randomUUID();
	   
        new JobScheduler(createRegistryCenter(), createJobConfiguration(),createJobEventConfiguration()).init();
    }
    
    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("127.0.0.1:2181", "elastic-job-demo1"));
        regCenter.init();
        return regCenter;
    }
    
    public static JobEventConfiguration createJobEventConfiguration() {
        JobEventConfiguration jobEventConfig = new JobEventRdbConfiguration(dataSource);
        return jobEventConfig;
    }
    
    private static LiteJobConfiguration createJobConfiguration() {
    	
    /*	通用作业配置
     *  // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 10).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, SimpleDemoJob.class.getCanonicalName());
        // 定义Lite作业根配置
        JobRootConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
        
        // 定义作业核心配置
        JobCoreConfiguration dataflowCoreConfig = JobCoreConfiguration.newBuilder("demoDataflowJob", "0/30 * * * * ?", 10).build();
        // 定义DATAFLOW类型配置
        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(dataflowCoreConfig, DataflowDemoJob.class.getCanonicalName(), true);
        // 定义Lite作业根配置
        JobRootConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).build();
        
        // 定义作业核心配置配置
        JobCoreConfiguration scriptCoreConfig = JobCoreConfiguration.newBuilder("demoScriptJob", "0/45 * * * * ?", 10).build();
        // 定义SCRIPT类型配置
        ScriptJobConfiguration scriptJobConfig = new ScriptJobConfiguration(scriptCoreConfig, "test.sh");
        // 定义Lite作业根配置
        JobRootConfiguration scriptJobRootConfig = LiteJobConfiguration.newBuilder(scriptCoreConfig).build();
    	*/
    	
    	
        // 创建作业配置
        // ...
    	 // 定义作业核心配置
	    JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "*/10 * * * * ?", 1).build();
	    // 定义SIMPLE类型配置
	    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
	    // 定义Lite作业根配置
	    LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
	    
	    return simpleJobRootConfig;
    }
}
