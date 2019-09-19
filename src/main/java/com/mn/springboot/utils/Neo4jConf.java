package com.mn.springboot.utils;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * neo4j配置
 */
@Configuration
@ConfigurationProperties(prefix = "neo4j")
public class Neo4jConf {
    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean(name = "driver")
    public Driver initDriver() {
        Driver driver;
        try {
            driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
