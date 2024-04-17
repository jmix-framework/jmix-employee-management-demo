package com.company.sample.entity;

import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.*;
import io.jmix.data.DdlGeneration;
import jakarta.persistence.*;

import java.util.UUID;

@DdlGeneration(value = DdlGeneration.DbScriptGenerationMode.CREATE_ONLY)
@JmixEntity
@Store(name = "management")
@Table(name = "department")
@Entity
public class Department {
    @Column(name = "department_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @InstanceName
    @Column(name = "name", nullable = false)
    private String name;

    @SystemLevel
    @Column(name = "RESPONSIBLE_ID")
    private UUID responsibleId;

    @DependsOnProperties({"responsibleId"})
    @JmixProperty
    @Transient
    private User responsible;

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public UUID getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(UUID responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}