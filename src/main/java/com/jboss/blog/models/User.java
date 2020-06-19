package com.jboss.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "phone" }),
        @UniqueConstraint(columnNames = { "email" }) })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "Details of User Model")
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Here is the unique ID for User")
    private Long id;
    @ApiModelProperty(notes = "This is the field for User fullname.")
    private String fullname;
    @ApiModelProperty(notes = "This is the field for User email.")
    private String email;
    @ApiModelProperty(notes = "This is the field for Username.")
    private String username;
    @ApiModelProperty(notes = "This is the Password field.")
    private String password;
    @ApiModelProperty(notes = "This is the field for User phone.")
    private String phone;
    @ApiModelProperty(notes = "This field determine Users activeness")
    private Long status;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

}
