package com.hendisantika.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.19
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
@Data
public abstract class CommonHistoryEntity implements Serializable {

    private static final long serialVersionUID = -20200109182700L;

    @CreatedDate
    @Column(name = "valid_from", nullable = false)
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    @Column(name = "is_active", nullable = false, columnDefinition = "bit(1) default b'1'")
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "source_identifier")
    private String sourceIdentifier;
}
