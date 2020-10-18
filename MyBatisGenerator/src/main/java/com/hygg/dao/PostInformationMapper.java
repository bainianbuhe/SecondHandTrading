package com.hygg.dao;

import com.hygg.entity.PostInformation;
import com.hygg.entity.PostInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int countByExample(PostInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int deleteByExample(PostInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int insert(PostInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int insertSelective(PostInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    List<PostInformation> selectByExample(PostInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    PostInformation selectByPrimaryKey(Integer postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PostInformation record, @Param("example") PostInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PostInformation record, @Param("example") PostInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PostInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PostInformation record);
}