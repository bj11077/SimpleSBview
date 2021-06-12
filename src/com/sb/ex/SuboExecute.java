package com.sb.ex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sb.vo.SuboVO;

public class SuboExecute {

	public static void main(String[] args) throws IOException {
		String path = "https://www.op.gg/summoner/userName=";
	
		SuboVO sv = new SuboVO();
		//Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("=======================Twitch 블러스 Recent Game =======================\n");
		
		//System.out.println("====================================================");
		//System.out.print("조회할 아이디 입력 : ");
		//String id = sc.nextLine();
		
		//if(id.contains(" ")) {
		//	id = id.replace(" ", "");
		//}
		//path += id;
		String id= "twitch블러스";
		path +=id;

		
		System.out.println(path);
		
		//적립용
		int kill=0;
		int death=0;
		int ass=0;
		int win=0;
		int lose=0;
		
		
		
		Document doc = Jsoup.connect(path).get();
			
		Elements element = doc.select("div.Content");
		
		
	
		//System.out.println(element);
		
		Iterator<Element> ie1 = element.select("div.GameItemWrap").iterator();
		
		String[] splitMan;
		
		while(ie1.hasNext()) {
			splitMan = ie1.next().text().split(" ");
			if(splitMan[0].equals("Ranked") && splitMan[1].equals("Solo")) {
				//4 패배 56 분초  8킬 10데스 12 어시
				sb.append(splitMan[2] +" " + splitMan[3] + " " + splitMan[4] + " " + splitMan[5]+"  " +splitMan[6]
						+" " + splitMan[7] + "  " + splitMan[8] +" " + splitMan[9] +" " + splitMan[10] + " " +splitMan[11]+
						" " +splitMan[12]+ "  " + splitMan[13] +"\n");
				// 킬데스어시적립
				kill += Integer.parseInt(splitMan[8]);
				death += Integer.parseInt(splitMan[10]);
				ass += Integer.parseInt(splitMan[12]);
				
				switch (splitMan[4]) {
				case "Victory":
						win += 1;
					break;
				case "Defeat":
						lose += 1;
					break;
					
				default:
					break;
				}//switch
				
			}
		}// small while
		
		
		//total manage
		sv.setWin(win);
		sv.setLose(lose);
		sv.setKill(kill);
		sv.setAss(ass);
		sv.setDeath(death);
	String resultSet;
	resultSet = "\n\nVictory : " + sv.getWin()+" Defeat : "+sv.getLose()+" Kill : "+sv.getKill()+" Death : "+sv.getDeath()+" Assist : "+sv.getAss();
	sb.append(resultSet);
	String setting = sb.toString();
	FileOutputStream output = new FileOutputStream("C:\\subo.txt");
	byte[] by = setting.getBytes();
	output.write(by);
	output.close();
	
	
	}//dd
}
