package io.pingzi.telecheck.repository;

import io.pingzi.telecheck.domain.Tasklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TasklistRepository extends JpaRepository<Tasklist,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update task_list user set user.state =?1,user.result =?2 where user.phone =?3",nativeQuery = true)
    void update(int state,int result,String phone);


}
