package com.app.buzz.web.member;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.Filter;
import com.app.Message;
import com.app.Order;
import com.app.buzz.bean.StatsCycle;
import com.app.buzz.bean.StatsMethod;
import com.app.buzz.bean.StatsValueType;
import com.app.buzz.entity.StatsData;
import com.app.buzz.entity.StatsItem;
import com.app.buzz.service.StatsDataService;
import com.app.buzz.service.StatsItemService;

/***
 * @author nobleyd
 */
@Controller
@RequestMapping("/stats_data")
public class StatsDataController extends BaseController {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource(name = "statsItemServiceImpl")
	private StatsItemService statsItemService;

	@Resource(name = "statsDataServiceImpl")
	private StatsDataService statsDataService;

	@RequestMapping("/list")
	public String list(Long id, ModelMap model) {
		List<Order> orders = new ArrayList<Order>();
		List<Filter> filters = new ArrayList<Filter>();
		orders.add(Order.desc("dataTime"));
		if (id != null) {
			StatsItem statsItem = statsItemService.find(id);
			if (statsItem == null) {
				model.addAttribute("statsDatas", Collections.EMPTY_LIST);
				return "stats/stats_data/list";
			}
			filters.add(Filter.eq("statsItem", statsItem, false));
		}
		model.addAttribute("statsDatas", statsDataService.findList(null, filters, orders));
		return "stats/stats_data/list";
	}

	@RequestMapping("/add")
	public String add() {
		return "stats/stats_data/add";
	}

	/***
	 * 向指定统计项添加一条数据。 需要注意的是数据时间不做处理直接使用即可，不需要根据统计周期做调整，这样做是为了记录最原始的数据（比如秒级别），避免以后有调整周期的情况。
	 * 
	 * @param statsData
	 *            待添加数据
	 * @param statsItemId
	 *            统计项ID
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Message save(StatsData statsData, Long statsItemId) {
		try {
			statsDataService.acceptData(statsItemId, statsData.getDataTime(), statsData.getNumberValue(), statsData.getTextValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Message.success("添加数据成功！");
	}

	@RequestMapping("/batch_add")
	public String batchAdd() {
		return "stats/stats_data/batch_add";
	}

	@RequestMapping("/batch_save")
	public String batchSave(String dataTimes, String numberValues, String textValues) {
		String[] dataTimeArr = dataTimes.split(",");
		String[] numberValueArr = numberValues.split(",");
		String[] textValueArr = textValues.split(",");

		for (int i = 0; i < dataTimeArr.length; i++) {
			StatsData statsData = new StatsData();
			statsData.setDataTime(Long.parseLong(dataTimeArr[i]));
			statsData.setNumberValue(new BigDecimal(numberValueArr[i]));
			statsData.setTextValue(textValueArr[i]);
			statsDataService.save(statsData);
		}

		return "redirect:batch_add.jhtml";
	}

	@RequestMapping(value = "/stats")
	@ResponseBody
	public Object stats(Long statsItemId, StatsCycle statsCycle, StatsMethod statsMethod, Date startDate, Date endDate, Long start, Long end) {

		if (start == null && startDate != null) {
			start = statsCycle.date2Long(startDate);
		}
		if (end == null && endDate != null) {
			end = statsCycle.date2Long(endDate);
		}

		if (statsMethod == null) {
			statsMethod = StatsMethod.sum;
		}

		StatsItem statsItem = statsItemService.find(statsItemId);
		Object obj = null;
		switch (statsMethod) {
		case sum:
			obj = statsDataService.findSumData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			break;
		case last:
			if (statsItem.getStatsValueType() == StatsValueType.number) {
				obj = statsDataService.findLastNumberData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			} else {
				obj = statsDataService.findLastTextData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			}
			break;
		case max:
			obj = statsDataService.findMaxData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			break;
		case min:
			obj = statsDataService.findMinData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			break;
		case avg:
			obj = statsDataService.findAverageData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end);
			break;
		}

		return obj;
	}

	@RequestMapping(value = "/stats_list")
	public String stats_list(Long statsItemId, StatsCycle statsCycle, StatsMethod statsMethod, Date startDate, Date endDate, Long start, Long end, ModelMap model) {

		if (statsCycle == null) {
			statsCycle = StatsCycle.second;
		}
		if (statsMethod == null) {
			statsMethod = StatsMethod.sum;
		}

		if (start == null && startDate != null) {
			start = statsCycle.date2Long(startDate);
		}
		if (end == null && endDate != null) {
			end = statsCycle.date2Long(endDate);
		}

		StatsItem statsItem = statsItemService.find(statsItemId);
		switch (statsMethod) {
		case sum:
			model.addAttribute("statsDatas", statsDataService.findSumData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			break;
		case last:
			if (statsItem.getStatsValueType() == StatsValueType.number) {
				model.addAttribute("statsDatas", statsDataService.findLastNumberData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			} else {
				model.addAttribute("statsDatas", statsDataService.findLastTextData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			}
			break;
		case max:
			model.addAttribute("statsDatas", statsDataService.findMaxData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			break;
		case min:
			model.addAttribute("statsDatas", statsDataService.findMinData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			break;
		case avg:
			model.addAttribute("statsDatas", statsDataService.findAverageData(statsItemId, statsCycle, statsItem.getDivNumber(), start, end));
			break;
		}

		return "stats/stats_data/stats_list";
	}

}
