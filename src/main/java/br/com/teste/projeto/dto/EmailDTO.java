package br.com.teste.projeto.dto;

public class EmailDTO {

	private String emailDestino;
	private String titulo;
	private String mensagem;
	
	public EmailDTO() {

	}

	public EmailDTO(String emailDestino, String titulo, String mensagem) {
		super();
		this.emailDestino = emailDestino;
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
