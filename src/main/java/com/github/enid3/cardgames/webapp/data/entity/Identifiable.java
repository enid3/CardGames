package com.github.enid3.cardgames.webapp.data.entity;


import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Identifiable {
    @Id
    @GeneratedValue
    private Long id;

}
