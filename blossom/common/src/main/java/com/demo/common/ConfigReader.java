//*************************************************************************
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*                                                                       *
//*                COPYRIGHT (C) FINANCING CITY CORPORATION 2012          *
//*    ALL RIGHTS RESERVED BY FINANCING CITY CORPORATION. THIS PROGRAM    *
//* MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY    *
//* FINANCING CITY CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED *
//* OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN         *
//* PERMISSION OF FINANCING CITY CORPORATION. USE OF COPYRIGHT NOTICE     *
//* DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                         *
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*************************************************************************
package com.demo.common;

import org.apache.log4j.Logger;

/**
 * ConfigReader
 * 从config properties 文件中取得相应的PropertyValue
 *
 */
public class ConfigReader {

	/**
	 * The log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(ConfigReader.class);

	/**
	 * memcached server
	 */
	public static final String MEMCACHED_SERVER = "memcached.server";

	/**
	 * sms指定 channel 0: ORIGINAL; 1: MONTNET;  default:0
	 */
	public static final String SMS_CHANNEL_VENDOR = "sms.channel.vendor";

	/**
	 * sms指定 channel的权重 0-100; default:100
	 */
	public static final String SMS_CHANNEL_WEIGHT = "sms.channel.weight";

	/**
	 * 发送失败重试次数 0-10; default:3
	 */
	public static final String SMS_CHANNEL_RETRY_NUM = "sms.channel.retry.num";

	/**
	 * 发送失败重试间隔t 0-30秒; default:3秒
	 */
	public static final String SMS_CHANNEL_RETRY_INTERVAL = "sms.channel.retry.interval";

	/**
	 * sms 发送失败时的报告文件
	 */
	public static final String SMS_CHANNEL_FAIL_REPORT_FILE = "sms.channel.fail.report.file";

	/**
	 * 数据导出 Path
	 */
	public static final String DATA_OUTPU_PATH = "data.output.path";

	/**
	 * 数据导出 HttpPath
	 */
	public static final String DATA_OUTPU_HTTP_PATH = "data.output.http.path";
	/**
	 * 数据导出文件 最大保存天数(default:7天),范围:1天-- 5*365天(5年)
	 */
	public static final String DATA_OUTPUT_FILE_MAX_DAYS = "data.output.file.max.days";

	/**
	 * 数据导出任务 最大保存天数(default:90天),范围:1天-- 5*365天(5年)
	 */
	public static final String DATA_OUTPUT_TASK_MAX_DAYS = "data.output.task.max.days";

	/**
	 *  短信发送状态报告取回 前置时间(default:60秒),范围:1秒-- 30*60秒(30分)
	 */
	public static final String SMS_SEND_STATUS_FETCH_BEFORE = "sms.send.status.fetch.before";

	/**
	 * 短信发送任务 最大保存天数(default:90天),范围:1天-- 5*365天(5年)
	 */
	public static final String SMS_SEND_TASK_MAX_DAYS = "sms.send.task.max.days";

	/**
	 * 短信发送history 最大保存天数(default:90天),范围:1天-- 5*365天(5年)
	 */
	public static final String SMS_SEND_HISTORY_MAX_DAYS = "sms.send.history.max.days";

	/**
	 * 短信发送状态报告 最大保存天数(default:90天),范围:1天-- 5*365天(5年)
	 */
	public static final String SMS_SEND_STATUS_REPORT_MAX_DAYS = "sms.send.status.report.max.days";

	/**
	 * 机房主备状态更新后的timout(秒)
	 */
	public static final String HOUSE_STATUS_TIMEOUT = "house.status.timeout";

	/**
	 * MONTNET 通道 url
	 */
	public static final String SMS_CHANNEL_MONTNET_URL = "sms.channel.montnet.url";
	/**
	 * MONTNET 通道 statusReport url
	 */
	public static final String SMS_CHANNEL_MONTNET_STATUS_REPORT_URL = "sms.channel.montnet.statusReport.url";

	/**
	 * MONTNET 通道 userId
	 */
	public static final String SMS_CHANNEL_MONTNET_USER_ID = "sms.channel.montnet.userId";

