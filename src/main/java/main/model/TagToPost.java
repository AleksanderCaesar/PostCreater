package main.model;

import javax.persistence.*;

@Entity
@Table(name = "tag2post")
public class TagToPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts postId;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tagId;

    public TagToPost() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public Tags getTagId() {
        return tagId;
    }

    public void setTagId(Tags tagId) {
        this.tagId = tagId;
    }
}
