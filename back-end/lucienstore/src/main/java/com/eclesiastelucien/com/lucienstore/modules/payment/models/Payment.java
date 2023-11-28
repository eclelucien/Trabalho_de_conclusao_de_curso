package com.eclesiastelucien.com.lucienstore.modules.payment.models;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity {

}
