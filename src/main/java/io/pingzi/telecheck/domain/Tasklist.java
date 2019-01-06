package io.pingzi.telecheck.domain;

import javax.persistence.*;

@Entity
@Table(name="task_list")

public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @Column(name = "phone")
    private String phone;
    @Column(name = "state")
    private int state;
    @Column(name = "result")
    private int result;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
