package spring.batch.boot.example.services.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.batch.boot.example.model.User;
import spring.batch.boot.example.repository.UserRepository;

import java.util.List;

@Component
public class UserWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends User> list) throws Exception {
        System.out.println("i am here in writer");
        for(User u : list) {
            System.out.println("the u iam printing "+u);
            userRepository.save(u);
        }
    }
}
