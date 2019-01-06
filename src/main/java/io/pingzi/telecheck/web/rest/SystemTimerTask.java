package io.pingzi.telecheck.web.rest;

import io.pingzi.telecheck.domain.Tasklist;
import io.pingzi.telecheck.domain.UserInfo;
import io.pingzi.telecheck.repository.TasklistRepository;
import io.pingzi.telecheck.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
public class SystemTimerTask extends Thread{
   /* @Autowired
    private TasklistRepository tasklistRepository;
    @Autowired
        private UserInfoRepository userInfoRepository;
*//*    Thread t2 = new Thread() {*//*
        @Override
        public void run() {
            System.out.println("线程2启动");
            try {
                Thread.sleep(300);
                List<Tasklist> list = tasklistRepository.findAll();
                System.out.println(list.size() + "条");
                String phone;
                for (Tasklist tasklists : list) {
                    if (tasklists.getState() == 0) {
                        System.out.println("执行线程2业务");
                        phone = tasklists.getPhone();
                        System.out.println(phone + "是多少");
                        List<UserInfo> lists = userInfoRepository.findByPhone(phone);
                        UserInfo Type = new UserInfo();
                        System.err.print(lists.get(0).getPhone() + "手机号");
                        Type.setUserid(lists.get(0).getUserid());
                        Type.setPhone(lists.get(0).getPhone());
                        Type.setIsregister(lists.get(0).getIsregister());
                        Type.setStatus(lists.get(0).getStatus());
                        Type.setLogintime(lists.get(0).getLogintime());
                        Type.setUsername(lists.get(0).getUsername());
                        Type.setFirstname(lists.get(0).getFirstname());
                        Type.setLastname(lists.get(0).getLastname());
                        Type.setIsimage(lists.get(0).getIsimage());
                        Type.setRemark(lists.get(0).getRemark());
                        Type.setPortraitContentType(lists.get(0).getPortraitContentType());
                        System.out.println("保存");
                        userInfoRepository.save(Type);
                                    *//* Tasklist tasklist=new Tasklist();
                                   tasklist.setTid(tasklists.getTid());
                                    tasklist.setResult("1");
                                    tasklist.setState(1);
                                    System.out.println("修改");
                                    tasklistrepository.update(tasklist);*//*

                    }
                }
                System.out.println("线程2完成任务退出");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }*/
}
