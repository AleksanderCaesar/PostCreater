package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_comments")
public class PostComments
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "parent_id")
    private Integer parentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Posts postId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private Date time;
    private String title;

    public PostComments() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
