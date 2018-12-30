package io.pingzi.telecheck.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserInfo.
 */
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "isregister")
    private String isregister;

    @Column(name = "status")
    private String status;

    @Column(name = "logintime")
    private String logintime;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "isimage")
    private String isimage;

    @Column(name = "remark")
    private String remark;

    @Lob
    @Column(name = "portrait")
    private byte[] portrait;

    @Column(name = "portrait_content_type")
    private String portraitContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public UserInfo userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsregister() {
        return isregister;
    }

    public UserInfo isregister(String isregister) {
        this.isregister = isregister;
        return this;
    }

    public void setIsregister(String isregister) {
        this.isregister = isregister;
    }

    public String getStatus() {
        return status;
    }

    public UserInfo status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogintime() {
        return logintime;
    }

    public UserInfo logintime(String logintime) {
        this.logintime = logintime;
        return this;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserInfo firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public UserInfo lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIsimage() {
        return isimage;
    }

    public UserInfo isimage(String isimage) {
        this.isimage = isimage;
        return this;
    }

    public void setIsimage(String isimage) {
        this.isimage = isimage;
    }

    public String getRemark() {
        return remark;
    }

    public UserInfo remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public UserInfo portrait(byte[] portrait) {
        this.portrait = portrait;
        return this;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public String getPortraitContentType() {
        return portraitContentType;
    }

    public UserInfo portraitContentType(String portraitContentType) {
        this.portraitContentType = portraitContentType;
        return this;
    }

    public void setPortraitContentType(String portraitContentType) {
        this.portraitContentType = portraitContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInfo userInfo = (UserInfo) o;
        if (userInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", userid=" + getUserid() +
            ", phone='" + getPhone() + "'" +
            ", isregister='" + getIsregister() + "'" +
            ", status='" + getStatus() + "'" +
            ", logintime='" + getLogintime() + "'" +
            ", username='" + getUsername() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", isimage='" + getIsimage() + "'" +
            ", remark='" + getRemark() + "'" +
            ", portrait='" + getPortrait() + "'" +
            ", portraitContentType='" + getPortraitContentType() + "'" +
            "}";
    }
}
