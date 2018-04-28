package project;

import javax.xml.parsers.*;
import java.io.File;
import java.util.LinkedList;

import org.w3c.dom.*;

public class XML {
	
	/*FIELDS*/
	int[] var;
	LinkedList<Zone> zones;
	LinkedList<Coord> obstacles;
	
	public XML(){
		this.var = new int[14];
		this.zones = null;
		this.obstacles = null;		
	}
	
	public void ParseXML(String filename) {
		
		try {
			
			//Construir um DOM parser
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			File file = new File(filename);
			
			if(file.exists()) {
				
				int xi, yi, xf, yf, val, i;
				
				//Obter o documento
				Document doc = db.parse(file);
				
				//Obter a raiz do documento
				Element elem = doc.getDocumentElement();
				
				this.var[0] = Integer.parseInt(elem.getAttribute("finalinst"));
				this.var[1] = Integer.parseInt(elem.getAttribute("initpop"));
				this.var[2] = Integer.parseInt(elem.getAttribute("maxpop"));
				this.var[3] = Integer.parseInt(elem.getAttribute("comfortsens"));
				
				NodeList nodelist = doc.getElementsByTagName("grid");
				Node node = nodelist.item(0);
				elem = (Element) node;
				this.var[4] = Integer.parseInt(elem.getAttribute("colsnb"));
				this.var[5] = Integer.parseInt(elem.getAttribute("rowsnb"));
				
				nodelist = doc.getElementsByTagName("initialpoint");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[6] = Integer.parseInt(elem.getAttribute("xinitial"));
				this.var[7] = Integer.parseInt(elem.getAttribute("yinitial"));
		
				
				nodelist = doc.getElementsByTagName("finalpoint");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[8] = Integer.parseInt(elem.getAttribute("xfinal"));
				this.var[9] = Integer.parseInt(elem.getAttribute("yfinal"));
				
				nodelist = doc.getElementsByTagName("zone");
				
				if(nodelist != null && nodelist.getLength()>0) {
					
					this.zones = new LinkedList<Zone>();
					
					for(i=0; i<nodelist.getLength(); i++) {
						node = nodelist.item(i);
						elem = (Element) node;
						
						xi = Integer.parseInt(elem.getAttribute("xinitial"));
						yi = Integer.parseInt(elem.getAttribute("yinitial"));
						xf = Integer.parseInt(elem.getAttribute("xfinal"));
						yf = Integer.parseInt(elem.getAttribute("yfinal"));
						val = Integer.parseInt(elem.getTextContent());
						this.zones.add(new Zone(new Coord(xi, yi), new Coord(xf,yf),val));
					}
				} 
				
				nodelist = doc.getElementsByTagName("obstacles");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[10] = Integer.parseInt(elem.getAttribute("num"));
				
				nodelist = doc.getElementsByTagName("obstacle");
				
				if(nodelist != null && this.var[10]>0) {
					
					this.obstacles = new LinkedList<Coord>();
					
					for(i=0; i<this.var[10]; i++) {
						node = nodelist.item(i);
						elem = (Element) node;
						
						xi = Integer.parseInt(elem.getAttribute("xpos"));
						yi = Integer.parseInt(elem.getAttribute("ypos"));
						this.obstacles.add(new Coord(xi,yi));
					}
				} 
				
				nodelist = doc.getElementsByTagName("death");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[11] = Integer.parseInt(elem.getAttribute("param"));
				nodelist = doc.getElementsByTagName("reproduction");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[12] = Integer.parseInt(elem.getAttribute("param"));
				nodelist = doc.getElementsByTagName("move");
				node = nodelist.item(0);
				elem = (Element) node;
				this.var[13] = Integer.parseInt(elem.getAttribute("param"));
				
				
			}else {
				System.exit(0);
			}
			
		}catch (Exception e){
			System.exit(0);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Tentar por o nome do ficheiro a entrar como argumento
		XML parser = new XML();
		parser.ParseXML("C:/Users/RitaC/Documents/2ºsemestre2017_2018/POO/Projecto/data1.xml");
		
		System.out.println("Simulation");
		System.out.println("finalinst= "+parser.var[0]);
		System.out.println("initpop= "+parser.var[1]);
		System.out.println("maxpop= "+parser.var[2]);
		System.out.println("comfortsens= "+parser.var[3]);
		System.out.println("-grid= ");
		System.out.println("colsnb= "+parser.var[4]);
		System.out.println("rowsnb= "+parser.var[5]);
		System.out.println("-initialpoint");
		System.out.println("xinitial= "+parser.var[6]);
		System.out.println("yinitial= "+parser.var[7]);
		System.out.println("-finalpoint");
		System.out.println("xfinal= "+parser.var[8]);
		System.out.println("yfinal= "+parser.var[9]);
		System.out.println("nobs="+parser.var[10]);
		System.out.println("-events");
		System.out.println("death= "+parser.var[11]);
		System.out.println("reproduction= "+parser.var[12]);
		System.out.println("move= "+parser.var[13]);
		System.out.println("zone: xi="+parser.zones.get(0).point1.x+" yi="+parser.zones.get(0).point1.y+" xf="+parser.zones.get(0).point2.x+" yf="+parser.zones.get(0).point2.y+" custo="+parser.zones.get(0).cost);
		System.out.println("Obstacles xpos="+parser.obstacles.getFirst().x+" ypos="+parser.obstacles.getFirst().y);
	}	

}
