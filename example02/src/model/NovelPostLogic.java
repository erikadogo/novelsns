package model;

import dao.NovelDAO;

public class NovelPostLogic {
	public void execute(Novel novel) {
		NovelDAO dao = new NovelDAO();
		dao.create(novel);
	}
	
	
}
