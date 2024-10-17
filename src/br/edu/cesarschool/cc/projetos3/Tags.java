public enum Tags {
	
	ACAO("Ação"),
	ROMANCE("Romance"),
	TERROR("Terror"),
	SUSPENSE("Suspense"),
	COMEDIA("Comédia"),
	DOCUMENTARIOS("Documentários"),
	DRAMA("Drama"),
	FICCAOCIENTIFICA("Ficção Científica");
	
	private String tag;
	
	Tags(String tag){
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
