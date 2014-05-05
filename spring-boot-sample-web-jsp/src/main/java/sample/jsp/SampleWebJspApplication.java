/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.jsp;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.h2.Driver;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebJspApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
	    ApplicationContext ctx = SpringApplication.run(SampleWebJspApplication.class, args);
	    //printContext(ctx);

	}

    private static void printContext(ApplicationContext ctx) {
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean(name = "dataSource")
    @DependsOn("h2Server")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        return dataSource;
    }

    @Bean(name="h2Server")
    public Server h2Server() throws SQLException {
        Server server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        return server;
    }

    @Bean(name="h2WebServer")
    @DependsOn("h2Server")
    public Server h2WebServer() throws SQLException {
        Server webServer = Server.createWebServer("-webAllowOthers","-webPort","8082").start();
        return webServer;
    }




}
