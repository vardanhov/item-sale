package com.itemsale.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "party")
@ApiModel(description = "party")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long id;

    @NonNull
    @Column(name ="name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "version")
    private Integer version;


}
