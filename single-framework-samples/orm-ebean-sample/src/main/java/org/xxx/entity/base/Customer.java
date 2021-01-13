package org.xxx.entity.base;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Deveik
 */
@Entity
@Data
public class Customer {
    @Id
    private long id;
    private String name;
}
