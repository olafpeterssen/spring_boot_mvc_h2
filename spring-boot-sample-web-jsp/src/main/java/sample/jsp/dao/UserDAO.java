package sample.jsp.dao;

import java.util.List;

import javax.transaction.Transactional;

import sample.jsp.model.User;

public interface UserDAO {

    void create(User user);

    void delete(Long id);

    List<User> read();

}