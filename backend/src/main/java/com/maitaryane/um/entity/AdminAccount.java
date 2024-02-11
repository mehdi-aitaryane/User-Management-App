package com.maitaryane.um.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admin_account")
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminAccount extends Account {


}