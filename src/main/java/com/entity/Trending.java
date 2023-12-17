package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trending", catalog = "memetube")
public class Trending {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Idtrending")
    private Integer idtrending;
    @ManyToOne
    @JoinColumn(name = "Idvideo")
    private Video video;
    @Column(name = "Datecreated")
    private Timestamp datecreated;
}
