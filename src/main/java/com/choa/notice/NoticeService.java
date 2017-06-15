package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;

@Service
public class NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	public NoticeDTO noticeView(int num) throws Exception{
		return noticeDAO.noticeView(num);
	}
	
	public List<NoticeDTO> noticeList(Model model, Integer curPage, String kind, String search) throws Exception{
		List<NoticeDTO> ar = null;
		
		PageMaker pageMaker = new PageMaker(curPage);
		RowMaker rowMaker = pageMaker.getRowMaker(kind, search);
		
		int totalCount = noticeDAO.noticeCount(rowMaker);
		MakePage makePage = pageMaker.getMakePage(totalCount);
		
		ar = noticeDAO.noticeList(rowMaker);
		
		model.addAttribute("list", ar);
		model.addAttribute("makePage", makePage);

		/*request.setAttribute("boardList", ar);
		request.setAttribute("makePage", makePage);
		request.setAttribute("board", "notice");*/

		return ar;
	}
	
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeWrite(noticeDTO);
	}
	
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	public int noticeDelete(int num){
		return noticeDAO.noticeDelete(num);
	}
}
