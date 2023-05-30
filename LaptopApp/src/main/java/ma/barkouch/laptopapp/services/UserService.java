package ma.barkouch.laptopapp.services;

import ma.barkouch.laptopapp.entities.User;
import ma.barkouch.laptopapp.model.Dao;
import ma.barkouch.laptopapp.model.impl.UserDaoImpl;
import java.util.List;

public class UserService {
    final private Dao<User,String> userDao = new UserDaoImpl();

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void insert(User user){
        userDao.insert(user);
    }

    public User findByUsername(String username){
        return userDao.findById(username);
    }

    public boolean exists(User user){
        return userDao.exist(user);
    }
}
