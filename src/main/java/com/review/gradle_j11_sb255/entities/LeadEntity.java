package com.review.gradle_j11_sb255.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Table(value = "leads")
@Getter
@Setter
public class LeadEntity extends PersonEntity implements Serializable {

    @Id
    private Long id;

    @NonNull
    private String source;

    @Version
    private Long version;

}
