package com.vancuongngo.springwebapp.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@NoArgsConstructor
@Data
public class PersistentLogins implements Serializable {
	private static final long serialVersionUID = -587067425711091104L;

	@Id
    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "token", nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used", nullable = false)
    private Date lastUsed;
}
