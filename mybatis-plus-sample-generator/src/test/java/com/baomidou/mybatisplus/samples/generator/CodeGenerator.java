package com.baomidou.mybatisplus.samples.generator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * mybatis plus 的代码生成功能，附带全套自定义模板
 * @author lizhongbin
 * @date  2020-11-27
 */
public class CodeGenerator {

    // 作者
    private static String AUTHOR = "lizhongbin";
    // 生成的实体类忽略表前缀: 不需要则置空
    private static String ENTITY_IGNORE_PREFIX = "";
    // 表名数组
    private static String[] TABLES ="daily_abs,daily_abs_second,daily_cd,daily_cd_credit_profit_gap,daily_cd_profit_gap,daily_comment,daily_drzp,daily_drzp_base_profit_gap,daily_drzp_credit_profit_gap,daily_drzp_profit_gap,daily_jrz,daily_llz,daily_llz_region,daily_llz_term_profit_gap"
            .split(",");

    // 数据库
    private static String DB_URL = "jdbc:mysql://10.60.160.30:3306/shggs?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static DbType DB_TYPE = DbType.MYSQL;
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_SCHEMA = "shggs";
    private static String DB_USER_NAME = "root";
    private static String DB_PASSWORD = "sg123456";

    // 输出路径
    private static String OUTPUT_PATH = "C:\\working\\idea project\\gushou-data-api\\src\\main";


    // 父包名
    private static String PARENT_PACKAGE         = "com.shgsec.gushoudata_api.daily";
    // 下边是分类包名
    // 输出包名 = 父包名 + "." + 分类包名
    private static String CONTROLLER_PACKAGE     = "controller";
    private static String SERVICE_PACKAGE        = "service";
    private static String SERVICE_IMPL_PACKAGE   = "service.impl";
    private static String MAPPER_PACKAGE         = "dao";
    private static String ENTITY_PACKAGE         = "bean";
    private static String C_SHARP_ENTITY_PACKAGE = "DailyReport";

    // mapper存放路径 = OUTPUT_PATH + MAPPER_XML_PACKAGE
    private static String MAPPER_XML_PACKAGE     = "/resources/mapper/daily";

    // 模板路径
    private static JSONObject templates = new JSONObject();

    // 注释掉模板表示不生成该类模板
    static{
//        templates.put("CONTROLLER_TEMPLATE", "templates/controller.java.ftl");
        templates.put("SERVICE_TEMPLATE", "templates/service.java.ftl");
        templates.put("SERVICE_IMPL_TEMPLATE", "templates/serviceImpl.java.ftl");
        templates.put("ENTITY_TEMPLATE", "templates/entity.java.ftl");
        templates.put("MAPPER_TEMPLATE", "templates/mapper.java.ftl");
        templates.put("MAPPER_XML_TEMPLATE", "templates/mapper.xml.ftl");
//        templates.put("C_SHARP_ENTITY_TEMPLATE", "templates/C#entity.cs.ftl");
    }

    // 生成的实体类尾缀  例如: UserEntity
    private static String Entity_SUFFIX = "Entity";

    @Test
    public  void main(){

        // 全局配置
        GlobalConfig globalConfig = globalConfig();

        // 数据库连接配置
        DataSourceConfig dataSourceConfig = dataSourceConfig();

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig();

        // 包配置
        PackageConfig packageConfig = packageConfig();

        // 模板配置
        TemplateConfig templateConfig = templateConfig();


        // 自定义配置
        InjectionConfig injectionConfig = injectionConfig();

        // 执行
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                // 因为使用了自定义模板,所以需要把各项置空否则会多生成一次
                .setTemplate(templateConfig)
                // 使用的模板引擎，如果不是默认模板引擎则需要添加模板依赖到pom,现在用的是freemarker，需要导包
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setCfg(injectionConfig)
                .execute();

        // 用于查看配置中都有什么，方便写ftl模板时候自己去找配置，写到ftl中
//        System.out.println("================= 配置信息 =======================");
//        System.out.println(JSON.toJSONString(autoGenerator.getConfig(), SerializerFeature.PrettyFormat));

//        List<TableInfo> list = autoGenerator.getConfig().getTableInfoList();
//        for (TableInfo t:list) {
//            System.out.println("================= 表格信息 =======================");
//            System.out.println(JSON.toJSONString(t,SerializerFeature.PrettyFormat));
//        }
    }


    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                // 打开文件
                .setOpen(false)
                // 文件覆盖
                .setFileOverride(true)
                // 开启activeRecord模式，开启之后就可以直接使用entity对数据库进行操作
                .setActiveRecord(true)
                // XML ResultMap: mapper.xml生成查询映射结果
                .setBaseResultMap(true)
                // XML ColumnList: mapper.xml生成查询结果列
                .setBaseColumnList(true)
                // swagger注解; 须添加swagger依赖
                .setSwagger2(true)
                // 作者
                .setAuthor(AUTHOR)
                // 设置实体类后缀名称
                .setEntityName("%s"+Entity_SUFFIX);
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {

        // 数据源配置
        return  new DataSourceConfig()
         // 地址
        .setUrl(DB_URL)
         // 数据库名
        .setSchemaName(DB_SCHEMA)
         // 驱动
        .setDriverName(DB_DRIVER)
         // 用户名
        .setUsername(DB_USER_NAME)
         // 密码
        .setPassword(DB_PASSWORD)
         // 数据库类型
        .setDbType(DB_TYPE);
    }

