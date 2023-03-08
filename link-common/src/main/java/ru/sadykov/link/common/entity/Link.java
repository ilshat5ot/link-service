package ru.sadykov.link.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "link")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Link {
    @Id
    @Column(name = "short_link")
    private String  shortLink;
    @Column(name = "full_link")
    private String fullLink;
    @Column (name = "visits")
    private Integer visits;

}
