package com.springboot.practice.Bean;


public class AdminUser {
    private Integer adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Byte locked;

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName == null ? null:loginUserName.trim();
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null?null:loginPassword.trim();
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null? null:nickName.trim();
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public Byte getLocked() {
        return locked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminUserId=").append(adminUserId);
        sb.append(", loginUserName=").append(loginUserName);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", nickName=").append(nickName);
        sb.append(", locked=").append(locked);
        sb.append("]");
        return sb.toString();
    }
}
