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
@Table(name = "MEMBER")
public class Member implements Serializable {

    private static final long serialVersionUID = 2365398259212790000L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ID")
    @SequenceGenerator(sequenceName = "SEQ_MEMBER_ID", allocationSize = 1, name = "SEQ_MEMBER_ID")
    private Long id;

    private String account;

    private String alias;

    private String password;

    private LocalDateTime createTime;

}
