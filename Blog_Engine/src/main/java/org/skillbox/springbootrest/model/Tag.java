package org.skillbox.springbootrest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag implements Comparable<Tag> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "tag2post",
            joinColumns = @JoinColumn(name = "tagId"),
            inverseJoinColumns = @JoinColumn(name = "postId"))
    private Set<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int compareTo(Tag o) {
        int result = o.getPosts().size() - this.getPosts().size(); // need reversed order
        return result != 0 ? result : this.getName().compareTo(o.getName());
    }
}