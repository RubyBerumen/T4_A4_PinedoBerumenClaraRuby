import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Vertice{
	String nombre;
	int numVertice;
	
	public Vertice(String x){
	 nombre = x;
	 numVertice = -1;
	}
	
	public String nomVertice(){
	 return nombre;
	}
	
	public boolean equals(Vertice n){
	 return nombre.equals(n.nombre);
	}
	
	public void asigVert(int n) {
	 numVertice = n;
	}
	
	public String toString(){
	 return nombre + " (" + numVertice + ")";
	}

}//class Vertice


class GrafoMatriz{
	
	int numVerts;
	static int maxVerts;
	Vertice [] verts;
	int [][] matAd;
	
	public GrafoMatriz(){
    	this(maxVerts);
    }
	
    public GrafoMatriz(int mx){
    	maxVerts=mx;
        matAd = new int [mx][mx];
        verts = new Vertice[mx];
        for (int i = 0; i < mx; i++)
        	for (int j = 0; i < mx; i++)
        		matAd[i][j] = 0;
        numVerts = 0;
    }
    
    public void nuevoVertice (String nom){
    	boolean esta = numVertice(nom) >= 0;
    	if (!esta){
    		Vertice v = new Vertice(nom);
    		v.asigVert(numVerts);
    		verts[numVerts++] = v;
    	}
    }
    
    public int numVertice(String vs) {
         Vertice v = new Vertice(vs);
         boolean encontrado = false;
         int i = 0;
         for (; (i < numVerts) && !encontrado;){
        	 encontrado = verts[i].equals(v);
        	 if (!encontrado) 
        		 i++ ;
        	 }
         return (i < numVerts) ? i : -1 ;
    }
    
    public void nuevoArco(String a, String b)throws Exception{
    	int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("Vétice no existe");
        matAd[va][vb] = 1;
    }
    
    public boolean adyacente(String a, String b)throws Exception{
    	int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("Vétice no existe");
        return matAd[va][vb] == 1;
    }
    
    public int[]recorrerAnchura(GrafoMatriz g, String org) throws Exception{
        int w, v;
        int [] m;
        v = g.numVertice(org);
        
        int CLAVE =-1;
        if (v < 0) throw new Exception("Vétice origen no existe");
        
        ColaLista cola = new ColaLista();
        m = new int[g.numVerts];
        
        for (int i = 0; i < g.numVerts; i++)
        m[i] = CLAVE;
        m[v] = 0; 
        cola.insertar(new Integer(v));
        while (! cola.colaVacia()){
        	Integer cw;
        	cw = (Integer) cola.quitar();
        	w = cw.intValue();
        	System.out.println("Vétice " + g.verts[w] + "visitado");
        	for (int u = 0; u < g.numVerts; u++)
        	if ((g.matAd[w][u] == 1) && (m[u] == CLAVE))
        	{
        	m[u] = m[w] + 1;
        	cola.insertar(new Integer(u));
        	}
        }
        return m;
        
    }

}//class GrafoMatriz


class Nodo {
	Object elemento;
	Nodo siguiente;
	int dato;
	
	public Nodo(Object x){
		elemento = x;
		siguiente = null;
		}
	public Nodo(int x){
		dato = x;
	    siguiente = null;
	}
	public Nodo(int x, Nodo n){
	    dato = x;
	    siguiente = n;
	}
	
	public int getDato(){
	    return dato;
	}
	public Nodo getEnlace(){
	    return siguiente;
	}
	public void setEnlace(Nodo enlace){
	    this.siguiente = enlace;
	}
}//class Nodo


class ColaLista { 
	protected Nodo frente;
	protected Nodo fin;
	
	public ColaLista(){
		frente = fin = null;
	}
	
    public void insertar(Object elemento){
    	Nodo a;
        a = new Nodo(elemento);
        if (colaVacia()){
        	frente = a;
        	}else{
        		fin.siguiente = a;
        	}
        fin = a;
    }
    
    public Object quitar() throws Exception{
    	Object aux;
    	if (!colaVacia()){
    		aux = frente.elemento;
    		frente = frente.siguiente;
    	}else
    		throw new Exception("Eliminar de una cola vac?");
    	return aux;
    }
    
    public void borrarCola(){
    	for (; frente != null;){
    		frente = frente.siguiente;
        }
    	System.gc();
    }
    
    public Object frenteCola() throws Exception{
    	if (colaVacia()){
    		throw new Exception("Error: cola vac?");
        }
    	return (frente.elemento);
    }
    
    public boolean colaVacia(){
    	return (frente == null);
    }
    
}//class ColaLista


class Arco{
	int destino;
    double peso;
    
    public Arco(int d){
    	destino = d;
    }
    public Arco(int d, double p){
    	this(d);
    	peso = p;
    	}
    
    public int getDestino(){
        return destino;
    }
    public boolean equals(Object n){
    	Arco a = (Arco)n;
    	return destino == a.destino;
    }
} //class Arco

public class EjemploGrafos {

	public static void main(String[] args) {
		
		Scanner ent = new Scanner (System.in);
		
		System.out.println("Ingresa el número máximo de vertices: ");
		int numV = ent.nextInt();
		GrafoMatriz gm = new GrafoMatriz(numV);
		
		boolean salir = false;
		
		do {
			System.out.println("1. Agregar vertice");
            System.out.println("2. Agregar arco");
            System.out.println("3. Saber si son adyacentes");
            System.out.println("4. Recorrer");
            System.out.println("5. Salir");
           
            try {
            	System.out.println("Escribe una de las opciones");
                int opcion = ent.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Ingresa el vertice: ");
                        String nombre = ent.nextLine();
                        gm.nuevoVertice(nombre);
                        break;
                    case 2:
                        System.out.println("Ingresa el origen: ");
                        String a = ent.nextLine();
                        System.out.println("Ingresa el destino: ");
                        String b = ent.nextLine();
                        try {
							gm.nuevoArco(a, b);
						} catch (Exception e) {
							System.out.println("Debes ingresar vertices correctos");
						}
                        
                        break;
                    case 3:
                        System.out.println("Ingresa verice origen a buscar: ");
                        a = ent.nextLine();
                        System.out.println("Ingresa el vertice destino");
                        b = ent.nextLine();
                        try {
                        	System.out.println(gm.adyacente(a, b)?"Son adyacentes":"No son adyacentes!");
						} catch (Exception e) {
							System.out.println("Debes ingresar vertices correctos");
						}
                        
                        break;
                    case 4:
                    	System.out.println("Ingresa el vertice origen a recorrer");
            			String origen = ent.nextLine();
            			try {
            				gm.recorrerAnchura(gm, origen);
            				System.out.println();
            			} catch (Exception e) {
            				System.out.println("Debes ingresar vertices correctos");
            			}
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Debes ingresar números entre 1 y 5");
                }
				
			} catch (InputMismatchException e) {
				System.out.println("Debes ingresar un número");
                ent.next();
				
			}
            
            
			
		}while (!salir);//while
	
		
		
	}

}
