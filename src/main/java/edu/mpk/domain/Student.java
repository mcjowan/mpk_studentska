package edu.mpk.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name = "student")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "ime", length = 255)
    private String ime;
    
//    private String brojIndexa;
    
    @NotNull
    @Size(min = 2, max = 255)
    @Column(length = 255)
    private String prezime;
    
    @Size(max = 20)
    @Column
    private String zvanje;
    
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;
    
//    @ManyToMany(mappedBy = "students")
//    private List<Predmet> predmeti;
    
    @NotNull
    @Size(max = 255)
    @Column
    private String opis;
    

    public Student() {}

	public Student(Long id, String ime, String prezime, String zvanje,
			String opis) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
		this.opis = opis;
	}

    @Override
    public String toString() {
        return String.format(
                "Student[id=%d, ime='%s', prezime='%s']",
                id, ime, prezime);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
