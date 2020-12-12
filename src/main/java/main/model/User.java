package main.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "is_moderator")
    private int isModerator;
    @Column(name = "reg_time")
    private Date regTime;
    private String name;
    private String email;
    private String password;
    private String code;
    private String photo;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Posts> posts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PostVotes> postVotes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PostComments> postComments;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(int isModerator) {
        this.isModerator = isModerator;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Set<Posts> posts) {
        this.posts = posts;
    }

    public Set<PostVotes> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(Set<PostVotes> postVotes) {
        this.postVotes = postVotes;
    }
}