    /**
     * 策略配置
     */
    private static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                // 表名生成策略：下划线连转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表字段生成策略：下划线连转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(TABLES)
                // 生成controller
                .setRestControllerStyle(true)
                // 去除表前缀
                .setTablePrefix(ENTITY_IGNORE_PREFIX)
                // controller映射地址：驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // 是否为lombok模型; 需要lombok依赖
                .setEntityLombokModel(true)
                // 生成实体类字段注解
                .setEntityTableFieldAnnotationEnable(true);
    }

    /**
     * 包配置
     * 设置包路径用于导包时使用，路径示例：com.path
     */
    private static PackageConfig packageConfig() {

        return new PackageConfig()
                // 如果下面包没写全，还有前缀那就加上，如果包写全了就不加了
                .setParent(PARENT_PACKAGE)
                .setController(CONTROLLER_PACKAGE)
                .setService(SERVICE_PACKAGE)
                .setServiceImpl(SERVICE_IMPL_PACKAGE)
                .setEntity(ENTITY_PACKAGE)
                .setMapper(MAPPER_PACKAGE)
                .setXml(MAPPER_PACKAGE);
    }

    /**
     * 模板配置
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig()
                // 置空后方便使用自定义输出位置
                .setEntity(null)
                .setXml(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig injectionConfig() {

        // 自定义日期和日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String dateString = format.format(new Date());
        Map<String,Object> userDefiedData = new HashMap<>();
        userDefiedData.put("createTime", dateString);
        userDefiedData.put("schema", DB_SCHEMA);


        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入配置
            }
        }
                // 判断是否创建文件
                .setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        // 检查文件目录，不存在自动递归创建
                        createFile(filePath);

                        // 指定需要覆盖的文件, 文件不存在就创建，否则只覆盖Dao,Mapper.xml,Mapper.java,Model.cs
                        if (isExists(filePath)
                                && (!filePath.endsWith("Mapper.xml")
                                && !filePath.endsWith(Entity_SUFFIX+".java")
                                && !filePath.endsWith("Mapper.java")
                                && !filePath.endsWith("Model.cs")
                        )) {
                            return false;
                        }

                        return true;
                    }
                })
                // 自定义输出文件
                .setFileOutConfigList(fileOutConfigList())
                // 自定义参数使用cfg.xxx来调用
                .setMap(userDefiedData);
    }

    /**
     * 自定义输出文件配置
     */
    private static List<FileOutConfig> fileOutConfigList() {
        List<FileOutConfig> list = new ArrayList<>();
        String CONTROLLER_TEMPLATE = templates.getString("CONTROLLER_TEMPLATE");
        String SERVICE_TEMPLATE  =  templates.getString("SERVICE_TEMPLATE");
        String SERVICE_IMPL_TEMPLATE  = templates.getString("SERVICE_IMPL_TEMPLATE");
        String ENTITY_TEMPLATE  = templates.getString("ENTITY_TEMPLATE");
        String MAPPER_TEMPLATE = templates.getString("MAPPER_TEMPLATE");
        String MAPPER_XML_TEMPLATE  = templates.getString("MAPPER_XML_TEMPLATE");
        String C_SHARP_ENTITY_TEMPLATE = templates.getString("C_SHARP_ENTITY_TEMPLATE");
        // 实体类文件输出
        list.add(new FileOutConfig(ENTITY_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,ENTITY_PACKAGE) +"/"+ tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        // C#实体类文件输出
        list.add(new FileOutConfig(C_SHARP_ENTITY_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,C_SHARP_ENTITY_PACKAGE) +"/"+ tableInfo.getEntityName() + ".cs";
            }
        });

        // mapper xml文件输出
        list.add(new FileOutConfig(MAPPER_XML_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + replaceDot("",MAPPER_XML_PACKAGE) +"/"+ tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });
        // mapper文件输出
        list.add(new FileOutConfig(MAPPER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,MAPPER_PACKAGE) +"/"+ tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        // service文件输出
        list.add(new FileOutConfig(SERVICE_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,SERVICE_PACKAGE) +"/"+ tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        // service impl文件输出
        list.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,SERVICE_IMPL_PACKAGE)+"/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        // controller文件输出
        list.add(new FileOutConfig(CONTROLLER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_PATH + "/java/" + replaceDot(PARENT_PACKAGE,CONTROLLER_PACKAGE)+"/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });

        return list;
    }

    /**
     * 判断文件是否存在
     * @param path 路径
     * @return
     */
    private static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 将.替换成为文件分隔符
     * */
    private static String replaceDot(String parent,String value){
        return (parent+"."+value).replaceAll("\\.","/");
    }

    /**
     * 自动创建
     */
    private static void  createFile(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            File folder = file.getParentFile();
            if(!folder.exists()){
                folder.mkdirs();
            }
            file = new File(filePath);
        }
    }
}
