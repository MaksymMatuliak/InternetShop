package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {

    Optional<User> findByUsername(String username) throws DataProcessingException;
}
