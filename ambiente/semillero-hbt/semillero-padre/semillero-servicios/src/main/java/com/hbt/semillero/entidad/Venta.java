package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>Descripción:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."VENTA"
 * 
 * @author ehernandez
 * @version
 */
@Entity
@Table(name = "VENTA")
public class Venta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "VENTA_ID_GENERATOR", sequenceName = "SEC_VENTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTA_ID_GENERATOR")
	@Column(name = "VEN_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEN_IDPERSONA", nullable = false)
	private Persona idpersona;
	
	@Column(name = "VEN_FECVENTA", nullable = false)
	private LocalDateTime fecventa;
	
	@JoinTable(
        name = "VENTA_COMIC",
        joinColumns = @JoinColumn(name = "FK_VENTA", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_COMIC", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Comic> comics;

	public void addComic(Comic comic){
        if(this.comics == null){
            this.comics = new ArrayList<>();
        }
        
        this.comics.add(comic);
    }
	
	/**
	 * Metodo retorna el id de la persona asociado a la venta
	 * @return estado de tipo string
	 */
	public Persona getPersona() {
		return idpersona;
	}	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idpersona
	 */
	public Persona getIdpersona() {
		return idpersona;
	}

	/**
	 * @param idpersona the idpersona to set
	 */
	public void setIdpersona(Persona idpersona) {
		this.idpersona = idpersona;
	}

	/**
	 * @return the fecventa
	 */
	public LocalDateTime getFecventa() {
		return fecventa;
	}

	/**
	 * @param fecventa the fecventa to set
	 */
	public void setFecventa(LocalDateTime fecventa) {
		this.fecventa = fecventa;
	}

	/**
	 * @return the comics
	 */
	public List<Comic> getComics() {
		return comics;
	}

	/**
	 * @param comics the comics to set
	 */
	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}
}
