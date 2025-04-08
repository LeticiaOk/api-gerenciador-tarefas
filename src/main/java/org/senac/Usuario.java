package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends PanacheEntity {
    public String nome;
    public String email;
}
