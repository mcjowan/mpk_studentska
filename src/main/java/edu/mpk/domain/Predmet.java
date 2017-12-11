package edu.mpk.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "predmet")
public class Predmet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "naziv", length = 255)
    private String naziv;
    
    @NotNull
    @Size(max = 255)
    @Column
    private String opis;
    
//    @ManyToMany
//    private List<Student> students;

    public Predmet() {}

	public Predmet(Long id, String naziv, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
	}

    @Override
    public String toString() {
        return String.format(
                "Predmet[id=%d, naziv='%s', opis='%s']",
                id, naziv, opis);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

//	public List<Student> getStudents() {
//		return students;
//	}
//
//	public void addItem(Student item) {
//		students.add(item);
//	}
}
