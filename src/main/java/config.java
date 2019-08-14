import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class config {
    @Configuration
    @ComponentScan
    public class DatabaseConfig {

        @Bean(name = "localDb")
        @Primary
        @ConfigurationProperties(prefix = "datasource.ds_users")
        public DataSource userDbMaria() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "mariaJdbcTemplate")
        @Primary
        public JdbcTemplate jdbcTemplate(@Qualifier("localDb") DataSource localDb) {
            return new JdbcTemplate(localDb);
        }

        @Bean(name = "cepheidDb")
        @ConfigurationProperties(prefix = "datasource.ds_cepheid")
        public DataSource mySqlDataSource() {
            return  DataSourceBuilder.create().build();
        }

        @Bean(name = "sqlJdbcTemplate")
        public JdbcTemplate mySqlJdbcTemplate(@Qualifier("cepheidDb") DataSource cepheidDb) {
            return new JdbcTemplate(cepheidDb);
        }

        @Bean(name = "cepheidDb1")
        @ConfigurationProperties(prefix = "datasource.ds_materialTracking")
        public DataSource mySqlDataSource1() {
            return  DataSourceBuilder.create().build();
        }

        @Bean(name = "sqlJdbcTemplate1")
        public JdbcTemplate mySqlJdbcTemplate1(@Qualifier("cepheidDb1") DataSource cepheidDb1) {
            return new JdbcTemplate(cepheidDb1);
        }
    }

}
