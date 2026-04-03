package w2l.inspired.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.DailyLogDao;
import w2l.inspired.logical.CustomerDaoDB;
import w2l.inspired.logical.DailyLogDaoDB;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Controller
public class CustomerController {

    public static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerDaoDB customerDao;

    @Autowired
    private DailyLogDaoDB dailyLogDao;


    @RequestMapping({"/"})
    public String index(){return "index";}
    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getCustomersForToday() {
        ModelAndView modelAndView = new ModelAndView("markCustomers");
        modelAndView.addObject("customers", customerDao.getCustomers());
        modelAndView.addObject("serverTime",LocalDate.now());
        return modelAndView;
    }
    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public ModelAndView returnResults(@RequestBody(required = false) String payload) throws IOException {


        ModelAndView mv = new ModelAndView("result");
        mv.addObject("serverTime", LocalDate.now());
        mv.addObject("inputString", payload);
        List<DailyLog> list = new LinkedList<>();


        String[] requestParams = (payload == null || payload.isEmpty()) ? new String[0]
                : payload.split("&"); //1=on&2=on  , off  doesn't exist
        List<Customer> customers = customerDao.getCustomers();
        for(Customer c:customers){
            for(String pair:requestParams) {
                String[] split = pair.split("=");
                if (Integer.parseInt(split[0]) == c.getId()) {
                    list.add(new DailyLog(c));
                }
            }
        }
        mv.addObject("logList", list);
        dailyLogDao.reWriteLog(list);
        mv.addObject("now", list.size());
        return mv;
    }

}
