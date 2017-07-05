package knn;

import java.util.HashMap;
import tags.Tag;

public class KnowledgeNode {
	Tag name;
	HashMap<Tag, Integer> outputs;	// Integer is the value of confidence 
	int activation = 0;			    // int starts at 0 goes to 1 (can be sigmoid, or jump to 1). Increases when sees tag.
    int threshold; 		 	// limit: When activation > threshold : fires output tags (outputTags array). These tags can be lists of rules or facts.
    double age = System.currentTimeMillis() / 1000L; 			// Age timestamp. Set to current UNIX time when node is newly formed.
    int strength = 1;			// Which strength approach to take?
    double maxAge = 60;
    boolean isActivated = false;
    int[] accuracy = {0, 2, 5, 11, 27, 50, 73, 88, 95, 98, 100};	//sigmoid function activation value
    
    public KnowledgeNode(Tag inputName, HashMap<Tag, Integer> outputTags, int threshold) {
        this.name = inputName;
        this.outputs = outputTags;
        this.threshold = threshold;
    }
    
    /**
     * Full constructor
     *
     * @param inputTag    the input tag of the Knowledge Node
     * @param outputTags  the output Tag of the Knowledge Node
     * @param threshold   the threshold of activation
     * @param strength    the strength value to bias activation
     * @param type        the type of the Knowledge Node (linear or sigmoid activation)
     * @param maxAge      threshold age for the node to be discarded
     */
    public KnowledgeNode(Tag inputName, HashMap<Tag, Integer> outputTags, int threshold, int strength, double maxAge) {
        this.name = inputName;
        this.outputs = outputTags;
        this.threshold = threshold;
        this.strength = strength;
        this.maxAge = maxAge;
    }
    
    /**
     * Creates a Knowledge Node from Strings. Assumes all Tags are of the provided TagType.
     *
     * @param inputTag    the input Tag of the Knowledge Node
     * @param outputTags  the output Tag of the Knowledge Node
     * @param tagType     the type of all Tags (input and output)
     */
    public KnowledgeNode(String[] inputInfo, Tag.TagType tagType) {
        this.outputs = new HashMap<Tag, Integer>();
        this.name = Tag.createTagFromString(inputInfo[0], tagType);
        this.threshold = Integer.parseInt(inputInfo[1]);
        for (int i = 2; i < inputInfo.length; i+=2) {        	
        	int confidence = Integer.parseInt(inputInfo[i+1]);
            Tag t = Tag.createTagFromString(inputInfo[i], tagType);
            this.outputs.put(t, confidence);
        }
    }

    /**
     * Ages the current Knowledge Node.
     * @return the age (time elapsed since initialisation/last update)
     */
    public double updateAge() {
        this.age = (System.currentTimeMillis() / 1000L) - this.age;
        return this.age;
    }

    @Override
    public String toString() {
        return name.toString() + " => " + outputs.toString();
    }

    public void increaseActivation() {
        this.activation++;
    }
    
    public void increaseActivation(int value) {
    	this.activation = this.activation + this.accuracy[value];
    }

    public int getActivation() {
        return activation;
    }

}
