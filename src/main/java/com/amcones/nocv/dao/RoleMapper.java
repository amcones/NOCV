package com.amcones.nocv.dao;

import com.amcones.nocv.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select mid from role_menu where rid = #{roleId}")
    List<Integer> queryMidByRid(Integer roleId);

    @Delete("delete from role_menu where rid = #{rid}")
    void deleteRoleByRid(Integer rid);

    @Insert("insert into role_menu(rid,mid) values (#{rid},#{mid})")
    void saveRoleMenu(Integer rid, Integer mid);

    @Select("select rid from user_role where uid = #{id}")
    List<Integer> queryUserRoleById(Integer id);

    @Delete("delete from user_role where uid = #{uid}")
    void deleteRoleUserByUid(Integer uid);

    @Insert("insert into user_role(uid,rid) values(#{uid},#{rid})")
    void saveUserRole(Integer uid, Integer rid);
}
