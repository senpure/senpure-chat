package com.senpure.chat.data;


import com.senpure.base.generator.CodeGenerator;
import com.senpure.base.generator.GeneratorConfig;
import com.senpure.base.generator.ModelConfig;


/**
 * DataCodeGenerator
 *
 * @author senpure
 * @time 2019-01-28 15:52:23
 */
public class DataCodeGenerator {

    public static void main(String[] args) {

        GeneratorConfig config = new GeneratorConfig();
        String path="../senpure-chat-bean/src/main/java";
        config.setModelRootPath(path);
        config.setCriteriaRootPath(path);
        config.setResultRootPath(path);
        config.setGeneratePermission(false);
        config.setGenerateMenu(false);
        ModelConfig modelConfig = new ModelConfig();

        modelConfig.setCoverModel(true);
        modelConfig.setCoverResult(true);
        modelConfig.setCoverCriteria(true);
        modelConfig.setCoverMapperXml(true);
        config.setModelConfig("User", modelConfig);

        CodeGenerator generator = new CodeGenerator();

        generator.generate("com.senpure.chat.data",config);
    }
}
