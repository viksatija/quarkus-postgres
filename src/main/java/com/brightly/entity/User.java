package com.brightly.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "user_table", schema = "brightly")
public class User extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "usersequence",
            sequenceName = "brightly.user_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersequence")
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;
    @Column(name = "description")
    public String description;

}