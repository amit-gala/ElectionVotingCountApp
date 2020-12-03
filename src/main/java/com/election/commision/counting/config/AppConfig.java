package com.election.commision.counting.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class AppConfig {
    @Value("${output.file}")
    String outFile;

    @Bean
    public BlockingQueue<String> getQueue(){
        return new LinkedBlockingQueue<>();
    }

    @Bean("mapper")
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean("map")
    public Map<String,Integer> getMap(){
//        return new ConcurrentHashMap<>();
        return Collections.synchronizedMap(new HashMap<>());
    }

    @Bean("fos")
    public FileOutputStream fos() throws FileNotFoundException {
        return new FileOutputStream(getOutputFile(),true);
    }

    @Bean
    public File getOutputFile() {
        return new File(outFile);
    }


}
