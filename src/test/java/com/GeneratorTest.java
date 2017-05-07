package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GeneratorTest {

    private GeneratorFacade generator;

    private static final String TEMPLATE = "template";

    @Before
    public void setUp() throws Exception {
        generator = new GeneratorFacade();
        generator.deleteOutRootDir();
    }

    @After
    public void tearDown() throws Exception {
        generator = null;
        String outRoot = GeneratorProperties.getRequiredProperty("outRoot");
        outRoot += "/java_src/";
        String basePackage = GeneratorProperties.getRequiredProperty("basepackage").replace(".", "/");
        Runtime.getRuntime().exec("cmd.exe /c start " + outRoot + basePackage);
    }

    /**
     * 生成单表, 参数为表名
     * 
     * @throws Exception 异常
     */
    @Test
    public void testSingleTableGenerator() throws Exception {
        String tables = "performance_plan_monitored";
        String moduleName = "";
        generator.generateByTable(tables, moduleName, TEMPLATE);
    }

    /**
     * 生成多表, 参数为多个表名
     * 
     * @throws Exception 异常
     */
    @Test
    public void testMultipleTableGenerator() throws Exception {
        String[] tables = new String[] {"endpoint_config", "open_app", "open_partner", "partner_app"};
        String moduleName = "gateway";
        generator.generateByTables(tables, moduleName, TEMPLATE);
    }

    /**
     * 生成全表
     * 
     * @throws Exception 异常
     */
    @Test
    public void testFullTableGenerator() throws Exception {
        generator.generateByAllTable(TEMPLATE);
    }

//    public static void main(String[] args) {
//        System.out.println(Math.random() * 10);
//    }

}
