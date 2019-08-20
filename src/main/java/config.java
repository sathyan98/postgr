import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
class config
{


    @Bean(name = "dvdrental")
    @ConfigurationProperties(prefix = "datasource.dsdvdrental")
    public DataSource userDbMaria() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dvdrental") DataSource dvdrental) {

        return new JdbcTemplate(dvdrental);
    }
}