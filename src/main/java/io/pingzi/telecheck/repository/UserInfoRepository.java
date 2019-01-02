package io.pingzi.telecheck.repository;

import io.pingzi.telecheck.domain.UserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
List <UserInfo>findByPhone(String phone);
    @Modifying(clearAutomatically = true)
@Query("update UserInfo user set user.isregister =:#{#users.isregister},user.status =:#{#users.status},user.logintime =:#{#users.logintime},user.username =:#{#users.username},user.firstname =:#{#users.firstname},user.lastname =:#{#users.lastname},user.isimage =:#{#users.isimage},user.remark =:#{#users.remark},user.portrait =:#{#users.portrait} where user.phone =:#{#users.phone}")
void update(@Param("users") UserInfo users);

}
