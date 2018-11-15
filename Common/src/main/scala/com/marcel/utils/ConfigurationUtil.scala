package com.marcel.utils

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder
import org.apache.commons.configuration2.builder.fluent.Parameters
import org.apache.commons.configuration2.{FileBasedConfiguration, PropertiesConfiguration}


/**
  * @Desc:
  * @Author: Mars9527
  * @Date: 2018-11-11
  */
object ConfigurationUtil {

  /*
  FileBasedConfigurationBuilder: 产生一个传入的类的实例对象
  FileBasedConfiguration: 融合FileBased与Configuration的接口
  PropertiesConfiguration: 从一个或者多个文件读取配置的标准配置加载器
  configure(): 通过params实例初始化配置生成器
  向FileBasedConfigurationBuilder() 中传入一个标准配置加载器类
  ， 生成一个加载器类的实例对象
  ， 然后通过params参数对其初始化

  需要导入依赖
  commons-configuration2 2.2
  commons-beanutils 1.9.3


  用法
  val config: FileBasedConfiguration = ConfigurationUtil("condition.properties").config
  val conditionJson: String = config.getString("condition.params.json")*/

  def apply(file:String): ConfigurationUtil = {
    val configurationUtil = new ConfigurationUtil()
    if (configurationUtil.config == null) {
      configurationUtil.config = new FileBasedConfigurationBuilder[FileBasedConfiguration](classOf[PropertiesConfiguration])
        .configure(new Parameters().properties().setFileName(file)).getConfiguration
    }
    configurationUtil
  }
/**
 * desc:测试
 * @param:[args]
 * @return:void
 * @author:Mars9527
 * @date:2018/11/11
 */
  def main(args: Array[String]): Unit = {
    val config: FileBasedConfiguration = ConfigurationUtil("config.properties").config
    val database: String = config.getString("hive.database")
    println(database)

    val config1: FileBasedConfiguration = ConfigurationUtil("condition.properties").config
    val conditionJson: String = config1.getString("condition.params.json")
    val conditionJsonObj: JSONObject = JSON.parseObject(conditionJson)
    println(conditionJsonObj)
  }

}

class ConfigurationUtil{

  var config: FileBasedConfiguration = null

}
