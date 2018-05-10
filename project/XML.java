package project;

/*Importacao dos packages necessarios*/
import javax.xml.parsers.*;
import java.io.File;
import java.util.LinkedList;
import org.w3c.dom.*;

public class XML {
	
	/*FIELDS*/
	int[] var; /*Vector de inteiros para armazenar paramentros lidos do ficheiro*/
	Coord size; /*Tamanho da grelha*/
	Coord init; /*Posicao inicial*/
	Coord end; /*Posicao final*/
	LinkedList<Zone> zones; /*Lista de zones especiais*/
	LinkedList<Coord> obstacles; /*Lista de obstaculos*/
	
	/*CONSTRUCTOR*/
	public XML(){
		this.var = new int[8]; /*Inicializa vector com o tamanho pretendido*/
		this.zones = null; /*Lista de zonas a null*/
		this.obstacles = null; /*Lista obstaculos a null*/
	}
	
	/*METODOS*/
	
	/*Preenche a lista de zonas especiais*/
	public void addZone(Coord point_a, Coord point_b,int cost) {
		Zone temp = new Zone(point_a,point_b,cost); /*Cria nova zona*/
		this.zones.addLast(temp); /*Adiciona zona a lista*/
	}
	
	/*Preenche a lista de obstaculos*/
	public void addObstacle(int x,int y) {
		Coord temp = new Coord(x,y); /*Cria coordenada do obstaculos*/
		this.obstacles.addLast(temp); /*Adiciona a lista de obstaculos*/
	}
	
	/*Le paramatros do ficheiro xml e armazena-os*/
	public void ParseXML(String filename) {
		
		try {
			
			/*Construir um DOM parser*/
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			File file = new File(filename); 
			
			if(file.exists()) {
				
				int xi, yi, xf, yf, val; /*Variaveis auxiliares*/
				
				/*Obter o documento*/
				Document doc = db.parse(file);
				
				/*Obter a raiz do documento*/
				Element elem = doc.getDocumentElement();
				
				/*Da raiz (Simulation) retira:*/
				this.var[0] = Integer.parseInt(elem.getAttribute("finalinst")); /*instante final*/
				this.var[1] = Integer.parseInt(elem.getAttribute("initpop")); /*Populacao inicial*/
				this.var[2] = Integer.parseInt(elem.getAttribute("maxpop")); /*Maximo de individuos permitido*/
				this.var[3] = Integer.parseInt(elem.getAttribute("comfortsens")); /*Sensibilidade do conforto*/
				
				/*Obtem o elemento com tag "grid" correpondente ao tamanho da grelha*/
				NodeList nodelist = doc.getElementsByTagName("grid");
				Node node = nodelist.item(0);
				elem = (Element) node;
				xi = Integer.parseInt(elem.getAttribute("colsnb")); /*retira atributo numero de colunas*/
				yi = Integer.parseInt(elem.getAttribute("rowsnb")); /*retira atributo numero de linhas*/
				this.size = new Coord(xi,yi); /*Cria coordenada correspondente ao tamanho da grelha*/
				
				/*Obtem o elemento com tag "initialpoint" correspondente a posicao inicial*/
				nodelist = doc.getElementsByTagName("initialpoint");
				node = nodelist.item(0);
				elem = (Element) node;
				xi = Integer.parseInt(elem.getAttribute("xinitial")); /*retira atributo x*/ 
				yi = Integer.parseInt(elem.getAttribute("yinitial")); /*retira atributo y*/
				this.init = new Coord(xi,yi); /*cria coordenada inicial*/
				
				/*Obtem o elemento com tag "finalpoint" correspondente a posicao final*/
				nodelist = doc.getElementsByTagName("finalpoint");
				node = nodelist.item(0);
				elem = (Element) node;
				xf = Integer.parseInt(elem.getAttribute("xfinal")); /*retira atributo x*/
				yf = Integer.parseInt(elem.getAttribute("yfinal")); /*retira atributo y*/ 
				this.end = new Coord(xf,yf); /*Cria coordenada final*/
				
				/*Obtem o elemento com tag "zone" correspondente as zonas especiais*/
				nodelist = doc.getElementsByTagName("zone");
				
				if(nodelist != null && nodelist.getLength()>0) {/*Se existem zonas especiais*/
					
					this.zones = new LinkedList<Zone>(); /*Cria lista de zonas*/
					
					/*Percorre lista de zonas no ficheiro*/
					for(int i=0; i<nodelist.getLength(); i++) {
						node = nodelist.item(i);
						elem = (Element) node;
						
						xi = Integer.parseInt(elem.getAttribute("xinitial")); /*Retira atributo x inicial*/
						yi = Integer.parseInt(elem.getAttribute("yinitial")); /*Retira atributo y inicial*/
						xf = Integer.parseInt(elem.getAttribute("xfinal")); /*Retira atributo x final*/
						yf = Integer.parseInt(elem.getAttribute("yfinal")); /*Retira atributo y final*/
						val = Integer.parseInt(elem.getTextContent()); /*Retira custo da zona*/
						this.addZone(new Coord(xi, yi), new Coord(xf,yf),val); /*Adiciona zona a lista*/
					}
				} 
				
				/*Obtem o elemento com tag "obstacles" correspondente a obstaculos*/
				nodelist = doc.getElementsByTagName("obstacles");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[4] = Integer.parseInt(elem.getAttribute("num")); /*retira atributo numero de obstaculos*/
				
				/*Obtem o elemento com tag "obstacle" correspondente aum obstaculo*/
				nodelist = doc.getElementsByTagName("obstacle"); 
				
				if(nodelist != null && this.var[4]>0) { /*Se existem obstaculos*/
					
					this.obstacles = new LinkedList<Coord>(); /*Cria lista de obstaculos*/
					
					/*Percorre lista de obstaculos no ficheiro*/
					for(int i=0; i<this.var[4]; i++) {
						node = nodelist.item(i);
						elem = (Element) node;
						
						/*retira atributo x da coordenada do obstaculo*/
						xi = Integer.parseInt(elem.getAttribute("xpos")); 
						/*retira atributo y da coordenada do obstaculo*/
						yi = Integer.parseInt(elem.getAttribute("ypos"));
						this.addObstacle(xi,yi); /*Adiciona obstaculo a lista*/
					}
				} 
				
				/*Retira parametro usado no calculo do instante da morte*/
				nodelist = doc.getElementsByTagName("death");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[5] = Integer.parseInt(elem.getAttribute("param"));
				/*Retira parametro usado no calculo do instante da reproducao*/
				nodelist = doc.getElementsByTagName("reproduction");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[6] = Integer.parseInt(elem.getAttribute("param"));
				/*Retira parametro usado no calculo do instante do movimento*/
				nodelist = doc.getElementsByTagName("move");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[7] = Integer.parseInt(elem.getAttribute("param"));
				
			}else {
				System.exit(0); /*Caso nao exista ficheiro programa termina*/
			}
			
		}catch (Exception e){
			System.exit(0); /*Em caso de excepcao o programa termina*/
		}
		
	}

}
