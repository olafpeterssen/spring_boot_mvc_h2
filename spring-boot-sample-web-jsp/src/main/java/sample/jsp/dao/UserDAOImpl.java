package sample.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.jsp.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    /* (non-Javadoc)
     * @see sample.jsp.dao.UserDAO#create(sample.jsp.model.User)
     */
    @Override
    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    /* (non-Javadoc)
     * @see sample.jsp.dao.UserDAO#delete(java.lang.Long)
     */
    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("delete from User u where u.id = :id").setParameter("id", id).executeUpdate();
    }

    /* (non-Javadoc)
     * @see sample.jsp.dao.UserDAO#read()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> read() {
        List<User> users = entityManager.createQuery("select u from User u").getResultList();
        return users;
    }

}
