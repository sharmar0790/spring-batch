package spring.batch.boot.example.services.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import spring.batch.boot.example.model.User;

import java.util.HashMap;
import java.util.Map;



@Component
public class UserProcessor implements ItemProcessor<User, User> {

    public static Logger LOG = LogManager.getLogger(UserProcessor.class);

    private static Map<String,String> DEPT_NAMES=new HashMap<>(){
        {
            put("001","ABC");
            put("002","DEF");
            put("003","FGH");
        }
    };


    @Override
    public User process(User u) throws Exception {
        LOG.info("Converted the value of dept from {} to {}",u.getDept(),DEPT_NAMES.get(u.getDept()));
        u.setDept(DEPT_NAMES.get(u.getDept()));
        return u;
    }
}
