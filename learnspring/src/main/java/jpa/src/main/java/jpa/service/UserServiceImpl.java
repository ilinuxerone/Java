package jpa.service;

import jpa.domain.User;
import jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;//注入UserRepository

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    @Transactional(readOnly=true)
    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.delete(id);
    }

}