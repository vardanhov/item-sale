package com.itemsale.domain;

import com.itemsale.domain.enumeration.ItemType;
import com.itemsale.domain.enumeration.convert.ItemTypeConverter;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="item")
@ApiModel(description = "item")
@Data
@NoArgsConstructor
@ToString
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "FK_ITEM_REFERENCE_PARTY"), insertable=true, updatable=true)
    private Party owner;

    @JoinColumn(name="parent_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "FK_ITEM_REFERENCE_ITEM"), insertable=true, updatable=true)
    private Long parentId;


    @Column(name="serial")
    private String serial;

    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;

    @Column(name="children_count")
    private Integer childrenCount;

    @Column(name="create_date")
    @CreationTimestamp
    private Date createDate;

}
