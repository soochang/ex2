package com.choa.ex2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeService;

@Controller
@RequestMapping(value="notice/**")
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public void noticeList(Model model, @RequestParam(defaultValue="1") Integer curPage, String kind, String search) throws Exception{
		noticeService.noticeList(model, curPage, kind, search);
	}
	
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public void noticeWrite(){
		
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception{
		int result = noticeService.noticeWrite(noticeDTO);
		String message ="FAIL";
		if(result > 0)
			message="SUCCESS";
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public void noticeUpdate(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception{
		int result = noticeService.noticeUpdate(noticeDTO);
		String message ="FAIL";
		if(result > 0)
			message="SUCCESS";
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
	}
	
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public void noticeDelete(Integer num){
		int result = noticeService.noticeDelete(num);
	}
}
