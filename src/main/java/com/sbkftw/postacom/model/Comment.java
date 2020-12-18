package com.sbkftw.postacom.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comment", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Integer       id;
    @NonNull
    private String        message;
    @NonNull
    private LocalDateTime publishDate;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User          owner;
}