	/**
	 * MONTNET 通道 password
	 */
	public static final String SMS_CHANNEL_MONTNET_PASSWORD = "sms.channel.montnet.password";

	/**
	 * UMS 通道 url
	 */
	public static final String SMS_CHANNEL_UMS_URL = "sms.channel.ums.url";

	/**
	 * UMS 通道 statusReport url
	 */
	public static final String SMS_CHANNEL_UMS_STATUS_REPORT_URL = "sms.channel.ums.statusReport.url";


	/**
	 * UMS 通道 spCode
	 */
	public static final String SMS_CHANNEL_UMS_SP_CODE = "sms.channel.ums.spCode";
	/**
	 * UMS 通道 userId
	 */
	public static final String SMS_CHANNEL_UMS_USER_ID = "sms.channel.ums.userId";

	/**
	 * UMS 通道 password
	 */
	public static final String SMS_CHANNEL_UMS_PASSWORD = "sms.channel.ums.password";

	/**
	 * 短信猫 server url
	 */
	public static final String SMS_CHANNEL_MODEM_SERVER_URL = "sms.channel.modem.server.url";
	/**
	 * 短信猫B server url
	 */
	public static final String SMS_CHANNEL_MODEM_B_SERVER_URL = "sms.channel.modem.b.server.url";
	/**
	 * 短信猫C server url
	 */
	public static final String SMS_CHANNEL_MODEM_C_SERVER_URL = "sms.channel.modem.c.server.url";

	/**
	 * AP 业务状态报告文件名
	 */
	public static final String AP_BUSINESS_STATUS_REPORT_FILE = "ap.business.status.report.file";

	/**
	 * 银行入账单超时时间
	 */
	public static final String SUPER_BANK_IN_OVER_TIME = "super.bank.in.over.time";
	/**
	 * 银行入账单超时时间
	 */
	public static final String MEMBER_LOGIN_LOCK_TIME = "member.login.lock.time";

	/** 允许登录，默认允许，在机房维护时可以临时设置为禁止 */
	public static final String ENABLE_MEMBER_LOGIN = "enable.member.login";

	/**
	 * 其它的状态管理的ap名
	 */
	public static final String AP_OTHER_NAME_LIST = "ap.other.name.list";

	/**
	 * 验证码相关的短信，如果目标手机号是中国移动或中国电信，同时复制一份短信到特定手机号 复制 enable
	 */
	public static final String SMS_REPLICATION_ENABLE = "sms.replication.enable";

	/**
	 * 验证码相关的短信，如果目标手机号是中国移动的，复制短信所发向的特定手机号
	 */
	public static final String SMS_REPLICATION_TARGET_NO = "sms.replication.target.no";
	/**
	 * 激活监管账户-建议注资金额
	 */
	public static final String SUPER_ACTIVE_BANK_IN_PROPOSE_AMOUNT = "super.active.bank.in.propose.amount";

	/**
	 * 从库需要导出的dubbo service名列表
	 */
	public static final String SLAVE_EXPORTED_SERVICE_NAME_LIST = "slave.exported.service.name.list";

	/**
	 *  cdn url
	 */
	public static final String PAGE_STATIC_CDN_URL = "page.static.cdn.url";

	/**
	 *  cdn userId
	 */
	public static final String PAGE_STATIC_CDN_USER_ID = "page.static.cdn.userId";

	/**
	 *  cdn password
	 */
	public static final String PAGE_STATIC_CDN_PASSWORD = "page.static.cdn.password";

	/**
	 * msg service enable
	 */
	public static final String MSG_SERVICE_ENABLE = "msg.service.enable";

	/**
	 * msg service url
	 */
	public static final String MSG_SERVICE_URL = "msg.service.url";

	/**
	 * msg service user
	 */
	public static final String MSG_SERVICE_USER = "msg.service.user";

	/**
	 * msg service password
	 */
	public static final String MSG_SERVICE_PASSWORD = "msg.service.password";

	/**
	 * 需要接收消息的queue名称列表
	 */
	public static final String MSG_SERVICE_RECEIVE_DESTINATION_QUEUES = "msg.service.receive.destination.queues";

