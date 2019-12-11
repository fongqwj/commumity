package qwj.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qwj.community.community.model.User;

/**
 * @Project
 * @Title UserMapper
 * @Description
 * @Author qwj
 * @Date 2019年12月02日 14:45
 */
@Mapper
public interface UserMapper {

    @Select("SELECT name FROM user WHERE id = #{id}")
    String getUserName(@Param("id")int id);

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")int id);
}
