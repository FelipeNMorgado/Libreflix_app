public class Usuario {
	
	private String nome;
	private String email;
	private String userName;
	private String senha;
	private Filme[] filmes;
	private Serie[] series;
	
	public Usuario(String nome, String email, String userName, String senha, Filme[] filmes, Serie[] series) {
		this.nome = nome;
		this.email = email;
		this.userName = userName;
		this.senha = senha;
		this.filmes = filmes;
		this.series = series;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getSenha() {
		return senha;
	}

	public Filme[] getFilmes() {
		return filmes;
	}

	public Serie[] getSeries() {
		return series;
	}
	
	
}
