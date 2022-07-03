package com.github.enid3.cardgames.webapp.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.flogger.Flogger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Identifiable {
    @NotNull
    private String name;
    @NotNull
    @Min(0L)
    private Long tokens;

    private Long reservedTokes;

    public User(Long id, String name, Long tokens, Long reservedTokes) {
        super(id);
        this.name = name;
        this.tokens = tokens;
        this.reservedTokes = reservedTokes;
    }
}
