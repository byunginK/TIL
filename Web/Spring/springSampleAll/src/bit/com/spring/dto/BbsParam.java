package bit.com.spring.dto;

import java.io.Serializable;

public class BbsParam implements Serializable {

	private String choice;
	private String searchWord;
	
	public BbsParam() {
	}

	public BbsParam(String choice, String searchWord) {
		super();
		this.choice = choice;
		this.searchWord = searchWord;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "BbsParam [choice=" + choice + ", searchWord=" + searchWord + "]";
	}
}
