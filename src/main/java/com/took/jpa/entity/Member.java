package com.took.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.took.jpa.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="entity_member")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String userID;
    private String userPW;
    private String userName;
    private String userEmail;

    private String tel;
    private String address01;
    private String address02;
    private String zipcode;
    private int age;

//    private String profile;
//    private String originalProfile;
//    private String renameProfile;
    private String roles;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentList;

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .userID(member.getUserID())
                .userPW(member.getUserPW())
                .userName(member.getUserName())
                .userEmail(member.getUserEmail())
                .tel(member.getTel())
                .address01(member.getAddress01())
                .address02(member.getAddress02())
                .zipcode(member.getZipcode())
                .roles(member.getRoles())
                .build();
    }
}
