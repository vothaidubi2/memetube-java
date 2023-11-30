package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report", catalog = "memetube")
public class Report {

    @Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "Idreport")
    private Integer idreport;
    
    @ManyToOne
    @JoinColumn(name = "Iduserreport")
    private Users userReported;

    @ManyToOne
    @JoinColumn(name = "Idreportedchannel")
    private Channel reportedChannel;

    @Column(name = "Datecreate")
    private Timestamp datecreate;

    @Column(name = "Status")
    private Boolean status;
}
