package tn.seif.steedex.Models;

public class UserToken {

    public int id;

    public String token;

    public String userId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserToken(int id, String token, String userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
