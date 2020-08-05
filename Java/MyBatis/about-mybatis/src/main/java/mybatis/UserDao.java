package mybatis;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(mybatis.User record);

    int insertSelective(mybatis.User record);

    mybatis.User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(mybatis.User record);

    int updateByPrimaryKey(mybatis.User record);
}