package model;

public class NovelWrite {
	private String novelId;
	private String title;
	private String text;
	private String genre;
	private String desc;

	public NovelWrite(String novelId, String title, String text, String genre, String desc) {
		this.novelId = novelId;
		this.title = title;
		this.text = text;
		this.genre = genre;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

}
