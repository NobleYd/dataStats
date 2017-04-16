package com.app.buzz.web.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.buzz.bean.StatsCycle;
import com.app.buzz.bean.StatsMethod;
import com.app.buzz.bean.StatsValueType;
import com.app.buzz.entity.StatsItem;
import com.app.buzz.service.StatsItemService;

@Controller
@RequestMapping("/stats_item")
public class StatsItemController extends BaseController {

	@Resource(name = "statsItemServiceImpl")
	private StatsItemService statsItemService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("statsItems", statsItemService.findAll());
		return "stats/stats_item/list";
	}

	@RequestMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("statsValueTypes", StatsValueType.values());
		model.addAttribute("statsMethods", StatsMethod.values());
		model.addAttribute("statsCycles", StatsCycle.values());
		return "stats/stats_item/add";
	}

	@RequestMapping("/save")
	public String save(StatsItem statsItem) {
		statsItemService.save(statsItem);
		return "redirect:list.jhtml";
	}

	@RequestMapping("/async_save")
	@ResponseBody
	public Long save2(StatsItem statsItem) {
		statsItemService.save(statsItem);
		return statsItem.getId();
	}

	@RequestMapping("/edit")
	public String edit(Long id, ModelMap model) {
		StatsItem statsItem = statsItemService.find(id);
		model.addAttribute("statsValueTypes", StatsValueType.values());
		model.addAttribute("statsMethods", StatsMethod.values());
		model.addAttribute("statsCycles", StatsCycle.values());
		model.addAttribute("statsItem", statsItem);
		return "stats/stats_item/edit";
	}

	@RequestMapping("/update")
	public String update(StatsItem statsItem, ModelMap model) {
		StatsItem persist = statsItemService.find(statsItem.getId());
		persist.setShowTitle(statsItem.getShowTitle());
		persist.setSubShowTitle(statsItem.getSubShowTitle());
		persist.setStatsCycle(statsItem.getStatsCycle());
		if (StatsCycle.selfDefine.equals(statsItem.getStatsCycle())) {
			persist.setDivNumber(statsItem.getDivNumber());
		}
		persist.setStatsMethod(statsItem.getStatsMethod());
		statsItemService.update(persist);
		return "redirect:list.jhtml";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		if (id != null)
			statsItemService.delete(id);
		return "redirect:list.jhtml";
	}

	@RequestMapping("/chart/{statsItemIds}")
	public String chart(@PathVariable("statsItemIds") Long[] statsItemIds, Date startDate, Date endDate, Long start, Long end, ModelMap model) {
		if (statsItemIds == null) {
			return ERROR_VIEW;
		}
		List<StatsItem> statsItems = statsItemService.findList(statsItemIds);
		model.addAttribute("statsItems", statsItems);

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("start", start);
		model.addAttribute("end", end);

		return "stats/stats_item/chart";
	}

}
