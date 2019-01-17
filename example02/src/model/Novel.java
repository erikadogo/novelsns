package model;

public class Novel {
	private String novelId;
	private String title;
	private String text;
	private String genre;
	private String summary;

	public Novel(String novelId, String title, String text, String genre, String summary) {
		this.novelId = novelId;
		this.title = title;
		this.text = text;
		this.genre = genre;
		this.summary = summary;
	}

	public String getNovelId() {
		return novelId;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String getGenre() {
		return genre;
	}

	public String getSummary() {
		return summary;
	}

}
