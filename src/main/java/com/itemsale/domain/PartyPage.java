package com.itemsale.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="page_open")
@ApiModel(description = "Open page")
@Data
@NoArgsConstructor
@ToString
public class PartyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="date")
    @CreationTimestamp
    private Date date;

}
