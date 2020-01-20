package qwj.community.community.mapper;

import org.apache.ibatis.annotations.*;
import qwj.community.community.model.Question;

import java.util.List;

/**
 * @Project
 * @Title QuestionMapper
 * @Description
 * @Author qwj
 * @Date 2019年12月03日 20:50
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);

    @Select("select * from question ORDER BY gmt_modified DESC limit #{offset},#{size}")
    List<Question> getList(@Param(value = "offset") int offset,@Param(value = "size") int size);

    @Select("select count(id) from question")
    Integer count();

    @Select("select count(id) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);

    @Select("select * from question where creator = #{userId} ORDER BY gmt_modified DESC limit #{offset},#{size}")
    List<Question> getListByUserId(@Param(value = "userId")Integer userId, @Param(value = "offset") int offset,@Param(value = "size") int size);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("UPDATE question SET title=#{title}, description=#{description}, tag=#{tag} WHERE id=#{id};")
    void update(Question question);
}