	/**
	 * 需要接收消息的topic名称列表
	 */
	public static final String MSG_SERVICE_RECEIVE_DESTINATION_TOPICS = "msg.service.receive.destination.topics";

	/**
	 * dubbo master flag
	 */
	public static final String DUBBO_MASTER_FLAG = "dubbo.master.flag";
	/**
	 * 从库dataSource监视间隔（秒）
	 */
	public static final String SLAVE_DATASOURCE_MONITOR_INTERVAL = "slave.dataSource.monitor.interval";
	/**
	 * 从库dataSource监视最大故障次数
	 */
	public static final String SLAVE_DATASOURCE_MONITOR_MAXFAILURECOUNT = "slave.dataSource.monitor.maxFailureCount";


	/**
	 * 取得 super.active.bank.in.propose.amount
	 * @return
	 */
	public static String getSuperActiveBankInProposeAmount() {
		// default value
		String resultValue = "任意金额";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SUPER_ACTIVE_BANK_IN_PROPOSE_AMOUNT);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SUPER_ACTIVE_BANK_IN_PROPOSE_AMOUNT + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 memcached server
	 *
	 * @return memcached server
	 */
	public static String getMemcachedServer() {
		// default value
		String resultValue = "localhost:11211";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MEMCACHED_SERVER);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MEMCACHED_SERVER + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 sms指定 channel
	 *
	 * @return  0: ORIGINAL; 1: MONTNET;  default:0
	 */
	public static int getSmsChannelVendor() {
		// default value: 0
		int resultValue = 0;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_VENDOR);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_VENDOR + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_CHANNEL_VENDOR + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :0,1,2 ,3,4,5
		if (resultValue < 0 ||  resultValue > 5) {
			resultValue = 0;
			LOG.warn(SMS_CHANNEL_VENDOR + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 sms指定 channel的权重
	 *
	 * @return   0-100; default:100
	 */
	public static int getSmsChannelWeight() {
		// default value: 100
		int resultValue = 100;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_WEIGHT);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_WEIGHT + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_CHANNEL_WEIGHT + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :0-100
		if (resultValue < 0 &&  resultValue > 100) {
			resultValue = 100;
			LOG.warn(SMS_CHANNEL_WEIGHT + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 发送失败重试次数
	 *
	 * @return  0-10; default:3
	 */
	public static int getSmsChannelRetryNum() {
		// default value: 3
		int resultValue = 3;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_RETRY_NUM);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_RETRY_NUM + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_CHANNEL_RETRY_NUM + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :0-10
		if (resultValue < 0 &&  resultValue > 10) {
			resultValue = 3;
			LOG.warn(SMS_CHANNEL_WEIGHT + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 发送失败重试间隔
	 *
	 * @return   0-30秒; default:3秒
	 */
	public static int getSmsChannelRetryInterval() {
		// default value: 3
		int resultValue = 3;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_RETRY_INTERVAL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_RETRY_INTERVAL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_CHANNEL_RETRY_INTERVAL + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :0-30
		if (resultValue < 0 &&  resultValue > 30) {
			resultValue = 3;
			LOG.warn(SMS_CHANNEL_RETRY_INTERVAL + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 sms 发送失败时的报告文件
	 *
	 * @return 文件名
	 */
	public static String getSmsChannelFailReportFile() {
		// default value
		String resultValue = "/log/blossomlog/sms-fail.txt";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_FAIL_REPORT_FILE);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_FAIL_REPORT_FILE + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue;

		if( !resultValue.endsWith("/") && !resultValue.endsWith("\\")){
			// 补足后缀
			resultValue = resultValue + "/";
		}
		return resultValue;
	}

	/**
	 * 取得 数据导出Path
	 *
	 * @return 数据导出Path
	 */
	public static String getDataOutputPath() {
		// default value
		String resultValue = "/data/blossom/dataoutput/";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(DATA_OUTPU_PATH);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(DATA_OUTPU_PATH + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue;

		if( !resultValue.endsWith("/") && !resultValue.endsWith("\\")){
			// 补足后缀
			resultValue = resultValue + "/";
		}
		return resultValue;
	}

	/**
	 * 取得 数据导出HttpPath
	 *
	 * @return 数据导出HttpPath
	 */
	public static String getDataOutputHttpPath() {
		// default value
		String resultValue = "http://localhost:8080/dataoutput/";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(DATA_OUTPU_HTTP_PATH);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(DATA_OUTPU_HTTP_PATH + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue;

		if( !resultValue.endsWith("/") && !resultValue.endsWith("\\")){
			// 补足后缀
			resultValue = resultValue + "/";
		}

		return resultValue;
	}

	/**
	 *  短信发送状态报告取回 前置时间(default:60秒),范围:1秒-- 30*60秒(30分)
	 *
	 * @return 短信发送状态报告取回 前置时间
	 */
	public static int getSmsSendStatusFetchBefore() {
		// default value: 60秒
		int resultValue = 60;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_SEND_STATUS_FETCH_BEFORE);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_SEND_STATUS_FETCH_BEFORE + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_SEND_STATUS_FETCH_BEFORE + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1秒-- 30*60秒(30分)
		if (resultValue < 1 || resultValue >30 * 60) {
			resultValue = 60;
			LOG.warn(SMS_SEND_STATUS_FETCH_BEFORE + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 数据导出文件 最大保存天数
	 *
	 * @return 数据导出文件 最大保存天数
	 */
	public static int getDataOutputFileMaxDays() {
		// default value: 7天
		int resultValue = 7;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(DATA_OUTPUT_FILE_MAX_DAYS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(DATA_OUTPUT_FILE_MAX_DAYS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(DATA_OUTPUT_FILE_MAX_DAYS + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1天-- 5*365天(5年)
		if (resultValue < 1 || resultValue > 5 * 365) {
			resultValue = 7;
			LOG.warn(DATA_OUTPUT_FILE_MAX_DAYS + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 数据导出任务 最大保存天数
	 *
	 * @return 数据导出任务 最大保存天数
	 */
	public static int getDataOutputTaskMaxDays() {
		// default value: 90天
		int resultValue = 90;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(DATA_OUTPUT_TASK_MAX_DAYS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(DATA_OUTPUT_TASK_MAX_DAYS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(DATA_OUTPUT_TASK_MAX_DAYS + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1天-- 5*365天(5年)
		if (resultValue < 1 || resultValue > 5 * 365) {
			resultValue = 90;
			LOG.warn(DATA_OUTPUT_TASK_MAX_DAYS + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 短信发送任务 最大保存天数
	 *
	 * @return 短信发送任务 最大保存天数
	 */
	public static int getSmsSendTaskMaxDays() {
		// default value: 90天
		int resultValue = 90;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_SEND_TASK_MAX_DAYS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_SEND_TASK_MAX_DAYS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_SEND_TASK_MAX_DAYS + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1天-- 5*365天(5年)
		if (resultValue < 1 || resultValue > 5 * 365) {
			resultValue = 90;
			LOG.warn(SMS_SEND_TASK_MAX_DAYS + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 短信发送history 最大保存天数
	 *
	 * @return 短信发送history 最大保存天数
	 */
	public static int getSmsSendHistoryMaxDays() {
		// default value: 90天
		int resultValue = 90;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_SEND_HISTORY_MAX_DAYS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_SEND_HISTORY_MAX_DAYS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_SEND_HISTORY_MAX_DAYS + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1天-- 5*365天(5年)
		if (resultValue < 1 || resultValue > 5 * 365) {
			resultValue = 90;
			LOG.warn(SMS_SEND_HISTORY_MAX_DAYS + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 短信发送状态报告 最大保存天数
	 *
	 * @return 短信发送状态报告 最大保存天数
	 */
	public static int getSmsSendStatusReportMaxDays() {
		// default value: 90天
		int resultValue = 90;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_SEND_STATUS_REPORT_MAX_DAYS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_SEND_STATUS_REPORT_MAX_DAYS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(SMS_SEND_STATUS_REPORT_MAX_DAYS + "的值[" + strValue + "]不正确，将使用默认值[" + resultValue + "]");
		}

		// 有效范围 check :1天-- 5*365天(5年)
		if (resultValue < 1 || resultValue > 5 * 365) {
			resultValue = 90;
			LOG.warn(SMS_SEND_STATUS_REPORT_MAX_DAYS + "的值[" + resultValue + "]不正确，将使用默认值[" + resultValue + "]");
		}
		return resultValue;
	}

	/**
	 * 取得 机房主备状态更新后的timout(秒)
	 *
	 * @return   default:60
	 */
	public static int getHouseStatusTimeout() {
		ConfigManager configManager = ConfigManager.getInstance();

		String strValue = configManager.getProperty(HOUSE_STATUS_TIMEOUT);
		int resultValue = getIntValue(HOUSE_STATUS_TIMEOUT, strValue, 60);

		return resultValue;
	}

	/**
	 * 取得 int value
	 *
	 * @return IntValue
	 */
	public static int getIntValue(String strKey, String strValue, int defaultValue) {
		int resultValue = 0;

		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(strKey + "的值为空，将使用默认值[" + defaultValue + "]");
			return defaultValue;
		}

		// 数据类型 check
		try {
			resultValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			LOG.warn(strKey + "的值[" + strValue + "]不正确，将使用默认值[" + defaultValue + "]");
			return defaultValue;
		}

		// 有效范围
		if (resultValue < 0) {
			LOG.warn(strKey + "的值[" + resultValue + "]不正确，将使用默认值[" + defaultValue + "]");
			resultValue = defaultValue;
		}

		return resultValue;
	}

	/**
	 * 取得 Sms Channel Montnet url
	 *
	 * @return Sms Channel Montnet url
	 */
	public static String getSmsChannelMontnetUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MONTNET_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MONTNET_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel Montnet statusReport url
	 *
	 * @return Sms Channel Montnet url
	 */
	public static String getSmsChannelMontnetStatusReportUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MONTNET_STATUS_REPORT_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MONTNET_STATUS_REPORT_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel Montnet UserId
	 *
	 * @return Sms Channel Montnet UserId
	 */
	public static String getSmsChannelMontnetUserId() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MONTNET_USER_ID);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MONTNET_USER_ID + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel Montnet Password
	 *
	 * @return Sms Channel Montnet Password
	 */
	public static String getSmsChannelMontnetPassword() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MONTNET_PASSWORD);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MONTNET_PASSWORD + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();
		return resultValue;
	}

	/**
	 * 取得 Sms Channel UMS url
	 *
	 * @return Sms Channel UMS url
	 */
	public static String getSmsChannelUMSUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_UMS_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_UMS_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel UMS StatusReport url
	 *
	 * @return Sms Channel UMS StatusReport url
	 */
	public static String getSmsChannelUMSStatusReportUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_UMS_STATUS_REPORT_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_UMS_STATUS_REPORT_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}


	/**
	 * 取得 Sms Channel UMS spCode
	 *
	 * @return Sms Channel UMS spCode
	 */
	public static String getSmsChannelUMSspCode() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_UMS_SP_CODE);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_UMS_SP_CODE + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel UMS UserId
	 *
	 * @return Sms Channel UMS UserId
	 */
	public static String getSmsChannelUMSUserId() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_UMS_USER_ID);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_UMS_USER_ID + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 Sms Channel UMS Password
	 *
	 * @return Sms Channel UMS Password
	 */
	public static String getSmsChannelUMSPassword() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_UMS_PASSWORD);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_UMS_PASSWORD + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();
		return resultValue;
	}


	/**
	 * 银行入账单超时时间
	 *
	 * @return 银行入账单超时时间
	 */
	public static String getSuperBankInOverTime() {
		// default value
		String resultValue = "604800";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SUPER_BANK_IN_OVER_TIME);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SUPER_BANK_IN_OVER_TIME + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}


	/**
	 * 取得 AP 业务状态报告文件名
	 *
	 * @return AP 业务状态报告文件名
	 */
	public static String getApBusinessStatusReportFile() {
		// default value
		String resultValue = "/log/blossomlog/apBusinessStatusReport.txt";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(AP_BUSINESS_STATUS_REPORT_FILE);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(AP_BUSINESS_STATUS_REPORT_FILE + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 其它的状态管理的ap名
	 *
	 * @return 其它的状态管理的ap名
	 */
	public static String getApOtherNameList() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(AP_OTHER_NAME_LIST);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(AP_OTHER_NAME_LIST + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 验证码相关的短信，如果目标手机号是中国移动或中国电信，复制Enable
	 *
	 * @return 复制Enable
	 */
	public static int getSmsReplicationEnable() {
		ConfigManager configManager = ConfigManager.getInstance();

		String strValue = configManager.getProperty(SMS_REPLICATION_ENABLE);
		int resultValue = getIntValue(SMS_REPLICATION_ENABLE, strValue, 0);

		return resultValue;
	}

	/**
	 * 取得 验证码相关的短信，如果目标手机号是中国移动或中国电信，同时复制一份到特定手机号
	 *
	 * @return 特定手机号
	 */
	public static String getSmsReplicationTargetNo() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_REPLICATION_TARGET_NO);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_REPLICATION_TARGET_NO + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 短信猫 server url
	 *
	 * @return 短信猫 server url
	 */
	public static String getSmsChannelModemServerUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MODEM_SERVER_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MODEM_SERVER_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 短信猫B server url
	 *
	 * @return 短信猫B server url
	 */
	public static String getSmsChannelModemBServerUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MODEM_B_SERVER_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MODEM_B_SERVER_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 短信猫C server url
	 *
	 * @return 短信猫C server url
	 */
	public static String getSmsChannelModemCServerUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SMS_CHANNEL_MODEM_C_SERVER_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SMS_CHANNEL_MODEM_C_SERVER_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 指定短信Chanel的Enable
	 *
	 * @return 指定短信Chanel的Enable
	 */
	public static int getSmsChanelEnable(int chanelNo) {
		ConfigManager configManager = ConfigManager.getInstance();

		String strKey = "sms.channel." + chanelNo + ".enable" ;
		String strValue = configManager.getProperty(strKey);

		int resultValue = getIntValue(strKey, strValue, 0);

		return resultValue;
	}

	/**
	 * 取得 页面静态化 site urlPrefix
	 *
	 * @return 页面静态化 site urlPrefix
	 */
	public static String getPageStaticSiteUrlPrefix(int siteId) {
		// default value
		String resultValue = "http://localhost:8080";

		ConfigManager configManager = ConfigManager.getInstance();

		String strKey = "page.static.site." + siteId + ".urlPrefix" ;
		String strValue = configManager.getProperty(strKey);

		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(strKey + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}

		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}


	/**
	 * 网主登录次数过多账号锁定时间
	 *
	 * @return 网主登录次数过多账号锁定时间（分）
	 */
	public static String getMemberLoginLockTime() {
		// default value
		String resultValue = "30";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MEMBER_LOGIN_LOCK_TIME);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MEMBER_LOGIN_LOCK_TIME + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 允许登录，默认允许，在机房维护时可以临时设置为禁止
	 *
	 * @return 允许登录，默认允许，在机房维护时可以临时设置为禁止（0--禁止  1--允许）
	 */
	public static String getEnableMemberLogin() {
		// default value
		String resultValue = "1";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(ENABLE_MEMBER_LOGIN);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
//            LOG.warn(ENABLE_MEMBER_LOGIN + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		if(!"0".equals(strValue.trim())) {
			// 不为禁止，默认允许
			return resultValue;
		}

		return strValue.trim();
	}

	/**
	 * 取得 从库需要导出的dubbo service名列表
	 *
	 * @return 从库需要导出的dubbo service名列表
	 */
	public static String getSlaveExportedServiceNameList() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(SLAVE_EXPORTED_SERVICE_NAME_LIST);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(SLAVE_EXPORTED_SERVICE_NAME_LIST + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 cdn url
	 *
	 * @return cdn url
	 */
	public static String getPageStaticCDNUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(PAGE_STATIC_CDN_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(PAGE_STATIC_CDN_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 cdn userId
	 *
	 * @return cdn userId
	 */
	public static String getPageStaticCDNUserId() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(PAGE_STATIC_CDN_USER_ID);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(PAGE_STATIC_CDN_USER_ID + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 cdn Password
	 *
	 * @return cdn Password
	 */
	public static String getPageStaticCDNPassword() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(PAGE_STATIC_CDN_PASSWORD);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(PAGE_STATIC_CDN_PASSWORD + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得  MsgService的Enable
	 *
	 * @return MsgService的Enable
	 */
	public static int getMsgServiceEnable() {
		ConfigManager configManager = ConfigManager.getInstance();

		String strValue = configManager.getProperty(MSG_SERVICE_ENABLE);

		int resultValue = getIntValue(MSG_SERVICE_ENABLE, strValue, 0);

		return resultValue;
	}

	/**
	 * 取得 MsgService url
	 *
	 * @return MsgService url
	 */
	public static String getMsgServiceUrl() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MSG_SERVICE_URL);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MSG_SERVICE_URL + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 MsgService user
	 *
	 * @return MsgService user
	 */
	public static String getMsgServiceUser() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MSG_SERVICE_USER);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MSG_SERVICE_USER + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 MsgService Password
	 *
	 * @return MsgService Password
	 */
	public static String getMsgServicePassword() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MSG_SERVICE_PASSWORD);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MSG_SERVICE_PASSWORD + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 MsgService Receive Destination Queues
	 *
	 * @return MsgService Receive Destination Queues
	 */
	public static String getMsgServiceReceiveDestinationQueues() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MSG_SERVICE_RECEIVE_DESTINATION_QUEUES);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MSG_SERVICE_RECEIVE_DESTINATION_QUEUES + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得 MsgService Receive Destination Topics
	 *
	 * @return MsgService Receive Destination Topics
	 */
	public static String getMsgServiceReceiveDestinationTopics() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(MSG_SERVICE_RECEIVE_DESTINATION_TOPICS);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(MSG_SERVICE_RECEIVE_DESTINATION_TOPICS + "的值为空，将使用默认值[" + resultValue + "]");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得通信服务器IP
	 *
	 * @return 通信服务器IP
	 */
	public static String getChatServer() {
		// default value
		String resultValue = "";

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty("chatServer");
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn("chatServer" + "的值为空");
			return resultValue;
		}
		// 设定
		resultValue = strValue.trim();

		return resultValue;
	}

	/**
	 * 取得  MsgService的Enable
	 *
	 * @return MsgService的Enable
	 */
	public static boolean getDubboMasterFlag() {
		// default value
		boolean resultValue = true;

		ConfigManager configManager = ConfigManager.getInstance();
		String strValue = configManager.getProperty(DUBBO_MASTER_FLAG);
		// 空值 check
		if (strValue == null || strValue.trim().length() == 0) {
			LOG.warn(DUBBO_MASTER_FLAG + "的值为空");
			return resultValue;
		}

		// 设定
		if( strValue.trim().equalsIgnoreCase("false") ){
			resultValue = false;
		}

		return resultValue;
	}

	/**
	 * 取得 从库dataSource监视间隔（秒）
	 *
	 * @return   default:30
	 */
	public static int getSlaveDataSourceMonitorInterval() {
		ConfigManager configManager = ConfigManager.getInstance();

		String strValue = configManager.getProperty(SLAVE_DATASOURCE_MONITOR_INTERVAL);
		int resultValue = getIntValue(SLAVE_DATASOURCE_MONITOR_INTERVAL, strValue, 30);

		return resultValue;
	}

	/**
	 * 取得 从库dataSource监视最大故障次数
	 *
	 * @return  default:10
	 */
	public static int getSlaveDataSourceMonitorMaxFailureCount() {
		ConfigManager configManager = ConfigManager.getInstance();

		String strValue = configManager.getProperty(SLAVE_DATASOURCE_MONITOR_MAXFAILURECOUNT);
		int resultValue = getIntValue(SLAVE_DATASOURCE_MONITOR_MAXFAILURECOUNT, strValue, 10);

		return resultValue;
	}
}
