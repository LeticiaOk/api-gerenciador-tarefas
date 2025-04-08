package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Tarefa extends PanacheEntity {
    public String titulo;
    public String descricao;
    public String status; // pendente, em andamento, concluída

    @ManyToOne
    public Usuario usuario;

    @ManyToOne
    public Projeto projeto;
}
