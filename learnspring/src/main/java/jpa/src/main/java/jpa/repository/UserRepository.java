package jpa.repository;

import jpa.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public  interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

    List<User> findByName(String name, Sort sort);

    List<User> findByName(String name, Pageable paging);

    List<User> findByUser5();

    @Transactional(timeout = 2, propagation = Propagation.REQUIRED)
    @Query("SELECT u FROM User u WHERE u.name = 'zzh2'")
    List<User> findByGivenQuery();

    List<User> findByIdAndName(@Param("id") String id, @Param("name") String name);
}
