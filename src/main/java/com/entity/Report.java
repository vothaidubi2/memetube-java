package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report", catalog = "memetube")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idreport")
    private Integer idreport;


    @Column(name = "Datecreate")
    private Timestamp datecreate;

    @Column(name = "Status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "Idchannel")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "Idcomment")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "Idvideo")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "Iduserreport")
    private Users userReport;
}
