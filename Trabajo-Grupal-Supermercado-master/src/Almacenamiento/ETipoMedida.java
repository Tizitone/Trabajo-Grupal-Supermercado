package Gestion.Almacenamiento;

public enum ETipoMedida {
	KILOGRAMO("KG"),GRAMO("G"),MILIGRAMO("mg"),LITRO("L"),MILILITRO("ml");
	
	private String medida;

	private ETipoMedida(String medida) {
		this.medida = medida;
	}

	public String getMedida() {
		return medida;
	}
	
	
}
