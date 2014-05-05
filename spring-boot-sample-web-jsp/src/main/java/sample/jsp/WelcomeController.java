/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.jsp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sample.jsp.dao.UserDAO;
import sample.jsp.model.User;

@Controller
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/user/insert")
    public String insertUser(Map<String, Object> model) {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        userDAO.create(user);
        model.put("user", user);
        return "userAdded";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, ModelMap model) {
        System.out.println("Posted user:" + user);
        userDAO.create(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public ModelAndView showAddUserPage() {
        return new ModelAndView("addUser", "command", new User());
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, ModelMap model) {
        System.out.println("User to delete:" + id);
        userDAO.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/user/list")
    public String listUsers(Map<String, Object> model) {
        List<User> users = userDAO.read();
        model.put("users", users);
        return "userList";
    }

}
