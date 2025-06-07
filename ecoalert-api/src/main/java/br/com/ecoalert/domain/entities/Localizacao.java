package br.com.ecoalert.domain.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "TB_LOCALIZACAO")
@SequenceGenerator(name = "localizacao", sequenceName = "SEQ_TB_LOCALIZACAO", allocationSize = 1)
public class Localizacao {

    @Id
    @GeneratedValue(generator = "localizacao", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_localizacao")
    private Long id;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    public Localizacao() {
    }

    public Localizacao(String cidade, String estado, String latitude, String longitude) {
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
