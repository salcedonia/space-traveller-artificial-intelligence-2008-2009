package micromundo;

/*
 * Clase que representa ana localización dentro del micromundo.
 * Se utiliza para generar el micromundo aleatorio.
 */

public class Localizacion implements Cloneable {
	private int x;
	private int y;
	
	public Localizacion(){
		this.x=0;
		this.y=0;
	}
	
	public Localizacion(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static String ARRIBA = "Arriba";
	public static String DERECHA = "Derecha";
	public static String ABAJO = "Abajo";
	public static String IZQUIERDA = "Izquierda";	
	
	
	public Localizacion avanza(int d){
		switch (d){
		case 0:
			this.y--;
		break;
		case 1:
			this.x++;
		break;
		case 2:
			this.y++;
		break;
		case 3:
			this.x--;
		break;		
		}
		return this;
	}
	
	public Object clone(){
		Object ret = null;
		try {
			ret = super.clone();		
		} catch (CloneNotSupportedException ex){
			return null;
		}
		Localizacion copia = (Localizacion)ret;
		copia.x = (int) this.x;
		copia.y = (int) this.y;			
		return copia;
	}
	
	public boolean equals(Object o){
		if (o == null )
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Localizacion))
			return false;
		Localizacion l=(Localizacion)o;
		return (this.x==l.x) && (this.y==l.y);
	}	
	
}
