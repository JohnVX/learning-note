package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class RunMyBatis {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"), "mysqlID");
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mybatis.UserDao userDao = sqlSession.getMapper(mybatis.UserDao.class);

        User user = new User("shuwei", "idje231edfd232");
        System.out.println(userDao.insert(user));
        System.out.println(userDao.selectByPrimaryKey(user.getId()));
    }
}
