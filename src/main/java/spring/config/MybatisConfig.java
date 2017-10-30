package spring.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="spring.dao")
/*@PropertySource("classpath:application.properties")*/
public class MybatisConfig implements TransactionManagementConfigurer {
	/*@Value("${jdbc.driver}")
    private String driver;
	@Value("${jdbc.url}")
    private String url;
	@Value("${jdbc.username}")
    private String username;
	@Value("${jdbc.password}")
    private String password;
	@Value("${jdbc.maxActive}")
	private int maxActive;
	@Value("${jdbc.minIdel}")
	private int minIdel;
	@Value("${jdbc.maxWait}")
	private long maxWait;*/
	@Autowired
    private Environment env;
	@Bean
	public DruidDataSource dataSource(){
		DruidDataSource dataSource=new DruidDataSource();
		/*dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setMinIdle(minIdel);*/
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		return dataSource;
	}
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		 return new DataSourceTransactionManager(dataSource());
	}
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sfb=new SqlSessionFactoryBean();
		/*sfb.setDataSource(dataSource());
		sfb.setTypeAliasesPackage("spring.entity");
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Dao.xml"));*/
		sfb.setDataSource(dataSource());
		//下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		sfb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
		try{
			return sfb.getObject();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
