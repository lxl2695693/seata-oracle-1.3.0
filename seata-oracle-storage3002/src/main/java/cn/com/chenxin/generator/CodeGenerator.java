package cn.com.chenxin.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

@Component
@PropertySource(value = {"classpath:config.properties"})
public class CodeGenerator {
    /**
     * 读取控制台内容
     * @param tip
     * @return
     */
    public static  String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ":  ");
        System.out.println(help.toString());
        if (scanner.hasNext()){
            String next = scanner.next();
            if (StringUtils.isNotBlank(next)){
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "  !");
    }

    public static void main(String[] args) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("config.properties");
        String projectPath = properties.getProperty("file.path");
        String driver = properties.getProperty("datasource.driver-class-name");
        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");;
        String password = properties.getProperty("datasource.password");;
        //代码生成器
        AutoGenerator generator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lxl");
        gc.setOpen(false);
        generator.setGlobalConfig(gc);
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDriverName(driver);
        generator.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("cn.com.chenxin");
        generator.setPackageInfo(pc);

        //模板引擎设置
        String templatePath = "/templates/mapper.xml.ftl";
        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        //自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输出文件名，如果你Entity设置了前后缀、此处注意xml的名称会跟着发生变化
                /*return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;*/
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir(filePath);
                if (fileType == FileType.MAPPER){
                    return !new File(filePath).exists();
                }

                return true;
            }
        });*/
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setSuperEntityClass("自己的父类实体，没有就不用设置");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        //公共父类
//        strategyConfig.setSuperControllerClass("自己的父类控制器，没有就不用设置");
        //写于父类中的公共字段
//        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分隔").split(","));
//        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix(pc.getModuleName() + "_");
        generator.setStrategy(strategyConfig);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
