package br.usjt.falacidadao.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="postagem")
public class Postagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@NotEmpty(message="Não pode ser vazio")
	@Column(name="descricao", nullable = false)
	@Size(max = 1000, message = "Tamanho máximo 1000 caracteres.")
	private String descricao;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="datasugestao", nullable = true)
	private Date dataSugestao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datamural", nullable = true)
	private Date dataMural;
	
	@Column(name="imagem",length = 20971520, nullable = true)
	@Lob
	private byte[] imagem;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",length=20,nullable=true)
	private Status status;	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "area_id")
	@JsonIgnore
	private Area area;

	@Column(name="protocolo",length=15,nullable=true)
	private String protocolo;
	
	transient String comentario;
	transient String mural;
	
	private String contador;
	
	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataSugestao() {
		return dataSugestao;
	}

	public void setDataSugestao(Date dataSugestao) {
		this.dataSugestao = dataSugestao;
	}

	public Date getDataMural() {
		return dataMural;
	}

	public void setDataMural(Date dataMural) {
		this.dataMural = dataMural;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getProtocolo() {
		return protocolo;
	}
	
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setMural(String mural) {
		this.mural = mural;
	}
	
	public String getMural() {
		return mural;
	}
	
	@Override
	public String toString() {
		return "Postagem [id=" + id + ", descricao=" + descricao + ", dataSugestao=" + dataSugestao + ", dataMural="
				+ dataMural + ", imagem=" + Arrays.toString(imagem) + ", status=" + status + ", usuario=" + usuario
				+ ", area=" + area + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
