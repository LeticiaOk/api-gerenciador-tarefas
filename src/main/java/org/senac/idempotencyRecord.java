package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class idempotencyRecord extends PanacheEntity {
    public String keyRecord;
}
