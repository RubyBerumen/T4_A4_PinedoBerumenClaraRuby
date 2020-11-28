
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
        if (va < 0 || vb < 0) throw new Exception ("V�tice no existe");
        matAd[va][vb] = 1;
    }
    
    public boolean adyacente(String a, String b)throws Exception{
    	int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("V�tice no existe");
        return matAd[va][vb] == 1;
    }
    
    public int[]recorrerAnchura(GrafoMatriz g, String org) throws Exception{
        int w, v;
        int [] m;
        v = g.numVertice(org);
        
        int CLAVE =-1;
        if (v < 0) throw new Exception("V?tice origen no existe");
        
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
        	System.out.println("V?tice " + g.verts[w] + "visitado");
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





public class EjemploGrafos {

	public static void main(String[] args) {


		
		
		
		
		
		
	}

}
