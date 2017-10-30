package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import spring.entity.User;


/*@Mapper*/
@Repository
public interface UserDao {
     public void save(User user);
     
     /*@Select("select id,name,password,age,time from t_user")*/
     public List<User> findUserById();
}
