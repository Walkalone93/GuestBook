package ua.com.codefire.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.codefire.service.GuestBookService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ua.com.codefire.entities.Result;

@Controller
public class HelloController {

    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printWelcome() {
        return "index";
    }

    /**
     * The method allows to obtain records from the database
     *
     * @param session it is used to identify the user and to store his
     * information
     * @return a list of records from the database
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> init(HttpSession session) {
        Integer offset = 0;
        session.setAttribute("offset", offset);
        List<Result> list = guestBookService.getAllRecords(offset);
        Integer amount = guestBookService.getCountRecords();
        JSONArray arr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject sub = new JSONObject();
            sub.put("login", list.get(i).getLogin());
            sub.put("info", list.get(i).getRecord());
            sub.put("date", list.get(i).getDate());
            sub.put("amount", amount);
            sub.put("userLogin", session.getAttribute("login"));
            arr.add(sub);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        ResponseEntity<String> entity = new ResponseEntity<String>((String) arr.toString(), headers, HttpStatus.OK);
        return entity;
    }

    /**
     * The method allows to keep the user in the database
     *
     * @param login user login
     * @param password user password
     * @param session it is used to identify the user and to store his
     * information
     * @return the result of saving user in the database (saved - true)
     */
    @RequestMapping(value = "/addGuest", method = RequestMethod.GET)
    public @ResponseBody
    String addGuest(@RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password,
            HttpSession session) {
        session.setAttribute("login", login);
        String result = guestBookService.saveGuest(login, password);
        JSONArray arr = new JSONArray();
        JSONObject sub = new JSONObject();
        sub.put("result", result);
        arr.add(sub);
        return arr.toString();
    }

    /**
     * The method allows to verify the existence of the user in the database
     *
     * @param login user login
     * @param password user password
     * @param session it is used to identify the user and to store his
     * information
     * @return the result of verifying the existence of the user in the database
     * (there is - true)
     */
    @RequestMapping(value = "/enterGuest", method = RequestMethod.GET)
    public @ResponseBody
    String enterGuest(@RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpSession session) {
        String result = guestBookService.enterGuest(login, password);
        if (login.equals("true")) {
            session.setAttribute("login", login);
        }
        JSONArray arr = new JSONArray();
        JSONObject sub = new JSONObject();
        sub.put("result", result);
        arr.add(sub);
        return arr.toString();
    }

    /**
     * The method allows you to add a specific record in the database and obtain
     * records from the database based on the shift
     *
     * @param login user login
     * @param record record
     * @param session it is used to identify the user and to store his
     * information
     * @return recording database based shift
     */
    @RequestMapping(value = "/addRecord", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> addRecord(@RequestParam("login") String login,
            @RequestParam("record") String record,
            HttpSession session) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy k:mm");
        String date = format.format(d);
        if (session.getAttribute("login").equals(login)) {
            guestBookService.saveRecord(login, record, date);
        }
        Integer offset = 0;
        List<Result> list = guestBookService.getAllRecords(offset);
        JSONArray arr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject sub = new JSONObject();
            sub.put("login", list.get(i).getLogin());
            sub.put("info", list.get(i).getRecord());
            sub.put("date", list.get(i).getDate());
            arr.add(sub);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        ResponseEntity<String> entity = new ResponseEntity<String>((String) arr.toString(), headers, HttpStatus.OK);
        return entity;
    }

    /**
     * The method allows to delete an entry from the database and obtain records
     * from the database based on the shift
     *
     * @param login user login
     * @param message record
     * @param date the date and time when the record was created
     * @param session it is used to identify the user and to store his
     * information
     * @return recording database based shift
     */
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> deleteRecord(@RequestParam("login") String login,
            @RequestParam("message") String message,
            @RequestParam("date") String date,
            HttpSession session) {
        if (session.getAttribute("login").equals(login)) {
            guestBookService.deleteRecord(login, message, date);
        }
        Integer offset = 0;
        List<Result> list = guestBookService.getAllRecords(offset);
        JSONArray arr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject sub = new JSONObject();
            sub.put("login", list.get(i).getLogin());
            sub.put("info", list.get(i).getRecord());
            sub.put("date", list.get(i).getDate());
            arr.add(sub);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        ResponseEntity<String> entity = new ResponseEntity<String>((String) arr.toString(), headers, HttpStatus.OK);
        return entity;
    }

    /**
     * The method allows to obtain records from the database based on the shift
     *
     * @param action indicates the direction of the shift (previous - back)
     * @param session it is used to identify the user and to store his
     * information
     * @return a list of records from the database
     */
    @RequestMapping(value = "/getRecords", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getRecords(@RequestParam("action") String action,
            HttpSession session) {
        Integer offset = 0;
        Integer amountRecords = guestBookService.getCountRecords();
        if (action.equals("previous")) {
            offset = Integer.parseInt(session.getAttribute("offset").toString());
            offset = offset + 10;
            if (amountRecords - offset < 1) {
                offset = amountRecords - (amountRecords % 10);
                session.setAttribute("offset", (offset - 10));
            }
            session.setAttribute("offset", offset);
        } else {
            offset = Integer.parseInt(session.getAttribute("offset").toString());
            offset = offset - 10;
            if (offset < 10) {
                offset = 0;
                session.setAttribute("offset", offset);
            }
            session.setAttribute("offset", offset);
        }

        List<Result> list = guestBookService.getAllRecords(offset);
        JSONArray arr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject sub = new JSONObject();
            sub.put("login", list.get(i).getLogin());
            sub.put("info", list.get(i).getRecord());
            sub.put("date", list.get(i).getDate());
            sub.put("amount", amountRecords);
            sub.put("offset", offset);
            arr.add(sub);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html; charset=utf-8");
        ResponseEntity<String> entity = new ResponseEntity<String>((String) arr.toString(), headers, HttpStatus.OK);
        return entity;
    }
}