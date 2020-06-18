package com.jboss.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "Details of Post Model")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Here is the unique ID for Post")
    private Long id;
    @ApiModelProperty(notes = "Here is the field for blog title")
    private String title;
    @ApiModelProperty(notes = "This is the field for the blog content.")
    private String body;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
