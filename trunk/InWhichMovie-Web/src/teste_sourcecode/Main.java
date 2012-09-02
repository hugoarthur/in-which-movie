/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_sourcecode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.inwhichmovie.beans.Filme;

/**
 *
 * @author hugoarthur
 */
public class Main {

    static List<Filme> filmes;
	
    public static void main(String[] args) {
    	NumberFormat nf =  NumberFormat.getIntegerInstance();
    	nf.setMinimumIntegerDigits(7);
    	nf.setGroupingUsed(false);
    	//848228
    	Integer id = 848228;
    	int cont = 1;
    	filmes = new ArrayList<Filme>();
    	while(id<848232){
    		try {
				if (isFilme(nf.format(id))) {
					gravaArquivo(getFilme(nf.format(id)));
				}
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id = id + cont;
    	}
    }
    
    public static boolean isFilme(String id){
    	String nomeUrl = "http://www.imdb.com/title/tt"+id+"/";
			Parser parser;
			try {
				parser = new Parser(nomeUrl);
				NodeList source = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#overview-top")));
				String html = source.toHtml();
				if(!(html.contains("TV Series")||html.contains("Video Game")||html.contains("TV Episode")))
					return true;
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
    }
    
    public static Filme getFilme(String id){
    	Filme filme = null;
    	String nomeUrl = "http://www.imdb.com/title/tt"+id+"/soundtrack";
    	try {
			Parser parser = new Parser(nomeUrl);
			
			//filme
			Node source = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#tn15title > h1"))).elementAt(0);
			NodeList nodeList = source.getChildren();
			String html = nodeList.toHtml();
			filme = new Filme();
			filme.setIdImdb(id);
			filme.setNome(StringUtils.substringBetween(html, "<a class=\"main\" href=\"/title/tt"+id+"/\">", "</a>"));
			filme.setNomeOriginal(StringUtils.substringBetween(html, "<span class=\"title-extra\">", "<i>"));
			filme.setAno(StringUtils.substringBetween(html, "<a href=\"/year/", "/\""));
			System.out.println(filme);
//			getMusicas(filme, parser);
			return filme;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filme;
    }
    
    public static void getMusicas(Filme filme, Parser parser){
    	//musica
		try {
			Node source = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#tn15content"))).elementAt(0);
			NodeList nodeList = source.getChildren();
			for(Node node : nodeList.toNodeArray())
			{
				if(node.getText().contains("ul class=\"trivia\""))
				{
					String html = node.toHtml();
					String soundtrack = StringUtils.substringBetween(html, "<ul class=\"trivia\">", "</ul>");
					System.out.println(soundtrack);
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void gravaArquivo(Object obj){
    	try {
			if (obj != null) {
				FileOutputStream fos = new FileOutputStream(obj.getClass().toString() + ".txt", true);
				fos.write(obj.toString().getBytes());
				fos.close();
			}
			else
				System.out.println("Objeto nao gravado");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void getFilmesAntigo(String id){
    	try {
            String nomeUrl = "http://www.imdb.com/title/tt"+id+"/soundtrack";
            URL url = new URL(nomeUrl);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            Permission permi = conn.getPermission();
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine();
            String codigo = "";

            while (linha != null) {
                codigo = codigo+"\n"+linha;
                System.out.println(codigo);
                linha = br.readLine();
            }
            System.out.println("Nome do filme = "+StringUtils.substringBetween(codigo, "<title>", "</title>"));
            System.out.println("Músicas = "+StringUtils.substringBetween(codigo, "<ul class=\"trivia\">", "</ul>"));
//            File file = new File("arquivo.txt");
//            OutputStreamWriter write = new FileWriter(file);
//            write.write(codigo);
//            write.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
