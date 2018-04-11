package com.avalon.ms.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

import com.avalon.ms.common.util.FileUtil;
import com.avalon.ms.common.util.JedisUtil;
import com.avalon.ms.common.util.StringUtils;
import com.avalon.ms.contants.SymBolConstants;
import com.avalon.ms.dao.entity.BhifPayIbankParaInfo;
import com.avalon.ms.dao.entity.KyBranchBankInfos;
import com.avalon.ms.dao.mybatis.mapper.BhifPayIbankParaInfoMapper;
import com.avalon.ms.dao.mybatis.mapper.KyBranchBankInfosMapper;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月18日 下午12:49:04
 *@version
 */
public class PayBank {
	
	static{
		PropertyConfigurator.configure("E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\main\\resources\\config\\log4j.properties");
	    Logger logger = Logger.getLogger(PayBank.class);
		logger.info("start!");
	}
	
	public static void main(String[] args) {
	
		PayBank payBank = new PayBank();
		Set<String> mysqlOracleDifSet =payBank.getDifData(BANKINFO_MYSQL, BANKINFO_ORACLE, "dif_bankInfo_Mysql_Oracle");
		System.out.println(mysqlOracleDifSet.size());
		payBank.outPut("e:\\dif_bankInfo_M_O", mysqlOracleDifSet);
		Set<String> oracleMysqlDifSet =payBank.getDifData(BANKINFO_ORACLE, BANKINFO_MYSQL, "dif_bankInfo_Oracle_Mysql");
		System.out.println(oracleMysqlDifSet.size());
		payBank.outPut("e:\\dif_bankInfo_O_M", oracleMysqlDifSet);
	}
	
	public void outPut(String target,Set<String> content){
		File file = new File(target);
		try {
			file.createNewFile();
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		for(String line: content){
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("文件生成完成");
	}
	
	//config/oracle-datasource.xml
	//config/datasource.xml
	public List getBankData(String config,Class bankEntity){

		List bankInfoList = null;
		ApplicationContext context = new ClassPathXmlApplicationContext(config);
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		SqlSession sqlsession2 = sqlSessionFactory.openSession();
		try {
			if(bankEntity.equals(KyBranchBankInfosMapper.class)){
				KyBranchBankInfosMapper kyBranchBankInfosMapper = (KyBranchBankInfosMapper) sqlsession2.getMapper(bankEntity);
				bankInfoList = kyBranchBankInfosMapper.selectAll();
			}else if(bankEntity.equals(BhifPayIbankParaInfoMapper.class)){
				BhifPayIbankParaInfoMapper bhifPayIbankParaInfoMapper = (BhifPayIbankParaInfoMapper) sqlsession2.getMapper(bankEntity);
				bankInfoList = bhifPayIbankParaInfoMapper.selectAll();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			sqlsession2.close();
		}
		System.out.println("查询完成");
		return bankInfoList;
	}
	
	
	private static final String BANKINFO_MYSQL = "bankinfo_mysql";
	private static final String BANKINFO_ORACLE = "bankinfo_oracle";
	private static final int  REDIS_INDEX = 2;
	
	public Set getDifData(String set1,String set2,String targetSet){
		Jedis jedis = JedisUtil.getJedis(REDIS_INDEX);
		jedis.sdiffstore(targetSet, set1,set2);
		Set<String> set = jedis.smembers(targetSet);
		jedis.close();
		return set;
	}
	
	
	public void storeBankInfofromOracle(List<KyBranchBankInfos> bankInfoList){
		Jedis jedis = JedisUtil.getJedis(REDIS_INDEX);
		jedis.del(BANKINFO_ORACLE);
		for(KyBranchBankInfos cur: bankInfoList){
			jedis.sadd(BANKINFO_ORACLE, (cur.getCode()+"|"+cur.getName()));
		}
		System.out.println("set add end");
		jedis.close();
	}
	
	public void storeBankInfofromMysql(List<BhifPayIbankParaInfo> bankInfoList ){
		Jedis jedis = JedisUtil.getJedis(REDIS_INDEX);
		jedis.del(BANKINFO_MYSQL);
		for(BhifPayIbankParaInfo cur: bankInfoList){
			jedis.sadd(BANKINFO_MYSQL, (cur.getPayIbankNum()+"|"+cur.getPayIbankFullNm()));
		}
		System.out.println("set add end");
		jedis.close();
	}
	
	
	public static List<BhifPayIbankParaInfo> getPayBankInfoList(String path){
		
		List<String> bankList = FileUtil.readFile(path);
		if(bankList==null || bankList.size()==0)
			return null;
		System.out.println(bankList.size());
		BhifPayIbankParaInfo bpIbankParaInfo =null;
		List<BhifPayIbankParaInfo> bpipiList = new ArrayList<BhifPayIbankParaInfo>();
		for (String cur : bankList) {
			bpIbankParaInfo = new BhifPayIbankParaInfo();
			bpIbankParaInfo.setPayIbankNum(StringUtils.getFieldsplitBySpecity(cur,SymBolConstants.VERTICAL_LINE_REGEX, 3));
			bpIbankParaInfo.setPayIbankFullNm(StringUtils.getFieldsplitBySpecity(cur,SymBolConstants.VERTICAL_LINE_REGEX, 13));
			bpipiList.add(bpIbankParaInfo);
		}
		return bpipiList;
	}
}
