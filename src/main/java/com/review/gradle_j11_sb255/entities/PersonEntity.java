package com.review.gradle_j11_sb255.entities;

import com.review.gradle_j11_sb255.utils.emuns.PersonType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public abstract class PersonEntity implements Serializable {

    @NonNull
    private String identification;

    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private String phone;

    @NonNull
    private String homeAddress;

    @NonNull
    private String workAddress;

    @NonNull
    private LocalDate dateOfBirth;

}
