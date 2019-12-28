package org.yfr.sample.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ITEM")
public class Item implements Serializable {

    private static final long serialVersionUID = -6801888825514350894L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM_ID")
    @SequenceGenerator(sequenceName = "SEQ_ITEM_ID", allocationSize = 1, name = "SEQ_ITEM_ID")
    private Long id;

    private String code;

    private LocalDateTime createTime;

    private Float price;

}
