package spring.batch.boot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.batch.boot.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
