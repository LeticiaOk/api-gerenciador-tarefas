package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Projeto extends PanacheEntity {
    public String nome;
    public String descricao;
}
