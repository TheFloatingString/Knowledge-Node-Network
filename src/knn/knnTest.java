package knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import tags.Tag;
import tags.Tag.TagType;

public class knnTest {
	public static void main(String[] args){
		ArrayList<KnowledgeNode> animal = new ArrayList<>();
		KnowledgeNodeNetwork relation = new KnowledgeNodeNetwork();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/Alex/Desktop/pet.txt")); //change the directory for the test file to run
			String line;
			while( (line = br.readLine()) != null){
				String[] info = line.split(",\\s+");
				KnowledgeNode kn = new KnowledgeNode(info, Tag.TagType.FACT);
				animal.add(kn);
			}
			br.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		//building knowledge node network
		for(int i=0; i<animal.size(); i++){
			System.out.print(animal.get(i).toString());
			System.out.println(" threshold is " + animal.get(i).threshold);
			relation.addKN(animal.get(i));
		}		

		//testing fire method
		/*
		HashSet<Tag> resultOne = new HashSet<>();		
		for(KnowledgeNode kn : animal){
			if(kn.name.value.equals("dog") || kn.name.value.equals("cat") || kn.name.value.equals("turtle")){
				resultOne.addAll(relation.fire(kn));
			}
		}	
		for(KnowledgeNode kn : animal){
			if(kn.name.value.equals("pet") || kn.name.value.equals("mammal")){
				resultOne.addAll(relation.fire(kn));
			}
		}
		System.out.println(resultOne.toString());
		*/
		
		//testing excite method
		/*
		for(KnowledgeNode kn : animal){
			if(kn.name.value.equals("dog") || kn.name.value.equals("cat") || kn.name.value.equals("turtle")){
				relation.excite(kn, 10);
			}
		}
		System.out.println(relation.getActiveTags().toString());
		*/
		
		//testing forward searching with or without ply input
		/*
		ArrayList<Tuple> inputs = new ArrayList<>();
		Tuple data1 = new Tuple("dog", 10); inputs.add(data1);
		Tuple data2 = new Tuple("cat", 10); inputs.add(data2);
		Tuple data3 = new Tuple("hamster", 10); inputs.add(data3);
		//Tuple data4 = new Tuple("missing", 10); inputs.add(data4);
		relation.forwardSearch(inputs, 2);
		relation.buildTree();
		System.out.println(relation.getActiveTags().toString());
		System.out.println(relation.getTreeRoots().toString());
		System.out.println(relation.getLinks().toString());		
		*/
		
		//testing backward searching without ply input
		/*
		relation.addFiredTag(Tag.createTagFromString("mammal", TagType.FACT));
		relation.addFiredTag(Tag.createTagFromString("pet", TagType.FACT));
		relation.backwardSearch(2.0/3.0, 20);
		relation.buildTree();
		System.out.println(relation.getActiveTags().toString());
		System.out.println(relation.getTreeRoots().toString());
		System.out.println(relation.getLinks().toString());	
		*/
		
		//testing backward searching with ply input
		/*
		//relation.addFiredTag(Tag.createTagFromString("E", TagType.FACT));
		relation.addFiredTag(Tag.createTagFromString("F", TagType.FACT));
		relation.addFiredTag(Tag.createTagFromString("J", TagType.FACT));
		relation.addFiredTag(Tag.createTagFromString("G", TagType.FACT));
		relation.backwardSearch(2.0/3.0, 55, 1);
		System.out.println(relation.getActiveTags().toString());
		*/
		
		//testing lambda searching
		/*
		ArrayList<Tag> result = new ArrayList<>();
		relation.depthFirstSearch(Tag.createTagFromString("D", TagType.FACT), result);
		ArrayList<Tag> p = relation.pathFinder(Tag.createTagFromString("I", TagType.FACT), Tag.createTagFromString("I", TagType.FACT));
		double t = relation.confidenceCalculator(p);
		System.out.println(p.toString());
		*/
		
		relation.addFiredTag(Tag.createTagFromString("I", TagType.FACT));
		relation.addFiredTag(Tag.createTagFromString("H", TagType.FACT));
		relation.lambdaSearch("F");
		System.out.println(relation.getActiveTags().toString());
		
	}
}
