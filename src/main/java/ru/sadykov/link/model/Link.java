package ru.sadykov.link.model;

import lombok.*;

import javax.persistence.*;

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
