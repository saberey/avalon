package test;

import java.util.Iterator;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateTest {
	
	private ApplicationContext ac;
	private RedisTemplate template;
	
	public void init(){
		 ac = new ClassPathXmlApplicationContext("classpath:config/redis.xml");
	     template = (RedisTemplate) ac.getBean("redisTemplate");
	}
	
	@SuppressWarnings("unchecked")
	public void templateTest(){
		template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
            	connection.select(1);
            	byte[] key = template.getStringSerializer().serialize("*");
            	Set keys = connection.keys(key);
            	//Set keys = template.keys('*');
            	Iterator it = keys.iterator();
            	while(it.hasNext()){
            		System.out.println(template.getStringSerializer().deserialize((byte[]) it.next()));
            		//System.out.println(it.next());
            	}
            	return null;
            }
        });
	}
	
	
	public static void main(String[] args) {
		RedisTemplateTest rts = new RedisTemplateTest();
		rts.init();
		rts.templateTest();
	}
}
