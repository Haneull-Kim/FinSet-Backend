package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration

@PropertySource("classpath:/application.properties")

@MapperScan(basePackages = {"member.mapper"})
@ComponentScan(basePackages = {"member.service"})//
@Slf4j
@EnableTransactionManagement
public class RootConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Autowired
    ApplicationContext applicationContext;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();  // SqlSessionFactoryBean 객체 생성
        sqlSessionFactory.setConfigLocation(
                applicationContext.getResource("classpath:/mybatis-config.xml"));  // MyBatis 설정 파일 위치 설정
        sqlSessionFactory.setDataSource(dataSource());

        return sqlSessionFactory.getObject();
    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());  // DataSourceTransactionManager 객체 생성
        return manager;  // DataSourceTransactionManager 객체를 반환
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();


        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);


        HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }
}
