package wise.wisewomenhackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.wisewomenhackathon.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUserId(Long userId);
    User save(User user);
    void deleteById(Long userId);
}
