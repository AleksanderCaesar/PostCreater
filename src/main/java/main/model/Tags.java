package main.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tags
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "tagId")
    private Set<TagToPost> tagToPosts;

    public Tags() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TagToPost> getTagToPosts() {
        return tagToPosts;
    }

    public void setTagToPosts(Set<TagToPost> tagToPosts) {
        this.tagToPosts = tagToPosts;
    }
}
